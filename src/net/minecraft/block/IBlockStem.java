package net.minecraft.block;

import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public interface IBlockStem
{
	public int getState(IBlockAccess blockAccess, int par2, int par3, int par4);
	
	public Icon func_94368_p();
}
