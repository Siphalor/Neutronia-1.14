package team.abnormals.neutronia.init;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.recipe.SawmillingRecipe;

public class NRecipeSerializers {

    public static final RecipeSerializer<SawmillingRecipe> SAWMILLING = register("sawmilling", new SawmillingRecipe.Serilizer<>(SawmillingRecipe::new));

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String string_1, S recipeSerializer_1) {
        return Registry.register(Registry.RECIPE_SERIALIZER, string_1, recipeSerializer_1);
    }

}
