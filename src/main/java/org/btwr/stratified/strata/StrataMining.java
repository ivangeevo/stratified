package org.btwr.stratified.strata;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import org.btwr.stratified.tag.ModTags;

/**
 * BTW's strata mining speed, applied the same way as Tough Environment's MiningEfficiency but only to strata stone.
 * <p>Skipped when Tough Environment is installed, since it applies BTW's speeds to every block itself.
 */
public final class StrataMining {
    private static final boolean TOUGH_ENVIRONMENT_LOADED = FabricLoader.getInstance().isModLoaded("tough_environment");

    /** Divisor for a tool that isn't efficient on the block (vanilla: 30 when it can harvest, 100 if not) **/
    public static final int INEFFICIENT_DIVISOR = 200;

    private StrataMining() {}

    public static boolean isActive(PlayerEntity player) {
        return !TOUGH_ENVIRONMENT_LOADED && !player.isCreative();
    }

    /**
     * Strata stone mined with anything that isn't efficient on it: a pickaxe below the
     * strata's level, but also a bare hand or the wrong tool, which BTW slows the same way
     */
    public static boolean isInefficient(ItemStack stack, BlockState state) {
        int requiredLevel = getRequiredToolLevel(state);

        if (requiredLevel == 0) {
            return false;
        }

        boolean rightToolType = stack.getItem() instanceof MiningToolItem && stack.getMiningSpeedMultiplier(state) > 1.0F;

        return !rightToolType || getToolLevel(stack) < requiredLevel;
    }

    private static int getRequiredToolLevel(BlockState state) {
        if (state.isIn(ModTags.Blocks.STONE_STRATA3)) {
            return 3;
        }

        if (state.isIn(ModTags.Blocks.STONE_STRATA2)) {
            return 2;
        }

        return 0;
    }

    private static int getToolLevel(ItemStack stack) {
        if (stack.getItem() instanceof ToolItem toolItem && toolItem.getMaterial() instanceof ToolMaterials vanilla) {
            return switch (vanilla) {
                case WOOD, GOLD -> 0;
                case STONE -> 1;
                case IRON -> 2;
                case DIAMOND -> 3;
                case NETHERITE -> 4;
            };
        }

        // Other mods' tools: go by their tier tags
        if (stack.isIn(ModTags.Items.ADVANCED_PICKAXES)) {
            return 3;
        }

        if (stack.isIn(ModTags.Items.MODERN_PICKAXES)) {
            return 2;
        }

        return 1;
    }
}