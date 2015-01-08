package net.aegistudio.minecraft.utopian;

import net.aegistudio.json.JSONObject;

public interface Installation
{
	public boolean install() throws Exception;
	
	public JSONObject getDescription();
}
