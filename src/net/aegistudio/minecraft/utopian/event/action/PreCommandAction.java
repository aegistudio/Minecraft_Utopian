package net.aegistudio.minecraft.utopian.event.action;

import net.aegistudio.minecraft.utopian.event.Event;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Raised when the command handler is to process a command. But the actual command handler
 * is not determined, you've only got the raw command.
 * @side <b>Server Only</b>
 * @author aegistudio
 */

public class PreCommandAction extends Event implements Action
{
	private String raw_command;
	private ICommandSender command_sender;
	
	public PreCommandAction(String command, ICommandSender commandsender)
	{
		this.raw_command = command;
		this.command_sender = commandsender;
	}
	
	public String getRawCommand()
	{
		return this.raw_command;
	}
	
	public void setRawCommand(String command)
	{
		this.raw_command = command;
	}
	
	public EntityPlayerMP getEventPlayer()
	{
		return (EntityPlayerMP)((this.command_sender instanceof EntityPlayerMP)? this.command_sender : null);
	}
	
	public ICommandSender getCommandSender()
	{
		return command_sender;
	}
	
	public boolean isServerAction()
	{
		return true;
	}
}
