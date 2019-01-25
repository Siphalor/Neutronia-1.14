package team.abnormals.neutronia.recipe;

import com.google.gson.JsonObject;
import net.minecraft.class_3972;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import team.abnormals.neutronia.init.NRecipeSerializers;
import team.abnormals.neutronia.init.NRecipeType;

public class SawmillingRecipe extends class_3972 {

    public SawmillingRecipe(Identifier identifier_1, String string_1, Ingredient ingredient_1, ItemStack itemStack_1) {
        super(NRecipeType.SAWMILLING, NRecipeSerializers.SAWMILLING, identifier_1, string_1, ingredient_1, itemStack_1);
    }

    public boolean matches(Inventory inventory_1, World world_1) {
        return this.input.matches(inventory_1.getInvStack(0));
    }

    public static class Serilizer<T extends SawmillingRecipe> implements RecipeSerializer<SawmillingRecipe> {

        private final SawmillingRecipe.Serilizer.RecipeFactory<T> recipeFactory;

        public Serilizer(SawmillingRecipe.Serilizer.RecipeFactory<T> class_3972$class_3973$class_3974_1) {
            this.recipeFactory = class_3972$class_3973$class_3974_1;
        }

        @Override
        public T read(Identifier identifier_1, JsonObject jsonObject_1) {
            String string_1 = JsonHelper.getString(jsonObject_1, "group", "");
            Ingredient ingredient_2;
            if (JsonHelper.hasArray(jsonObject_1, "ingredient")) {
                ingredient_2 = Ingredient.fromJson(JsonHelper.getArray(jsonObject_1, "ingredient"));
            } else {
                ingredient_2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject_1, "ingredient"));
            }

            String string_2 = JsonHelper.getString(jsonObject_1, "result");
            int int_1 = JsonHelper.getInt(jsonObject_1, "count");
            ItemStack itemStack_1 = new ItemStack(Registry.ITEM.get(new Identifier(string_2)), int_1);
            return this.recipeFactory.create(identifier_1, string_1, ingredient_2, itemStack_1);
        }

        @Override
        public SawmillingRecipe read(Identifier var1, PacketByteBuf var2) {
            String string_1 = var2.readString(32767);
            Ingredient ingredient_1 = Ingredient.fromPacket(var2);
            ItemStack itemStack_1 = var2.readItemStack();
            return this.recipeFactory.create(var1, string_1, ingredient_1, itemStack_1);
        }

        @Override
        public void write(PacketByteBuf var1, SawmillingRecipe var2) {
            var1.writeString(var2.group);
            var2.input.write(var1);
            var1.writeItemStack(var2.output);
        }

        public interface RecipeFactory<T extends SawmillingRecipe> {
            T create(Identifier var1, String var2, Ingredient var3, ItemStack var4);
        }

    }

}