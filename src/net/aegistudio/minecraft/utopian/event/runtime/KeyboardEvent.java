package net.aegistudio.minecraft.utopian.event.runtime;

import net.aegistudio.minecraft.utopian.Dedicated;
import net.aegistudio.minecraft.utopian.event.Event;

@Dedicated.Client
public class KeyboardEvent extends Event
{
	private int key;
	private boolean keystate;
	public KeyboardEvent(int key, boolean keystate)
	{
		this.key = key;
		this.keystate = keystate;
	}
	
	public int getEventKey()
	{
		return this.key;
	}
	
	public boolean getEventKeyState()
	{
		return this.keystate;
	}
}
