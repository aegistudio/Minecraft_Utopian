package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer006_Crops extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer006_Crops();
	
	private BlockRenderer006_Crops()
	{
		super(6);
	}
	
	static void nopInit(){}

	@Override
	/**
     * Render block crops
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        this.renderBlockCropsImpl(renderBlocks, par1Block, renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4), (double)par2, (double)((float)par3 - 0.0625F), (double)par4);
        return true;
    }
	
    /**
     * Render block crops implementation
     */
    public void renderBlockCropsImpl(RenderBlocks renderBlocks, Block par1Block, int par2, double par3, double par5, double par7)
    {
        Tessellator var9 = Tessellator.instance;
        Icon var10 = renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 0, par2);

        if (renderBlocks.hasOverrideBlockTexture()) var10 = renderBlocks.overrideBlockTexture;

        double var11 = (double)var10.getMinU();
        double var13 = (double)var10.getMinV();
        double var15 = (double)var10.getMaxU();
        double var17 = (double)var10.getMaxV();
        double var19 = par3 + 0.5D - 0.25D;
        double var21 = par3 + 0.5D + 0.25D;
        double var23 = par7 + 0.5D - 0.5D;
        double var25 = par7 + 0.5D + 0.5D;
        var9.addVertexWithUV(var19, par5 + 1.0D, var23, var11, var13);
        var9.addVertexWithUV(var19, par5 + 0.0D, var23, var11, var17);
        var9.addVertexWithUV(var19, par5 + 0.0D, var25, var15, var17);
        var9.addVertexWithUV(var19, par5 + 1.0D, var25, var15, var13);
        var9.addVertexWithUV(var19, par5 + 1.0D, var25, var11, var13);
        var9.addVertexWithUV(var19, par5 + 0.0D, var25, var11, var17);
        var9.addVertexWithUV(var19, par5 + 0.0D, var23, var15, var17);
        var9.addVertexWithUV(var19, par5 + 1.0D, var23, var15, var13);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var11, var13);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var11, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var23, var15, var17);
        var9.addVertexWithUV(var21, par5 + 1.0D, var23, var15, var13);
        var9.addVertexWithUV(var21, par5 + 1.0D, var23, var11, var13);
        var9.addVertexWithUV(var21, par5 + 0.0D, var23, var11, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var17);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var15, var13);
        var19 = par3 + 0.5D - 0.5D;
        var21 = par3 + 0.5D + 0.5D;
        var23 = par7 + 0.5D - 0.25D;
        var25 = par7 + 0.5D + 0.25D;
        var9.addVertexWithUV(var19, par5 + 1.0D, var23, var11, var13);
        var9.addVertexWithUV(var19, par5 + 0.0D, var23, var11, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var23, var15, var17);
        var9.addVertexWithUV(var21, par5 + 1.0D, var23, var15, var13);
        var9.addVertexWithUV(var21, par5 + 1.0D, var23, var11, var13);
        var9.addVertexWithUV(var21, par5 + 0.0D, var23, var11, var17);
        var9.addVertexWithUV(var19, par5 + 0.0D, var23, var15, var17);
        var9.addVertexWithUV(var19, par5 + 1.0D, var23, var15, var13);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var11, var13);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var11, var17);
        var9.addVertexWithUV(var19, par5 + 0.0D, var25, var15, var17);
        var9.addVertexWithUV(var19, par5 + 1.0D, var25, var15, var13);
        var9.addVertexWithUV(var19, par5 + 1.0D, var25, var11, var13);
        var9.addVertexWithUV(var19, par5 + 0.0D, var25, var11, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var17);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var15, var13);
    }
    
    public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
    {
        tess.startDrawingQuads();
        tess.setNormal(0.0F, -1.0F, 0.0F);
        this.renderBlockCropsImpl(renderBlocks, block, par2, -0.5D, -0.5D, -0.5D);
        tess.draw();
    }
}