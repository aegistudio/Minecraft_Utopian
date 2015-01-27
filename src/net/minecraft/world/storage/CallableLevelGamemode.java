package net.minecraft.world.storage;

import java.util.concurrent.Callable;

class CallableLevelGamemode implements Callable<String>
{
    final WorldInfo worldInfoInstance;

    CallableLevelGamemode(WorldInfo par1WorldInfo)
    {
        this.worldInfoInstance = par1WorldInfo;
    }

    public String callLevelGameModeInfo()
    {
        return String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", new Object[] {WorldInfo.getGameType(this.worldInfoInstance).getName(), Integer.valueOf(WorldInfo.getGameType(this.worldInfoInstance).getID()), Boolean.valueOf(WorldInfo.func_85117_p(this.worldInfoInstance)), Boolean.valueOf(WorldInfo.func_85131_q(this.worldInfoInstance))});
    }

    public String call()
    {
        return this.callLevelGameModeInfo();
    }
}
