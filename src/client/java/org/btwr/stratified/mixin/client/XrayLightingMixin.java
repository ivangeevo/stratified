package org.btwr.stratified.mixin.client;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.btwr.stratified.debug.StrataXray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Full brightness while x-ray is on, since blocks buried in terrain have no light. */
@Mixin(WorldRenderer.class)
public abstract class XrayLightingMixin {
    @Inject(method = "getLightmapCoordinates(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)I", at = @At("HEAD"), cancellable = true)
    private static void stratified$fullBright(BlockRenderView world, BlockState state, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (StrataXray.isEnabled()) {
            cir.setReturnValue(LightmapTextureManager.MAX_LIGHT_COORDINATE);
        }
    }
}