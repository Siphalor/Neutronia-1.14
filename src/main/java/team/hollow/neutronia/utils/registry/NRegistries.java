package team.hollow.neutronia.utils.registry;

import net.minecraft.util.registry.DefaultedRegistry;
import team.hollow.neutronia.village.PointOfInterestType;
import team.hollow.neutronia.village.VillagerPlusProfession;

public class NRegistries {

    public static final DefaultedRegistry<VillagerPlusProfession> VILLAGER_PROFESSION = new DefaultedRegistry<>("villager_plus_profession");

    public static final DefaultedRegistry<PointOfInterestType> POINT_OF_INTEREST_TYPE = new DefaultedRegistry<>("point_of_interest_type");

}
