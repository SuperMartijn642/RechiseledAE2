package com.supermartijn642.rechiseled.ae.chiseling_pattern.screen;

import appeng.api.crafting.PatternDetailsHelper;
import appeng.core.definitions.AEItems;
import com.supermartijn642.core.gui.BaseContainerType;
import com.supermartijn642.core.gui.BlockEntityBaseContainer;
import com.supermartijn642.core.gui.CustomSlot;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.ChiselingPatternEncoderBlockEntity;
import com.supermartijn642.rechiseled.api.chiseling.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

/**
 * Created 16/02/2026 by SuperMartijn642
 */
public class ChiselingPatternEncoderContainer extends BlockEntityBaseContainer<ChiselingPatternEncoderBlockEntity> {

    public static final BaseContainerType<ChiselingPatternEncoderContainer> TYPE = BaseContainerType.create(
        (container, buffer) -> buffer.writeBlockPos(container.blockEntityPos),
        (player, buffer) -> new ChiselingPatternEncoderContainer(player, buffer.readBlockPos())
    );

    private final boolean isClient;
    public ChiselingRecipe currentRecipe = null;
    public ChiselingEntry currentEntry = null;
    public ChiselingBlockShape shape = null;
    public boolean connecting = false;

    /**
     * Used to find encoded pattern slot and overwrite the displayed item.
     */
    public Slot encodedPatternSlot;

    public ChiselingPatternEncoderContainer(Player player, BlockPos blockEntityPos){
        super(TYPE, player, blockEntityPos);
        //noinspection resource
        this.isClient = player.level().isClientSide();
        this.addSlots();
        this.findRecipe();
    }

    @Override
    protected void addSlots(Player player, @NotNull ChiselingPatternEncoderBlockEntity object){
        // Input and output
        this.addSlot(CustomSlot.builder()
            .position(159, 110)
            .canExtract(false)
            .inserter(stack -> {
                this.object.trySetInput(stack.getItem());
                return 0;
            })
            .filter(stack -> this.object.isValidInput(stack.getItem()))
            .getter(() -> this.object.getInputAsStack())
            .onChange((oldStack, newStack) -> this.findRecipe())
            .build().getVanillaSlot()
        );
        this.addSlot(CustomSlot.builder()
            .position(204, 110)
            .canExtract(false)
            .inserter(stack -> {
                this.object.trySetOutput(stack.getItem());
                return 0;
            })
            .filter(stack -> this.object.isValidOutput(stack.getItem()))
            .getter(() -> {
                Item item = this.object.getOutput();
                return item != null && this.currentRecipe != null && this.currentRecipe.contains(item) ? this.object.getOutputAsStack() : ItemStack.EMPTY;
            })
            .onChange((oldStack, newStack) -> this.findRecipe())
            .build().getVanillaSlot()
        );
        // Patterns
        this.addSlot(CustomSlot.builder()
            .position(247, 84)
            .getter(() -> this.object.getBlankPatterns())
            .setter(stack -> this.object.setBlankPatterns(stack))
            .filter(AEItems.BLANK_PATTERN::is)
            .build().getVanillaSlot()
        );
        this.encodedPatternSlot = this.addSlot(CustomSlot.builder()
            .position(244, 128)
            .size(24)
            .getter(() -> this.object.getEncodedPatterns())
            .setter(stack -> this.object.setEncodedPatterns(stack))
            .filter(PatternDetailsHelper::isEncodedPattern)
            .build().getVanillaSlot()
        );
        // Player inventory
        this.addPlayerSlots(57, 165);
    }

    public Item getRecipeItem(){
        Item input = this.object.getInput();
        if(input == null)
            return Items.AIR;
        ChiselingRecipe recipe = ChiselingRecipeManager.get(this.isClient).getRecipeForItem(input);
        if(recipe == null)
            return Items.AIR;
        Item item = this.object.getOutput();
        if(item == null || !recipe.contains(item))
            item = input;
        return item;
    }

    @Override
    public boolean stillValid(Player player){
        if(!super.stillValid(player))
            return false;
        this.findRecipe();
        return true;
    }

    void findRecipe(){
        // Check if the current recipe is still applicable
        Item item = this.getRecipeItem();
        ChiselingRecipe recipe = ChiselingRecipeManager.get(this.isClient).getRecipeForItem(item);
        if(recipe != null && this.currentRecipe == recipe && this.currentEntry.contains(item)
            && (this.connecting ? this.currentEntry.getConnectingItem(this.shape).item() == item : this.currentEntry.getRegularItem(this.shape).item() == item))
            return;

        // Find a matching recipe
        if(recipe != null){
            this.currentRecipe = recipe;
            for(ChiselingEntry entry : this.currentRecipe.entries()){
                if(entry.contains(item)){
                    for(ChiselingBlockShape shape : ChiselingBlockShape.values()){
                        if(this.connecting && entry.hasConnectingItem(shape) && entry.getConnectingItem(shape).item() == item){
                            this.currentEntry = entry;
                            this.shape = shape;
                            return;
                        }else if(entry.hasRegularItem(shape) && entry.getRegularItem(shape).item() == item){
                            this.currentEntry = entry;
                            this.shape = shape;
                            this.connecting = false;
                            return;
                        }else if(!this.connecting && entry.hasConnectingItem(shape) && entry.getConnectingItem(shape).item() == item){
                            this.currentEntry = entry;
                            this.shape = shape;
                            this.connecting = true;
                            return;
                        }
                    }
                }
            }
        }
        this.currentRecipe = null;
        this.currentEntry = null;
        this.shape = null;
        this.connecting = false;
    }

    public void setCurrentEntry(int index, ChiselingBlockShape shape, boolean connecting){
        if(this.currentRecipe == null || this.currentEntry == null || index >= this.currentRecipe.entries().size())
            return;

        ChiselingEntry entry = this.currentRecipe.entries().get(index);
        if(connecting ? !entry.hasConnectingItem(shape) : !entry.hasRegularItem(shape))
            return;

        this.currentEntry = entry;
        this.shape = shape;
        this.connecting = connecting;
        ItemWithWorth target = connecting ? entry.getConnectingItem(shape) : entry.getRegularItem(shape);
        this.object.setOutput(target.item());
    }

    public boolean canEncode(){
        return this.currentEntry != null && (!this.object.getBlankPatterns().isEmpty() || !this.object.getEncodedPatterns().isEmpty());
    }

    public void encodePattern(){
        this.object.writePattern();
    }

    @Override
    public void clicked(int slotIndex, int button, ClickType clickType, Player player){
        if(clickType == ClickType.PICKUP && (slotIndex == 0 || slotIndex == 1)){
            ItemStack carried = this.getCarried();
            Item item = carried.isEmpty() ? null : carried.getItem();
            if(slotIndex == 0)
                this.object.trySetInput(item);
            else
                this.object.trySetOutput(item);
            return;
        }
        super.clicked(slotIndex, button, clickType, player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index){
        // Ignore shift-clicks on input and output
        if(index == 0 || index == 1)
            return ItemStack.EMPTY;

        Slot slot = this.getSlot(index);
        ItemStack stack = slot.getItem();
        if(stack.isEmpty())
            return ItemStack.EMPTY;

        // Handle transfer from pattern slots
        if(index == 2 || index == 3){
            if(!this.moveItemStackTo(stack, 4, this.slots.size(), true))
                return ItemStack.EMPTY;
            slot.set(stack);
            return stack;
        }

        // Handle inventory click
        if(AEItems.BLANK_PATTERN.is(stack) || PatternDetailsHelper.isEncodedPattern(stack)){
            if(!this.moveItemStackTo(stack, 2, 4, false))
                return ItemStack.EMPTY;
            slot.set(stack);
            return stack;
        }
        if(ChiselingRecipeManager.get(this.isClient).getRecipeForItem(stack.getItem()) != null)
            this.object.setInput(stack.getItem());
        return ItemStack.EMPTY;
    }
}
