package com.supermartijn642.rechiseled.ae;

import appeng.core.definitions.AEBlocks;
import com.supermartijn642.core.block.BlockProperties;
import com.supermartijn642.rechiseled.api.blocks.BlockSpecification;
import com.supermartijn642.rechiseled.api.blocks.RechiseledBlockBuilder;
import com.supermartijn642.rechiseled.api.blocks.RechiseledBlockType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Consumer;

/**
 * Created 25/04/2023 by SuperMartijn642
 */
@SuppressWarnings({"unused", "Convert2MethodRef"})
public class RechiseledAEBlocks {

    // Certus quartz block
    private static final Consumer<BlockProperties> CERTUS_QUARTZ_BLOCK_BLOCK_PROPERTIES = p -> p.sound(SoundType.STONE).mapColor(MapColor.COLOR_CYAN).destroyTime(3).explosionResistance(5).requiresCorrectTool();
    private static final Consumer<RechiseledBlockBuilder> CERTUS_QUARTZ_BLOCK_CONFIGURER = b -> b.properties(CERTUS_QUARTZ_BLOCK_BLOCK_PROPERTIES).miningTagsFrom(() -> Blocks.QUARTZ_BLOCK).recipe(RechiseledAERecipes.CERTUS_QUARTZ_BLOCK).itemAndBlockTag(RechiseledAE.identifier("certus_quartz_block")).withStairs(s -> s.itemAndBlockTag(RechiseledAE.identifier("certus_quartz_block_stairs"))).withSlabs(s -> s.itemAndBlockTag(RechiseledAE.identifier("certus_quartz_block_slabs")));
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_BRICK_BORDERED = create("certus_quartz_block_brick_bordered", "Brick Bordered Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_BRICKS = create("certus_quartz_block_bricks", "Certus Quartz Bricks").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.QUARTZ_BRICKS.block(), () -> AEBlocks.QUARTZ_BRICK_STAIRS.block(), () -> AEBlocks.QUARTZ_BRICK_SLAB.block()).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CHISELED = create("certus_quartz_block_chiseled", "Chiseled Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CRYSTALLIZED = create("certus_quartz_block_crystallized", "Crystallized Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CRYSTALLIZED_BORDERED = create("certus_quartz_block_crystallized_bordered", "Bordered Crystallized Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CRYSTALLIZED_POLISHED = create("certus_quartz_block_crystallized_polished", "Polished Crystallized Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT = create("certus_quartz_block_cut", "Cut Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.CUT_QUARTZ_BLOCK.block(), () -> AEBlocks.CUT_QUARTZ_STAIRS.block(), () -> AEBlocks.CUT_QUARTZ_SLAB.block()).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_PILLAR = create("certus_quartz_block_cut_pillar", "Cut Certus Quartz Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_DIAGONAL_TILES = create("certus_quartz_block_diagonal_tiles", "Diagonal Cut Certus Quartz Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_DIAGONAL_TILES_PILLAR = create("certus_quartz_block_diagonal_tiles_pillar", "Diagonal Cut Certus Quartz Tiles Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_GROOVY_PILLAR = create("certus_quartz_block_groovy_pillar", "Groovy Certus Quartz Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_PATTERN = create("certus_quartz_block_pattern", "Certus Quartz Block Pattern").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_PAVING = create("certus_quartz_block_paving", "Certus Quartz Paving").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_PILLAR = create("certus_quartz_block_pillar", "Certus Quartz Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).regularVariant(() -> AEBlocks.QUARTZ_PILLAR.block(), () -> AEBlocks.QUARTZ_PILLAR_STAIRS.block(), () -> AEBlocks.QUARTZ_PILLAR_SLAB.block()).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_ROTATED_BRICKS = create("certus_quartz_block_rotated_bricks", "Rotated Certus Quartz Bricks").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_SCALES = create("certus_quartz_block_scales", "Certus Quartz Scales").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_SMALL_TILES = create("certus_quartz_block_small_tiles", "Small Certus Quartz Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_SQUARES = create("certus_quartz_block_squares", "Certus Quartz Block Squares").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_STRIPES = create("certus_quartz_block_stripes", "Certus Quartz Block Stripes").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_TILES = create("certus_quartz_block_tiles", "Certus Quartz Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    // Fluix block
    private static final Consumer<BlockProperties> FLUIX_BLOCK_BLOCK_PROPERTIES = p -> p.sound(SoundType.STONE).mapColor(MapColor.COLOR_PURPLE).destroyTime(3).explosionResistance(5).requiresCorrectTool();
    private static final Consumer<RechiseledBlockBuilder> FLUIX_BLOCK_CONFIGURER = b -> b.properties(FLUIX_BLOCK_BLOCK_PROPERTIES).miningTagsFrom(() -> Blocks.QUARTZ_BLOCK).recipe(RechiseledAERecipes.FLUIX_BLOCK).itemAndBlockTag(RechiseledAE.identifier("fluix_block")).withStairs(s -> s.itemAndBlockTag(RechiseledAE.identifier("fluix_block_stairs"))).withSlabs(s -> s.itemAndBlockTag(RechiseledAE.identifier("fluix_block_slabs")));
    public static final RechiseledBlockType FLUIX_BLOCK_BRICKS = create("fluix_block_bricks", "Fluix Bricks").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_CHECKERBOARD = create("fluix_block_checkerboard", "Checkerboard Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_CONNECTING = create("fluix_block_connecting", "Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.FLUIX_BLOCK.block(), () -> AEBlocks.FLUIX_STAIRS.block(), () -> AEBlocks.FLUIX_SLAB.block()).build();
    public static final RechiseledBlockType FLUIX_BLOCK_CRUSHED = create("fluix_block_crushed", "Crushed Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_ENCHANTED = create("fluix_block_enchanted", "Enchanted Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED = create("fluix_block_polished", "Polished Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_BORDERED = create("fluix_block_polished_bordered", "Bordered Polished Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_BRICKS = create("fluix_block_polished_bricks", "Polished Fluix Bricks").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_CHECKERBOARD = create("fluix_block_polished_checkerboard", "Polished Checkerboard Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_CRUSHED = create("fluix_block_polished_crushed", "Polished Crushed Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_DECORATED = create("fluix_block_polished_decorated", "Decorated Polished Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_DIAGONAL_TILES = create("fluix_block_polished_diagonal_tiles", "Diagonal Polished Fluix Tiles").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_ENCHANTED = create("fluix_block_polished_enchanted", "Polished Enchanted Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_SMOOTH = create("fluix_block_polished_smooth", "Smooth Polished Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_SQUARES = create("fluix_block_polished_squares", "Polished Fluix Block Squares").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_SWIRLING = create("fluix_block_polished_swirling", "Polished Swirling Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_TILES = create("fluix_block_polished_tiles", "Polished Fluix Tiles").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_POLISHED_WOVEN = create("fluix_block_polished_woven", "Woven Polished Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_SMOOTH = create("fluix_block_smooth", "Smooth Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_SWIRLING = create("fluix_block_swirling", "Swirling Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    // Sky stone block
    private static final Consumer<BlockProperties> SKY_STONE_BLOCK_BLOCK_PROPERTIES = p -> p.sound(SoundType.STONE).mapColor(MapColor.COLOR_BLACK).destroyTime(5).explosionResistance(150).requiresCorrectTool();
    private static final Consumer<RechiseledBlockBuilder> SKY_STONE_BLOCK_CONFIGURER = b -> b.properties(SKY_STONE_BLOCK_BLOCK_PROPERTIES).blockTag(BlockTags.MINEABLE_WITH_PICKAXE.location()).blockTag(BlockTags.NEEDS_IRON_TOOL.location()).recipe(RechiseledAERecipes.SKY_STONE_BLOCK).itemAndBlockTag(RechiseledAE.identifier("sky_stone_block")).withStairs(s -> s.itemAndBlockTag(RechiseledAE.identifier("sky_stone_block_stairs"))).withSlabs(s -> s.itemAndBlockTag(RechiseledAE.identifier("sky_stone_block_slabs")));
    public static final RechiseledBlockType SKY_STONE_BLOCK_BORDERED = create("sky_stone_block_bordered", "Bordered Sky Stone Block").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_BORDERED_DIAGONAL_BRICKS = create("sky_stone_block_bordered_diagonal_bricks", "Bordered Diagonal Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_BORDERED_SQUARES = create("sky_stone_block_bordered_squares", "Bordered Sky Stone Squares").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_BRICK_PATTERN = create("sky_stone_block_brick_pattern", "Sky Stone Brick Pattern").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_BRICK_PAVING = create("sky_stone_block_brick_paving", "Sky Stone Brick Paving").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_BRICKS = create("sky_stone_block_bricks", "Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.SKY_STONE_BRICK.block(), () -> AEBlocks.SKY_STONE_BRICK_STAIRS.block(), () -> AEBlocks.SKY_STONE_BRICK_SLAB.block()).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_CONNECTING = create("sky_stone_block_connecting", "Sky Stone Block").configure(SKY_STONE_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.SMOOTH_SKY_STONE_BLOCK.block(), () -> AEBlocks.SMOOTH_SKY_STONE_STAIRS.block(), () -> AEBlocks.SMOOTH_SKY_STONE_SLAB.block()).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_DIAGONAL_BRICKS = create("sky_stone_block_diagonal_bricks", "Diagonal Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_ROTATED_BRICKS = create("sky_stone_block_rotated_bricks", "Rotated Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_SMALL_BRICKS = create("sky_stone_block_small_bricks", "Small Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.SKY_STONE_SMALL_BRICK.block(), () -> AEBlocks.SKY_STONE_SMALL_BRICK_STAIRS.block(), () -> AEBlocks.SKY_STONE_SMALL_BRICK_SLAB.block()).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_SMOOTH = create("sky_stone_block_smooth", "Smooth Sky Stone Block").configure(SKY_STONE_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_SQUARES = create("sky_stone_block_squares", "Sky Stone Squares").configure(SKY_STONE_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_TILES = create("sky_stone_block_tiles", "Sky Stone Tiles").configure(SKY_STONE_BLOCK_CONFIGURER).build();

    private static RechiseledBlockBuilder create(String identifier, String translation){
        return RechiseledAE.REGISTRATION.block(identifier).translation(translation);
    }

    public static void init(){
        // Cause this class to be initialized
    }
}
