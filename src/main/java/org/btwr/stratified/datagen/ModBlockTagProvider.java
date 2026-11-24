package org.btwr.stratified.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import org.btwr.stratified.block.ModBlocks;
import org.btwr.stratified.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.MANTLESTONE)
                .add(ModBlocks.MANTLESTONE_STAIRS)
                .add(ModBlocks.MANTLESTONE_SLAB)
                .add(ModBlocks.MANTLESTONE_PRESSURE_PLATE)
                .add(ModBlocks.MANTLESTONE_BUTTON)
                .add(ModBlocks.COBBLED_MANTLESTONE)
                .add(ModBlocks.COBBLED_MANTLESTONE_STAIRS)
                .add(ModBlocks.COBBLED_MANTLESTONE_SLAB)
                .add(ModBlocks.COBBLED_MANTLESTONE_WALL)
                .forceAddTag(ModTags.Blocks.ORE_STRATA2);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.MANTLESTONE_COPPER_ORE)
                .add(ModBlocks.MANTLESTONE_IRON_ORE)
                .add(ModBlocks.MANTLESTONE_LAPIS_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MANTLESTONE_GOLD_ORE)
                .add(ModBlocks.MANTLESTONE_REDSTONE_ORE)
                .add(ModBlocks.MANTLESTONE_DIAMOND_ORE)
                .add(ModBlocks.MANTLESTONE_EMERALD_ORE);

        getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD).add(ModBlocks.MANTLESTONE);
        getOrCreateTagBuilder(BlockTags.STAIRS).add(ModBlocks.MANTLESTONE_STAIRS, ModBlocks.COBBLED_MANTLESTONE_STAIRS);
        getOrCreateTagBuilder(BlockTags.SLABS).add(ModBlocks.MANTLESTONE_SLAB, ModBlocks.COBBLED_MANTLESTONE_SLAB);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.COBBLED_MANTLESTONE_WALL);
        getOrCreateTagBuilder(BlockTags.STONE_PRESSURE_PLATES).add(ModBlocks.MANTLESTONE_PRESSURE_PLATE);
        getOrCreateTagBuilder(BlockTags.STONE_BUTTONS).add(ModBlocks.MANTLESTONE_BUTTON);

        getOrCreateTagBuilder(BlockTags.COAL_ORES).add(ModBlocks.MANTLESTONE_COAL_ORE);
        getOrCreateTagBuilder(BlockTags.COPPER_ORES).add(ModBlocks.MANTLESTONE_COPPER_ORE);
        getOrCreateTagBuilder(BlockTags.IRON_ORES).add(ModBlocks.MANTLESTONE_IRON_ORE);
        getOrCreateTagBuilder(BlockTags.GOLD_ORES).add(ModBlocks.MANTLESTONE_GOLD_ORE);
        getOrCreateTagBuilder(BlockTags.LAPIS_ORES).add(ModBlocks.MANTLESTONE_LAPIS_ORE);
        getOrCreateTagBuilder(BlockTags.REDSTONE_ORES).add(ModBlocks.MANTLESTONE_REDSTONE_ORE);
        getOrCreateTagBuilder(BlockTags.DIAMOND_ORES).add(ModBlocks.MANTLESTONE_DIAMOND_ORE);
        getOrCreateTagBuilder(BlockTags.EMERALD_ORES).add(ModBlocks.MANTLESTONE_EMERALD_ORE);

        getOrCreateTagBuilder(ConventionalBlockTags.ORES).forceAddTag(ModTags.Blocks.ORE_STRATA2);
        getOrCreateTagBuilder(ConventionalBlockTags.STONES).add(ModBlocks.MANTLESTONE);
        getOrCreateTagBuilder(ConventionalBlockTags.COBBLESTONES).add(ModBlocks.COBBLED_MANTLESTONE);

        getOrCreateTagBuilder(ModTags.Blocks.STONE_STRATA2)
                .add(ModBlocks.MANTLESTONE);

        getOrCreateTagBuilder(ModTags.Blocks.ORE_STRATA2)
                .add(ModBlocks.MANTLESTONE_COAL_ORE)
                .add(ModBlocks.MANTLESTONE_COPPER_ORE)
                .add(ModBlocks.MANTLESTONE_IRON_ORE)
                .add(ModBlocks.MANTLESTONE_GOLD_ORE)
                .add(ModBlocks.MANTLESTONE_LAPIS_ORE)
                .add(ModBlocks.MANTLESTONE_REDSTONE_ORE)
                .add(ModBlocks.MANTLESTONE_DIAMOND_ORE)
                .add(ModBlocks.MANTLESTONE_EMERALD_ORE);
    }
}