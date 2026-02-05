package com.supermartijn642.rechiseled.ae.compat.rei;

import com.supermartijn642.rechiseled.ae.RechiseledAE;
import com.supermartijn642.rechiseled.compat.rei.ChiselingREIPlugin;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;

/**
 * Created 19/02/2026 by SuperMartijn642
 */
@REIPluginClient
public class RechiseledCreateREIPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry){
        registry.addWorkstations(ChiselingREIPlugin.CHISELING_CATEGORY, EntryStacks.of(RechiseledAE.chiseling_pattern_encoder));
    }
}
