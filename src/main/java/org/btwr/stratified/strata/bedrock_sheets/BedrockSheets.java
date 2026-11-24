package org.btwr.stratified.strata.bedrock_sheets;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Optional strata 4 challenge, only in the "Bedrock Sheets" world type ({@link org.btwr.stratified.world.BedrockSheetsChunkGenerator}):
 * wide, wavy sheets of bedrock below the diamond cap, inspired by MiTE's underworld bedrock strata.
 * <p>Each sheet uses a low-frequency coverage noise: where it's above the sheet's threshold the sheet exists,
 * thickening away from its edges. A higher-frequency bump noise shifts its base up and down so it isn't flat.
 * Below the threshold are the gaps players have to find.
 * <ul>
 *     <li>The upper sheets only replace solid terrain, so caves still lead down through them,
 *     but digging down means mining around them.</li>
 *     <li>The floor sheet is far larger and also fills caves, so the deepest part of the world can only be
 *     reached by mining strata 3 stone around it, which takes an advanced pickaxe.</li>
 * </ul>
 * Structures are never cut through.
 */
public final class BedrockSheets {
    private static final Sheet[] SHEETS = {
            // Upper sheets, kept so the thickest, highest one tops out at StrataLayout.STRATA_4_TOP
            new Sheet(8, 96.0, -0.15, 2, 6, 6.0, false),
            new Sheet(-16, 96.0, -0.15, 2, 6, 6.0, false),
            new Sheet(-40, 96.0, -0.15, 2, 6, 6.0, false),
            // Floor sheet: about 90% coverage, with rare gaps far apart
            new Sheet(-50, 256.0, -0.7, 3, 7, 4.0, true),
    };

    private static final double BUMP_SCALE = 16.0;
    private static final int MAX_BUMP = 2;
    /** Extra space kept clear around structure pieces. */
    private static final int STRUCTURE_MARGIN = 2;

    private static final BlockState BEDROCK = Blocks.BEDROCK.getDefaultState();

    private record Samplers(long seed, SimplexNoiseSampler[] coverage, SimplexNoiseSampler[] bump) {}

    private static volatile Samplers samplers;

    private BedrockSheets() {}

    /** Places the sheets in a chunk whose structures and features have already generated. */
    public static void generate(Chunk chunk, long worldSeed, StructureAccessor structureAccessor) {
        Samplers noise = getSamplers(worldSeed);
        ChunkPos chunkPos = chunk.getPos();
        List<BlockBox> structureBoxes = getStructureBoxes(chunkPos, structureAccessor);
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int index = 0; index < SHEETS.length; index++) {
            Sheet sheet = SHEETS[index];

            for (int localX = 0; localX < 16; localX++) {
                for (int localZ = 0; localZ < 16; localZ++) {
                    int x = chunkPos.getStartX() + localX;
                    int z = chunkPos.getStartZ() + localZ;

                    double coverage = noise.coverage()[index].sample(x / sheet.coverageScale(), z / sheet.coverageScale());
                    if (coverage <= sheet.coverageThreshold()) continue;

                    int thickness = Math.min(sheet.maxThickness(), sheet.minThickness() + (int) ((coverage - sheet.coverageThreshold()) * sheet.thicknessPerNoise()));
                    int bump = (int) Math.round(noise.bump()[index].sample(x / BUMP_SCALE, z / BUMP_SCALE) * MAX_BUMP);
                    int bottomY = sheet.baseY() + bump;

                    for (int y = bottomY; y < bottomY + thickness; y++) {
                        if (chunk.isOutOfHeightLimit(y)) continue;

                        pos.set(x, y, z);

                        if (canReplace(chunk.getBlockState(pos), sheet.fillsCaves()) && !isInAny(structureBoxes, pos)) {
                            chunk.setBlockState(pos, BEDROCK, false);
                        }
                    }
                }
            }
        }
    }

    /** Natural terrain, plus cave air and fluids when the sheet fills caves. */
    private static boolean canReplace(BlockState state, boolean fillsCaves) {
        if (state.isAir() || state.isOf(Blocks.WATER) || state.isOf(Blocks.LAVA)) return fillsCaves;

        return state.isIn(BlockTags.BASE_STONE_OVERWORLD)
                || state.isIn(ConventionalBlockTags.ORES)
                || state.isOf(Blocks.GRAVEL)
                || state.isOf(Blocks.DIRT)
                || state.isOf(Blocks.CLAY);
    }

    private static List<BlockBox> getStructureBoxes(ChunkPos chunkPos, StructureAccessor structureAccessor) {
        List<BlockBox> boxes = new ArrayList<>();
        for (StructureStart start : structureAccessor.getStructureStarts(chunkPos, structure -> true)) {
            for (StructurePiece piece : start.getChildren()) {
                BlockBox box = piece.getBoundingBox().expand(STRUCTURE_MARGIN);
                if (box.intersectsXZ(chunkPos.getStartX(), chunkPos.getStartZ(), chunkPos.getEndX(), chunkPos.getEndZ())) {
                    boxes.add(box);
                }
            }
        }

        return boxes;
    }

    private static boolean isInAny(List<BlockBox> boxes, BlockPos pos) {
        for (BlockBox box : boxes) {
            if (box.contains(pos)) return true;
        }
        return false;
    }

    private static Samplers getSamplers(long worldSeed) {
        Samplers current = samplers;
        if (current != null && current.seed() == worldSeed) return current;

        SimplexNoiseSampler[] coverage = new SimplexNoiseSampler[SHEETS.length];
        SimplexNoiseSampler[] bump = new SimplexNoiseSampler[SHEETS.length];

        for (int index = 0; index < SHEETS.length; index++) {
            coverage[index] = new SimplexNoiseSampler(new CheckedRandom(worldSeed + 0x5EEDL * (index + 1)));
            bump[index] = new SimplexNoiseSampler(new CheckedRandom(worldSeed - 0xB0B5L * (index + 1)));
        }

        current = new Samplers(worldSeed, coverage, bump);
        samplers = current;

        return current;
    }
}