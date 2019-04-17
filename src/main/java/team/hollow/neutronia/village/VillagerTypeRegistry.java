package team.hollow.neutronia.village;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;

import java.util.*;

public interface VillagerTypeRegistry {
	HashMap<Biome, ArrayList<VillagerType>> customVillagerTypes = new HashMap<>();

    static VillagerType register(String string_1, Biome... biomes) {
        VillagerType villagerType = Registry.register(Registry.VILLAGER_TYPE, new Identifier("neutronia", string_1), new net.minecraft.village.VillagerType() {
            public String toString() {
                return string_1;
            }
        });
        for (Biome biome : biomes) {
            if(customVillagerTypes.containsKey(biome))
                customVillagerTypes.get(biome).add(villagerType);
            else {
                customVillagerTypes.put(biome, new ArrayList<>(Collections.singleton(villagerType)));
            }
        }
        return villagerType;
    }

    static VillagerType getVillagerTypeForBiome(Biome biome) {
        if(customVillagerTypes.containsKey(biome)) {
            ArrayList<VillagerType> villagerTypes = customVillagerTypes.get(biome);
            return villagerTypes.get(MathHelper.floor(villagerTypes.size() * Math.random()));
        } else {
            return VillagerType.forBiome(biome);
        }
    }
}