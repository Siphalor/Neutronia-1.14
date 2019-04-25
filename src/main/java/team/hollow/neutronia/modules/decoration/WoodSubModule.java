package team.hollow.neutronia.modules.decoration;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Pair;
import team.hollow.module_api.api.SubModule;
import team.hollow.neutronia.blocks.CustomLadderBlock;
import team.hollow.neutronia.blocks.NeutroniaBaseLectern;

import java.util.function.Supplier;

public class WoodSubModule extends SubModule {
	public static WoodTypeBlockFeature bookshelves;
	public static WoodTypeBlockFeature barrels;
	public static WoodTypeBlockFeature campfires;
	public static WoodTypeBlockFeature strippedCampfires;
	public static WoodTypeBlockFeature lecterns;
	public static WoodTypeBlockFeature ladders;
	// TODO: refactor custom chests to be more dynamic
	//public static ChestFeature chests;
	public static PlanksVariationsFeature patternedPlanks;

	public WoodSubModule() {
		super("wood", "Contains wood related block variations");

		Supplier<Block> simpleWoodenBlockSupplier = () -> new Block(Block.Settings.of(Material.WOOD));
		Block.Settings campFireSettings = FabricBlockSettings.of(Material.WOOD, MaterialColor.SPRUCE)
			.hardness(2.0F)
			.sounds(BlockSoundGroup.WOOD)
			.lightLevel(15)
			.ticksRandomly()
			.build();

		bookshelves = register(new WoodTypeBlockFeature("bookshelf", "bookshelves", simpleWoodenBlockSupplier));
		barrels = register(new WoodTypeBlockFeature("barrel", "barrels", () -> new BarrelBlock(FabricBlockSettings.of(Material.WOOD).hardness(2.5F).sounds(BlockSoundGroup.WOOD).build())));
		campfires = register(new WoodTypeBlockFeature("campfire", "campfires", () -> new CampfireBlock(campFireSettings)));
		strippedCampfires = register(new WoodTypeBlockFeature("stripped_campfire", "stripped campfires", () -> new CampfireBlock(campFireSettings)));
		lecterns = register(new WoodTypeBlockFeature("lectern", "lecterns", NeutroniaBaseLectern::new));
		ladders = register(new WoodTypeBlockFeature("ladder", "ladders", CustomLadderBlock::new));
		//chests = register(new ChestFeature());
		patternedPlanks = register(new PlanksVariationsFeature("patterned-planks", "Adds patterned and carved planks variations",
			new Pair<>("patterned", simpleWoodenBlockSupplier),
			new Pair<>("carved", simpleWoodenBlockSupplier)
		));
	}

	public void addModdedWoodBlocks(String woodName) {
		bookshelves.applyForModdedWood(woodName);
		barrels.applyForModdedWood(woodName);
		campfires.applyForModdedWood(woodName);
		lecterns.applyForModdedWood(woodName);
		ladders.applyForModdedWood(woodName);
		//chests.applyForModdedWood(woodName);
		patternedPlanks.applyForModdedWood(woodName);
	}
}
