package net.minecraft.world.gen.feature;

import java.util.Random;

import net.minecraft.block.BlockInfoContainer;
import net.minecraft.world.World;

public class WorldGenFlowers extends WorldGenerator
{
    /** The ID of the plant block used in this plant generator. */
    private int plantBlockId;

    public WorldGenFlowers(int par1)
    {
        this.plantBlockId = par1;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
    	BlockInfoContainer whocallme = BlockInfoContainer.getBlockInfoContainer();
    	
        for (int generateLoop = 0; generateLoop < 64; ++generateLoop)
        {
            int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(var7, var8, var9) && (!par1World.provider.hasNoSky || var8 < 127) && whocallme.getBlock(this.plantBlockId).canBlockStay(par1World, var7, var8, var9))
            {
                par1World.setBlock(var7, var8, var9, this.plantBlockId, 0, 2);
            }
        }

        return true;
    }
}
