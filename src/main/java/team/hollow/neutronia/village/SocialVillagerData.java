/*
package team.hollow.neutronia.village;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.init.NRegistries;

public class SocialVillagerData {
   private final SocialVillagerProfession profession;

   public SocialVillagerData(SocialVillagerProfession villagerProfession_1) {
      this.profession = villagerProfession_1;
   }

   public SocialVillagerData(Dynamic<?> dynamic_1) {
      this(NRegistries.VILLAGER_PROFESSION.get(Identifier.create(dynamic_1.get("profession").asString(""))));
   }

   public SocialVillagerProfession getProfession() {
      return this.profession;
   }

   public SocialVillagerData withProfession(SocialVillagerProfession villagerProfession_1) {
      return new SocialVillagerData(villagerProfession_1);
   }

   public <T> T serialize(DynamicOps<T> dynamicOps_1) {
      return dynamicOps_1.createMap(ImmutableMap.of(dynamicOps_1.createString("profession"), dynamicOps_1.createString(NRegistries.VILLAGER_PROFESSION.getId(this.profession).toString())));
   }

}
*/
