package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandTime extends CommandBase
{
    public String getCommandName()
    {
        return "time";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return par1ICommandSender.translateString("commands.time.usage", new Object[0]);
    }

    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        if (par2ArrayOfStr.length > 1)
        {
            int var3;

            if (par2ArrayOfStr[0].equals("set"))
            {
                if (par2ArrayOfStr[1].equals("day"))
                {
                    var3 = 0;
                }
                else if (par2ArrayOfStr[1].equals("night"))
                {
                    var3 = 12500;
                }
                else
                {
                    var3 = parseIntWithMin(par1ICommandSender, par2ArrayOfStr[1], 0);
                }

                this.setTime(par1ICommandSender, var3);
                notifyAdmins(par1ICommandSender, "commands.time.set", new Object[] {Integer.valueOf(var3)});
                return;
            }

            if (par2ArrayOfStr[0].equals("add"))
            {
                var3 = parseIntWithMin(par1ICommandSender, par2ArrayOfStr[1], 0);
                this.addTime(par1ICommandSender, var3);
                notifyAdmins(par1ICommandSender, "commands.time.added", new Object[] {Integer.valueOf(var3)});
                return;
            }
        }

        throw new WrongUsageException("commands.time.usage", new Object[0]);
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[] {"set", "add"}): (par2ArrayOfStr.length == 2 && par2ArrayOfStr[0].equals("set") ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[] {"day", "night"}): null);
    }

    /**
     * Set the time in the server object.
     */
    protected void setTime(ICommandSender par1ICommandSender, int par2)
    {
        MinecraftServer var3 = MinecraftServer.getServer();
        WorldServer[] worlds = var3.worldServers.values().toArray(new WorldServer[0]);
        
        for (WorldServer world : worlds) if(world != null)
        {
            world.setWorldTime((long)par2);
        }
    }

    /**
     * Adds (or removes) time in the server object.
     */
    protected void addTime(ICommandSender par1ICommandSender, int par2)
    {
    	MinecraftServer var3 = MinecraftServer.getServer();
        WorldServer[] worlds = var3.worldServers.values().toArray(new WorldServer[0]);
        
        for (WorldServer world : worlds) if(world != null)
        {
            world.setWorldTime(world.getWorldTime() + (long)par2);
        }
    }
}
