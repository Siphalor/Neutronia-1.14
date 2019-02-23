package team.abnormals.neutronia.villagers;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.utils.registry.NRegistries;

public interface VillagerPlusProfession {

    VillagerPlusProfession NONE = register("none");
    VillagerPlusProfession NITWIT = register("nitwit");
    VillagerPlusProfession BARD = register("nitwit");
    VillagerPlusProfession CHIEF = register("nitwit");

    static VillagerPlusProfession register(final String string_1) {
        return Registry.register(NRegistries.VILLAGER_PROFESSION, new Identifier(string_1), new VillagerPlusProfession() {
            public String toString() {
                return string_1;
            }
        });
    }

}
