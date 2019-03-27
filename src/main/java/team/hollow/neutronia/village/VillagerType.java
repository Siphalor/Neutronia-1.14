package team.hollow.neutronia.village;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public interface VillagerType {
    static net.minecraft.village.VillagerType register(String string_1, Biome... biomes) {
        net.minecraft.village.VillagerType villagerType = Registry.register(Registry.VILLAGER_TYPE, new Identifier("neutronia", string_1), new net.minecraft.village.VillagerType() {
            public String toString() {
                return string_1;
            }
        });
        for (Biome biome : biomes) {
            net.minecraft.village.VillagerType.BIOME_TO_TYPE.put(biome, villagerType);
        }
        return villagerType;
    }
}