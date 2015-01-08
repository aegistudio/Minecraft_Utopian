package net.minecraft.crash;

import java.util.concurrent.Callable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockInfoContainer;

final class CallableBlockType implements Callable
{
    final int blockID;

    CallableBlockType(int par1)
    {
        this.blockID = par1;
    }

    public String callBlockType()
    {
        try
        {
        	BlockInfoContainer whocallme = BlockInfoContainer.getBlockInfoContainer();
            return String.format("ID #%d (%s // %s)", new Object[] {Integer.valueOf(this.blockID), whocallme.getBlock(this.blockID).getUnlocalizedName(), whocallme.getBlock(this.blockID).getClass().getCanonicalName()});
        }
        catch (Throwable var2)
        {
            return "ID #" + this.blockID;
        }
    }

    public Object call()
    {
        return this.callBlockType();
    }
}
