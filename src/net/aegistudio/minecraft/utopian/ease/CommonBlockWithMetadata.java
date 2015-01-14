package net.aegistudio.minecraft.utopian.ease;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class CommonBlockWithMetadata extends CommonBlock
{
	protected int metadataLength;
	
	public CommonBlockWithMetadata(int id, String unlocalizedName, String... texturePaths)
	{
		this(id, unlocalizedName, Material.rock, texturePaths);
	}
	
	public CommonBlockWithMetadata(int id, String unlocalizedName, Material material, String... texturePaths)
	{
		this(id, unlocalizedName, material, true, texturePaths.length, texturePaths);
	}
	
	public CommonBlockWithMetadata(int id, String unlocalizedName, Material material, boolean registerItemBlockWithMetadata, int metadataLength, String... texturePath)
	{
		super(id, unlocalizedName, material, false, texturePath);
		if(registerItemBlockWithMetadata) super.itemCommonBlock = new ItemBlockWithMetadata(this.blockID - Item.ITEMBLOCK_BIAS, this)
		{
			@Override
			public String getItemDisplayName(ItemStack itemstack)
			{
				return displayName;
			}
		};
		this.metadataLength = metadataLength;
	}
	
	public void getSubBlocks(int id, CreativeTabs creativeTabs, List<ItemStack> subblockList)
	{
		for(int i = 0; i < metadataLength; i ++) subblockList.add(new ItemStack(this.itemCommonBlock.itemID, 1, i));
	}
}
