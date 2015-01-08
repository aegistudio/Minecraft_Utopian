package net.aegistudio.minecraft.utopian;

import net.minecraft.client.Minecraft;

public class Client
{
	public static void main(String[] arguments) throws Exception
	{
		UtopianLoader.createLoadingGUI();
		UtopianLoader.loadUtopian();
		
		UtopianLoader.setLoadingGUI("Patching the minecraft directory...", 50);
		UtopianLoader.patchMinecraftDirectory();
		
		UtopianLoader.setLoadingGUI("Launching minecraft main class...", 60);
		Minecraft.main(new String[]{Configuration.getConfig().getStringKey(Configuration.PLAYER_NAME)});
	}
}
