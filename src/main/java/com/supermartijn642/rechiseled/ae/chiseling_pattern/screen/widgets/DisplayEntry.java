package com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.widgets;

import com.supermartijn642.rechiseled.api.chiseling.ChiselingBlockShape;
import com.supermartijn642.rechiseled.api.chiseling.ChiselingEntry;
import com.supermartijn642.rechiseled.api.chiseling.ItemWithWorth;

/**
 * Created 17/02/2026 by SuperMartijn642
 */
public record DisplayEntry(int entryIndex, ChiselingEntry entry, ChiselingBlockShape shape) {
    public ItemWithWorth getItem(boolean connecting){
        return (!connecting || !this.entry.hasConnectingItem(this.shape)) && this.entry.hasRegularItem(this.shape) ? this.entry.getRegularItem(this.shape) : this.entry.getConnectingItem(this.shape);
    }

    public boolean hasItem(boolean connecting){
        return connecting ? this.entry.hasConnectingItem(this.shape) : this.entry.hasRegularItem(this.shape);
    }
}
