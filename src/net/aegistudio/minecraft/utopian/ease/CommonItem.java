package net.aegistudio.minecraft.utopian.ease;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class CommonItem extends Item
{
	protected String displayName;
	protected String[] texturePaths;
	
	protected Icon[] itemIcons;
	
	protected CommonItem(int id, String unlocalizedName, String... texturePaths)
	{
		super(id);
		if(unlocalizedName == null) throw new IllegalArgumentException("You should input a unlocalized name for the item in order to register it in the game");
		this.setUnlocalizedName(unlocalizedName);
		this.displayName = unlocalizedName;
		this.texturePaths = texturePaths;
	}
	
	public CommonItem setDisplayName(String newDisplayName)
	{
		this.displayName = newDisplayName;
		return this;
	}
	
	@Override
	public String getItemDisplayName(ItemStack itemstack)
	{
		return displayName;
	}
	
	public Icon getIconFromDamage(int damage)
	{
		if(this.itemIcons == null) return itemIcon;
		else return this.itemIcons[0];
	}
	
	@Override
	public void registerIcons(IconRegister register)
	{
		if(this.texturePaths == null || this.texturePaths.length == 0)
			this.itemIcon = register.registerIcon(this.getUnlocalizedName());
		else
		{
			this.itemIcons = new Icon[this.texturePaths.length];
			for(int i = 0; i < this.texturePaths.length; i ++)
				this.itemIcons[i] = register.registerIcon(texturePaths[i]);
		}
	}
}
