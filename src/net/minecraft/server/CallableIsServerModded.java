package net.minecraft.server;

import java.util.concurrent.Callable;

public class CallableIsServerModded implements Callable<String>
{
    /** Reference to the MinecraftServer object. */
    final MinecraftServer mcServer;

    public CallableIsServerModded(MinecraftServer par1)
    {
        this.mcServer = par1;
    }

    public String func_96558_a()
    {
        return this.mcServer.theProfiler.profilingEnabled ? this.mcServer.theProfiler.getNameOfLastSection() : "N/A (disabled)";
    }

    public String call()
    {
        return this.func_96558_a();
    }
}
