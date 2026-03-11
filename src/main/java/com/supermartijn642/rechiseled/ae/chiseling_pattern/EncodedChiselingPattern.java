package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Created 13/02/2026 by SuperMartijn642
 */
public record EncodedChiselingPattern(Item input, Item output) {

    // Item codec
    @SuppressWarnings("deprecation")
    private static final Codec<Item> ITEM_CODEC = BuiltInRegistries.ITEM.byNameCodec().orElse(Items.AIR);

    private static final Codec<EncodedChiselingPattern> CODEC = RecordCodecBuilder.create(builder -> builder.group(
        ITEM_CODEC.fieldOf("input").forGetter(EncodedChiselingPattern::input),
        ITEM_CODEC.fieldOf("output").forGetter(EncodedChiselingPattern::output)
    ).apply(builder, EncodedChiselingPattern::new));

    public static EncodedChiselingPattern deserialize(Tag tag){
        return CODEC.decode(NbtOps.INSTANCE, tag).map(Pair::getFirst).result().orElse(null);
    }

    public static EncodedChiselingPattern deserialize(ItemStack stack){
        if(!stack.hasTag() || !stack.getOrCreateTag().contains("pattern"))
            return null;
        return deserialize(stack.getOrCreateTag().get("pattern"));
    }

    public EncodedChiselingPattern{
        if(input == null || output == null)
            throw new IllegalArgumentException("Input and output must not be null!");
    }

    public boolean containsMissingContent(){
        return this.input == Items.AIR || this.output == Items.AIR;
    }

    public Tag serialize(){
        return CODEC.encode(this, NbtOps.INSTANCE, null).getOrThrow(false, s -> {});
    }

    public void serialize(ItemStack stack){
        stack.getOrCreateTag().put("pattern", this.serialize());
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
