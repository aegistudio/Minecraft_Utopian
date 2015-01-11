package net.aegistudio.minecraft.utopian.event.action;

import net.aegistudio.minecraft.utopian.event.Event;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandAction extends Event
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
	
	public EntityPlayerMP getCommandSenderAsPlayer()
	{
		return (EntityPlayerMP)((this.command_sender instanceof EntityPlayerMP)? this.command_sender : null);
	}
	
	public ICommandSender getCommandSender()
	{
		return this.command_sender;
	}
}
