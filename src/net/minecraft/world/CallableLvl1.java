package net.minecraft.world;

import java.util.concurrent.Callable;

import net.minecraft.block.BlockInfoContainer;

class CallableLvl1 implements Callable<String>
{
    final int field_85179_a;

    /** Reference to the World object. */
    final World theWorld;

    CallableLvl1(World par1World, int par2)
    {
        this.theWorld = par1World;
        this.field_85179_a = par2;
    }

    public String getWorldEntitiesAsString()
    {
        try
        {
        	BlockInfoContainer whocallme = BlockInfoContainer.getBlockInfoContainer();
            return String.format("ID #%d (%s // %s)", new Object[] {Integer.valueOf(this.field_85179_a), whocallme.getBlock(this.field_85179_a).getUnlocalizedName(), whocallme.getBlock(this.field_85179_a).getClass().getCanonicalName()});
        }
        catch (Throwable var2)
        {
            return "ID #" + this.field_85179_a;
        }
    }

    public String call()
    {
        return this.getWorldEntitiesAsString();
    }
}
