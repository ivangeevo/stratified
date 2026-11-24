package org.btwr.stratified.debug;

import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;

/**
 * Dev-only x-ray for checking bedrock sheet generation: hides most natural terrain so only bedrock,
 * fluids, structures, features and the like are drawn, at full brightness. Toggle with {@code /stratified_xray}.
 * <p>Clip into the ground in spectator mode so chunk culling turns off and everything renders.
 */
public final class StrataXray {
    private static boolean enabled = false;

    private StrataXray() {}

    public static boolean isEnabled() {
        return enabled;
    }

    public static boolean isHidden(BlockState state) {
        return state.isIn(BlockTags.BASE_STONE_OVERWORLD)
                || state.isIn(ConventionalBlockTags.ORES)
                || state.isOf(Blocks.DIRT)
                || state.isOf(Blocks.GRAVEL)
                || state.isOf(Blocks.CLAY);
    }

    public static void register() {
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) return;

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(ClientCommandManager.literal("stratified_xray").executes(context -> {
                    enabled = !enabled;
                    MinecraftClient.getInstance().worldRenderer.reload();
                    context.getSource().sendFeedback(Text.literal("Strata x-ray " + (enabled ? "on" : "off")));
                    return Command.SINGLE_SUCCESS;
                }))
        );
    }
}