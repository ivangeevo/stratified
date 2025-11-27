package org.btwr.stratified.datagen;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import org.btwr.stratified.block.ModBlocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    //private static final ImmutableList<ItemConvertible> COAL_ORES = ImmutableList.of(ModBlocks.MANTLESTONE_COAL_ORE);
    //private static final ImmutableList<ItemConvertible> IRON_ORES = ImmutableList.of(ModBlocks.MANTLESTONE_IRON_ORE);
    //private static final ImmutableList<ItemConvertible> COPPER_ORES = ImmutableList.of(ModBlocks.MANTLESTONE_COPPER_ORE);
    //private static final ImmutableList<ItemConvertible> GOLD_ORES = ImmutableList.of(ModBlocks.MANTLESTONE_GOLD_ORE);
    //private static final ImmutableList<ItemConvertible> DIAMOND_ORES = ImmutableList.of(ModBlocks.MANTLESTONE_DIAMOND_ORE);
    //private static final ImmutableList<ItemConvertible> LAPIS_ORES = ImmutableList.of(ModBlocks.MANTLESTONE_LAPIS_ORE);
    //private static final ImmutableList<ItemConvertible> REDSTONE_ORES = ImmutableList.of(ModBlocks.MANTLESTONE_REDSTONE_ORE);
    //private static final ImmutableList<ItemConvertible> EMERALD_ORES = ImmutableList.of(ModBlocks.MANTLESTONE_EMERALD_ORE);

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        //offerSmelting(exporter, COAL_ORES, RecipeCategory.MISC, Items.COAL, 0.1F, 200, "coal");
        //offerSmelting(exporter, IRON_ORES, RecipeCategory.MISC, Items.IRON_INGOT, 0.7F, 200, "iron_ingot");
        //offerSmelting(exporter, COPPER_ORES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7F, 200, "copper_ingot");
        //offerSmelting(exporter, GOLD_ORES, RecipeCategory.MISC, Items.GOLD_INGOT, 1.0F, 200, "gold_ingot");
        //offerSmelting(exporter, DIAMOND_ORES, RecipeCategory.MISC, Items.DIAMOND, 1.0F, 200, "diamond");
        //offerSmelting(exporter, LAPIS_ORES, RecipeCategory.MISC, Items.LAPIS_LAZULI, 0.2F, 200, "lapis_lazuli");
        //offerSmelting(exporter, REDSTONE_ORES, RecipeCategory.REDSTONE, Items.REDSTONE, 0.7F, 200, "redstone");
        //offerSmelting(exporter, EMERALD_ORES, RecipeCategory.MISC, Items.EMERALD, 1.0F, 200, "emerald");

        //offerBlasting(exporter, COAL_ORES, RecipeCategory.MISC, Items.COAL, 0.1F, 100, "coal");
        //offerBlasting(exporter, IRON_ORES, RecipeCategory.MISC, Items.IRON_INGOT, 0.7F, 100, "iron_ingot");
        //offerBlasting(exporter, COPPER_ORES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7F, 100, "copper_ingot");
        //offerBlasting(exporter, GOLD_ORES, RecipeCategory.MISC, Items.GOLD_INGOT, 1.0F, 100, "gold_ingot");
        //offerBlasting(exporter, DIAMOND_ORES, RecipeCategory.MISC, Items.DIAMOND, 1.0F, 100, "diamond");
        //offerBlasting(exporter, LAPIS_ORES, RecipeCategory.MISC, Items.LAPIS_LAZULI, 0.2F, 100, "lapis_lazuli");
        //offerBlasting(exporter, REDSTONE_ORES, RecipeCategory.REDSTONE, Items.REDSTONE, 0.7F, 100, "redstone");
        //offerBlasting(exporter, EMERALD_ORES, RecipeCategory.MISC, Items.EMERALD, 1.0F, 100, "emerald");
    }
}