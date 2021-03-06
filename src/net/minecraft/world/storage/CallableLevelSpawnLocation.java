package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;

class CallableLevelSpawnLocation implements Callable<String>
{
    final WorldInfo worldInfoInstance;

    CallableLevelSpawnLocation(WorldInfo par1WorldInfo)
    {
        this.worldInfoInstance = par1WorldInfo;
    }

    public String callLevelSpawnLocation()
    {
        return CrashReportCategory.getLocationInfo(WorldInfo.getSpawnXCoordinate(this.worldInfoInstance), WorldInfo.getSpawnYCoordinate(this.worldInfoInstance), WorldInfo.getSpawnZCoordinate(this.worldInfoInstance));
    }

    public String call()
    {
        return this.callLevelSpawnLocation();
    }
}
