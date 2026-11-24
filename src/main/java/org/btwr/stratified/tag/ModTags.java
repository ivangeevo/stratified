package org.btwr.stratified.tag;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * Same ids as BTWR Shared Library's conventional tags (filled by the shared library),
 * defined by id so the shared library's package layout doesn't matter.
 */
public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> STONE_STRATA2 = conventional("stone_strata2");
        public static final TagKey<Block> STONE_STRATA3 = conventional("stone_strata3");
        public static final TagKey<Block> ORE_STRATA2 = conventional("ore_strata2");

        private static TagKey<Block> conventional(String path) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", path));
        }
    }

    public static class Items {
        public static final TagKey<Item> PRIMITIVE_PICKAXES = conventional("primitive_pickaxes");
        public static final TagKey<Item> MODERN_PICKAXES = conventional("modern_pickaxes");
        public static final TagKey<Item> ADVANCED_PICKAXES = conventional("advanced_pickaxes");

        private static TagKey<Item> conventional(String path) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", path));
        }
    }
}