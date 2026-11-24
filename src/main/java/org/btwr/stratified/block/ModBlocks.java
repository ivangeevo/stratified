package org.btwr.stratified.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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
    // Strata 2 hardness follows BTW's ratios: stone 1.5 -> 2.0, ores 3.0 -> 4.5
    private static final float STONE_HARDNESS = 2.0f;
    private static final float STONE_RESISTANCE = 6.0f;
    private static final float COBBLE_HARDNESS = 2.5f;
    private static final float ORE_HARDNESS = 4.5f;
    private static final float ORE_RESISTANCE = 3.0f;

    public static final Block MANTLESTONE = registerBlock("mantlestone", new Block(mantleStoneSettings()));
    public static final Block MANTLESTONE_STAIRS = registerBlock("mantlestone_stairs", new StairsBlock(MANTLESTONE.getDefaultState(), mantleStoneSettings()));
    public static final Block MANTLESTONE_SLAB = registerBlock("mantlestone_slab", new SlabBlock(mantleStoneSettings()));
    public static final Block MANTLESTONE_PRESSURE_PLATE = registerBlock("mantlestone_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(Blocks.STONE_PRESSURE_PLATE).sounds(BlockSoundGroup.TUFF)));
    public static final Block MANTLESTONE_BUTTON = registerBlock("mantlestone_button", new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(Blocks.STONE_BUTTON).sounds(BlockSoundGroup.TUFF)));

    public static final Block COBBLED_MANTLESTONE = registerBlock("cobbled_mantlestone", new Block(cobbleMantleStoneSettings()));
    public static final Block COBBLED_MANTLESTONE_STAIRS = registerBlock("cobbled_mantlestone_stairs", new StairsBlock(COBBLED_MANTLESTONE.getDefaultState(), cobbleMantleStoneSettings()));
    public static final Block COBBLED_MANTLESTONE_SLAB = registerBlock("cobbled_mantlestone_slab", new SlabBlock(cobbleMantleStoneSettings()));
    public static final Block COBBLED_MANTLESTONE_WALL = registerBlock("cobbled_mantlestone_wall", new WallBlock(cobbleMantleStoneSettings().solid()));

    public static final Block MANTLESTONE_COAL_ORE = registerBlock("mantlestone_coal_ore", new ExperienceDroppingBlock(UniformIntProvider.create(0, 2), mantleStoneOreSettings(Blocks.COAL_ORE)));
    public static final Block MANTLESTONE_COPPER_ORE = registerBlock("mantlestone_copper_ore", new ExperienceDroppingBlock(ConstantIntProvider.create(0), mantleStoneOreSettings(Blocks.COPPER_ORE)));
    public static final Block MANTLESTONE_IRON_ORE = registerBlock("mantlestone_iron_ore", new ExperienceDroppingBlock(ConstantIntProvider.create(0), mantleStoneOreSettings(Blocks.IRON_ORE)));
    public static final Block MANTLESTONE_GOLD_ORE = registerBlock("mantlestone_gold_ore", new ExperienceDroppingBlock(ConstantIntProvider.create(0), mantleStoneOreSettings(Blocks.GOLD_ORE)));
    public static final Block MANTLESTONE_LAPIS_ORE = registerBlock("mantlestone_lapis_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), mantleStoneOreSettings(Blocks.LAPIS_ORE)));
    public static final Block MANTLESTONE_REDSTONE_ORE = registerBlock("mantlestone_redstone_ore", new RedstoneOreBlock(mantleStoneOreSettings(Blocks.REDSTONE_ORE)));
    public static final Block MANTLESTONE_DIAMOND_ORE = registerBlock("mantlestone_diamond_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), mantleStoneOreSettings(Blocks.DIAMOND_ORE)));
    public static final Block MANTLESTONE_EMERALD_ORE = registerBlock("mantlestone_emerald_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), mantleStoneOreSettings(Blocks.EMERALD_ORE)));

    public static final BlockFamily MANTLESTONE_FAMILY = BlockFamilies.register(MANTLESTONE)
            .stairs(MANTLESTONE_STAIRS)
            .slab(MANTLESTONE_SLAB)
            .pressurePlate(MANTLESTONE_PRESSURE_PLATE)
            .button(MANTLESTONE_BUTTON)
            .build();

    public static final BlockFamily COBBLED_MANTLESTONE_FAMILY = BlockFamilies.register(COBBLED_MANTLESTONE)
            .stairs(COBBLED_MANTLESTONE_STAIRS)
            .slab(COBBLED_MANTLESTONE_SLAB)
            .wall(COBBLED_MANTLESTONE_WALL)
            .build();

    private static AbstractBlock.Settings mantleStoneSettings() {
        return AbstractBlock.Settings.copy(Blocks.STONE)
                .strength(STONE_HARDNESS, STONE_RESISTANCE)
                .sounds(BlockSoundGroup.TUFF);
    }

    private static AbstractBlock.Settings cobbleMantleStoneSettings() {
        return AbstractBlock.Settings.copy(Blocks.COBBLESTONE)
                .strength(COBBLE_HARDNESS, STONE_RESISTANCE)
                .sounds(BlockSoundGroup.TUFF)
                .mapColor(MapColor.STONE_GRAY);
    }

    private static AbstractBlock.Settings mantleStoneOreSettings(Block vanillaOre) {
        return AbstractBlock.Settings.copy(vanillaOre)
                .strength(ORE_HARDNESS, ORE_RESISTANCE)
                .sounds(BlockSoundGroup.TUFF);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
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

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Blocks.STONE_BUTTON, MANTLESTONE, MANTLESTONE_STAIRS, MANTLESTONE_SLAB, MANTLESTONE_PRESSURE_PLATE, MANTLESTONE_BUTTON);
            content.addAfter(Blocks.COBBLESTONE_WALL, COBBLED_MANTLESTONE, COBBLED_MANTLESTONE_STAIRS, COBBLED_MANTLESTONE_SLAB, COBBLED_MANTLESTONE_WALL);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Blocks.STONE, MANTLESTONE);
            content.addAfter(Blocks.COAL_ORE, MANTLESTONE_COAL_ORE);
            content.addAfter(Blocks.IRON_ORE, MANTLESTONE_IRON_ORE);
            content.addAfter(Blocks.COPPER_ORE, MANTLESTONE_COPPER_ORE);
            content.addAfter(Blocks.GOLD_ORE, MANTLESTONE_GOLD_ORE);
            content.addAfter(Blocks.REDSTONE_ORE, MANTLESTONE_REDSTONE_ORE);
            content.addAfter(Blocks.EMERALD_ORE, MANTLESTONE_EMERALD_ORE);
            content.addAfter(Blocks.LAPIS_ORE, MANTLESTONE_LAPIS_ORE);
            content.addAfter(Blocks.DIAMOND_ORE, MANTLESTONE_DIAMOND_ORE);
        });
    }
}