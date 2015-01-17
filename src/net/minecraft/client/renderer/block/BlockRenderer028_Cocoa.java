package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.IBlockCocoa;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer028_Cocoa extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer028_Cocoa();
	
	private BlockRenderer028_Cocoa()
	{
		super(28);
	}
	
	static void nopInit(){}
	
    /**
     * Renders a Cocoa block at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockCocoa, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(par1BlockCocoa.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        int var6 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        int var7 = BlockDirectional.getDirection(var6);
        int var8 = BlockCocoa.func_72219_c(var6);
        Icon var9 = ((IBlockCocoa)par1BlockCocoa).func_94468_i_(var8);
        int var10 = 4 + var8 * 2;
        int var11 = 5 + var8 * 2;
        double var12 = 15.0D - (double)var10;
        double var14 = 15.0D;
        double var16 = 4.0D;
        double var18 = 4.0D + (double)var11;
        double var20 = (double)var9.getInterpolatedU(var12);
        double var22 = (double)var9.getInterpolatedU(var14);
        double var24 = (double)var9.getInterpolatedV(var16);
        double var26 = (double)var9.getInterpolatedV(var18);
        double var28 = 0.0D;
        double var30 = 0.0D;

        switch (var7)
        {
            case 0:
                var28 = 8.0D - (double)(var10 / 2);
                var30 = 15.0D - (double)var10;
                break;

            case 1:
                var28 = 1.0D;
                var30 = 8.0D - (double)(var10 / 2);
                break;

            case 2:
                var28 = 8.0D - (double)(var10 / 2);
                var30 = 1.0D;
                break;

            case 3:
                var28 = 15.0D - (double)var10;
                var30 = 8.0D - (double)(var10 / 2);
        }

        double var32 = (double)par2 + var28 / 16.0D;
        double var34 = (double)par2 + (var28 + (double)var10) / 16.0D;
        double var36 = (double)par3 + (12.0D - (double)var11) / 16.0D;
        double var38 = (double)par3 + 0.75D;
        double var40 = (double)par4 + var30 / 16.0D;
        double var42 = (double)par4 + (var30 + (double)var10) / 16.0D;
        var5.addVertexWithUV(var32, var36, var40, var20, var26);
        var5.addVertexWithUV(var32, var36, var42, var22, var26);
        var5.addVertexWithUV(var32, var38, var42, var22, var24);
        var5.addVertexWithUV(var32, var38, var40, var20, var24);
        var5.addVertexWithUV(var34, var36, var42, var20, var26);
        var5.addVertexWithUV(var34, var36, var40, var22, var26);
        var5.addVertexWithUV(var34, var38, var40, var22, var24);
        var5.addVertexWithUV(var34, var38, var42, var20, var24);
        var5.addVertexWithUV(var34, var36, var40, var20, var26);
        var5.addVertexWithUV(var32, var36, var40, var22, var26);
        var5.addVertexWithUV(var32, var38, var40, var22, var24);
        var5.addVertexWithUV(var34, var38, var40, var20, var24);
        var5.addVertexWithUV(var32, var36, var42, var20, var26);
        var5.addVertexWithUV(var34, var36, var42, var22, var26);
        var5.addVertexWithUV(var34, var38, var42, var22, var24);
        var5.addVertexWithUV(var32, var38, var42, var20, var24);
        int var44 = var10;

        if (var8 >= 2)
        {
            var44 = var10 - 1;
        }

        var20 = (double)var9.getMinU();
        var22 = (double)var9.getInterpolatedU((double)var44);
        var24 = (double)var9.getMinV();
        var26 = (double)var9.getInterpolatedV((double)var44);
        var5.addVertexWithUV(var32, var38, var42, var20, var26);
        var5.addVertexWithUV(var34, var38, var42, var22, var26);
        var5.addVertexWithUV(var34, var38, var40, var22, var24);
        var5.addVertexWithUV(var32, var38, var40, var20, var24);
        var5.addVertexWithUV(var32, var36, var40, var20, var24);
        var5.addVertexWithUV(var34, var36, var40, var22, var24);
        var5.addVertexWithUV(var34, var36, var42, var22, var26);
        var5.addVertexWithUV(var32, var36, var42, var20, var26);
        var20 = (double)var9.getInterpolatedU(12.0D);
        var22 = (double)var9.getMaxU();
        var24 = (double)var9.getMinV();
        var26 = (double)var9.getInterpolatedV(4.0D);
        var28 = 8.0D;
        var30 = 0.0D;
        double var45;

        switch (var7)
        {
            case 0:
                var28 = 8.0D;
                var30 = 12.0D;
                var45 = var20;
                var20 = var22;
                var22 = var45;
                break;

            case 1:
                var28 = 0.0D;
                var30 = 8.0D;
                break;

            case 2:
                var28 = 8.0D;
                var30 = 0.0D;
                break;

            case 3:
                var28 = 12.0D;
                var30 = 8.0D;
                var45 = var20;
                var20 = var22;
                var22 = var45;
        }

        var32 = (double)par2 + var28 / 16.0D;
        var34 = (double)par2 + (var28 + 4.0D) / 16.0D;
        var36 = (double)par3 + 0.75D;
        var38 = (double)par3 + 1.0D;
        var40 = (double)par4 + var30 / 16.0D;
        var42 = (double)par4 + (var30 + 4.0D) / 16.0D;

        if (var7 != 2 && var7 != 0)
        {
            if (var7 == 1 || var7 == 3)
            {
                var5.addVertexWithUV(var34, var36, var40, var20, var26);
                var5.addVertexWithUV(var32, var36, var40, var22, var26);
                var5.addVertexWithUV(var32, var38, var40, var22, var24);
                var5.addVertexWithUV(var34, var38, var40, var20, var24);
                var5.addVertexWithUV(var32, var36, var40, var22, var26);
                var5.addVertexWithUV(var34, var36, var40, var20, var26);
                var5.addVertexWithUV(var34, var38, var40, var20, var24);
                var5.addVertexWithUV(var32, var38, var40, var22, var24);
            }
        }
        else
        {
            var5.addVertexWithUV(var32, var36, var40, var22, var26);
            var5.addVertexWithUV(var32, var36, var42, var20, var26);
            var5.addVertexWithUV(var32, var38, var42, var20, var24);
            var5.addVertexWithUV(var32, var38, var40, var22, var24);
            var5.addVertexWithUV(var32, var36, var42, var20, var26);
            var5.addVertexWithUV(var32, var36, var40, var22, var26);
            var5.addVertexWithUV(var32, var38, var40, var22, var24);
            var5.addVertexWithUV(var32, var38, var42, var20, var24);
        }

        return true;
    }
}
