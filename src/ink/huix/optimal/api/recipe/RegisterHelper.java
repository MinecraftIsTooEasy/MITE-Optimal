package ink.huix.optimal.api.recipe;

import ink.huix.optimal.mixin.item.recipe.CraftingManagerInvoker;
import net.minecraft.CraftingManager;
import net.minecraft.ShapedRecipes;
import net.minecraft.ShapelessRecipes;

import java.util.ArrayList;

@SuppressWarnings("RedundantCast")
public class RegisterHelper {
    public static ArrayList<RecipesArgs> shapedRecipes = new ArrayList<>();
    public static ArrayList<RecipesArgs> shapelessRecipe = new ArrayList<>();
    public static void registerAllItems(){

    }

    public static void registerAllRecipes(CraftingManager crafters){

        RecipesArgs recipesArgs;
        for (RecipesArgs shapedRecipe : shapedRecipes) {
            recipesArgs = shapedRecipe;
            ShapedRecipes shapedRecipes = ((CraftingManagerInvoker) crafters).addRecipeForMITE(recipesArgs.result, recipesArgs.include_in_lowest_crafting_difficulty_determination, recipesArgs.inputs);
            if (recipesArgs.isExtendsNBT()){
                shapedRecipes.func_92100_c();
            }
        }
        for (RecipesArgs args : shapelessRecipe) {
            recipesArgs = args;
            ShapelessRecipes shapelessRecipes = ((CraftingManagerInvoker) crafters).addShapelessRecipeForMITE(recipesArgs.result, recipesArgs.include_in_lowest_crafting_difficulty_determination, recipesArgs.inputs);
            if (recipesArgs.isExtendsNBT()){
                shapelessRecipes.propagateTagCompound();
            }
        }
    }
}
