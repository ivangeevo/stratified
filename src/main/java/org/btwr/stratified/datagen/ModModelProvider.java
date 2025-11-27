package org.btwr.stratified.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.registry.RegistryWrapper;
import org.btwr.stratified.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MANTLESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBBLED_MANTLESTONE);
        blockStateModelGenerator.registerLog(ModBlocks.MANTLESTONE_COAL_ORE);
        blockStateModelGenerator.registerLog(ModBlocks.MANTLESTONE_COPPER_ORE);
        blockStateModelGenerator.registerLog(ModBlocks.MANTLESTONE_IRON_ORE);
        blockStateModelGenerator.registerLog(ModBlocks.MANTLESTONE_GOLD_ORE);
        blockStateModelGenerator.registerLog(ModBlocks.MANTLESTONE_LAPIS_ORE);
        blockStateModelGenerator.registerLog(ModBlocks.MANTLESTONE_REDSTONE_ORE);
        blockStateModelGenerator.registerLog(ModBlocks.MANTLESTONE_DIAMOND_ORE);
        blockStateModelGenerator.registerLog(ModBlocks.MANTLESTONE_EMERALD_ORE);

        //blockStateModelGenerator.registerSingleton(ModBlocks.MANTLESTONE_STAIRS, BlockStateModelGenerator.createStairsBlockState(ModBlocks.MANTLESTONE_STAIRS, Models.INNER_STAIRS, Models.STAIRS, Models.OUTER_STAIRS));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}