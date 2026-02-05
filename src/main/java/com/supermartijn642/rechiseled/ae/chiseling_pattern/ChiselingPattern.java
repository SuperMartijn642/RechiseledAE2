package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import appeng.api.crafting.IPatternDetails;
import appeng.api.crafting.PatternDetailsTooltip;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.api.stacks.KeyCounter;
import appeng.blockentity.crafting.IMolecularAssemblerSupportedPattern;
import com.supermartijn642.core.registry.Registries;
import com.supermartijn642.rechiseled.api.chiseling.ChiselingRecipe;
import com.supermartijn642.rechiseled.api.chiseling.ChiselingRecipeManager;
import com.supermartijn642.rechiseled.api.chiseling.ItemWithWorth;
import com.supermartijn642.rechiseled.api.chiseling.conversion.ChiselingConversionHelper;
import com.supermartijn642.rechiseled.api.chiseling.conversion.ConversionResult;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * Created 13/02/2026 by SuperMartijn642
 */
public class ChiselingPattern implements IPatternDetails, IMolecularAssemblerSupportedPattern {
    // The slot index in the 3x3 crafting grid that we insert our item into (in the MAC)
    private static final int CRAFTING_GRID_SLOT = 4;

    private final AEItemKey definition;
    private final Item input, output;
    private final int inputCount, outputCount;
    private final IInput[] inputs;
    private final List<GenericStack> outputs;

    public ChiselingPattern(AEItemKey definition, Level level){
        this.definition = definition;

        var encodedPattern = definition.get(EncodedChiselingPattern.COMPONENT_TYPE);
        if(encodedPattern == null)
            throw new IllegalArgumentException("Given item does not encode a chiseling pattern: " + definition);
        else if(encodedPattern.containsMissingContent())
            throw new IllegalArgumentException("Pattern references missing content");

        this.input = Objects.requireNonNull(encodedPattern.input());
        this.output = Objects.requireNonNull(encodedPattern.output());

        // Find recipe
        ChiselingRecipe recipe = ChiselingRecipeManager.get(level).getRecipeForItem(this.input);
        if(recipe == null)
            throw new IllegalStateException("Chiseling recipe for item '" + Registries.ITEMS.getIdentifier(this.input) + "' no longer exists!");
        if(!recipe.contains(this.output))
            throw new IllegalStateException("Chiseling recipe for item '" + Registries.ITEMS.getIdentifier(this.input) + "' no longer contains the encoded output item '" + Registries.ITEMS.getIdentifier(this.output) + "'!");

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

        // Cache representative inputs and outputs
        GenericStack[] possibleInputs = {new GenericStack(AEItemKey.of(ChiselingPattern.this.input), this.inputCount)};
        this.inputs = new IInput[]{
            new IInput() {
                @Override
                public GenericStack[] getPossibleInputs(){
                    return possibleInputs;
                }

                @Override
                public long getMultiplier(){
                    return 1;
                }

                @Override
                public boolean isValid(AEKey input, Level level){
                    return input.matches(possibleInputs[0]);
                }

                @Override
                public @Nullable AEKey getRemainingKey(AEKey template){
                    return null;
                }
            }
        };
        this.outputs = List.of(new GenericStack(AEItemKey.of(this.output), this.outputCount));
    }

    @Override
    public int hashCode(){
        return this.definition.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        return obj != null && obj.getClass() == this.getClass()
            && ((ChiselingPattern)obj).definition.equals(this.definition);
    }

    @Override
    public AEItemKey getDefinition(){
        return this.definition;
    }

    @Override
    public IInput[] getInputs(){
        return this.inputs;
    }

    @Override
    public List<GenericStack> getOutputs(){
        return this.outputs;
    }

    @Override
    public PatternDetailsTooltip getTooltip(Level level, TooltipFlag flags){
        var tooltip = new PatternDetailsTooltip(PatternDetailsTooltip.OUTPUT_TEXT_CRAFTS);
        tooltip.addInputsAndOutputs(this);
        return tooltip;
    }

    public static PatternDetailsTooltip getInvalidTooltip(ItemStack stack, Level level, @Nullable Exception cause, TooltipFlag flags) {
        return new PatternDetailsTooltip(PatternDetailsTooltip.OUTPUT_TEXT_CRAFTS);
    }

    @Override
    public ItemStack assemble(CraftingInput input, Level level){
        if(input.size() != 1)
            return ItemStack.EMPTY;

        ItemStack item = input.getItem(0);
        if(item.isEmpty() || item.getItem() != this.input || item.getCount() < this.inputCount)
            return ItemStack.EMPTY;

        return new ItemStack(this.output, this.outputCount);
    }

    @Override
    public boolean isItemValid(int slot, AEItemKey key, Level level){
        return slot == CRAFTING_GRID_SLOT && key.is(this.input);
    }

    @Override
    public boolean isSlotEnabled(int slot){
        return slot == CRAFTING_GRID_SLOT;
    }

    @Override
    public void fillCraftingGrid(KeyCounter[] table, CraftingGridAccessor gridAccessor){
        var entry = table[0].getFirstEntry();
        if(entry != null && entry.getKey() instanceof AEItemKey itemKey && entry.getLongValue() >= this.inputCount){
            gridAccessor.set(CRAFTING_GRID_SLOT, itemKey.toStack(this.inputCount));
            table[0].remove(entry.getKey(), this.inputCount);
        }
    }
}
