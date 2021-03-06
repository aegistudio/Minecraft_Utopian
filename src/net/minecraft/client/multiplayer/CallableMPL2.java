package net.minecraft.client.multiplayer;

import java.util.concurrent.Callable;

class CallableMPL2 implements Callable<String>
{
    /** Reference to the WorldClient object. */
    final WorldClient theWorldClient;

    CallableMPL2(WorldClient par1WorldClient)
    {
        this.theWorldClient = par1WorldClient;
    }

    /**
     * Returns the size and contents of the entity spawn queue.
     */
    public String getEntitySpawnQueueCountAndList()
    {
        return WorldClient.getEntitySpawnQueue(this.theWorldClient).size() + " total; " + WorldClient.getEntitySpawnQueue(this.theWorldClient).toString();
    }

    public String call()
    {
        return this.getEntitySpawnQueueCountAndList();
    }
}
