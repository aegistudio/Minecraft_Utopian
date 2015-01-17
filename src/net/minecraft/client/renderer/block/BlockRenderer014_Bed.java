package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Direction;
import net.minecraft.util.Icon;

public class BlockRenderer014_Bed extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer014_Bed();
	
	private BlockRenderer014_Bed()
	{
		super(14);
	}
	
	static void nopInit(){}

    /**
     * render a bed at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator tessellator = Tessellator.instance;
        int var6 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        int var7 = BlockBed.getDirection(var6);
        boolean var8 = BlockBed.isBlockHeadOfBed(var6);
        float var9 = 0.5F;
        float var10 = 1.0F;
        float var11 = 0.8F;
        float var12 = 0.6F;
        int var25 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4);
        tessellator.setBrightness(var25);
        tessellator.setColorOpaque_F(var9, var9, var9);
        Icon var27 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 0);
        double var28 = (double)var27.getMinU();
        double var30 = (double)var27.getMaxU();
        double var32 = (double)var27.getMinV();
        double var34 = (double)var27.getMaxV();
        double var36 = (double)par2 + renderBlocks.renderMinX;
        double var38 = (double)par2 + renderBlocks.renderMaxX;
        double var40 = (double)par3 + renderBlocks.renderMinY + 0.1875D;
        double var42 = (double)par4 + renderBlocks.renderMinZ;
        double var44 = (double)par4 + renderBlocks.renderMaxZ;
        tessellator.addVertexWithUV(var36, var40, var44, var28, var34);
        tessellator.addVertexWithUV(var36, var40, var42, var28, var32);
        tessellator.addVertexWithUV(var38, var40, var42, var30, var32);
        tessellator.addVertexWithUV(var38, var40, var44, var30, var34);
        tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4));
        tessellator.setColorOpaque_F(var10, var10, var10);
        var27 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 1);
        var28 = (double)var27.getMinU();
        var30 = (double)var27.getMaxU();
        var32 = (double)var27.getMinV();
        var34 = (double)var27.getMaxV();
        var36 = var28;
        var38 = var30;
        var40 = var32;
        var42 = var32;
        var44 = var28;
        double var46 = var30;
        double var48 = var34;
        double var50 = var34;

        if (var7 == 0)
        {
            var38 = var28;
            var40 = var34;
            var44 = var30;
            var50 = var32;
        }
        else if (var7 == 2)
        {
            var36 = var30;
            var42 = var34;
            var46 = var28;
            var48 = var32;
        }
        else if (var7 == 3)
        {
            var36 = var30;
            var42 = var34;
            var46 = var28;
            var48 = var32;
            var38 = var28;
            var40 = var34;
            var44 = var30;
            var50 = var32;
        }

        double var52 = (double)par2 + renderBlocks.renderMinX;
        double var54 = (double)par2 + renderBlocks.renderMaxX;
        double var56 = (double)par3 + renderBlocks.renderMaxY;
        double var58 = (double)par4 + renderBlocks.renderMinZ;
        double var60 = (double)par4 + renderBlocks.renderMaxZ;
        tessellator.addVertexWithUV(var54, var56, var60, var44, var48);
        tessellator.addVertexWithUV(var54, var56, var58, var36, var40);
        tessellator.addVertexWithUV(var52, var56, var58, var38, var42);
        tessellator.addVertexWithUV(var52, var56, var60, var46, var50);
        int var62 = Direction.directionToFacing[var7];

        if (var8)
        {
            var62 = Direction.directionToFacing[Direction.rotateOpposite[var7]];
        }

        byte var63 = 4;

        switch (var7)
        {
            case 0:
                var63 = 5;
                break;

            case 1:
                var63 = 3;

            case 2:
            default:
                break;

            case 3:
                var63 = 2;
        }

        if (var62 != 2 && (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 - 1, 2)))
        {
            tessellator.setBrightness(renderBlocks.renderMinZ > 0.0D ? var25 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1));
            tessellator.setColorOpaque_F(var11, var11, var11);
            renderBlocks.flipTexture = var63 == 2;
            renderBlocks.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 2));
        }

        if (var62 != 3 && (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 + 1, 3)))
        {
            tessellator.setBrightness(renderBlocks.renderMaxZ < 1.0D ? var25 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1));
            tessellator.setColorOpaque_F(var11, var11, var11);
            renderBlocks.flipTexture = var63 == 3;
            renderBlocks.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 3));
        }

        if (var62 != 4 && (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 - 1, par3, par4, 4)))
        {
            tessellator.setBrightness(renderBlocks.renderMinZ > 0.0D ? var25 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4));
            tessellator.setColorOpaque_F(var12, var12, var12);
            renderBlocks.flipTexture = var63 == 4;
            renderBlocks.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 4));
        }

        if (var62 != 5 && (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 + 1, par3, par4, 5)))
        {
            tessellator.setBrightness(renderBlocks.renderMaxZ < 1.0D ? var25 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4));
            tessellator.setColorOpaque_F(var12, var12, var12);
            renderBlocks.flipTexture = var63 == 5;
            renderBlocks.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 5));
        }

        renderBlocks.flipTexture = false;
        return true;
    }
}
