package net.minecraft.block;

import net.minecraft.world.IBlockAccess;

public interface IBlockWall
{
	public boolean canConnectWallTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4);
}
