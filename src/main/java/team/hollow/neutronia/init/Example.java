package team.hollow.neutronia.init;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.village.VillagerProfession;
import team.hollow.neutronia.Neutronia;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

class Example {

    private static <T> T construct(Class<T> tClass, Object... args) {
        for (Constructor<?> constructor : tClass.getConstructors()) {
            constructor.setAccessible(true);
            try {
                return (T) constructor.newInstance(args);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalArgumentException ignored) {
            }
        }
        throw new IllegalArgumentException("None of the constructors matched the provided args!");
    }

    static void register(String poiName, String professionName) {
        Identifier bardPoi = new Identifier(Neutronia.MOD_ID, poiName);
        Identifier bardProfession = new Identifier(Neutronia.MOD_ID, professionName);

        Class blockTagClass = Arrays.stream(BlockTags.class.getDeclaredClasses())
                .filter(Tag.class::isAssignableFrom)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No class within BlocksTags extends Tag!"));

        //noinspection unchecked
        Tag<Block> poiBlockTag = construct(
                (Class<Tag<Block>>) blockTagClass,
                bardPoi);

        PointOfInterestType poiType = construct(
                PointOfInterestType.class,
                bardPoi.toString(), poiBlockTag, 1, SoundEvents.ENTITY_VILLAGER_WORK_TOOLSMITH);

        VillagerProfession profession = construct(
                VillagerProfession.class,
                bardProfession.toString(), poiType, ImmutableSet.<Item>of(), ImmutableSet.<Block>of());

        Registry.register(Registry.POINT_OF_INTEREST_TYPE, bardPoi, poiType);
        Registry.register(Registry.VILLAGER_PROFESSION, bardProfession, profession);
    }
}