package com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.widgets;

import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.GuiGraphicsHelper;
import com.supermartijn642.core.gui.widget.WidgetRenderContext;
import com.supermartijn642.core.gui.widget.premade.AbstractButtonWidget;
import com.supermartijn642.core.util.Holder;
import com.supermartijn642.rechiseled.Rechiseled;
import com.supermartijn642.rechiseled.ae.RechiseledAE;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created 17/02/2026 by SuperMartijn642
 */
public class ConnectingToggleWidget extends AbstractButtonWidget {

    public static final Identifier TOGGLE_BUTTONS = RechiseledAE.identifier("screen/toggle_buttons");
    public static final Identifier ICON_CONNECTED_ON = Rechiseled.identifier("screen/icon_connecting_true");
    public static final Identifier ICON_CONNECTED_OFF = Rechiseled.identifier("screen/icon_connecting_false");

    private final Supplier<Boolean> connecting;
    private final Supplier<DisplayEntry> currentEntry;

    public ConnectingToggleWidget(int x, int y, Supplier<Boolean> connecting, Supplier<DisplayEntry> currentEntry, Runnable onPress){
        super(x, y, 22, 12, onPress);
        this.connecting = connecting;
        this.currentEntry = currentEntry;
    }

    @Override
    protected boolean isClickable(){
        DisplayEntry display = this.currentEntry.get();
        return display != null && display.hasItem(!this.connecting.get());
    }

    @Override
    public Component getNarrationMessage(){
        Holder<Component> message = new Holder<>();
        this.getTooltips(message::set);
        return message.get();
    }

    @Override
    protected void getTooltips(Consumer<Component> tooltips){
        if(this.isClickable())
            tooltips.accept(TextComponents.translation("rechiseledae.chiseling_pattern_encoder.connecting", TextComponents.translation("rechiseledae.chiseling_pattern_encoder.connecting." + (this.connecting.get() ? "on" : "off")).color(ChatFormatting.GOLD).get()).get());
    }

    @Override
    public void renderBackground(WidgetRenderContext context, GuiGraphicsHelper graphics, int mouseX, int mouseY){
        // Toggle background
        boolean canSwitch = this.isClickable();
        graphics.submitSprite(
            TOGGLE_BUTTONS,
            this.x, this.y, this.width, this.height,
            p -> p.uv(
                this.connecting.get() ? 0 : 0.25f,
                (canSwitch ? this.isFocused() ? 1 : 0 : 2) / 3f,
                1 / 4f, 1 / 3f
            )
        );
        // Icon
        boolean connecting = this.connecting.get();
        graphics.submitSprite(
            connecting ? ICON_CONNECTED_ON : ICON_CONNECTED_OFF,
            connecting ? this.x + 1 : this.x + 12, this.y + 2, 9, 9
        );
        // Toggle overlay
        graphics.submitSprite(
            TOGGLE_BUTTONS,
            this.x, this.y, this.width, this.height,
            p -> p.uv(
                this.connecting.get() ? 0.5f : 0.75f,
                (canSwitch ? this.isFocused() ? 1 : 0 : 2) / 3f,
                1 / 4f, 1 / 3f
            )
        );
    }
}
