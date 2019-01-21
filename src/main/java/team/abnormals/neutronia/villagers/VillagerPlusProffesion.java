package team.abnormals.neutronia.villagers;

import net.minecraft.util.Identifier;
import team.abnormals.neutronia.utils.registry.NRegistries;

public interface VillagerPlusProffesion {

    VillagerPlusProffesion NONE = register("none");
    VillagerPlusProffesion NITWIT = register("nitwit");

    static VillagerPlusProffesion register(final String string_1) {
        return NRegistries.VILLAGER_PROFESSION.register(new Identifier(string_1), new VillagerPlusProffesion() {
            public String toString() {
                return string_1;
            }
        });
    }

}
