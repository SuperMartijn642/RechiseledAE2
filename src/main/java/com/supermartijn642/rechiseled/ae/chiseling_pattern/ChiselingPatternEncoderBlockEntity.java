package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import appeng.api.crafting.PatternDetailsHelper;
import appeng.core.definitions.AEItems;
import com.supermartijn642.core.block.BaseBlockEntity;
import com.supermartijn642.core.registry.Registries;
import com.supermartijn642.rechiseled.ae.RechiseledAE;
import com.supermartijn642.rechiseled.api.chiseling.ChiselingRecipe;
import com.supermartijn642.rechiseled.api.chiseling.ChiselingRecipeManager;
import com.supermartijn642.rechiseled.api.chiseling.ItemWithWorth;
import com.supermartijn642.rechiseled.api.chiseling.conversion.ChiselingConversionHelper;
import com.supermartijn642.rechiseled.api.chiseling.conversion.ConversionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

/**
 * Created 14/02/2026 by SuperMartijn642
 */
public class ChiselingPatternEncoderBlockEntity extends BaseBlockEntity {

    private Item input, output;
    private int inputCount, outputCount;
    private ItemStack inputStack, outputStack;
    private ItemStack blankPatterns = ItemStack.EMPTY, encodedPatterns = ItemStack.EMPTY;
    private int rotationOffset = -1;

    public ChiselingPatternEncoderBlockEntity(BlockPos pos, BlockState state){
        super(RechiseledAE.chiseling_pattern_encoder_entity, pos, state);
    }

    public Item getInput(){
        return this.input;
    }

    public ItemStack getInputAsStack(){
        if(this.input == null)
            return ItemStack.EMPTY;
        if(this.inputStack == null)
            this.inputStack = new ItemStack(this.input, Math.max(1, this.inputCount));
        return this.inputStack;
    }

    public boolean isValidInput(Item item){
        return item == null || ChiselingRecipeManager.get(this.level).getRecipeForItem(item) != null;
    }

    public void setInput(Item input){
        if(!this.isValidInput(input))
            throw new IllegalArgumentException("Invalid input: " + input);
        this.input = input;
        this.inputStack = null;
        // Update output
        if(input == null || this.output == null){
            this.output = input;
            this.outputStack = null;
        }else{
            ChiselingRecipe recipe = ChiselingRecipeManager.get(this.level.isClientSide).getRecipeForItem(this.input);
            if(recipe == null || !recipe.contains(this.output)){
                this.output = this.input;
                this.outputStack = null;
            }
        }
        // Update block state
        if(!this.level.isClientSide){
            BlockState state = this.getBlockState();
            if((input == null) == state.getValue(ChiselingPatternEncoderBlock.ON))
                this.level.setBlock(this.worldPosition, state.setValue(ChiselingPatternEncoderBlock.ON, input != null), Block.UPDATE_CLIENTS);
        }
        this.updateConversionFactors();
        this.dataChanged();
    }

    public void trySetInput(Item input){
        if(this.isValidInput(input))
            this.setInput(input);
    }

    public Item getOutput(){
        return this.output;
    }

    public ItemStack getOutputAsStack(){
        if(this.output == null)
            return ItemStack.EMPTY;
        if(this.outputStack == null)
            this.outputStack = new ItemStack(this.output, Math.max(1, this.outputCount));
        return this.outputStack;
    }

    public boolean isValidOutput(Item output){
        if(output == null || this.input == null)
            return false;
        ChiselingRecipe recipe = ChiselingRecipeManager.get(this.level).getRecipeForItem(this.input);
        return recipe != null && recipe.contains(output);
    }

    public void setOutput(Item output){
        if(!this.isValidOutput(output))
            throw new IllegalArgumentException("Invalid output: " + output);
        this.output = output;
        this.outputStack = null;
        this.updateConversionFactors();
        this.dataChanged();
    }

    public void trySetOutput(Item output){
        if(this.isValidOutput(output))
            this.setOutput(output);
    }

    public int getInputCount(){
        return this.inputCount;
    }

    public int getOutputCount(){
        return this.outputCount;
    }

    private void updateConversionFactors(){
        if(this.input == null || this.output == null){
            this.inputCount = this.outputCount = 0;
            return;
        }
        ChiselingRecipe recipe = ChiselingRecipeManager.get(this.level.isClientSide).getRecipeForItem(this.input);
        if(recipe == null || !recipe.contains(this.output)){
            this.inputCount = this.outputCount = 0;
            return;
        }

        // Calculate conversion factor
        ItemWithWorth inputWorth = recipe.getWorth(this.input);
        ItemWithWorth outputWorth = recipe.getWorth(this.output);
        int inputCount, outputCount = -1;
        for(inputCount = 1; inputCount <= 100; inputCount++){
            ConversionResult conversion = ChiselingConversionHelper.convert(inputCount, inputWorth, outputWorth);
            if(conversion.result() >= 1 && conversion.leftover() == 0){
                outputCount = conversion.result();
                break;
            }
        }
        if(outputCount < 0)
            throw new IllegalStateException("No optimal conversion factor!");
        this.inputCount = inputCount;
        this.outputCount = outputCount;
    }

    public ItemStack getBlankPatterns(){
        return this.blankPatterns;
    }

    public void setBlankPatterns(ItemStack stack){
        if(!stack.isEmpty() && !AEItems.BLANK_PATTERN.isSameAs(stack))
            throw new IllegalArgumentException("Input must be a blank pattern!");
        this.blankPatterns = stack;
        this.dataChanged();
    }

    public ItemStack getEncodedPatterns(){
        return this.encodedPatterns;
    }

    public void setEncodedPatterns(ItemStack stack){
        if(!stack.isEmpty() && !PatternDetailsHelper.isEncodedPattern(stack))
            throw new IllegalArgumentException("Input must be a blank pattern!");
        this.encodedPatterns = stack;
        this.dataChanged();
    }

    public void writePattern(){
        if(this.input == null || this.output == null
            || (this.encodedPatterns.isEmpty() && this.blankPatterns.isEmpty()))
            return;

        // Validate recipe
        ChiselingRecipe recipe = ChiselingRecipeManager.get(this.level.isClientSide).getRecipeForItem(this.input);
        if(recipe == null || !recipe.contains(this.output))
            return;

        // Decrease blank patterns stack
        if(this.encodedPatterns.isEmpty()){
            this.blankPatterns.shrink(1);
            this.encodedPatterns = new ItemStack(RechiseledAE.chiseling_pattern, 1);
        }else
            this.encodedPatterns = new ItemStack(RechiseledAE.chiseling_pattern, this.encodedPatterns.getCount());

        // Encode pattern
        new EncodedChiselingPattern(this.input, this.output).serialize(this.encodedPatterns);
        this.dataChanged();
    }

    public int getRotationOffset(){
        if(this.rotationOffset == -1){
            Random random = new Random(this.getBlockPos().asLong());
            random.nextLong();
            random.nextLong();
            random.nextLong();
            this.rotationOffset = random.nextInt(4);
            this.dataChanged();
        }
        return this.rotationOffset;
    }

    public void increaseRotationOffset(){
        this.rotationOffset = (this.getRotationOffset() + 1) % 4;
        this.dataChanged();
    }

    @Override
    protected CompoundTag writeData(){
        CompoundTag data = new CompoundTag();
        if(this.input != null){
            data.putString("input", Registries.ITEMS.getIdentifier(this.input).toString());
            data.putInt("inputCount", this.inputCount);
        }
        if(this.output != null){
            data.putString("output", Registries.ITEMS.getIdentifier(this.output).toString());
            data.putInt("outputCount", this.outputCount);
        }
        if(!this.blankPatterns.isEmpty())
            data.put("blankPatterns", this.blankPatterns.save(new CompoundTag()));
        if(!this.encodedPatterns.isEmpty())
            data.put("encodedPatterns", this.encodedPatterns.save(new CompoundTag()));
        if(this.rotationOffset != -1)
            data.putInt("rotationOffset", this.rotationOffset);
        return data;
    }

    @Override
    protected void readData(CompoundTag data){
        this.input = data.contains("input") ? Registries.ITEMS.getValue(new ResourceLocation(data.getString("input"))) : null;
        this.inputCount = data.contains("inputCount") ? data.getInt("inputCount") : 0;
        this.inputStack = null;
        this.output = data.contains("output") ? Registries.ITEMS.getValue(new ResourceLocation(data.getString("output"))) : null;
        this.outputCount = data.contains("outputCount") ? data.getInt("outputCount") : 0;
        this.outputStack = null;
        this.blankPatterns = data.contains("blankPatterns") ? ItemStack.of(data.getCompound("blankPatterns")) : ItemStack.EMPTY;
        this.encodedPatterns = data.contains("encodedPatterns") ? ItemStack.of(data.getCompound("encodedPatterns")) : ItemStack.EMPTY;
        this.rotationOffset = data.contains("rotationOffset") ? data.getInt("rotationOffset") : -1;
    }
}
