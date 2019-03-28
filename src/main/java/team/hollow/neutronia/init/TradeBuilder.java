package team.hollow.neutronia.init;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.village.TraderRecipe;
import net.minecraft.village.Trades;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

class TradeBuilder {
    private static final Logger LOGGER = LogManager.getLogger();

    static void createRecipes() {
        BuyItemFactory WOOL = null;
        BuyItemFactory BANNERS = null;
        BuyItemFactory BEDS = null;
        BuyItemFactory CARPETS = null;

        for (Block item : BlockTags.WOOL.values()) {
            WOOL = new BuyItemFactory(item, 18, 22, 2);
        }
        for (Block item : BlockTags.BANNERS.values()) {
            BANNERS = new BuyItemFactory(item, 18, 22, 2);
        }
        for (Block item : BlockTags.BEDS.values()) {
            BEDS = new BuyItemFactory(item, 18, 22, 2);
        }
        for (Block item : BlockTags.CARPETS.values()) {
            CARPETS = new BuyItemFactory(item, 18, 22, 2);
        }

        /*Trades.PROFESSION_TO_LEVELED_TRADE.put(ARTIST, copyToFastUtilMap(ImmutableMap.of(1, new Trades.Factory[]{
                new BuyItemFactory(new ItemStack(Items.RED_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.GREEN_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.PURPLE_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.CYAN_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.LIGHT_GRAY_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.GRAY_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.PINK_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.LIME_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.YELLOW_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.LIGHT_BLUE_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.MAGENTA_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.ORANGE_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.BLUE_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.BROWN_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.BLACK_DYE).getItem(), 18, 22, 2),
                new BuyItemFactory(new ItemStack(Items.WHITE_DYE).getItem(), 18, 22, 2),
                WOOL, BANNERS, BEDS, CARPETS
        })));

        Trades.PROFESSION_TO_LEVELED_TRADE.put(RECEPTIONIST, copyToFastUtilMap(ImmutableMap.of(1, new Trades.Factory[]{
                new BuyItemFactory(Items.BOOK, 18, 22, 2)
        })));

        Trades.PROFESSION_TO_LEVELED_TRADE.put(CARPENTER, copyToFastUtilMap(ImmutableMap.of(1, new Trades.Factory[]{
                new BuyItemFactory(Blocks.ACACIA_PLANKS, 18, 22, 2),
                new BuyItemFactory(Blocks.BIRCH_PLANKS, 18, 22, 2),
                new BuyItemFactory(Blocks.DARK_OAK_PLANKS, 18, 22, 2),
                new BuyItemFactory(Blocks.JUNGLE_PLANKS, 18, 22, 2),
                new BuyItemFactory(Blocks.OAK_PLANKS, 18, 22, 2),
                new BuyItemFactory(Blocks.SPRUCE_PLANKS, 18, 22, 2),

                new BuyItemFactory(Blocks.ACACIA_STAIRS, 18, 22, 2),
                new BuyItemFactory(Blocks.BIRCH_STAIRS, 18, 22, 2),
                new BuyItemFactory(Blocks.DARK_OAK_STAIRS, 18, 22, 2),
                new BuyItemFactory(Blocks.JUNGLE_STAIRS, 18, 22, 2),
                new BuyItemFactory(Blocks.OAK_STAIRS, 18, 22, 2),
                new BuyItemFactory(Blocks.SPRUCE_STAIRS, 18, 22, 2),

                new BuyItemFactory(Blocks.ACACIA_SLAB, 18, 22, 2),
                new BuyItemFactory(Blocks.BIRCH_SLAB, 18, 22, 2),
                new BuyItemFactory(Blocks.DARK_OAK_SLAB, 18, 22, 2),
                new BuyItemFactory(Blocks.JUNGLE_SLAB, 18, 22, 2),
                new BuyItemFactory(Blocks.OAK_SLAB, 18, 22, 2),
                new BuyItemFactory(Blocks.SPRUCE_SLAB, 18, 22, 2),

                new BuyItemFactory(Blocks.ACACIA_LOG, 18, 22, 2),
                new BuyItemFactory(Blocks.BIRCH_LOG, 18, 22, 2),
                new BuyItemFactory(Blocks.DARK_OAK_LOG, 18, 22, 2),
                new BuyItemFactory(Blocks.JUNGLE_LOG, 18, 22, 2),
                new BuyItemFactory(Blocks.OAK_LOG, 18, 22, 2),
                new BuyItemFactory(Blocks.SPRUCE_LOG, 18, 22, 2)
        })));*/
    }

    private static Int2ObjectMap<Trades.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, Trades.Factory[]> immutableMap_1) {
        return new Int2ObjectOpenHashMap<>(immutableMap_1);
    }

    static class PriceRange {
        final int lower;
        final int range;

        PriceRange(int int_1, int int_2) {
            this.lower = int_1;
            this.range = 1 + Math.max(0, int_2 - int_1);
            if (int_2 < int_1) {
                LOGGER.warn("PriceRange({}, {}) invalid, {} smaller than {}", int_1, int_2, int_2, int_1);
            }

        }

        int getPrice(Random random_1) {
            return this.lower + random_1.nextInt(this.range);
        }
    }

    static class BuyItemFactory implements Trades.Factory {

        private final Item field_18548;
        private final int field_18549;
        private final int field_18550;
        private final int field_18551;
        private final float field_18552;

        public BuyItemFactory(ItemProvider itemProvider_1, int int_1, int int_2, int int_3) {
            this.field_18548 = itemProvider_1.getItem();
            this.field_18549 = int_1;
            this.field_18550 = int_2;
            this.field_18551 = int_3;
            this.field_18552 = 0.05F;
        }

        @Override
        public TraderRecipe create(Entity var1, Random var2) {
            ItemStack itemStack_1 = new ItemStack(this.field_18548, this.field_18549);
            return new TraderRecipe(itemStack_1, new ItemStack(Items.EMERALD), this.field_18550, this.field_18551, this.field_18552);
        }


    }
}