package ink.huix.optimal.mixin.item.recipe;

import ink.huix.optimal.api.recipe.RegisterHelper;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.List;

@Mixin(CraftingManager.class)
public class CraftingManagerHelper {
   @Redirect(method = "<init>",
           at = @At(value = "INVOKE",target = "Lnet/minecraft/RecipesMITE;addCraftingRecipes(Lnet/minecraft/CraftingManager;)V"))
   private void injectRegisterRecipes(CraftingManager crafters) {
      RegisterHelper.registerAllItems();
      RecipesMITE.addCraftingRecipes(crafters);
      RegisterHelper.registerAllRecipes(crafters);
   }

}
