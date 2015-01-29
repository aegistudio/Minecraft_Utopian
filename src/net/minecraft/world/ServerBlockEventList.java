package net.minecraft.world;

import java.util.ArrayList;

import net.minecraft.block.BlockEventData;

@SuppressWarnings("serial")
class ServerBlockEventList extends ArrayList<BlockEventData>
{
    private ServerBlockEventList() {}

    ServerBlockEventList(ServerBlockEvent par1ServerBlockEvent)
    {
        this();
    }
}
