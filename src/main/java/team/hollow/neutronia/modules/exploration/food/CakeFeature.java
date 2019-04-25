package team.hollow.neutronia.modules.exploration.food;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.blocks.CakeBaseBlock;
import team.hollow.neutronia.blocks.PieBlock;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class CakeFeature extends OptionalFeature {
	public static Block cheeseCake;
	public static Block chocolateCake;
	public static Block pumpkinPie;
	public static Block blueberryPie;
	public static Block sweetBerryPie;
	public static Block applePie;

	public CakeFeature() {
		super("cake", "Adds more cakes and pies (as blocks)");
	}

	@Override
	protected void applyEnabled() {
		cheeseCake = RegistryUtils.register(new CakeBaseBlock(), "cheese_cake", ItemGroup.FOOD);
		chocolateCake = RegistryUtils.register(new CakeBaseBlock(), "chocolate_cake", ItemGroup.FOOD);
		pumpkinPie = RegistryUtils.register(new PieBlock(), "pumpkin_pie", ItemGroup.FOOD);
		blueberryPie = RegistryUtils.register(new PieBlock(), "blueberry_pie", ItemGroup.FOOD);
		sweetBerryPie = RegistryUtils.register(new PieBlock(), "sweet_berry_pie", ItemGroup.FOOD);
		applePie = RegistryUtils.register(new PieBlock(), "apple_pie", ItemGroup.FOOD);
	}
}
