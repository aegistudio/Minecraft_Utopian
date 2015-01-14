package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class BlockRendererFluids extends BlockRenderer
{

	public static BlockRenderer renderer = new BlockRendererFluids();
	
	private BlockRendererFluids()
	{
		super(4);
	}
	
	static void nopInit(){}
	
	/**
     * Renders a block based on the BlockFluids class at the given coordinates
     */
    public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        int var6 = par1Block.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
        float var7 = (float)(var6 >> 16 & 255) / 255.0F;
        float var8 = (float)(var6 >> 8 & 255) / 255.0F;
        float var9 = (float)(var6 & 255) / 255.0F;
        boolean var10 = par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 + 1, par4, 1);
        boolean var11 = par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 - 1, par4, 0);
        boolean[] var12 = new boolean[] {par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 - 1, 2), par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 + 1, 3), par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 - 1, par3, par4, 4), par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 + 1, par3, par4, 5)};

        if (!var10 && !var11 && !var12[0] && !var12[1] && !var12[2] && !var12[3])
        {
            return false;
        }
        else
        {
            boolean var13 = false;
            float var14 = 0.5F;
            float var15 = 1.0F;
            float var16 = 0.8F;
            float var17 = 0.6F;
            double var18 = 0.0D;
            double var20 = 1.0D;
            Material var22 = par1Block.blockMaterial;
            int var23 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
            double var24 = (double)this.getFluidHeight(renderBlocks, par2, par3, par4, var22);
            double var26 = (double)this.getFluidHeight(renderBlocks, par2, par3, par4 + 1, var22);
            double var28 = (double)this.getFluidHeight(renderBlocks, par2 + 1, par3, par4 + 1, var22);
            double var30 = (double)this.getFluidHeight(renderBlocks, par2 + 1, par3, par4, var22);
            double var32 = 0.0010000000474974513D;
            float var53;
            float var52;

            if (renderBlocks.renderAllFaces || var10)
            {
                var13 = true;
                Icon var34 = renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 1, var23);
                float var35 = (float)BlockFluid.getFlowDirection(renderBlocks.blockAccess, par2, par3, par4, var22);

                if (var35 > -999.0F)
                {
                    var34 = renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 2, var23);
                }

                var24 -= var32;
                var26 -= var32;
                var28 -= var32;
                var30 -= var32;
                double var38;
                double var36;
                double var42;
                double var40;
                double var46;
                double var44;
                double var50;
                double var48;

                if (var35 < -999.0F)
                {
                    var36 = (double)var34.getInterpolatedU(0.0D);
                    var44 = (double)var34.getInterpolatedV(0.0D);
                    var38 = var36;
                    var46 = (double)var34.getInterpolatedV(16.0D);
                    var40 = (double)var34.getInterpolatedU(16.0D);
                    var48 = var46;
                    var42 = var40;
                    var50 = var44;
                }
                else
                {
                    var52 = MathHelper.sin(var35) * 0.25F;
                    var53 = MathHelper.cos(var35) * 0.25F;
                    var36 = (double)var34.getInterpolatedU((double)(8.0F + (-var53 - var52) * 16.0F));
                    var44 = (double)var34.getInterpolatedV((double)(8.0F + (-var53 + var52) * 16.0F));
                    var38 = (double)var34.getInterpolatedU((double)(8.0F + (-var53 + var52) * 16.0F));
                    var46 = (double)var34.getInterpolatedV((double)(8.0F + (var53 + var52) * 16.0F));
                    var40 = (double)var34.getInterpolatedU((double)(8.0F + (var53 + var52) * 16.0F));
                    var48 = (double)var34.getInterpolatedV((double)(8.0F + (var53 - var52) * 16.0F));
                    var42 = (double)var34.getInterpolatedU((double)(8.0F + (var53 - var52) * 16.0F));
                    var50 = (double)var34.getInterpolatedV((double)(8.0F + (-var53 - var52) * 16.0F));
                }

                var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
                var52 = 1.0F;
                var5.setColorOpaque_F(var15 * var52 * var7, var15 * var52 * var8, var15 * var52 * var9);
                var5.addVertexWithUV((double)(par2 + 0), (double)par3 + var24, (double)(par4 + 0), var36, var44);
                var5.addVertexWithUV((double)(par2 + 0), (double)par3 + var26, (double)(par4 + 1), var38, var46);
                var5.addVertexWithUV((double)(par2 + 1), (double)par3 + var28, (double)(par4 + 1), var40, var48);
                var5.addVertexWithUV((double)(par2 + 1), (double)par3 + var30, (double)(par4 + 0), var42, var50);
            }

            if (renderBlocks.renderAllFaces || var11)
            {
                var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4));
                float var58 = 1.0F;
                var5.setColorOpaque_F(var14 * var58, var14 * var58, var14 * var58);
                renderBlocks.renderFaceYNeg(par1Block, (double)par2, (double)par3 + var32, (double)par4, renderBlocks.getBlockIconFromSide(par1Block, 0));
                var13 = true;
            }

            for (int var57 = 0; var57 < 4; ++var57)
            {
                int var59 = par2;
                int var37 = par4;

                if (var57 == 0)
                {
                    var37 = par4 - 1;
                }

                if (var57 == 1)
                {
                    ++var37;
                }

                if (var57 == 2)
                {
                    var59 = par2 - 1;
                }

                if (var57 == 3)
                {
                    ++var59;
                }

                Icon var60 = renderBlocks.getBlockIconFromSideAndMetadata(par1Block, var57 + 2, var23);

                if (renderBlocks.renderAllFaces || var12[var57])
                {
                    double var39;
                    double var43;
                    double var41;
                    double var47;
                    double var45;
                    double var49;

                    if (var57 == 0)
                    {
                        var39 = var24;
                        var41 = var30;
                        var43 = (double)par2;
                        var47 = (double)(par2 + 1);
                        var45 = (double)par4 + var32;
                        var49 = (double)par4 + var32;
                    }
                    else if (var57 == 1)
                    {
                        var39 = var28;
                        var41 = var26;
                        var43 = (double)(par2 + 1);
                        var47 = (double)par2;
                        var45 = (double)(par4 + 1) - var32;
                        var49 = (double)(par4 + 1) - var32;
                    }
                    else if (var57 == 2)
                    {
                        var39 = var26;
                        var41 = var24;
                        var43 = (double)par2 + var32;
                        var47 = (double)par2 + var32;
                        var45 = (double)(par4 + 1);
                        var49 = (double)par4;
                    }
                    else
                    {
                        var39 = var30;
                        var41 = var28;
                        var43 = (double)(par2 + 1) - var32;
                        var47 = (double)(par2 + 1) - var32;
                        var45 = (double)par4;
                        var49 = (double)(par4 + 1);
                    }

                    var13 = true;
                    float var51 = var60.getInterpolatedU(0.0D);
                    var52 = var60.getInterpolatedU(8.0D);
                    var53 = var60.getInterpolatedV((1.0D - var39) * 16.0D * 0.5D);
                    float var54 = var60.getInterpolatedV((1.0D - var41) * 16.0D * 0.5D);
                    float var55 = var60.getInterpolatedV(8.0D);
                    var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, var59, par3, var37));
                    float var56 = 1.0F;

                    if (var57 < 2)
                    {
                        var56 *= var16;
                    }
                    else
                    {
                        var56 *= var17;
                    }

                    var5.setColorOpaque_F(var15 * var56 * var7, var15 * var56 * var8, var15 * var56 * var9);
                    var5.addVertexWithUV(var43, (double)par3 + var39, var45, (double)var51, (double)var53);
                    var5.addVertexWithUV(var47, (double)par3 + var41, var49, (double)var52, (double)var54);
                    var5.addVertexWithUV(var47, (double)(par3 + 0), var49, (double)var52, (double)var55);
                    var5.addVertexWithUV(var43, (double)(par3 + 0), var45, (double)var51, (double)var55);
                }
            }

            renderBlocks.renderMinY = var18;
            renderBlocks.renderMaxY = var20;
            return var13;
        }
    }

    /**
     * Get fluid height
     */
    private float getFluidHeight(RenderBlocks renderBlocks, int par1, int par2, int par3, Material par4Material)
    {
        int var5 = 0;
        float var6 = 0.0F;

        for (int var7 = 0; var7 < 4; ++var7)
        {
            int var8 = par1 - (var7 & 1);
            int var10 = par3 - (var7 >> 1 & 1);

            if (renderBlocks.blockAccess.getBlockMaterial(var8, par2 + 1, var10) == par4Material)
            {
                return 1.0F;
            }

            Material var11 = renderBlocks.blockAccess.getBlockMaterial(var8, par2, var10);

            if (var11 == par4Material)
            {
                int var12 = renderBlocks.blockAccess.getBlockMetadata(var8, par2, var10);

                if (var12 >= 8 || var12 == 0)
                {
                    var6 += BlockFluid.getFluidHeightPercent(var12) * 10.0F;
                    var5 += 10;
                }

                var6 += BlockFluid.getFluidHeightPercent(var12);
                ++var5;
            }
            else if (!var11.isSolid())
            {
                ++var6;
                ++var5;
            }
        }

        return 1.0F - var6 / (float)var5;
    }
}
