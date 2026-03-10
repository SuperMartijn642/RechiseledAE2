package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import com.mojang.blaze3d.vertex.PoseStack;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.render.CustomItemRenderer;
import com.supermartijn642.rechiseled.Rechiseled;
import com.supermartijn642.rechiseled.ae.RechiseledAE;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;

/**
 * Created 09/03/2026 by SuperMartijn642
 */
public class ChiselingPatternEncoderItemRenderer implements CustomItemRenderer {

    @Override
    public void render(ItemStack itemStack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay){
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(new Quaternionf().rotateY((float)Math.PI));
        poseStack.translate(-0.5, -0.5, -0.5);
        // Render the block itself
        BlockState state = RechiseledAE.chiseling_pattern_encoder.defaultBlockState().setValue(ChiselingPatternEncoderBlock.ON, true);
        ClientUtils.getBlockRenderer().renderSingleBlock(state, poseStack, bufferSource, combinedLight, combinedOverlay);
        // Render the chisel on top
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(new Quaternionf().rotateY(1.1f * (float)Math.PI));
        poseStack.translate(-0.5, -0.5, -0.5);
        poseStack.translate(0.5, 10/16f, 0.5);
        poseStack.scale(0.8f, 1f, 0.8f);
        poseStack.translate(0.1, 0, 0.1);
        poseStack.mulPose(new Quaternionf().rotateX((float)Math.PI / 2));
        ClientUtils.getItemRenderer().renderStatic(Rechiseled.chisel.getDefaultInstance(), ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, null, 0);
        poseStack.popPose();
    }
}
