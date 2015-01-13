package net.aegistudio.minecraft.utopian.event.action;

import net.aegistudio.minecraft.utopian.event.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockAction extends Event implements Action
{
	private EntityPlayer player;
	private World world;
	private int x, y, z, side;
	private ItemStack itemstack;
	
	public BlockAction(EntityPlayer player, World world, int x, int y, int z, int side, ItemStack itemstack)
	{
		super();
		this.player = player;
		this.world = world;
		this.x = x; this.y = y; this.z = z; this.side = side;
		this.itemstack = itemstack;
	}
	
	public EntityPlayer getEventPlayer()
	{
		return this.player;
	}
	
	public World getWorld()
	{
		return this.world;
	}
	
	public int getBlockX()
	{
		return this.x;
	}
	
	public int getBlockY()
	{
		return this.y;
	}
	
	public int getBlockZ()
	{
		return this.z;
	}
	
	public int getBlockSide()
	{
		return this.side;
	}
	
	public ItemStack getUsingItem()
	{
		return this.itemstack;
	}
}
