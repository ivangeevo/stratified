package org.btwr.stratified.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import org.btwr.stratified.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.addDrop(ModBlocks.MANTLESTONE, block -> this.drops(block, ModBlocks.COBBLED_MANTLESTONE));
        this.addDrop(ModBlocks.MANTLESTONE_STAIRS);
        this.addDrop(ModBlocks.MANTLESTONE_SLAB, this::slabDrops);
        this.addDrop(ModBlocks.MANTLESTONE_PRESSURE_PLATE);
        this.addDrop(ModBlocks.MANTLESTONE_BUTTON);
        this.addDrop(ModBlocks.COBBLED_MANTLESTONE);
        this.addDrop(ModBlocks.COBBLED_MANTLESTONE_STAIRS);
        this.addDrop(ModBlocks.COBBLED_MANTLESTONE_SLAB, this::slabDrops);
        this.addDrop(ModBlocks.COBBLED_MANTLESTONE_WALL);

        this.addDrop(ModBlocks.MANTLESTONE_COAL_ORE, block -> this.oreDrops(block, Items.COAL));
        this.addDrop(ModBlocks.MANTLESTONE_COPPER_ORE, this::copperOreDrops);
        this.addDrop(ModBlocks.MANTLESTONE_IRON_ORE, block -> this.oreDrops(block, Items.RAW_IRON));
        this.addDrop(ModBlocks.MANTLESTONE_GOLD_ORE, block -> this.oreDrops(block, Items.RAW_GOLD));
        this.addDrop(ModBlocks.MANTLESTONE_LAPIS_ORE, this::lapisOreDrops);
        this.addDrop(ModBlocks.MANTLESTONE_REDSTONE_ORE, this::redstoneOreDrops);
        this.addDrop(ModBlocks.MANTLESTONE_DIAMOND_ORE, block -> this.oreDrops(block, Items.DIAMOND));
        this.addDrop(ModBlocks.MANTLESTONE_EMERALD_ORE, block -> this.oreDrops(block, Items.EMERALD));
    }
}