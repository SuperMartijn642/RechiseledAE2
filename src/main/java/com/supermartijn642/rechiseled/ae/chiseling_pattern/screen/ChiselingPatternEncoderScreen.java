package com.supermartijn642.rechiseled.ae.chiseling_pattern.screen;

import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.ScreenUtils;
import com.supermartijn642.core.gui.widget.BaseContainerWidget;
import com.supermartijn642.core.gui.widget.WidgetRenderContext;
import com.supermartijn642.core.gui.widget.premade.ScissorWidget;
import com.supermartijn642.core.gui.widget.premade.ScrollbarWidget;
import com.supermartijn642.core.gui.widget.premade.TextFieldWidget;
import com.supermartijn642.rechiseled.ae.RechiseledAE;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.packet.PacketSelectEntry;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.widgets.*;
import com.supermartijn642.rechiseled.api.chiseling.*;
import com.supermartijn642.rechiseled.screen.preview.PreviewMode;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created 16/02/2026 by SuperMartijn642
 */
public class ChiselingPatternEncoderScreen extends BaseContainerWidget<ChiselingPatternEncoderContainer> {

    private static final ResourceLocation BACKGROUND = RechiseledAE.identifier("textures/screen/encoder_background.png");
    private static final ResourceLocation SCROLLER = RechiseledAE.identifier("textures/screen/scroller.png");
    private static final int OPTION_ROWS = 6, OPTION_COLUMNS = 6;

    private static PreviewMode previewMode = PreviewMode.SINGLE;
    private static String searchText = "";
    private static String formattedSearchText = "";

    private final Component title;
    private final int titleWidth;
    private final List<EntryButtonWidget> entryButtons = new ArrayList<>();
    private TextFieldWidget searchField;

    private DisplayEntry lastContainerEntry;
    private ChiselingRecipe recipe;
    private final List<DisplayEntry> allEntries = new ArrayList<>();
    private final List<DisplayEntry> visibleEntries = new ArrayList<>();
    private DisplayEntry selectedEntry;
    private boolean connecting = false;
    private float scrollOffset;
    private int scrollIndexOffset;

    public ChiselingPatternEncoderScreen(){
        super(0, 0, 274, 249);
        this.title = TextComponents.translation("rechiseledae.chiseling_pattern_encoder.title").get();
        this.titleWidth = Math.min(ClientUtils.getFontRenderer().width(this.title), 70);
    }

    @Override
    protected void addWidgets(){
        // Entry buttons
        ScissorWidget scissorWidget = this.addWidget(ScissorWidget.create(8, 18, 120, 132));
        for(int row = 0; row < OPTION_ROWS + 1; row++){
            for(int column = 0; column < OPTION_COLUMNS; column++){
                int index = row * OPTION_COLUMNS + column;
                int x = 8 + 20 * column;
                int y = 18 + 22 * row;

                EntryButtonWidget button = new EntryButtonWidget(
                    x, y,
                    () -> this.getDisplayEntry(index),
                    () -> this.selectedEntry,
                    () -> this.selectDisplayEntry(this.getDisplayEntry(index)),
                    () -> this.connecting
                );
                scissorWidget.addWidget(button);
                this.entryButtons.add(button);
            }
        }
        this.addWidget(ScrollbarWidget.builder(132)
            .position(130, 18)
            .width(12)
            .scrollerHeight(14)
            .scroller(SCROLLER)
            .scrollValue(
                () -> this.scrollOffset,
                () -> 0,
                () -> (int)Math.ceil((float)this.visibleEntries.size() / OPTION_COLUMNS) - OPTION_ROWS
            )
            .onChange((oldValue, newValue) -> this.setScrollOffset((float)newValue))
            .smoothScrolling()
            .scrollWheelValueChange(0)
            .background(null)
            .build()
        );

        // Search
        this.searchField = this.addWidget(new TextFieldWidget(18 + this.titleWidth, 4, 122 - this.titleWidth - 11, 12, "", 20, s -> {
            s = s.trim();
            if(!s.equals(searchText)){
                searchText = s;
                formattedSearchText = s.toLowerCase();
                this.updateDisplayEntries();
            }
        }));
        this.searchField.setSuggestion("Search");
        this.searchField.setTextSuppressed(searchText);
        this.searchField.setActive(false);

        // Preview
        this.addWidget(new EntryPreviewWidget(152, 7, 87, 69, () -> {
            DisplayEntry display = this.selectedEntry;
            ItemWithWorth item = display == null ? null : display.getItem(this.connecting);
            return item == null ? null : item.item();
        }, () -> previewMode));
        Supplier<Boolean> enablePreviewButtons = () -> {
            DisplayEntry display = this.selectedEntry;
            ItemWithWorth item = display == null ? null : display.getItem(this.connecting);
            return item != null && item.item() instanceof BlockItem;
        };
        this.addWidget(new PreviewModeButtonWidget(245, 8, PreviewMode.PANEL, () -> previewMode, enablePreviewButtons, () -> previewMode = PreviewMode.PANEL));
        this.addWidget(new PreviewModeButtonWidget(245, 31, PreviewMode.ROW, () -> previewMode, enablePreviewButtons, () -> previewMode = PreviewMode.ROW));
        this.addWidget(new PreviewModeButtonWidget(245, 54, PreviewMode.SINGLE, () -> previewMode, enablePreviewButtons, () -> previewMode = PreviewMode.SINGLE));

        // Shape and connecting buttons
        this.addWidget(new ConnectingToggleWidget(201, 129, () -> this.connecting, () -> this.selectedEntry, this::toggleConnecting));
        this.addWidget(new ShapeSelectionWidget(195, 93, ChiselingBlockShape.BLOCK, () -> this.selectedEntry, () -> this.changeShape(ChiselingBlockShape.BLOCK)));
        this.addWidget(new ShapeSelectionWidget(206, 93, ChiselingBlockShape.STAIRS, () -> this.selectedEntry, () -> this.changeShape(ChiselingBlockShape.STAIRS)));
        this.addWidget(new ShapeSelectionWidget(217, 93, ChiselingBlockShape.SLAB, () -> this.selectedEntry, () -> this.changeShape(ChiselingBlockShape.SLAB)));

        // Encode button
        this.addWidget(new EncodeButtonWidget(246, 104, () -> this.container.canEncode()));
    }

    @Override
    public void update(){
        this.container.findRecipe();
        this.updateRecipe();
        super.update();
    }

    @Override
    public Component getNarrationMessage(){
        return this.title;
    }

    @Override
    public void renderBackground(WidgetRenderContext context, int mouseX, int mouseY){
        ScreenUtils.bindTexture(BACKGROUND);
        ScreenUtils.drawTexture(context.poseStack(), 0, 0, this.width, this.height);
        super.renderBackground(context, mouseX, mouseY);
    }

    @Override
    public void render(WidgetRenderContext context, int mouseX, int mouseY){
        super.render(context, mouseX, mouseY);
        // Highlight blocks that can chiseled
        for(int index = 4; index < this.container.slots.size(); index++){
            Slot slot = this.container.getSlot(index);
            ItemStack stack = slot.getItem();
            if(stack.isEmpty() || !stack.hasTag())
                continue;

            // Check if the stack is in the current recipe
            if(this.recipe != null && this.recipe.contains(stack.getItem()))
                ScreenUtils.fillRect(context.poseStack(), slot.x + 13, slot.y, 3, 3, 52 / 355f, 108 / 355f, 173 / 355f, 0.5f);
            else if(ChiselingRecipeManager.get(true).getRecipeForItem(stack.getItem()) != null)
                ScreenUtils.fillRect(context.poseStack(), slot.x + 13, slot.y, 3, 3, 1, 207 / 355f, 74 / 355f, 0.5f);
        }
    }

    @Override
    public void renderForeground(WidgetRenderContext context, int mouseX, int mouseY){
        super.renderForeground(context, mouseX, mouseY);
        ScreenUtils.drawString(context.poseStack(), this.title, 8, 6);
        ScreenUtils.drawString(context.poseStack(), ClientUtils.getPlayer().getInventory().getName(), 57, 154);
    }

    private void updateDisplayEntries(){
        this.visibleEntries.clear();
        for(DisplayEntry entry : this.allEntries){
            if(this.matchesFilters(entry))
                this.visibleEntries.add(entry);
        }
        this.setScrollOffset(this.scrollOffset);
    }

    private void updateRecipe(){
        if(this.container.currentRecipe == null && this.recipe == null)
            return;
        if(this.container.currentRecipe != null && this.recipe == this.container.currentRecipe){
            if(this.lastContainerEntry != null && this.lastContainerEntry.entry() == this.container.currentEntry && this.lastContainerEntry.shape() == this.container.shape)
                return;
            if(this.selectedEntry != null && this.selectedEntry.entry() == this.container.currentEntry && this.selectedEntry.shape() == this.container.shape){
                this.lastContainerEntry = new DisplayEntry(-1, this.container.currentEntry, this.container.shape);
                return;
            }
        }
        this.recipe = this.container.currentRecipe;
        this.lastContainerEntry = new DisplayEntry(-1, this.container.currentEntry, this.container.shape);

        // Reset everything
        this.allEntries.clear();
        this.selectedEntry = null;
        this.connecting = false;
        this.searchField.setTextSuppressed("");
        searchText = "";
        formattedSearchText = "";
        this.searchField.setActive(this.recipe != null);

        // Update for new recipe
        DisplayEntry matchingDisplay = null;
        if(this.recipe != null){
            Item currentItem = this.container.getRecipeItem();
            for(ChiselingBlockShape shape : ChiselingBlockShape.values()){
                for(int i = 0; i < this.recipe.entries().size(); i++){
                    ChiselingEntry entry = this.recipe.entries().get(i);
                    if(entry.hasShape(shape)){
                        DisplayEntry display = new DisplayEntry(i, entry, shape);
                        this.allEntries.add(display);
                        if(matchingDisplay == null && entry == this.container.currentEntry){
                            if(entry.hasRegularItem(shape) && entry.getRegularItem(shape).item() == currentItem){
                                matchingDisplay = display;
                                this.connecting = false;
                            }else if(entry.hasConnectingItem(shape) && entry.getConnectingItem(shape).item() == currentItem){
                                matchingDisplay = display;
                                this.connecting = true;
                            }
                        }
                    }
                }
            }
        }
        this.updateDisplayEntries();
        if(matchingDisplay == null)
            this.setScrollOffset(0);
        else
            this.selectDisplayEntry(matchingDisplay);
    }

    private boolean matchesFilters(DisplayEntry entry){
        return (entry.hasItem(false) && this.doesItemMatchSearch(entry.getItem(false).item()))
            || (entry.hasItem(true) && this.doesItemMatchSearch(entry.getItem(true).item()));
    }

    private boolean doesItemMatchSearch(Item item){
        if(formattedSearchText.isEmpty())
            return true;

        boolean isModSearch = formattedSearchText.charAt(0) == '@';
        if(isModSearch){
            if(formattedSearchText.length() == 1)
                return true;
            ResourceLocation identifier = BuiltInRegistries.ITEM.getKey(item);
            if(identifier.getNamespace().toLowerCase().startsWith(formattedSearchText.substring(1)))
                return true;
            String modName = ModList.get().getModContainerById(identifier.getNamespace()).map(ModContainer::getModInfo).map(IModInfo::getDisplayName).orElse(null);
            return modName != null && modName.toLowerCase().startsWith(formattedSearchText);
        }

        String name = TextComponents.item(item).format();
        return name.toLowerCase().contains(formattedSearchText);
    }

    private DisplayEntry getDisplayEntry(int index){
        index += this.scrollIndexOffset * OPTION_COLUMNS;
        return index >= 0 && index < this.visibleEntries.size() ? this.visibleEntries.get(index) : null;
    }

    private void selectDisplayEntry(DisplayEntry entry){
        if(entry == null || this.selectedEntry == entry)
            return;
        this.selectedEntry = entry;
        if(!entry.hasItem(this.connecting))
            this.connecting = !this.connecting;
        if(this.container.currentEntry != entry.entry() || this.container.shape != entry.shape())
            RechiseledAE.CHANNEL.sendToServer(new PacketSelectEntry(entry.entryIndex(), entry.shape(), this.connecting));
        // Scroll to entry
        int index = this.visibleEntries.indexOf(entry);
        if(index < 0)
            return;
        int row = index / OPTION_COLUMNS;
        if(row < this.scrollOffset || row + 1 > this.scrollIndexOffset + OPTION_ROWS)
            this.setScrollOffset(row - 2);
    }

    private void toggleConnecting(){
        if(this.selectedEntry == null || !this.selectedEntry.hasItem(!this.connecting))
            return;
        this.connecting = !this.connecting;
        RechiseledAE.CHANNEL.sendToServer(new PacketSelectEntry(this.selectedEntry.entryIndex(), this.selectedEntry.shape(), this.connecting));
    }

    private void changeShape(ChiselingBlockShape shape){
        if(this.selectedEntry == null || this.selectedEntry.shape() == shape || !this.selectedEntry.entry().hasShape(shape))
            return;
        for(DisplayEntry display : this.allEntries){
            if(display.entry() == this.selectedEntry.entry() && display.shape() == shape){
                this.selectDisplayEntry(display);
                break;
            }
        }
    }

    private void setScrollOffset(float offset){
        int rows = (int)Math.ceil((float)this.visibleEntries.size() / OPTION_COLUMNS);
        this.scrollOffset = Math.max(0, Math.min(rows - OPTION_ROWS, offset));
        this.scrollIndexOffset = (int)Math.floor(this.scrollOffset);
        for(EntryButtonWidget button : this.entryButtons)
            button.setVerticalOffset(this.scrollOffset % 1);
    }

    @Override
    public boolean mouseScrolled(int mouseX, int mouseY, double scrollAmount, boolean hasBeenHandled){
        if(!hasBeenHandled && mouseX > 8 && mouseX < 145 && mouseY > 33 && mouseY < 145){
            this.setScrollOffset(this.scrollOffset - (float)scrollAmount / 3);
            hasBeenHandled = true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollAmount, hasBeenHandled);
    }
}
