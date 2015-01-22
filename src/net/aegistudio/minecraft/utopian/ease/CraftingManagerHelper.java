package net.aegistudio.minecraft.utopian.ease;

import java.lang.reflect.Method;

import net.aegistudio.minecraft.utopian.util.Intrude;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;

public final class CraftingManagerHelper
{
	public static CraftingManagerHelper getInstance()
	{
		return instance;
	}
	
	private static final CraftingManagerHelper instance = new CraftingManagerHelper();
	
	private Method addRecipe;
	private Method addShapelessRecipe;
	
	private CraftingManagerHelper()
	{
		Method[] methods = CraftingManager.class.getDeclaredMethods();
		for(Method method : methods) if(method.isAnnotationPresent(Intrude.class))
		{
			Intrude intrude = method.getAnnotation(Intrude.class);
			if("addShapelessRecipe".equals(intrude.value())) this.addShapelessRecipe = method;
			else if("addRecipe".equals(intrude.value())) this.addRecipe = method;
		}
	}
	
	public ShapedRecipes addRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
	{
		CraftingManager craftingManager = CraftingManager.getInstance();
		try
		{
			addRecipe.setAccessible(true);
			Object returnValue = addRecipe.invoke(craftingManager, par1ItemStack, par2ArrayOfObj);
			addRecipe.setAccessible(false);
			return (ShapedRecipes) returnValue;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}
	
	public void addShapelessRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
	{
		CraftingManager craftingManager = CraftingManager.getInstance();
		try
		{
			addShapelessRecipe.setAccessible(true);
			addShapelessRecipe.invoke(craftingManager, par1ItemStack, par2ArrayOfObj);
			addShapelessRecipe.setAccessible(false);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
