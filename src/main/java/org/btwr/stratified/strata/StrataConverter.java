package org.btwr.stratified.strata;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.PalettedContainer;
import net.minecraft.world.chunk.WorldChunk;

/**
 * Swaps natural vanilla blocks to their strata variants in loaded chunks, and back again in saved data.
 */
public final class StrataConverter {
    private StrataConverter() {}

    /** Converts every natural vanilla block in the chunk to its strata variant, leaving placed ones alone. */
    public static void convertChunk(WorldChunk chunk) {
        ChunkPos chunkPos = chunk.getPos();
        ChunkSection[] sections = chunk.getSectionArray();
        Int2ObjectMap<long[]> placed = chunk.getAttached(PlacedBlocks.TYPE);

        for (int index = 0; index < sections.length; index++) {
            int sectionY = chunk.sectionIndexToCoord(index);
            int bottomY = ChunkSectionPos.getBlockCoord(sectionY);
            if (bottomY > StrataLayout.MAX_STRATA_Y) break;

            ChunkSection section = sections[index];
            if (section.isEmpty() || !section.hasAny(StrataMappings::isConvertible)) continue;

            long[] placedInSection = PlacedBlocks.getSection(placed, sectionY);

            for (int y = 0; y < 16; y++) {
                for (int z = 0; z < 16; z++) {
                    for (int x = 0; x < 16; x++) {
                        int worldX = chunkPos.getStartX() + x;
                        int worldY = bottomY + y;
                        int worldZ = chunkPos.getStartZ() + z;
                        BlockState converted = StrataMappings.toStrata(section.getBlockState(x, y, z), worldX, worldY, worldZ);
                        if (converted != null && !PlacedBlocks.isPlaced(placedInSection, x, y, z)) {
                            section.setBlockState(x, y, z, converted, false);
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns a new container with stratified blocks reverted to vanilla,
     * or the container itself if nothing needs reverting.
     */
    public static PalettedContainer<BlockState> revertForSave(PalettedContainer<BlockState> container, ChunkPos chunkPos, int bottomY) {
        if (bottomY > StrataLayout.MAX_STRATA_Y || !container.hasAny(StrataMappings::isRevertible)) {
            return container;
        }

        // Built from scratch rather than with copy(): a copied palette still reports resizes to the
        // original container, so adding entries to it would resize the live chunk's data instead
        PalettedContainer<BlockState> reverted = new PalettedContainer<>(
                Block.STATE_IDS, Blocks.AIR.getDefaultState(), PalettedContainer.PaletteProvider.BLOCK_STATE
        );

        for (int y = 0; y < 16; y++) {
            for (int z = 0; z < 16; z++) {
                for (int x = 0; x < 16; x++) {
                    BlockState state = container.get(x, y, z);
                    BlockState vanilla = StrataMappings.toVanilla(
                            state,
                            chunkPos.getStartX() + x, bottomY + y, chunkPos.getStartZ() + z
                    );

                    reverted.swapUnsafe(x, y, z, vanilla != null ? vanilla : state);
                }
            }
        }

        return reverted;
    }
}