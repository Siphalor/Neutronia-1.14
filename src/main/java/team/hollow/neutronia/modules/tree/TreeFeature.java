package team.hollow.neutronia.modules.tree;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import team.hollow.module_api.api.OptionalFeature;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.modules.DecorationModule;
import team.hollow.neutronia.utils.registry.BlockRegistryBuilder;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class TreeFeature extends OptionalFeature {
	public Block log;
	public Block wood;
	public Block planks;
	public Block leaves;
	public Block sapling;
	public Block door;
	public Block trapDoor;

	protected SaplingGenerator saplingGenerator;

	public TreeFeature(String name, SaplingGenerator saplingGenerator) {
		super(name, "Enable " + name + " trees and wood.");

		this.saplingGenerator = saplingGenerator;
	}

	@Override
	protected void applyEnabled() {
		log = new NeutroniaPillarBlock(Material.WOOD, name + "_log");
		wood = RegistryUtils.register(
			new Block(
				FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD)
					.hardness(2.0F).sounds(BlockSoundGroup.WOOD).build()
			),
			new Identifier(Neutronia.MOD_ID, name + "_wood"),
			ItemGroup.BUILDING_BLOCKS
		);
		planks = new NeutroniaBaseBlock(Material.WOOD, name + "_planks");
		leaves = RegistryUtils.register(new NeutroniaLeavesBlock(), new Identifier(Neutronia.MOD_ID, name + "_leaves"));
		sapling = RegistryUtils.register(new NeutroniaSaplingBlock(saplingGenerator), new Identifier(Neutronia.MOD_ID, name + "_sapling"));
		door = RegistryUtils.register(new NeutroniaDoorBlock(Material.WOOD), name + "_door", ItemGroup.REDSTONE);
        trapDoor = RegistryUtils.register(new NeutroniaTrapdoorBlock(Material.WOOD), name + "_trapdoor", ItemGroup.REDSTONE);

		if(DecorationModule.enableStairsAndSlabs.value) {
			BlockRegistryBuilder.getInstance(name, planks).slab().stair();
		}
		if(DecorationModule.enableFences.value) {
        	BlockRegistryBuilder.getInstance(name, planks).fence().fenceGate();
		}
		if(DecorationModule.enableRedstoneyBlocks.value) {
			BlockRegistryBuilder.getInstance(name, planks).button(true).pressurePlate(PressurePlateBlock.Type.WOOD);
		}
		if(DecorationModule.enableSidings.value) {
			BlockRegistryBuilder.getInstance(name, planks).siding().corner();
		}
	}

}
