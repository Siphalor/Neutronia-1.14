package team.hollow.neutronia.modules.variation;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Pair;
import team.hollow.abnormalib.modules.api.SubModule;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.abnormalib.modules.api.features.woodtype.WoodTypeFeature;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.abnormalib.utils.registry.WoodType;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.CustomLadderBlock;
import team.hollow.neutronia.blocks.NeutroniaBaseLectern;
import team.hollow.neutronia.blocks.NeutroniaBookshelfBlock;
import team.hollow.neutronia.modules.features.WoodTypeBlockFeature;
import team.hollow.neutronia.modules.features.WoodTypeBlocksFeature;
import team.hollow.neutronia.modules.variation.wood.BambooWoodFeature;
import team.hollow.neutronia.modules.variation.wood.ChestFeature;
import team.hollow.neutronia.modules.variation.wood.TreatedWoodFeature;

import java.util.Collections;
import java.util.function.Function;

public class WoodSubModule extends SubModule {
	public static BambooWoodFeature bamboo;
	public static TreatedWoodFeature treatedWood;

	public static WoodTypeFeature bookshelves;
	public static OptionalFeature barrels;
	public static WoodTypeBlockFeature campfires;
	public static WoodTypeBlockFeature strippedCampfires;
	public static WoodTypeBlockFeature lecterns;
	public static WoodTypeBlockFeature ladders;

	public static ChestFeature chests;

	public static WoodTypeBlocksFeature patternedPlanks;

	public WoodSubModule() {
		super("wood", "Contains wood related block variations");

		ContentBuilder contentBuilder = Neutronia.getContentBuilder();

		Function<WoodType, Block> simpleWoodenBlockFunction = (woodType) -> new Block(Block.Settings.of(Material.WOOD));
		Block.Settings campFireSettings = FabricBlockSettings.of(Material.WOOD, MaterialColor.SPRUCE)
			.hardness(2.0F)
			.sounds(BlockSoundGroup.WOOD)
			.lightLevel(15)
			.ticksRandomly()
			.build();

		bamboo = register(new BambooWoodFeature());
		treatedWood = register(new TreatedWoodFeature());

		bookshelves = register(new WoodTypeFeature("bookshelves", "Adds more bookshelves", WoodTypeFeature.SKIP_OAK) {
			@Override
			protected void process(WoodType woodType) {
				contentBuilder.asBaseBlock(woodType.getBaseBlock(), woodType.getIdentifier());
				contentBuilder.newBlock(woodType.getIdentifier().getPath() + "_bookshelf", new NeutroniaBookshelfBlock(Block.Settings.copy(Blocks.BOOKSHELF)));
			}
		});
		barrels = register(new WoodTypeFeature("barrels", "Adds more barrels", WoodTypeFeature.SKIP_OAK) {
			@Override
			protected void process(WoodType woodType) {
				contentBuilder.asBaseBlock(woodType.getBaseBlock(), woodType.getIdentifier());
				contentBuilder.barrel();
			}
		});
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

		chests = register(new ChestFeature());
		
		patternedPlanks = register(new WoodTypeBlocksFeature("patterned-planks", "Adds patterned and carved planks variations",
				Collections.emptySet(),
				"planks",
				new Pair<>("patterned", simpleWoodenBlockFunction),
				new Pair<>("carved", simpleWoodenBlockFunction)
		));
	}
}
