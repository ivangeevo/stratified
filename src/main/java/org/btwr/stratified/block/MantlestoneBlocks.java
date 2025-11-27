package org.btwr.stratified.block;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.Identifier;

public class MantlestoneBlocks {
    public BlockSetType blockSetType;
    public BlockFamily blockFamily;

    public Block stoneBlock;
    public Block stoneStairsBlockStairs;
    public Block stoneSlabBlockSlab;
    public Block stonePressurePlateBlock;
    public Block stoneButtonBlock;

    public MantlestoneBlocks initialize() {
        blockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(Identifier.of("blood_wood"));

        stoneBlock = Blocks.createWoodenButtonBlock(blockSetType);
        stoneStairsBlockStairs = Blocks.createWoodenButtonBlock(blockSetType);
        stoneSlabBlockSlab = Blocks.createWoodenButtonBlock(blockSetType);
        stonePressurePlateBlock = Blocks.createWoodenButtonBlock(blockSetType);
        stoneButtonBlock = Blocks.createWoodenButtonBlock(blockSetType);

        blockFamily = BlockFamilies.register(stoneBlock)
                .button(stoneButtonBlock)
                .pressurePlate(stonePressurePlateBlock)
                .slab(stoneSlabBlockSlab)
                .stairs(stoneStairsBlockStairs)
                .group("stone")
                .unlockCriterionName("has_mantlestone")
                .build();
        return this;
    }

}
