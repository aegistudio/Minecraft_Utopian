package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockInfoContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.util.Vec3;

public class BlockRenderer012_Lever extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer012_Lever();
	
	private BlockRenderer012_Lever()
	{
		super(12);
	}
	
	static void nopInit(){}

    /**
     * Renders a lever block at the given coordinates
     */
	
	@Override
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        int var5 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        int var6 = var5 & 7;
        boolean var7 = (var5 & 8) > 0;
        Tessellator var8 = Tessellator.instance;
        boolean var9 = renderBlocks.hasOverrideBlockTexture();

        if (!var9)
        {
            renderBlocks.setOverrideBlockTexture(renderBlocks.getBlockIcon(Block.cobblestone));
        }

        float var10 = 0.25F;
        float var11 = 0.1875F;
        float var12 = 0.1875F;

        if (var6 == 5)
        {
            renderBlocks.setRenderBounds((double)(0.5F - var11), 0.0D, (double)(0.5F - var10), (double)(0.5F + var11), (double)var12, (double)(0.5F + var10));
        }
        else if (var6 == 6)
        {
            renderBlocks.setRenderBounds((double)(0.5F - var10), 0.0D, (double)(0.5F - var11), (double)(0.5F + var10), (double)var12, (double)(0.5F + var11));
        }
        else if (var6 == 4)
        {
            renderBlocks.setRenderBounds((double)(0.5F - var11), (double)(0.5F - var10), (double)(1.0F - var12), (double)(0.5F + var11), (double)(0.5F + var10), 1.0D);
        }
        else if (var6 == 3)
        {
            renderBlocks.setRenderBounds((double)(0.5F - var11), (double)(0.5F - var10), 0.0D, (double)(0.5F + var11), (double)(0.5F + var10), (double)var12);
        }
        else if (var6 == 2)
        {
            renderBlocks.setRenderBounds((double)(1.0F - var12), (double)(0.5F - var10), (double)(0.5F - var11), 1.0D, (double)(0.5F + var10), (double)(0.5F + var11));
        }
        else if (var6 == 1)
        {
            renderBlocks.setRenderBounds(0.0D, (double)(0.5F - var10), (double)(0.5F - var11), (double)var12, (double)(0.5F + var10), (double)(0.5F + var11));
        }
        else if (var6 == 0)
        {
            renderBlocks.setRenderBounds((double)(0.5F - var10), (double)(1.0F - var12), (double)(0.5F - var11), (double)(0.5F + var10), 1.0D, (double)(0.5F + var11));
        }
        else if (var6 == 7)
        {
            renderBlocks.setRenderBounds((double)(0.5F - var11), (double)(1.0F - var12), (double)(0.5F - var10), (double)(0.5F + var11), 1.0D, (double)(0.5F + var10));
        }

        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);

        if (!var9)
        {
            renderBlocks.clearOverrideBlockTexture();
        }

        var8.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var13 = 1.0F;

        BlockInfoContainer whocallme = BlockInfoContainer.getBlockInfoContainer();
        
        if (whocallme.getLookupLightValue(par1Block.blockID) > 0)
        {
            var13 = 1.0F;
        }

        var8.setColorOpaque_F(var13, var13, var13);
        Icon var14 = renderBlocks.getBlockIconFromSide(par1Block, 0);

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var14 = renderBlocks.overrideBlockTexture;
        }

        double var15 = (double)var14.getMinU();
        double var17 = (double)var14.getMinV();
        double var19 = (double)var14.getMaxU();
        double var21 = (double)var14.getMaxV();
        Vec3[] var23 = new Vec3[8];
        float var24 = 0.0625F;
        float var25 = 0.0625F;
        float var26 = 0.625F;
        var23[0] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var24), 0.0D, (double)(-var25));
        var23[1] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var24, 0.0D, (double)(-var25));
        var23[2] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var24, 0.0D, (double)var25);
        var23[3] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var24), 0.0D, (double)var25);
        var23[4] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var24), (double)var26, (double)(-var25));
        var23[5] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var24, (double)var26, (double)(-var25));
        var23[6] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)var24, (double)var26, (double)var25);
        var23[7] = renderBlocks.blockAccess.getWorldVec3Pool().getVecFromPool((double)(-var24), (double)var26, (double)var25);

        for (int var27 = 0; var27 < 8; ++var27)
        {
            if (var7)
            {
                var23[var27].zCoord -= 0.0625D;
                var23[var27].rotateAroundX(((float)Math.PI * 2F / 9F));
            }
            else
            {
                var23[var27].zCoord += 0.0625D;
                var23[var27].rotateAroundX(-((float)Math.PI * 2F / 9F));
            }

            if (var6 == 0 || var6 == 7)
            {
                var23[var27].rotateAroundZ((float)Math.PI);
            }

            if (var6 == 6 || var6 == 0)
            {
                var23[var27].rotateAroundY(((float)Math.PI / 2F));
            }

            if (var6 > 0 && var6 < 5)
            {
                var23[var27].yCoord -= 0.375D;
                var23[var27].rotateAroundX(((float)Math.PI / 2F));

                if (var6 == 4)
                {
                    var23[var27].rotateAroundY(0.0F);
                }

                if (var6 == 3)
                {
                    var23[var27].rotateAroundY((float)Math.PI);
                }

                if (var6 == 2)
                {
                    var23[var27].rotateAroundY(((float)Math.PI / 2F));
                }

                if (var6 == 1)
                {
                    var23[var27].rotateAroundY(-((float)Math.PI / 2F));
                }

                var23[var27].xCoord += (double)par2 + 0.5D;
                var23[var27].yCoord += (double)((float)par3 + 0.5F);
                var23[var27].zCoord += (double)par4 + 0.5D;
            }
            else if (var6 != 0 && var6 != 7)
            {
                var23[var27].xCoord += (double)par2 + 0.5D;
                var23[var27].yCoord += (double)((float)par3 + 0.125F);
                var23[var27].zCoord += (double)par4 + 0.5D;
            }
            else
            {
                var23[var27].xCoord += (double)par2 + 0.5D;
                var23[var27].yCoord += (double)((float)par3 + 0.875F);
                var23[var27].zCoord += (double)par4 + 0.5D;
            }
        }

        Vec3 var32 = null;
        Vec3 var28 = null;
        Vec3 var29 = null;
        Vec3 var30 = null;

        for (int var31 = 0; var31 < 6; ++var31)
        {
            if (var31 == 0)
            {
                var15 = (double)var14.getInterpolatedU(7.0D);
                var17 = (double)var14.getInterpolatedV(6.0D);
                var19 = (double)var14.getInterpolatedU(9.0D);
                var21 = (double)var14.getInterpolatedV(8.0D);
            }
            else if (var31 == 2)
            {
                var15 = (double)var14.getInterpolatedU(7.0D);
                var17 = (double)var14.getInterpolatedV(6.0D);
                var19 = (double)var14.getInterpolatedU(9.0D);
                var21 = (double)var14.getMaxV();
            }

            if (var31 == 0)
            {
                var32 = var23[0];
                var28 = var23[1];
                var29 = var23[2];
                var30 = var23[3];
            }
            else if (var31 == 1)
            {
                var32 = var23[7];
                var28 = var23[6];
                var29 = var23[5];
                var30 = var23[4];
            }
            else if (var31 == 2)
            {
                var32 = var23[1];
                var28 = var23[0];
                var29 = var23[4];
                var30 = var23[5];
            }
            else if (var31 == 3)
            {
                var32 = var23[2];
                var28 = var23[1];
                var29 = var23[5];
                var30 = var23[6];
            }
            else if (var31 == 4)
            {
                var32 = var23[3];
                var28 = var23[2];
                var29 = var23[6];
                var30 = var23[7];
            }
            else if (var31 == 5)
            {
                var32 = var23[0];
                var28 = var23[3];
                var29 = var23[7];
                var30 = var23[4];
            }

            var8.addVertexWithUV(var32.xCoord, var32.yCoord, var32.zCoord, var15, var21);
            var8.addVertexWithUV(var28.xCoord, var28.yCoord, var28.zCoord, var19, var21);
            var8.addVertexWithUV(var29.xCoord, var29.yCoord, var29.zCoord, var19, var17);
            var8.addVertexWithUV(var30.xCoord, var30.yCoord, var30.zCoord, var15, var17);
        }

        return true;
    }
}
