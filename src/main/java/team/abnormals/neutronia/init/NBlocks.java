package team.abnormals.neutronia.init;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import team.abnormals.neutronia.blocks.*;
import team.abnormals.neutronia.enums.*;

public class NBlocks {

    public static Block[] BOOKSHELVES = new Block[9], PATTERNED_PLANKS = new Block[6], STRIPPED_LOGS = new Block[6],
            STRIPPED_WOOD = new Block[6], WOOD = new Block[6], CARVED_PLANKS = new Block[10], BARRELS = new Block[10],
            FLUID_BARRELS = new Block[10], WOOD_LANTERNS = new Block[10], LADDERS = new Block[9],
            GLAZED_TERRACOTTA_PILLAR = new Block[13], SOUL_STONE = new Block[4];
    public static BlockCustomChest[] WOODEN_CHESTS = new BlockCustomChest[CustomChestTypes.values().length];
    public static Block CARVED_MELON, MEL_O_LANTERN;
    public static Block PHANTOM_LANTERN, LIT_PHANTOM_LANTERN, phantomItemFrame;
    public static BlockNeutroniaDoor SANDSTONE_DOOR, RED_SANDSTONE_DOOR, ICE_DOOR, BAMBOO_DOOR;
    public static BlockNeutroniaTrapdoor SANDSTONE_TRAPDOOR, RED_SANDSTONE_TRAPDOOR, ICE_TRAPDOOR, BAMBOO_TRAPDOOR;
    public static BlockChiseled CHISELED_NETHER_BRICK, CHISELED_PURPUR, CHISELED_BRICKS, CHISELED_END_BRICK, CHISELED_RED_NETHER_BRICK;
    public static Block CHISELED_PRISMARINE, CHISELED_PRISMARINE_BRICKS, CHISELED_DARK_PRISMARINE, CUT_PRISMARINE, CUT_PRISMARINE_BRICKS, CUT_DARK_PRISMARINE, ENGRAVED_PRISMARINE, ENGRAVED_PRISMARINE_BRICKS, ENGRAVED_DARK_PRISMARINE;
    public static Block OBSIDIAN_BRICKS, OBSIDIAN_COBBLE, OBSIDIAN_PILLAR, CHISELED_OBSIDIAN, GLOWING_OBSIDIAN;
    public static Block SMOOTH_END_BRICK, SMOOTH_PRISMARINE_BRICKS, SMOOTH_PRISMARINE, SMOOTH_DARK_PRISMARINE, SMOOTH_OBSIDIAN, SMOOTH_PURPUR_BRICK, SMOOTH_NETHER_BRICK, SMOOTH_RED_NETHER_BRICK, SMOOTH_STONE, SMOOTH_STONE_BRICK;
    public static BlockNeutroniaPillar DIAMOND_PILLAR, EMERALD_PILLAR, IRON_PILLAR, GOLD_PILLAR;
    public static Block DIAMOND_BRICKS, EMERALD_BRICKS, IRON_BRICKS, GOLD_BRICKS;
    public static Block SANDSTONE_PILLAR, RED_SANDSTONE_PILLAR, STONE_PILLAR, POLISHED_ANDESITE_PILLAR, POLISHED_GRANITE_PILLAR, POLISHED_DIORITE_PILLAR,
            STONE_BRICK_PILLAR, PRISMARINE_COLUMN, PRISMARINE_PILLAR, PRISMARINE_BRICK_PILLAR, DARK_PRISMARINE_PILLAR, RED_NETHER_BRICK_PILLAR, END_BRICK_PILLAR,
            BRICK_PILLAR, DARK_PRISMARINE_COLUMN;
    public static Block CRACKED_BRICKS, MOSSY_BRICKS, BRICK_PATH, BRICK_TILE, SMALL_BRICK_TILE, CHISELED_BRICK, SMOOTH_BRICK;
    public static Block SANDY_BRICKS, CRACKED_SANDY_BRICKS, MOSSY_SANDY_BRICKS, SANDY_BRICK_PATH, SANDY_BRICK_TILE, SMALL_SANDY_BRICK_TILE, CHISELED_SANDY_BRICK, SANDY_BRICK_PILLAR, SMOOTH_SANDY_BRICK;
    public static Block DIRTY_BRICKS, CRACKED_DIRTY_BRICKS, MOSSY_DIRTY_BRICKS, DIRTY_BRICK_PATH, DIRTY_BRICK_TILE, SMALL_DIRTY_BRICK_TILE, CHISELED_DIRTY_BRICK, DIRTY_BRICK_PILLAR, SMOOTH_DIRTY_BRICK;
    public static Block STONE_TILE, SMALL_STONE_TILE, ANDESITE_TILE, SMALL_ANDESITE_TILE, DIORITE_TILE, SMALL_DIORITE_TILE, GRANITE_TILE, SMALL_GRANITE_TILE,
            STONE_BRICK_TILE, SMALL_STONE_BRICK_TILE, OBSIDIAN_TILES, SMALL_OBSIDIAN_TILES, CHECKERED_TILE, SMALL_CHECKERED_TILE;
    public static Block ROPE_COIL;
    public static Block DARK_ANDESITE, DARK_ANDESITE_BRICKS, POLISHED_DARK_ANDESITE;
    public static Block ANDESITE_BRICKS, GRANITE_BRICKS, DIORITE_BRICKS;
    public static Block MUD, MUD_BRICKS, DRIED_MUD, DRIED_MUD_BRICKS;
    public static Block PACKED_ICE_BRICKS, PACKED_ICE_PILLAR, SMALL_SNOW_BRICKS, SNOW_BRICKS, ICE_TILES, ICE_ROD;
    public static Block FROSTED_GLASS, FROSTED_GLASS_PANE;

    public static Block SAWMILL;

    public static void init() {
        for (VanillaWoodTypes woodType : VanillaWoodTypes.values()) {
            BOOKSHELVES[woodType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("%s_bookshelf", woodType.asString()));
        }

        for (VanillaWoodTypes2 woodTypes2 : VanillaWoodTypes2.values()) {
            LADDERS[woodTypes2.getMetadata()] = new BlockCustomLadder(woodTypes2.asString());
        }

        for(CustomChestTypes woodenChestTypes : CustomChestTypes.values()) {
            WOODEN_CHESTS[woodenChestTypes.getId()] = new BlockCustomChest(woodenChestTypes.asString());
        }

        CARVED_MELON = new BlockNeutroniaBase(Material.ORGANIC,"carved_melon");
        MEL_O_LANTERN = new BlockMelOLantern();

        SANDSTONE_DOOR = new BlockNeutroniaDoor("sandstone_door");
        SANDSTONE_TRAPDOOR = new BlockNeutroniaTrapdoor("sandstone_trapdoor");
        RED_SANDSTONE_DOOR = new BlockNeutroniaDoor("red_sandstone_door");
        RED_SANDSTONE_TRAPDOOR = new BlockNeutroniaTrapdoor("red_sandstone_trapdoor");
        ICE_DOOR = new BlockNeutroniaDoor(Material.ICE, "ice_door");
        ICE_TRAPDOOR = new BlockNeutroniaTrapdoor(Material.ICE, "ice_trapdoor");
        BAMBOO_DOOR = new BlockNeutroniaDoor("bamboo_door");
        BAMBOO_TRAPDOOR = new BlockNeutroniaTrapdoor("bamboo_trapdoor");

        for (WoodTypes woodTypes : WoodTypes.values()) {
//            BARRELS[woodTypes.getMetadata()] = new BlockBarrel(woodTypes);
//            FLUID_BARRELS[woodTypes.getMetadata()] = new BlockFluidBarrel(woodTypes);
        }

        for (WoodTypesVanilla woodType : WoodTypesVanilla.values()) {
            WOOD_LANTERNS[woodType.getMetadata()] = new BlockWoodenLantern(woodType);
            CARVED_PLANKS[woodType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("carved_%s_planks", woodType.asString()));
            PATTERNED_PLANKS[woodType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("patterned_%s_planks", woodType.asString()));
        }

        for (EnumGTPVariants color : EnumGTPVariants.values()) {
            GLAZED_TERRACOTTA_PILLAR[color.getId()] = new BlockNeutroniaPillar(Material.STONE, String.format("%s_glazed_terracotta_pillar", color.asString()));
        }

        CHISELED_PRISMARINE = new BlockNeutroniaBase(Material.STONE, "chiseled_prismarine");
        CHISELED_PRISMARINE_BRICKS = new BlockNeutroniaBase(Material.STONE, "chiseled_prismarine_bricks");
        CHISELED_DARK_PRISMARINE = new BlockNeutroniaBase(Material.STONE, "chiseled_dark_prismarine");
        CHISELED_NETHER_BRICK = new BlockChiseled(Material.STONE, "chiseled_nether_brick", 1.5F, 10.0F, Items.LAVA_BUCKET);
        CHISELED_PURPUR = new BlockChiseled(Material.STONE, "chiseled_purpur", 1.5F, 10.0F, Items.ENDER_PEARL);
        CHISELED_END_BRICK = new BlockChiseled(Material.STONE, "chiseled_end_brick", 3.0F, 15.0F, Items.POPPED_CHORUS_FRUIT);
        CHISELED_RED_NETHER_BRICK = new BlockChiseled(Material.STONE, "chiseled_red_nether_brick", 1.5F, 10.0F, Item.getItemFromBlock(Blocks.GLOWSTONE));

        CUT_PRISMARINE = new BlockNeutroniaBase(Material.STONE, "cut_prismarine");
        CUT_PRISMARINE_BRICKS = new BlockNeutroniaBase(Material.STONE, "cut_prismarine_bricks");
        CUT_DARK_PRISMARINE = new BlockNeutroniaBase(Material.STONE, "cut_dark_prismarine");

        ENGRAVED_PRISMARINE = new BlockNeutroniaBase(Material.STONE, "engraved_prismarine");
        ENGRAVED_PRISMARINE_BRICKS = new BlockNeutroniaBase(Material.STONE, "engraved_prismarine_bricks");
        ENGRAVED_DARK_PRISMARINE = new BlockNeutroniaBase(Material.STONE, "engraved_dark_prismarine");

        OBSIDIAN_BRICKS = new BlockNeutroniaBase(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F).build(), "obsidian_bricks");
        OBSIDIAN_COBBLE = new BlockNeutroniaBase(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F).build(), "obsidian_cobble");
        OBSIDIAN_PILLAR = new BlockNeutroniaPillar(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F).build(), "obsidian_pillar");
        CHISELED_OBSIDIAN = new BlockNeutroniaBase(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F).build(), "chiseled_obsidian");
        GLOWING_OBSIDIAN = new BlockNeutroniaBase(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F).build(), "glowing_obsidian");

        SMOOTH_END_BRICK = new BlockNeutroniaBase(Material.STONE, "smooth_end_brick", 0.8F, 10.0F);
        SMOOTH_PRISMARINE_BRICKS = new BlockNeutroniaBase(Material.STONE, "smooth_prismarine_bricks", 1.5F, 10.0F);
        SMOOTH_PRISMARINE = new BlockNeutroniaBase(Material.STONE, "smooth_prismarine");
        SMOOTH_DARK_PRISMARINE = new BlockNeutroniaBase(Material.STONE, "smooth_dark_prismarine");
        SMOOTH_OBSIDIAN = new BlockNeutroniaBase(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F).build(), "smooth_obsidian");
        SMOOTH_PURPUR_BRICK = new BlockNeutroniaBase(Material.STONE, "smooth_purpur_brick", 1.5F, 10.0F);
        SMOOTH_NETHER_BRICK = new BlockNeutroniaBase(Material.STONE, "smooth_nether_brick"/*, 2.0F, 10.0F*/);
        SMOOTH_RED_NETHER_BRICK = new BlockNeutroniaBase(Material.STONE, "smooth_red_nether_brick", 1.5F, 10.0F);
        SMOOTH_STONE = new BlockNeutroniaBase(Material.STONE, "smooth_stone", 1.5F, 10.0F);
        SMOOTH_STONE_BRICK = new BlockNeutroniaBase(Material.STONE, "smooth_stone_brick", 1.5F, 10.0F);

        DIAMOND_BRICKS = new BlockNeutroniaBase(Material.METAL, "diamond_bricks", 3.0F, 10.0F);
        EMERALD_BRICKS = new BlockNeutroniaBase(Material.METAL, "emerald_bricks", 3.0F, 10.0F);
        IRON_BRICKS = new BlockNeutroniaBase(Material.METAL, "iron_bricks", 3.0F, 10.0F);
        GOLD_BRICKS = new BlockNeutroniaBase(Material.METAL, "gold_bricks", 3.0F, 10.0F);

        DIAMOND_PILLAR = new BlockNeutroniaPillar(Material.METAL, "diamond_pillar", 5.0F, 10.0F);
        EMERALD_PILLAR = new BlockNeutroniaPillar(Material.METAL, "emerald_pillar", 5.0F, 10.0F);
        IRON_PILLAR = new BlockNeutroniaPillar(Material.METAL, "iron_pillar", 5.0F, 10.0F);
        GOLD_PILLAR = new BlockNeutroniaPillar(Material.METAL, "gold_pillar", 3.0F, 10.0F);

        CHISELED_BRICKS = new BlockChiseled(Material.STONE, "chiseled_bricks", 1.5F, 10.0F, Item.getItemFromBlock(Blocks.STONE_SLAB));
        CRACKED_BRICKS = new BlockNeutroniaBase(Material.STONE, "cracked_bricks");
        MOSSY_BRICKS = new BlockNeutroniaBase(Material.STONE, "mossy_bricks");
        BRICK_PATH = new BlockNeutroniaBase(Material.STONE, "brick_path");
        BRICK_TILE = new BlockNeutroniaBase(Material.STONE, "brick_tile");
        SMALL_BRICK_TILE = new BlockNeutroniaBase(Material.STONE, "small_brick_tile");
        CHISELED_BRICK = new BlockNeutroniaBase(Material.STONE, "chiseled_brick");
        BRICK_PILLAR = new BlockNeutroniaPillar(Material.STONE, "brick_pillar", 2.0F, 10.0F);
        SMOOTH_BRICK = new BlockNeutroniaBase(Material.STONE, "smooth_brick", 2.0F, 10.0F);

        SANDY_BRICKS = new BlockNeutroniaBase(Material.STONE, "sandy_bricks");
        CHISELED_BRICKS = new BlockChiseled(Material.STONE, "chiseled_sandy_bricks", 1.5F, 10.0F, Item.getItemFromBlock(Blocks.STONE_SLAB));
        CRACKED_SANDY_BRICKS = new BlockNeutroniaBase(Material.STONE, "cracked_sandy_bricks");
        MOSSY_SANDY_BRICKS = new BlockNeutroniaBase(Material.STONE, "mossy_sandy_bricks");
        SANDY_BRICK_PATH = new BlockNeutroniaBase(Material.STONE, "sandy_brick_path");
        SANDY_BRICK_TILE = new BlockNeutroniaBase(Material.STONE, "sandy_brick_tile");
        SMALL_SANDY_BRICK_TILE = new BlockNeutroniaBase(Material.STONE, "small_sandy_brick_tile");
        CHISELED_SANDY_BRICK = new BlockNeutroniaBase(Material.STONE, "chiseled_sandy_brick");
        SANDY_BRICK_PILLAR = new BlockNeutroniaPillar(Material.STONE, "sandy_brick_pillar", 2.0F, 10.0F);
        SMOOTH_SANDY_BRICK = new BlockNeutroniaBase(Material.STONE, "smooth_sandy_brick", 2.0F, 10.0F);

        DIRTY_BRICKS = new BlockNeutroniaBase(Material.STONE, "dirty_bricks");
        CHISELED_BRICKS = new BlockChiseled(Material.STONE, "chiseled_dirty_bricks", 1.5F, 10.0F, Item.getItemFromBlock(Blocks.STONE_SLAB));
        CRACKED_DIRTY_BRICKS = new BlockNeutroniaBase(Material.STONE, "cracked_dirty_bricks");
        MOSSY_DIRTY_BRICKS = new BlockNeutroniaBase(Material.STONE, "mossy_dirty_bricks");
        DIRTY_BRICK_PATH = new BlockNeutroniaBase(Material.STONE, "dirty_brick_path");
        DIRTY_BRICK_TILE = new BlockNeutroniaBase(Material.STONE, "dirty_brick_tile");
        SMALL_DIRTY_BRICK_TILE = new BlockNeutroniaBase(Material.STONE, "small_dirty_brick_tile");
        CHISELED_DIRTY_BRICK = new BlockNeutroniaBase(Material.STONE, "chiseled_dirty_brick");
        DIRTY_BRICK_PILLAR = new BlockNeutroniaPillar(Material.STONE, "dirty_brick_pillar", 2.0F, 10.0F);
        SMOOTH_DIRTY_BRICK = new BlockNeutroniaBase(Material.STONE, "smooth_dirty_brick", 2.0F, 10.0F);

        CHECKERED_TILE = new BlockNeutroniaBase(Material.STONE, "checkered_tiles");
        SMALL_CHECKERED_TILE = new BlockNeutroniaBase(Material.STONE, "small_checkered_tiles");
        STONE_TILE = new BlockNeutroniaBase(Material.STONE, "stone_tile");
        SMALL_STONE_TILE = new BlockNeutroniaBase(Material.STONE, "small_stone_tile");
        ANDESITE_TILE = new BlockNeutroniaBase(Material.STONE, "andesite_tile");
        SMALL_ANDESITE_TILE = new BlockNeutroniaBase(Material.STONE, "small_andesite_tile");
        DIORITE_TILE = new BlockNeutroniaBase(Material.STONE, "diorite_tile");
        SMALL_DIORITE_TILE = new BlockNeutroniaBase(Material.STONE, "small_diorite_tile");
        GRANITE_TILE = new BlockNeutroniaBase(Material.STONE, "granite_tile");
        SMALL_GRANITE_TILE = new BlockNeutroniaBase(Material.STONE, "small_granite_tile");
        STONE_BRICK_TILE = new BlockNeutroniaBase(Material.STONE, "stone_brick_tile");
        SMALL_STONE_BRICK_TILE = new BlockNeutroniaBase(Material.STONE, "small_stone_brick_tile");
        SMALL_OBSIDIAN_TILES = new BlockNeutroniaBase(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F).build(), "small_obsidian_tiles");
        OBSIDIAN_TILES = new BlockNeutroniaBase(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F).build(), "obsidian_tiles");

        ROPE_COIL = new BlockNeutroniaPillar(Material.WOOL, "rope_coil");

        DARK_ANDESITE = new BlockNeutroniaBase(Material.STONE, "dark_andesite");
        DARK_ANDESITE_BRICKS = new BlockNeutroniaBase(Material.STONE, "dark_andesite_bricks");
        POLISHED_DARK_ANDESITE = new BlockNeutroniaBase(Material.STONE, "polished_dark_andesite");

        ANDESITE_BRICKS = new BlockNeutroniaBase(Material.STONE, "andesite_bricks");
        DIORITE_BRICKS = new BlockNeutroniaBase(Material.STONE, "diorite_bricks");
        GRANITE_BRICKS = new BlockNeutroniaBase(Material.STONE, "granite_bricks");

        MUD = new BlockMud();
        MUD_BRICKS = new BlockMud("mud_bricks");
        DRIED_MUD = new BlockNeutroniaBase(Material.ORGANIC, "dried_mud");
        DRIED_MUD_BRICKS = new BlockNeutroniaBase(Material.ORGANIC, "dried_mud_bricks");

        PACKED_ICE_BRICKS = new BlockNeutroniaBase(Material.PACKED_ICE, "packed_ice_bricks");
        PACKED_ICE_PILLAR = new BlockNeutroniaPillar(Material.PACKED_ICE, "packed_ice_pillar");
        SMALL_SNOW_BRICKS = new BlockNeutroniaBase(Material.PACKED_ICE, "small_snow_bricks");
        SNOW_BRICKS = new BlockNeutroniaBase(Material.PACKED_ICE, "snow_bricks");
        ICE_TILES = new BlockNeutroniaBase(Material.PACKED_ICE, "ice_tiles");
//        ICE_ROD = new BlockRodBase("ice_rod", true);

        SANDSTONE_PILLAR = new BlockNeutroniaPillar(Material.STONE, "sandstone_pillar");
        RED_SANDSTONE_PILLAR = new BlockNeutroniaPillar(Material.STONE, "red_sandstone_pillar");
        STONE_PILLAR = new BlockNeutroniaPillar(Material.STONE, "stone_pillar");
        POLISHED_ANDESITE_PILLAR = new BlockNeutroniaPillar(Material.STONE, "polished_andesite_pillar");
        POLISHED_DIORITE_PILLAR = new BlockNeutroniaPillar(Material.STONE, "polished_diorite_pillar");
        POLISHED_GRANITE_PILLAR = new BlockNeutroniaPillar(Material.STONE, "polished_granite_pillar");
        STONE_BRICK_PILLAR = new BlockNeutroniaPillar(Material.STONE, "stone_brick_pillar");
        PRISMARINE_COLUMN = new BlockNeutroniaPillar(Material.STONE, "prismarine_column");
        PRISMARINE_PILLAR = new BlockNeutroniaPillar(Material.STONE, "prismarine_pillar");
        PRISMARINE_BRICK_PILLAR = new BlockNeutroniaPillar(Material.STONE, "prismarine_brick_pillar");
        DARK_PRISMARINE_PILLAR = new BlockNeutroniaPillar(Material.STONE, "dark_prismarine_pillar");
        RED_NETHER_BRICK_PILLAR = new BlockNeutroniaPillar(Material.STONE, "red_nether_brick_pillar", 2.0F, 10.0F);
        END_BRICK_PILLAR = new BlockNeutroniaPillar(Material.STONE, "end_brick_pillar", 3.0F, 15.0F);
        DARK_PRISMARINE_COLUMN = new BlockNeutroniaPillar(Material.STONE, "dark_prismarine_column", 1.5F, 10.0F);

        FROSTED_GLASS = new BlockFrostedGlass();
        FROSTED_GLASS_PANE = new BlockFrostedGlassPane();

        for (SoulStoneVariants soulStoneTypes : SoulStoneVariants.values()) {
            SOUL_STONE[soulStoneTypes.getMetadata()] = new BlockNeutroniaBase(Material.STONE, soulStoneTypes.asString());
//            BlockRegisteringUtils.addSlabAndStair(soulStoneTypes.getName(), SOUL_STONE[soulStoneTypes.getMetadata()], 0, true);
        }

        /*BlockRegisteringUtils.addWalls("stone", Blocks.STONE, 0, true);
        BlockRegisteringUtils.addWalls("granite", Blocks.STONE, 1, true);
        BlockRegisteringUtils.addWalls("smooth_granite", Blocks.STONE, 2, true);
        BlockRegisteringUtils.addWalls("diorite", Blocks.STONE, 3, true);
        BlockRegisteringUtils.addWalls("smooth_diorite", Blocks.STONE, 4, true);
        BlockRegisteringUtils.addWalls("andesite", Blocks.STONE, 5, true);
        BlockRegisteringUtils.addWalls("smooth_andesite", Blocks.STONE, 6, true);
        BlockRegisteringUtils.addWalls("dark_andesite", DARK_ANDESITE, 0, true);
        BlockRegisteringUtils.addWalls("polished_dark_andesite", POLISHED_DARK_ANDESITE, 0, true);

        BlockRegisteringUtils.addWalls("brick", Blocks.BRICKS, 0, true);
        BlockRegisteringUtils.addWalls("nether_brick", Blocks.NETHER_BRICKS, 0, true);
        BlockRegisteringUtils.addWalls("stone_brick", Blocks.STONE, 0, true);
        BlockRegisteringUtils.addWalls("mossy_stone_brick", Blocks.STONE, 0, true);
        BlockRegisteringUtils.addWalls("prismarine", Blocks.PRISMARINE, 0, true);
        BlockRegisteringUtils.addWalls("cracked_stone_brick", Blocks.STONE, 0, true);
        BlockRegisteringUtils.addWalls("red_nether_brick", Blocks.RED_NETHER_BRICKS, 0, true);
        BlockRegisteringUtils.addWalls("end_brick", Blocks.END_STONE_BRICKS, 0, true);
        BlockRegisteringUtils.addWalls("smooth_quartz", Blocks.QUARTZ_BLOCK, 0, true);
        BlockRegisteringUtils.addWalls("sandstone", Blocks.SANDSTONE, 0, true);
        BlockRegisteringUtils.addWalls("red_sandstone", Blocks.RED_SANDSTONE, 0, true);
        BlockRegisteringUtils.addWalls("smooth_end_brick", SMOOTH_END_BRICK, 0, true);
        BlockRegisteringUtils.addWalls("smooth_prismarine_bricks", SMOOTH_PRISMARINE_BRICKS, 0, true);
        BlockRegisteringUtils.addWalls("smooth_prismarine", SMOOTH_PRISMARINE, 0, true);
        BlockRegisteringUtils.addWalls("smooth_brick", SMOOTH_BRICK, 0, true);
        BlockRegisteringUtils.addWalls("smooth_purpur", SMOOTH_PURPUR_BRICK, 0, true);
        BlockRegisteringUtils.addWalls("smooth_nether_brick", SMOOTH_NETHER_BRICK, 0, true);
        BlockRegisteringUtils.addWalls("smooth_red_nether_brick", SMOOTH_RED_NETHER_BRICK, 0, true);
        BlockRegisteringUtils.addWalls("smooth_stone", SMOOTH_STONE, 0, true);
        BlockRegisteringUtils.addWalls("smooth_stone_brick", SMOOTH_STONE_BRICK, 0, true);
        BlockRegisteringUtils.addWalls("dirty_brick", DIRTY_BRICKS, 0, true);
        BlockRegisteringUtils.addWalls("sandy_brick", SANDY_BRICKS, 0, true);
        BlockRegisteringUtils.addWalls("smooth_dirty_brick", SMOOTH_DIRTY_BRICK, 0, true);
        BlockRegisteringUtils.addWalls("smooth_sandy_brick", SMOOTH_SANDY_BRICK, 0, true);

        BlockRegisteringUtils.addSlabAndStair("stone", Blocks.STONE, 0, true);
        BlockRegisteringUtils.addSlabAndStair("granite", Blocks.STONE, 1, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_granite", Blocks.STONE, 2, true);
        BlockRegisteringUtils.addSlabAndStair("diorite", Blocks.STONE, 3, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_diorite", Blocks.STONE, 4, true);
        BlockRegisteringUtils.addSlabAndStair("andesite", Blocks.STONE, 5, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_andesite", Blocks.STONE, 6, true);
        BlockRegisteringUtils.addSlabAndStair("dark_andesite", DARK_ANDESITE, 0, true);
        BlockRegisteringUtils.addSlabAndStair("polished_dark_andesite", POLISHED_DARK_ANDESITE, 0, true);

        BlockRegisteringUtils.addSlabAndStair("mossy_stone_brick", Blocks.STONE, 0, true);
        BlockRegisteringUtils.addSlabAndStair("cracked_stone_brick", Blocks.STONE, 0, true);
        BlockRegisteringUtils.addSlabAndStair("red_nether_brick", Blocks.RED_NETHER_BRICK, 0, true);
        BlockRegisteringUtils.addSlabAndStair("end_stone_brick", Blocks.END_BRICKS, 0, true);
        BlockRegisteringUtils.addSlabAndStair("prismarine", Blocks.PRISMARINE, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_sandstone", SMOOTH_SANDSTONE, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_red_sandstone", SMOOTH_RED_SANDSTONE, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_quartz", Blocks.QUARTZ_BLOCK, 0, true);
        BlockRegisteringUtils.addSlabAndStair("mossy_cobblestone", Blocks.RED_NETHER_BRICK, 0, true);

        BlockRegisteringUtils.addSlabAndStair("smooth_end_stone_brick", SMOOTH_END_BRICK, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_prismarine_bricks", SMOOTH_PRISMARINE_BRICKS, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_prismarine", SMOOTH_PRISMARINE, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_brick", SMOOTH_BRICK, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_purpur", SMOOTH_PURPUR_BRICK, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_nether_brick", SMOOTH_NETHER_BRICK, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_red_nether_brick", SMOOTH_RED_NETHER_BRICK, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_stone", SMOOTH_STONE, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_stone_brick", SMOOTH_STONE_BRICK, 0, true);
        BlockRegisteringUtils.addSlabAndStair("dirty_brick", DIRTY_BRICKS, 0, true);
        BlockRegisteringUtils.addSlabAndStair("sandy_brick", SANDY_BRICKS, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_dirty_brick", SMOOTH_DIRTY_BRICK, 0, true);
        BlockRegisteringUtils.addSlabAndStair("smooth_sandy_brick", SMOOTH_SANDY_BRICK, 0, true);*/

//        SAWMILL = new BlockSawmill("sawmill");
    }

}