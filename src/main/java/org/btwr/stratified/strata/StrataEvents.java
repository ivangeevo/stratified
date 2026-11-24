package org.btwr.stratified.strata;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.minecraft.world.World;

public final class StrataEvents {
    private StrataEvents() {}

    public static void register() {
        // Fires for both generated and disk-loaded chunks, as they become full and before they're sent to players
        ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
            if (world.getRegistryKey() == World.OVERWORLD) {
                StrataConverter.convertChunk(chunk);
            }
        });
    }
}