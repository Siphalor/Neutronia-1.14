package team.hollow.neutronia.modules.exploration;

import net.minecraft.block.sapling.OakSaplingGenerator;
import team.hollow.module_api.api.SubModule;
import team.hollow.neutronia.blocks.sapling.BaobabSaplingGenerator;
import team.hollow.neutronia.blocks.sapling.MangroveSaplingGenerator;
import team.hollow.neutronia.blocks.sapling.PalmSaplingGenerator;
import team.hollow.neutronia.blocks.sapling.WillowSaplingGenerator;
import team.hollow.neutronia.modules.exploration.trees.ColoredLeavesTreeFeature;
import team.hollow.neutronia.modules.exploration.trees.PalmTreeFeature;
import team.hollow.neutronia.modules.exploration.trees.TreeFeature;
import team.hollow.neutronia.modules.exploration.trees.WaterloggableTreeFeature;

public class TreesSubModule extends SubModule {
	public static TreeFeature mangrove;
	public static TreeFeature red_mangrove;
	public static TreeFeature baobab;
	public static TreeFeature wenge;
	public static TreeFeature purpleheart;
	public static TreeFeature lacewood;
	public static TreeFeature cherry;
	public static TreeFeature bolivianRosewood;
	public static TreeFeature gabonEbony;

	public static WaterloggableTreeFeature willow;
	public static PalmTreeFeature palm;

	public TreesSubModule() {
		super("trees", "This submodule defines new trees and wood types");

		mangrove = register(new TreeFeature("mangrove", new MangroveSaplingGenerator()));
		red_mangrove = register(new TreeFeature("red_mangrove", new MangroveSaplingGenerator()));
		baobab = register(new TreeFeature("baobab", new BaobabSaplingGenerator()));
		wenge = register(new TreeFeature("wenge", new OakSaplingGenerator()));
		purpleheart = register(new TreeFeature("purpleheart", new OakSaplingGenerator()));
		lacewood = register(new TreeFeature("lacewood", new OakSaplingGenerator()));
		cherry = register(new TreeFeature("cherry", new OakSaplingGenerator()));

		bolivianRosewood = register(new ColoredLeavesTreeFeature("bolivian_rosewood", new OakSaplingGenerator()));
		gabonEbony = register(new ColoredLeavesTreeFeature("gabon_ebony", new OakSaplingGenerator()));
		willow = new WaterloggableTreeFeature("willow", new WillowSaplingGenerator());
		palm = new PalmTreeFeature("palm", new PalmSaplingGenerator());
	}
}
