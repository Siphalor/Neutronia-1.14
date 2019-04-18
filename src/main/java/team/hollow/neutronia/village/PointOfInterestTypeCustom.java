package team.hollow.neutronia.village;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import team.hollow.neutronia.event_system.Reflect;

import java.util.Set;

public class PointOfInterestTypeCustom {

    String id;
    Set<BlockState> workStationStates;
    int ticketCount;
    SoundEvent sound;

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

        return (PointOfInterestType) Reflect.constructClass(PointOfInterestType.class, id, workStationStates, ticketCount, sound);
    }

}
