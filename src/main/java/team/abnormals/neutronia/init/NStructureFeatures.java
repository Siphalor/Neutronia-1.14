package team.abnormals.neutronia.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Locale;

public class NStructureFeatures {

    public static final StructureFeature PILLAGER_MANSION = register("Pillager_Mansion", NFeatures.PILLAGER_MANSION);
    public static final StructureFeature CARTOGRAPHER_CAMP = register("Cartographer_Camp", NFeatures.CARTOGRAPHER_CAMP);

    private static StructureFeature<?> register(String string_1, StructureFeature<?> structureFeature_1) {
        return Registry.register(Registry.STRUCTURE_FEATURE, new Identifier("neutronia", string_1.toLowerCase(Locale.ROOT)), structureFeature_1);
    }

}
