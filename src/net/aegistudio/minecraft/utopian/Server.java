package net.aegistudio.minecraft.utopian;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.aegistudio.minecraft.utopian.plugin.event.runtime.PostInitEvent;
import net.aegistudio.minecraft.utopian.plugin.event.runtime.PreInitEvent;
import net.aegistudio.minecraft.utopian.registry.EventHandlerRegistry;
import net.minecraft.logging.ILogAgent;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.stats.StatList;

public class Server
{
	public static void main(String[] arguments) throws Exception
	{
		ILogAgent logger = null;
		try
		{
			UtopianLoader.loadUtopian();
			
	    	//XXX Begin Minecraft UtopianHook
	    	//XXX Hook PreInitEvent
	    	{
		    	PreInitEvent preinit_event = new PreInitEvent(arguments);
		    	EventHandlerRegistry.getEventHandlerRegistry().invoke(preinit_event);
		    	arguments = preinit_event.getArguments();
		    	if(preinit_event.isCancelled()) return;
	    	}
	    	//XXX End Of Minecraft UtopianHook
	    	
	    	StatList.nopInit();
	    	
	    	File minecraftPath = new File(Configuration.getConfig().getStringKey(Configuration.MINECRAFT_PATH));
	    	DedicatedServer dedicatedServer = new DedicatedServer(minecraftPath);
	    	
	    	//XXX Begin Minecraft UtopianHook
	    	//XXX Hook PostInitEvent
	    	{
		    	PostInitEvent postinit_event = new PostInitEvent();
		    	EventHandlerRegistry.getEventHandlerRegistry().invoke(postinit_event);
		    	if(postinit_event.isCancelled()) return;
	    	}
	    	//XXX End Of Minecraft UtopianHook
	    	
	    	logger = dedicatedServer.getLogAgent();
	    	dedicatedServer.setServerPort(80);
	    	dedicatedServer.startServerThread();
		}
    	catch(Exception exception)
		{
    		String failureInfo = "Fail to start minecraft utopian server!";
    		if(logger != null) logger.logSevereException(failureInfo, exception);
    		else Logger.getAnonymousLogger().log(Level.SEVERE, failureInfo, exception);
		}
	}
}
