package org.btwr.stratified.event;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Blocks;

public class ModLootTableEvents {

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!key.equals(Blocks.STONE.getLootTableKey())) return;
            // todo?
        });

    }


}
