package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenMinable extends WorldGenerator
{
    /** The block ID of the ore to be placed using this generator. */
    private int minableBlockId;

    /** The number of blocks to generate. */
    private int numberOfBlocks;
    private int replacingBlockId;

    public WorldGenMinable(int minableBlockId, int numberOfBlocks)
    {
        this(minableBlockId, numberOfBlocks, Block.stone.blockID);
    }

    public WorldGenMinable(int par1, int par2, int par3)
    {
        this.minableBlockId = par1;
        this.numberOfBlocks = par2;
        this.replacingBlockId = par3;
    }

    public boolean generate(World par1World, Random par2Random, int x, int y, int z)
    {
        float n_pai = par2Random.nextFloat() * (float)Math.PI;
        
        //Eight stands for the middle size of chunk, which will bring the gen to the middle.
        
        float blockFactor = (float)this.numberOfBlocks / 8.0F;
        
        float calculatedSine = MathHelper.sin(n_pai) * blockFactor;
        double x_axis_a = (double)((float)(x + 8) + calculatedSine);
        double x_axis_b = (double)((float)(x + 8) - calculatedSine);
        
        float calculatedCosine = MathHelper.cos(n_pai) * blockFactor;
        double z_axis_a = (double)((float)(z + 8) + calculatedCosine);
        double z_axis_b = (double)((float)(z + 8) - calculatedCosine);
        
        double y_axis_a = (double)(y + par2Random.nextInt(3) - 2);
        double y_axis_b = (double)(y + par2Random.nextInt(3) - 2);

        for (int i = 0; i <= this.numberOfBlocks; ++i)
        {
            double var20 = x_axis_a + (x_axis_b - x_axis_a) * (double)i / (double)this.numberOfBlocks;
            double var22 = y_axis_a + (y_axis_b - y_axis_a) * (double)i / (double)this.numberOfBlocks;
            double var24 = z_axis_a + (z_axis_b - z_axis_a) * (double)i / (double)this.numberOfBlocks;
            double var26 = par2Random.nextDouble() * (double)this.numberOfBlocks / 16.0D;
            double var28 = (double)(MathHelper.sin((float)i * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
            double var30 = (double)(MathHelper.sin((float)i * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
            
            int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
            int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
            int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
            int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
            int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
            int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

            for (int var38 = var32; var38 <= var35; ++var38)
            {
                double var39 = ((double)var38 + 0.5D - var20) / (var28 / 2.0D);

                if (var39 * var39 < 1.0D)
                {
                    for (int var41 = var33; var41 <= var36; ++var41)
                    {
                        double var42 = ((double)var41 + 0.5D - var22) / (var30 / 2.0D);

                        if (var39 * var39 + var42 * var42 < 1.0D)
                        {
                            for (int var44 = var34; var44 <= var37; ++var44)
                            {
                                double var45 = ((double)var44 + 0.5D - var24) / (var28 / 2.0D);

                                if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D && par1World.getBlockId(var38, var41, var44) == this.replacingBlockId)
                                {
                                    par1World.setBlock(var38, var41, var44, this.minableBlockId, 0, 2);
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
