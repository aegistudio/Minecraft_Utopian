package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer038_Hopper extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer038_Hopper();
	
	private BlockRenderer038_Hopper()
	{
		super(38);
	}
	
	static void nopInit(){}

	@Override
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockHopper, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(par1BlockHopper.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var6 = 1.0F;
        int var7 = par1BlockHopper.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var11;
            var9 = var12;
            var10 = var13;
        }

        var5.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
        return this.renderBlockHopperMetadata(renderBlocks, par1BlockHopper, par2, par3, par4, renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4), false);
    }

    public boolean renderBlockHopperMetadata(RenderBlocks renderBlocks, Block par1BlockHopper, int par2, int par3, int par4, int par5, boolean par6)
    {
        Tessellator var7 = Tessellator.instance;
        int var8 = BlockHopper.getDirectionFromMetadata(par5);
        double var9 = 0.625D;
        renderBlocks.setRenderBounds(0.0D, var9, 0.0D, 1.0D, 1.0D, 1.0D);

        if (par6)
        {
            var7.startDrawingQuads();
            var7.setNormal(0.0F, -1.0F, 0.0F);
            renderBlocks.renderFaceYNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1BlockHopper, 0, par5));
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(0.0F, 1.0F, 0.0F);
            renderBlocks.renderFaceYPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1BlockHopper, 1, par5));
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(0.0F, 0.0F, -1.0F);
            renderBlocks.renderFaceZNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1BlockHopper, 2, par5));
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(0.0F, 0.0F, 1.0F);
            renderBlocks.renderFaceZPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1BlockHopper, 3, par5));
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(-1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1BlockHopper, 4, par5));
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1BlockHopper, 5, par5));
            var7.draw();
        }
        else
        {
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockHopper, par2, par3, par4);
        }

        float var13;

        if (!par6)
        {
            var7.setBrightness(par1BlockHopper.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
            float var11 = 1.0F;
            int var12 = par1BlockHopper.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
            var13 = (float)(var12 >> 16 & 255) / 255.0F;
            float var14 = (float)(var12 >> 8 & 255) / 255.0F;
            float var15 = (float)(var12 & 255) / 255.0F;

            if (EntityRenderer.anaglyphEnable)
            {
                float var16 = (var13 * 30.0F + var14 * 59.0F + var15 * 11.0F) / 100.0F;
                float var17 = (var13 * 30.0F + var14 * 70.0F) / 100.0F;
                float var18 = (var13 * 30.0F + var15 * 70.0F) / 100.0F;
                var13 = var16;
                var14 = var17;
                var15 = var18;
            }

            var7.setColorOpaque_F(var11 * var13, var11 * var14, var11 * var15);
        }

        Icon var24 = BlockHopper.getHopperIcon("hopper");
        Icon var25 = BlockHopper.getHopperIcon("hopper_inside");
        var13 = 0.125F;

        if (par6)
        {
            var7.startDrawingQuads();
            var7.setNormal(1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXPos(par1BlockHopper, (double)(-1.0F + var13), 0.0D, 0.0D, var24);
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(-1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXNeg(par1BlockHopper, (double)(1.0F - var13), 0.0D, 0.0D, var24);
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(0.0F, 0.0F, 1.0F);
            renderBlocks.renderFaceZPos(par1BlockHopper, 0.0D, 0.0D, (double)(-1.0F + var13), var24);
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(0.0F, 0.0F, -1.0F);
            renderBlocks.renderFaceZNeg(par1BlockHopper, 0.0D, 0.0D, (double)(1.0F - var13), var24);
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(0.0F, 1.0F, 0.0F);
            renderBlocks.renderFaceYPos(par1BlockHopper, 0.0D, -1.0D + var9, 0.0D, var25);
            var7.draw();
        }
        else
        {
            renderBlocks.renderFaceXPos(par1BlockHopper, (double)((float)par2 - 1.0F + var13), (double)par3, (double)par4, var24);
            renderBlocks.renderFaceXNeg(par1BlockHopper, (double)((float)par2 + 1.0F - var13), (double)par3, (double)par4, var24);
            renderBlocks.renderFaceZPos(par1BlockHopper, (double)par2, (double)par3, (double)((float)par4 - 1.0F + var13), var24);
            renderBlocks.renderFaceZNeg(par1BlockHopper, (double)par2, (double)par3, (double)((float)par4 + 1.0F - var13), var24);
            renderBlocks.renderFaceYPos(par1BlockHopper, (double)par2, (double)((float)par3 - 1.0F) + var9, (double)par4, var25);
        }

        renderBlocks.setOverrideBlockTexture(var24);
        double var26 = 0.25D;
        double var27 = 0.25D;
        renderBlocks.setRenderBounds(var26, var27, var26, 1.0D - var26, var9 - 0.002D, 1.0D - var26);

        if (par6)
        {
            var7.startDrawingQuads();
            var7.setNormal(1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(-1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(0.0F, 0.0F, 1.0F);
            renderBlocks.renderFaceZPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(0.0F, 0.0F, -1.0F);
            renderBlocks.renderFaceZNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(0.0F, 1.0F, 0.0F);
            renderBlocks.renderFaceYPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
            var7.draw();
            var7.startDrawingQuads();
            var7.setNormal(0.0F, -1.0F, 0.0F);
            renderBlocks.renderFaceYNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
            var7.draw();
        }
        else
        {
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockHopper, par2, par3, par4);
        }

        if (!par6)
        {
            double var20 = 0.375D;
            double var22 = 0.25D;
            renderBlocks.setOverrideBlockTexture(var24);

            if (var8 == 0)
            {
                renderBlocks.setRenderBounds(var20, 0.0D, var20, 1.0D - var20, 0.25D, 1.0D - var20);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockHopper, par2, par3, par4);
            }

            if (var8 == 2)
            {
                renderBlocks.setRenderBounds(var20, var27, 0.0D, 1.0D - var20, var27 + var22, var26);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockHopper, par2, par3, par4);
            }

            if (var8 == 3)
            {
                renderBlocks.setRenderBounds(var20, var27, 1.0D - var26, 1.0D - var20, var27 + var22, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockHopper, par2, par3, par4);
            }

            if (var8 == 4)
            {
                renderBlocks.setRenderBounds(0.0D, var27, var20, var26, var27 + var22, 1.0D - var20);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockHopper, par2, par3, par4);
            }

            if (var8 == 5)
            {
                renderBlocks.setRenderBounds(1.0D - var26, var27, var20, 1.0D, var27 + var22, 1.0D - var20);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockHopper, par2, par3, par4);
            }
        }

        renderBlocks.clearOverrideBlockTexture();
        return true;
    }
}
