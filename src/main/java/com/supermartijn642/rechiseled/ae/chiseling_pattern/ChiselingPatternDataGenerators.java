package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import com.supermartijn642.core.generator.BlockStateGenerator;
import com.supermartijn642.core.generator.LanguageGenerator;
import com.supermartijn642.core.generator.ModelGenerator;
import com.supermartijn642.core.generator.RecipeGenerator;
import com.supermartijn642.core.registry.GeneratorRegistrationHandler;
import com.supermartijn642.rechiseled.Rechiseled;
import com.supermartijn642.rechiseled.ae.RechiseledAE;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.Tags;

/**
 * Created 14/02/2026 by SuperMartijn642
 */
public class ChiselingPatternDataGenerators {

    public static void register(){
        GeneratorRegistrationHandler handler = GeneratorRegistrationHandler.get(RechiseledAE.MODID);
        handler.addGenerator(cache -> new LanguageGenerator(RechiseledAE.MODID, cache, "en_us") {
            @Override
            public void generate(){
                // Chiseling pattern
                this.item(RechiseledAE.chiseling_pattern, "Chiseling Pattern");
                // Chiseling pattern encoder
                this.block(RechiseledAE.chiseling_pattern_encoder, "Chiseling Pattern Encoder");
                this.translation("rechiseledae.chiseling_pattern_encoder.hint", "Allows encoding chiseling patterns for autocrafting with %s");
                // Chiseling pattern encoder screen
                this.translation("rechiseledae.chiseling_pattern_encoder.title", "Encoder");
                this.translation("rechiseledae.chiseling_pattern_encoder.connecting", "Connected textures: %s");
                this.translation("rechiseledae.chiseling_pattern_encoder.connecting.on", "On");
                this.translation("rechiseledae.chiseling_pattern_encoder.connecting.off", "Off");
                this.translation("rechiseledae.chiseling_pattern_encoder.select_block", "Select %s");
                this.translation("rechiseledae.chiseling_pattern_encoder.preview", "Block Preview");
                this.translation("rechiseledae.chiseling_pattern_encoder.select_shape", "Shape: %s");
                this.translation("rechiseledae.chiseling_pattern_encoder.entry.recipe", "Recipe: %s");
                this.translation("rechiseledae.chiseling_pattern_encoder.entry.owner", "Plugin: %s");
                this.translation("rechiseledae.chiseling_pattern_encoder.encode", "Encode Pattern");
            }
        });
        handler.addGenerator(cache -> new ModelGenerator(RechiseledAE.MODID, cache) {
            @Override
            public void generate(){
                this.itemGenerated(RechiseledAE.chiseling_pattern, RechiseledAE.identifier("item/chiseling_pattern"));
                this.model("block/chiseling_pattern_encoder_off")
                    .parent("block/chiseling_pattern_encoder")
                    .texture("all", "block/chiseling_pattern_encoder_off");
                this.model("item/chiseling_pattern_encoder")
                    .parent("block/chiseling_pattern_encoder");
            }
        });
        handler.addGenerator(cache -> new BlockStateGenerator(RechiseledAE.MODID, cache) {
            @Override
            public void generate(){
                this.blockState(RechiseledAE.chiseling_pattern_encoder)
                    .variantsForProperty(ChiselingPatternEncoderBlock.ON, (state, variant) -> {
                        if(state.get(ChiselingPatternEncoderBlock.ON))
                            variant.model("block/chiseling_pattern_encoder");
                        else
                            variant.model("block/chiseling_pattern_encoder_off");
                    });
            }
        });
        handler.addGenerator(cache -> new RecipeGenerator(RechiseledAE.MODID, cache) {
            @Override
            public void generate(){
                this.shaped(RechiseledAE.chiseling_pattern_encoder)
                    .pattern("ABA")
                    .pattern("CDC")
                    .input('A', Tags.Items.GLASS_BLOCKS_CHEAP)
                    .input('B', Rechiseled.chisel)
                    .input('C', Tags.Items.INGOTS_IRON)
                    .input('D', Tags.Items.INGOTS_COPPER)
                    .unlockedBy(Rechiseled.chisel);
            }
        });
    }
}
