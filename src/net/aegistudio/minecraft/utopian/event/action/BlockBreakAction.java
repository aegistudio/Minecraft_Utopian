package net.aegistudio.minecraft.utopian.event.action;

import net.minecraft.entity.player.EntityPlayer;

public class BlockBreakAction extends BlockAction
{
	public BlockBreakAction(EntityPlayer player, int x, int y, int z, int side, boolean serverAction)
	{
		super(player, player.worldObj, x, y, z, side, player.getHeldItem(), serverAction);
	}	
}
