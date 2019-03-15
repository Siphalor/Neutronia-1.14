package team.hollow.neutronia.world;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.StructureFeature;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.world.gen.RitualSiteGenerator;
import team.hollow.neutronia.world.gen.features.RitualSiteFeature;
import team.hollow.neutronia.world.gen.features.RitualSiteFeatureConfig;

public class SmallStructuresRegistry {
	
	public static final StructureFeature<RitualSiteFeatureConfig> RITUAL_SITE_FEATURE = Registry.register(
		Registry.FEATURE,
		new Identifier(Neutronia.MOD_ID, "ritual_site"),
		new RitualSiteFeature(RitualSiteFeatureConfig::deserialize)
	);
	
	public static final StructureFeature<?> RITUAL_SITE_STRUCTURE = Registry.register(
		Registry.STRUCTURE_FEATURE,
		new Identifier(Neutronia.MOD_ID, "ritual_site"),
		RITUAL_SITE_FEATURE
	);
	
	public static final StructurePieceType RITUAL_SITE_PIECE_TYPE = Registry.register(
		Registry.STRUCTURE_PIECE,
		new Identifier(Neutronia.MOD_ID, "ritual_site"),
		RitualSiteGenerator.Piece::new
	);
	
}