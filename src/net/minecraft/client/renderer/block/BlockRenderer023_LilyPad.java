package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer023_LilyPad extends BlockRenderer
{
	
	public static final BlockRenderer renderer = new BlockRenderer023_LilyPad();
	
	private BlockRenderer023_LilyPad()
	{
		super(23);
	}

	static void nopInit(){}

	@Override
	/**
     * Render BlockLilyPad
     */
    public boolean onRenderBlock(RenderBlocks renderBlocks, Block block, int x, int y, int z)
    {
        Tessellator tess = Tessellator.instance;
        Icon blockIcon = renderBlocks.getBlockIconFromSide(block, 1);

        if (renderBlocks.hasOverrideBlockTexture()) blockIcon = renderBlocks.overrideBlockTexture;

        float var7 = 0.015625F;
        double minu = (double)blockIcon.getMinU();
        double minv = (double)blockIcon.getMinV();
        double maxu = (double)blockIcon.getMaxU();
        double maxv = (double)blockIcon.getMaxV();
        
        long var16 = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)y;
        var16 = var16 * var16 * 42317861L + var16 * 11L;
        int var18 = (int)(var16 >> 16 & 3L);
        tess.setBrightness(block.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z));
        float var19 = (float)x + 0.5F;
        float var20 = (float)z + 0.5F;
        float var21 = (float)(var18 & 1) * 0.5F * (float)(1 - var18 / 2 % 2 * 2);
        float var22 = (float)(var18 + 1 & 1) * 0.5F * (float)(1 - (var18 + 1) / 2 % 2 * 2);
        
        tess.setColorOpaque_I(block.getBlockColor());
        tess.addVertexWithUV((double)(var19 + var21 - var22), (double)((float)y + var7), (double)(var20 + var21 + var22), minu, minv);
        tess.addVertexWithUV((double)(var19 + var21 + var22), (double)((float)y + var7), (double)(var20 - var21 + var22), maxu, minv);
        tess.addVertexWithUV((double)(var19 - var21 + var22), (double)((float)y + var7), (double)(var20 - var21 - var22), maxu, maxv);
        tess.addVertexWithUV((double)(var19 - var21 - var22), (double)((float)y + var7), (double)(var20 + var21 - var22), minu, maxv);
        tess.setColorOpaque_I((block.getBlockColor() & 16711422) >> 1);
        tess.addVertexWithUV((double)(var19 - var21 - var22), (double)((float)y + var7), (double)(var20 + var21 - var22), minu, maxv);
        tess.addVertexWithUV((double)(var19 - var21 + var22), (double)((float)y + var7), (double)(var20 - var21 - var22), maxu, maxv);
        tess.addVertexWithUV((double)(var19 + var21 + var22), (double)((float)y + var7), (double)(var20 - var21 + var22), maxu, minv);
        tess.addVertexWithUV((double)(var19 + var21 - var22), (double)((float)y + var7), (double)(var20 + var21 + var22), minu, minv);
        return true;
    }
	
	public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
	{
		tess.startDrawingQuads();
        tess.setNormal(0.0F, -1.0F, 0.0F);
        block.setBlockBoundsForItemRender();
        tess.draw();
	}
}