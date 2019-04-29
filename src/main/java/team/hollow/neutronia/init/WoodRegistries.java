package team.hollow.neutronia.init;

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

        WENGE = new WoodRegistry.Builder(new Identifier(MOD_ID, "wenge"))
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
    }

}
