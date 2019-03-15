package team.hollow.neutronia.mixin.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.TaigaBiome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.world.SmallStructuresRegistry;
import team.hollow.neutronia.world.gen.features.RitualSiteFeatureConfig;

@Mixin(value = TaigaBiome.class)
public class TaigaMixin {
	
	@Inject(at = @At("RETURN"), method = "<init>()V")
	public void insertGen(CallbackInfo cbinfo) {
		TaigaBiome target = (TaigaBiome) (Object) this;
		target.addStructureFeature(SmallStructuresRegistry.RITUAL_SITE_FEATURE, new RitualSiteFeatureConfig(1));
		target.addFeature(
			GenerationStep.Feature.SURFACE_STRUCTURES,
			Biome.configureFeature(
				SmallStructuresRegistry.RITUAL_SITE_FEATURE,
				new RitualSiteFeatureConfig(1),
				Decorator.CHANCE_PASSTHROUGH,
				new ChanceDecoratorConfig(25)
			)
		);
		System.out.println("-----------------------------Added Taiga Structure");
	}
}