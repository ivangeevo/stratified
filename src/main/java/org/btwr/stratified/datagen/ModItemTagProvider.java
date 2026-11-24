package org.btwr.stratified.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import org.btwr.stratified.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, BlockTagProvider blockTagProvider) {
        super(output, registriesFuture, blockTagProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Cobbled mantlestone works like cobblestone/cobbled deepslate for stone tools and furnaces
        getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS).add(ModBlocks.COBBLED_MANTLESTONE.asItem());
        getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS).add(ModBlocks.COBBLED_MANTLESTONE.asItem());

        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.STONE_BUTTONS, ItemTags.STONE_BUTTONS);

        copy(BlockTags.COAL_ORES, ItemTags.COAL_ORES);
        copy(BlockTags.COPPER_ORES, ItemTags.COPPER_ORES);
        copy(BlockTags.IRON_ORES, ItemTags.IRON_ORES);
        copy(BlockTags.GOLD_ORES, ItemTags.GOLD_ORES);
        copy(BlockTags.LAPIS_ORES, ItemTags.LAPIS_ORES);
        copy(BlockTags.REDSTONE_ORES, ItemTags.REDSTONE_ORES);
        copy(BlockTags.DIAMOND_ORES, ItemTags.DIAMOND_ORES);
        copy(BlockTags.EMERALD_ORES, ItemTags.EMERALD_ORES);

        copy(ConventionalBlockTags.ORES, ConventionalItemTags.ORES);
        copy(ConventionalBlockTags.STONES, ConventionalItemTags.STONES);
        copy(ConventionalBlockTags.COBBLESTONES, ConventionalItemTags.COBBLESTONES);
    }
}