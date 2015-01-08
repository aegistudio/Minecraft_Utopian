package net.minecraft.tileentity;

import java.util.concurrent.Callable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockInfoContainer;

class CallableTileEntityID implements Callable
{
    final TileEntity theTileEntity;

    CallableTileEntityID(TileEntity par1TileEntity)
    {
        this.theTileEntity = par1TileEntity;
    }

    public String callTileEntityID()
    {
    	BlockInfoContainer whocallme = BlockInfoContainer.getBlockInfoContainer();
    	
        int var1 = this.theTileEntity.worldObj.getBlockId(this.theTileEntity.xCoord, this.theTileEntity.yCoord, this.theTileEntity.zCoord);

        try
        {
            return String.format("ID #%d (%s // %s)", new Object[] {Integer.valueOf(var1), whocallme.getBlock(var1).getUnlocalizedName(), whocallme.getBlock(var1).getClass().getCanonicalName()});
        }
        catch (Throwable var3)
        {
            return "ID #" + var1;
        }
    }

    public Object call()
    {
        return this.callTileEntityID();
    }
}
