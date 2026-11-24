package org.btwr.stratified.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import org.btwr.stratified.strata.StrataMining;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    /**
     * BTW: a pickaxe below the strata's level mines strata stone at speed 1, like any tool that isn't efficient.
     * Efficiency only applies above 1, so it's lost too; Haste, water and air still apply as in BTW.
     */
    @ModifyExpressionValue(method = "getBlockBreakingSpeed",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F"))
    private float stratified$strataToolSpeed(float speed, BlockState state) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (StrataMining.isActive(player) && StrataMining.isInefficient(player.getMainHandStack(), state)) {
            return 1.0F;
        }
        return speed;
    }
}
