package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockPane;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer018_Pane extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer018_Pane();
	
	private BlockRenderer018_Pane()
	{
		super(18);
	}
	
	static void nopInit(){}
	
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
		IBlockPane par1BlockPane = (IBlockPane) par1Block;
        int var5 = renderBlocks.blockAccess.getHeight();
        Tessellator var6 = Tessellator.instance;
        var6.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var7 = 1.0F;
        int var8 = par1Block.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
        float var9 = (float)(var8 >> 16 & 255) / 255.0F;
        float var10 = (float)(var8 >> 8 & 255) / 255.0F;
        float var11 = (float)(var8 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var12 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
            float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
            float var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
            var9 = var12;
            var10 = var13;
            var11 = var14;
        }

        var6.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
        Icon var64;
        Icon var65;
        int var66;

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var64 = renderBlocks.overrideBlockTexture;
            var65 = renderBlocks.overrideBlockTexture;
        }
        else
        {
            var66 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
            var64 = renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 0, var66);
            var65 = par1BlockPane.getSideTextureIndex();
        }

        var66 = var64.getOriginX();
        /*int var15 = */var64.getOriginY();
        double var16 = (double)var64.getMinU();
        double var18 = (double)var64.getInterpolatedU(8.0D);
        double var20 = (double)var64.getMaxU();
        double var22 = (double)var64.getMinV();
        double var24 = (double)var64.getMaxV();
        /*int var26 = */var65.getOriginX();
        /*int var27 = */var65.getOriginY();
        double var28 = (double)var65.getInterpolatedU(7.0D);
        double var30 = (double)var65.getInterpolatedU(9.0D);
        double var32 = (double)var65.getMinV();
        double var34 = (double)var65.getInterpolatedV(8.0D);
        double var36 = (double)var65.getMaxV();
        double var38 = (double)par2;
        double var40 = (double)par2 + 0.5D;
        double var42 = (double)(par2 + 1);
        double var44 = (double)par4;
        double var46 = (double)par4 + 0.5D;
        double var48 = (double)(par4 + 1);
        double var50 = (double)par2 + 0.5D - 0.0625D;
        double var52 = (double)par2 + 0.5D + 0.0625D;
        double var54 = (double)par4 + 0.5D - 0.0625D;
        double var56 = (double)par4 + 0.5D + 0.0625D;
        boolean var58 = par1BlockPane.canThisPaneConnectToThisBlockID(renderBlocks.blockAccess.getBlockId(par2, par3, par4 - 1));
        boolean var59 = par1BlockPane.canThisPaneConnectToThisBlockID(renderBlocks.blockAccess.getBlockId(par2, par3, par4 + 1));
        boolean var60 = par1BlockPane.canThisPaneConnectToThisBlockID(renderBlocks.blockAccess.getBlockId(par2 - 1, par3, par4));
        boolean var61 = par1BlockPane.canThisPaneConnectToThisBlockID(renderBlocks.blockAccess.getBlockId(par2 + 1, par3, par4));
        boolean var62 = par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 + 1, par4, 1);
        boolean var63 = par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 - 1, par4, 0);

        if ((!var60 || !var61) && (var60 || var61 || var58 || var59))
        {
            if (var60 && !var61)
            {
                var6.addVertexWithUV(var38, (double)(par3 + 1), var46, var16, var22);
                var6.addVertexWithUV(var38, (double)(par3 + 0), var46, var16, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var46, var18, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 1), var46, var18, var22);
                var6.addVertexWithUV(var40, (double)(par3 + 1), var46, var16, var22);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var46, var16, var24);
                var6.addVertexWithUV(var38, (double)(par3 + 0), var46, var18, var24);
                var6.addVertexWithUV(var38, (double)(par3 + 1), var46, var18, var22);

                if (!var59 && !var58)
                {
                    var6.addVertexWithUV(var40, (double)(par3 + 1), var56, var28, var32);
                    var6.addVertexWithUV(var40, (double)(par3 + 0), var56, var28, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 0), var54, var30, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 1), var54, var30, var32);
                    var6.addVertexWithUV(var40, (double)(par3 + 1), var54, var28, var32);
                    var6.addVertexWithUV(var40, (double)(par3 + 0), var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 0), var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 1), var56, var30, var32);
                }

                if (var62 || par3 < var5 - 1 && renderBlocks.blockAccess.isAirBlock(par2 - 1, par3 + 1, par4))
                {
                    var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var54, var28, var34);
                }

                if (var63 || par3 > 1 && renderBlocks.blockAccess.isAirBlock(par2 - 1, par3 - 1, par4))
                {
                    var6.addVertexWithUV(var38, (double)par3 - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var38, (double)par3 - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var38, (double)par3 - 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var38, (double)par3 - 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var54, var28, var34);
                }
            }
            else if (!var60 && var61)
            {
                var6.addVertexWithUV(var40, (double)(par3 + 1), var46, var18, var22);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var46, var18, var24);
                var6.addVertexWithUV(var42, (double)(par3 + 0), var46, var20, var24);
                var6.addVertexWithUV(var42, (double)(par3 + 1), var46, var20, var22);
                var6.addVertexWithUV(var42, (double)(par3 + 1), var46, var18, var22);
                var6.addVertexWithUV(var42, (double)(par3 + 0), var46, var18, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var46, var20, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 1), var46, var20, var22);

                if (!var59 && !var58)
                {
                    var6.addVertexWithUV(var40, (double)(par3 + 1), var54, var28, var32);
                    var6.addVertexWithUV(var40, (double)(par3 + 0), var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 0), var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 1), var56, var30, var32);
                    var6.addVertexWithUV(var40, (double)(par3 + 1), var56, var28, var32);
                    var6.addVertexWithUV(var40, (double)(par3 + 0), var56, var28, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 0), var54, var30, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 1), var54, var30, var32);
                }

                if (var62 || par3 < var5 - 1 && renderBlocks.blockAccess.isAirBlock(par2 + 1, par3 + 1, par4))
                {
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var54, var28, var32);
                    var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var54, var28, var32);
                }

                if (var63 || par3 > 1 && renderBlocks.blockAccess.isAirBlock(par2 + 1, par3 - 1, par4))
                {
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var42, (double)par3 - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var42, (double)par3 - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var54, var28, var32);
                    var6.addVertexWithUV(var42, (double)par3 - 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var42, (double)par3 - 0.01D, var54, var28, var32);
                }
            }
        }
        else
        {
            var6.addVertexWithUV(var38, (double)(par3 + 1), var46, var16, var22);
            var6.addVertexWithUV(var38, (double)(par3 + 0), var46, var16, var24);
            var6.addVertexWithUV(var42, (double)(par3 + 0), var46, var20, var24);
            var6.addVertexWithUV(var42, (double)(par3 + 1), var46, var20, var22);
            var6.addVertexWithUV(var42, (double)(par3 + 1), var46, var16, var22);
            var6.addVertexWithUV(var42, (double)(par3 + 0), var46, var16, var24);
            var6.addVertexWithUV(var38, (double)(par3 + 0), var46, var20, var24);
            var6.addVertexWithUV(var38, (double)(par3 + 1), var46, var20, var22);

            if (var62)
            {
                var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var56, var30, var36);
                var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var56, var30, var32);
                var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var54, var28, var32);
                var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var54, var28, var36);
                var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var56, var30, var36);
                var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var56, var30, var32);
                var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var54, var28, var32);
                var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var54, var28, var36);
            }
            else
            {
                if (par3 < var5 - 1 && renderBlocks.blockAccess.isAirBlock(par2 - 1, par3 + 1, par4))
                {
                    var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var38, (double)(par3 + 1) + 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var54, var28, var34);
                }

                if (par3 < var5 - 1 && renderBlocks.blockAccess.isAirBlock(par2 + 1, par3 + 1, par4))
                {
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var54, var28, var32);
                    var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)(par3 + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var42, (double)(par3 + 1) + 0.01D, var54, var28, var32);
                }
            }

            if (var63)
            {
                var6.addVertexWithUV(var38, (double)par3 - 0.01D, var56, var30, var36);
                var6.addVertexWithUV(var42, (double)par3 - 0.01D, var56, var30, var32);
                var6.addVertexWithUV(var42, (double)par3 - 0.01D, var54, var28, var32);
                var6.addVertexWithUV(var38, (double)par3 - 0.01D, var54, var28, var36);
                var6.addVertexWithUV(var42, (double)par3 - 0.01D, var56, var30, var36);
                var6.addVertexWithUV(var38, (double)par3 - 0.01D, var56, var30, var32);
                var6.addVertexWithUV(var38, (double)par3 - 0.01D, var54, var28, var32);
                var6.addVertexWithUV(var42, (double)par3 - 0.01D, var54, var28, var36);
            }
            else
            {
                if (par3 > 1 && renderBlocks.blockAccess.isAirBlock(par2 - 1, par3 - 1, par4))
                {
                    var6.addVertexWithUV(var38, (double)par3 - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var38, (double)par3 - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var38, (double)par3 - 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var38, (double)par3 - 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var54, var28, var34);
                }

                if (par3 > 1 && renderBlocks.blockAccess.isAirBlock(par2 + 1, par3 - 1, par4))
                {
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var42, (double)par3 - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var42, (double)par3 - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var54, var28, var32);
                    var6.addVertexWithUV(var42, (double)par3 - 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)par3 - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var42, (double)par3 - 0.01D, var54, var28, var32);
                }
            }
        }

        if ((!var58 || !var59) && (var60 || var61 || var58 || var59))
        {
            if (var58 && !var59)
            {
                var6.addVertexWithUV(var40, (double)(par3 + 1), var44, var16, var22);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var44, var16, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var46, var18, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 1), var46, var18, var22);
                var6.addVertexWithUV(var40, (double)(par3 + 1), var46, var16, var22);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var46, var16, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var44, var18, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 1), var44, var18, var22);

                if (!var61 && !var60)
                {
                    var6.addVertexWithUV(var50, (double)(par3 + 1), var46, var28, var32);
                    var6.addVertexWithUV(var50, (double)(par3 + 0), var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 0), var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 1), var46, var30, var32);
                    var6.addVertexWithUV(var52, (double)(par3 + 1), var46, var28, var32);
                    var6.addVertexWithUV(var52, (double)(par3 + 0), var46, var28, var36);
                    var6.addVertexWithUV(var50, (double)(par3 + 0), var46, var30, var36);
                    var6.addVertexWithUV(var50, (double)(par3 + 1), var46, var30, var32);
                }

                if (var62 || par3 < var5 - 1 && renderBlocks.blockAccess.isAirBlock(par2, par3 + 1, par4 - 1))
                {
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var44, var30, var32);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var44, var28, var32);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var46, var30, var32);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var44, var30, var34);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var44, var28, var34);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var46, var28, var32);
                }

                if (var63 || par3 > 1 && renderBlocks.blockAccess.isAirBlock(par2, par3 - 1, par4 - 1))
                {
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var44, var30, var32);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var44, var28, var32);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var46, var30, var32);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var44, var30, var34);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var44, var28, var34);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var46, var28, var32);
                }
            }
            else if (!var58 && var59)
            {
                var6.addVertexWithUV(var40, (double)(par3 + 1), var46, var18, var22);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var46, var18, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var48, var20, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 1), var48, var20, var22);
                var6.addVertexWithUV(var40, (double)(par3 + 1), var48, var18, var22);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var48, var18, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 0), var46, var20, var24);
                var6.addVertexWithUV(var40, (double)(par3 + 1), var46, var20, var22);

                if (!var61 && !var60)
                {
                    var6.addVertexWithUV(var52, (double)(par3 + 1), var46, var28, var32);
                    var6.addVertexWithUV(var52, (double)(par3 + 0), var46, var28, var36);
                    var6.addVertexWithUV(var50, (double)(par3 + 0), var46, var30, var36);
                    var6.addVertexWithUV(var50, (double)(par3 + 1), var46, var30, var32);
                    var6.addVertexWithUV(var50, (double)(par3 + 1), var46, var28, var32);
                    var6.addVertexWithUV(var50, (double)(par3 + 0), var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 0), var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 1), var46, var30, var32);
                }

                if (var62 || par3 < var5 - 1 && renderBlocks.blockAccess.isAirBlock(par2, par3 + 1, par4 + 1))
                {
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var48, var28, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var48, var30, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var48, var28, var34);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var48, var30, var34);
                }

                if (var63 || par3 > 1 && renderBlocks.blockAccess.isAirBlock(par2, par3 - 1, par4 + 1))
                {
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var48, var28, var36);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var48, var30, var36);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var48, var28, var34);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var48, var30, var34);
                }
            }
        }
        else
        {
            var6.addVertexWithUV(var40, (double)(par3 + 1), var48, var16, var22);
            var6.addVertexWithUV(var40, (double)(par3 + 0), var48, var16, var24);
            var6.addVertexWithUV(var40, (double)(par3 + 0), var44, var20, var24);
            var6.addVertexWithUV(var40, (double)(par3 + 1), var44, var20, var22);
            var6.addVertexWithUV(var40, (double)(par3 + 1), var44, var16, var22);
            var6.addVertexWithUV(var40, (double)(par3 + 0), var44, var16, var24);
            var6.addVertexWithUV(var40, (double)(par3 + 0), var48, var20, var24);
            var6.addVertexWithUV(var40, (double)(par3 + 1), var48, var20, var22);

            if (var62)
            {
                var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var48, var30, var36);
                var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var44, var30, var32);
                var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var44, var28, var32);
                var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var48, var28, var36);
                var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var44, var30, var36);
                var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var48, var30, var32);
                var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var48, var28, var32);
                var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var44, var28, var36);
            }
            else
            {
                if (par3 < var5 - 1 && renderBlocks.blockAccess.isAirBlock(par2, par3 + 1, par4 - 1))
                {
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var44, var30, var32);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var44, var28, var32);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var46, var30, var32);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var44, var30, var34);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var44, var28, var34);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var46, var28, var32);
                }

                if (par3 < var5 - 1 && renderBlocks.blockAccess.isAirBlock(par2, par3 + 1, par4 + 1))
                {
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var48, var28, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var48, var30, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var48, var28, var34);
                    var6.addVertexWithUV(var50, (double)(par3 + 1) + 0.005D, var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)(par3 + 1) + 0.005D, var48, var30, var34);
                }
            }

            if (var63)
            {
                var6.addVertexWithUV(var52, (double)par3 - 0.005D, var48, var30, var36);
                var6.addVertexWithUV(var52, (double)par3 - 0.005D, var44, var30, var32);
                var6.addVertexWithUV(var50, (double)par3 - 0.005D, var44, var28, var32);
                var6.addVertexWithUV(var50, (double)par3 - 0.005D, var48, var28, var36);
                var6.addVertexWithUV(var52, (double)par3 - 0.005D, var44, var30, var36);
                var6.addVertexWithUV(var52, (double)par3 - 0.005D, var48, var30, var32);
                var6.addVertexWithUV(var50, (double)par3 - 0.005D, var48, var28, var32);
                var6.addVertexWithUV(var50, (double)par3 - 0.005D, var44, var28, var36);
            }
            else
            {
                if (par3 > 1 && renderBlocks.blockAccess.isAirBlock(par2, par3 - 1, par4 - 1))
                {
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var44, var30, var32);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var44, var28, var32);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var46, var30, var32);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var44, var30, var34);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var44, var28, var34);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var46, var28, var32);
                }

                if (par3 > 1 && renderBlocks.blockAccess.isAirBlock(par2, par3 - 1, par4 + 1))
                {
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var48, var28, var36);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var48, var30, var36);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var48, var28, var34);
                    var6.addVertexWithUV(var50, (double)par3 - 0.005D, var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)par3 - 0.005D, var48, var30, var34);
                }
            }
        }

        return true;
    }
}
