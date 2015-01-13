package net.aegistudio.minecraft.utopian.event.action;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAttackAction extends EntityAction
{

	public EntityAttackAction(EntityPlayer player, Entity targetEntity)
	{
		super(player, targetEntity);
	}
	
}
