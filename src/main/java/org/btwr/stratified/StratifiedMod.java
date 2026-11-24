package org.btwr.stratified;

import net.fabricmc.api.ModInitializer;
import org.btwr.stratified.block.ModBlocks;
import org.btwr.stratified.strata.PlacedBlocks;
import org.btwr.stratified.strata.StrataEvents;
import org.btwr.stratified.world.BedrockSheetsChunkGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StratifiedMod implements ModInitializer {
    public static final String MOD_ID = "stratified";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.register();
        BedrockSheetsChunkGenerator.register();
        PlacedBlocks.register();
        StrataEvents.register();
    }
}