package team.hollow.neutronia.modules.exploration.trees;

import generators.ModelGenerator;
import generators.RecipeGenerator;
import generators.ShapedRecipeIngredients;
import generators.ShapelessRecipeIngredients;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
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

		if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			clientApply();
		}

		woodType = WoodTypeRegistry.registerModded(new WoodType(name, planks));
	}

	public void clientApply() {
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

		Block siding = Registry.BLOCK.get(new Identifier(Neutronia.MOD_ID, String.format("%s_siding", name)));
		Block slab = Registry.BLOCK.get(new Identifier(Neutronia.MOD_ID, String.format("%s_slab", name)));
		Block stairs = Registry.BLOCK.get(new Identifier(Neutronia.MOD_ID, String.format("%s_stairs", name)));
		Block pressurePlate = Registry.BLOCK.get(new Identifier(Neutronia.MOD_ID, String.format("%s_pressure_plate", name)));
		Block button = Registry.BLOCK.get(new Identifier(Neutronia.MOD_ID, String.format("%s_button", name)));
		Block wall = Registry.BLOCK.get(new Identifier(Neutronia.MOD_ID, String.format("%s_wall", name)));
		Block fence = Registry.BLOCK.get(new Identifier(Neutronia.MOD_ID, String.format("%s_fence", name)));
		Block fenceGate = Registry.BLOCK.get(new Identifier(Neutronia.MOD_ID, String.format("%s_fence_gate", name)));
		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addShaped(new ItemStack(siding, 6), new Identifier(Neutronia.MOD_ID, name + "_siding"), "sidings", new String[]{
						"X",
						"X",
						"X"
				}, new ShapedRecipeIngredients("X", new ItemStack(planks)));
		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addShapeless(
						new ItemStack(siding, 6),
						new Identifier(Neutronia.MOD_ID, name + "_siding_to_slab"),
						"sidings_to_slabs",
						new ShapelessRecipeIngredients(new ItemStack(siding))
				);
		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addShapeless(
						new ItemStack(siding, 6),
						new Identifier(Neutronia.MOD_ID, name + "_siding_to_block"),
						"sidings_to_blocks",
						new ShapelessRecipeIngredients(new ItemStack(siding)),
						new ShapelessRecipeIngredients(new ItemStack(siding))
				);
		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addStonecutting(
						new ItemStack(siding, 6),
						new Identifier(Neutronia.MOD_ID, String.format("%s_siding_from_%s_stonecutting", name, Registry.BLOCK.getId(planks).getPath())),
						"siding_from_block_stonecutting",
						new ShapelessRecipeIngredients(new ItemStack(siding))
				);

		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addShaped(new ItemStack(slab), new Identifier(Neutronia.MOD_ID, name + "_slab"), "slabs", new String[]{
						"XXX"
				}, new ShapedRecipeIngredients("X", new ItemStack(planks)));

		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addShaped(new ItemStack(stairs), new Identifier(Neutronia.MOD_ID, name + "_stairs"), "stairs", new String[]{
						"X  ",
						"XX ",
						"XXX"
				}, new ShapedRecipeIngredients("X", new ItemStack(planks)));

		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addShaped(new ItemStack(pressurePlate, 6), new Identifier(Neutronia.MOD_ID, name + "_pressure_plate"), "pressure_plates", new String[]{
						"XX"
				}, new ShapedRecipeIngredients("X", new ItemStack(planks)));

		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addShapeless(
						new ItemStack(button),
						new Identifier(Neutronia.MOD_ID, name + "_button"),
						"buttons",
						new ShapelessRecipeIngredients(new ItemStack(planks))
				);

		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addShaped(
						new ItemStack(wall),
						new Identifier(Neutronia.MOD_ID, name + "_wall"),
						"walls",
						new String[]{
								"OOO",
								"OOO"
						},
						new ShapedRecipeIngredients("O", new ItemStack(planks))
				);

		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addShaped(
						new ItemStack(fenceGate),
						new Identifier(Neutronia.MOD_ID, name + "_fence_gate"),
						"fence_gates",
						new String[]{
								"/O/",
								"/O/"
						},
						new ShapedRecipeIngredients("O", new ItemStack(planks)),
						new ShapedRecipeIngredients("/", new ItemStack(Items.STICK))
				);

		RecipeGenerator.getInstance(Neutronia.MOD_ID)
				.addShaped(
						new ItemStack(fence),
						new Identifier(Neutronia.MOD_ID, name + "_fence"),
						"fences",
						new String[]{
								"O/O",
								"O/O"
						},
						new ShapedRecipeIngredients("O", new ItemStack(planks)),
						new ShapedRecipeIngredients("/", new ItemStack(Items.STICK))
				);
	}

}
