package net.aegistudio.minecraft.utopian.event.action;

import net.minecraft.entity.player.EntityPlayer;

public class BlockStopDamageAction extends BlockAction
{
	public BlockStopDamageAction(EntityPlayer player, int x, int y, int z, int side, boolean serverAction)
	{
		super(player, player.worldObj, x, y, z, side, player.getHeldItem(), serverAction);
	}
}
