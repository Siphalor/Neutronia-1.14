package team.hollow.neutronia.init;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.village.Trader;
import net.minecraft.village.TraderRecipe;
import net.minecraft.village.Trades;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

import static team.hollow.neutronia.init.NVillagers.*;

class TradeBuilder {
    private static final Logger LOGGER = LogManager.getLogger();

    static void createRecipes() {
        BuyItemFactory WOOL = null;
        BuyItemFactory BANNERS = null;
        BuyItemFactory BEDS = null;
        BuyItemFactory CARPETS = null;

        for (Block item : BlockTags.WOOL.values()) {
            WOOL = new BuyItemFactory(item, new PriceRange(18, 22));
        }
        for (Block item : BlockTags.BANNERS.values()) {
            BANNERS = new BuyItemFactory(item, new PriceRange(18, 22));
        }
        for (Block item : BlockTags.BEDS.values()) {
            BEDS = new BuyItemFactory(item, new PriceRange(18, 22));
        }
        for (Block item : BlockTags.CARPETS.values()) {
            CARPETS = new BuyItemFactory(item, new PriceRange(18, 22));
        }

        Trades.PROFESSION_TO_LEVELED_TRADE.put(ARTIST, copyToFastUtilMap(ImmutableMap.of(1, new Trades.Factory[]{
                new BuyItemFactory(new ItemStack(Items.RED_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.GREEN_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.PURPLE_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.CYAN_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.LIGHT_GRAY_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.GRAY_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.PINK_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.LIME_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.YELLOW_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.LIGHT_BLUE_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.MAGENTA_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.ORANGE_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.BLUE_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.BROWN_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.BLACK_DYE), new PriceRange(18, 22)),
                new BuyItemFactory(new ItemStack(Items.WHITE_DYE), new PriceRange(18, 22)),
                WOOL, BANNERS, BEDS, CARPETS
        })));

        Trades.PROFESSION_TO_LEVELED_TRADE.put(RECEPTIONIST, copyToFastUtilMap(ImmutableMap.of(1, new Trades.Factory[]{
                new BuyItemFactory(Items.BOOK, new PriceRange(18, 22))
        })));

        Trades.PROFESSION_TO_LEVELED_TRADE.put(CARPENTER, copyToFastUtilMap(ImmutableMap.of(1, new Trades.Factory[]{
                new BuyItemFactory(Blocks.ACACIA_PLANKS, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.BIRCH_PLANKS, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.DARK_OAK_PLANKS, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.JUNGLE_PLANKS, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.OAK_PLANKS, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.SPRUCE_PLANKS, new PriceRange(18, 22)),

                new BuyItemFactory(Blocks.ACACIA_STAIRS, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.BIRCH_STAIRS, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.DARK_OAK_STAIRS, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.JUNGLE_STAIRS, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.OAK_STAIRS, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.SPRUCE_STAIRS, new PriceRange(18, 22)),

                new BuyItemFactory(Blocks.ACACIA_SLAB, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.BIRCH_SLAB, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.DARK_OAK_SLAB, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.JUNGLE_SLAB, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.OAK_SLAB, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.SPRUCE_SLAB, new PriceRange(18, 22)),

                new BuyItemFactory(Blocks.ACACIA_LOG, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.BIRCH_LOG, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.DARK_OAK_LOG, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.JUNGLE_LOG, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.OAK_LOG, new PriceRange(18, 22)),
                new BuyItemFactory(Blocks.SPRUCE_LOG, new PriceRange(18, 22))
        })));
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
        Item bought;
        PriceRange range;

        BuyItemFactory(ItemProvider itemProvider_1, PriceRange villagerTrades$PriceRange_1) {
            this.bought = itemProvider_1.getItem();
            this.range = villagerTrades$PriceRange_1;
        }

        BuyItemFactory(ItemStack itemProvider_1, PriceRange villagerTrades$PriceRange_1) {
            this.bought = itemProvider_1.getItem();
            this.range = villagerTrades$PriceRange_1;
        }

        public TraderRecipe create(Trader villager_1, Random random_1) {
            return new TraderRecipe(new ItemStack(Items.EMERALD, this.range == null ? 1 : this.range.getPrice(random_1)), new ItemStack(this.bought));
        }
    }
}