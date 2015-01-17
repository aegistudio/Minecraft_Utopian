package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockFire;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer003_Fire extends BlockRenderer
{

	public static final BlockRenderer renderer = new BlockRenderer003_Fire();
	
	private BlockRenderer003_Fire()
	{
		super(3);
	}
	
	static void nopInit(){}

    /**
     * Renders a fire block at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
		IBlockFire par1BlockFire = (IBlockFire) par1Block;
        Tessellator var5 = Tessellator.instance;
        Icon var6 = par1BlockFire.func_94438_c(0);
        Icon var7 = par1BlockFire.func_94438_c(1);
        Icon var8 = var6;

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var8 = renderBlocks.overrideBlockTexture;
        }

        var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        double var9 = (double)var8.getMinU();
        double var11 = (double)var8.getMinV();
        double var13 = (double)var8.getMaxU();
        double var15 = (double)var8.getMaxV();
        float var17 = 1.4F;
        double var32;
        double var20;
        double var22;
        double var24;
        double var26;
        double var28;
        double var30;

        if (!renderBlocks.blockAccess.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && !Block.fire.canBlockCatchFire(renderBlocks.blockAccess, par2, par3 - 1, par4))
        {
            float var36 = 0.2F;
            float var19 = 0.0625F;

            if ((par2 + par3 + par4 & 1) == 1)
            {
                var9 = (double)var7.getMinU();
                var11 = (double)var7.getMinV();
                var13 = (double)var7.getMaxU();
                var15 = (double)var7.getMaxV();
            }

            if ((par2 / 2 + par3 / 2 + par4 / 2 & 1) == 1)
            {
                var20 = var13;
                var13 = var9;
                var9 = var20;
            }

            if (Block.fire.canBlockCatchFire(renderBlocks.blockAccess, par2 - 1, par3, par4))
            {
                var5.addVertexWithUV((double)((float)par2 + var36), (double)((float)par3 + var17 + var19), (double)(par4 + 1), var13, var11);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1), var13, var15);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
                var5.addVertexWithUV((double)((float)par2 + var36), (double)((float)par3 + var17 + var19), (double)(par4 + 0), var9, var11);
                var5.addVertexWithUV((double)((float)par2 + var36), (double)((float)par3 + var17 + var19), (double)(par4 + 0), var9, var11);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1), var13, var15);
                var5.addVertexWithUV((double)((float)par2 + var36), (double)((float)par3 + var17 + var19), (double)(par4 + 1), var13, var11);
            }

            if (Block.fire.canBlockCatchFire(renderBlocks.blockAccess, par2 + 1, par3, par4))
            {
                var5.addVertexWithUV((double)((float)(par2 + 1) - var36), (double)((float)par3 + var17 + var19), (double)(par4 + 0), var9, var11);
                var5.addVertexWithUV((double)(par2 + 1 - 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
                var5.addVertexWithUV((double)(par2 + 1 - 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1), var13, var15);
                var5.addVertexWithUV((double)((float)(par2 + 1) - var36), (double)((float)par3 + var17 + var19), (double)(par4 + 1), var13, var11);
                var5.addVertexWithUV((double)((float)(par2 + 1) - var36), (double)((float)par3 + var17 + var19), (double)(par4 + 1), var13, var11);
                var5.addVertexWithUV((double)(par2 + 1 - 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1), var13, var15);
                var5.addVertexWithUV((double)(par2 + 1 - 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
                var5.addVertexWithUV((double)((float)(par2 + 1) - var36), (double)((float)par3 + var17 + var19), (double)(par4 + 0), var9, var11);
            }

            if (Block.fire.canBlockCatchFire(renderBlocks.blockAccess, par2, par3, par4 - 1))
            {
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17 + var19), (double)((float)par4 + var36), var13, var11);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var13, var15);
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17 + var19), (double)((float)par4 + var36), var9, var11);
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17 + var19), (double)((float)par4 + var36), var9, var11);
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var13, var15);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17 + var19), (double)((float)par4 + var36), var13, var11);
            }

            if (Block.fire.canBlockCatchFire(renderBlocks.blockAccess, par2, par3, par4 + 1))
            {
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17 + var19), (double)((float)(par4 + 1) - var36), var9, var11);
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 0) + var19), (double)(par4 + 1 - 0), var9, var15);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1 - 0), var13, var15);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17 + var19), (double)((float)(par4 + 1) - var36), var13, var11);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17 + var19), (double)((float)(par4 + 1) - var36), var13, var11);
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1 - 0), var13, var15);
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 0) + var19), (double)(par4 + 1 - 0), var9, var15);
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17 + var19), (double)((float)(par4 + 1) - var36), var9, var11);
            }

            if (Block.fire.canBlockCatchFire(renderBlocks.blockAccess, par2, par3 + 1, par4))
            {
                var20 = (double)par2 + 0.5D + 0.5D;
                var22 = (double)par2 + 0.5D - 0.5D;
                var24 = (double)par4 + 0.5D + 0.5D;
                var26 = (double)par4 + 0.5D - 0.5D;
                var28 = (double)par2 + 0.5D - 0.5D;
                var30 = (double)par2 + 0.5D + 0.5D;
                var32 = (double)par4 + 0.5D - 0.5D;
                double var34 = (double)par4 + 0.5D + 0.5D;
                var9 = (double)var6.getMinU();
                var11 = (double)var6.getMinV();
                var13 = (double)var6.getMaxU();
                var15 = (double)var6.getMaxV();
                ++par3;
                var17 = -0.2F;

                if ((par2 + par3 + par4 & 1) == 0)
                {
                    var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 0), var13, var11);
                    var5.addVertexWithUV(var20, (double)(par3 + 0), (double)(par4 + 0), var13, var15);
                    var5.addVertexWithUV(var20, (double)(par3 + 0), (double)(par4 + 1), var9, var15);
                    var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 1), var9, var11);
                    var9 = (double)var7.getMinU();
                    var11 = (double)var7.getMinV();
                    var13 = (double)var7.getMaxU();
                    var15 = (double)var7.getMaxV();
                    var5.addVertexWithUV(var30, (double)((float)par3 + var17), (double)(par4 + 1), var13, var11);
                    var5.addVertexWithUV(var22, (double)(par3 + 0), (double)(par4 + 1), var13, var15);
                    var5.addVertexWithUV(var22, (double)(par3 + 0), (double)(par4 + 0), var9, var15);
                    var5.addVertexWithUV(var30, (double)((float)par3 + var17), (double)(par4 + 0), var9, var11);
                }
                else
                {
                    var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17), var34, var13, var11);
                    var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), var26, var13, var15);
                    var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), var26, var9, var15);
                    var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17), var34, var9, var11);
                    var9 = (double)var7.getMinU();
                    var11 = (double)var7.getMinV();
                    var13 = (double)var7.getMaxU();
                    var15 = (double)var7.getMaxV();
                    var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17), var32, var13, var11);
                    var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), var24, var13, var15);
                    var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), var24, var9, var15);
                    var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17), var32, var9, var11);
                }
            }
        }
        else
        {
            double var18 = (double)par2 + 0.5D + 0.2D;
            var20 = (double)par2 + 0.5D - 0.2D;
            var22 = (double)par4 + 0.5D + 0.2D;
            var24 = (double)par4 + 0.5D - 0.2D;
            var26 = (double)par2 + 0.5D - 0.3D;
            var28 = (double)par2 + 0.5D + 0.3D;
            var30 = (double)par4 + 0.5D - 0.3D;
            var32 = (double)par4 + 0.5D + 0.3D;
            var5.addVertexWithUV(var26, (double)((float)par3 + var17), (double)(par4 + 1), var13, var11);
            var5.addVertexWithUV(var18, (double)(par3 + 0), (double)(par4 + 1), var13, var15);
            var5.addVertexWithUV(var18, (double)(par3 + 0), (double)(par4 + 0), var9, var15);
            var5.addVertexWithUV(var26, (double)((float)par3 + var17), (double)(par4 + 0), var9, var11);
            var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 0), var13, var11);
            var5.addVertexWithUV(var20, (double)(par3 + 0), (double)(par4 + 0), var13, var15);
            var5.addVertexWithUV(var20, (double)(par3 + 0), (double)(par4 + 1), var9, var15);
            var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 1), var9, var11);
            var9 = (double)var7.getMinU();
            var11 = (double)var7.getMinV();
            var13 = (double)var7.getMaxU();
            var15 = (double)var7.getMaxV();
            var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17), var32, var13, var11);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), var24, var13, var15);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), var24, var9, var15);
            var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17), var32, var9, var11);
            var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17), var30, var13, var11);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), var22, var13, var15);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), var22, var9, var15);
            var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17), var30, var9, var11);
            var18 = (double)par2 + 0.5D - 0.5D;
            var20 = (double)par2 + 0.5D + 0.5D;
            var22 = (double)par4 + 0.5D - 0.5D;
            var24 = (double)par4 + 0.5D + 0.5D;
            var26 = (double)par2 + 0.5D - 0.4D;
            var28 = (double)par2 + 0.5D + 0.4D;
            var30 = (double)par4 + 0.5D - 0.4D;
            var32 = (double)par4 + 0.5D + 0.4D;
            var5.addVertexWithUV(var26, (double)((float)par3 + var17), (double)(par4 + 0), var9, var11);
            var5.addVertexWithUV(var18, (double)(par3 + 0), (double)(par4 + 0), var9, var15);
            var5.addVertexWithUV(var18, (double)(par3 + 0), (double)(par4 + 1), var13, var15);
            var5.addVertexWithUV(var26, (double)((float)par3 + var17), (double)(par4 + 1), var13, var11);
            var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 1), var9, var11);
            var5.addVertexWithUV(var20, (double)(par3 + 0), (double)(par4 + 1), var9, var15);
            var5.addVertexWithUV(var20, (double)(par3 + 0), (double)(par4 + 0), var13, var15);
            var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 0), var13, var11);
            var9 = (double)var6.getMinU();
            var11 = (double)var6.getMinV();
            var13 = (double)var6.getMaxU();
            var15 = (double)var6.getMaxV();
            var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17), var32, var9, var11);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), var24, var9, var15);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), var24, var13, var15);
            var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17), var32, var13, var11);
            var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17), var30, var9, var11);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), var22, var9, var15);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), var22, var13, var15);
            var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17), var30, var13, var11);
        }

        return true;
    }
}
