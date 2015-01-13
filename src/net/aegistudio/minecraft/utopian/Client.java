package net.aegistudio.minecraft.utopian;

import net.minecraft.client.Minecraft;

public class Client
{
	public static void main(String[] arguments) throws Exception
	{
		UtopianLoader.createLoadingGUI();
		UtopianLoader.initUtopian();
		
		UtopianLoader.loadConfig();
		
		if(arguments.length > 0) for(int i = 0; i < arguments.length; i ++)
		{
			if(arguments[i].equals("--player"))
				ClientConfiguration.getConfig().setKey(ClientConfiguration.PLAYER_NAME, arguments[++ i]);
			else if(arguments[i].equals("--gameDir"))
				ClientConfiguration.getConfig().setKey(ClientConfiguration.MINECRAFT_PATH, arguments[++ i]);
		}
		
		UtopianLoader.scanningPluginsAndPatches();
		UtopianLoader.installPatches();
		
		UtopianLoader.setLoadingGUI("Patching the minecraft directory...", 50);
		UtopianLoader.patchMinecraftDirectory();
		
		UtopianLoader.setLoadingGUI("Launching minecraft main class...", 60);
		Minecraft.main(new String[]{(String) ClientConfiguration.getConfig().getKey(ClientConfiguration.PLAYER_NAME)});
	}
}
