package com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.widgets;

import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.GuiGraphicsHelper;
import com.supermartijn642.core.gui.widget.WidgetRenderContext;
import com.supermartijn642.core.gui.widget.premade.AbstractButtonWidget;
import com.supermartijn642.rechiseled.ae.RechiseledAE;
import com.supermartijn642.rechiseled.screen.preview.PreviewMode;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

/**
 * Created 16/02/2026 by SuperMartijn642
 */
public class PreviewModeButtonWidget extends AbstractButtonWidget {

    public static final Identifier PREVIEW_BUTTONS = RechiseledAE.identifier("screen/preview_buttons");

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
    public void render(WidgetRenderContext context, GuiGraphicsHelper graphics, int mouseX, int mouseY){
        boolean selected = this.mode == this.currentMode.get();
        graphics.submitSprite(PREVIEW_BUTTONS, this.x, this.y, this.width, this.height, p -> p.uv(0, (!this.isClickable() ? 2 : this.isFocused() ? 1 : 0) / 3f, 1, 1 / 3f));
        int offset = !this.isClickable() ? 2 : this.isFocused() ? 1 : 0;
        graphics.submitSprite(this.mode.icon(selected), this.x + 2, this.y + 1 + offset, this.width - 4, this.width - 4);
    }
}
