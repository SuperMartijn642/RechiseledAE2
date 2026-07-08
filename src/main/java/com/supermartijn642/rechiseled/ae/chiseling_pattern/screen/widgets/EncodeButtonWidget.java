package com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.widgets;

import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.GuiGraphicsHelper;
import com.supermartijn642.core.gui.widget.WidgetRenderContext;
import com.supermartijn642.core.gui.widget.premade.AbstractButtonWidget;
import com.supermartijn642.rechiseled.ae.RechiseledAE;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.packet.PacketEncodePattern;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created 17/02/2026 by SuperMartijn642
 */
public class EncodeButtonWidget extends AbstractButtonWidget {

    public static final Identifier BUTTONS = RechiseledAE.identifier("screen/encode_buttons");

    private final Supplier<Boolean> canEncode;

    public EncodeButtonWidget(int x, int y, Supplier<Boolean> canEncode){
        super(x, y, 18, 20, () -> RechiseledAE.CHANNEL.sendToServer(new PacketEncodePattern()));
        this.canEncode = canEncode;
    }

    @Override
    protected boolean isClickable(){
        return super.isClickable() && this.canEncode.get();
    }

    @Override
    public Component getNarrationMessage(){
        return TextComponents.translation("rechiseledae.chiseling_pattern_encoder.encode").get();
    }

    @Override
    protected void getTooltips(Consumer<Component> tooltips){
        tooltips.accept(this.getNarrationMessage());
    }

    @Override
    public void render(WidgetRenderContext context, GuiGraphicsHelper graphics, int mouseX, int mouseY){
        graphics.submitSprite(
            BUTTONS,
            this.x, this.y, this.width, this.height,
            p -> p.uv(0, this.isClickable() ? this.isFocused() ? 1 / 3f : 0 : 2 / 3f, 1, 1 / 3f)
        );
    }
}
