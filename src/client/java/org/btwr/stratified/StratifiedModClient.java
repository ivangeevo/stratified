package org.btwr.stratified;

import net.fabricmc.api.ClientModInitializer;
import org.btwr.stratified.debug.StrataXray;

public class StratifiedModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        StrataXray.register();
    }
}