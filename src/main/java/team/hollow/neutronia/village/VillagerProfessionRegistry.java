package team.hollow.neutronia.village;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.village.VillagerProfession;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.event_system.Reflect;

public interface VillagerProfessionRegistry {

    static VillagerProfession register(String id, PointOfInterestType pointOfInterestType_1, ImmutableSet<Item> immutableSet_1, ImmutableSet<Block> immutableSet_2) {
        VillagerProfession profession = (VillagerProfession) Reflect.constructClass(VillagerProfession.class,
                id, pointOfInterestType_1, immutableSet_1, immutableSet_2);
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(Neutronia.MOD_ID, id), profession);
    }

}