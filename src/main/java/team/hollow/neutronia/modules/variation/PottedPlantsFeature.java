package team.hollow.neutronia.modules.variation;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.unsure.ContentBuilder;

import java.util.Set;

public class PottedPlantsFeature extends OptionalFeature {
	private Set<Pair<Block, String>> blocksToPot = ImmutableSet.of(
		new Pair<>(Blocks.BEETROOTS, "minecraft:block/grass"),
		new Pair<>(Blocks.CARROTS, "minecraft:block/grass"),
		new Pair<>(Blocks.CHORUS_FLOWER, "minecraft:block/grass"),
		new Pair<>(Blocks.GRASS, "minecraft:block/grass"),
		new Pair<>(Blocks.LILAC, "minecraft:block/grass"),
		new Pair<>(Blocks.MELON, "minecraft:block/grass"),
		new Pair<>(Blocks.NETHER_WART, "minecraft:block/grass"),
		new Pair<>(Blocks.PEONY, "minecraft:block/grass"),
		new Pair<>(Blocks.POTATOES, "minecraft:block/grass"),
		new Pair<>(Blocks.PUMPKIN, "minecraft:block/grass"),
		new Pair<>(Blocks.ROSE_BUSH, "minecraft:block/grass"),
		new Pair<>(Blocks.SUGAR_CANE, "minecraft:block/grass"),
		new Pair<>(Blocks.SUNFLOWER, "minecraft:block/grass"),
		new Pair<>(Blocks.TALL_GRASS, "minecraft:block/grass"),
		new Pair<>(Blocks.WHEAT, "minecraft:block/grass")
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
}
