package org.btwr.stratified.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.btwr.stratified.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {
    public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {
        builder.add(ModBlocks.MANTLESTONE, "Mantlestone");
        builder.add(ModBlocks.MANTLESTONE_STAIRS, "Mantlestone Stairs");
        builder.add(ModBlocks.MANTLESTONE_SLAB, "Mantlestone Slab");
        builder.add(ModBlocks.MANTLESTONE_PRESSURE_PLATE, "Mantlestone Pressure Plate");
        builder.add(ModBlocks.MANTLESTONE_BUTTON, "Mantlestone Button");
        builder.add(ModBlocks.COBBLED_MANTLESTONE, "Cobbled Mantlestone");
        builder.add(ModBlocks.COBBLED_MANTLESTONE_STAIRS, "Cobbled Mantlestone Stairs");
        builder.add(ModBlocks.COBBLED_MANTLESTONE_SLAB, "Cobbled Mantlestone Slab");
        builder.add(ModBlocks.COBBLED_MANTLESTONE_WALL, "Cobbled Mantlestone Wall");

        builder.add(ModBlocks.MANTLESTONE_COAL_ORE, "Mantlestone Coal Ore");
        builder.add(ModBlocks.MANTLESTONE_COPPER_ORE, "Mantlestone Copper Ore");
        builder.add(ModBlocks.MANTLESTONE_IRON_ORE, "Mantlestone Iron Ore");
        builder.add(ModBlocks.MANTLESTONE_GOLD_ORE, "Mantlestone Gold Ore");
        builder.add(ModBlocks.MANTLESTONE_LAPIS_ORE, "Mantlestone Lapis Lazuli Ore");
        builder.add(ModBlocks.MANTLESTONE_REDSTONE_ORE, "Mantlestone Redstone Ore");
        builder.add(ModBlocks.MANTLESTONE_DIAMOND_ORE, "Mantlestone Diamond Ore");
        builder.add(ModBlocks.MANTLESTONE_EMERALD_ORE, "Mantlestone Emerald Ore");

        builder.add("generator.stratified.bedrock_sheets", "Bedrock Sheets");
        builder.add("generator.stratified.bedrock_sheets.info", "Bedrock sheets below Y 16 that must be mined around, with a near-solid floor sheet sealing the deepest layer");
    }
}