package team.hollow.neutronia.init;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.village.TradeOffers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class TradeBuilder {
    private static final Logger LOGGER = LogManager.getLogger();

    static void createRecipes() {

        /*TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(NVillagers.ARTIST, copyToFastUtilMap(
            ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                    new BuyItemFactory(Items.RED_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.GREEN_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.PURPLE_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.CYAN_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.LIGHT_GRAY_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.GRAY_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.PINK_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.LIME_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.YELLOW_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.LIGHT_BLUE_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.MAGENTA_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.ORANGE_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.BLUE_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.BROWN_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.BLACK_DYE, 18, 22, 2),
                    new BuyItemFactory(Items.WHITE_DYE, 18, 22, 2),
                },
                2, new TradeOffers.Factory[]{
                    new SellItemFactory(Items.WHITE_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.BLUE_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.LIGHT_BLUE_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.RED_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.PINK_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.GREEN_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.LIME_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.GRAY_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.BLACK_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.PURPLE_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.MAGENTA_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.CYAN_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.BROWN_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.YELLOW_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.ORANGE_BANNER, 3, 1, 15),
                    new SellItemFactory(Items.LIGHT_GRAY_BANNER, 3, 1, 15)
                }
            )
        ));

        TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(VillagerProfession.CARTOGRAPHER, copyToFastUtilMap(
            ImmutableMap.of(1,
                new TradeOffers.Factory[]{
                        new BuyForOneEmeraldFactory(Items.PAPER, 24, 8, 2),
                        new SellItemFactory(Items.MAP, 7, 1, 1)
                },
                2, new TradeOffers.Factory[]{
                        new BuyForOneEmeraldFactory(Items.GLASS_PANE, 10, 8, 10),
                        new SellMapFactory(13, "Monument", MapIcon.Type.MONUMENT, 6, 5)
                },
                3, new TradeOffers.Factory[]{
                        new BuyForOneEmeraldFactory(Items.COMPASS, 1, 6, 20),
                        new SellMapFactory(14, "Mansion", MapIcon.Type.MANSION, 6, 10)
                },
                4, new TradeOffers.Factory[]{
                        new SellItemFactory(Items.ITEM_FRAME, 7, 1, 15),
                        new SellItemFactory(Items.WHITE_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.BLUE_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.LIGHT_BLUE_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.RED_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.PINK_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.GREEN_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.LIME_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.GRAY_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.BLACK_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.PURPLE_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.MAGENTA_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.CYAN_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.BROWN_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.YELLOW_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.ORANGE_BANNER, 3, 1, 15),
                        new SellItemFactory(Items.LIGHT_GRAY_BANNER, 3, 1, 15)
                },
                5, new TradeOffers.Factory[]{
                        new SellItemFactory(Items.GLOBE_BANNER_PATTERN, 8, 1, 30)
                }
            )
        ));

        TradeOffers.PROFESSION_TO_LEVELED_TRADE.get(VillagerProfession.FISHERMAN).merge(1, new TradeOffers.Factory[]{
            new TypeAwareBuyItemFactory(ImmutableMap.of(VillagerType.SNOW, Items.SNOW, VillagerType.DESERT, Items.BONE), 10, 22, 2)
        }, ArrayUtils::addAll);

        /*TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(NVillagers.RECEPTIONIST, copyToFastUtilMap(ImmutableMap.of(1, new TradeOffers.Factory[]{
                new BuyItemFactory(Items.BOOK, 18, 22, 2)
        })));*/

        /*TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(CARPENTER, copyToFastUtilMap(ImmutableMap.of(1, new TradeOffers.Factory[]{
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

    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> immutableMap_1) {
        return new Int2ObjectOpenHashMap<>(immutableMap_1);
    }

}