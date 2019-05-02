package team.hollow.neutronia.init;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import team.hollow.abnormalib.blocks.FlowerPotBaseBlock;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.blocks.sapling.PalmSaplingGenerator;
import team.hollow.neutronia.blocks.sapling.WillowSaplingGenerator;
import team.hollow.neutronia.enums.*;
import team.hollow.neutronia.utils.registry.BlockRegistryBuilder;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import static team.hollow.neutronia.Neutronia.MOD_ID;

public class NBlocks {

    public static final Block[] BOOKSHELVES = new Block[9], PATTERNED_PLANKS = new Block[WoodType.VANILLA_WOODS.size()],
            WOOD = new Block[WoodType.VANILLA_WOODS.size()], CARVED_PLANKS = new Block[WoodType.values().length]/*, BARRELS = new Block[WoodType.VANILLA_WOODS.size()]*/, WOOD_LANTERNS = new Block[WoodType.values().length],
            LADDERS = new Block[LadderVariants.values().length], LOG_CAMPFIRE = new Block[WoodType.values().length],
            STRIPPED_LOGS = new Block[WoodType.values().length], STRIPPED_WOOD = new Block[WoodType.values().length], LECTERNS = new Block[WoodType.VANILLA_WOODS.size()];
    //    public static final Block GOLD_SCAFFOLDING, IRON_SCAFFOLDING;
    public static final Block[] GLAZED_TERRACOTTA_PILLAR = new Block[GTPVariants.values().length], SOUL_STONE = new Block[4];
    public static final Block PALM_LOG, PALM_WOOD, PALM_PLANKS, PALM_DOOR, PALM_TRAPDOOR, PALM_LEAVES, PALM_LOG_TOP, PALM_SAPLING/*, COCONUT*/;
    public static final Block WILLOW_LOG, WILLOW_WOOD, WILLOW_PLANKS, WILLOW_DOOR, WILLOW_TRAPDOOR, WILLOW_LEAVES, WILLOW_SAPLING, WILLOW_UNDERWATER_SAPLING;
    public static final Block GRATE, IRON_GRATE, GOLD_GRATE, WROUGHT_IRON_GRATE;
    public static final Block /*WROUGHT_IRON_BLOCK, */WROUGHT_IRON_BARS;
    public static final Block[] WOODEN_CHESTS = new Block[CustomChestTypes.values().length];
    public static final Block SANDSTONE_DOOR, RED_SANDSTONE_DOOR, ICE_DOOR, BAMBOO_DOOR, WROUGHT_IRON_DOOR;
    public static final Block SANDSTONE_TRAPDOOR, RED_SANDSTONE_TRAPDOOR, ICE_TRAPDOOR, BAMBOO_TRAPDOOR;
    public static final Block CHISELED_PRISMARINE, CHISELED_PRISMARINE_BRICKS, CHISELED_DARK_PRISMARINE, CUT_PRISMARINE, CUT_PRISMARINE_BRICKS, CUT_DARK_PRISMARINE, ENGRAVED_PRISMARINE, ENGRAVED_PRISMARINE_BRICKS, ENGRAVED_DARK_PRISMARINE;
    public static final Block OBSIDIAN_BRICKS, OBSIDIAN_COBBLE, OBSIDIAN_PILLAR, CHISELED_OBSIDIAN, GLOWING_OBSIDIAN;
    public static final Block SMOOTH_END_BRICK, SMOOTH_PRISMARINE_BRICKS, SMOOTH_PRISMARINE, SMOOTH_DARK_PRISMARINE, SMOOTH_OBSIDIAN, SMOOTH_PURPUR_BRICK, SMOOTH_NETHER_BRICK, SMOOTH_RED_NETHER_BRICK, SMOOTH_STONE, SMOOTH_STONE_BRICK;
    public static final NeutroniaPillarBlock DIAMOND_PILLAR, EMERALD_PILLAR, IRON_PILLAR, GOLD_PILLAR;
    public static final Block DIAMOND_BRICKS, EMERALD_BRICKS, IRON_BRICKS, GOLD_BRICKS;
    public static final Block SANDSTONE_PILLAR, RED_SANDSTONE_PILLAR, STONE_PILLAR, POLISHED_ANDESITE_PILLAR, POLISHED_GRANITE_PILLAR, POLISHED_DIORITE_PILLAR,
            STONE_BRICK_PILLAR, PRISMARINE_COLUMN, PRISMARINE_PILLAR, PRISMARINE_BRICK_PILLAR, DARK_PRISMARINE_PILLAR, RED_NETHER_BRICK_PILLAR, END_BRICK_PILLAR,
            BRICK_PILLAR, DARK_PRISMARINE_COLUMN;
    public static final Block SQUARED_BRICKS, GAPLESS_BRICKS, SCALY_BRICKS, CRACKED_BRICKS, MOSSY_BRICKS, BRICK_PATH, BRICK_TILE, SMALL_BRICK_TILE, CHISELED_BRICK, SMOOTH_BRICK;
    public static final Block SQUARED_SANDY_BRICKS, GAPLESS_SANDY_BRICKS, SCALY_SANDY_BRICKS, SANDY_BRICKS, CHISELED_SANDY_BRICKS, CRACKED_SANDY_BRICKS, MOSSY_SANDY_BRICKS, SANDY_BRICK_PATH, SANDY_BRICK_TILE, SMALL_SANDY_BRICK_TILE, SANDY_BRICK_PILLAR, SMOOTH_SANDY_BRICK;
    public static final Block SQUARED_DIRTY_BRICKS, GAPLESS_DIRTY_BRICKS, SCALY_DIRTY_BRICKS, DIRTY_BRICKS, CHISELED_DIRTY_BRICK, CRACKED_DIRTY_BRICKS, MOSSY_DIRTY_BRICKS, DIRTY_BRICK_PATH, DIRTY_BRICK_TILE, SMALL_DIRTY_BRICK_TILE, DIRTY_BRICK_PILLAR, SMOOTH_DIRTY_BRICK;
    public static final Block STONE_TILE, SMALL_STONE_TILE, ANDESITE_TILE, SMALL_ANDESITE_TILE, DIORITE_TILE, SMALL_DIORITE_TILE, GRANITE_TILE, SMALL_GRANITE_TILE,
            STONE_BRICK_TILE, SMALL_STONE_BRICK_TILE, OBSIDIAN_TILES, SMALL_OBSIDIAN_TILES, CHECKERED_TILE, SMALL_CHECKERED_TILE;
    public static final Block ROPE_COIL;
    public static final Block DARK_ANDESITE, DARK_ANDESITE_BRICKS, POLISHED_DARK_ANDESITE;
    public static final Block ANDESITE_BRICKS, GRANITE_BRICKS, DIORITE_BRICKS;
    public static final Block MUD, MUD_BRICKS, DRIED_MUD, DRIED_MUD_BRICKS;
    public static final Block PACKED_ICE_BRICKS, PACKED_ICE_PILLAR, SMALL_SNOW_BRICKS, SNOW_BRICKS, ICE_TILES;
    public static final Block FROSTED_GLASS, FROSTED_GLASS_PANE;
    public static final Block CHAIN, IRON_CHAIN, ICE_CHAIN, WROUGHT_IRON_CHAIN, GOLD_CHAIN, PRISMARINE_CHAIN;
    /*public static final Block PUMPKIN = new PumpkinBlock();
    public static final Block JACK_O_LANTERN = new JackOLanternBlock();
    public static final Block MELON = new MelonBlock(), MEL_O_LANTERN = new MelOLanternBlock();*/
    public static final Block CHEESE_CAKE = RegistryUtils.register(new CakeBaseBlock(), "cheese_cake", ItemGroup.FOOD);
    public static final Block COCOA_CAKE = RegistryUtils.register(new CakeBaseBlock(), "cocoa_cake", ItemGroup.FOOD);
    public static final Block CHOCOLATE_CAKE = RegistryUtils.register(new CakeBaseBlock(), "chocolate_cake", ItemGroup.FOOD);
    public static final Block PUMPKIN_PIE = RegistryUtils.register(new PieBlock(), new Identifier("minecraft", "pumpkin_pie"), ItemGroup.FOOD);
    public static final Block BLUEBERRY_PIE = RegistryUtils.register(new PieBlock(), "blueberry_pie", ItemGroup.FOOD);
    public static final Block SWEET_BERRY_PIE = RegistryUtils.register(new PieBlock(), "sweet_berry_pie", ItemGroup.FOOD);
    public static final Block APPLE_PIE = RegistryUtils.register(new PieBlock(), "apple_pie", ItemGroup.FOOD);
    public static final Block STICK_BUNDLE, CHORUS_BUNDLE, SUGAR_CANE_BUNDLE, BAMBOO_BUNDLE, UNCUT_BAMBOO_BUNDLE, NETHER_WART_SACK, COCOA_BEAN_SACK, GUNPOWDER_SACK,
            EGG_CRATE, BEETROOT_CRATE, POTATO_CRATE, CARROT_CRATE, APPLE_CRATE, GOLDEN_APPLE_CRATE, CACTUS_BUNDLE;
    public static final Block BAMBOO_PLANKS, BAMBOO_SIGN, BAMBOO_WALL_SIGN/*, BAMBOO_TORCH*/, THATCH;
    public static final Block ACIDIAN, ACIDIAN_BRICKS, ACIDIAN_PILLAR, CHISELED_ACIDIAN/*, ACIDIAN_BARS*/;
    public static final Block TREATED_PLANKS, TREATED_SIDING;
    public static final Block POTTED_BEETROOT, POTTED_CARROTS, POTTED_CHORUS, POTTED_GRASS, POTTED_LILAC, POTTED_MELON, POTTED_NETHER_WART, POTTED_PEONY,
            POTTED_POTATOES, POTTED_PUMPKIN, POTTED_ROSE_BUSH, POTTED_SUGAR_CANE, POTTED_SUNFLOWER, POTTED_TALL_GRASS, POTTED_LARGE_FERN, POTTED_WHEAT;
    public static final Block PILLAR_CORAL_BLOCK, LIME_BRAIN_CORAL_BLOCK, GREEN_BUBBLE_CORAL_BLOCK, ACAN_CORAL_BLOCK, ANTIPATHES_CORAL_BLOCK, STAGHORN_CORAL_BLOCK;
    public static final Block DEAD_PILLAR_CORAL_BLOCK, DEAD_LIME_BRAIN_CORAL_BLOCK, DEAD_GREEN_BUBBLE_CORAL_BLOCK, DEAD_ACAN_CORAL_BLOCK, DEAD_ANTIPATHES_CORAL_BLOCK, DEAD_STAGHORN_CORAL_BLOCK;
    public static final Block PILLAR_CORAL_FAN, LIME_BRAIN_CORAL_FAN, GREEN_BUBBLE_CORAL_FAN, ACAN_CORAL_FAN, ANTIPATHES_CORAL_FAN, STAGHORN_CORAL_FAN;
    public static final Block DEAD_PILLAR_CORAL_FAN, DEAD_LIME_BRAIN_CORAL_FAN, DEAD_GREEN_BUBBLE_CORAL_FAN, DEAD_ACAN_CORAL_FAN, DEAD_ANTIPATHES_CORAL_FAN, DEAD_STAGHORN_CORAL_FAN;
    public static final Block PILLAR_CORAL_WALL_FAN, LIME_BRAIN_CORAL_WALL_FAN, GREEN_BUBBLE_CORAL_WALL_FAN, ACAN_CORAL_WALL_FAN, ANTIPATHES_CORAL_WALL_FAN, STAGHORN_CORAL_WALL_FAN;
    public static final Block DEAD_PILLAR_CORAL_WALL_FAN, DEAD_LIME_BRAIN_CORAL_WALL_FAN, DEAD_GREEN_BUBBLE_CORAL_WALL_FAN, DEAD_ACAN_CORAL_WALL_FAN, DEAD_ANTIPATHES_CORAL_WALL_FAN, DEAD_STAGHORN_CORAL_WALL_FAN;
    public static final Block PILLAR_CORAL, LIME_BRAIN_CORAL, GREEN_BUBBLE_CORAL, ACAN_CORAL, ANTIPATHES_CORAL, STAGHORN_CORAL;
    public static final Block DEAD_PILLAR_CORAL, DEAD_LIME_BRAIN_CORAL, DEAD_GREEN_BUBBLE_CORAL, DEAD_ACAN_CORAL, DEAD_ANTIPATHES_CORAL, DEAD_STAGHORN_CORAL;
    //    public static final Block BLUE_BERRY_BUSH, GOOSEBERRY_BUSH, WITHER_BERRY_BUSH, GREEN_GRAPE_BUSH, PURPLE_GRAPE_BUSH;
    public static final Block GOLD_LANTERN, IRON_LANTERN, WROUGHT_IRON_LANTERN, PRISMARINE_LANTERN, ICE_LANTERN;
    public static Block[] PUMPKIN = new Block[CarvedFaceTypes.values().length];
    public static final Block ICE_SAW;
    public static final Block SAWMILL;

    static {
        DEAD_PILLAR_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseDeadCoralBlockBlock(), new Identifier(MOD_ID, "dead_pillar_coral_block"), ItemGroup.BUILDING_BLOCKS);
        DEAD_LIME_BRAIN_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseDeadCoralBlockBlock(), new Identifier(MOD_ID, "dead_lime_brain_coral_block"), ItemGroup.BUILDING_BLOCKS);
        DEAD_GREEN_BUBBLE_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseDeadCoralBlockBlock(), new Identifier(MOD_ID, "dead_green_bubble_coral_block"), ItemGroup.BUILDING_BLOCKS);
        DEAD_ACAN_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseDeadCoralBlockBlock(), new Identifier(MOD_ID, "dead_acan_coral_block"), ItemGroup.BUILDING_BLOCKS);
        DEAD_ANTIPATHES_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseDeadCoralBlockBlock(), new Identifier(MOD_ID, "dead_antipathes_coral_block"), ItemGroup.BUILDING_BLOCKS);
        DEAD_STAGHORN_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseDeadCoralBlockBlock(), new Identifier(MOD_ID, "dead_staghorn_coral_block"), ItemGroup.BUILDING_BLOCKS);

        PILLAR_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseCoralBlockBlock(DEAD_PILLAR_CORAL_BLOCK), new Identifier(MOD_ID, "pillar_coral_block"), ItemGroup.BUILDING_BLOCKS);
        LIME_BRAIN_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseCoralBlockBlock(DEAD_LIME_BRAIN_CORAL_BLOCK), new Identifier(MOD_ID, "lime_brain_coral_block"), ItemGroup.BUILDING_BLOCKS);
        GREEN_BUBBLE_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseCoralBlockBlock(DEAD_GREEN_BUBBLE_CORAL_BLOCK), new Identifier(MOD_ID, "green_bubble_coral_block"), ItemGroup.BUILDING_BLOCKS);
        ACAN_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseCoralBlockBlock(DEAD_ACAN_CORAL_BLOCK), new Identifier(MOD_ID, "acan_coral_block"), ItemGroup.BUILDING_BLOCKS);
        ANTIPATHES_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseCoralBlockBlock(DEAD_ANTIPATHES_CORAL_BLOCK), new Identifier(MOD_ID, "antipathes_coral_block"), ItemGroup.BUILDING_BLOCKS);
        STAGHORN_CORAL_BLOCK = RegistryUtils.register(new NeutroniaBaseCoralBlockBlock(DEAD_STAGHORN_CORAL_BLOCK), new Identifier(MOD_ID, "staghorn_coral_block"), ItemGroup.BUILDING_BLOCKS);

        DEAD_PILLAR_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseDeadWallCoralFanBlock(), "dead_pillar_coral_wall_fan");
        DEAD_LIME_BRAIN_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseDeadWallCoralFanBlock(), "dead_lime_brain_coral_wall_fan");
        DEAD_GREEN_BUBBLE_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseDeadWallCoralFanBlock(), "dead_green_bubble_coral_wall_fan");
        DEAD_ACAN_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseDeadWallCoralFanBlock(), "dead_acan_coral_wall_fan");
        DEAD_ANTIPATHES_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseDeadWallCoralFanBlock(), "dead_antipathes_coral_wall_fan");
        DEAD_STAGHORN_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseDeadWallCoralFanBlock(), "dead_staghorn_coral_wall_fan");

        PILLAR_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseCoralWallFanBlock(DEAD_PILLAR_CORAL_WALL_FAN), "pillar_coral_wall_fan");
        LIME_BRAIN_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseCoralWallFanBlock(DEAD_LIME_BRAIN_CORAL_WALL_FAN), "lime_brain_coral_wall_fan");
        GREEN_BUBBLE_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseCoralWallFanBlock(DEAD_GREEN_BUBBLE_CORAL_WALL_FAN), "green_bubble_coral_wall_fan");
        ACAN_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseCoralWallFanBlock(DEAD_ACAN_CORAL_WALL_FAN), "acan_coral_wall_fan");
        ANTIPATHES_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseCoralWallFanBlock(DEAD_ANTIPATHES_CORAL_WALL_FAN), "antipathes_coral_wall_fan");
        STAGHORN_CORAL_WALL_FAN = RegistryUtils.registerNoBI(new NeutroniaBaseCoralWallFanBlock(DEAD_STAGHORN_CORAL_WALL_FAN), "staghorn_coral_wall_fan");

        DEAD_PILLAR_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseDeadCoralFanBlock(), DEAD_PILLAR_CORAL_WALL_FAN, "dead_pillar_coral_fan");
        DEAD_LIME_BRAIN_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseDeadCoralFanBlock(), DEAD_LIME_BRAIN_CORAL_WALL_FAN, "dead_lime_brain_coral_fan");
        DEAD_GREEN_BUBBLE_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseDeadCoralFanBlock(), DEAD_GREEN_BUBBLE_CORAL_WALL_FAN, "dead_green_bubble_coral_fan");
        DEAD_ACAN_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseDeadCoralFanBlock(), DEAD_ACAN_CORAL_WALL_FAN, "dead_acan_coral_fan");
        DEAD_ANTIPATHES_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseDeadCoralFanBlock(), DEAD_ANTIPATHES_CORAL_WALL_FAN, "dead_antipathes_coral_fan");
        DEAD_STAGHORN_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseDeadCoralFanBlock(), DEAD_STAGHORN_CORAL_WALL_FAN, "dead_staghorn_coral_fan");

        PILLAR_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseCoralFanBlock(DEAD_PILLAR_CORAL_FAN), PILLAR_CORAL_WALL_FAN, "pillar_coral_fan");
        LIME_BRAIN_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseCoralFanBlock(DEAD_LIME_BRAIN_CORAL_FAN), LIME_BRAIN_CORAL_WALL_FAN, "lime_brain_coral_fan");
        GREEN_BUBBLE_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseCoralFanBlock(DEAD_GREEN_BUBBLE_CORAL_FAN), GREEN_BUBBLE_CORAL_WALL_FAN, "green_bubble_coral_fan");
        ACAN_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseCoralFanBlock(DEAD_ACAN_CORAL_FAN), ACAN_CORAL_WALL_FAN, "acan_coral_fan");
        ANTIPATHES_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseCoralFanBlock(DEAD_ANTIPATHES_CORAL_FAN), ANTIPATHES_CORAL_WALL_FAN, "antipathes_coral_fan");
        STAGHORN_CORAL_FAN = RegistryUtils.register(new NeutroniaBaseCoralFanBlock(DEAD_STAGHORN_CORAL_FAN), STAGHORN_CORAL_WALL_FAN, "staghorn_coral_fan");

        DEAD_PILLAR_CORAL = RegistryUtils.register(new NeutroniaBaseDeadCoralBlock(), new Identifier(MOD_ID, "dead_pillar_coral"));
        DEAD_LIME_BRAIN_CORAL = RegistryUtils.register(new NeutroniaBaseDeadCoralBlock(), new Identifier(MOD_ID, "dead_lime_brain_coral"));
        DEAD_GREEN_BUBBLE_CORAL = RegistryUtils.register(new NeutroniaBaseDeadCoralBlock(), new Identifier(MOD_ID, "dead_green_bubble_coral"));
        DEAD_ACAN_CORAL = RegistryUtils.register(new NeutroniaBaseDeadCoralBlock(), new Identifier(MOD_ID, "dead_acan_coral"));
        DEAD_ANTIPATHES_CORAL = RegistryUtils.register(new NeutroniaBaseDeadCoralBlock(), new Identifier(MOD_ID, "dead_antipathes_coral"));
        DEAD_STAGHORN_CORAL = RegistryUtils.register(new NeutroniaBaseDeadCoralBlock(), new Identifier(MOD_ID, "dead_staghorn_coral"));

        PILLAR_CORAL = RegistryUtils.register(new NeutroniaBaseCoralBlock(DEAD_PILLAR_CORAL), new Identifier(MOD_ID, "pillar_coral"));
        LIME_BRAIN_CORAL = RegistryUtils.register(new NeutroniaBaseCoralBlock(DEAD_LIME_BRAIN_CORAL), new Identifier(MOD_ID, "lime_brain_coral"));
        GREEN_BUBBLE_CORAL = RegistryUtils.register(new NeutroniaBaseCoralBlock(DEAD_GREEN_BUBBLE_CORAL), new Identifier(MOD_ID, "green_bubble_coral"));
        ACAN_CORAL = RegistryUtils.register(new NeutroniaBaseCoralBlock(DEAD_ACAN_CORAL), new Identifier(MOD_ID, "acan_coral"));
        ANTIPATHES_CORAL = RegistryUtils.register(new NeutroniaBaseCoralBlock(DEAD_ANTIPATHES_CORAL), new Identifier(MOD_ID, "antipathes_coral"));
        STAGHORN_CORAL = RegistryUtils.register(new NeutroniaBaseCoralBlock(DEAD_STAGHORN_CORAL), new Identifier(MOD_ID, "staghorn_coral"));

        /*BLUE_BERRY_BUSH = RegistryUtils.registerNoBI(new NeutroniaBerryBushBlock(), "blueberry_bush");
        GOOSEBERRY_BUSH = RegistryUtils.registerNoBI(new NeutroniaGooseberryBushBlock(), "gooseberry_bush");
        WITHER_BERRY_BUSH = RegistryUtils.registerNoBI(new NeutroniaWitherBerryBushBlock(), "wither_berry_bush");
        GREEN_GRAPE_BUSH = RegistryUtils.registerNoBI(new NeutroniaGreenGrapeBushBlock(), "green_grape_bush");
        PURPLE_GRAPE_BUSH = RegistryUtils.registerNoBI(new NeutroniaPurpleGrapeBushBlock(), "purple_grape_bush");*/

        IRON_LANTERN = RegistryUtils.register(new LanternBlock(FabricBlockSettings.of(Material.METAL).hardness(3.5F).sounds(BlockSoundGroup.LANTERN).lightLevel(15).build()), new Identifier(MOD_ID, "iron_lantern"));
        GOLD_LANTERN = RegistryUtils.register(new LanternBlock(FabricBlockSettings.of(Material.METAL).hardness(3.5F).sounds(BlockSoundGroup.LANTERN).lightLevel(15).build()), new Identifier(MOD_ID, "gold_lantern"));
        WROUGHT_IRON_LANTERN = RegistryUtils.register(new LanternBlock(FabricBlockSettings.of(Material.METAL).hardness(3.5F).sounds(BlockSoundGroup.LANTERN).lightLevel(15).build()), new Identifier(MOD_ID, "wrought_iron_lantern"));
        PRISMARINE_LANTERN = RegistryUtils.register(new LanternBlock(FabricBlockSettings.of(Material.GLASS).hardness(3.5F).sounds(BlockSoundGroup.LANTERN).lightLevel(15).build()), new Identifier(MOD_ID, "prismarine_lantern"));
        ICE_LANTERN = RegistryUtils.register(new LanternBlock(FabricBlockSettings.of(Material.PACKED_ICE).hardness(3.5F).sounds(BlockSoundGroup.LANTERN).lightLevel(15).build()), new Identifier(MOD_ID, "ice_lantern"));

        /*REDSTONE_IRON_LANTERN = RegistryUtils.register(new RedstoneLanternBlock(), "redstone_iron_lantern");
        REDSTONE_GOLD_LANTERN = RegistryUtils.register(new RedstoneLanternBlock(), "redstone_gold_lantern");*/

        ICE_SAW = RegistryUtils.register(new team.hollow.abnormalib.blocks.BaseModBlock(Material.ICE), new Identifier(MOD_ID, "ice_saw"));
        SAWMILL = RegistryUtils.register(new team.hollow.abnormalib.blocks.BaseModBlock(Material.ICE), new Identifier(MOD_ID, "sawmill"));

        for (WoodType woodType : WoodType.VANILLA_WOODS) {
            if (woodType == WoodType.OAK) continue;
            BOOKSHELVES[woodType.getIndex()] = RegistryUtils.register(new Block(Block.Settings.of(Material.WOOD)), String.format("%s_bookshelf", woodType.asString()), ItemGroup.BUILDING_BLOCKS);
            /*BARRELS[woodType.getIndex()] = RegistryUtils.register(new BarrelBlock(FabricBlockSettings.of(Material.WOOD).hardness(2.5F).sounds(BlockSoundGroup.WOOD).build()),
                    new Identifier(MOD_ID, woodType.asString() + "_barrel"), ItemGroup.DECORATIONS);*/
            LOG_CAMPFIRE[woodType.getIndex()] = RegistryUtils.register(new CampfireBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.SPRUCE).hardness(2.0F)
                    .sounds(BlockSoundGroup.WOOD).lightLevel(15).ticksRandomly().build()), new Identifier(MOD_ID, String.format("%s_campfire", woodType.asString())));
            LECTERNS[woodType.getIndex()] = RegistryUtils.register(new NeutroniaBaseLectern(), new Identifier(MOD_ID, String.format("%s_lectern", woodType.asString())), ItemGroup.REDSTONE);
        }

        for (LadderVariants woodTypes2 : LadderVariants.values()) {
            LADDERS[woodTypes2.getIndex()] = RegistryUtils.register(new CustomLadderBlock(), new Identifier(MOD_ID, woodTypes2.asString() + "_ladder"));
        }

        for (CustomChestTypes woodenChestTypes : CustomChestTypes.values()) {
            WOODEN_CHESTS[woodenChestTypes.getId()] = RegistryUtils.register(new CustomChestBlock(woodenChestTypes.asString()), new Identifier(MOD_ID, woodenChestTypes.asString()));
        }

        PALM_LOG = new NeutroniaPillarBlock(Material.WOOD, "palm_log");
        PALM_WOOD = RegistryUtils.register(new Block(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD)
                .hardness(2.0F).sounds(BlockSoundGroup.WOOD).build()), new Identifier(MOD_ID, "palm_wood"), ItemGroup.BUILDING_BLOCKS);
        PALM_PLANKS = new NeutroniaBaseBlock(Material.WOOD, "palm_planks");
        BlockRegistryBuilder.getInstance("palm", PALM_PLANKS)
                .slab().stair()/*.button(true)*/.fence().fenceGate()/*.pressurePlate(PressurePlateBlock.Type.WOOD)*/;
        PALM_LEAVES = RegistryUtils.register(new NeutroniaLeavesBlock(), new Identifier(MOD_ID, "palm_leaves"));
        PALM_LOG_TOP = new NeutroniaBaseBlock(Material.WOOD, "palm_top_log");
        PALM_SAPLING = RegistryUtils.register(new NeutroniaSaplingBlock(new PalmSaplingGenerator()),
                new Identifier(MOD_ID, "palm_sapling"));
//        COCONUT = new CoconutBlock();

        WILLOW_LOG = new NeutroniaPillarBlock(Material.WOOD, "willow_log");
        WILLOW_WOOD = RegistryUtils.register(new Block(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD)
                .hardness(2.0F).sounds(BlockSoundGroup.WOOD).build()), new Identifier(MOD_ID, "willow_wood"), ItemGroup.BUILDING_BLOCKS);
        WILLOW_PLANKS = new NeutroniaBaseBlock(Material.WOOD, "willow_planks");
        BlockRegistryBuilder.getInstance("willow", WILLOW_PLANKS)
                .slab().stair()/*.button(true)*/.fence().fenceGate()/*.pressurePlate(PressurePlateBlock.Type.WOOD)*/;
        WILLOW_LEAVES = RegistryUtils.register(new NeutroniaLeavesBlock(), new Identifier(MOD_ID, "willow_leaves"));
        WILLOW_SAPLING = RegistryUtils.register(new NeutroniaSaplingBlock(new WillowSaplingGenerator()), new Identifier(MOD_ID, "willow_sapling"));
        WILLOW_UNDERWATER_SAPLING = RegistryUtils.register(new NeutroniaWaterloggedSaplingBlock(new WillowSaplingGenerator()), new Identifier(MOD_ID, "underwater_willow_sapling"));

        GRATE = RegistryUtils.register(new NeutroniaTrapdoorBlock(Material.METAL), "grate");
        IRON_GRATE = RegistryUtils.register(new NeutroniaTrapdoorBlock(Material.METAL), "iron_grate");
        GOLD_GRATE = RegistryUtils.register(new NeutroniaTrapdoorBlock(Material.METAL), "gold_grate");
        WROUGHT_IRON_GRATE = RegistryUtils.register(new NeutroniaTrapdoorBlock(Material.METAL), "wrought_iron_grate");

//        IRON_SCAFFOLDING = RegistryUtils.registerScaffolding(new NeutroniaScaffolding(FabricBlockSettings.of(Material.METAL, MaterialColor.SAND).noCollision().sounds(BlockSoundGroup.SCAFFOLDING).dynamicBounds().getModMenuBadge()), "iron_scaffolding");
//        GOLD_SCAFFOLDING = RegistryUtils.registerScaffolding(new NeutroniaScaffolding(FabricBlockSettings.of(Material.METAL, MaterialColor.SAND).noCollision().sounds(BlockSoundGroup.SCAFFOLDING).dynamicBounds().getModMenuBadge()), "gold_scaffolding");

        SANDSTONE_DOOR = RegistryUtils.register(new NeutroniaDoorBlock(Material.STONE), "sandstone_door", ItemGroup.REDSTONE);
        SANDSTONE_TRAPDOOR = RegistryUtils.register(new NeutroniaTrapdoorBlock(), "sandstone_trapdoor", ItemGroup.REDSTONE);
        RED_SANDSTONE_DOOR = RegistryUtils.register(new NeutroniaDoorBlock(Material.STONE), "red_sandstone_door", ItemGroup.REDSTONE);
        RED_SANDSTONE_TRAPDOOR = RegistryUtils.register(new NeutroniaTrapdoorBlock(), "red_sandstone_trapdoor", ItemGroup.REDSTONE);
        ICE_DOOR = RegistryUtils.register(new NeutroniaDoorBlock(Material.ICE), "ice_door", ItemGroup.REDSTONE);
        ICE_TRAPDOOR = RegistryUtils.register(new NeutroniaTrapdoorBlock(Material.ICE), "ice_trapdoor", ItemGroup.REDSTONE);
        BAMBOO_DOOR = RegistryUtils.register(new NeutroniaDoorBlock(Material.WOOD), "bamboo_door", ItemGroup.REDSTONE);
        BAMBOO_TRAPDOOR = RegistryUtils.register(new NeutroniaTrapdoorBlock(), "bamboo_trapdoor", ItemGroup.REDSTONE);
        PALM_DOOR = RegistryUtils.register(new NeutroniaDoorBlock(Material.WOOD), "palm_door", ItemGroup.REDSTONE);
        PALM_TRAPDOOR = RegistryUtils.register(new NeutroniaTrapdoorBlock(Material.WOOD), "palm_trapdoor", ItemGroup.REDSTONE);
        WILLOW_DOOR = RegistryUtils.register(new NeutroniaDoorBlock(Material.WOOD), "willow_door", ItemGroup.REDSTONE);
        WILLOW_TRAPDOOR = RegistryUtils.register(new NeutroniaTrapdoorBlock(Material.WOOD), "willow_trapdoor", ItemGroup.REDSTONE);
        WROUGHT_IRON_DOOR = RegistryUtils.register(new NeutroniaDoorBlock(Material.METAL), "wrought_iron_door", ItemGroup.REDSTONE);

//        WROUGHT_IRON_BLOCK = RegistryUtils.register(new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.AIR).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).getModMenuBadge()), new Identifier(Neutronia.MOD_ID, "wrought_iron_block"));
        WROUGHT_IRON_BARS = RegistryUtils.register(new NeutroniaPaneBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.AIR).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL)), new Identifier(MOD_ID, "wrought_iron_bars"));

        for (WoodType newWoodTypes : WoodType.MODDED_WOODS) {
            if (newWoodTypes == WoodType.BAMBOO) continue;
            STRIPPED_LOGS[newWoodTypes.getIndex()] = new NeutroniaPillarBlock(Material.WOOD, String.format("stripped_%s_log", newWoodTypes.asString()));
            STRIPPED_WOOD[newWoodTypes.getIndex()] = RegistryUtils.register(new Block(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD)
                    .hardness(2.0F).sounds(BlockSoundGroup.WOOD).build()), new Identifier(MOD_ID, String.format("stripped_%s_wood", newWoodTypes.asString())), ItemGroup.BUILDING_BLOCKS);
//            CARVED_PLANKS[newWoodTypes.getIndex()] = new NeutroniaBaseBlock(Material.WOOD, String.format("carved_%s_planks", newWoodTypes.asString()));
//            STRIPPED_LOG_CAMPFIRE[newWoodTypes.getIndex()] = RegistryUtils.register(new CampfireBaseBlock(), String.format("stripped_%s_campfire", newWoodTypes.asString()));
        }

        for (WoodType woodType : WoodType.values()) {
            if (woodType == WoodType.BAMBOO) continue;
            if (woodType == WoodType.CHERRY) continue;
            if (woodType == WoodType.WILLOW) continue;
            WOOD_LANTERNS[woodType.getIndex()] = new WoodenLanternBlock(woodType);
        }

        for (WoodType woodType : WoodType.VANILLA_WOODS) {
            CARVED_PLANKS[woodType.getIndex()] = new NeutroniaBaseBlock(Material.WOOD, String.format("carved_%s_planks", woodType.asString()));
            PATTERNED_PLANKS[woodType.getIndex()] = new NeutroniaBaseBlock(Material.WOOD, String.format("patterned_%s_planks", woodType.asString()));
//            WOODEN_SCAFFOLDING[woodType.getIndex()] = RegistryUtils.registerScaffolding(new NeutroniaScaffolding(FabricBlockSettings.of(Material.PART, MaterialColor.SAND).noCollision().sounds(BlockSoundGroup.SCAFFOLDING).dynamicBounds().getModMenuBadge()), String.format("%s_scaffolding", woodType.asString()));
        }

        for (GTPVariants color : GTPVariants.values()) {
            GLAZED_TERRACOTTA_PILLAR[color.getId()] = new NeutroniaPillarBlock(Material.STONE, String.format("%s_glazed_terracotta_pillar", color.asString()));
        }

        CHISELED_PRISMARINE = new NeutroniaBaseBlock(Material.STONE, "chiseled_prismarine");
        BlockRegistryBuilder.getInstance("chiseled_prismarine", CHISELED_PRISMARINE).slab().stair().wall();
        CHISELED_PRISMARINE_BRICKS = new NeutroniaBaseBlock(Material.STONE, "chiseled_prismarine_bricks");
        BlockRegistryBuilder.getInstance("chiseled_prismarine_bricks", CHISELED_PRISMARINE_BRICKS).slab().stair().wall();
        CHISELED_DARK_PRISMARINE = new NeutroniaBaseBlock(Material.STONE, "chiseled_dark_prismarine");
        BlockRegistryBuilder.getInstance("chiseled_dark_prismarine", CHISELED_DARK_PRISMARINE).slab().stair().wall();

        CUT_PRISMARINE = new NeutroniaBaseBlock(Material.STONE, "cut_prismarine");
        BlockRegistryBuilder.getInstance("cut_prismarine", CUT_PRISMARINE).slab().stair().wall();
        CUT_PRISMARINE_BRICKS = new NeutroniaBaseBlock(Material.STONE, "cut_prismarine_bricks");
        BlockRegistryBuilder.getInstance("cut_prismarine_bricks", CUT_PRISMARINE_BRICKS).slab().stair().wall();
        CUT_DARK_PRISMARINE = new NeutroniaBaseBlock(Material.STONE, "cut_dark_prismarine");
        BlockRegistryBuilder.getInstance("cut_dark_prismarine", CUT_DARK_PRISMARINE).slab().stair().wall();

        ENGRAVED_PRISMARINE = new NeutroniaBaseBlock(Material.STONE, "engraved_prismarine");
        BlockRegistryBuilder.getInstance("engraved_prismarine", ENGRAVED_PRISMARINE).slab().stair().wall();
        ENGRAVED_PRISMARINE_BRICKS = new NeutroniaBaseBlock(Material.STONE, "engraved_prismarine_bricks");
        BlockRegistryBuilder.getInstance("engraved_prismarine_brick", ENGRAVED_PRISMARINE_BRICKS).slab().stair().wall();
        ENGRAVED_DARK_PRISMARINE = new NeutroniaBaseBlock(Material.STONE, "engraved_dark_prismarine");
        BlockRegistryBuilder.getInstance("engraved_dark_prismarine", ENGRAVED_DARK_PRISMARINE).slab().stair().wall();

        OBSIDIAN_BRICKS = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F), "obsidian_bricks");
        OBSIDIAN_COBBLE = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F), "obsidian_cobble");
        OBSIDIAN_PILLAR = new NeutroniaPillarBlock(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F), "obsidian_pillar");
        CHISELED_OBSIDIAN = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F), "chiseled_obsidian");
        GLOWING_OBSIDIAN = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F), "glowing_obsidian");

        SMOOTH_END_BRICK = new NeutroniaBaseBlock(Material.STONE, "smooth_endstone_bricks", 0.8F, 10.0F);
        SMOOTH_PRISMARINE_BRICKS = new NeutroniaBaseBlock(Material.STONE, "smooth_prismarine_bricks", 1.5F, 10.0F);
        SMOOTH_PRISMARINE = new NeutroniaBaseBlock(Material.STONE, "smooth_prismarine");
        SMOOTH_DARK_PRISMARINE = new NeutroniaBaseBlock(Material.STONE, "smooth_dark_prismarine");
        SMOOTH_OBSIDIAN = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F), "smooth_obsidian");
        SMOOTH_PURPUR_BRICK = new NeutroniaBaseBlock(Material.STONE, "smooth_purpur", 1.5F, 10.0F);
        SMOOTH_NETHER_BRICK = new NeutroniaBaseBlock(Material.STONE, "smooth_nether_bricks", 2.0F, 10.0F);
        SMOOTH_RED_NETHER_BRICK = new NeutroniaBaseBlock(Material.STONE, "smooth_red_nether_bricks", 1.5F, 10.0F);
        SMOOTH_STONE = new NeutroniaBaseBlock(Material.STONE, "polished_stone", 1.5F, 10.0F);
        SMOOTH_STONE_BRICK = new NeutroniaBaseBlock(Material.STONE, "smooth_stone_bricks", 1.5F, 10.0F);

        DIAMOND_BRICKS = new NeutroniaBaseBlock(Material.METAL, "diamond_bricks", 3.0F, 10.0F);
        EMERALD_BRICKS = new NeutroniaBaseBlock(Material.METAL, "emerald_bricks", 3.0F, 10.0F);
        IRON_BRICKS = new NeutroniaBaseBlock(Material.METAL, "iron_bricks", 3.0F, 10.0F);
        GOLD_BRICKS = new NeutroniaBaseBlock(Material.METAL, "gold_bricks", 3.0F, 10.0F);

        DIAMOND_PILLAR = new NeutroniaPillarBlock(Material.METAL, "diamond_pillar", 5.0F, 10.0F);
        EMERALD_PILLAR = new NeutroniaPillarBlock(Material.METAL, "emerald_pillar", 5.0F, 10.0F);
        IRON_PILLAR = new NeutroniaPillarBlock(Material.METAL, "iron_pillar", 5.0F, 10.0F);
        GOLD_PILLAR = new NeutroniaPillarBlock(Material.METAL, "gold_pillar", 3.0F, 10.0F);

        SQUARED_BRICKS = new NeutroniaBaseBlock(Material.STONE, "squared_bricks");
        GAPLESS_BRICKS = new NeutroniaBaseBlock(Material.STONE, "gapless_bricks");
        SCALY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "scaly_bricks");
        CRACKED_BRICKS = new NeutroniaBaseBlock(Material.STONE, "cracked_bricks");
        MOSSY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "mossy_bricks");
        BRICK_PATH = new NeutroniaBaseBlock(Material.STONE, "brick_path");
        BRICK_TILE = new NeutroniaBaseBlock(Material.STONE, "brick_tile");
        SMALL_BRICK_TILE = new NeutroniaBaseBlock(Material.STONE, "small_brick_tile");
        CHISELED_BRICK = new NeutroniaBaseBlock(Material.STONE, "chiseled_bricks");
        BRICK_PILLAR = new NeutroniaPillarBlock(Material.STONE, "brick_pillar", 2.0F, 10.0F);
        SMOOTH_BRICK = new NeutroniaBaseBlock(Material.STONE, "smooth_bricks", 2.0F, 10.0F);

        SQUARED_SANDY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "squared_sandy_bricks");
        GAPLESS_SANDY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "gapless_sandy_bricks");
        SCALY_SANDY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "scaly_sandy_bricks");
        SANDY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "sandy_bricks");
        CHISELED_SANDY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "chiseled_sandy_bricks");
        CRACKED_SANDY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "cracked_sandy_bricks");
        MOSSY_SANDY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "mossy_sandy_bricks");
        SANDY_BRICK_PATH = new NeutroniaBaseBlock(Material.STONE, "sandy_brick_path");
        SANDY_BRICK_TILE = new NeutroniaBaseBlock(Material.STONE, "sandy_brick_tile");
        SMALL_SANDY_BRICK_TILE = new NeutroniaBaseBlock(Material.STONE, "small_sandy_brick_tile");
        SANDY_BRICK_PILLAR = new NeutroniaPillarBlock(Material.STONE, "sandy_brick_pillar", 2.0F, 10.0F);
        SMOOTH_SANDY_BRICK = new NeutroniaBaseBlock(Material.STONE, "smooth_sandy_bricks", 2.0F, 10.0F);

        SQUARED_DIRTY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "squared_dirty_bricks");
        GAPLESS_DIRTY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "gapless_dirty_bricks");
        SCALY_DIRTY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "scaly_dirty_bricks");
        DIRTY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "dirty_bricks");
        CHISELED_DIRTY_BRICK = new NeutroniaBaseBlock(Material.STONE, "chiseled_dirty_bricks");
        CRACKED_DIRTY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "cracked_dirty_bricks");
        MOSSY_DIRTY_BRICKS = new NeutroniaBaseBlock(Material.STONE, "mossy_dirty_bricks");
        DIRTY_BRICK_PATH = new NeutroniaBaseBlock(Material.STONE, "dirty_brick_path");
        DIRTY_BRICK_TILE = new NeutroniaBaseBlock(Material.STONE, "dirty_brick_tile");
        SMALL_DIRTY_BRICK_TILE = new NeutroniaBaseBlock(Material.STONE, "small_dirty_brick_tile");
        DIRTY_BRICK_PILLAR = new NeutroniaPillarBlock(Material.STONE, "dirty_brick_pillar", 2.0F, 10.0F);
        SMOOTH_DIRTY_BRICK = new NeutroniaBaseBlock(Material.STONE, "smooth_dirty_bricks", 2.0F, 10.0F);

        CHECKERED_TILE = new NeutroniaBaseBlock(Material.STONE, "checkered_tiles");
        SMALL_CHECKERED_TILE = new NeutroniaBaseBlock(Material.STONE, "small_checkered_tiles");
        STONE_TILE = new NeutroniaBaseBlock(Material.STONE, "stone_tile");
        SMALL_STONE_TILE = new NeutroniaBaseBlock(Material.STONE, "small_stone_tile");
        ANDESITE_TILE = new NeutroniaBaseBlock(Material.STONE, "andesite_tile");
        SMALL_ANDESITE_TILE = new NeutroniaBaseBlock(Material.STONE, "small_andesite_tile");
        DIORITE_TILE = new NeutroniaBaseBlock(Material.STONE, "diorite_tile");
        SMALL_DIORITE_TILE = new NeutroniaBaseBlock(Material.STONE, "small_diorite_tile");
        GRANITE_TILE = new NeutroniaBaseBlock(Material.STONE, "granite_tile");
        SMALL_GRANITE_TILE = new NeutroniaBaseBlock(Material.STONE, "small_granite_tile");
        STONE_BRICK_TILE = new NeutroniaBaseBlock(Material.STONE, "stone_brick_tile");
        SMALL_STONE_BRICK_TILE = new NeutroniaBaseBlock(Material.STONE, "small_stone_brick_tile");
        SMALL_OBSIDIAN_TILES = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F), "small_obsidian_tiles");
        OBSIDIAN_TILES = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.STONE).hardness(50.0F).resistance(2000.0F), "obsidian_tiles");

        BlockRegistryBuilder.getInstance("stone_tile", STONE_TILE).slab().stair();
        BlockRegistryBuilder.getInstance("andesite_tile", ANDESITE_TILE).slab().stair();
        BlockRegistryBuilder.getInstance("diorite_tile", DIORITE_TILE).slab().stair();
        BlockRegistryBuilder.getInstance("granite_tile", GRANITE_TILE).slab().stair();
        BlockRegistryBuilder.getInstance("stone_brick_tile", STONE_BRICK_TILE).slab().stair();

        BlockRegistryBuilder.getInstance("andesite", Blocks.ANDESITE)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);
        BlockRegistryBuilder.getInstance("granite", Blocks.GRANITE)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);
        BlockRegistryBuilder.getInstance("diorite", Blocks.DIORITE)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);
        BlockRegistryBuilder.getInstance("stone_brick", Blocks.STONE_BRICKS)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);
        BlockRegistryBuilder.getInstance("cracked_stone_brick", Blocks.CRACKED_STONE_BRICKS)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);
        BlockRegistryBuilder.getInstance("mossy_stone_brick", Blocks.MOSSY_STONE_BRICKS)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);
        BlockRegistryBuilder.getInstance("cobblestone", Blocks.COBBLESTONE)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);
        BlockRegistryBuilder.getInstance("mossy_cobblestone", Blocks.MOSSY_COBBLESTONE)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);
        BlockRegistryBuilder.getInstance("polished_andesite", Blocks.STONE)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);
        BlockRegistryBuilder.getInstance("polished_granite", Blocks.STONE)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);
        BlockRegistryBuilder.getInstance("polished_diorite", Blocks.STONE)
                .button(false).pressurePlate(PressurePlateBlock.Type.STONE);

        ROPE_COIL = new NeutroniaPillarBlock(Material.WOOL, "rope_coil");

        DARK_ANDESITE = new NeutroniaBaseBlock(Material.STONE, "dark_andesite");
        DARK_ANDESITE_BRICKS = new NeutroniaBaseBlock(Material.STONE, "dark_andesite_bricks");
        POLISHED_DARK_ANDESITE = new NeutroniaBaseBlock(Material.STONE, "polished_dark_andesite");

        ANDESITE_BRICKS = new NeutroniaBaseBlock(Material.STONE, "andesite_bricks");
        BlockRegistryBuilder.getInstance("andesite_brick", ANDESITE_BRICKS).slab().stair().wall();
        DIORITE_BRICKS = new NeutroniaBaseBlock(Material.STONE, "diorite_bricks");
        BlockRegistryBuilder.getInstance("diorite_brick", DIORITE_BRICKS).slab().stair().wall();
        GRANITE_BRICKS = new NeutroniaBaseBlock(Material.STONE, "granite_bricks");
        BlockRegistryBuilder.getInstance("granite_brick", GRANITE_BRICKS).slab().stair().wall();

        MUD = new MudBlock();
        MUD_BRICKS = new MudBlock("mud_bricks");
        DRIED_MUD = new NeutroniaBaseBlock(Material.ORGANIC, "dried_mud");
        BlockRegistryBuilder.getInstance("dried_mud", DRIED_MUD).slab().stair().wall();
        DRIED_MUD_BRICKS = new NeutroniaBaseBlock(Material.ORGANIC, "dried_mud_bricks");
        BlockRegistryBuilder.getInstance("dried_mud_brick", DRIED_MUD_BRICKS).slab().stair().wall();

        PACKED_ICE_BRICKS = new NeutroniaBaseBlock(Material.PACKED_ICE, "ice_bricks");
        BlockRegistryBuilder.getInstance("ice_bricks", PACKED_ICE_BRICKS).slab().stair();
        PACKED_ICE_PILLAR = new NeutroniaPillarBlock(Material.PACKED_ICE, "ice_pillar");
        ICE_TILES = new NeutroniaBaseBlock(Material.PACKED_ICE, "ice_tiles");
        BlockRegistryBuilder.getInstance("ice_tiles", ICE_TILES).slab().stair();
        SMALL_SNOW_BRICKS = new NeutroniaBaseBlock(Material.PACKED_ICE, "small_snow_bricks");
        SNOW_BRICKS = new NeutroniaBaseBlock(Material.PACKED_ICE, "snow_bricks");
        BlockRegistryBuilder.getInstance("snow_bricks", SNOW_BRICKS).slab().stair();

        SANDSTONE_PILLAR = new NeutroniaPillarBlock(Material.STONE, "sandstone_pillar");
        RED_SANDSTONE_PILLAR = new NeutroniaPillarBlock(Material.STONE, "red_sandstone_pillar");
        STONE_PILLAR = new NeutroniaPillarBlock(Material.STONE, "stone_pillar");
        POLISHED_ANDESITE_PILLAR = new NeutroniaPillarBlock(Material.STONE, "polished_andesite_pillar");
        POLISHED_DIORITE_PILLAR = new NeutroniaPillarBlock(Material.STONE, "polished_diorite_pillar");
        POLISHED_GRANITE_PILLAR = new NeutroniaPillarBlock(Material.STONE, "polished_granite_pillar");
        STONE_BRICK_PILLAR = new NeutroniaPillarBlock(Material.STONE, "stone_brick_pillar");
        PRISMARINE_COLUMN = new NeutroniaPillarBlock(Material.STONE, "prismarine_column");
        PRISMARINE_PILLAR = new NeutroniaPillarBlock(Material.STONE, "prismarine_pillar");
        PRISMARINE_BRICK_PILLAR = new NeutroniaPillarBlock(Material.STONE, "prismarine_brick_pillar");
        DARK_PRISMARINE_PILLAR = new NeutroniaPillarBlock(Material.STONE, "dark_prismarine_pillar");
        RED_NETHER_BRICK_PILLAR = new NeutroniaPillarBlock(Material.STONE, "red_nether_brick_pillar", 2.0F, 10.0F);
        END_BRICK_PILLAR = new NeutroniaPillarBlock(Material.STONE, "end_brick_pillar", 3.0F, 15.0F);
        DARK_PRISMARINE_COLUMN = new NeutroniaPillarBlock(Material.STONE, "dark_prismarine_column", 1.5F, 10.0F);

        FROSTED_GLASS = new FrostedGlassBlock();
        FROSTED_GLASS_PANE = RegistryUtils.register(new FrostedGlassPaneBlock(), new Identifier(MOD_ID, "frosted_glass_pane"));

        CHAIN = RegistryUtils.register(new ChainBlock(), "chain");
        IRON_CHAIN = RegistryUtils.register(new ChainBlock(), "iron_chain");
        ICE_CHAIN = RegistryUtils.register(new ChainBlock(), "ice_chain");
        WROUGHT_IRON_CHAIN = RegistryUtils.register(new ChainBlock(), "wrought_iron_chain");
        GOLD_CHAIN = RegistryUtils.register(new ChainBlock(), "gold_chain");
        PRISMARINE_CHAIN = RegistryUtils.register(new ChainBlock(), "prismarine_chain");

        /*PUMPKIN = CarvedFaceManager.forRegistry("pumpkin", new Identifier("minecraft", "carved_pumpkin"));

        for(Block block : CarvedFaceManager.forRegistry("pumpkin", new Identifier("minecraft", "carved_pumpkin"))) {
            RegistryUtils.register(block, "pumpkin");
        }*/

        for (SoulStoneVariants soulStoneTypes : SoulStoneVariants.values()) {
            SOUL_STONE[soulStoneTypes.getIndex()] = new NeutroniaBaseBlock(Material.STONE, soulStoneTypes.asString());
            BlockRegistryBuilder.getInstance(soulStoneTypes.asString(), SOUL_STONE[soulStoneTypes.getIndex()]).slab().stair().wall();
        }

        BlockRegistryBuilder.getInstance("dark_andesite", DARK_ANDESITE).wall();
        BlockRegistryBuilder.getInstance("polished_dark_andesite", POLISHED_DARK_ANDESITE).wall();
        BlockRegistryBuilder.getInstance("cracked_stone_brick", Blocks.CRACKED_STONE_BRICKS).wall();

        BlockRegistryBuilder.getInstance("smooth_endstone_brick", SMOOTH_END_BRICK).wall();
        BlockRegistryBuilder.getInstance("smooth_prismarine_brick", SMOOTH_PRISMARINE_BRICKS).wall();
        BlockRegistryBuilder.getInstance("smooth_prismarine", SMOOTH_PRISMARINE).wall();
        BlockRegistryBuilder.getInstance("smooth_brick", SMOOTH_BRICK).wall();
        BlockRegistryBuilder.getInstance("smooth_purpur", SMOOTH_PURPUR_BRICK).wall();
        BlockRegistryBuilder.getInstance("smooth_nether_brick", SMOOTH_NETHER_BRICK).wall();
        BlockRegistryBuilder.getInstance("smooth_red_nether_brick", SMOOTH_RED_NETHER_BRICK).wall();
        BlockRegistryBuilder.getInstance("polished_stone", SMOOTH_STONE).wall();
        BlockRegistryBuilder.getInstance("smooth_stone_brick", SMOOTH_STONE_BRICK).wall();
        BlockRegistryBuilder.getInstance("dirty_brick", DIRTY_BRICKS).wall();
        BlockRegistryBuilder.getInstance("sandy_brick", SANDY_BRICKS).wall();
        BlockRegistryBuilder.getInstance("smooth_dirty_brick", SMOOTH_DIRTY_BRICK).wall();
        BlockRegistryBuilder.getInstance("smooth_sandy_brick", SMOOTH_SANDY_BRICK).wall();

        BlockRegistryBuilder.getInstance("dark_andesite", DARK_ANDESITE).slab().stair();
        BlockRegistryBuilder.getInstance("polished_dark_andesite", POLISHED_DARK_ANDESITE).slab().stair();
        BlockRegistryBuilder.getInstance("cracked_stone_brick", Blocks.CRACKED_STONE_BRICKS).slab().stair();

        BlockRegistryBuilder.getInstance("smooth_endstone_brick", SMOOTH_END_BRICK).slab().stair();
        BlockRegistryBuilder.getInstance("smooth_prismarine_brick", SMOOTH_PRISMARINE_BRICKS).slab().stair();
        BlockRegistryBuilder.getInstance("smooth_prismarine", SMOOTH_PRISMARINE).slab().stair();
        BlockRegistryBuilder.getInstance("smooth_brick", SMOOTH_BRICK).slab().stair();
        BlockRegistryBuilder.getInstance("smooth_purpur", SMOOTH_PURPUR_BRICK).slab().stair();
        BlockRegistryBuilder.getInstance("smooth_nether_brick", SMOOTH_NETHER_BRICK).slab().stair();
        BlockRegistryBuilder.getInstance("smooth_red_nether_brick", SMOOTH_RED_NETHER_BRICK).slab().stair();
        BlockRegistryBuilder.getInstance("polished_stone", SMOOTH_STONE).slab().stair();
        BlockRegistryBuilder.getInstance("smooth_stone_brick", SMOOTH_STONE_BRICK).slab().stair();
        BlockRegistryBuilder.getInstance("dirty_brick", DIRTY_BRICKS).slab().stair();
        BlockRegistryBuilder.getInstance("sandy_brick", SANDY_BRICKS).slab().stair();
        BlockRegistryBuilder.getInstance("smooth_dirty_brick", SMOOTH_DIRTY_BRICK).slab().stair();
        BlockRegistryBuilder.getInstance("smooth_sandy_brick", SMOOTH_SANDY_BRICK).slab().stair();

        BAMBOO_PLANKS = new NeutroniaBaseBlock(Material.WOOD, "bamboo_planks");
        BlockRegistryBuilder.getInstance("bamboo", BAMBOO_PLANKS)
                .slab().stair().button(true).fence().fenceGate().pressurePlate(PressurePlateBlock.Type.WOOD);
        BAMBOO_SIGN = RegistryUtils.registerNoBI(new SignBlock(FabricBlockSettings.of(Material.WOOD).noCollision().hardness(1.0F).sounds(BlockSoundGroup.WOOD).build()), "bamboo_sign");
        BAMBOO_WALL_SIGN = RegistryUtils.registerNoBI(new WallSignBlock(FabricBlockSettings.of(Material.WOOD).noCollision().hardness(1.0F).sounds(BlockSoundGroup.WOOD).build()), "bamboo_wall_sign");
        THATCH = new NeutroniaBaseBlock(Material.ORGANIC, "thatch");
        BlockRegistryBuilder.getInstance("thatch", THATCH).slab().stair();

        ACIDIAN = new NeutroniaBaseBlock(Material.STONE, "natural_acidian");
        BlockRegistryBuilder.getInstance("natural_acidian", ACIDIAN)
                .slab().stair().button(true).wall().pressurePlate(PressurePlateBlock.Type.WOOD);
        ACIDIAN_BRICKS = new NeutroniaBaseBlock(Material.STONE, "acidian_bricks");
        BlockRegistryBuilder.getInstance("acidian_brick", ACIDIAN_BRICKS)
                .slab().stair().button(true).wall().pressurePlate(PressurePlateBlock.Type.WOOD);
        ACIDIAN_PILLAR = new NeutroniaPillarBlock(Material.STONE, "acidian_pillar");
        CHISELED_ACIDIAN = new NeutroniaBaseBlock(Material.STONE, "chiseled_acidian");
//        ACIDIAN_BARS = RegistryUtils.register(new NeutroniaPaneBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.AIR).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL)), new Identifier(Neutronia.MOD_ID, "acidian_bars"));

        STICK_BUNDLE = new NeutroniaPillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD), "stick_bundle");
        CHORUS_BUNDLE = new NeutroniaPillarBlock(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.CROP), "chorus_fruit_bundle");
        SUGAR_CANE_BUNDLE = new NeutroniaPillarBlock(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.CROP), "sugar_cane_bundle");
        BAMBOO_BUNDLE = new NeutroniaPillarBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO), "bamboo_bundle");
        UNCUT_BAMBOO_BUNDLE = new NeutroniaPillarBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO), "bamboo_bundle");
        CACTUS_BUNDLE = new CactusBundleBlock();

        NETHER_WART_SACK = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL), "nether_wart_sack");
        COCOA_BEAN_SACK = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL), "cocoa_beans_sack");
        GUNPOWDER_SACK = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL), "gunpowder_sack");

        EGG_CRATE = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD), "egg_crate");
        BEETROOT_CRATE = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD), "beetroot_crate");
        POTATO_CRATE = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD), "potato_crate");
        CARROT_CRATE = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD), "carrot_crate");
        APPLE_CRATE = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD), "apple_crate");
        GOLDEN_APPLE_CRATE = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD), "golden_apple_crate");

        TREATED_PLANKS = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD), "treated_planks");
        BlockRegistryBuilder.getInstance("treated_planks", TREATED_PLANKS)
                .slab().stair().button(true).fence().fenceGate().pressurePlate(PressurePlateBlock.Type.WOOD);
        TREATED_SIDING = new NeutroniaBaseBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD), "treated_siding");
        BlockRegistryBuilder.getInstance("treated_siding", TREATED_SIDING)
                .slab().stair().button(true).fence().fenceGate().pressurePlate(PressurePlateBlock.Type.WOOD);

        POTTED_BEETROOT = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.BEETROOTS), new Identifier(MOD_ID, "potted_beetroot"));
        POTTED_CARROTS = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.CARROTS), new Identifier(MOD_ID, "potted_carrots"));
        POTTED_CHORUS = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.CHORUS_FLOWER), new Identifier(MOD_ID, "potted_chorus"));
        POTTED_GRASS = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.GRASS), new Identifier(MOD_ID, "potted_grass"));
        POTTED_LILAC = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.LILAC), new Identifier(MOD_ID, "potted_lilac"));
        POTTED_MELON = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.MELON), new Identifier(MOD_ID, "potted_melon"));
        POTTED_NETHER_WART = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.NETHER_WART), new Identifier(MOD_ID, "potted_nether_wart"));
        POTTED_PEONY = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.PEONY), new Identifier(MOD_ID, "potted_peony"));
        POTTED_POTATOES = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.POTATOES), new Identifier(MOD_ID, "potted_potatoes"));
        POTTED_PUMPKIN = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.PUMPKIN), new Identifier(MOD_ID, "potted_pumpkin"));
        POTTED_ROSE_BUSH = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.ROSE_BUSH), new Identifier(MOD_ID, "potted_rose_bush"));
        POTTED_SUGAR_CANE = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.SUGAR_CANE), new Identifier(MOD_ID, "potted_sugar_cane"));
        POTTED_SUNFLOWER = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.SUNFLOWER), new Identifier(MOD_ID, "potted_sunflower"));
        POTTED_TALL_GRASS = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.TALL_GRASS), new Identifier(MOD_ID, "potted_tall_grass"));
        POTTED_LARGE_FERN = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.LARGE_FERN), new Identifier(MOD_ID, "potted_large_fern"));
        POTTED_WHEAT = RegistryUtils.registerNoBI(new FlowerPotBaseBlock(Blocks.WHEAT), new Identifier(MOD_ID, "potted_wheat"));
    }


}