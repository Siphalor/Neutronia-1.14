package team.hollow.neutronia.modules.villagesandvillagers;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import team.hollow.module_api.api.SubModule;
import team.hollow.neutronia.village.TradeOfferFactories.BuyItemFactory;
import team.hollow.neutronia.village.TradeOfferFactories.SellItemFactory;

import java.util.Collections;
import java.util.Map;

public class VillagerProfessionsSubModule extends SubModule {
	public VillagerProfessionFeature artist;
	public VillagerProfessionFeature receptionist;
	public VillagerProfessionFeature carpenter;
	public VillagerProfessionFeature guard;
	public VillagerProfessionFeature viking;
	public VillagerProfessionFeature bard;
	public VillagerProfessionFeature druid;
	public VillagerProfessionFeature archer;
	public VillagerProfessionFeature enchanter;
	public VillagerProfessionFeature wizard;

	public VillagerProfessionsSubModule() {
		super("professions", "Adds new villager professions");

		artist = register(new VillagerProfessionFeature("artist", "Adds an artist villager", null, Collections.singleton(Blocks.LOOM.getDefaultState()), this::createArtistRecipes));
		/*receptionist = register(new VillagerProfessionFeature("receptionists", "Adds a receptionist villager", null, Collections.singleton(Blocks.CHEST.getDefaultState())));
		carpenter = register(new VillagerProfessionFeature("carpenter", "Adds a carpenter villager", null, Collections.singleton(Blocks.CRAFTING_TABLE.getDefaultState())));
		guard = register(new VillagerProfessionFeature("guard", "Adds a guard villager", null, Collections.singleton(Blocks.CRAFTING_TABLE.getDefaultState())));
		viking = register(new VillagerProfessionFeature("viking", "Adds a viking villager", null, Collections.singleton(Blocks.CRAFTING_TABLE.getDefaultState())));
		bard = register(new VillagerProfessionFeature("bard", "Adds a bard villager", null, Collections.singleton(Blocks.CRAFTING_TABLE.getDefaultState())));
		druid = register(new VillagerProfessionFeature("druid", "Adds a druid villager", null, Collections.singleton(Blocks.CRAFTING_TABLE.getDefaultState())));
		archer = register(new VillagerProfessionFeature("archer", "Adds an archer villager", null, Collections.singleton(Blocks.CRAFTING_TABLE.getDefaultState())));
		enchanter = register(new VillagerProfessionFeature("enchanter", "Adds an enchanter villager", null, Collections.singleton(Blocks.CRAFTING_TABLE.getDefaultState())));
		wizard = register(new VillagerProfessionFeature("wizard", "Adds a wizard villager", null, Collections.singleton(Blocks.CRAFTING_TABLE.getDefaultState())));*/
	}

	private Map<Integer, TradeOffers.Factory[]> createArtistRecipes() {
		return ImmutableMap.of(
			1, new TradeOffers.Factory[]{
				new BuyItemFactory(Items.RED_DYE, 18, 22, 2),
				new BuyItemFactory(Items.GREEN_DYE, 18, 22, 2),
				new BuyItemFactory(Items.PURPLE_DYE, 18, 22, 2),
				new BuyItemFactory(Items.CYAN_DYE, 18, 22, 2),
				new BuyItemFactory(Items.LIGHT_GRAY_DYE, 18, 22, 2),
				new BuyItemFactory(Items.GRAY_DYE, 18, 22, 2),
				new BuyItemFactory(Items.PINK_DYE, 18, 22, 2),
				new BuyItemFactory(Items.LIME_DYE, 18, 22, 2),
				new BuyItemFactory(Items.YELLOW_DYE, 18, 22, 2),
				new BuyItemFactory(Items.LIGHT_BLUE_DYE, 18, 22, 2),
				new BuyItemFactory(Items.MAGENTA_DYE, 18, 22, 2),
				new BuyItemFactory(Items.ORANGE_DYE, 18, 22, 2),
				new BuyItemFactory(Items.BLUE_DYE, 18, 22, 2),
				new BuyItemFactory(Items.BROWN_DYE, 18, 22, 2),
				new BuyItemFactory(Items.BLACK_DYE, 18, 22, 2),
				new BuyItemFactory(Items.WHITE_DYE, 18, 22, 2),
			},
			2, new TradeOffers.Factory[]{
				new SellItemFactory(Items.WHITE_BANNER, 3, 1, 15),
				new SellItemFactory(Items.BLUE_BANNER, 3, 1, 15),
				new SellItemFactory(Items.LIGHT_BLUE_BANNER, 3, 1, 15),
				new SellItemFactory(Items.RED_BANNER, 3, 1, 15),
				new SellItemFactory(Items.PINK_BANNER, 3, 1, 15),
				new SellItemFactory(Items.GREEN_BANNER, 3, 1, 15),
				new SellItemFactory(Items.LIME_BANNER, 3, 1, 15),
				new SellItemFactory(Items.GRAY_BANNER, 3, 1, 15),
				new SellItemFactory(Items.BLACK_BANNER, 3, 1, 15),
				new SellItemFactory(Items.PURPLE_BANNER, 3, 1, 15),
				new SellItemFactory(Items.MAGENTA_BANNER, 3, 1, 15),
				new SellItemFactory(Items.CYAN_BANNER, 3, 1, 15),
				new SellItemFactory(Items.BROWN_BANNER, 3, 1, 15),
				new SellItemFactory(Items.YELLOW_BANNER, 3, 1, 15),
				new SellItemFactory(Items.ORANGE_BANNER, 3, 1, 15),
				new SellItemFactory(Items.LIGHT_GRAY_BANNER, 3, 1, 15)
			}
		);
	}
}
