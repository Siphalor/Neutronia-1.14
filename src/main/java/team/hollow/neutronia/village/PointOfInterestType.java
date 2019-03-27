package team.hollow.neutronia.village;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BedPart;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloadListener;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.init.NTags;
import team.hollow.neutronia.utils.registry.NRegistries;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
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

    static {
        UNEMPLOYED = register("unemployed", BlockTags.UNEMPLOYED_POI, 1, null, IS_USED_BY_PROFESSION);
        ARMORER = register("armorer", BlockTags.ARMORER_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_ARMORER);
        BUTCHER = register("butcher", BlockTags.BUTCHER_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_BUTCHER);
        BARD = register("bard", NTags.BARD_POI, 1, null);
        CARTOGRAPHER = register("cartographer", BlockTags.CARTOGRAPHER_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_CARTOGRAPHER);
        CLERIC = register("cleric", BlockTags.CLERIC_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_CLERIC);
        FARMER = register("farmer", BlockTags.FARMER_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_FARMER);
        FISHERMAN = register("fisherman", BlockTags.FISHERMAN_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_FISHERMAN);
        FLETCHER = register("fletcher", BlockTags.FLETCHER_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_FLETCHER);
        LEATHERWORKER = register("leatherworker", BlockTags.LEATHERWORKER_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_LEATHERWORKER);
        LIBRARIAN = register("librarian", BlockTags.LIBRARIAN_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN);
        MASON = register("mason", BlockTags.MASON_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_MASON);
        NITWIT = register("nitwit", BlockTags.NITWIT_POI, 1, null);
        SHEPHERD = register("shepherd", BlockTags.SHEPHERD_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_SHEPHERD);
        TOOLSMITH = register("toolsmith", BlockTags.TOOLSMITH_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_TOOLSMITH);
        WEAPONSMITH = register("weaponsmith", BlockTags.WEAPONSMITH_POI, 1, SoundEvents.ENTITY_VILLAGER_WORK_WEAPONSMITH);
        HOME = register("home", BlockTags.BEDS, 1, null);
        MEETING = register("meeting", BlockTags.MEETING_SITE_POI, 32, null);
        field_18849 = Maps.newHashMap();
    }

    private final String id;
    private final Tag<Block> blockTag;
    private final Set<BlockState> field_18850 = Sets.newHashSet();
    private final int ticketCount;
    private final SoundEvent sound;
    private final Predicate<PointOfInterestType> completedCondition;

    private PointOfInterestType(String string_1, Tag<Block> tag_1, int int_1, SoundEvent soundEvent_1, Predicate<PointOfInterestType> predicate_1) {
        this.id = string_1;
        this.blockTag = tag_1;
        this.ticketCount = int_1;
        this.sound = soundEvent_1;
        this.completedCondition = predicate_1;
    }

    private PointOfInterestType(String string_1, Tag<Block> tag_1, int int_1, SoundEvent soundEvent_1) {
        this.id = string_1;
        this.blockTag = tag_1;
        this.ticketCount = int_1;
        this.sound = soundEvent_1;
        this.completedCondition = (pointOfInterestType_1) -> pointOfInterestType_1 == this;
    }

    private static PointOfInterestType register(String string_1, Tag<Block> tag_1, int int_1, SoundEvent soundEvent_1) {
        return NRegistries.POINT_OF_INTEREST_TYPE.add(new Identifier(string_1), new PointOfInterestType(string_1, tag_1, int_1, soundEvent_1));
    }

    private static PointOfInterestType register(String string_1, Tag<Block> tag_1, int int_1, SoundEvent soundEvent_1, Predicate<PointOfInterestType> predicate_1) {
        return NRegistries.POINT_OF_INTEREST_TYPE.add(new Identifier(string_1), new PointOfInterestType(string_1, tag_1, int_1, soundEvent_1, predicate_1));
    }

    public static Optional<PointOfInterestType> method_19516(BlockState blockState_1) {
        return Optional.ofNullable(field_18849.get(blockState_1));
    }

    private static boolean method_19517(BlockState blockState_1) {
        return !blockState_1.matches(BlockTags.BEDS) || blockState_1.get(BedBlock.PART) != BedPart.FOOT;
    }

    public static Stream<BlockState> method_19518() {
        return field_18849.keySet().stream();
    }

    public static CompletableFuture<Void> method_19515(ResourceReloadListener.Helper resourceReloadListener$Helper_1, ResourceManager resourceManager_1, Profiler profiler_1, Profiler profiler_2, Executor executor_1, Executor executor_2) {
        return resourceReloadListener$Helper_1.waitForAll(net.minecraft.util.Void.INSTANCE).thenRunAsync(() -> {
            field_18849.clear();
            NRegistries.POINT_OF_INTEREST_TYPE.forEach((pointOfInterestType_1) -> {
                pointOfInterestType_1.field_18850.clear();
            });
            Registry.BLOCK.stream().filter((block_1) -> block_1.matches(BlockTags.POINTS_OF_INTEREST)).forEach((block_1) -> {
                List<PointOfInterestType> list_1 = NRegistries.POINT_OF_INTEREST_TYPE.stream().filter((pointOfInterestType_1x) ->
                        pointOfInterestType_1x.blockTag.contains(block_1)).collect(Collectors.toList());
                if (list_1.size() > 1) {
                    throw new IllegalStateException(String.format("%s is defined in too many tags", block_1));
                } else {
                    PointOfInterestType pointOfInterestType_1 = list_1.get(0);
                    block_1.getStateFactory().getStates().stream().filter(PointOfInterestType::method_19517).forEach((blockState_1) -> {
                        pointOfInterestType_1.field_18850.add(blockState_1);
                        field_18849.put(blockState_1, pointOfInterestType_1);
                    });
                }
            });
        }, executor_2);
    }

    public int getTicketCount() {
        return this.ticketCount;
    }

    public Predicate<PointOfInterestType> getCompletedCondition() {
        return this.completedCondition;
    }

    public String toString() {
        return this.id;
    }

    public SoundEvent getSound() {
        return this.sound;
    }
}
