package org.btwr.stratified.strata;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Property;
import org.btwr.stratified.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Which block a natural vanilla block becomes in each strata, and back.
 * <p>Only stratified's own blocks are ever reverted on save. Strata 3 maps to vanilla deepslate,
 * which is safe to keep in the save as-is.
 */
public final class StrataMappings {
    /** vanilla block -> block per strata index (null = unchanged). */
    private static final Map<Block, Block[]> FORWARD = new IdentityHashMap<>();
    /** stratified block -> vanilla block it was converted from, and the strata it lives in. */
    private static final Map<Block, Reverse> REVERSE = new IdentityHashMap<>();

    private record Reverse(Block vanilla, int strata) {}

    static {
        add(Blocks.STONE, ModBlocks.MANTLESTONE, Blocks.DEEPSLATE);
        add(Blocks.COAL_ORE, ModBlocks.MANTLESTONE_COAL_ORE, Blocks.DEEPSLATE_COAL_ORE);
        add(Blocks.COPPER_ORE, ModBlocks.MANTLESTONE_COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE);
        add(Blocks.IRON_ORE, ModBlocks.MANTLESTONE_IRON_ORE, Blocks.DEEPSLATE_IRON_ORE);
        add(Blocks.GOLD_ORE, ModBlocks.MANTLESTONE_GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE);
        add(Blocks.LAPIS_ORE, ModBlocks.MANTLESTONE_LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE);
        add(Blocks.REDSTONE_ORE, ModBlocks.MANTLESTONE_REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE);
        add(Blocks.DIAMOND_ORE, ModBlocks.MANTLESTONE_DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE);
        add(Blocks.EMERALD_ORE, ModBlocks.MANTLESTONE_EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE);
    }

    private static void add(Block vanilla, Block strata2, Block strata3) {
        FORWARD.put(vanilla, new Block[]{null, strata2, strata3});
        REVERSE.put(strata2, new Reverse(vanilla, StrataLayout.STRATA_2));
    }

    private StrataMappings() {}

    public static boolean isConvertible(BlockState state) {
        return FORWARD.containsKey(state.getBlock());
    }

    public static boolean isRevertible(BlockState state) {
        return REVERSE.containsKey(state.getBlock());
    }

    /** The state {@code state} should be at this position, or null if it stays as it is. */
    @Nullable
    public static BlockState toStrata(BlockState state, int x, int y, int z) {
        Block[] targets = FORWARD.get(state.getBlock());
        if (targets == null) return null;

        Block target = targets[StrataLayout.getStrata(x, y, z)];
        return target == null ? null : copyProperties(state, target.getDefaultState());
    }

    /** The vanilla state to save in place of {@code state}, or null if it should be saved as it is. */
    @Nullable
    public static BlockState toVanilla(BlockState state, int x, int y, int z) {
        Reverse reverse = REVERSE.get(state.getBlock());
        if (reverse == null || StrataLayout.getStrata(x, y, z) != reverse.strata()) return null;

        return copyProperties(state, reverse.vanilla().getDefaultState());
    }

    private static BlockState copyProperties(BlockState from, BlockState to) {
        for (Property<?> property : from.getProperties()) {
            if (to.contains(property)) {
                to = copy(from, to, property);
            }
        }
        return to;
    }

    private static <T extends Comparable<T>> BlockState copy(BlockState from, BlockState to, Property<T> property) {
        return to.with(property, from.get(property));
    }
}