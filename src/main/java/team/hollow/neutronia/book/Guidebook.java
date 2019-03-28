package team.hollow.neutronia.book;

import com.google.gson.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.loot.LootPool;
import net.minecraft.world.loot.context.LootContextType;
import net.minecraft.world.loot.context.LootContextTypes;

import java.lang.reflect.Type;

public class Guidebook {

    public static class Serializer implements JsonDeserializer<Guidebook>, JsonSerializer<Guidebook> {
        @Override
        public Guidebook deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject_1 = JsonHelper.asObject(json, "book");
            LootPool[] lootPools_1 = JsonHelper.deserialize(jsonObject_1, "pools", new LootPool[0], context, LootPool[].class);
            LootContextType lootContextType_1 = null;
            if (jsonObject_1.has("type")) {
                String string_1 = JsonHelper.getString(jsonObject_1, "type");
                lootContextType_1 = LootContextTypes.get(new Identifier(string_1));
            }

            Guidebook[] lootFunctions_1 = JsonHelper.deserialize(jsonObject_1, "functions", new Guidebook[0], context, Guidebook[].class);
            return null;
        }

        @Override
        public JsonElement serialize(Guidebook src, Type typeOfSrc, JsonSerializationContext context) {
            return null;
        }
    }

}
