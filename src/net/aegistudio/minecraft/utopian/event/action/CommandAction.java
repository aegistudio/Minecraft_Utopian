package net.aegistudio.minecraft.utopian.event.action;

import net.aegistudio.minecraft.utopian.event.Event;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Raised when the command handler is to process a command. The actual command has been determined at this phase, and the arguments have been splitted.
 * @side <b>Server Only</b>
 * @author aegistudio
 */

public class CommandAction extends Event implements Action
{
	private String command_prefix;
	private String[] command_arguments;
	private ICommand command;
	
	private ICommandSender command_sender;
	
	public CommandAction(ICommand command, String command_prefix, String[] command_arguments, ICommandSender command_sender)
	{
		this.command = command;
		this.command_prefix = (this.command != null)? this.command.getCommandName() : command_prefix;
		this.command_arguments = command_arguments;
		
		this.command_sender = command_sender;
	}
	
	public String getCommandPrefix()
	{
		return this.command_prefix;
	}
	
	public void setCommand(ICommand command)
	{
		this.command = command;
		if(command != null) this.command_prefix = this.command.getCommandName();
	}
	
	public ICommand getCommand()
	{
		return this.command;
	}
	
	public String[] getCommandArguments()
	{
		return this.command_arguments;
	}
	
	public void setCommandArguments(String[] command_arguments)
	{
		this.command_arguments = command_arguments;
	}
	
	public EntityPlayerMP getEventPlayer()
	{
		return (EntityPlayerMP)((this.command_sender instanceof EntityPlayerMP)? this.command_sender : null);
	}
	
	public ICommandSender getCommandSender()
	{
		return this.command_sender;
	}
	
	public boolean isServerAction()
	{
		return true;
	}
}
