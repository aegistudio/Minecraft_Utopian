package net.minecraft.client;

import java.util.concurrent.Callable;

public class CallableType2 implements Callable<String>
{
    final Minecraft mc;

    public CallableType2(Minecraft par1Minecraft)
    {
        this.mc = par1Minecraft;
    }

    public String func_82886_a()
    {
        return "Client (map_client.txt)";
    }

    public String call()
    {
        return this.func_82886_a();
    }
}
