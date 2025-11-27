package org.btwr.stratified.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.btwr.stratified.StratifiedMod;

public class ModBlocks {

    // TODO: Check strength values of these blocks.
    public static final Block MANTLESTONE = registerBlock("mantlestone", new Block(mantlestoneBlockSettings(2.25f, 9.0f)));
    public static final Block MANTLESTONE_STAIRS = registerBlock("mantlestone_stairs", new StairsBlock(ModBlocks.MANTLESTONE.getDefaultState(), mantlestoneBlockSettings(2.25f, 9.0f)));
    public static final Block MANTLESTONE_SLAB = registerBlock("mantlestone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_SLAB).sounds(BlockSoundGroup.TUFF)));
    public static final Block MANTLESTONE_PRESSURE_PLATE = registerBlock("mantlestone_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, mantlestoneBlockSettings(2.25f, 9.0f)));
    public static final Block MANTLESTONE_BUTTON = registerBlock("mantlestone_button", new ButtonBlock(BlockSetType.STONE, 20, mantlestoneBlockSettings(2.25f, 9.0f)));
    public static final Block COBBLED_MANTLESTONE = registerBlock("cobbled_mantlestone", new Block(mantlestoneCobbleBlockSettings(2.25f, 9.0f)));
    public static final Block COBBLED_MANTLESTONE_STAIRS = registerBlock("cobbled_mantlestone_stairs", new StairsBlock(ModBlocks.COBBLED_MANTLESTONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.COBBLESTONE_STAIRS).sounds(BlockSoundGroup.TUFF)));
    public static final Block COBBLED_MANTLESTONE_SLAB = registerBlock("cobbled_mantlestone_slab", new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE_SLAB).sounds(BlockSoundGroup.TUFF)));
    public static final Block COBBLED_MANTLESTONE_WALL = registerBlock("cobbled_mantlestone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.COBBLESTONE_WALL).sounds(BlockSoundGroup.TUFF)));

    public static final Block MANTLESTONE_COAL_ORE = registerBlock("mantlestone_coal_ore", new ExperienceDroppingBlock(UniformIntProvider.create(0, 2), AbstractBlock.Settings.copy(Blocks.COAL_ORE).sounds(BlockSoundGroup.TUFF)));
    public static final Block MANTLESTONE_COPPER_ORE = registerBlock("mantlestone_copper_ore", new ExperienceDroppingBlock(ConstantIntProvider.create(0), AbstractBlock.Settings.copy(Blocks.COPPER_ORE).sounds(BlockSoundGroup.TUFF)));
    public static final Block MANTLESTONE_IRON_ORE = registerBlock("mantlestone_iron_ore", new ExperienceDroppingBlock(ConstantIntProvider.create(0), AbstractBlock.Settings.copy(Blocks.IRON_ORE).sounds(BlockSoundGroup.TUFF)));
    public static final Block MANTLESTONE_GOLD_ORE = registerBlock("mantlestone_gold_ore", new ExperienceDroppingBlock(ConstantIntProvider.create(0), AbstractBlock.Settings.copy(Blocks.GOLD_ORE).sounds(BlockSoundGroup.TUFF)));
    public static final Block MANTLESTONE_LAPIS_ORE = registerBlock("mantlestone_lapis_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.LAPIS_ORE).sounds(BlockSoundGroup.TUFF)));
    public static final Block MANTLESTONE_REDSTONE_ORE = registerBlock("mantlestone_redstone_ore", new RedstoneOreBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_ORE).sounds(BlockSoundGroup.TUFF)));
    public static final Block MANTLESTONE_DIAMOND_ORE = registerBlock("mantlestone_diamond_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE).sounds(BlockSoundGroup.TUFF)));
    public static final Block MANTLESTONE_EMERALD_ORE = registerBlock("mantlestone_emerald_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), AbstractBlock.Settings.copy(Blocks.EMERALD_ORE).sounds(BlockSoundGroup.TUFF)));

    private static AbstractBlock.Settings mantlestoneCobbleBlockSettings(float str, float hardness) {
        return stoneBlockSettings(Blocks.COBBLESTONE, str, hardness)
                .sounds(BlockSoundGroup.TUFF)
                .mapColor(MapColor.STONE_GRAY);
    }

    private static AbstractBlock.Settings mantlestoneBlockSettings(float str, float hardness) {
        return stoneBlockSettings(Blocks.STONE, str, hardness).sounds(BlockSoundGroup.TUFF);
    }

    private static AbstractBlock.Settings stoneBlockSettings(Block block, float str, float hardness) {
        return AbstractBlock.Settings.copy(block).strength(str).hardness(hardness);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(StratifiedMod.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(StratifiedMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(StratifiedMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings())
        );
    }

    public static void register() {
        StratifiedMod.LOGGER.debug("Registering ModBlocks for " + StratifiedMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Blocks.STONE, MANTLESTONE);
            content.addAfter(Blocks.STONE_STAIRS, MANTLESTONE_STAIRS);
            content.addAfter(Blocks.STONE_SLAB, MANTLESTONE_SLAB);
            content.addAfter(Blocks.STONE_PRESSURE_PLATE, MANTLESTONE_PRESSURE_PLATE);
            content.addAfter(Blocks.STONE_BUTTON, MANTLESTONE_BUTTON);
            content.addAfter(Blocks.COBBLESTONE, COBBLED_MANTLESTONE);
            content.addAfter(Blocks.COBBLESTONE_STAIRS, COBBLED_MANTLESTONE_STAIRS);
            content.addAfter(Blocks.COBBLESTONE_SLAB, COBBLED_MANTLESTONE_SLAB);
            content.addAfter(Blocks.COBBLESTONE_WALL, COBBLED_MANTLESTONE_WALL);

            content.addAfter(Blocks.COAL_ORE, MANTLESTONE_COAL_ORE);
            content.addAfter(Blocks.COPPER_ORE, MANTLESTONE_COPPER_ORE);
            content.addAfter(Blocks.IRON_ORE, MANTLESTONE_IRON_ORE);
            content.addAfter(Blocks.GOLD_ORE, MANTLESTONE_GOLD_ORE);
            content.addAfter(Blocks.LAPIS_ORE, MANTLESTONE_LAPIS_ORE);
            content.addAfter(Blocks.REDSTONE_ORE, MANTLESTONE_REDSTONE_ORE);
            content.addAfter(Blocks.DIAMOND_ORE, MANTLESTONE_DIAMOND_ORE);
            content.addAfter(Blocks.EMERALD_ORE, MANTLESTONE_EMERALD_ORE);
        });
    }

    public class MantlestoneBlocks {
        public BlockSetType blockSetType;
        public BlockFamily blockFamily;

        public Block stoneBlock;
        public Block stoneStairsBlockStairs;
        public Block stoneSlabBlockSlab;
        public Block stonePressurePlateBlock;
        public Block stoneButtonBlock;

        public MantlestoneBlocks initialize() {
            blockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(Identifier.of("blood_wood"));

            stoneBlock = Blocks.createWoodenButtonBlock(blockSetType);
            stoneStairsBlockStairs = Blocks.createWoodenButtonBlock(blockSetType);
            stoneSlabBlockSlab = Blocks.createWoodenButtonBlock(blockSetType);
            stonePressurePlateBlock = Blocks.createWoodenButtonBlock(blockSetType);
            stoneButtonBlock = Blocks.createWoodenButtonBlock(blockSetType);

            blockFamily = BlockFamilies.register(stoneBlock)
                    .button(stoneButtonBlock)
                    .pressurePlate(stonePressurePlateBlock)
                    .slab(stoneSlabBlockSlab)
                    .stairs(stoneStairsBlockStairs)
                    .group("stone")
                    .unlockCriterionName("has_mantlestone")
                    .build();
            return this.initialize();
        }

    }

    public class CobbledMantlestoneBlocks {
        public BlockSetType blockSetType;
        public BlockFamily blockFamily;

        public Block cobbleBlock;
        public Block cobbleStairsBlock;
        public Block cobbleSlabBlock;
        public Block cobbleWallBlock;

        public CobbledMantlestoneBlocks initialize() {
            blockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(Identifier.of("blood_wood"));

            cobbleBlock = Blocks.createWoodenButtonBlock(blockSetType);
            cobbleStairsBlock = Blocks.createWoodenButtonBlock(blockSetType);
            cobbleSlabBlock = Blocks.createWoodenButtonBlock(blockSetType);
            cobbleWallBlock = Blocks.createWoodenButtonBlock(blockSetType);

            blockFamily = BlockFamilies.register(cobbleBlock)
                    .stairs(cobbleStairsBlock)
                    .slab(cobbleSlabBlock)
                    .wall(cobbleWallBlock)
                    .group("stone")
                    .unlockCriterionName("has_cobbled_mantlestone")
                    .build();
            return this;
        }
    }
}