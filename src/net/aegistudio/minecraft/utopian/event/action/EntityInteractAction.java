package net.aegistudio.minecraft.utopian.event.action;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EntityInteractAction extends EntityAction
{

	public EntityInteractAction(EntityPlayer player, Entity targetEntity,
			boolean serverAction)
	{
		super(player, targetEntity, serverAction);
	}


}
