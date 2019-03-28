/*
package team.hollow.neutronia.init;

import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.utils.registry.NRegistries;
import team.hollow.neutronia.village.VillagerPlusData;

public class NTrackedDataHandlerRegistry {

    public static final TrackedDataHandler<VillagerPlusData> VILLAGER_DATA = new TrackedDataHandler<VillagerPlusData>() {
        public void write(PacketByteBuf packetByteBuf_1, VillagerPlusData villagerData_1) {
            packetByteBuf_1.writeVarInt(Registry.VILLAGER_TYPE.getRawId(villagerData_1.getType()));
            packetByteBuf_1.writeVarInt(NRegistries.VILLAGER_PROFESSION.getRawId(villagerData_1.getProfession()));
            packetByteBuf_1.writeVarInt(villagerData_1.getLevel());
        }

        public VillagerPlusData read(PacketByteBuf packetByteBuf_1) {
            return new VillagerPlusData(Registry.VILLAGER_TYPE.get(packetByteBuf_1.readVarInt()), NRegistries.VILLAGER_PROFESSION.get(packetByteBuf_1.readVarInt()), packetByteBuf_1.readVarInt());
        }

        public VillagerPlusData copy(VillagerPlusData villagerData_1) {
            return villagerData_1;
        }

    };

}
*/
