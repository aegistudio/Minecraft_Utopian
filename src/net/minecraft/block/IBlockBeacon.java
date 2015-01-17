package net.minecraft.block;

import net.minecraft.util.Icon;

public interface IBlockBeacon
{
	public Icon getBeaconIcon();
	
	public Block getShellBlock();
	
	public Block getBaseBlock();
}
