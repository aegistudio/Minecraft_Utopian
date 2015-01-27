package net.minecraft.client;

import java.util.concurrent.Callable;
import org.lwjgl.Sys;

public class CallableLWJGLVersion implements Callable<String>
{
    /** Reference to the Minecraft object. */
    final Minecraft mc;

    public CallableLWJGLVersion(Minecraft par1Minecraft)
    {
        this.mc = par1Minecraft;
    }

    public String getType()
    {
        return Sys.getVersion();
    }

    public String call()
    {
        return this.getType();
    }
}
