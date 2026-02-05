package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import com.mojang.blaze3d.vertex.PoseStack;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.render.CustomBlockEntityRenderer;
import com.supermartijn642.rechiseled.Rechiseled;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Quaternionf;

import java.util.Random;

/**
 * Created 16/02/2026 by SuperMartijn642
 */
public class ChiselingPatternEncoderRenderer implements CustomBlockEntityRenderer<ChiselingPatternEncoderBlockEntity> {

    private static final Random RANDOM = new Random();

    @Override
    public void render(ChiselingPatternEncoderBlockEntity entity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay){
        poseStack.pushPose();
        poseStack.translate(0.5, 10/16f, 0.5);
        // Apply random rotation based on block position
        RANDOM.setSeed(entity.getBlockPos().asLong());
        RANDOM.nextLong();RANDOM.nextLong();RANDOM.nextLong();
        poseStack.mulPose(new Quaternionf().rotateY((float)Math.PI * RANDOM.nextInt(4) / 2));
        // Render chisel
        poseStack.pushPose();
        poseStack.scale(0.5f, 1f, 0.5f);
        poseStack.translate(0.2, 0, 0.2);
        poseStack.mulPose(new Quaternionf().rotateX((float)Math.PI / 2));
        ClientUtils.getItemRenderer().renderStatic(Rechiseled.chisel.getDefaultInstance(), ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, entity.getLevel(), 0);
        poseStack.popPose();
        // Render item
        Item item = entity.getInput();
        if(item != null){
            poseStack.pushPose();
            poseStack.translate(-0.1, 0.1, -0.1);
            poseStack.scale(0.5f, 0.5f, 0.5f);
            poseStack.mulPose(new Quaternionf().rotateY(0.6f));
            ClientUtils.getItemRenderer().renderStatic(item.getDefaultInstance(), ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, entity.getLevel(), 0);
            poseStack.popPose();
        }
        poseStack.popPose();
    }
}
