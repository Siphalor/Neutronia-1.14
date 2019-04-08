//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.social_villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class VillagerProfession {
    public static final VillagerProfession NONE;
    public static final VillagerProfession ARMORER;
    public static final VillagerProfession BUTCHER;
    public static final VillagerProfession CARTOGRAPHER;
    public static final VillagerProfession CLERIC;
    public static final VillagerProfession FARMER;
    public static final VillagerProfession FISHERMAN;
    public static final VillagerProfession FLETCHER;
    public static final VillagerProfession LEATHERWORKER;
    public static final VillagerProfession LIBRARIAN;
    public static final VillagerProfession MASON;
    public static final VillagerProfession NITWIT;
    public static final VillagerProfession SHEPHERD;
    public static final VillagerProfession TOOLSMITH;
    public static final VillagerProfession WEAPONSMITH;
    private final String id;
    private final ImmutableSet<Item> gatherableItems;
    private final ImmutableSet<Block> field_18880;

    private VillagerProfession(String string_1, ImmutableSet<Item> immutableSet_1, ImmutableSet<Block> immutableSet_2) {
        this.id = string_1;
        this.gatherableItems = immutableSet_1;
        this.field_18880 = immutableSet_2;
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

    static VillagerProfession register(String string_1) {
        return register(string_1, ImmutableSet.of(), ImmutableSet.of());
    }

    static VillagerProfession register(String string_1, ImmutableSet<Item> immutableSet_1, ImmutableSet<Block> immutableSet_2) {
        return null;
    }

    static {
        NONE = register("none");
        ARMORER = register("armorer");
        BUTCHER = register("butcher");
        CARTOGRAPHER = register("cartographer");
        CLERIC = register("cleric");
        FARMER = register("farmer", ImmutableSet.of(Items.WHEAT, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS), ImmutableSet.of(Blocks.FARMLAND));
        FISHERMAN = register("fisherman");
        FLETCHER = register("fletcher");
        LEATHERWORKER = register("leatherworker");
        LIBRARIAN = register("librarian");
        MASON = register("mason");
        NITWIT = register("nitwit");
        SHEPHERD = register("shepherd");
        TOOLSMITH = register("toolsmith");
        WEAPONSMITH = register("weaponsmith");
    }
}
