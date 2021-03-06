package net.minecraft.server;

import java.util.concurrent.Callable;

public class CallableServerProfiler implements Callable<String>
{
    /** Reference to the MinecraftServer object. */
    final MinecraftServer mcServer;

    public CallableServerProfiler(MinecraftServer par1)
    {
        this.mcServer = par1;
    }

    public String callServerProfiler()
    {
        int var1 = this.mcServer.worldServers.get(0).getWorldVec3Pool().getPoolSize();
        int var2 = 56 * var1;
        int var3 = var2 / 1024 / 1024;
        int var4 = this.mcServer.worldServers.get(0).getWorldVec3Pool().func_82590_d();
        int var5 = 56 * var4;
        int var6 = var5 / 1024 / 1024;
        return var1 + " (" + var2 + " bytes; " + var3 + " MB) allocated, " + var4 + " (" + var5 + " bytes; " + var6 + " MB) used";
    }

    public String call()
    {
        return this.callServerProfiler();
    }
}
