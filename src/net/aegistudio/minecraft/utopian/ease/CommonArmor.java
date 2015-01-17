package net.aegistudio.minecraft.utopian.ease;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class CommonArmor extends ItemArmor
{

	private String displayName;
	
	private String armorPathOne;
	private String armorPathTwo;
	
	protected String[] texturePaths;
	
	protected Icon[] itemIcons;
	
	public CommonArmor(int id, String unlocalizedName, int armorType, String armorPathOne, String armorPathTwo, String... texturePaths)
	{
		super(id, EnumArmorMaterial.IRON, 1, armorType);
		this.setUnlocalizedName(unlocalizedName);
		this.displayName = unlocalizedName;
		this.armorPathOne = armorPathOne;
		this.armorPathTwo = armorPathTwo;
		this.texturePaths = texturePaths;
	}
	
	public CommonArmor setDisplayName(String newDisplayName)
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
	
	public String getArmorTexture(int par2)
	{
		return (par2 == 2)? this.armorPathTwo : this.armorPathOne;
	}
	
	
}
