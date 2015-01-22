package net.minecraft.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import net.aegistudio.minecraft.utopian.event.EventHandlerRegistry;
import net.aegistudio.minecraft.utopian.event.runtime.ShutdownEvent;

public final class GameWindowListener extends WindowAdapter
{
    public void windowClosing(WindowEvent par1WindowEvent)
    {
    	//XXX Begin Minecraft UtopianHook
    	//XXX Hook ShutdownEvent
    	{
	    	ShutdownEvent shutdown_event = new ShutdownEvent();
	    	EventHandlerRegistry.getInstance().invoke(shutdown_event);
	    	if(shutdown_event.isCancelled()) return;
    	}
    	//XXX End Of Minecraft UtopianHook
        System.err.println("Someone is closing me!");
        System.exit(1);
    }
}
