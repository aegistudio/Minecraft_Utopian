package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockStem;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer019_Stem extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer019_Stem();

	private BlockRenderer019_Stem()
	{
		super(19);
	}

	static void nopInit(){}
	
    /**
     * Render block stem
     */
    public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        IBlockStem var5 = (IBlockStem)par1Block;
        Tessellator var6 = Tessellator.instance;
        var6.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var7 = 1.0F;
        int var8 = par1Block.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
        float var9 = (float)(var8 >> 16 & 255) / 255.0F;
        float var10 = (float)(var8 >> 8 & 255) / 255.0F;
        float var11 = (float)(var8 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var12 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
            float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
            float var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
            var9 = var12;
            var10 = var13;
            var11 = var14;
        }

        var6.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
        par1Block.setBlockBoundsBasedOnState(renderBlocks.blockAccess, par2, par3, par4);
        int var15 = var5.getState(renderBlocks.blockAccess, par2, par3, par4);

        if (var15 < 0)
        {
            this.renderBlockStemSmall(renderBlocks, par1Block, renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4), renderBlocks.renderMaxY, (double)par2, (double)((float)par3 - 0.0625F), (double)par4);
        }
        else
        {
            this.renderBlockStemSmall(renderBlocks, par1Block, renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4), 0.5D, (double)par2, (double)((float)par3 - 0.0625F), (double)par4);
            this.renderBlockStemBig(renderBlocks, var5, renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4), var15, renderBlocks.renderMaxY, (double)par2, (double)((float)par3 - 0.0625F), (double)par4);
        }

        return true;
    }
    
    /**
     * Render block stem big
     */
    public void renderBlockStemBig(RenderBlocks renderBlocks, IBlockStem par1BlockStem, int par2, int par3, double par4, double par6, double par8, double par10)
    {
        Tessellator var12 = Tessellator.instance;
        Icon var13 = par1BlockStem.func_94368_p();

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var13 = renderBlocks.overrideBlockTexture;
        }

        double var14 = (double)var13.getMinU();
        double var16 = (double)var13.getMinV();
        double var18 = (double)var13.getMaxU();
        double var20 = (double)var13.getMaxV();
        double var22 = par6 + 0.5D - 0.5D;
        double var24 = par6 + 0.5D + 0.5D;
        double var26 = par10 + 0.5D - 0.5D;
        double var28 = par10 + 0.5D + 0.5D;
        double var30 = par6 + 0.5D;
        double var32 = par10 + 0.5D;

        if ((par3 + 1) / 2 % 2 == 1)
        {
            double var34 = var18;
            var18 = var14;
            var14 = var34;
        }

        if (par3 < 2)
        {
            var12.addVertexWithUV(var22, par8 + par4, var32, var14, var16);
            var12.addVertexWithUV(var22, par8 + 0.0D, var32, var14, var20);
            var12.addVertexWithUV(var24, par8 + 0.0D, var32, var18, var20);
            var12.addVertexWithUV(var24, par8 + par4, var32, var18, var16);
            var12.addVertexWithUV(var24, par8 + par4, var32, var18, var16);
            var12.addVertexWithUV(var24, par8 + 0.0D, var32, var18, var20);
            var12.addVertexWithUV(var22, par8 + 0.0D, var32, var14, var20);
            var12.addVertexWithUV(var22, par8 + par4, var32, var14, var16);
        }
        else
        {
            var12.addVertexWithUV(var30, par8 + par4, var28, var14, var16);
            var12.addVertexWithUV(var30, par8 + 0.0D, var28, var14, var20);
            var12.addVertexWithUV(var30, par8 + 0.0D, var26, var18, var20);
            var12.addVertexWithUV(var30, par8 + par4, var26, var18, var16);
            var12.addVertexWithUV(var30, par8 + par4, var26, var18, var16);
            var12.addVertexWithUV(var30, par8 + 0.0D, var26, var18, var20);
            var12.addVertexWithUV(var30, par8 + 0.0D, var28, var14, var20);
            var12.addVertexWithUV(var30, par8 + par4, var28, var14, var16);
        }
    }
    
    /**
     * Render block stem small
     */
    public void renderBlockStemSmall(RenderBlocks renderBlocks, Block par1Block, int par2, double par3, double par5, double par7, double par9)
    {
        Tessellator var11 = Tessellator.instance;
        Icon var12 = renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 0, par2);

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var12 = renderBlocks.overrideBlockTexture;
        }

        double var13 = (double)var12.getMinU();
        double var15 = (double)var12.getMinV();
        double var17 = (double)var12.getMaxU();
        double var19 = (double)var12.getInterpolatedV(par3 * 16.0D);
        double var21 = par5 + 0.5D - 0.44999998807907104D;
        double var23 = par5 + 0.5D + 0.44999998807907104D;
        double var25 = par9 + 0.5D - 0.44999998807907104D;
        double var27 = par9 + 0.5D + 0.44999998807907104D;
        var11.addVertexWithUV(var21, par7 + par3, var25, var13, var15);
        var11.addVertexWithUV(var21, par7 + 0.0D, var25, var13, var19);
        var11.addVertexWithUV(var23, par7 + 0.0D, var27, var17, var19);
        var11.addVertexWithUV(var23, par7 + par3, var27, var17, var15);
        var11.addVertexWithUV(var23, par7 + par3, var27, var13, var15);
        var11.addVertexWithUV(var23, par7 + 0.0D, var27, var13, var19);
        var11.addVertexWithUV(var21, par7 + 0.0D, var25, var17, var19);
        var11.addVertexWithUV(var21, par7 + par3, var25, var17, var15);
        var11.addVertexWithUV(var21, par7 + par3, var27, var13, var15);
        var11.addVertexWithUV(var21, par7 + 0.0D, var27, var13, var19);
        var11.addVertexWithUV(var23, par7 + 0.0D, var25, var17, var19);
        var11.addVertexWithUV(var23, par7 + par3, var25, var17, var15);
        var11.addVertexWithUV(var23, par7 + par3, var25, var13, var15);
        var11.addVertexWithUV(var23, par7 + 0.0D, var25, var13, var19);
        var11.addVertexWithUV(var21, par7 + 0.0D, var27, var17, var19);
        var11.addVertexWithUV(var21, par7 + par3, var27, var17, var15);
    }
    
    /**
     * Is called to render the image of a block on an inventory, as a held item, or as a an item on the ground
     */
    public void renderBlockAsItem(RenderBlocks renderBlocks, Block par1Block, int par2, float par3)
    {
    	Tessellator tess = Tessellator.instance;
    	tess.startDrawingQuads();
        tess.setNormal(0.0F, -1.0F, 0.0F);
        par1Block.setBlockBoundsForItemRender();
        this.renderBlockStemSmall(renderBlocks, par1Block, par2, renderBlocks.renderMaxY, -0.5D, -0.5D, -0.5D);
        tess.draw();
    }
}
