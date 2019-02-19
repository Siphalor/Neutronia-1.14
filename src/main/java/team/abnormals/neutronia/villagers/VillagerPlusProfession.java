package team.abnormals.neutronia.villagers;

import net.minecraft.util.Identifier;
import team.abnormals.neutronia.utils.registry.NRegistries;

public interface VillagerPlusProfession {

    VillagerPlusProfession NONE = register("none");
    VillagerPlusProfession NITWIT = register("nitwit");
    VillagerPlusProfession BARD = register("nitwit");
    VillagerPlusProfession CHIEF = register("nitwit");

    static VillagerPlusProfession register(final String string_1) {
        return NRegistries.VILLAGER_PROFESSION.register(new Identifier(string_1), new VillagerPlusProfession() {
            public String toString() {
                return string_1;
            }
        });
    }

}
