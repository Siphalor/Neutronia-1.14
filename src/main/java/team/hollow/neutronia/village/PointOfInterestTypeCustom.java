package team.hollow.neutronia.village;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.village.PointOfInterestType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;

public class PointOfInterestTypeCustom {

    String id;
    private Set<BlockState> workStationStates;
    private int ticketCount;
    private SoundEvent sound;

    public PointOfInterestTypeCustom(String id, Set<BlockState> workStationStates, int ticketCount, SoundEvent sound) {
        this.id = id;
        this.workStationStates = workStationStates;
        this.ticketCount = ticketCount;
        this.sound = sound;
    }

    public static Set<BlockState> getAllStatesOf(Block block_1) {
        return ImmutableSet.copyOf(block_1.getStateFactory().getStates());
    }

    public PointOfInterestType register() {
        PointOfInterestType interestType = null;
        try {
            Constructor<PointOfInterestType> pointOfInterestType = PointOfInterestType.class.getDeclaredConstructor(String.class, Set.class, int.class, SoundEvent.class);
            pointOfInterestType.setAccessible(true);
            interestType = pointOfInterestType.newInstance(id, workStationStates, ticketCount, sound);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(Objects.requireNonNull(interestType).toString());
        return interestType;
    }

}
