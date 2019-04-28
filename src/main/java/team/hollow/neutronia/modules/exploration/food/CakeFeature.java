package team.hollow.neutronia.modules.exploration.food;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.blocks.CakeBaseBlock;
import team.hollow.neutronia.blocks.PieBlock;
import team.hollow.neutronia.unsure.ContentBuilder;
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
		ContentBuilder contentBuilder = ContentBuilder.getInstance();
		cheeseCake = contentBuilder.newBlock("cheese_cake", new CakeBaseBlock());
		chocolateCake = contentBuilder.newBlock("chocolate_cake", new CakeBaseBlock());
		pumpkinPie = contentBuilder.newBlock("pumpkin_pie", new PieBlock());
		blueberryPie = contentBuilder.newBlock("blueberry_pie", new PieBlock());
		sweetBerryPie = contentBuilder.newBlock("sweet_berry_pie", new PieBlock());
		applePie = contentBuilder.newBlock("apple_pie", new PieBlock());
	}
}
