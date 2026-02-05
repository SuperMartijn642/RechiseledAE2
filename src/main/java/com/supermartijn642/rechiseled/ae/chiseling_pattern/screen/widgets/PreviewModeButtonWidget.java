package com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.widgets;

import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.ScreenUtils;
import com.supermartijn642.core.gui.widget.WidgetRenderContext;
import com.supermartijn642.core.gui.widget.premade.AbstractButtonWidget;
import com.supermartijn642.rechiseled.ae.RechiseledAE;
import com.supermartijn642.rechiseled.screen.preview.PreviewMode;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/**
 * Created 16/02/2026 by SuperMartijn642
 */
public class PreviewModeButtonWidget extends AbstractButtonWidget {

    private static final ResourceLocation PREVIEW_BUTTONS = RechiseledAE.identifier("textures/screen/preview_buttons.png");

    private final PreviewMode mode;
    private final Supplier<PreviewMode> currentMode;
    private final Supplier<Boolean> enabled;

    public PreviewModeButtonWidget(int x, int y,
                                   PreviewMode mode,
                                   Supplier<PreviewMode> currentMode,
                                   Supplier<Boolean> enabled,
                                   Runnable onPress){
        super(x, y, 20, 21, onPress);
        this.mode = mode;
        this.currentMode = currentMode;
        this.enabled = enabled;
    }

    @Override
    protected boolean isClickable(){
        return this.enabled.get() && this.currentMode.get() != this.mode;
    }

    @Override
    public Component getNarrationMessage(){
        return TextComponents.translation("rechiseledae.chiseling_pattern_encoder.preview").get();
    }

    @Override
    public void render(WidgetRenderContext context, int mouseX, int mouseY){
        boolean selected = this.mode == this.currentMode.get();
        ScreenUtils.bindTexture(PREVIEW_BUTTONS);
        ScreenUtils.drawTexture(context.poseStack(), this.x, this.y, this.width, this.height, 0, (!this.isClickable() ? 2 : this.isFocused() ? 1 : 0) / 3f, 1, 1 / 3f);
        ScreenUtils.bindTexture(this.mode.icon(selected));
        int offset = !this.isClickable() ? 2 : this.isFocused() ? 1 : 0;
        ScreenUtils.drawTexture(context.poseStack(), this.x + 2, this.y + 1 + offset, this.width - 4, this.width - 4);
    }
}
