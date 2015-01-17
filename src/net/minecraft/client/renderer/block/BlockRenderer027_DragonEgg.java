package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;

public class BlockRenderer027_DragonEgg extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer027_DragonEgg();
	
	private BlockRenderer027_DragonEgg()
	{
		super(27);
	}
	
	static void nopInit(){}
	
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockDragonEgg, int par2, int par3, int par4)
    {
        boolean var5 = false;
        int var6 = 0;

        for (int var7 = 0; var7 < 8; ++var7)
        {
            byte var8 = 0;
            byte var9 = 1;

            if (var7 == 0)
            {
                var8 = 2;
            }

            if (var7 == 1)
            {
                var8 = 3;
            }

            if (var7 == 2)
            {
                var8 = 4;
            }

            if (var7 == 3)
            {
                var8 = 5;
                var9 = 2;
            }

            if (var7 == 4)
            {
                var8 = 6;
                var9 = 3;
            }

            if (var7 == 5)
            {
                var8 = 7;
                var9 = 5;
            }

            if (var7 == 6)
            {
                var8 = 6;
                var9 = 2;
            }

            if (var7 == 7)
            {
                var8 = 3;
            }

            float var10 = (float)var8 / 16.0F;
            float var11 = 1.0F - (float)var6 / 16.0F;
            float var12 = 1.0F - (float)(var6 + var9) / 16.0F;
            var6 += var9;
            renderBlocks.setRenderBounds((double)(0.5F - var10), (double)var12, (double)(0.5F - var10), (double)(0.5F + var10), (double)var11, (double)(0.5F + var10));
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockDragonEgg, par2, par3, par4);
        }

        var5 = true;
        renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        return var5;
    }
}
