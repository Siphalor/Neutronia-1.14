package team.hollow.neutronia.modules.exploration.food;

import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Pair;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.blocks.vanillish.NeutroniaBerryBushBlock;
import team.hollow.neutronia.registry.ContentBuilder;

import java.util.HashMap;
import java.util.Map;

public class BerriesFeature extends OptionalFeature {
	private static Map<BerryType, Pair<Item, Block>> berryMap = new HashMap<>();

	public BerriesFeature() {
		super("berries", "Adds more berries");
	}

	@Override
	protected void applyEnabled() {
        Item.Settings itemSettings = new Item.Settings().itemGroup(ItemGroup.FOOD).food(FoodItemSettings.SWEET_BERRIES);
		ContentBuilder contentBuilder = ContentBuilder.getInstance();

        for(BerryType berryType : BerryType.values()) {
            Block bush = contentBuilder.newBlock(berryType.singular + "_bush", new NeutroniaBerryBushBlock(berryType));
            Item berries = contentBuilder.newItem(berryType.plural, new AliasedBlockItem(bush, itemSettings));

        	berryMap.put(berryType, new Pair<>(berries, bush));
		}
	}

	public static Item getBerries(BerryType berryType) {
		return berryMap.get(berryType).getLeft();
	}

	public static Block getBush(BerryType berryType) {
		return berryMap.get(berryType).getRight();
	}

	public enum BerryType {
		BLUEBERRY("blueberry", "blueberries"), GOOSEBERRY("gooseberry", "gooseberries"), WITHER_BERRY("wither_berry", "wither_berries"), GREEN_GRAPE("green_grape", "green_grapes"), PURPLE_GRAPE("purple_grape", "purple_grapes");
		public final String singular;
		public final String plural;

		BerryType(String singular, String plural) {
			this.singular = singular;
			this.plural = plural;
		}
	}
}
