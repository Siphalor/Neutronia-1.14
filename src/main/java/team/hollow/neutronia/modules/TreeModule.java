package team.hollow.neutronia.modules;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.TranslatableTextComponent;
import team.hollow.module_api.api.Module;
import team.hollow.neutronia.blocks.sapling.MangroveSaplingGenerator;

public class TreeModule extends Module {
	public static TreeFeature mangrove;

	public TreeModule() {
		super("tree-module", new ItemStack(Items.OAK_SAPLING), new TranslatableTextComponent("test"));

		mangrove = register(new TreeFeature("mangrove", "test", new MangroveSaplingGenerator()));
	}
}
