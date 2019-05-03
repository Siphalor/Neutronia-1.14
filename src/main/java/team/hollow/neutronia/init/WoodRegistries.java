package team.hollow.neutronia.init;

import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.blocks.sapling.*;
import team.hollow.neutronia.utils.registry.WoodRegistry;

import static team.hollow.neutronia.Neutronia.MOD_ID;

public class WoodRegistries {

    public static WoodRegistry PALM;
    public static WoodRegistry MANGROVE;
    public static WoodRegistry RED_MANGROVE;
    public static WoodRegistry BAOBAB;
    public static WoodRegistry WENGE;
    public static WoodRegistry PURPLEHEART;
    public static WoodRegistry LACEWOOD;
    public static WoodRegistry CHERRY;
    public static WoodRegistry BOLIVIAN_ROSEWOOD;
    public static WoodRegistry GABON_EBONY;
    public static WoodRegistry REDWOOD;

    public static WoodRegistry ACACIA;
    public static WoodRegistry BIRCH;
    public static WoodRegistry DARK_OAK;
    public static WoodRegistry JUNGLE;
    public static WoodRegistry OAK;
    public static WoodRegistry SPRUCE;

    static {
        PALM = new WoodRegistry.Builder(new Identifier(MOD_ID, "palm"), new PalmSaplingGenerator())
                .planks().log().wood().leaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().paperLantern()/*.chest().bookshelf()*/.build();

        MANGROVE = new WoodRegistry.Builder(new Identifier(MOD_ID, "mangrove"), new MangroveSaplingGenerator())
                .planks().log().wood().leaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().paperLantern().ladder().chest().bookshelf().build();

        RED_MANGROVE = new WoodRegistry.Builder(new Identifier(MOD_ID, "red_mangrove"), new MangroveSaplingGenerator())
                .planks().log().wood().leaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().paperLantern().ladder().chest()
                .bookshelf().build();

        BAOBAB = new WoodRegistry.Builder(new Identifier(MOD_ID, "baobab"), new BaobabSaplingGenerator())
                .planks().log().wood().leaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().paperLantern().ladder().chest()
                .bookshelf().build();

        WENGE = new WoodRegistry.Builder(new Identifier(MOD_ID, "wenge"), new OakSaplingGenerator())
                .planks().log().wood().leaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().paperLantern().ladder().chest()
                .bookshelf().build();

        PURPLEHEART = new WoodRegistry.Builder(new Identifier(MOD_ID, "purpleheart"), new OakSaplingGenerator())
                .planks().log().wood().leaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().paperLantern().chest().bookshelf().build();

        LACEWOOD = new WoodRegistry.Builder(new Identifier(MOD_ID, "lacewood"), new OakSaplingGenerator())
                .planks().log().wood().coloredLeaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().paperLantern().chest().bookshelf().build();

        CHERRY = new WoodRegistry.Builder(new Identifier(MOD_ID, "cherry"), new CherrySaplingGenerator())
                .planks().log().wood().leaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().bookshelf().build();

        BOLIVIAN_ROSEWOOD = new WoodRegistry.Builder(new Identifier(MOD_ID, "bolivian_rosewood"), new OakSaplingGenerator())
                .planks().log().wood().coloredLeaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().paperLantern().chest().bookshelf().build();

        GABON_EBONY = new WoodRegistry.Builder(new Identifier(MOD_ID, "gabon_ebony"), new OakSaplingGenerator())
                .planks().log().wood().leaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().ladder().chest().bookshelf().build();

        REDWOOD = new WoodRegistry.Builder(new Identifier(MOD_ID, "redwood"), new RedwoodSaplingGenerator())
                .planks().log().wood().coloredLeaves().sapling().fence().fenceGate().slab().stairs()
                .strippedLog().strippedWood().door().trapdoor().paperLantern().ladder().chest()
                .bookshelf().build();

        ACACIA = new WoodRegistry.Builder(new Identifier(MOD_ID, "acacia"), Blocks.ACACIA_PLANKS)
                .chest().bookshelf().paperLantern().patternedPlanks().carvedPlanks()
                .logCampfire().strippedLogCampfire().siding().post().corner().build();

        BIRCH = new WoodRegistry.Builder(new Identifier(MOD_ID, "birch"), Blocks.BIRCH_PLANKS)
                .chest().bookshelf().paperLantern().patternedPlanks().carvedPlanks()
                .logCampfire().strippedLogCampfire().siding().post().corner().build();

        DARK_OAK = new WoodRegistry.Builder(new Identifier(MOD_ID, "dark_oak"), Blocks.DARK_OAK_PLANKS)
                .chest().bookshelf().paperLantern().patternedPlanks().carvedPlanks()
                .logCampfire().strippedLogCampfire().siding().post().corner().build();

        JUNGLE = new WoodRegistry.Builder(new Identifier(MOD_ID, "jungle"), Blocks.JUNGLE_PLANKS)
                .chest().bookshelf().paperLantern().patternedPlanks().carvedPlanks()
                .logCampfire().strippedLogCampfire().siding().post().corner().build();

        OAK = new WoodRegistry.Builder(new Identifier(MOD_ID, "oak"), Blocks.OAK_PLANKS)
                .paperLantern().patternedPlanks().carvedPlanks().strippedLogCampfire()
                .siding().post().corner().build();

        SPRUCE = new WoodRegistry.Builder(new Identifier(MOD_ID, "spruce"), Blocks.SPRUCE_PLANKS)
                .chest().bookshelf().paperLantern().patternedPlanks().carvedPlanks()
                .logCampfire().strippedLogCampfire().siding().post().corner().build();
    }

}
