package team.hollow.neutronia.modules.variation;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.registry.ContentBuilder;

import java.util.Set;

public class PottedPlantsFeature extends OptionalFeature {
	private Set<Pair<Block, String>> blocksToPot = ImmutableSet.of(
		new Pair<>(Blocks.BEETROOTS, "minecraft:grass"),
		new Pair<>(Blocks.CARROTS, "minecraft:grass"),
		new Pair<>(Blocks.CHORUS_FLOWER, "minecraft:chorus_flower"),
		new Pair<>(Blocks.GRASS, "minecraft:grass"),
		new Pair<>(Blocks.LILAC, "minecraft:lilac_top"),
		new Pair<>(Blocks.MELON, "minecraft:melon_block"),
		new Pair<>(Blocks.NETHER_WART, "minecraft:grass"),
		new Pair<>(Blocks.PEONY, "minecraft:peony_top"),
		new Pair<>(Blocks.POTATOES, "minecraft:grass"),
		new Pair<>(Blocks.PUMPKIN, "minecraft:pumpkin_block"),
		new Pair<>(Blocks.ROSE_BUSH, "minecraft:rose_bush_top"),
		new Pair<>(Blocks.SUGAR_CANE, "minecraft:sugar_cane"),
		new Pair<>(Blocks.SUNFLOWER, "minecraft:grass"),
		new Pair<>(Blocks.TALL_GRASS, "minecraft:tall_grass_top"),
		new Pair<>(Blocks.WHEAT, "minecraft:grass")
	);

	public PottedPlantsFeature() {
		super("potted-plants", "Adds the ability to put even more plants into flower pots");
	}

	@Override
	protected void applyEnabled() {
		blocksToPot.forEach(pair -> add(pair.getLeft(), Identifier.create(pair.getRight())));
	}

	public void add(Block block, Identifier texture) {
		ContentBuilder contentBuilder = ContentBuilder.getInstance();
		contentBuilder.asBaseBlock(block, Registry.BLOCK.getId(block));
		contentBuilder.addPotted(texture);
	}

	static class PottablePlant {
		Block block;
	}
}
