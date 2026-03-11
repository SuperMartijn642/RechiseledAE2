package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import appeng.api.crafting.IPatternDetails;
import appeng.api.stacks.AEItemKey;
import appeng.crafting.pattern.EncodedPatternItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

/**
 * Created 10/03/2026 by SuperMartijn642
 */
public class ChiselingPatternItem extends EncodedPatternItem {

    public ChiselingPatternItem(Properties properties){
        super(properties);
    }

    @Override
    public @Nullable IPatternDetails decode(ItemStack stack, Level level, boolean tryRecovery){
        if(stack.getItem() == this && stack.hasTag() && level != null)
            return this.decode(AEItemKey.of(stack), level);
        return null;
    }

    @Override
    public @Nullable IPatternDetails decode(AEItemKey item, Level level){
        if(item != null && item.hasTag()){
            try{
                return new ChiselingPattern(item, level);
            }catch(Exception ignore){}
        }
        return null;
    }
}
