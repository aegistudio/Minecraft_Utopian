package net.aegistudio.minecraft.utopian.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import net.aegistudio.json.JSONBuilder;
import net.aegistudio.json.JSONObject;

public class Configuration
{

	protected final JSONBuilder FORMAT_BUILDER = JSONBuilder.deriveBuilder();
	
	protected final JSONObject default_config = new JSONObject();
	
	protected Configuration()
	{
		FORMAT_BUILDER.set(JSONBuilder.BREAK_AFTER_HEAD_BRACKET, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.BREAK_AFTER_COMMA, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.SPACE_AFTER_COMMA_ARRAY, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.SPACE_BEFORE_VALUE, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.BREAK_BEFORE_FOOT_BRACKET, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.INDENT, JSONBuilder.TRUE);
		FORMAT_BUILDER.set(JSONBuilder.TAB_AS_INDENT, JSONBuilder.TRUE);
	}

	protected JSONObject config;
	
	public void loadConfig(String config, File parent) throws Exception
	{
		File config_file = new File(parent, config);
		this.loadConfigInternal(config_file);
	}
	
	public void loadConfig(String config) throws Exception
	{
		File config_file = new File(config);
		this.loadConfigInternal(config_file);
	}
	
	private void loadConfigInternal(File config_file) throws Exception
	{
		if(!config_file.exists())
		{
			PrintStream default_config_printer = new PrintStream(new FileOutputStream(config_file));
			default_config_printer.print(default_config.toString(FORMAT_BUILDER));
			default_config_printer.close();
		}
		this.config = new JSONFile(config_file).getObject();
	}
	
	public Object getKey(String keyname)
	{
		Object return_value = config.get(keyname);
		if(return_value == null) return_value = this.default_config.get(keyname);
		return return_value;
	}
	
	public void setKey(String keyname, String object)
	{
		config.set(keyname, object);
	}
	
	public void saveConfig(String config) throws Exception
	{
		File config_file = new File(config);
		PrintStream config_printer = new PrintStream(new FileOutputStream(config_file));
		config_printer.print(this.config.toString(FORMAT_BUILDER));
		config_printer.close();
	}
}
