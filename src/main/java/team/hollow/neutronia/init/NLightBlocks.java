package team.hollow.neutronia.init;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.NeutroniaLightBlock;
import team.hollow.neutronia.blocks.NeutroniaLightRotatableBlock;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class NLightBlocks {

    public static final Block ILLUMINATED_ACACIA_LOG;
    public static final Block ILLUMINATED_BIRCH_LOG;
    public static final Block ILLUMINATED_DARK_OAK_LOG;
    public static final Block ILLUMINATED_JUNGLE_LOG;
    public static final Block ILLUMINATED_OAK_LOG;
    public static final Block ILLUMINATED_SPRUCE_LOG;

    public static final Block ILLUMINATED_CHISELED_QUARTZ_BLOCK;
    public static final Block ILLUMINATED_DARK_PRISMARINE;
    public static final Block ILLUMINATED_END_STONE_BRICKS;
    public static final Block ILLUMINATED_PRISMARINE_BRICKS;
    public static final Block ILLUMINATED_PURPUR_BLOCK;

    public static final Block ILLUMINATED_BLACK_CONCRETE;
    public static final Block ILLUMINATED_BLUE_CONCRETE;
    public static final Block ILLUMINATED_BROWN_CONCRETE;
    public static final Block ILLUMINATED_CYAN_CONCRETE;
    public static final Block ILLUMINATED_GRAY_CONCRETE;
    public static final Block ILLUMINATED_GREEN_CONCRETE;
    public static final Block ILLUMINATED_LIGHT_BLUE_CONCRETE;
    public static final Block ILLUMINATED_LIGHT_GRAY_CONCRETE;
    public static final Block ILLUMINATED_LIME_CONCRETE;
    public static final Block ILLUMINATED_MAGENTA_CONCRETE;
    public static final Block ILLUMINATED_ORANGE_CONCRETE;
    public static final Block ILLUMINATED_PINK_CONCRETE;
    public static final Block ILLUMINATED_PURPLE_CONCRETE;
    public static final Block ILLUMINATED_RED_CONCRETE;
    public static final Block ILLUMINATED_WHITE_CONCRETE;
    public static final Block ILLUMINATED_YELLOW_CONCRETE;

    public static final Block ILLUMINATED_CHISELED_RED_SANDSTONE;
    public static final Block ILLUMINATED_CHISELED_SANDSTONE;
    public static final Block ILLUMINATED_CHISELED_STONE_BRICKS;
    public static final Block ILLUMINATED_CRACKED_STONE_BRICKS;
    public static final Block ILLUMINATED_CUT_RED_SANDSTONE;
    public static final Block ILLUMINATED_CUT_SANDSTONE;
    public static final Block ILLUMINATED_MOSSY_STONE_BRICKS;
    public static final Block ILLUMINATED_NETHER_BRICKS;
    public static final Block ILLUMINATED_POLISHED_ANDESITE;
    public static final Block ILLUMINATED_POLISHED_DIORITE;
    public static final Block ILLUMINATED_POLISHED_GRANITE;
    public static final Block ILLUMINATED_RED_NETHER_BRICKS;
    public static final Block ILLUMINATED_RED_SANDSTONE;
    public static final Block ILLUMINATED_SANDSTONE;
    public static final Block ILLUMINATED_SMOOTH_STONE;
    public static final Block ILLUMINATED_STONE_BRICKS;

    static {
        //Wood Blocks
        ILLUMINATED_ACACIA_LOG = register(new NeutroniaLightRotatableBlock(Material.WOOD, 15, 2.0F, 3.0F, BlockSoundGroup.WOOD), "illuminated_acacia_log");
        ILLUMINATED_BIRCH_LOG = register(new NeutroniaLightRotatableBlock(Material.WOOD, 15, 2.0F, 3.0F, BlockSoundGroup.WOOD), "illuminated_birch_log");
        ILLUMINATED_DARK_OAK_LOG = register(new NeutroniaLightRotatableBlock(Material.WOOD, 15, 2.0F, 3.0F, BlockSoundGroup.WOOD), "illuminated_dark_oak_log");
        ILLUMINATED_JUNGLE_LOG = register(new NeutroniaLightRotatableBlock(Material.WOOD, 15, 2.0F, 3.0F, BlockSoundGroup.WOOD), "illuminated_jungle_log");
        ILLUMINATED_OAK_LOG = register(new NeutroniaLightRotatableBlock(Material.WOOD, 15, 2.0F, 3.0F, BlockSoundGroup.WOOD), "illuminated_oak_log");
        ILLUMINATED_SPRUCE_LOG = register(new NeutroniaLightRotatableBlock(Material.WOOD, 15, 2.0F, 3.0F, BlockSoundGroup.WOOD), "illuminated_spruce_log");

        //Bonus Blocks
        ILLUMINATED_CHISELED_QUARTZ_BLOCK = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_chiseled_quartz_block");
        ILLUMINATED_DARK_PRISMARINE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_dark_prismarine");
        ILLUMINATED_END_STONE_BRICKS = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_end_stone_bricks");
        ILLUMINATED_PRISMARINE_BRICKS = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_prismarine_bricks");
        ILLUMINATED_PURPUR_BLOCK = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_purpur_block");

        //Concrete
        ILLUMINATED_BLACK_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_black_concrete");
        ILLUMINATED_BLUE_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_blue_concrete");
        ILLUMINATED_BROWN_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_brown_concrete");
        ILLUMINATED_CYAN_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_cyan_concrete");
        ILLUMINATED_GRAY_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_gray_concrete");
        ILLUMINATED_GREEN_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_green_concrete");
        ILLUMINATED_LIGHT_BLUE_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_light_blue_concrete");
        ILLUMINATED_LIGHT_GRAY_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_light_gray_concrete");
        ILLUMINATED_LIME_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_lime_concrete");
        ILLUMINATED_MAGENTA_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_magenta_concrete");
        ILLUMINATED_ORANGE_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_orange_concrete");
        ILLUMINATED_PINK_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_pink_concrete");
        ILLUMINATED_PURPLE_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_purple_concrete");
        ILLUMINATED_RED_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_red_concrete");
        ILLUMINATED_WHITE_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_white_concrete");
        ILLUMINATED_YELLOW_CONCRETE = register(new NeutroniaLightBlock(Material.STONE, 7, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_yellow_concrete");

        //Stone blocks
        ILLUMINATED_CHISELED_RED_SANDSTONE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_chiseled_red_sandstone");
        ILLUMINATED_CHISELED_SANDSTONE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_chiseled_sandstone");
        ILLUMINATED_CHISELED_STONE_BRICKS = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_chiseled_stone_bricks");
        ILLUMINATED_CRACKED_STONE_BRICKS = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_cracked_stone_bricks");
        ILLUMINATED_CUT_RED_SANDSTONE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_cut_red_sandstone");
        ILLUMINATED_CUT_SANDSTONE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_cut_sandstone");
        ILLUMINATED_MOSSY_STONE_BRICKS = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_mossy_stone_bricks");
        ILLUMINATED_NETHER_BRICKS = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_nether_bricks");
        ILLUMINATED_POLISHED_ANDESITE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_polished_andesite");
        ILLUMINATED_POLISHED_DIORITE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_polished_diorite");
        ILLUMINATED_POLISHED_GRANITE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_polished_granite");
        ILLUMINATED_RED_NETHER_BRICKS = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_red_nether_bricks");
        ILLUMINATED_RED_SANDSTONE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_red_sandstone");
        ILLUMINATED_SANDSTONE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_sandstone");
        ILLUMINATED_SMOOTH_STONE = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_smooth_stone");
        ILLUMINATED_STONE_BRICKS = register(new NeutroniaLightBlock(Material.STONE, 15, 2.0F, 3.0F, BlockSoundGroup.STONE), "illuminated_stone_bricks");
    }

    public static Block register(Block block, String name) {
        return RegistryUtils.register(block, new Identifier(Neutronia.MOD_ID, name));
    }

}
