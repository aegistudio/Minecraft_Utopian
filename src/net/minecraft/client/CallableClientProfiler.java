package net.minecraft.client;

import java.util.concurrent.Callable;

public class CallableClientProfiler implements Callable<String>
{
    final Minecraft theMinecraft;

    public CallableClientProfiler(Minecraft par1Minecraft)
    {
        this.theMinecraft = par1Minecraft;
    }

    public String callClientProfilerInfo()
    {
        return this.theMinecraft.mcProfiler.profilingEnabled ? this.theMinecraft.mcProfiler.getNameOfLastSection() : "N/A (disabled)";
    }

    public String call()
    {
        return this.callClientProfilerInfo();
    }
}
