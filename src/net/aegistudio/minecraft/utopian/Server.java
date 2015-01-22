package net.aegistudio.minecraft.utopian;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.aegistudio.minecraft.utopian.event.EventHandlerRegistry;
import net.aegistudio.minecraft.utopian.event.runtime.InitResourceEvent;
import net.aegistudio.minecraft.utopian.event.runtime.PostInitEvent;
import net.aegistudio.minecraft.utopian.event.runtime.PreInitEvent;
import net.aegistudio.minecraft.utopian.event.runtime.ShutdownEvent;
import net.minecraft.logging.ILogAgent;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.stats.StatList;

public class Server extends DedicatedServer
{
	public Server(File par1File)
	{
		super(par1File);
	}

	public static void main(String[] arguments)
	{
		ILogAgent logger = null;
		try
		{
			UtopianLoader.is_server = true;
			UtopianLoader.initUtopian();
			UtopianLoader.loadConfig();
			UtopianLoader.scanningPluginsAndPatches();
			UtopianLoader.installPatches();
			
	    	//XXX Begin Minecraft UtopianHook
	    	//XXX Hook PreInitEvent
	    	{
		    	PreInitEvent preinit_event = new PreInitEvent(arguments);
		    	EventHandlerRegistry.getInstance().invoke(preinit_event);
		    	arguments = preinit_event.getArguments();
		    	if(preinit_event.isCancelled()) return;
	    	}
	    	//XXX End Of Minecraft UtopianHook
	    	
	    	StatList.nopInit();
	    	
	    	//XXX Begin Minecraft UtopianHook
	    	//XXX Hook InitResourceEvent
	    	{
		    	InitResourceEvent initresource_event = new InitResourceEvent();
		    	EventHandlerRegistry.getInstance().invoke(initresource_event);
		    	if(initresource_event.isCancelled()) return;
	    	}
	    	//XXX End Of Minecraft UtopianHook
	    	
	    	//XXX the configuration may be changed to server config?
	    	File minecraftPath = new File((String) ClientConfiguration.getConfig().getKey(ClientConfiguration.MINECRAFT_PATH));
	    	DedicatedServer server = new Server(minecraftPath);
	    	
	    	//XXX Begin Minecraft UtopianHook
	    	//XXX Hook PostInitEvent
	    	{
		    	PostInitEvent postinit_event = new PostInitEvent();
		    	EventHandlerRegistry.getInstance().invoke(postinit_event);
		    	if(postinit_event.isCancelled()) return;
	    	}
	    	//XXX End Of Minecraft UtopianHook
	    	
	    	logger = server.getLogAgent();
	    	server.startServerThread();
		}
    	catch(Exception exception)
		{
    		String failureInfo = "Fail to start minecraft utopian server!";
    		if(logger != null) logger.logSevereException(failureInfo, exception);
    		else Logger.getAnonymousLogger().log(Level.SEVERE, failureInfo, exception);
		}
	}
	
    protected void systemExitNow()
    {
    	//XXX Begin Minecraft UtopianHook
    	//XXX Hook ShutdownEvent
    	ShutdownEvent shutdown_event = new ShutdownEvent();
    	EventHandlerRegistry.getInstance().invoke(shutdown_event);
    	if(shutdown_event.isCancelled());
    	//XXX Can't stop!
    	//XXX End Of Minecraft UtopianHook
        super.systemExitNow();
    }
}
