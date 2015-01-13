package net.aegistudio.minecraft.utopian;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import net.aegistudio.minecraft.utopian.event.EventHandler;
import net.aegistudio.minecraft.utopian.event.EventHandlerRegistry;
import net.aegistudio.minecraft.utopian.event.runtime.InitResourceEvent;
import net.aegistudio.minecraft.utopian.event.runtime.InitWindowEvent;
import net.aegistudio.minecraft.utopian.event.runtime.PostInitEvent;
import net.aegistudio.minecraft.utopian.util.SingletonIntruder;
import net.minecraft.client.Minecraft;

public class UtopianLoader
{
	public static final String CONFIG_FILENAME = "utopian-config.json";
	
	public static void initUtopian() throws Exception
	{
		new SingletonIntruder<EventHandlerRegistry>("instance")
		.intrude(EventHandlerRegistry.class, EventHandlerRegistry.class);
		
		Object jframe_state_renderer = new Runnable()
		{
			@EventHandler(value = InitWindowEvent.class, async = true)
			public void initWindow(InitWindowEvent event)
			{
				setLoadingGUI("Creating minecraft display window...", 70);
				EventHandlerRegistry.getEventHandlerRegistry().unregisterHandler(this, InitWindowEvent.class);
			}
			
			@EventHandler(value = InitResourceEvent.class, async = true)
			public void initResource(InitResourceEvent event)
			{
				setLoadingGUI("Installing plugins...", 75);
				InstallationLoader.getInstallationLoader().installPlugins();
				
				setLoadingGUI("Installing minecraft resources...", 83);
				EventHandlerRegistry.getEventHandlerRegistry().unregisterHandler(this, InitResourceEvent.class);
			}
			
			@EventHandler(value = PostInitEvent.class, async = true)
			public void postInit(PostInitEvent event)
			{
				setLoadingGUI("Welcome to the world of minecraft utopian!", 100);
				new Thread(this).start();
				EventHandlerRegistry.getEventHandlerRegistry().unregisterHandler(this, PostInitEvent.class);
			}
	
			@Override
			public void run()
			{
				try	{	Thread.sleep(100);	}	catch(InterruptedException e)	{	}
				destroyLoadingGUI();
			}
		};
		EventHandlerRegistry.getEventHandlerRegistry().registerHandler(jframe_state_renderer, InitWindowEvent.class);
		EventHandlerRegistry.getEventHandlerRegistry().registerHandler(jframe_state_renderer, InitResourceEvent.class);
		EventHandlerRegistry.getEventHandlerRegistry().registerHandler(jframe_state_renderer, PostInitEvent.class);
	}
	
	public static void loadConfig() throws Exception
	{
		setLoadingGUI("Loading configuration files...", 10);
		new SingletonIntruder<ClientConfiguration>("instance")
			.intrude(ClientConfiguration.class, ClientConfiguration.class)
			.loadConfig(CONFIG_FILENAME);
	}
	
	public static void scanningPluginsAndPatches() throws Exception
	{
		setLoadingGUI("Scanning for plugins and patches...", 20);
		new SingletonIntruder<InstallationLoader>("loader")
			.intrude(InstallationLoader.class, InstallationLoader.class)
			.scanningPluginsAndPatches();
	}
	
	public static void installPatches() throws Exception
	{
		setLoadingGUI("Installing patches...", 35);
		InstallationLoader.getInstallationLoader().installPatches();
	}
	
	private static JFrame startup = null;
	private static JLabel status = null;
	private static JProgressBar progress_bar = null;
	
	public static boolean is_server = false;
	
	public static void createLoadingGUI()
	{
		Font font = Font.getFont(Font.MONOSPACED);
		startup = new JFrame();
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		startup.setSize(400, 140);
		startup.setLocation(screensize.width * 3/4 - 200, screensize.height * 3/4 - 100);
		startup.setLayout(null);
		startup.setResizable(false);
		startup.setAlwaysOnTop(true);
		startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startup.setTitle("Minecraft Utopian Game Loader");
		
		status = new JLabel();
		status.setSize(380, 30);
		status.setLocation(10, 10);
		if(font != null) status.setFont(font);
		startup.add(status);
		
		progress_bar = new JProgressBar();
		progress_bar.setLocation(10, 50);
		progress_bar.setSize(380, 30);
		progress_bar.setMinimum(0);
		progress_bar.setMaximum(100);
		startup.add(progress_bar);
		
		startup.setVisible(true);
	}
	
	public static void setLoadingGUI(String text, int value)
	{
		System.out.println("[$percentage%] $event"
				.replace("$percentage", Integer.toString(value))
				.replace("$event", text));
		
		if(startup == null) return;
		status.setText(text);
		progress_bar.setValue(value);
	}
	
	public static void destroyLoadingGUI()
	{
		if(startup == null) return;
		startup.setVisible(false);
		startup.dispose();
	}
	
	public static void patchMinecraftDirectory() throws Exception
	{
		File minecraft_path = new File((String) ClientConfiguration.getConfig().getKey(ClientConfiguration.MINECRAFT_PATH));
		
		Field minecraft_directory = Minecraft.class.getDeclaredField("minecraftDir");
		minecraft_directory.setAccessible(true);
		minecraft_directory.set(null, minecraft_path);
		minecraft_directory.setAccessible(false);
	}
}