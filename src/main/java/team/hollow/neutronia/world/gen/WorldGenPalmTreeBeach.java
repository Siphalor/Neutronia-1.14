/*
package team.hollow.neutronia.world.gen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.MinecraftClientGame;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import vibrantjourneys.worldgen.feature.WorldGenPalmTree;

import java.util.Random;
import java.util.Set;

public class WorldGenPalmTreeBeach extends AbstractTreeFeature<DefaultFeatureConfig> {
	private int frequency;
	
	public WorldGenPalmTreeBeach(int frequency) {
		super(DefaultFeatureConfig::deserialize, false);
		this.frequency = frequency;
	}
	
	public WorldGenPalmTree palmtreegen = new WorldGenPalmTree();

	@Override
	public boolean generate(Set<BlockPos> pos, ModifiableTestableWorld modifiableTestableWorld, Random random, BlockPos blockPos) {
		World world = MinecraftClient.getInstance().world;
		int x = chunkX * 16 + 8;
		int z = chunkZ * 16 + 8;
		
		Biome biome = world.getBiome(new BlockPos(x, 0, z));
		//beach but not cold beach
		//who wants a palm tree in the middle of a frozen beach?
		if(biome.getCategory() == Biome.Category.BEACH && biome.getTemperature() >= 1.0F) {
			for(int i = 0; i < frequency; i++) {
				int xPos = x + random.nextInt(4) - random.nextInt(4);
				int zPos = z + random.nextInt(4) - random.nextInt(4);
				int yPos;
				if(random.nextInt(6) == 0) {
					for(int y = 0; y < 4; y++) {
						yPos = 63 + y;
						BlockPos blockPos1 = new BlockPos(xPos, yPos, zPos);
						BlockState state = world.getBlockState(blockPos1.down());
						if(state.getBlock() == Blocks.SAND) {
							if(palmtreegen.generate(pos, modifiableTestableWorld, random, blockPos1)) {
								return true;
								break;
							}
						}
					}
				}
			}
		}
		return false;
	}
}*/
