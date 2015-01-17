package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer008_Ladder extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer008_Ladder();
	
	private BlockRenderer008_Ladder()
	{
		super(8);
	}
	
	static void nopInit(){}

    /**
     * Renders a ladder block at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int x, int y, int z)
    {
        Tessellator tess = Tessellator.instance;
        Icon icon = renderBlocks.getBlockIconFromSide(par1Block, 0);

        if (renderBlocks.hasOverrideBlockTexture()) icon = renderBlocks.overrideBlockTexture;

        tess.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z));
        float var7 = 1.0F;
        tess.setColorOpaque_F(var7, var7, var7);
        double var20 = (double)icon.getMinU();
        double var9 = (double)icon.getMinV();
        double var11 = (double)icon.getMaxU();
        double var13 = (double)icon.getMaxV();
        int var15 = renderBlocks.blockAccess.getBlockMetadata(x, y, z);
        double var16 = 0.0D;
        double delta = 0.05000000074505806D;

        if (var15 == 5)
        {
            tess.addVertexWithUV((double)x + delta, (double)(y + 1) + var16, (double)(z + 1) + var16, var20, var9);
            tess.addVertexWithUV((double)x + delta, (double)(y + 0) - var16, (double)(z + 1) + var16, var20, var13);
            tess.addVertexWithUV((double)x + delta, (double)(y + 0) - var16, (double)(z + 0) - var16, var11, var13);
            tess.addVertexWithUV((double)x + delta, (double)(y + 1) + var16, (double)(z + 0) - var16, var11, var9);
        }

        if (var15 == 4)
        {
            tess.addVertexWithUV((double)(x + 1) - delta, (double)(y + 0) - var16, (double)(z + 1) + var16, var11, var13);
            tess.addVertexWithUV((double)(x + 1) - delta, (double)(y + 1) + var16, (double)(z + 1) + var16, var11, var9);
            tess.addVertexWithUV((double)(x + 1) - delta, (double)(y + 1) + var16, (double)(z + 0) - var16, var20, var9);
            tess.addVertexWithUV((double)(x + 1) - delta, (double)(y + 0) - var16, (double)(z + 0) - var16, var20, var13);
        }

        if (var15 == 3)
        {
            tess.addVertexWithUV((double)(x + 1) + var16, (double)(y + 0) - var16, (double)z + delta, var11, var13);
            tess.addVertexWithUV((double)(x + 1) + var16, (double)(y + 1) + var16, (double)z + delta, var11, var9);
            tess.addVertexWithUV((double)(x + 0) - var16, (double)(y + 1) + var16, (double)z + delta, var20, var9);
            tess.addVertexWithUV((double)(x + 0) - var16, (double)(y + 0) - var16, (double)z + delta, var20, var13);
        }

        if (var15 == 2)
        {
            tess.addVertexWithUV((double)(x + 1) + var16, (double)(y + 1) + var16, (double)(z + 1) - delta, var20, var9);
            tess.addVertexWithUV((double)(x + 1) + var16, (double)(y + 0) - var16, (double)(z + 1) - delta, var20, var13);
            tess.addVertexWithUV((double)(x + 0) - var16, (double)(y + 0) - var16, (double)(z + 1) - delta, var11, var13);
            tess.addVertexWithUV((double)(x + 0) - var16, (double)(y + 1) + var16, (double)(z + 1) - delta, var11, var9);
        }

        return true;
    }
}