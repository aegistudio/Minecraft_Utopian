package net.aegistudio.minecraft.utopian.event.action;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockActivateAction extends BlockAction
{

	public BlockActivateAction(EntityPlayer player, World world, int x, int y,
			int z, int side, ItemStack itemstack, boolean serverAction)
	{
		super(player, world, x, y, z, side, itemstack, serverAction);
		// TODO Auto-generated constructor stub
	}

}
