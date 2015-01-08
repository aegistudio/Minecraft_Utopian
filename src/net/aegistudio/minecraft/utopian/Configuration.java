package net.aegistudio.minecraft.utopian;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import net.aegistudio.json.JSONBuilder;
import net.aegistudio.json.JSONObject;

public class Configuration
{
	public static final String PATCHES_PATH = "patches_path";
	public static final String PLUGINS_PATH = "plugins_path";
	
	public static final String MINECRAFT_PATH = "minecraft_path";
	
	public static final String PLAYER_NAME = "player_name";
	
	final JSONBuilder FORMAT_BUILDER = JSONBuilder.deriveBuilder();
	
	final JSONObject default_config = new JSONObject();
		
	private static Configuration instance;
	
	protected Configuration()
	{
		FORMAT_BUILDER.set(JSONBuilder.BREAK_AFTER_HEAD_BRACKET, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.BREAK_AFTER_COMMA, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.SPACE_AFTER_COMMA_ARRAY, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.SPACE_BEFORE_VALUE, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.BREAK_BEFORE_FOOT_BRACKET, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.INDENT, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.TAB_AS_INDENT, JSONBuilder.TRUE);
		
		default_config.set(MINECRAFT_PATH, ".");
		default_config.set(PLAYER_NAME, "player");
		default_config.set(PATCHES_PATH, "patch");
		default_config.set(PLUGINS_PATH, "plugin");
	}
	
	public static Configuration getConfig()
	{
		return instance;
	}

	private JSONObject config;
	
	void loadConfig(String config) throws Exception
	{
		File config_file = new File(config);
		if(!config_file.exists())
		{
			PrintStream default_config_printer = new PrintStream(new FileOutputStream(config_file));
			default_config_printer.print(default_config.toString(FORMAT_BUILDER));
			default_config_printer.close();
		}
		this.config = new JSONFile(config_file).getObject();
	}
	
	public String getStringKey(String keyname)
	{
		String return_value = (String)config.get(keyname);
		if(return_value == null) return_value = (String) this.default_config.get(return_value);
		return return_value;
	}
}
