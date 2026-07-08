package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import com.google.common.base.Suppliers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.render.CustomBlockEntityRenderer;
import com.supermartijn642.rechiseled.Rechiseled;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;

import java.util.function.Supplier;

/**
 * Created 16/02/2026 by SuperMartijn642
 */
public class ChiselingPatternEncoderRenderer implements CustomBlockEntityRenderer<ChiselingPatternEncoderBlockEntity,ChiselingPatternEncoderRenderer.State> {

    private static final Supplier<ItemStack> CHISEL = Suppliers.memoize(() -> Rechiseled.chisel.getDefaultInstance());

    @Override
    public State createStateHolder(){
        return new State();
    }

    @Override
    public void updateState(State state, ChiselingPatternEncoderBlockEntity entity, UpdateContext context){
        state.rotationOffset = entity.getRotationOffset();
        ItemModelResolver itemModelResolver = ClientUtils.getMinecraft().getItemModelResolver();
        itemModelResolver.updateForTopItem(state.chiselRenderState, CHISEL.get(), ItemDisplayContext.FIXED, entity.getLevel(), null, 0);
        Item input = entity.getInput();
        if(input != null){
            state.hasInput = true;
            itemModelResolver.updateForTopItem(state.inputRenderState, input.getDefaultInstance(), ItemDisplayContext.FIXED, entity.getLevel(), null, 0);
        }else
            state.hasInput = false;
    }

    @Override
    public void submit(SubmitNodeCollector output, State state, RenderContext context){
        PoseStack poseStack = context.poseStack();
        poseStack.pushPose();
        poseStack.translate(0.5, 10 / 16f, 0.5);
        // Apply random rotation based on block position
        poseStack.mulPose(new Quaternionf().rotateY((float)Math.PI * state.rotationOffset / 2));
        // Render chisel
        poseStack.pushPose();
        poseStack.scale(0.5f, 1f, 0.5f);
        poseStack.translate(0.2, 0, 0.2);
        poseStack.mulPose(new Quaternionf().rotateX((float)Math.PI / 2));
        ModelFeatureRenderer.CrumblingOverlay breakingOverlay = context.breakingOverlay();
        state.chiselRenderState.submit(poseStack, output, context.packedLight(), breakingOverlay == null ? OverlayTexture.NO_OVERLAY : breakingOverlay.progress(), 0);
        poseStack.popPose();
        // Render item
        if(state.hasInput){
            poseStack.pushPose();
            poseStack.translate(-0.1, 0.125, -0.1);
            poseStack.scale(0.5f, 0.5f, 0.5f);
            poseStack.mulPose(new Quaternionf().rotateY(0.6f));
            state.inputRenderState.submit(poseStack, output, context.packedLight(), breakingOverlay == null ? OverlayTexture.NO_OVERLAY : breakingOverlay.progress(), 0);
            poseStack.popPose();
        }
        poseStack.popPose();
    }

    public static class State {
        private int rotationOffset;
        private final ItemStackRenderState chiselRenderState = new ItemStackRenderState();
        private boolean hasInput;
        private final ItemStackRenderState inputRenderState = new ItemStackRenderState();
    }
}
