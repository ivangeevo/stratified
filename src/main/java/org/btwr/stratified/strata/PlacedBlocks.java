package org.btwr.stratified.strata;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.chunk.WorldChunk;
import org.btwr.stratified.StratifiedMod;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

/**
 * Remembers, per chunk, where a convertible vanilla block (stone, ores) was placed after the chunk loaded, so
 * converting the chunk on its next load leaves it as it was placed instead of turning it into its strata
 * variant. Saved alongside the chunk; a world without Stratified simply ignores it.
 * <p>Stored as one 4096-bit set (64 longs, 512 bytes) per 16x16x16 section that has any placed block, keyed by
 * the section's Y, so the size is bounded however much is placed and lookups are a single bit test.
 */
public final class PlacedBlocks {
    private static final int WORDS = 16 * 16 * 16 / Long.SIZE;

    private record SectionBits(int sectionY, long[] bits) {
        static final Codec<SectionBits> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("y").forGetter(SectionBits::sectionY),
                Codec.LONG_STREAM.xmap(LongStream::toArray, Arrays::stream).fieldOf("bits").forGetter(SectionBits::bits)
        ).apply(instance, SectionBits::new));
    }

    private static final Codec<Int2ObjectOpenHashMap<long[]>> SECTIONS_CODEC = SectionBits.CODEC.listOf().xmap(
            PlacedBlocks::fromSectionList, PlacedBlocks::toSectionList);

    /** Dev worlds saved by the first version, a plain list of block positions **/
    private static final Codec<Int2ObjectOpenHashMap<long[]>> LEGACY_CODEC = Codec.LONG.listOf().xmap(
            PlacedBlocks::fromPositions, map -> { throw new UnsupportedOperationException("legacy format is read-only"); });

    private static final Codec<Int2ObjectOpenHashMap<long[]>> CODEC = Codec.withAlternative(SECTIONS_CODEC, LEGACY_CODEC);

    public static final AttachmentType<Int2ObjectOpenHashMap<long[]>> TYPE = AttachmentRegistry.<Int2ObjectOpenHashMap<long[]>>builder()
            .persistent(CODEC)
            .initializer(Int2ObjectOpenHashMap::new)
            .buildAndRegister(Identifier.of(StratifiedMod.MOD_ID, "placed_blocks"));

    private PlacedBlocks() {}

    public static void register() {}

    /** Records or forgets a position as its block changes, for the positions conversion would touch **/
    public static void onBlockSet(WorldChunk chunk, BlockPos pos, BlockState state) {
        if (StrataLayout.getStrata(pos.getX(), pos.getY(), pos.getZ()) == StrataLayout.STRATA_1) {
            return;
        }

        int sectionY = ChunkSectionPos.getSectionCoord(pos.getY());
        int bit = index(pos.getX(), pos.getY(), pos.getZ());

        if (StrataMappings.isConvertible(state)) {
            chunk.getAttachedOrCreate(TYPE).computeIfAbsent(sectionY, key -> new long[WORDS])[bit >>> 6] |= 1L << bit;
            return;
        }

        Int2ObjectOpenHashMap<long[]> placed = chunk.getAttached(TYPE);
        if (placed == null) return;

        long[] bits = placed.get(sectionY);
        if (bits == null || (bits[bit >>> 6] & (1L << bit)) == 0) return;

        bits[bit >>> 6] &= ~(1L << bit);
        if (isEmpty(bits)) {
            placed.remove(sectionY);
            if (placed.isEmpty()) {
                chunk.removeAttached(TYPE);
            }
        }
    }

    /** The placed-block bits of the section at {@code sectionY}, or null if nothing was placed in it **/
    @Nullable
    public static long[] getSection(@Nullable Int2ObjectMap<long[]> placed, int sectionY) {
        return placed == null ? null : placed.get(sectionY);
    }

    /** Whether the block at section-local x/y/z was placed, given its section's bits from {@link #getSection} **/
    public static boolean isPlaced(@Nullable long[] sectionBits, int x, int y, int z) {
        if (sectionBits == null) return false;
        int bit = index(x, y, z);
        return (sectionBits[bit >>> 6] & (1L << bit)) != 0;
    }

    /** Section-local block index: y, z, x in the low 4 bits of each coordinate **/
    private static int index(int x, int y, int z) {
        return (y & 15) << 8 | (z & 15) << 4 | (x & 15);
    }

    private static boolean isEmpty(long[] bits) {
        for (long word : bits) {
            if (word != 0) return false;
        }
        return true;
    }

    private static Int2ObjectOpenHashMap<long[]> fromSectionList(List<SectionBits> list) {
        Int2ObjectOpenHashMap<long[]> map = new Int2ObjectOpenHashMap<>();
        for (SectionBits section : list) {
            if (section.bits().length == WORDS && !isEmpty(section.bits())) {
                map.put(section.sectionY(), section.bits().clone());
            }
        }
        return map;
    }

    private static List<SectionBits> toSectionList(Int2ObjectOpenHashMap<long[]> map) {
        List<SectionBits> list = new ArrayList<>(map.size());
        for (Int2ObjectMap.Entry<long[]> entry : map.int2ObjectEntrySet()) {
            list.add(new SectionBits(entry.getIntKey(), entry.getValue().clone()));
        }
        return list;
    }

    private static Int2ObjectOpenHashMap<long[]> fromPositions(List<Long> positions) {
        Int2ObjectOpenHashMap<long[]> map = new Int2ObjectOpenHashMap<>();
        for (long packed : positions) {
            int x = BlockPos.unpackLongX(packed), y = BlockPos.unpackLongY(packed), z = BlockPos.unpackLongZ(packed);
            int bit = index(x, y, z);
            map.computeIfAbsent(ChunkSectionPos.getSectionCoord(y), key -> new long[WORDS])[bit >>> 6] |= 1L << bit;
        }
        return map;
    }
}