package team.hollow.neutronia.modules;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import team.hollow.module_api.api.SimpleFeature;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.utils.registry.BlockRegistryBuilder;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class TreeFeature extends SimpleFeature {
	public Block log;
	public Block wood;
	public Block planks;
	public Block leaves;
	public Block sapling;
	public Block door;
	public Block trapDoor;

	protected SaplingGenerator saplingGenerator;

	public TreeFeature(String name, String description, SaplingGenerator saplingGenerator) {
		super(name, description);

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
		BlockRegistryBuilder.getInstance(name, planks).slab().stair().fence().fenceGate();
		leaves = RegistryUtils.register(new NeutroniaLeavesBlock(), new Identifier(Neutronia.MOD_ID, name + "_leaves"));
		sapling = RegistryUtils.register(new NeutroniaSaplingBlock(saplingGenerator), new Identifier(Neutronia.MOD_ID, name + "_sapling"));
		door = RegistryUtils.register(new NeutroniaDoorBlock(Material.WOOD), name + "_door", ItemGroup.REDSTONE);
        trapDoor = RegistryUtils.register(new NeutroniaTrapdoorBlock(Material.WOOD), name + "_trapdoor", ItemGroup.REDSTONE);
	}

}
