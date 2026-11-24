package org.btwr.stratified.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.ChunkSerializer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.PalettedContainer;
import org.btwr.stratified.strata.StrataConverter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Writes vanilla blocks to disk in place of stratified ones, so worlds stay clean if the mod is removed.
 */
@Mixin(ChunkSerializer.class)
public abstract class ChunkSerializerMixin {
    @ModifyExpressionValue(
            method = "serialize",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/ChunkSection;getBlockStateContainer()Lnet/minecraft/world/chunk/PalettedContainer;")
    )
    private static PalettedContainer<BlockState> stratified$revertStrataBlocks(
            PalettedContainer<BlockState> container,
            @Local(argsOnly = true) ServerWorld world,
            @Local(argsOnly = true) Chunk chunk,
            @Local ChunkSection section
    ) {
        if (world.getRegistryKey() != World.OVERWORLD) return container;

        ChunkSection[] sections = chunk.getSectionArray();
        for (int index = 0; index < sections.length; index++) {
            if (sections[index] == section) {
                int bottomY = ChunkSectionPos.getBlockCoord(chunk.sectionIndexToCoord(index));
                return StrataConverter.revertForSave(container, chunk.getPos(), bottomY);
            }
        }
        return container;
    }
}