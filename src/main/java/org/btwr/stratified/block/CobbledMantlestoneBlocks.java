package org.btwr.stratified.block;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.Identifier;

public class CobbledMantlestoneBlocks {
    public BlockSetType blockSetType;
    public BlockFamily blockFamily;

    public Block cobbleBlock;
    public Block cobbleStairsBlock;
    public Block cobbleSlabBlock;
    public Block cobbleWallBlock;

    public CobbledMantlestoneBlocks initialize() {
        blockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(Identifier.of("blood_wood"));

        cobbleBlock = Blocks.createWoodenButtonBlock(blockSetType);
        cobbleStairsBlock = Blocks.createWoodenButtonBlock(blockSetType);
        cobbleSlabBlock = Blocks.createWoodenButtonBlock(blockSetType);
        cobbleWallBlock = Blocks.createWoodenButtonBlock(blockSetType);

        blockFamily = BlockFamilies.register(cobbleBlock)
                .stairs(cobbleStairsBlock)
                .slab(cobbleSlabBlock)
                .wall(cobbleWallBlock)
                .group("stone")
                .unlockCriterionName("has_cobbled_mantlestone")
                .build();
        return this;
    }

}
