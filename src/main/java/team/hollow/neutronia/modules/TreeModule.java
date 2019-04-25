package team.hollow.neutronia.modules;

import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.StringTextComponent;
import team.hollow.module_api.api.Module;
import team.hollow.neutronia.blocks.sapling.BaobabSaplingGenerator;
import team.hollow.neutronia.blocks.sapling.MangroveSaplingGenerator;
import team.hollow.neutronia.blocks.sapling.PalmSaplingGenerator;
import team.hollow.neutronia.blocks.sapling.WillowSaplingGenerator;
import team.hollow.neutronia.modules.tree.ColoredLeavesTreeFeature;
import team.hollow.neutronia.modules.tree.PalmTreeFeature;
import team.hollow.neutronia.modules.tree.TreeFeature;
import team.hollow.neutronia.modules.tree.WaterloggableTreeFeature;

@SuppressWarnings("WeakerAccess")
public class TreeModule extends Module {
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

	public TreeModule() {
		super("tree_module", "This module enables you to disable certain trees and their wood types");

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
