package net.aegistudio.minecraft.utopian.patch.core;

import net.aegistudio.json.JSONObject;
import net.aegistudio.minecraft.utopian.patch.Patch;

public class Patch_MinecraftUtopian implements Patch
{

	@Override
	public boolean install() throws Exception
	{
		return true;
	}

	private static final JSONObject patch_minecraftutopian = new JSONObject();
	static
	{
		patch_minecraftutopian.set("name", "Minecraft Utopian");
		patch_minecraftutopian.set("version", null);
		patch_minecraftutopian.set("features", new String[]{
				"Minecraft Utopian modified some source ",
				"file of minecraft and changes its structure",
				"in order to make the minecraft more like a ",
				"utopian."
		});
	}
	
	@Override
	public JSONObject getDescription()
	{
		return patch_minecraftutopian;
	}
	
}
