package org.btwr.stratified.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import org.btwr.stratified.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.MANTLESTONE)
                //.add(ModBlocks.MANTLESTONE_COAL_ORE)
                //.add(ModBlocks.MANTLESTONE_COPPER_ORE)
                //.add(ModBlocks.MANTLESTONE_IRON_ORE)
                //.add(ModBlocks.MANTLESTONE_GOLD_ORE)
                //.add(ModBlocks.MANTLESTONE_LAPIS_ORE)
                //.add(ModBlocks.MANTLESTONE_REDSTONE_ORE)
                //.add(ModBlocks.MANTLESTONE_DIAMOND_ORE)
                //.add(ModBlocks.MANTLESTONE_EMERALD_ORE)

        ;
    }
}
