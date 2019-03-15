package team.hollow.neutronia.world.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sortme.rule.AlwaysTrueRuleTest;
import net.minecraft.sortme.rule.RandomBlockMatchRuleTest;
import net.minecraft.sortme.rule.RandomBlockStateMatchRuleTest;
import net.minecraft.state.property.Property;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePool.Projection;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.BlockRotStructureProcessor;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import team.hollow.neutronia.world.SmallStructuresRegistry;

import java.util.ArrayList;
import java.util.List;

public class RitualSiteGenerator {
	
	public static void addPieces(ChunkGenerator<?> chunkGenerator_1, StructureManager structureManager_1, BlockPos blockPos_1, List<StructurePiece> list_1, ChunkRandom chunkRandom_1) {
		StructurePoolBasedGenerator.addPieces(new Identifier("neutronia", "ritual_sites"), 7, Piece::new, chunkGenerator_1, structureManager_1, blockPos_1, list_1, chunkRandom_1);
	}
	
	@SuppressWarnings({
		"unchecked", "rawtypes"
	})
	private static List<StructureProcessorRule> getSameState(Block block, float probability, Block replace) {
		List<StructureProcessorRule> out = new ArrayList<>();
		for(BlockState bs: block.getStateFactory().getStates()) {
			BlockState rbs = replace.getDefaultState();
			for(Property prop: bs.getProperties()) {
				rbs = rbs.with(prop, bs.get(prop));
			}
			
			out.add(new StructureProcessorRule(new RandomBlockStateMatchRuleTest(bs, probability), AlwaysTrueRuleTest.INSTANCE, rbs));
		}
		return out;
	}
	
	private static List<StructureProcessorRule> getMossify(float mossify) {
		List<StructureProcessorRule> decay = new ArrayList<>();
		
		decay.add(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, mossify), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_STONE_BRICKS.getDefaultState()));
		decay.addAll(getSameState(Blocks.STONE_BRICK_SLAB, mossify, Blocks.MOSSY_STONE_BRICK_SLAB));
		decay.addAll(getSameState(Blocks.STONE_BRICK_STAIRS, mossify, Blocks.MOSSY_STONE_BRICK_STAIRS));
		decay.addAll(getSameState(Blocks.STONE_BRICK_WALL, mossify, Blocks.MOSSY_STONE_BRICK_WALL));
		
		decay.add(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, mossify), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()));
		decay.addAll(getSameState(Blocks.COBBLESTONE_SLAB, mossify, Blocks.MOSSY_COBBLESTONE_SLAB));
		decay.addAll(getSameState(Blocks.COBBLESTONE_STAIRS, mossify, Blocks.MOSSY_COBBLESTONE_STAIRS));
		decay.addAll(getSameState(Blocks.COBBLESTONE_WALL, mossify, Blocks.MOSSY_COBBLESTONE_WALL));
		return decay;
	}
	
	private static List<StructureProcessorRule> getCrack(float crack) {
		List<StructureProcessorRule> decay = new ArrayList<>();
		
		decay.add(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, crack), AlwaysTrueRuleTest.INSTANCE, Blocks.COBBLESTONE.getDefaultState()));
		decay.addAll(getSameState(Blocks.STONE_BRICK_SLAB, crack, Blocks.COBBLESTONE_SLAB));
		decay.addAll(getSameState(Blocks.STONE_BRICK_STAIRS, crack, Blocks.COBBLESTONE_STAIRS));
		decay.addAll(getSameState(Blocks.STONE_BRICK_WALL, crack, Blocks.COBBLESTONE_WALL));
		
		decay.add(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MOSSY_STONE_BRICKS, crack), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()));
		decay.addAll(getSameState(Blocks.MOSSY_STONE_BRICK_SLAB, crack, Blocks.MOSSY_COBBLESTONE_SLAB));
		decay.addAll(getSameState(Blocks.MOSSY_STONE_BRICK_STAIRS, crack, Blocks.MOSSY_COBBLESTONE_STAIRS));
		decay.addAll(getSameState(Blocks.MOSSY_STONE_BRICK_WALL, crack, Blocks.MOSSY_COBBLESTONE_WALL));
		return decay;
	}
	
	static {
		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("neutronia", "ritual_sites"), new Identifier("empty"),
				ImmutableList.of(Pair.of(new SinglePoolElement("neutronia:ritual_sites/overgrown", ImmutableList.of(new RuleStructureProcessor(getMossify(0.6F)),
                        new RuleStructureProcessor(getCrack(0.25F)), new BlockRotStructureProcessor(0.9F))), 1),
					Pair.of(new SinglePoolElement("neutronia:ritual_sites/sacrifice", ImmutableList.of(new RuleStructureProcessor(getMossify(0.3F)))), 1)
				), Projection.TERRAIN_MATCHING)
		);
	}
	
	public static class Piece extends PoolStructurePiece {
		Piece(StructureManager manager, StructurePoolElement element, BlockPos blockPos, int delta, Rotation rot, MutableIntBoundingBox mutableIntBoundingBox) {
			super(SmallStructuresRegistry.RITUAL_SITE_PIECE_TYPE, manager, element, blockPos, delta, rot, mutableIntBoundingBox);
		}
		
		public Piece(StructureManager manager, CompoundTag compound) {
			super(manager, compound, SmallStructuresRegistry.RITUAL_SITE_PIECE_TYPE);
		}
	}
}