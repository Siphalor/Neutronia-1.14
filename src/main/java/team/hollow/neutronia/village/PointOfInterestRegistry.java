package team.hollow.neutronia.village;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;

public interface PointOfInterestRegistry {

    static PointOfInterestType register(PointOfInterestTypeCustom pointOfInterestType_1) {
        return Registry.register(Registry.POINT_OF_INTEREST_TYPE, new Identifier(pointOfInterestType_1.id), pointOfInterestType_1.register());
    }

}