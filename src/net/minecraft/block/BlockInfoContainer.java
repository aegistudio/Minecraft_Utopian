package net.minecraft.block;

public abstract class BlockInfoContainer
{
	public abstract Block getBlock(int blockid);
	
	public abstract void setBlock(Block block);
	
	public abstract Block[] getRegisteredBlocks();
	
	public abstract int getMaximumBlockId();
	
	public abstract boolean getLookupOpaqueCube(int blockid);
	
	public abstract void setLookupOpaqueCube(int blockid, boolean isOpaqueCube);
	
	public abstract int getLookupOpacity(int blockid);
	
	public abstract void setLookupOpacity(int blockid, int blockOpacity);
	
	public abstract boolean getCanBlockGrass(int blockid);
	
	public abstract void setCanBlockGrass(int blockid, boolean canBlockGrass);
	
	public abstract int getLookupLightValue(int blockid);
	
	public abstract void setLookupLightValue(int blockid, int lightValue);
	
	public abstract boolean getUseNeighbourBrightness(int blockid);
	
	public abstract void setUseNeighbourBrightness(int blockid, boolean useNeighbourBrightness);
	
	public static BlockInfoContainer getBlockInfoContainer()
	{
		return instance;
	}
	
	private static BlockInfoContainer instance;
}