package net.minecraft.command;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;

public class CommandServerSaveAll extends CommandBase
{
    public String getCommandName()
    {
        return "save-all";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 4;
    }

    @SuppressWarnings("unused")
	public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        MinecraftServer var3 = MinecraftServer.getServer();
        par1ICommandSender.sendChatToPlayer(par1ICommandSender.translateString("commands.save.start", new Object[0]));

        if (var3.getConfigurationManager() != null)
        {
            var3.getConfigurationManager().saveAllPlayerData();
        }

        try
        {
            int var4;
            WorldServer var5;
            boolean var6;

            WorldServer[] worlds = var3.worldServers.values().toArray(new WorldServer[0]);
            
            for (WorldServer world : worlds)
            {
                if (world != null)
                {
                    var5 = world;
                    var6 = var5.canNotSave;
                    var5.canNotSave = false;
                    var5.saveAllChunks(true, (IProgressUpdate)null);
                    var5.canNotSave = var6;
                }
            }

            if (par2ArrayOfStr.length > 0 && "flush".equals(par2ArrayOfStr[0]))
            {
                par1ICommandSender.sendChatToPlayer(par1ICommandSender.translateString("commands.save.flushStart", new Object[0]));

                for (WorldServer world : worlds)
                {
                    if (world != null)
                    {
                        var5 = world;
                        var6 = var5.canNotSave;
                        var5.canNotSave = false;
                        var5.func_104140_m();
                        var5.canNotSave = var6;
                    }
                }

                par1ICommandSender.sendChatToPlayer(par1ICommandSender.translateString("commands.save.flushEnd", new Object[0]));
            }
        }
        catch (MinecraftException var7)
        {
            notifyAdmins(par1ICommandSender, "commands.save.failed", new Object[] {var7.getMessage()});
            return;
        }

        notifyAdmins(par1ICommandSender, "commands.save.success", new Object[0]);
    }
}
