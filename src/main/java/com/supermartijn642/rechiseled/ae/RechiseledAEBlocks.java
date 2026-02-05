package com.supermartijn642.rechiseled.ae;

import appeng.core.definitions.AEBlocks;
import com.supermartijn642.core.block.BlockProperties;
import com.supermartijn642.rechiseled.api.blocks.BlockSpecification;
import com.supermartijn642.rechiseled.api.blocks.RechiseledBlockBuilder;
import com.supermartijn642.rechiseled.api.blocks.RechiseledBlockType;
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
    private static final Consumer<RechiseledBlockBuilder> CERTUS_QUARTZ_BLOCK_CONFIGURER = b -> b.properties(CERTUS_QUARTZ_BLOCK_BLOCK_PROPERTIES).miningTagsFrom(() -> Blocks.QUARTZ_BLOCK).recipe(RechiseledAERecipes.CERTUS_QUARTZ_BLOCK);
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_BRICKS = create("certus_quartz_block_bricks", "Certus Quartz Bricks").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.QUARTZ_BRICKS.block()).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT = create("certus_quartz_block_cut", "Cut Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.CUT_QUARTZ_BLOCK.block()).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_BORDERED = create("certus_quartz_block_cut_bordered", "Bordered Cut Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_BRICKS = create("certus_quartz_block_cut_bricks", "Cut Certus Quartz Bricks").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_CHISELED = create("certus_quartz_block_cut_chiseled", "Chiseled Cut Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_PATTERN = create("certus_quartz_block_cut_pattern", "Cut Certus Quartz Block Pattern").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_PAVING = create("certus_quartz_block_cut_paving", "Cut Certus Quartz Block Paving").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_PILLAR = create("certus_quartz_block_cut_pillar", "Certus Quartz Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).regularVariant(() -> AEBlocks.QUARTZ_PILLAR.block()).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_ROTATED_BRICKS = create("certus_quartz_block_cut_rotated_bricks", "Rotated Cut Certus Quartz Block Bricks").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_SCALES = create("certus_quartz_block_cut_scales", "Cut Certus Quartz Block Scales").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_SMALL_TILES = create("certus_quartz_block_cut_small_tiles", "Small Cut Certus Quartz Block Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_SQUARES = create("certus_quartz_block_cut_squares", "Cut Certus Quartz Block Squares").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_STRIPES = create("certus_quartz_block_cut_stripes", "Cut Certus Quartz Block Stripes").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_CUT_TILES = create("certus_quartz_block_cut_tiles", "Cut Certus Quartz Block Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_DIAGONAL_TILES = create("certus_quartz_block_diagonal_tiles", "Diagonal Certus Quartz Block Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_PILLAR = create("certus_quartz_block_pillar", "Certus Quartz Block Pillar").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).specification(BlockSpecification.PILLAR).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_POLISHED = create("certus_quartz_block_polished", "Polished Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_SHINY = create("certus_quartz_block_shiny", "Shiny Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_SMOOTH = create("certus_quartz_block_smooth", "Smooth Certus Quartz Block").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType CERTUS_QUARTZ_BLOCK_TILES = create("certus_quartz_block_tiles", "Certus Quartz Block Tiles").configure(CERTUS_QUARTZ_BLOCK_CONFIGURER).build();
    // Fluix block
    private static final Consumer<BlockProperties> FLUIX_BLOCK_BLOCK_PROPERTIES = p -> p.sound(SoundType.STONE).mapColor(MapColor.COLOR_PURPLE).destroyTime(3).explosionResistance(5).requiresCorrectTool();
    private static final Consumer<RechiseledBlockBuilder> FLUIX_BLOCK_CONFIGURER = b -> b.properties(FLUIX_BLOCK_BLOCK_PROPERTIES).miningTagsFrom(() -> Blocks.QUARTZ_BLOCK).recipe(RechiseledAERecipes.FLUIX_BLOCK);
    public static final RechiseledBlockType FLUIX_BLOCK_BORDERED = create("fluix_block_bordered", "Bordered Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_BRICKS = create("fluix_block_bricks", "Fluix Block Bricks").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_CHECKERBOARD = create("fluix_block_checkerboard", "Checkerboard Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_CONNECTING = create("fluix_block_connecting", "Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).regularVariant(() -> AEBlocks.FLUIX_BLOCK.block()).build();
    public static final RechiseledBlockType FLUIX_BLOCK_CRUSHED = create("fluix_block_crushed", "Crushed Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_DECORATED = create("fluix_block_decorated", "Decorated Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_DIAGONAL_TILES = create("fluix_block_diagonal_tiles", "Diagonal Fluix Block Tiles").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_ENCHANTED = create("fluix_block_enchanted", "Enchanted Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_SMOOTH = create("fluix_block_smooth", "Smooth Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_SQUARES = create("fluix_block_squares", "Fluix Block Squares").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_SWIRLING = create("fluix_block_swirling", "Swirling Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();
    public static final RechiseledBlockType FLUIX_BLOCK_TILES = create("fluix_block_tiles", "Fluix Block Tiles").configure(FLUIX_BLOCK_CONFIGURER).build();
    public static final RechiseledBlockType FLUIX_BLOCK_WOVEN = create("fluix_block_woven", "Woven Fluix Block").configure(FLUIX_BLOCK_CONFIGURER).noConnectingVariant().build();

    private static RechiseledBlockBuilder create(String identifier, String translation) {
        return RechiseledAE.REGISTRATION.block(identifier).translation(translation);
    }

    public static void init() {
        // Cause this class to be initialized
    }
}
