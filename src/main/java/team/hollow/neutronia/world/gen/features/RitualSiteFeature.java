package team.hollow.neutronia.world.gen.features;

import com.mojang.datafixers.Dynamic;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import team.hollow.neutronia.world.gen.RitualSiteGenerator;

import java.util.function.Function;

public class RitualSiteFeature extends AbstractTempleFeature<RitualSiteFeatureConfig> {
	
	public RitualSiteFeature(Function<Dynamic<?>, ? extends RitualSiteFeatureConfig> fn) {
		super(fn);
	}
	
	@Override
	public StructureStartFactory getStructureStartFactory() {
		return Start::new;
	}
	
	@Override
	public String getName() {
		return "smallst:ritual_site";
	}
	
	@Override
	public int getRadius() {
		return 3;
	}
	
	public static class Start extends StructureStart {
		
		public Start(
			StructureFeature<?> feature,
			int chunkX,
			int chunkZ,
			Biome biome,
			MutableIntBoundingBox boundingBox,
			int references,
			long seed
		) {
			super(feature, chunkX, chunkZ, biome, boundingBox, references, seed);
		}
		
		@Override
		public void initialize(ChunkGenerator<?> cg, StructureManager manager, int chunkX, int chunkZ, Biome biome) {
			BlockPos pos = new BlockPos(chunkX << 4, 90, chunkZ << 4);
			RitualSiteGenerator.addPieces(cg, manager, pos, this.children, this.random);
			this.setBoundingBoxFromChildren();
		}
		
	}

	@Override
	protected int getSeedModifier() {
		return 0;
	}
	
}