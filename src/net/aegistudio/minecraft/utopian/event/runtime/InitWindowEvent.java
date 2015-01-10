package net.aegistudio.minecraft.utopian.event.runtime;

import java.awt.Frame;

import net.aegistudio.minecraft.utopian.event.Event;

public class InitWindowEvent extends Event
{
	
	private Frame frame;
	
	public InitWindowEvent(Frame windowFrame)
	{
		this.frame = windowFrame;
	}
	
	public Frame getFrame()
	{
		return this.frame;
	}
	
	public void setFrame(Frame windowFrame)
	{
		this.frame = windowFrame;
	}
	
}
