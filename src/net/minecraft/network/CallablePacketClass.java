package net.minecraft.network;

import java.util.concurrent.Callable;
import net.minecraft.network.packet.Packet;

class CallablePacketClass implements Callable<String>
{
    final Packet thePacket;

    final NetServerHandler theNetServerHandler;

    CallablePacketClass(NetServerHandler par1NetServerHandler, Packet par2Packet)
    {
        this.theNetServerHandler = par1NetServerHandler;
        this.thePacket = par2Packet;
    }

    public String getPacketClass()
    {
        return this.thePacket.getClass().getCanonicalName();
    }

    public String call()
    {
        return this.getPacketClass();
    }
}
