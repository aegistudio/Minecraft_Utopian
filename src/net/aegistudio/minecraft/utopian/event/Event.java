package net.aegistudio.minecraft.utopian.event;

public abstract class Event
{
	private boolean isCancelled = false;
	
	public void setCancelled(boolean isCancelled)
	{
		this.isCancelled = isCancelled;
	}
	
	public boolean isCancelled()
	{
		return this.isCancelled;
	}
}
