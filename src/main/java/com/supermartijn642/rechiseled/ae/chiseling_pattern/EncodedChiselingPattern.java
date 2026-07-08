package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import appeng.core.definitions.AEItems;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;

/**
 * Created 13/02/2026 by SuperMartijn642
 */
public record EncodedChiselingPattern(Item input, Item output) {

    // Item codecs
    @SuppressWarnings("deprecation")
    private static final Codec<Item> ITEM_CODEC = Item.CODEC.xmap(Holder::value, Item::builtInRegistryHolder);
    @SuppressWarnings("deprecation")
    private static final StreamCodec<RegistryFriendlyByteBuf,Item> ITEM_STREAM_CODEC = ByteBufCodecs.holderRegistry(Registries.ITEM).map(Holder::value, Item::builtInRegistryHolder);

    private static final Codec<EncodedChiselingPattern> CODEC = RecordCodecBuilder.create(builder -> builder.group(
        ITEM_CODEC.fieldOf("input").forGetter(EncodedChiselingPattern::input),
        ITEM_CODEC.fieldOf("output").forGetter(EncodedChiselingPattern::output)
    ).apply(builder, EncodedChiselingPattern::new));
    private static final StreamCodec<RegistryFriendlyByteBuf,EncodedChiselingPattern> STREAM_CODEC = StreamCodec.composite(
        ITEM_STREAM_CODEC,
        EncodedChiselingPattern::input,
        ITEM_STREAM_CODEC,
        EncodedChiselingPattern::output,
        EncodedChiselingPattern::new
    );
    public static final DataComponentType<EncodedChiselingPattern> COMPONENT_TYPE = DataComponentType.<EncodedChiselingPattern>builder()
        .persistent(CODEC)
        .networkSynchronized(STREAM_CODEC)
        .build();

    public EncodedChiselingPattern{
        if(input == null || output == null)
            throw new IllegalArgumentException("Input and output must not be null!");
    }

    public boolean containsMissingContent(){
        return this.input == AEItems.MISSING_CONTENT.get() || this.output == AEItems.MISSING_CONTENT.get();
    }

    @Override
    public boolean equals(Object object){
        if(this == object)
            return true;
        if(object == null || this.getClass() != object.getClass())
            return false;

        EncodedChiselingPattern that = (EncodedChiselingPattern)object;
        return this.input == that.input
            && this.output == that.output;
    }

    @Override
    public int hashCode(){
        int result = this.input.hashCode();
        result = 31 * result + this.output.hashCode();
        return result;
    }
}
