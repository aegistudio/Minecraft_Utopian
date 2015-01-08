package net.aegistudio.minecraft.utopian.plugin.event.runtime;

import net.aegistudio.minecraft.utopian.plugin.event.Event;

/**
 * This event is created on entrying the main method in Minecraft class, the
 * main arguments could be mutated by changing the argument field of this class.
 * @author aegistudio
 */

public class PreInitEvent extends Event
{
	private String[] arguments;
	
	public PreInitEvent(String[] arguments)
	{
		this.arguments = arguments;
	}
	
	/**
	 * Getting the arguments of the Minecraft class main method.
	 * @return arguments of the Minecraft main class.
	 */
	
	public String[] getArguments()
	{
		return this.arguments;
	}
	
	/**
	 * Setting the main method arguments passed onto the Minecraft class.
	 * @param newArguments the argument to change in the minecraft class.
	 */
	
	public void setArguments(String[] newArguments)
	{
		this.arguments = newArguments;
	}
}
