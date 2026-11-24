package org.btwr.stratified.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class StratifiedModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        ModBlockTagProvider blockTagProvider = pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider((output, registries) -> new ModItemTagProvider(output, registries, blockTagProvider));
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModLangProvider::new);
    }
}