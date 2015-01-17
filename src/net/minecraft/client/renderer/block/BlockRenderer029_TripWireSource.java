package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockInfoContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.util.Vec3;

public class BlockRenderer029_TripWireSource extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer029_TripWireSource();
	
	private BlockRenderer029_TripWireSource()
	{
		super(29);
	}
	
	static void nopInit(){}
	
    /**
     * Renders a trip wire source block at the given coordinates
     */
	
    public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        int var6 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        int var7 = var6 & 3;
        boolean var8 = (var6 & 4) == 4;
        boolean var9 = (var6 & 8) == 8;
        boolean var10 = !renderBlocks.blockAccess.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4);
        boolean var11 = renderBlocks.hasOverrideBlockTexture();

        if (!var11)
        {
            renderBlocks.setOverrideBlockTexture(renderBlocks.getBlockIcon(Block.planks));
        }

        float var12 = 0.25F;
        float var13 = 0.125F;
        float var14 = 0.125F;
        float var15 = 0.3F - var12;
        float var16 = 0.3F + var12;

        if (var7 == 2)
        {
            renderBlocks.setRenderBounds((double)(0.5F - var13), (double)var15, (double)(1.0F - var14), (double)(0.5F + var13), (double)var16, 1.0D);
        }
        else if (var7 == 0)
        {
            renderBlocks.setRenderBounds((double)(0.5F - var13), (double)var15, 0.0D, (double)(0.5F + var13), (double)var16, (double)var14);
        }
        else if (var7 == 1)
        {
            renderBlocks.setRenderBounds((double)(1.0F - var14), (double)var15, (double)(0.5F - var13), 1.0D, (double)var16, (double)(0.5F + var13));
        }
        else if (var7 == 3)
        {
            renderBlocks.setRenderBounds(0.0D, (double)var15, (double)(0.5F - var13), (double)var14, (double)var16, (double)(0.5F + var13));
        }

        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);

        if (!var11)
        {
            renderBlocks.clearOverrideBlockTexture();
        }

        var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var17 = 1.0F;

        BlockInfoContainer whocallme = BlockInfoContainer.getBlockInfoContainer();
        if (whocallme.getLookupLightValue(par1Block.blockID) > 0)
        {
            var17 = 1.0F;
        }

        var5.setColorOpaque_F(var17, var17, var17);
        Icon var18 = renderBlocks.getBlockIconFromSide(par1Block, 0);

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var18 = renderBlocks.overrideBlockTexture;
        }

        double var19 = (double)var18.getMinU();
        double var21 = (double)var18.getMinV();
        double var23 = (double)var18.getMaxU();
        double var25 = (double)var18.getMaxV();
        Vec3[] var27 = new Vec3[8];
        float var28 = 0.046875F;
        float var29 = 0.046875F;
        float var30 = 0.3125F;
        var27[0] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var28), 0.0D, (double)(-var29));
        var27[1] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var28, 0.0D, (double)(-var29));
        var27[2] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var28, 0.0D, (double)var29);
        var27[3] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var28), 0.0D, (double)var29);
        var27[4] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var28), (double)var30, (double)(-var29));
        var27[5] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var28, (double)var30, (double)(-var29));
        var27[6] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var28, (double)var30, (double)var29);
        var27[7] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var28), (double)var30, (double)var29);

        for (int var31 = 0; var31 < 8; ++var31)
        {
            var27[var31].zCoord += 0.0625D;

            if (var9)
            {
                var27[var31].rotateAroundX(0.5235988F);
                var27[var31].yCoord -= 0.4375D;
            }
            else if (var8)
            {
                var27[var31].rotateAroundX(0.08726647F);
                var27[var31].yCoord -= 0.4375D;
            }
            else
            {
                var27[var31].rotateAroundX(-((float)Math.PI * 2F / 9F));
                var27[var31].yCoord -= 0.375D;
            }

            var27[var31].rotateAroundX(((float)Math.PI / 2F));

            if (var7 == 2)
            {
                var27[var31].rotateAroundY(0.0F);
            }

            if (var7 == 0)
            {
                var27[var31].rotateAroundY((float)Math.PI);
            }

            if (var7 == 1)
            {
                var27[var31].rotateAroundY(((float)Math.PI / 2F));
            }

            if (var7 == 3)
            {
                var27[var31].rotateAroundY(-((float)Math.PI / 2F));
            }

            var27[var31].xCoord += (double)par2 + 0.5D;
            var27[var31].yCoord += (double)((float)par3 + 0.3125F);
            var27[var31].zCoord += (double)par4 + 0.5D;
        }

        Vec3 var62 = null;
        Vec3 var32 = null;
        Vec3 var33 = null;
        Vec3 var34 = null;
        byte var35 = 7;
        byte var36 = 9;
        byte var37 = 9;
        byte var38 = 16;

        for (int var39 = 0; var39 < 6; ++var39)
        {
            if (var39 == 0)
            {
                var62 = var27[0];
                var32 = var27[1];
                var33 = var27[2];
                var34 = var27[3];
                var19 = (double)var18.getInterpolatedU((double)var35);
                var21 = (double)var18.getInterpolatedV((double)var37);
                var23 = (double)var18.getInterpolatedU((double)var36);
                var25 = (double)var18.getInterpolatedV((double)(var37 + 2));
            }
            else if (var39 == 1)
            {
                var62 = var27[7];
                var32 = var27[6];
                var33 = var27[5];
                var34 = var27[4];
            }
            else if (var39 == 2)
            {
                var62 = var27[1];
                var32 = var27[0];
                var33 = var27[4];
                var34 = var27[5];
                var19 = (double)var18.getInterpolatedU((double)var35);
                var21 = (double)var18.getInterpolatedV((double)var37);
                var23 = (double)var18.getInterpolatedU((double)var36);
                var25 = (double)var18.getInterpolatedV((double)var38);
            }
            else if (var39 == 3)
            {
                var62 = var27[2];
                var32 = var27[1];
                var33 = var27[5];
                var34 = var27[6];
            }
            else if (var39 == 4)
            {
                var62 = var27[3];
                var32 = var27[2];
                var33 = var27[6];
                var34 = var27[7];
            }
            else if (var39 == 5)
            {
                var62 = var27[0];
                var32 = var27[3];
                var33 = var27[7];
                var34 = var27[4];
            }

            var5.addVertexWithUV(var62.xCoord, var62.yCoord, var62.zCoord, var19, var25);
            var5.addVertexWithUV(var32.xCoord, var32.yCoord, var32.zCoord, var23, var25);
            var5.addVertexWithUV(var33.xCoord, var33.yCoord, var33.zCoord, var23, var21);
            var5.addVertexWithUV(var34.xCoord, var34.yCoord, var34.zCoord, var19, var21);
        }

        float var63 = 0.09375F;
        float var40 = 0.09375F;
        float var41 = 0.03125F;
        var27[0] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var63), 0.0D, (double)(-var40));
        var27[1] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var63, 0.0D, (double)(-var40));
        var27[2] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var63, 0.0D, (double)var40);
        var27[3] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var63), 0.0D, (double)var40);
        var27[4] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var63), (double)var41, (double)(-var40));
        var27[5] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var63, (double)var41, (double)(-var40));
        var27[6] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var63, (double)var41, (double)var40);
        var27[7] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var63), (double)var41, (double)var40);

        for (int var42 = 0; var42 < 8; ++var42)
        {
            var27[var42].zCoord += 0.21875D;

            if (var9)
            {
                var27[var42].yCoord -= 0.09375D;
                var27[var42].zCoord -= 0.1625D;
                var27[var42].rotateAroundX(0.0F);
            }
            else if (var8)
            {
                var27[var42].yCoord += 0.015625D;
                var27[var42].zCoord -= 0.171875D;
                var27[var42].rotateAroundX(0.17453294F);
            }
            else
            {
                var27[var42].rotateAroundX(0.87266463F);
            }

            if (var7 == 2)
            {
                var27[var42].rotateAroundY(0.0F);
            }

            if (var7 == 0)
            {
                var27[var42].rotateAroundY((float)Math.PI);
            }

            if (var7 == 1)
            {
                var27[var42].rotateAroundY(((float)Math.PI / 2F));
            }

            if (var7 == 3)
            {
                var27[var42].rotateAroundY(-((float)Math.PI / 2F));
            }

            var27[var42].xCoord += (double)par2 + 0.5D;
            var27[var42].yCoord += (double)((float)par3 + 0.3125F);
            var27[var42].zCoord += (double)par4 + 0.5D;
        }

        byte var65 = 5;
        byte var43 = 11;
        byte var44 = 3;
        byte var45 = 9;

        for (int var46 = 0; var46 < 6; ++var46)
        {
            if (var46 == 0)
            {
                var62 = var27[0];
                var32 = var27[1];
                var33 = var27[2];
                var34 = var27[3];
                var19 = (double)var18.getInterpolatedU((double)var65);
                var21 = (double)var18.getInterpolatedV((double)var44);
                var23 = (double)var18.getInterpolatedU((double)var43);
                var25 = (double)var18.getInterpolatedV((double)var45);
            }
            else if (var46 == 1)
            {
                var62 = var27[7];
                var32 = var27[6];
                var33 = var27[5];
                var34 = var27[4];
            }
            else if (var46 == 2)
            {
                var62 = var27[1];
                var32 = var27[0];
                var33 = var27[4];
                var34 = var27[5];
                var19 = (double)var18.getInterpolatedU((double)var65);
                var21 = (double)var18.getInterpolatedV((double)var44);
                var23 = (double)var18.getInterpolatedU((double)var43);
                var25 = (double)var18.getInterpolatedV((double)(var44 + 2));
            }
            else if (var46 == 3)
            {
                var62 = var27[2];
                var32 = var27[1];
                var33 = var27[5];
                var34 = var27[6];
            }
            else if (var46 == 4)
            {
                var62 = var27[3];
                var32 = var27[2];
                var33 = var27[6];
                var34 = var27[7];
            }
            else if (var46 == 5)
            {
                var62 = var27[0];
                var32 = var27[3];
                var33 = var27[7];
                var34 = var27[4];
            }

            var5.addVertexWithUV(var62.xCoord, var62.yCoord, var62.zCoord, var19, var25);
            var5.addVertexWithUV(var32.xCoord, var32.yCoord, var32.zCoord, var23, var25);
            var5.addVertexWithUV(var33.xCoord, var33.yCoord, var33.zCoord, var23, var21);
            var5.addVertexWithUV(var34.xCoord, var34.yCoord, var34.zCoord, var19, var21);
        }

        if (var8)
        {
            double var64 = var27[0].yCoord;
            float var48 = 0.03125F;
            float var49 = 0.5F - var48 / 2.0F;
            float var50 = var49 + var48;
            /*Icon var51 = */renderBlocks.getBlockIcon(Block.tripWire);
            double var52 = (double)var18.getMinU();
            double var54 = (double)var18.getInterpolatedV(var8 ? 2.0D : 0.0D);
            double var56 = (double)var18.getMaxU();
            double var58 = (double)var18.getInterpolatedV(var8 ? 4.0D : 2.0D);
            double var60 = (double)(var10 ? 3.5F : 1.5F) / 16.0D;
            var17 = par1Block.getBlockBrightness(renderBlocks.blockAccess, par2, par3, par4) * 0.75F;
            var5.setColorOpaque_F(var17, var17, var17);

            if (var7 == 2)
            {
                var5.addVertexWithUV((double)((float)par2 + var49), (double)par3 + var60, (double)par4 + 0.25D, var52, var54);
                var5.addVertexWithUV((double)((float)par2 + var50), (double)par3 + var60, (double)par4 + 0.25D, var52, var58);
                var5.addVertexWithUV((double)((float)par2 + var50), (double)par3 + var60, (double)par4, var56, var58);
                var5.addVertexWithUV((double)((float)par2 + var49), (double)par3 + var60, (double)par4, var56, var54);
                var5.addVertexWithUV((double)((float)par2 + var49), var64, (double)par4 + 0.5D, var52, var54);
                var5.addVertexWithUV((double)((float)par2 + var50), var64, (double)par4 + 0.5D, var52, var58);
                var5.addVertexWithUV((double)((float)par2 + var50), (double)par3 + var60, (double)par4 + 0.25D, var56, var58);
                var5.addVertexWithUV((double)((float)par2 + var49), (double)par3 + var60, (double)par4 + 0.25D, var56, var54);
            }
            else if (var7 == 0)
            {
                var5.addVertexWithUV((double)((float)par2 + var49), (double)par3 + var60, (double)par4 + 0.75D, var52, var54);
                var5.addVertexWithUV((double)((float)par2 + var50), (double)par3 + var60, (double)par4 + 0.75D, var52, var58);
                var5.addVertexWithUV((double)((float)par2 + var50), var64, (double)par4 + 0.5D, var56, var58);
                var5.addVertexWithUV((double)((float)par2 + var49), var64, (double)par4 + 0.5D, var56, var54);
                var5.addVertexWithUV((double)((float)par2 + var49), (double)par3 + var60, (double)(par4 + 1), var52, var54);
                var5.addVertexWithUV((double)((float)par2 + var50), (double)par3 + var60, (double)(par4 + 1), var52, var58);
                var5.addVertexWithUV((double)((float)par2 + var50), (double)par3 + var60, (double)par4 + 0.75D, var56, var58);
                var5.addVertexWithUV((double)((float)par2 + var49), (double)par3 + var60, (double)par4 + 0.75D, var56, var54);
            }
            else if (var7 == 1)
            {
                var5.addVertexWithUV((double)par2, (double)par3 + var60, (double)((float)par4 + var50), var52, var58);
                var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var60, (double)((float)par4 + var50), var56, var58);
                var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var60, (double)((float)par4 + var49), var56, var54);
                var5.addVertexWithUV((double)par2, (double)par3 + var60, (double)((float)par4 + var49), var52, var54);
                var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var60, (double)((float)par4 + var50), var52, var58);
                var5.addVertexWithUV((double)par2 + 0.5D, var64, (double)((float)par4 + var50), var56, var58);
                var5.addVertexWithUV((double)par2 + 0.5D, var64, (double)((float)par4 + var49), var56, var54);
                var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var60, (double)((float)par4 + var49), var52, var54);
            }
            else
            {
                var5.addVertexWithUV((double)par2 + 0.5D, var64, (double)((float)par4 + var50), var52, var58);
                var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var60, (double)((float)par4 + var50), var56, var58);
                var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var60, (double)((float)par4 + var49), var56, var54);
                var5.addVertexWithUV((double)par2 + 0.5D, var64, (double)((float)par4 + var49), var52, var54);
                var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var60, (double)((float)par4 + var50), var52, var58);
                var5.addVertexWithUV((double)(par2 + 1), (double)par3 + var60, (double)((float)par4 + var50), var56, var58);
                var5.addVertexWithUV((double)(par2 + 1), (double)par3 + var60, (double)((float)par4 + var49), var56, var54);
                var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var60, (double)((float)par4 + var49), var52, var54);
            }
        }

        return true;
    }
	
}
