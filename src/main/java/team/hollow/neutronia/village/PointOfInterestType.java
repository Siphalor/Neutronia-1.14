package team.hollow.neutronia.village;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BedPart;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.utils.registry.NRegistries;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PointOfInterestType {
    public static final Predicate<PointOfInterestType> IS_USED_BY_PROFESSION = (pointOfInterestType_1) ->
            NRegistries.VILLAGER_PROFESSION.stream().map(VillagerPlusProfession::getWorkStation).collect(Collectors.toSet()).contains(pointOfInterestType_1);
    public static final Predicate<PointOfInterestType> ALWAYS_TRUE = (pointOfInterestType_1) -> true;
    public static final PointOfInterestType UNEMPLOYED;
    public static final PointOfInterestType ARMORER;
    public static final PointOfInterestType BUTCHER;
    public static final PointOfInterestType BARD;
    public static final PointOfInterestType CARTOGRAPHER;
    public static final PointOfInterestType CLERIC;
    public static final PointOfInterestType FARMER;
    public static final PointOfInterestType FISHERMAN;
    public static final PointOfInterestType FLETCHER;
    public static final PointOfInterestType LEATHERWORKER;
    public static final PointOfInterestType LIBRARIAN;
    public static final PointOfInterestType MASON;
    public static final PointOfInterestType NITWIT;
    public static final PointOfInterestType SHEPHERD;
    public static final PointOfInterestType TOOLSMITH;
    public static final PointOfInterestType WEAPONSMITH;
    public static final PointOfInterestType HOME;
    public static final PointOfInterestType MEETING;
    private static final Map<BlockState, PointOfInterestType> field_18849;
    private static final Set<BlockState> BEDS;
    private static final Set<BlockState> MUSIC_BLOCKS;

    private final String id;
    private final Set<BlockState> field_18850;
    private final int ticketCount;
    private final SoundEvent sound;
    private final Predicate<PointOfInterestType> completionCondition;

    private static Set<BlockState> method_20356(Block block_1) {
        return ImmutableSet.copyOf(block_1.getStateFactory().getStates());
    }

    private PointOfInterestType(String string_1, Set<BlockState> tag_1, int int_1, SoundEvent soundEvent_1, Predicate<PointOfInterestType> predicate_1) {
        this.id = string_1;
        this.field_18850 = ImmutableSet.copyOf(tag_1);
        this.ticketCount = int_1;
        this.sound = soundEvent_1;
        this.completionCondition = predicate_1;
    }

    private PointOfInterestType(String string_1, Set<BlockState> tag_1, int int_1, SoundEvent soundEvent_1) {
        this.id = string_1;
        this.field_18850 = ImmutableSet.copyOf(tag_1);
        this.ticketCount = int_1;
        this.sound = soundEvent_1;
        this.completionCondition = (pointOfInterestType_1) -> pointOfInterestType_1 == this;
    }

    public int getTicketCount() {
        return this.ticketCount;
    }

    public Predicate<PointOfInterestType> getCompletionCondition() {
        return this.completionCondition;
    }

    public String toString() {
        return this.id;
    }

    public SoundEvent getSound() {
        return this.sound;
    }

    private static PointOfInterestType register(String string_1, Set<BlockState> set_1, int int_1, SoundEvent soundEvent_1) {
        return method_20354(NRegistries.POINT_OF_INTEREST_TYPE.add(new Identifier(string_1), new PointOfInterestType(string_1, set_1, int_1, soundEvent_1)));
    }

    private static PointOfInterestType register(String string_1, Set<BlockState> set_1, int int_1, SoundEvent soundEvent_1, Predicate<PointOfInterestType> predicate_1) {
        return method_20354(NRegistries.POINT_OF_INTEREST_TYPE.add(new Identifier(string_1), new PointOfInterestType(string_1, set_1, int_1, soundEvent_1, predicate_1)));
    }

    private static PointOfInterestType method_20354(PointOfInterestType pointOfInterestType_1) {
        pointOfInterestType_1.field_18850.forEach((blockState_1) -> {
            PointOfInterestType pointOfInterestType_2 = field_18849.put(blockState_1, pointOfInterestType_1);
            if (pointOfInterestType_2 != null) {
                throw new IllegalStateException(String.format("%s is defined in too many tags", blockState_1));
            }
        });
        return pointOfInterestType_1;
    }

    public static Optional<PointOfInterestType> method_19516(BlockState blockState_1) {
        return Optional.ofNullable(field_18849.get(blockState_1));
    }

    public static Stream<BlockState> method_19518() {
        return field_18849.keySet().stream();
    }

    static {
        BEDS = ImmutableList.of(Blocks.RED_BED, Blocks.BLACK_BED, Blocks.BLUE_BED, Blocks.BROWN_BED, Blocks.CYAN_BED, Blocks.GRAY_BED, Blocks.GREEN_BED,
                Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_GRAY_BED, Blocks.LIME_BED, Blocks.MAGENTA_BED, Blocks.ORANGE_BED, new Block[]{Blocks.PINK_BED, Blocks.PURPLE_BED,
                        Blocks.WHITE_BED, Blocks.YELLOW_BED}).stream().flatMap((block_1) -> block_1.getStateFactory().getStates().stream()).filter((blockState_1) ->
                blockState_1.get(BedBlock.PART) == BedPart.HEAD).collect(ImmutableSet.toImmutableSet());
        MUSIC_BLOCKS = ImmutableList.of(Blocks.NOTE_BLOCK, Blocks.JUKEBOX).stream().flatMap(block -> block.getStateFactory().getStates().stream()).filter(blockState ->
                blockState.get(BedBlock.PART) == BedPart.HEAD).collect(ImmutableSet.toImmutableSet());

        UNEMPLOYED = register("unemployed", ImmutableSet.of(), 1, null, IS_USED_BY_PROFESSION);
        ARMORER = register("armorer", method_20356(Blocks.BLAST_FURNACE), 1, SoundEvents.ENTITY_VILLAGER_WORK_ARMORER);
        BUTCHER = register("butcher", method_20356(Blocks.SMOKER), 1, SoundEvents.ENTITY_VILLAGER_WORK_BUTCHER);
        CARTOGRAPHER = register("cartographer", method_20356(Blocks.CARTOGRAPHY_TABLE), 1, SoundEvents.ENTITY_VILLAGER_WORK_CARTOGRAPHER);
        CLERIC = register("cleric", method_20356(Blocks.BREWING_STAND), 1, SoundEvents.ENTITY_VILLAGER_WORK_CLERIC);
        FARMER = register("farmer", method_20356(Blocks.COMPOSTER), 1, SoundEvents.ENTITY_VILLAGER_WORK_FARMER);
        FISHERMAN = register("fisherman", method_20356(Blocks.BARREL), 1, SoundEvents.ENTITY_VILLAGER_WORK_FISHERMAN);
        FLETCHER = register("fletcher", method_20356(Blocks.FLETCHING_TABLE), 1, SoundEvents.ENTITY_VILLAGER_WORK_FLETCHER);
        LEATHERWORKER = register("leatherworker", method_20356(Blocks.CAULDRON), 1, SoundEvents.ENTITY_VILLAGER_WORK_LEATHERWORKER);
        LIBRARIAN = register("librarian", method_20356(Blocks.LECTERN), 1, SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN);
        MASON = register("mason", method_20356(Blocks.STONECUTTER), 1, SoundEvents.ENTITY_VILLAGER_WORK_MASON);
        NITWIT = register("nitwit", ImmutableSet.of(), 1, null);
        SHEPHERD = register("shepherd", method_20356(Blocks.LOOM), 1, SoundEvents.ENTITY_VILLAGER_WORK_SHEPHERD);
        TOOLSMITH = register("toolsmith", method_20356(Blocks.SMITHING_TABLE), 1, SoundEvents.ENTITY_VILLAGER_WORK_TOOLSMITH);
        WEAPONSMITH = register("weaponsmith", method_20356(Blocks.GRINDSTONE), 1, SoundEvents.ENTITY_VILLAGER_WORK_WEAPONSMITH);
        BARD = register("bard", MUSIC_BLOCKS, 1, SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN);
        HOME = register("home", BEDS, 1, null);
        MEETING = register("meeting", method_20356(Blocks.BELL), 32, null);
        field_18849 = Maps.newHashMap();
    }

}
