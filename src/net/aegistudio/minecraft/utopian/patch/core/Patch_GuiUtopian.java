package net.aegistudio.minecraft.utopian.patch.core;

import java.lang.reflect.Field;

import net.aegistudio.json.JSONObject;
import net.aegistudio.minecraft.utopian.Dedicated;
import net.aegistudio.minecraft.utopian.patch.Patch;

@Dedicated.Client
public class Patch_GuiUtopian implements Patch
{

	@Override
	public boolean install() throws Exception
	{
		Field field_gui_class = net.minecraft.client.gui.main.GuiMainMenu.class.getDeclaredField("UTOPIAN_GUI_CLASS");
		field_gui_class.setAccessible(true);
		field_gui_class.set(null, "net.aegistudio.minecraft.utopian.gui.GuiUtopian");
		field_gui_class.setAccessible(false);
		
		Field field_gui_button_tag = net.minecraft.client.gui.main.GuiMainMenu.class.getDeclaredField("UTOPIAN_BUTTON_TAG");
		field_gui_button_tag.setAccessible(true);
		field_gui_button_tag.set(null, "Patches & Plugins");
		field_gui_button_tag.setAccessible(false);
		return true;
	}

	private static final JSONObject patch_guiutopian = new JSONObject();
	static
	{
		patch_guiutopian.set("name", "MinecraftUtopian Patch & Plugin GUI");
		patch_guiutopian.set("version", "0.0.0.1 alpha");
		patch_guiutopian.set("features", new String[]{
				"The graphic user interface that is used ",
				"to display installed patches and plugins ",
				"in the minecraft utopian."
		});
	}
	
	@Override
	public JSONObject getDescription()
	{
		return patch_guiutopian;
	}
	
}
