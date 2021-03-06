package net.minecraft.client;

import java.util.concurrent.Callable;

public class CallableModded implements Callable<String>
{
    /** Reference to the Minecraft object. */
    final Minecraft mc;

    public CallableModded(Minecraft par1Minecraft)
    {
        this.mc = par1Minecraft;
    }

    /**
     * Gets if Client Profiler (aka Snooper) is enabled.
     */
    public String getClientProfilerEnabled()
    {
        String var1 = ClientBrandRetriever.getClientModName();
        return !var1.equals("vanilla") ? "Definitely; Client brand changed to \'" + var1 + "\'" : (Minecraft.class.getSigners() == null ? "Very likely; Jar signature invalidated" : "Probably not. Jar signature remains and client brand is untouched.");
    }

    public String call()
    {
        return this.getClientProfilerEnabled();
    }
}
