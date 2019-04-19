package team.hollow.neutronia.village;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.village.VillagerProfession;
import team.hollow.neutronia.Neutronia;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public interface VillagerProfessionRegistry {

    static VillagerProfession register(String id, PointOfInterestType pointOfInterestType_1, ImmutableSet<Item> immutableSet_1, ImmutableSet<Block> immutableSet_2) {
        VillagerProfession profession1 = null;
        try {
            Constructor<VillagerProfession> villagerProfession = VillagerProfession.class.getDeclaredConstructor(String.class, PointOfInterestType.class, ImmutableSet.class, ImmutableSet.class);
            villagerProfession.setAccessible(true);
            profession1 = villagerProfession.newInstance(id, pointOfInterestType_1, immutableSet_1, immutableSet_1);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(Objects.requireNonNull(profession1).toString());
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(Neutronia.MOD_ID, id), profession1);
    }

}