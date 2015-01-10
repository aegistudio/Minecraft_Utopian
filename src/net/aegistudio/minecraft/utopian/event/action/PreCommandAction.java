package net.aegistudio.minecraft.utopian.event.action;

import net.aegistudio.minecraft.utopian.event.Event;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class PreCommandAction extends Event
{
	private String raw_command;
	private EntityPlayerMP player;
	
	public PreCommandAction(String command, ICommandSender player)
	{
		this.raw_command = command;
		this.player = (EntityPlayerMP) ((player instanceof EntityPlayerMP)? player : null);
	}
	
	public String getRawCommand()
	{
		return this.raw_command;
	}
	
	public void setRawCommand(String command)
	{
		this.raw_command = command;
	}
	
	public EntityPlayerMP getCommandSender()
	{
		return player;
	}
}
