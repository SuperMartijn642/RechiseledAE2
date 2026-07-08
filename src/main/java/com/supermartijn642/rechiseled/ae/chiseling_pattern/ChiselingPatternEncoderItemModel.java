package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import com.google.common.base.Suppliers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.rechiseled.Rechiseled;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.CuboidItemModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created 08/07/2026 by SuperMartijn642
 */
public class ChiselingPatternEncoderItemModel implements ItemModel {

    private static final SpecialModelRenderer<Void> CHISEL_RENDERER = new SpecialModelRenderer<>() {
        final Supplier<ItemStackRenderState> chisel = Suppliers.memoize(() -> {
            ItemStackRenderState chiselRenderState = new ItemStackRenderState();
            ClientUtils.getMinecraft().getItemModelResolver().updateForTopItem(chiselRenderState, Rechiseled.chisel.getDefaultInstance(), ItemDisplayContext.FIXED, null, null, 0);
            return chiselRenderState;
        });

        @Override
        public void submit(@Nullable Void argument, PoseStack poseStack, SubmitNodeCollector output, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor){
            poseStack.pushPose();
            poseStack.translate(0.5, 0.5, 0.5);
            poseStack.mulPose(new Quaternionf().rotateY((float)Math.PI * 1.5f));
            poseStack.translate(-0.5, -0.5, -0.5);
            // Render the chisel
            poseStack.translate(0.5, 0.5, 0.5);
            poseStack.mulPose(new Quaternionf().rotateY(1.1f * (float)Math.PI));
            poseStack.translate(-0.5, -0.5, -0.5);
            poseStack.translate(0.5, 10 / 16f, 0.5);
            poseStack.scale(0.8f, 1f, 0.8f);
            poseStack.translate(0.1, 0, 0.1);
            poseStack.mulPose(new Quaternionf().rotateX((float)Math.PI / 2));
            this.chisel.get().submit(poseStack, output, lightCoords, overlayCoords, outlineColor);
            poseStack.popPose();
        }

        @Override
        public void getExtents(Consumer<Vector3fc> output){
        }

        @Override
        public @Nullable Void extractArgument(ItemStack stack){
            return null;
        }
    };

    private final CuboidItemModelWrapper base;

    public ChiselingPatternEncoderItemModel(CuboidItemModelWrapper base){
        this.base = base;
    }

    @Override
    public void update(ItemStackRenderState output, ItemStack item, ItemModelResolver resolver, ItemDisplayContext displayContext, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed){
        this.base.update(output, item, resolver, displayContext, level, owner, seed);
        ItemStackRenderState.LayerRenderState layer = output.newLayer();
        this.base.properties.applyToLayer(layer, displayContext);
        layer.setLocalTransform(this.base.transformation);
        layer.setupSpecialModel(CHISEL_RENDERER, null);
    }
}
