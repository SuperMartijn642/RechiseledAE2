package com.supermartijn642.rechiseled.ae.compat.jei;

import com.supermartijn642.rechiseled.ae.RechiseledAE;
import com.supermartijn642.rechiseled.compat.jei.ChiselingJEIPlugin;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.resources.ResourceLocation;

/**
 * Created 19/02/2026 by SuperMartijn642
 */
@JeiPlugin
public class RechiseledCreateJEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid(){
        return ResourceLocation.fromNamespaceAndPath(RechiseledAE.MODID, "chiseling_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration){
        registration.addRecipeCatalyst(RechiseledAE.chiseling_pattern_encoder, ChiselingJEIPlugin.CHISELING_RECIPE_TYPE);
    }
}
