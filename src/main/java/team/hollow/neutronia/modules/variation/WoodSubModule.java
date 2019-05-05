package team.hollow.neutronia.modules.variation;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Pair;
import team.hollow.module_api.api.SubModule;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.module_api.api.features.woodtype.WoodTypeBlockFeature;
import team.hollow.module_api.api.features.woodtype.WoodTypeBlocksFeature;
import team.hollow.module_api.api.features.woodtype.WoodTypeFeature;
import team.hollow.neutronia.blocks.CustomLadderBlock;
import team.hollow.neutronia.blocks.NeutroniaBaseLectern;
import team.hollow.neutronia.blocks.NeutroniaBookshelfBlock;
import team.hollow.neutronia.modules.variation.wood.BambooWoodFeature;
import team.hollow.neutronia.modules.variation.wood.TreatedWoodFeature;
import team.hollow.neutronia.registry.ContentBuilder;

import java.util.Collections;
import java.util.function.Supplier;

public class WoodSubModule extends SubModule {
	public static BambooWoodFeature bamboo;
	public static TreatedWoodFeature treatedWood;

	public static WoodTypeBlockFeature bookshelves;
	public static OptionalFeature barrels;
	public static WoodTypeBlockFeature campfires;
	public static WoodTypeBlockFeature strippedCampfires;
	public static WoodTypeBlockFeature lecterns;
	public static WoodTypeBlockFeature ladders;
	// TODO: refactor custom chests to be more dynamic
	//public static ChestFeature chests;
	public static WoodTypeBlocksFeature patternedPlanks;

	public WoodSubModule() {
		super("wood", "Contains wood related block variations");

		Supplier<Block> simpleWoodenBlockSupplier = () -> new Block(Block.Settings.of(Material.WOOD));
		Block.Settings campFireSettings = FabricBlockSettings.of(Material.WOOD, MaterialColor.SPRUCE)
			.hardness(2.0F)
			.sounds(BlockSoundGroup.WOOD)
			.lightLevel(15)
			.ticksRandomly()
			.build();

		bamboo = register(new BambooWoodFeature());
		treatedWood = register(new TreatedWoodFeature());

		bookshelves = register(new WoodTypeBlockFeature("bookshelf", "Adds more bookshelves", WoodTypeFeature.SKIP_OAK, (woodType) -> {
			ContentBuilder.getInstance().asBaseBlock(woodType.getBaseBlock(), woodType.getBaseBlockIdentifier());
			return new NeutroniaBookshelfBlock(Block.Settings.copy(Blocks.BOOKSHELF));
		}));
		barrels = register(new WoodTypeBlockFeature("barrel", "Adds more barrels", WoodTypeFeature.SKIP_OAK,
			woodType -> new BarrelBlock(Block.Settings.copy(Blocks.BARREL))
		));
		campfires = register(new WoodTypeBlockFeature("campfire", "Adds more campfires", WoodTypeFeature.SKIP_OAK,
			woodType -> new CampfireBlock(campFireSettings)
		));
		strippedCampfires = register(new WoodTypeBlockFeature("stripped_campfire", "Adds stripped log campfires", Collections.emptySet(),
			woodType -> new CampfireBlock(campFireSettings)
		));
		lecterns = register(new WoodTypeBlockFeature("lectern", "Adds more lecterns", WoodTypeFeature.SKIP_OAK,
			woodType -> new NeutroniaBaseLectern()
		));
		ladders = register(new WoodTypeBlockFeature("ladder", "Adds more ladders", WoodTypeFeature.SKIP_OAK,
			woodType -> new CustomLadderBlock()
		));
		//chests = register(new ChestFeature());
		patternedPlanks = register(new WoodTypeBlocksFeature("patterned-planks", "Adds patterned and carved planks variations",
				Collections.emptySet(),
				"planks",
				new Pair<>("patterned", simpleWoodenBlockSupplier),
				new Pair<>("carved", simpleWoodenBlockSupplier)
		));
	}
}
