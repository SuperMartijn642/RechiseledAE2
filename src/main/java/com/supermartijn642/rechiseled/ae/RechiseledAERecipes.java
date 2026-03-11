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
    public static final ResourceLocation SKY_STONE_BLOCK = location("sky_stone_block");

    private static ResourceLocation location(String name){
        return new ResourceLocation(RechiseledAE.MODID, name);
    }

    private static Supplier<ItemLike> getBlock(String identifier){
        ResourceLocation location = new ResourceLocation(identifier);
        return () -> {
            if(!Registries.BLOCKS.hasIdentifier(location))
                throw new RuntimeException("Unknown block '" + identifier + "'!");
            return Registries.BLOCKS.getValue(location);
        };
    }

    public static void init(){
        // Certus quartz block
        regularSet(CERTUS_QUARTZ_BLOCK, "ae2:quartz_block", "ae2:quartz_stairs", "ae2:quartz_slab");
        regularSet(CERTUS_QUARTZ_BLOCK, "ae2:smooth_quartz_block", "ae2:smooth_quartz_stairs", "ae2:smooth_quartz_slab");
        regularSet(CERTUS_QUARTZ_BLOCK, "ae2:chiseled_quartz_block", "ae2:chiseled_quartz_stairs", "ae2:chiseled_quartz_slab");
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
}
