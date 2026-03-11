package com.supermartijn642.rechiseled.ae;

import appeng.crafting.pattern.EncodedPatternItem;
import com.supermartijn642.core.gui.CustomSlot;
import com.supermartijn642.core.gui.WidgetContainerScreen;
import com.supermartijn642.core.registry.ClientRegistrationHandler;
import com.supermartijn642.core.render.CustomRendererBakedModelWrapper;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.ChiselingPatternEncoderItemRenderer;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.ChiselingPatternEncoderRenderer;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.ChiselingPatternEncoderContainer;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.ChiselingPatternEncoderScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/**
 * Created 16/02/2026 by SuperMartijn642
 */
public class RechiseledAEClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(){
        ClientRegistrationHandler handler = ClientRegistrationHandler.get(RechiseledAE.MODID);
        handler.registerCustomBlockEntityRenderer(() -> RechiseledAE.chiseling_pattern_encoder_entity, ChiselingPatternEncoderRenderer::new);
        handler.registerContainerScreen(ChiselingPatternEncoderContainer.TYPE, container -> new WidgetContainerScreen<>(new ChiselingPatternEncoderScreen(), container, false) {
            private ItemStack slotStackOverwrite = ItemStack.EMPTY;
            private final CustomSlot dummySlot = CustomSlot.builder().getter(() -> this.slotStackOverwrite).build();

            @Override
            protected void renderSlot(GuiGraphics graphics, Slot slot){
                // For encoded pattern slot, override the displayed item
                if(slot == this.container.encodedPatternSlot){
                    ItemStack stack = slot.getItem();
                    if(stack.getItem() instanceof EncodedPatternItem pattern){
                        ItemStack output = pattern.getOutput(stack);
                        if(!output.isEmpty() && output != stack){
                            this.dummySlot.move(slot.x, slot.y);
                            this.slotStackOverwrite = output;
                            super.renderSlot(graphics, this.dummySlot.getVanillaSlot());
                            return;
                        }
                    }
                }
                super.renderSlot(graphics, slot);
            }
        });
        handler.registerCustomItemRenderer(() -> RechiseledAE.chiseling_pattern_encoder.asItem(), ChiselingPatternEncoderItemRenderer::new);
        handler.registerItemModelOverwrite(() -> RechiseledAE.chiseling_pattern_encoder.asItem(), CustomRendererBakedModelWrapper::wrap);
    }
}
