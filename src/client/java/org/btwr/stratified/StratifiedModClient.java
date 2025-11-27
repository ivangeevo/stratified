package org.btwr.stratified;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.util.Identifier;
import org.btwr.stratified.model.Strata2StoneModel;
import org.btwr.stratified.render.BlockRenderLayerMappings;

public class StratifiedModClient implements ClientModInitializer {

    private static final String[] oreTypes = new String[]{"coal", "copper", "iron", "gold", "lapis", "redstone", "diamond", "emerald"};

    public static final Identifier STONE_STRATA_1_TEXTURE = Identifier.ofVanilla("block/stone");
    public static final Identifier STONE_STRATA_2_TEXTURE = Identifier.of("stratified", "block/stone_strata_2");
    public static final Identifier STONE_STRATA_3_TEXTURE = Identifier.of("stratified", "block/stone_strata_3");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMappings.register();

        /**
        // Replace stone with another stone
        ModelLoadingPlugin.register(pluginContext -> {
            //pluginContext.modifyModelAfterBake().register(ModelModifier.OVERRIDE_PHASE, this::createUnderY33StrataModel);
        });
        // replace ores
        for (String oreType : oreTypes) {
            ModelLoadingPlugin.register(pluginContext -> {
                //pluginContext.modifyModelAfterBake().register(ModelModifier.OVERRIDE_PHASE, createUnderY33OreStrataModel(oreType, this::createUnderY33StrataModel));
            });
        }


        // Client mod init class
         // This one actually works
        // Modifies a block model with another one to always look different
        ModelLoadingPlugin.register(pluginContext -> {
            pluginContext.modifyModelAfterBake().register(ModelModifier.OVERRIDE_PHASE, this::createStrataModel);
        });
        **/
    }

    // baseType is like "stone", "ore", etc.
    private BakedModel createUnderY33StrataModel(String baseType, BakedModel bakedModel, ModelModifier.AfterBake.Context context) {
        Identifier id = context.resourceId();
        if (id != null && id.toString().contains(baseType)) {
            return new Strata2StoneModel(bakedModel, STONE_STRATA_3_TEXTURE, 32);
        }
        return bakedModel;
    }

    private BakedModel createUnderY33OreStrataModel(String type, BakedModel bakedModel, ModelModifier.AfterBake.Context context) {
        Identifier id = context.resourceId();
        if (id != null && id.toString().contains("ore")) {
            return new Strata2StoneModel(bakedModel, getOreModelIdWithStrata(type, 2), 32);
        }
        return bakedModel;
    }

    private Identifier getOreModelIdWithStrata(String type, int strata) {
        return Identifier.of("stratified", "block/stone_ore_" + type + "_strata_" + strata);
    }

}
