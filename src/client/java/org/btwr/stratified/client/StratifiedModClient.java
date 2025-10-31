package org.btwr.stratified.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import org.btwr.stratified.client.model.Strata2StoneModel;

public class StratifiedModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        /**
        // Client mod init class
        ModelLoadingPlugin.register(pluginContext -> {
            pluginContext.modifyModelAfterBake().register((model, context) -> {
                Identifier id = context.resourceId();

                // Check if this is the vanilla stone model
                if (id.equals(ModelIdentifier.ofVanilla("block/stone", ""))) {
                    return new Strata2StoneModel(model);
                }

                return model;
            });
        });


         // This one actually works
        // Modifies a block model with another one to always look different
        ModelLoadingPlugin.register(pluginContext -> {
            pluginContext.modifyModelAfterBake().register(ModelModifier.OVERRIDE_PHASE, (bakedModel, context) -> {
                Identifier id = context.resourceId();
                if (id != null && id.toString().contains("stone")) {
                    return new Strata2StoneModel(bakedModel);
                }
                return bakedModel;
            });
        });
         **/
    }
}
