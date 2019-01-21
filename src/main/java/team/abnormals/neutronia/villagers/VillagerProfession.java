package team.abnormals.neutronia.villagers;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public interface VillagerProfession {
    static net.minecraft.village.VillagerProfession register(String string_1) {
        return Registry.VILLAGER_PROFESSION.register(new Identifier("neutronia", string_1), new net.minecraft.village.VillagerProfession() {
            public String toString() {
                return string_1;
            }
        });
    }
}