package team.abnormals.neutronia.villagers;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public interface VillagerType {
    static net.minecraft.village.VillagerType register(String string_1, Biome... biomes) {
        net.minecraft.village.VillagerType villagerType = Registry.VILLAGER_TYPE.register(new Identifier("neutronia", string_1), new net.minecraft.village.VillagerType() {
            public String toString() {
                return string_1;
            }
        });
        for(Biome biome : biomes) {
            net.minecraft.village.VillagerType.biomeToType.put(biome, villagerType);
        }
        return villagerType;
    }
}