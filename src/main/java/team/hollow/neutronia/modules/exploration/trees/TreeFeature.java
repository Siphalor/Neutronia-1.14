package team.hollow.neutronia.modules.exploration.trees;

import generators.ModelGenerator;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import team.hollow.module_api.api.features.Feature;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.unsure.WoodType;
import team.hollow.neutronia.unsure.WoodTypeRegistry;
import team.hollow.neutronia.utils.registry.BlockRegistryBuilder;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class TreeFeature extends OptionalFeature {
	public Block log;
	public Block strippedLog;
	public Block wood;
	public Block strippedWood;
	public Block planks;
	public Block leaves;
	public Block sapling;
	public WoodType woodType;

	protected SaplingGenerator saplingGenerator;

	public TreeFeature(String name, SaplingGenerator saplingGenerator) {
		super(name, "Enable " + Feature.formatName(name) + " trees and wood.");

		this.saplingGenerator = saplingGenerator;
	}

	@Override
	protected void applyEnabled() {
		if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			ModelGenerator.genPackMcMeta(
					"The textures for the Neutronia resource pack"
			);
			ModelGenerator.genSlab(
					new Identifier(Neutronia.MOD_ID, name + "_slab"),
					new Identifier(Neutronia.MOD_ID, name + "_planks"),
					new Identifier(Neutronia.MOD_ID, name + "_planks"),
					new Identifier(Neutronia.MOD_ID, name + "_planks"),
					new Identifier(Neutronia.MOD_ID, name + "_planks")
			);
			ModelGenerator.genBlock(
					new Identifier(Neutronia.MOD_ID, name + "_planks"),
					new Identifier(Neutronia.MOD_ID, name + "_planks")
			);
			ModelGenerator.genStair(
					new Identifier(Neutronia.MOD_ID, name + "_stairs"),
					new Identifier(Neutronia.MOD_ID, name + "_planks"),
					new Identifier(Neutronia.MOD_ID, name + "_planks"),
					new Identifier(Neutronia.MOD_ID, name + "_planks")
			);
			ModelGenerator.genFenceBlock(
					new Identifier(Neutronia.MOD_ID, name + "_fence"),
					new Identifier(Neutronia.MOD_ID, name + "_planks")
			);
		}
		log = new NeutroniaPillarBlock(Material.WOOD, name + "_log");
		strippedLog = new NeutroniaPillarBlock(Material.WOOD, "stripped_" + name + "_log");
		wood = RegistryUtils.register(
				new Block(
						FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD)
								.hardness(2.0F).sounds(BlockSoundGroup.WOOD).build()
				),
				new Identifier(Neutronia.MOD_ID, name + "_wood"),
				ItemGroup.BUILDING_BLOCKS
		);
		strippedWood = RegistryUtils.register(
				new Block(
						FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD)
								.hardness(2.0F).sounds(BlockSoundGroup.WOOD).build()
				),
				new Identifier(Neutronia.MOD_ID, "stripped_" + name + "_wood")
		);
		planks = new NeutroniaBaseBlock(Material.WOOD, name + "_planks");
		leaves = RegistryUtils.register(new NeutroniaLeavesBlock(), new Identifier(Neutronia.MOD_ID, name + "_leaves"));
		sapling = RegistryUtils.register(new NeutroniaSaplingBlock(saplingGenerator), new Identifier(Neutronia.MOD_ID, name + "_sapling"));
		RegistryUtils.register(new NeutroniaDoorBlock(Material.WOOD), name + "_door", ItemGroup.REDSTONE);
		RegistryUtils.register(new NeutroniaTrapdoorBlock(Material.WOOD), name + "_trapdoor", ItemGroup.REDSTONE);

		BlockRegistryBuilder.getInstance(name, planks).slab().stair().fence().fenceGate().button(true).pressurePlate(PressurePlateBlock.Type.WOOD);

		woodType = WoodTypeRegistry.registerModded(new WoodType(name, planks));
	}

}
