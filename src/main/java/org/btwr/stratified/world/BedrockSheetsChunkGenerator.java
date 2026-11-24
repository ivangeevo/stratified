package org.btwr.stratified.world;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.btwr.stratified.StratifiedMod;
import org.btwr.stratified.strata.bedrock_sheets.BedrockSheets;

/**
 * The normal overworld generator plus strata 4 bedrock sheets, used by the "Bedrock Sheets" world type
 * ({@code data/stratified/worldgen/world_preset/bedrock_sheets.json}).
 */
public class BedrockSheetsChunkGenerator extends NoiseChunkGenerator {
    public static final MapCodec<BedrockSheetsChunkGenerator> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource),
                    ChunkGeneratorSettings.REGISTRY_CODEC.fieldOf("settings").forGetter(NoiseChunkGenerator::getSettings)
            ).apply(instance, instance.stable(BedrockSheetsChunkGenerator::new))
    );

    public BedrockSheetsChunkGenerator(BiomeSource biomeSource, RegistryEntry<ChunkGeneratorSettings> settings) {
        super(biomeSource, settings);
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    /** Sheets go in once structures and features are placed, so structure pieces can be skipped, and before lighting. */
    @Override
    public void generateFeatures(StructureWorldAccess world, Chunk chunk, StructureAccessor structureAccessor) {
        super.generateFeatures(world, chunk, structureAccessor);
        BedrockSheets.generate(chunk, world.getSeed(), structureAccessor);
    }

    public static void register() {
        Registry.register(Registries.CHUNK_GENERATOR, Identifier.of(StratifiedMod.MOD_ID, "bedrock_sheets"), CODEC);
    }
}