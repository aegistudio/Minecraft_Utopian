package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockBrewingStand;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer025_BrewingStand extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer025_BrewingStand();
	
	private BlockRenderer025_BrewingStand()
	{
		super(25);
	}

	static void nopInit(){}
	
    /**
     * Render BlockBrewingStand
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockBrewingStand, int par2, int par3, int par4)
    {
        renderBlocks.setRenderBounds(0.4375D, 0.0D, 0.4375D, 0.5625D, 0.875D, 0.5625D);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockBrewingStand, par2, par3, par4);
        renderBlocks.setOverrideBlockTexture(((IBlockBrewingStand)par1BlockBrewingStand).getBrewingStandIcon());
        renderBlocks.setRenderBounds(0.5625D, 0.0D, 0.3125D, 0.9375D, 0.125D, 0.6875D);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockBrewingStand, par2, par3, par4);
        renderBlocks.setRenderBounds(0.125D, 0.0D, 0.0625D, 0.5D, 0.125D, 0.4375D);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockBrewingStand, par2, par3, par4);
        renderBlocks.setRenderBounds(0.125D, 0.0D, 0.5625D, 0.5D, 0.125D, 0.9375D);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockBrewingStand, par2, par3, par4);
        renderBlocks.clearOverrideBlockTexture();
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(par1BlockBrewingStand.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var6 = 1.0F;
        int var7 = par1BlockBrewingStand.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
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
        Icon var32 = renderBlocks.getBlockIconFromSideAndMetadata(par1BlockBrewingStand, 0, 0);

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var32 = renderBlocks.overrideBlockTexture;
        }

        double var33 = (double)var32.getMinV();
        double var14 = (double)var32.getMaxV();
        int var16 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);

        for (int var17 = 0; var17 < 3; ++var17)
        {
            double var18 = (double)var17 * Math.PI * 2.0D / 3.0D + (Math.PI / 2D);
            double var20 = (double)var32.getInterpolatedU(8.0D);
            double var22 = (double)var32.getMaxU();

            if ((var16 & 1 << var17) != 0)
            {
                var22 = (double)var32.getMinU();
            }

            double var24 = (double)par2 + 0.5D;
            double var26 = (double)par2 + 0.5D + Math.sin(var18) * 8.0D / 16.0D;
            double var28 = (double)par4 + 0.5D;
            double var30 = (double)par4 + 0.5D + Math.cos(var18) * 8.0D / 16.0D;
            var5.addVertexWithUV(var24, (double)(par3 + 1), var28, var20, var33);
            var5.addVertexWithUV(var24, (double)(par3 + 0), var28, var20, var14);
            var5.addVertexWithUV(var26, (double)(par3 + 0), var30, var22, var14);
            var5.addVertexWithUV(var26, (double)(par3 + 1), var30, var22, var33);
            var5.addVertexWithUV(var26, (double)(par3 + 1), var30, var22, var33);
            var5.addVertexWithUV(var26, (double)(par3 + 0), var30, var22, var14);
            var5.addVertexWithUV(var24, (double)(par3 + 0), var28, var20, var14);
            var5.addVertexWithUV(var24, (double)(par3 + 1), var28, var20, var33);
        }

        par1BlockBrewingStand.setBlockBoundsForItemRender();
        return true;
    }
}
