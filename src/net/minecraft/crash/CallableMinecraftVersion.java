package net.minecraft.crash;

import java.util.concurrent.Callable;

class CallableMinecraftVersion implements Callable<String>
{
    /** Reference to the CrashReport object. */
    final CrashReport theCrashReport;

    CallableMinecraftVersion(CrashReport par1CrashReport)
    {
        this.theCrashReport = par1CrashReport;
    }

    /**
     * The current version of Minecraft
     */
    public String minecraftVersion()
    {
        return "X.X.X";
    }

    public String call()
    {
        return this.minecraftVersion();
    }
}
