package team.hollow.neutronia.modules;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.StringTextComponent;
import team.hollow.module_api.api.Module;
import team.hollow.neutronia.blocks.sapling.MangroveSaplingGenerator;

public class TreeModule extends Module {
	public static TreeFeature mangrove;
	public static TreeFeature redwood;

	public TreeModule() {
		super("tree_module", new ItemStack(Items.OAK_SAPLING), new StringTextComponent("Adds a lot of wood related things"));

		mangrove = register(new TreeFeature("mangrove", "Adds mangrove related blocks", new MangroveSaplingGenerator()));
		redwood = register(new TreeFeature("redwood", "Adds redwood related blocks", new MangroveSaplingGenerator()));
	}
}
