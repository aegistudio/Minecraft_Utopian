package net.minecraft.command;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandServerSaveOn extends CommandBase
{
    public String getCommandName()
    {
        return "save-on";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 4;
    }

    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        MinecraftServer var3 = MinecraftServer.getServer();

        WorldServer[] worlds = var3.worldServers.values().toArray(new WorldServer[0]);
        for (WorldServer world : worlds)
        {
            if (world != null)
            {
                WorldServer var5 = world;
                var5.canNotSave = false;
            }
        }

        notifyAdmins(par1ICommandSender, "commands.save.enabled", new Object[0]);
    }
}
