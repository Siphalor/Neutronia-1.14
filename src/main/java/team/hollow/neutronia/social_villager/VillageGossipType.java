package team.hollow.neutronia.social_villager;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public enum VillageGossipType {
    MAJOR_NEGATIVE("major_negative", -5, 100, 1, 10),
    MINOR_NEGATIVE("minor_negative", -1, 200, 2, 20),
    MINOR_POSITIVE("minor_positive", 1, 200, 2, 20),
    MAJOR_POSITIVE("major_positive", 5, 100, 1, 10),
    TRADING("trading", 1, 25, 2, 20),
    GOLEM("golem", 1, 100, 1, 1);

    private static final Map<String, VillageGossipType> BY_KEY = Stream.of(values()).collect(ImmutableMap.toImmutableMap((villageGossipType_1) -> villageGossipType_1.key, Function.identity()));
    public final String key;
    public final int multiplier;
    public final int maxReputation;
    public final int field_18433;
    public final int value;

    VillageGossipType(String string_1, int int_1, int int_2, int int_3, int int_4) {
        this.key = string_1;
        this.multiplier = int_1;
        this.maxReputation = int_2;
        this.field_18433 = int_3;
        this.value = int_4;
    }

    public static VillageGossipType byKey(String string_1) {
        return BY_KEY.get(string_1);
    }
}
