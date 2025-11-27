package org.btwr.stratified;

import net.fabricmc.api.ModInitializer;
import org.btwr.stratified.block.ModBlocks;
import org.btwr.stratified.event.ModEvents;
import org.btwr.stratified.event.ModServerEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StratifiedMod implements ModInitializer {
    public static final String MOD_ID = "stratified";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModEvents.register();
        ModServerEvents.register();
    }
}
