package team.hollow.neutronia.modules.exploration.food;

import net.minecraft.block.Block;
import team.hollow.abnormalib.blocks.CakeBaseBlock;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.neutronia.Neutronia;

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
		ContentBuilder contentBuilder = Neutronia.getContentBuilder();
		cheeseCake = contentBuilder.newBlock("cheese_cake", new CakeBaseBlock());
		chocolateCake = contentBuilder.newBlock("chocolate_cake", new CakeBaseBlock());
		pumpkinPie = contentBuilder.newBlock("pumpkin_pie", new CakeBaseBlock(4));
		blueberryPie = contentBuilder.newBlock("blueberry_pie", new CakeBaseBlock(4));
		sweetBerryPie = contentBuilder.newBlock("sweet_berry_pie", new CakeBaseBlock(4));
		applePie = contentBuilder.newBlock("apple_pie", new CakeBaseBlock(4));
	}
}
