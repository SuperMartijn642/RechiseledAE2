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
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_DIAGONAL_TILES = create("certus_quartz_block_diagonal_tiles", "Diagonal Cut Certus Quartz Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_DIAGONAL_TILES_PILLAR = create("certus_quartz_block_diagonal_tiles_pillar", "Diagonal Cut Certus Quartz Tiles Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_PATTERN = create("certus_quartz_block_pattern", "Certus Quartz Block Pattern").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_PAVING = create("certus_quartz_block_paving", "Certus Quartz Paving").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_PILLAR = create("certus_quartz_block_pillar", "Certus Quartz Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).regularVariant(() -> AEBlocks.QUARTZ_PILLAR.block(), () -> AEBlocks.QUARTZ_PILLAR_STAIRS.block(), () -> AEBlocks.QUARTZ_PILLAR_SLAB.block()).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED = create("certus_quartz_block_polished", "Polished Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_BRICK_BORDERED = create("certus_quartz_block_polished_brick_bordered", "Polished Brick Bordered Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_BRICKS = create("certus_quartz_block_polished_bricks", "Polished Certus Quartz Bricks").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_CHISELED = create("certus_quartz_block_polished_chiseled", "Polished Chiseled Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_CUT_PILLAR = create("certus_quartz_block_polished_cut_pillar", "Polished Cut Certus Quartz Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_GROOVY_PILLAR = create("certus_quartz_block_polished_groovy_pillar", "Polished Groovy Certus Quartz Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_PATTERN = create("certus_quartz_block_polished_pattern", "Polished Certus Quartz Block Pattern").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_PAVING = create("certus_quartz_block_polished_paving", "Polished Certus Quartz Paving").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_PILLAR = create("certus_quartz_block_polished_pillar", "Polished Certus Quartz Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_ROTATED_BRICKS = create("certus_quartz_block_polished_rotated_bricks", "Polished Rotated Certus Quartz Bricks").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_SCALES = create("certus_quartz_block_polished_scales", "Polished Certus Quartz Scales").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_SMALL_TILES = create("certus_quartz_block_polished_small_tiles", "Polished Small Certus Quartz Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_SQUARES = create("certus_quartz_block_polished_squares", "Polished Certus Quartz Block Squares").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_STRIPES = create("certus_quartz_block_polished_stripes", "Polished Certus Quartz Block Stripes").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED_TILES = create("certus_quartz_block_polished_tiles", "Polished Certus Quartz Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_ROTATED_BRICKS = create("certus_quartz_block_rotated_bricks", "Rotated Certus Quartz Bricks").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_SCALES = create("certus_quartz_block_scales", "Certus Quartz Scales").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_SMALL_TILES = create("certus_quartz_block_small_tiles", "Small Certus Quartz Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_SQUARES = create("certus_quartz_block_squares", "Certus Quartz Block Squares").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_STRIPES = create("certus_quartz_block_stripes", "Certus Quartz Block Stripes").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_TILES = create("certus_quartz_block_tiles", "Certus Quartz Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    // Fluix block
    private static final Consumer<BlockProperties> FLUIX_BLOCK_BLOCK_PROPERTIES = p -> p.sound(SoundType.STONE).mapColor(MapColor.COLOR_PURPLE).destroyTime(3).explosionResistance(5).requiresCorrectTool();
    private static final Consumer<RechiseledBlockBuilder> FLUIX_BLOCK_CONFIGURER = b -> b.properties(FLUIX_BLOCK_BLOCK_PROPERTIES).miningTagsFrom(() -> Blocks.QUARTZ_BLOCK).recipe(RechiseledAERecipes.FLUIX_BLOCK).itemAndBlockTag(RechiseledAE.identifier("fluix_block")).withStairs(s -> s.itemAndBlockTag(RechiseledAE.identifier("fluix_block_stairs"))).withSlabs(s -> s.itemAndBlockTag(RechiseledAE.identifier("fluix_block_slabs")));
    public static final RechiseledBlockType FLUIX_BLOCK_BORDERED = create("fluix_block_bordered", "Bordered Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_BRICKS = create("fluix_block_bricks", "Fluix Bricks").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_CHARGED = create("fluix_block_charged", "Charged Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_CHARGED_BRICKS = create("fluix_block_charged_bricks", "Charged Fluix Bricks").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_CHARGED_CHECKERBOARD = create("fluix_block_charged_checkerboard", "Charged Checkerboard Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_CHARGED_CRUSHED = create("fluix_block_charged_crushed", "Charged Crushed Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_CHARGED_ENCHANTED = create("fluix_block_charged_enchanted", "Charged Enchanted Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_CHARGED_SMOOTH = create("fluix_block_charged_smooth", "Smooth Charged Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_CHARGED_SWIRLING = create("fluix_block_charged_swirling", "Charged Swirling Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_CHECKERBOARD = create("fluix_block_checkerboard", "Checkerboard Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_CONNECTING = create("fluix_block_connecting", "Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.FLUIX_BLOCK.block(), () -> AEBlocks.FLUIX_STAIRS.block(), () -> AEBlocks.FLUIX_SLAB.block()).build();
    public static final RechiseledBlockType FLUIX_BLOCK_CRUSHED = create("fluix_block_crushed", "Crushed Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_DECORATED = create("fluix_block_decorated", "Decorated Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_DIAGONAL_TILES = create("fluix_block_diagonal_tiles", "Diagonal Fluix Tiles").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_ENCHANTED = create("fluix_block_enchanted", "Enchanted Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_SMOOTH = create("fluix_block_smooth", "Smooth Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_SQUARES = create("fluix_block_squares", "Fluix Block Squares").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_SWIRLING = create("fluix_block_swirling", "Swirling Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_TILES = create("fluix_block_tiles", "Fluix Tiles").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_WOVEN = create("fluix_block_woven", "Woven Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    // Sky stone block
    private static final Consumer<BlockProperties> SKY_STONE_BLOCK_BLOCK_PROPERTIES = p -> p.sound(SoundType.STONE).mapColor(MapColor.COLOR_BLACK).destroyTime(5).explosionResistance(150).requiresCorrectTool();
    private static final Consumer<RechiseledBlockBuilder> SKY_STONE_BLOCK_CONFIGURER = b -> b.properties(SKY_STONE_BLOCK_BLOCK_PROPERTIES).blockTag(BlockTags.MINEABLE_WITH_PICKAXE.location()).blockTag(BlockTags.NEEDS_IRON_TOOL.location()).recipe(RechiseledAERecipes.SKY_STONE_BLOCK).itemAndBlockTag(RechiseledAE.identifier("sky_stone_block")).withStairs(s -> s.itemAndBlockTag(RechiseledAE.identifier("sky_stone_block_stairs"))).withSlabs(s -> s.itemAndBlockTag(RechiseledAE.identifier("sky_stone_block_slabs")));
    public static final RechiseledBlockType SKY_STONE_BLOCK_BRICK_BORDERED = create("sky_stone_block_brick_bordered", "Brick Bordered Sky Stone Block").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_BRICK_PATTERN = create("sky_stone_block_brick_pattern", "Sky Stone Brick Pattern").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_BRICKS = create("sky_stone_block_bricks", "Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.SKY_STONE_BRICK.block(), () -> AEBlocks.SKY_STONE_BRICK_STAIRS.block(), () -> AEBlocks.SKY_STONE_BRICK_SLAB.block()).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_CONNECTING = create("sky_stone_block_connecting", "Sky Stone Block").configure(SKY_STONE_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.SMOOTH_SKY_STONE_BLOCK.block(), () -> AEBlocks.SMOOTH_SKY_STONE_STAIRS.block(), () -> AEBlocks.SMOOTH_SKY_STONE_SLAB.block()).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED = create("sky_stone_block_polished", "Polished Sky Stone Block").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_BORDERED = create("sky_stone_block_polished_bordered", "Bordered Polished Sky Stone Block").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_BORDERED_DIAGONAL_BRICKS = create("sky_stone_block_polished_bordered_diagonal_bricks", "Bordered Diagonal Polished Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_BORDERED_SQUARES = create("sky_stone_block_polished_bordered_squares", "Bordered Polished Sky Stone Squares").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_BRICK_PATTERN = create("sky_stone_block_polished_brick_pattern", "Polished Sky Stone Brick Pattern").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_BRICK_PAVING = create("sky_stone_block_polished_brick_paving", "Polished Sky Stone Brick Paving").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_BRICKS = create("sky_stone_block_polished_bricks", "Polished Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_DIAGONAL_BRICKS = create("sky_stone_block_polished_diagonal_bricks", "Diagonal Polished Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_ROTATED_BRICKS = create("sky_stone_block_polished_rotated_bricks", "Rotated Polished Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_SMALL_BRICKS = create("sky_stone_block_polished_small_bricks", "Small Polished Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_SMOOTH = create("sky_stone_block_polished_smooth", "Smooth Polished Sky Stone Block").configure(SKY_STONE_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_SQUARES = create("sky_stone_block_polished_squares", "Polished Sky Stone Squares").configure(SKY_STONE_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_POLISHED_TILES = create("sky_stone_block_polished_tiles", "Polished Sky Stone Tiles").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BLOCK_TILES = create("sky_stone_block_tiles", "Sky Stone Tiles").configure(SKY_STONE_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType SKY_STONE_BRICKS = create("sky_stone_bricks", "Sky Stone Bricks").configure(SKY_STONE_BLOCK_CONFIGURER).build();

    private static RechiseledBlockBuilder create(String identifier, String translation){
        return RechiseledAE.REGISTRATION.block(identifier).translation(translation);
    }

    public static void init(){
        // Cause this class to be initialized
    }
}
