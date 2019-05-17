package team.hollow.neutronia.modules.exploration.trees;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import team.hollow.abnormalib.blocks.SaplingBaseBlock;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.abnormalib.utils.registry.WoodType;
import team.hollow.abnormalib.utils.registry.WoodTypeRegistry;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.NeutroniaLeavesBlock;

public class TreeFeature extends OptionalFeature {
	public Block log;
	public Block strippedLog;
	public Block wood;
	public Block strippedWood;
	public Block leaves;
	public Block sapling;
	public WoodType woodType;

	protected SaplingGenerator saplingGenerator;

	public TreeFeature(String name, SaplingGenerator saplingGenerator) {
		super(name, "Enable " + formatName(name) + " trees and wood.");

		this.saplingGenerator = saplingGenerator;
	}

	@Override
	protected void applyEnabled() {
		ContentBuilder contentBuilder = Neutronia.getContentBuilder();
		contentBuilder.setSecondaryItem(Items.STICK);

		log = contentBuilder.newBlock(name + "_log", new PillarBlock(FabricBlockSettings.of(Material.WOOD).build()));
		strippedLog = contentBuilder.newBlock("stripped_" + name + "_log", new PillarBlock(FabricBlockSettings.of(Material.WOOD).build()));
		wood = contentBuilder.newBlock(
			name + "_wood",
			new Block(
				FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD)
					.hardness(2.0F).sounds(BlockSoundGroup.WOOD).build()
			),
			name + "_log"
		);
		strippedWood = contentBuilder.newBlock(
			"stripped_" + name + "_wood",
			new Block(
				FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD)
					.hardness(2.0F).sounds(BlockSoundGroup.WOOD).build()
			),
			"stripped_" + name + "_log"
		);
		leaves = contentBuilder.newBlock(name + "_leaves", new NeutroniaLeavesBlock());
		sapling = contentBuilder.newBaseBlock(name + "_sapling", new SaplingBaseBlock(saplingGenerator));
		contentBuilder.addPotted();

		woodType = WoodTypeRegistry.registerModded(new WoodType(new Identifier(Neutronia.MOD_ID, name)), 2.0F, 3.0F);
	}
}
