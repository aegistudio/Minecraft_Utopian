package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;

public class BlockRenderer001_CrossedSquares extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer001_CrossedSquares();
	
	private BlockRenderer001_CrossedSquares()
	{
		super(1);
	}
	
	static void nopInit(){}
	
    /**
     * Renders any block requiring croseed squares such as reeds, flowers, and mushrooms
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var6 = 1.0F;
        int var7 = par1Block.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
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
        double var19 = (double)par2;
        double var20 = (double)par3;
        double var15 = (double)par4;

        if (par1Block == Block.tallGrass)
        {
            long var17 = (long)(par2 * 3129871) ^ (long)par4 * 116129781L ^ (long)par3;
            var17 = var17 * var17 * 42317861L + var17 * 11L;
            var19 += ((double)((float)(var17 >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
            var20 += ((double)((float)(var17 >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
            var15 += ((double)((float)(var17 >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
        }

        renderBlocks.drawCrossedSquares(par1Block, renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, 1.0F);
        return true;
    }
}
