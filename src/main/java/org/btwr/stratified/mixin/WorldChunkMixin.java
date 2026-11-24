package org.btwr.stratified.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.btwr.stratified.strata.PlacedBlocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Blocks set after a chunk loads (by players, pistons, lava meeting water, commands, ...) stay what they are:
 * stone or ore placed in a lower strata is remembered so the next load doesn't convert it (see PlacedBlocks).
 * Only the chunk's natural blocks are converted, once, when it loads (StrataConverter).
 */
@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin {
    @Shadow @Final World world;

    @Inject(method = "setBlockState", at = @At("HEAD"))
    private void stratified$trackPlacedBlocks(BlockPos pos, BlockState state, boolean moved, CallbackInfoReturnable<BlockState> cir) {
        if (this.world.isClient() || this.world.getRegistryKey() != World.OVERWORLD) {
            return;
        }

        PlacedBlocks.onBlockSet((WorldChunk) (Object) this, pos, state);
    }
}