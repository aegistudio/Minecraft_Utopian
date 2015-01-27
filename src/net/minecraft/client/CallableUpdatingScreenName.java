package net.minecraft.client;

import java.util.concurrent.Callable;

public class CallableUpdatingScreenName implements Callable<String>
{
    final Minecraft theMinecraft;

    public CallableUpdatingScreenName(Minecraft par1Minecraft)
    {
        this.theMinecraft = par1Minecraft;
    }

    public String callUpdatingScreenName()
    {
        return this.theMinecraft.currentScreen.getClass().getCanonicalName();
    }

    public String call()
    {
        return this.callUpdatingScreenName();
    }
}
