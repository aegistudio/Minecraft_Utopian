package net.minecraft.item.crafting;

import java.util.Comparator;

class RecipeSorter implements Comparator<IRecipe>
{
    final CraftingManager craftingManager;

    RecipeSorter(CraftingManager par1CraftingManager)
    {
        this.craftingManager = par1CraftingManager;
    }

    public int compareRecipes(IRecipe par1IRecipe, IRecipe par2IRecipe)
    {
        return par1IRecipe instanceof ShapelessRecipes && par2IRecipe instanceof ShapedRecipes ? 1 : (par2IRecipe instanceof ShapelessRecipes && par1IRecipe instanceof ShapedRecipes ? -1 : (par2IRecipe.getRecipeSize() < par1IRecipe.getRecipeSize() ? -1 : (par2IRecipe.getRecipeSize() > par1IRecipe.getRecipeSize() ? 1 : 0)));
    }

    public int compare(IRecipe par1IRecipe, IRecipe par2IRecipe)
    {
        return this.compareRecipes((IRecipe)par1IRecipe, (IRecipe)par2IRecipe);
    }
}
