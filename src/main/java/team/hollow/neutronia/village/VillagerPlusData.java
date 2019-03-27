package team.hollow.neutronia.village;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.utils.registry.NRegistries;

public class VillagerPlusData {
   private static final int[] LEVEL_BASE_EXPERIENCE = new int[]{0, 10, 50, 100, 150};
   private final net.minecraft.village.VillagerType type;
   private final VillagerPlusProfession profession;
   private final String sex;
   private final int level;

   public VillagerPlusData(net.minecraft.village.VillagerType villagerType_1, VillagerPlusProfession villagerProfession_1, int int_1, String sex) {
      this.type = villagerType_1;
      this.profession = villagerProfession_1;
      this.level = Math.max(1, int_1);
      this.sex = sex;
   }

   public VillagerPlusData(Dynamic<?> dynamic_1) {
      this(Registry.VILLAGER_TYPE.get(Identifier.create(dynamic_1.get("type").asString(""))), NRegistries.VILLAGER_PROFESSION.get(Identifier.create(dynamic_1.get("profession").asString(""))), dynamic_1.get("level").asInt(1), dynamic_1.get("sex").asString(""));
   }

   public net.minecraft.village.VillagerType getType() {
      return this.type;
   }

   public VillagerPlusProfession getProfession() {
      return this.profession;
   }

   public int getLevel() {
      return this.level;
   }

   public VillagerPlusData withType(net.minecraft.village.VillagerType villagerType_1) {
      return new VillagerPlusData(villagerType_1, this.profession, this.level, this.sex);
   }

   public VillagerPlusData withProfession(VillagerPlusProfession villagerProfession_1) {
      return new VillagerPlusData(this.type, villagerProfession_1, this.level, this.sex);
   }

   public VillagerPlusData withLevel(int int_1) {
      return new VillagerPlusData(this.type, this.profession, int_1, this.sex);
   }

   public VillagerPlusData withSex(String sex) {
      return new VillagerPlusData(this.type, this.profession, this.level, sex);
   }

   public <T> T serialize(DynamicOps<T> dynamicOps_1) {
      return dynamicOps_1.createMap(ImmutableMap.of(dynamicOps_1.createString("type"), dynamicOps_1.createString(Registry.VILLAGER_TYPE.getId(this.type).toString()), dynamicOps_1.createString("profession"), dynamicOps_1.createString(NRegistries.VILLAGER_PROFESSION.getId(this.profession).toString()), dynamicOps_1.createString("level"), dynamicOps_1.createInt(this.level)));
   }

   public static int getLowerLevelExperience(int int_1) {
      return isLevelValid(int_1) ? LEVEL_BASE_EXPERIENCE[int_1 - 1] : 0;
   }

   public static int getUpperLevelExperience(int int_1) {
      return isLevelValid(int_1) ? LEVEL_BASE_EXPERIENCE[int_1] : 0;
   }

   public static boolean isLevelValid(int int_1) {
      return int_1 >= 1 && int_1 < 5;
   }
}
