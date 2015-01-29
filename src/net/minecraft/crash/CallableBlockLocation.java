package net.minecraft.crash;

import java.util.concurrent.Callable;

final class CallableBlockLocation implements Callable<String>
{
    final int blockXCoord;

    final int blockYCoord;

    final int blockZCoord;

    CallableBlockLocation(int par1, int par2, int par3)
    {
        this.blockXCoord = par1;
        this.blockYCoord = par2;
        this.blockZCoord = par3;
    }

    public String callBlockLocationInfo()
    {
        return CrashReportCategory.getLocationInfo(this.blockXCoord, this.blockYCoord, this.blockZCoord);
    }

    public String call()
    {
        return this.callBlockLocationInfo();
    }
}
