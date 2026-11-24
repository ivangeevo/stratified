package org.btwr.stratified.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.btwr.stratified.strata.StrataMining;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    /** Anything that isn't efficient on strata stone divides by 200, instead of vanilla's 30 (or 100 without harvesting) **/
    @ModifyVariable(method = "calcBlockBreakingDelta", at = @At("STORE"), ordinal = 0)
    private int stratified$strataHardnessDivisor(int divisor, BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        if (StrataMining.isActive(player) && StrataMining.isInefficient(player.getMainHandStack(), state)) {
            return StrataMining.INEFFICIENT_DIVISOR;
        }
        return divisor;
    }
}