package com.supermartijn642.rechiseled.ae;

import appeng.crafting.pattern.EncodedPatternItem;
import com.supermartijn642.core.gui.WidgetContainerScreen;
import com.supermartijn642.core.registry.ClientRegistrationHandler;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.ChiselingPatternEncoderRenderer;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.ChiselingPatternEncoderContainer;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.ChiselingPatternEncoderScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Created 16/02/2026 by SuperMartijn642
 */
public class RechiseledAEClient {

    public static void initialize(){
        ClientRegistrationHandler handler = ClientRegistrationHandler.get(RechiseledAE.MODID);
        handler.registerCustomBlockEntityRenderer(() -> RechiseledAE.chiseling_pattern_encoder_entity, ChiselingPatternEncoderRenderer::new);
        handler.registerContainerScreen(ChiselingPatternEncoderContainer.TYPE, container -> new WidgetContainerScreen<>(new ChiselingPatternEncoderScreen(), container, false) {
            @Override
            protected void renderSlotContents(GuiGraphics graphics, ItemStack stack, Slot slot, @Nullable String count){
                // For encoded pattern slot, override the displayed item
                if(slot == this.container.encodedPatternSlot && stack.getItem() instanceof EncodedPatternItem pattern){
                    ItemStack output = pattern.getOutput(stack);
                    if(!output.isEmpty() && output != stack)
                        stack = output;
                }
                super.renderSlotContents(graphics, stack, slot, count);
            }
        });
    }
}
