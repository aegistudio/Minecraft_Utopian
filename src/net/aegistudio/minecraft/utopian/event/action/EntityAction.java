package net.aegistudio.minecraft.utopian.event.action;

import net.aegistudio.minecraft.utopian.event.Event;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public abstract class EntityAction extends Event implements Action
{
	
	private EntityPlayer player;
	private Entity targetEntity;
	private boolean serverAction;
	
	public EntityAction(EntityPlayer player, Entity targetEntity, boolean serverAction)
	{
		super();
		this.player = player;
		this.targetEntity = targetEntity;
		this.serverAction = serverAction;
	}
	
	@Override
	public EntityPlayer getEventPlayer()
	{
		return this.player;
	}
	
	public Entity getTargetEntity()
	{
		return this.targetEntity;
	}
	
	public boolean isServerAction()
	{
		return this.serverAction;
	}
}
