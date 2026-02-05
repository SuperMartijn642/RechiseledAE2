package com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.packet;

import com.supermartijn642.core.network.BasePacket;
import com.supermartijn642.core.network.PacketContext;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.ChiselingPatternEncoderContainer;
import com.supermartijn642.rechiseled.api.chiseling.ChiselingBlockShape;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.AbstractContainerMenu;

/**
 * Created 17/02/2026 by SuperMartijn642
 */
public class PacketEncodePattern implements BasePacket {

    public PacketEncodePattern(){
    }

    @Override
    public void write(FriendlyByteBuf buffer){
    }

    @Override
    public void read(FriendlyByteBuf buffer){
    }

    @Override
    public void handle(PacketContext context){
        AbstractContainerMenu container = context.getPlayer().containerMenu;
        if(container instanceof ChiselingPatternEncoderContainer)
            ((ChiselingPatternEncoderContainer)container).encodePattern();
    }
}
