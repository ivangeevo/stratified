package org.btwr.stratified.event;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.btwr.shared_library.api.ServerChunkGenerateEvents;
import org.btwr.stratified.block.ModBlocks;

import java.util.Set;

public class ModServerEvents {

    private static final Set<Block> STONE_ORES = Set.of(
            Blocks.COAL_ORE, Blocks.COPPER_ORE,
            Blocks.IRON_ORE, Blocks.GOLD_ORE,
            Blocks.LAPIS_ORE, Blocks.REDSTONE_ORE,
            Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE
    );

    private static final Set<Block> MANTLESTONE_ORES = Set.of(
            //ModBlocks.MANTLESTONE_COAL_ORE, ModBlocks.MANTLESTONE_COPPER_ORE,
            //ModBlocks.MANTLESTONE_IRON_ORE, ModBlocks.MANTLESTONE_GOLD_ORE,
            //ModBlocks.MANTLESTONE_LAPIS_ORE, ModBlocks.MANTLESTONE_REDSTONE_ORE,
            //ModBlocks.MANTLESTONE_DIAMOND_ORE, ModBlocks.MANTLESTONE_EMERALD_ORE
    );

    private static final Set<Block> DEEPSLATE_ORES = Set.of(
            Blocks.DEEPSLATE_COAL_ORE, Blocks.DEEPSLATE_COPPER_ORE,
            Blocks.DEEPSLATE_IRON_ORE, Blocks.DEEPSLATE_GOLD_ORE,
            Blocks.DEEPSLATE_LAPIS_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE_EMERALD_ORE
    );

    public static void register() {
        int stoneMin = 9;
        int stoneMax = 33;
        int deepslateMin = -10;
        int deepslateMax = 8;
        // Replace bottom stone layer
        ServerChunkGenerateEvents.createChunkReplaceEventWithYRange(
                Blocks.STONE, ModBlocks.MANTLESTONE, stoneMin, stoneMax
        );
        // Replace top deepslate layer
        ServerChunkGenerateEvents.createChunkReplaceEventWithYRange(
                Blocks.DEEPSLATE, ModBlocks.MANTLESTONE, deepslateMin, deepslateMax
        );

        // Replace stone ores in the new mantle layer
        for (Block block : STONE_ORES) {
            for (Block newBlock : MANTLESTONE_ORES) {
                ServerChunkGenerateEvents.createChunkReplaceEventWithYRange(
                        block, newBlock, stoneMin, stoneMax
                );
            }
        }

        // Replace deepslate ores in the new mantle layer
        for (Block block : DEEPSLATE_ORES) {
            for (Block newBlock : MANTLESTONE_ORES) {
                ServerChunkGenerateEvents.createChunkReplaceEventWithYRange(
                        block, newBlock, deepslateMin, deepslateMax
                );
            }
        }
    }
}
