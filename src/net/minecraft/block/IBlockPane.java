package net.minecraft.block;

import net.minecraft.util.Icon;

public interface IBlockPane
{
	public Icon getSideTextureIndex();
	
	public boolean canThisPaneConnectToThisBlockID(int id);
}
