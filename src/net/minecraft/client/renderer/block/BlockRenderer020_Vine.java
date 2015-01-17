package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer020_Vine extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer020_Vine();

	private BlockRenderer020_Vine()
	{
		super(20);
	}

	static void nopInit(){}
	
    /**
     * Render block vine
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        Icon var6 = renderBlocks.getBlockIconFromSide(par1Block, 0);

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var6 = renderBlocks.overrideBlockTexture;
        }

        float var7 = 1.0F;
        var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        int var8 = par1Block.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
        float var9 = (float)(var8 >> 16 & 255) / 255.0F;
        float var10 = (float)(var8 >> 8 & 255) / 255.0F;
        float var11 = (float)(var8 & 255) / 255.0F;
        var5.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
        double var19 = (double)var6.getMinU();
        double var20 = (double)var6.getMinV();
        double var12 = (double)var6.getMaxU();
        double var14 = (double)var6.getMaxV();
        double var16 = 0.05000000074505806D;
        int var18 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);

        if ((var18 & 2) != 0)
        {
            var5.addVertexWithUV((double)par2 + var16, (double)(par3 + 1), (double)(par4 + 1), var19, var20);
            var5.addVertexWithUV((double)par2 + var16, (double)(par3 + 0), (double)(par4 + 1), var19, var14);
            var5.addVertexWithUV((double)par2 + var16, (double)(par3 + 0), (double)(par4 + 0), var12, var14);
            var5.addVertexWithUV((double)par2 + var16, (double)(par3 + 1), (double)(par4 + 0), var12, var20);
            var5.addVertexWithUV((double)par2 + var16, (double)(par3 + 1), (double)(par4 + 0), var12, var20);
            var5.addVertexWithUV((double)par2 + var16, (double)(par3 + 0), (double)(par4 + 0), var12, var14);
            var5.addVertexWithUV((double)par2 + var16, (double)(par3 + 0), (double)(par4 + 1), var19, var14);
            var5.addVertexWithUV((double)par2 + var16, (double)(par3 + 1), (double)(par4 + 1), var19, var20);
        }

        if ((var18 & 8) != 0)
        {
            var5.addVertexWithUV((double)(par2 + 1) - var16, (double)(par3 + 0), (double)(par4 + 1), var12, var14);
            var5.addVertexWithUV((double)(par2 + 1) - var16, (double)(par3 + 1), (double)(par4 + 1), var12, var20);
            var5.addVertexWithUV((double)(par2 + 1) - var16, (double)(par3 + 1), (double)(par4 + 0), var19, var20);
            var5.addVertexWithUV((double)(par2 + 1) - var16, (double)(par3 + 0), (double)(par4 + 0), var19, var14);
            var5.addVertexWithUV((double)(par2 + 1) - var16, (double)(par3 + 0), (double)(par4 + 0), var19, var14);
            var5.addVertexWithUV((double)(par2 + 1) - var16, (double)(par3 + 1), (double)(par4 + 0), var19, var20);
            var5.addVertexWithUV((double)(par2 + 1) - var16, (double)(par3 + 1), (double)(par4 + 1), var12, var20);
            var5.addVertexWithUV((double)(par2 + 1) - var16, (double)(par3 + 0), (double)(par4 + 1), var12, var14);
        }

        if ((var18 & 4) != 0)
        {
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), (double)par4 + var16, var12, var14);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 1), (double)par4 + var16, var12, var20);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 1), (double)par4 + var16, var19, var20);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), (double)par4 + var16, var19, var14);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), (double)par4 + var16, var19, var14);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 1), (double)par4 + var16, var19, var20);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 1), (double)par4 + var16, var12, var20);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), (double)par4 + var16, var12, var14);
        }

        if ((var18 & 1) != 0)
        {
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 1), (double)(par4 + 1) - var16, var19, var20);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), (double)(par4 + 1) - var16, var19, var14);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), (double)(par4 + 1) - var16, var12, var14);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 1), (double)(par4 + 1) - var16, var12, var20);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 1), (double)(par4 + 1) - var16, var12, var20);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), (double)(par4 + 1) - var16, var12, var14);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), (double)(par4 + 1) - var16, var19, var14);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 1), (double)(par4 + 1) - var16, var19, var20);
        }

        if (renderBlocks.blockAccess.isBlockNormalCube(par2, par3 + 1, par4))
        {
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 1) - var16, (double)(par4 + 0), var19, var20);
            var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 1) - var16, (double)(par4 + 1), var19, var14);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 1) - var16, (double)(par4 + 1), var12, var14);
            var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 1) - var16, (double)(par4 + 0), var12, var20);
        }

        return true;
    }
}
