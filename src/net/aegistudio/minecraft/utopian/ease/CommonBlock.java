package net.aegistudio.minecraft.utopian.ease;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class CommonBlock extends Block
{
	protected ItemBlock itemCommonBlock;
	protected String displayName;
	protected String[] texturePaths;
	
	protected Icon[] blockIcons;
	
	public CommonBlock(int id, String unlocalizedName, String... texturePaths)
	{
		this(id, unlocalizedName, Material.rock, texturePaths);
	}
	
	public CommonBlock(int id, String unlocalizedName, Material material, String... texturePaths)
	{
		this(id, unlocalizedName, material, true, texturePaths);
	}
	
	protected CommonBlock(int id, String unlocalizedName, Material material, boolean registerItemBlock, String... texturePaths)
	{
		super(id, material);
		if(unlocalizedName == null) throw new IllegalArgumentException("You should input a unlocalized name for the block in order to register it in the game");
		this.setUnlocalizedName(unlocalizedName);
		this.displayName = unlocalizedName;
		this.texturePaths = texturePaths;

		if(registerItemBlock) this.itemCommonBlock = new ItemBlock(id - Item.ITEMBLOCK_BIAS)
		{
			@Override
			public String getItemDisplayName(ItemStack itemstack)
			{
				return displayName;
			}
		};
	}
	
	public CommonBlock setDisplayName(String newDisplayName)
	{
		this.displayName = newDisplayName;
		return this;
	}
	
	public String getLocalizedName()
	{
		return this.displayName;
	}
	
	public Icon getIcon(int side, int metadata)
	{
		if(this.blockIcons == null) return super.blockIcon;
		else return this.blockIcons[0];
	}
	
    public int idDropped(int id, Random random, int meta)
    {
        return this.itemCommonBlock.itemID;
    }
	
	public void registerIcons(IconRegister register)
	{
		if(this.texturePaths == null || this.texturePaths.length == 0)
			this.blockIcon = register.registerIcon(this.getUnlocalizedName());
		else 
		{
			this.blockIcons = new Icon[this.texturePaths.length];
			for(int i = 0; i < this.texturePaths.length; i ++)
				this.blockIcons[i] = register.registerIcon(texturePaths[i]);
		}
	}
}
