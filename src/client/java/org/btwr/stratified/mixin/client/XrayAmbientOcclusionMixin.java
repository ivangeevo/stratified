package org.btwr.stratified.mixin.client;

import net.minecraft.block.AbstractBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.btwr.stratified.debug.StrataXray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** No ambient occlusion shading while x-ray is on, or faces against hidden terrain render nearly black. */
@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class XrayAmbientOcclusionMixin {

    @Inject(method = "getAmbientOcclusionLightLevel", at = @At("HEAD"), cancellable = true)
    private void stratified$noAmbientOcclusion(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (StrataXray.isEnabled()) {
            cir.setReturnValue(1.0f);
        }
    }
}