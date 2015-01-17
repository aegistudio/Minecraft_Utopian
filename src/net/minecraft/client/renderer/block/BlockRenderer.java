package net.minecraft.client.renderer.block;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BiMap;

public abstract class BlockRenderer
{
	public static final Map<Integer, BlockRenderer> blockRenderers = new BiMap<BlockRenderer>();
	
	public static final BlockRenderer empty = new BlockRenderer(-1)
	{
		public boolean onRenderBlock(RenderBlocks renderBlocks, Block block, int x, int y, int z)
		{
			return false;
		}
	};
	
	protected BlockRenderer(int rendererId)
	{
		this(rendererId, true);
	}
	
	protected BlockRenderer(int rendererId, boolean checkExistence)
	{
		if(checkExistence) if(blockRenderers.get(rendererId) != null) throw new IllegalArgumentException("Already registered renderer #" + rendererId);
		Minecraft.getMinecraft().getLogAgent().logInfo("Registered block renderer @" + this.getClass().getName());
		blockRenderers.put(rendererId, this);
	}
	
	public static BlockRenderer getBlockRenderer(int rendererId)
	{
		BlockRenderer returnValue;
		if((returnValue = blockRenderers.get(rendererId)) == null) return empty;
		return returnValue;
	}
	
	public abstract boolean onRenderBlock(RenderBlocks renderBlocks, Block block, int x, int y, int z);
}
