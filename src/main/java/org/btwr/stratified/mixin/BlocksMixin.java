package org.btwr.stratified.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Blocks.class)
public abstract class BlocksMixin {

    //@ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/PillarBlock;<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V", ordinal = 25))
    private static AbstractBlock.Settings stratified$init(AbstractBlock.Settings settings) {
        return settings.strength(20);
    }

    //@ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/bwt/blocks/unfired_pottery/UnfiredUrnBlock;<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V"))
    private static AbstractBlock.Settings stratified$init1(AbstractBlock.Settings settings) {
        return settings.solid();
    }

}
