/*
package team.hollow.neutronia.village;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import team.hollow.neutronia.Neutronia;
//import team.hollow.neutronia.init.NRegistries;

public class SocialVillagerProfession {
   public static final SocialVillagerProfession NONE;
   public static final SocialVillagerProfession ARMORER;
   public static final SocialVillagerProfession BUTCHER;
   public static final SocialVillagerProfession CARTOGRAPHER;
   public static final SocialVillagerProfession CLERIC;
   public static final SocialVillagerProfession FARMER;
   public static final SocialVillagerProfession FISHERMAN;
   public static final SocialVillagerProfession FLETCHER;
   public static final SocialVillagerProfession LEATHERWORKER;
   public static final SocialVillagerProfession LIBRARIAN;
   public static final SocialVillagerProfession MASON;
   public static final SocialVillagerProfession NITWIT;
   public static final SocialVillagerProfession SHEPHERD;
   public static final SocialVillagerProfession TOOLSMITH;
   public static final SocialVillagerProfession WEAPONSMITH;
   private final String id;
   private final PointOfInterestType workStation;
   private final ImmutableSet<Item> gatherableItems;
   private final ImmutableSet<Block> secondaryJobSites;

   public SocialVillagerProfession(String string_1, PointOfInterestType pointOfInterestType_1, ImmutableSet<Item> immutableSet_1, ImmutableSet<Block> immutableSet_2) {
      this.id = string_1;
      this.workStation = pointOfInterestType_1;
      this.gatherableItems = immutableSet_1;
      this.secondaryJobSites = immutableSet_2;
   }

   public PointOfInterestType getWorkStation() {
      return this.workStation;
   }

   public ImmutableSet<Item> getGatherableItems() {
      return this.gatherableItems;
   }

   public ImmutableSet<Block> getSecondaryJobSites() {
      return this.secondaryJobSites;
   }

   public String toString() {
      return this.id;
   }

   static SocialVillagerProfession register(String string_1, PointOfInterestType pointOfInterestType_1) {
      return register(string_1, pointOfInterestType_1, ImmutableSet.of(), ImmutableSet.of());
   }

   static SocialVillagerProfession register(String string_1, PointOfInterestType pointOfInterestType_1, ImmutableSet<Item> immutableSet_1, ImmutableSet<Block> immutableSet_2) {
      return Registry.register(NRegistries.VILLAGER_PROFESSION, (new Identifier(Neutronia.MOD_ID, string_1)), new SocialVillagerProfession(string_1, pointOfInterestType_1, immutableSet_1, immutableSet_2));
   }

   static {
      NONE = register("none", PointOfInterestType.UNEMPLOYED);
      ARMORER = register("armorer", PointOfInterestType.ARMORER);
      BUTCHER = register("butcher", PointOfInterestType.BUTCHER);
      CARTOGRAPHER = register("cartographer", PointOfInterestType.CARTOGRAPHER);
      CLERIC = register("cleric", PointOfInterestType.CLERIC);
      FARMER = register("farmer", PointOfInterestType.FARMER, ImmutableSet.of(Items.WHEAT, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS), ImmutableSet.of(Blocks.FARMLAND));
      FISHERMAN = register("fisherman", PointOfInterestType.FISHERMAN);
      FLETCHER = register("fletcher", PointOfInterestType.FLETCHER);
      LEATHERWORKER = register("leatherworker", PointOfInterestType.LEATHERWORKER);
      LIBRARIAN = register("librarian", PointOfInterestType.LIBRARIAN);
      MASON = register("mason", PointOfInterestType.MASON);
      NITWIT = register("nitwit", PointOfInterestType.NITWIT);
      SHEPHERD = register("shepherd", PointOfInterestType.SHEPHERD);
      TOOLSMITH = register("toolsmith", PointOfInterestType.TOOLSMITH);
      WEAPONSMITH = register("weaponsmith", PointOfInterestType.WEAPONSMITH);
   }
}
*/
