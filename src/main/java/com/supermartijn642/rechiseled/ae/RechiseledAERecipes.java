package com.supermartijn642.rechiseled.ae;

import com.supermartijn642.core.registry.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

import static com.supermartijn642.rechiseled.ae.RechiseledAE.REGISTRATION;

/**
 * Created 25/04/2023 by SuperMartijn642
 */
public class RechiseledAERecipes {

    public static final ResourceLocation CERTUS_QUARTZ_BLOCK = location("certus_quartz_block");
    public static final ResourceLocation FLUIX_BLOCK = location("fluix_block");
    public static final ResourceLocation QUARTZ_GLASS = location("quartz_glass");
    public static final ResourceLocation SKY_STONE = location("sky_stone");
    public static final ResourceLocation VIBRANT_QUARTZ_GLASS = location("vibrant_quartz_glass");

    private static ResourceLocation location(String name){
        return ResourceLocation.fromNamespaceAndPath(RechiseledAE.MODID, name);
    }

    private static Supplier<ItemLike> getBlock(String identifier){
        ResourceLocation location = ResourceLocation.parse(identifier);
        return () -> {
            if(!Registries.BLOCKS.hasIdentifier(location))
                throw new RuntimeException("Unknown block '" + identifier + "'!");
            return Registries.BLOCKS.getValue(location);
        };
    }

    public static void init(){
        // Certus quartz block
        regularBlockOnly(CERTUS_QUARTZ_BLOCK, getBlock("ae2:quartz_block"));
        regularBlockOnly(CERTUS_QUARTZ_BLOCK, getBlock("ae2:smooth_quartz_block"));
        regularBlockOnly(CERTUS_QUARTZ_BLOCK, getBlock("ae2:chiseled_quartz_block"));
        // Quartz glass
        regularBlockOnly(QUARTZ_GLASS, getBlock("ae2:quartz_glass"));
        // Sky stone
        regularBlockOnly(SKY_STONE, getBlock("ae2:sky_stone_block"));
        regularBlockOnly(SKY_STONE, getBlock("ae2:smooth_sky_stone_block"));
        regularBlockOnly(SKY_STONE, getBlock("ae2:sky_stone_brick"));
        regularBlockOnly(SKY_STONE, getBlock("ae2:sky_stone_small_brick"));
        // Vibrant quartz glass
        regularBlockOnly(VIBRANT_QUARTZ_GLASS, getBlock("ae2:quartz_vibrant_glass"));
    }

    private static void regularBlockOnly(ResourceLocation recipe, Supplier<ItemLike> block){
        REGISTRATION.chiselingEntry(recipe, entry -> entry.regularBlock(block.get()));
    }

    private static void regularBlockOnly(ResourceLocation recipe, String blockIdentifier){
        regularBlockOnly(recipe, getBlock(blockIdentifier));
    }

    private static void connectingBlockOnly(ResourceLocation recipe, Supplier<ItemLike> block){
        REGISTRATION.chiselingEntry(recipe, entry -> entry.connectingBlock(block.get()));
    }

    private static void connectingBlockOnly(ResourceLocation recipe, String blockIdentifier){
        connectingBlockOnly(recipe, getBlock(blockIdentifier));
    }

    private static void regularSet(ResourceLocation recipe, String blockIdentifier, String stairsIdentifier, String slabIdentifier){
        Supplier<ItemLike> block = getBlock(blockIdentifier);
        Supplier<ItemLike> stairs = getBlock(stairsIdentifier);
        Supplier<ItemLike> slab = getBlock(slabIdentifier);
        REGISTRATION.chiselingEntry(recipe, entry -> {
            entry.regularBlock(block.get());
            entry.regularStairs(stairs.get());
            entry.regularSlab(slab.get());
        });
    }

    private static void regularSet(ResourceLocation recipe, String identifier){
        regularSet(recipe, identifier, identifier + "_stairs", identifier + "_slab");
    }
}
