package team.hollow.neutronia.village;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.utils.registry.NRegistries;

public class VillagerPlusProfession {

    public static final VillagerPlusProfession NONE = register("none", PointOfInterestType.UNEMPLOYED);
    public static final VillagerPlusProfession NITWIT = register("nitwit", PointOfInterestType.NITWIT);
    public static final VillagerPlusProfession BARD = register("bard", PointOfInterestType.BARD);
    public static final VillagerPlusProfession CHIEF = register("chief", PointOfInterestType.FARMER);
    public static final VillagerPlusProfession CARPENTER = register("carpenter", PointOfInterestType.FARMER);
    public static final VillagerPlusProfession BAKER = register("baker", PointOfInterestType.FARMER, ImmutableSet.of(Items.WHEAT, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS), ImmutableSet.of(Blocks.FARMLAND));
    public static final VillagerPlusProfession BUTCHER = register("butcher", PointOfInterestType.FARMER);
    public static final VillagerPlusProfession LIBRARIAN = register("librarian", PointOfInterestType.LIBRARIAN);
    public static final VillagerPlusProfession DRUID = register("druid", PointOfInterestType.FARMER);
    public static final VillagerPlusProfession NOMAD = register("nomad", PointOfInterestType.FARMER);
    public static final VillagerPlusProfession CLERIC = register("cleric", PointOfInterestType.CLERIC);
    public static final VillagerPlusProfession TRADESMAN = register("tradesman", PointOfInterestType.FARMER);
    public static final VillagerPlusProfession FARMER = register("farmer", PointOfInterestType.FARMER);

    private final String id;
    private final PointOfInterestType workStation;
    private final ImmutableSet<Item> gatherableItems;
    private final ImmutableSet<Block> field_18880;

    public VillagerPlusProfession(String string_1, PointOfInterestType pointOfInterestType_1, ImmutableSet<Item> immutableSet_1, ImmutableSet<Block> immutableSet_2) {
        this.id = string_1;
        this.workStation = pointOfInterestType_1;
        this.gatherableItems = immutableSet_1;
        this.field_18880 = immutableSet_2;
    }

    private static VillagerPlusProfession register(String string_1, PointOfInterestType pointOfInterestType_1) {
        return register(string_1, pointOfInterestType_1, ImmutableSet.of(), ImmutableSet.of());
    }

    private static VillagerPlusProfession register(String string_1, PointOfInterestType pointOfInterestType_1, ImmutableSet<Item> immutableSet_1, ImmutableSet<Block> immutableSet_2) {
        return Registry.register(NRegistries.VILLAGER_PROFESSION, (new Identifier(Neutronia.MOD_ID, string_1)), new VillagerPlusProfession(string_1, pointOfInterestType_1, immutableSet_1, immutableSet_2));
    }

    public PointOfInterestType getWorkStation() {
        return this.workStation;
    }

    public ImmutableSet<Item> getGatherableItems() {
        return this.gatherableItems;
    }

    public ImmutableSet<Block> method_19630() {
        return this.field_18880;
    }

    public String toString() {
        return this.id;
    }

}
