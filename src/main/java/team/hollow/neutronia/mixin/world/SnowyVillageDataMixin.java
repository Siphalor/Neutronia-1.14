package team.hollow.neutronia.mixin.world;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.generator.village.SnowyVillageData;
import net.minecraft.structure.pool.EmptyPoolElement;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(SnowyVillageData.class)
public class SnowyVillageDataMixin {

    @Inject(method = "<clinit>()V", at = @At("RETURN"))
    private static void register() {
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/common/animals"), new Identifier("empty"), ImmutableList.of(new Pair(new SinglePoolElement("village/common/animals/cows_1"), 7), new Pair(new SinglePoolElement("village/common/animals/pigs_1"), 7), new Pair(new SinglePoolElement("village/common/animals/horses_1"), 1), new Pair(new SinglePoolElement("village/common/animals/horses_2"), 1), new Pair(new SinglePoolElement("village/common/animals/horses_3"), 1), new Pair(new SinglePoolElement("village/common/animals/horses_4"), 1), new Pair(new SinglePoolElement("village/common/animals/horses_5"), 1), new Pair(new SinglePoolElement("village/common/animals/sheep_1"), 1), new Pair(new SinglePoolElement("village/common/animals/sheep_2"), 1), Pair.of(EmptyPoolElement.INSTANCE, 5)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/common/sheep"), new Identifier("empty"), ImmutableList.of(new Pair<>(new SinglePoolElement("village/common/animals/sheep_1"), 1), new Pair<>(new SinglePoolElement("village/common/animals/sheep_2"), 1)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/common/cats"), new Identifier("empty"), ImmutableList.of(new Pair<>(new SinglePoolElement("village/common/animals/cat_black"), 1), new Pair(new SinglePoolElement("village/common/animals/cat_british"), 1), new Pair(new SinglePoolElement("village/common/animals/cat_calico"), 1), new Pair(new SinglePoolElement("village/common/animals/cat_persian"), 1), new Pair(new SinglePoolElement("village/common/animals/cat_ragdoll"), 1), new Pair(new SinglePoolElement("village/common/animals/cat_red"), 1), new Pair(new SinglePoolElement("village/common/animals/cat_siamese"), 1), new Pair(new SinglePoolElement("village/common/animals/cat_tabby"), 1), new Pair(new SinglePoolElement("village/common/animals/cat_white"), 1), new Pair(new SinglePoolElement("village/common/animals/cat_jellie"), 1), Pair.of(EmptyPoolElement.INSTANCE, 3)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/common/butcher_animals"), new Identifier("empty"), ImmutableList.of(new Pair<>(new SinglePoolElement("village/common/animals/cows_1"), 3), new Pair<>(new SinglePoolElement("village/common/animals/pigs_1"), 3), new Pair<>(new SinglePoolElement("village/common/animals/sheep_1"), 1), new Pair<net.minecraft.structure.pool.StructurePoolElement, Integer>(new SinglePoolElement("village/common/animals/sheep_2"), 1)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/common/iron_golem"), new Identifier("empty"), ImmutableList.of(new Pair<>(new SinglePoolElement("village/common/iron_golem"), 1)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/common/well_bottoms"), new Identifier("empty"), ImmutableList.of(new Pair<>(new SinglePoolElement("village/common/well_bottom"), 1)), StructurePool.Projection.RIGID));
    }

}
