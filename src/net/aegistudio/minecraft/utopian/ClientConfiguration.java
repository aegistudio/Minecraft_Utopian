package net.aegistudio.minecraft.utopian;

import net.aegistudio.minecraft.utopian.util.Configuration;

public class ClientConfiguration extends Configuration
{
	public static final String PATCHES_PATH = "patches_path";
	public static final String PLUGINS_PATH = "plugins_path";
	
	public static final String MINECRAFT_PATH = "minecraft_path";
	
	public static final String PLAYER_NAME = "player_name";
	
	protected ClientConfiguration()
	{
		super();
		
		default_config.set(MINECRAFT_PATH, ".");
		default_config.set(PLAYER_NAME, "player");
		default_config.set(PATCHES_PATH, "patch");
		default_config.set(PLUGINS_PATH, "plugin");
	}
	
	private static Configuration instance;
	
	public static Configuration getConfig()
	{
		return instance;
	}
}
