package org.btwr.stratified.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import org.btwr.stratified.block.ModBlocks;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        // Mantlestone gets vanilla stone's random mirrored/rotated look
        generator.registerMirrorable(ModBlocks.MANTLESTONE);

        TextureMap mantlestoneTextures = TextureMap.all(ModBlocks.MANTLESTONE);

        generator.new BlockTexturePool(mantlestoneTextures)
                .stairs(ModBlocks.MANTLESTONE_STAIRS)
                .pressurePlate(ModBlocks.MANTLESTONE_PRESSURE_PLATE)
                .button(ModBlocks.MANTLESTONE_BUTTON);

        Identifier mantlestoneModel = ModelIds.getBlockModelId(ModBlocks.MANTLESTONE);

        registerSlab(generator, ModBlocks.MANTLESTONE_SLAB, mantlestoneTextures, mantlestoneModel);

        generator.registerCubeAllModelTexturePool(ModBlocks.COBBLED_MANTLESTONE)
                .family(ModBlocks.COBBLED_MANTLESTONE_FAMILY);

        generator.registerSimpleCubeAll(ModBlocks.MANTLESTONE_COAL_ORE);
        generator.registerSimpleCubeAll(ModBlocks.MANTLESTONE_COPPER_ORE);
        generator.registerSimpleCubeAll(ModBlocks.MANTLESTONE_IRON_ORE);
        generator.registerSimpleCubeAll(ModBlocks.MANTLESTONE_GOLD_ORE);
        generator.registerSimpleCubeAll(ModBlocks.MANTLESTONE_LAPIS_ORE);
        generator.registerSimpleCubeAll(ModBlocks.MANTLESTONE_REDSTONE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.MANTLESTONE_DIAMOND_ORE);
        generator.registerSimpleCubeAll(ModBlocks.MANTLESTONE_EMERALD_ORE);
    }

    private static void registerSlab(BlockStateModelGenerator generator, Block slab, TextureMap textures, Identifier fullBlockModel) {
        Identifier bottom = Models.SLAB.upload(slab, textures, generator.modelCollector);
        Identifier top = Models.SLAB_TOP.upload(slab, textures, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab, bottom, top, fullBlockModel));
        generator.registerParentedItemModel(slab, bottom);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {}
}