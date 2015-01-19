package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer002_Torch extends BlockRenderer
{
	
	public static final BlockRenderer renderer = new BlockRenderer002_Torch();
	
	private BlockRenderer002_Torch()
	{
		super(2);
	}
	
	static void nopInit(){}
	
    /**
     * Renders a torch block at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        int var5 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        Tessellator var6 = Tessellator.instance;
        var6.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        double var7 = 0.4000000059604645D;
        double var9 = 0.5D - var7;
        double var11 = 0.20000000298023224D;

        if (var5 == 1) this.renderTorchAtAngle(renderBlocks, par1Block, (double)par2 - var9, (double)par3 + var11, (double)par4, -var7, 0.0D, 0);
        else if (var5 == 2) this.renderTorchAtAngle(renderBlocks, par1Block, (double)par2 + var9, (double)par3 + var11, (double)par4, var7, 0.0D, 0);
        else if (var5 == 3) this.renderTorchAtAngle(renderBlocks, par1Block, (double)par2, (double)par3 + var11, (double)par4 - var9, 0.0D, -var7, 0);
        else if (var5 == 4) this.renderTorchAtAngle(renderBlocks, par1Block, (double)par2, (double)par3 + var11, (double)par4 + var9, 0.0D, var7, 0);
        else this.renderTorchAtAngle(renderBlocks, par1Block, (double)par2, (double)par3, (double)par4, 0.0D, 0.0D, 0);

        return true;
    }
	

    /**
     * Renders a torch at the given coordinates, with the base slanting at the given delta
     */
    public void renderTorchAtAngle(RenderBlocks renderBlocks, Block par1Block, double par2, double par4, double par6, double par8, double par10, int par12)
    {
        Tessellator var13 = Tessellator.instance;
        Icon var14 = renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 0, par12);

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var14 = renderBlocks.overrideBlockTexture;
        }

        double var15 = (double)var14.getMinU();
        double var17 = (double)var14.getMinV();
        double var19 = (double)var14.getMaxU();
        double var21 = (double)var14.getMaxV();
        double var23 = (double)var14.getInterpolatedU(7.0D);
        double var25 = (double)var14.getInterpolatedV(6.0D);
        double var27 = (double)var14.getInterpolatedU(9.0D);
        double var29 = (double)var14.getInterpolatedV(8.0D);
        double var31 = (double)var14.getInterpolatedU(7.0D);
        double var33 = (double)var14.getInterpolatedV(13.0D);
        double var35 = (double)var14.getInterpolatedU(9.0D);
        double var37 = (double)var14.getInterpolatedV(15.0D);
        par2 += 0.5D;
        par6 += 0.5D;
        double var39 = par2 - 0.5D;
        double var41 = par2 + 0.5D;
        double var43 = par6 - 0.5D;
        double var45 = par6 + 0.5D;
        double var47 = 0.0625D;
        double var49 = 0.625D;
        var13.addVertexWithUV(par2 + par8 * (1.0D - var49) - var47, par4 + var49, par6 + par10 * (1.0D - var49) - var47, var23, var25);
        var13.addVertexWithUV(par2 + par8 * (1.0D - var49) - var47, par4 + var49, par6 + par10 * (1.0D - var49) + var47, var23, var29);
        var13.addVertexWithUV(par2 + par8 * (1.0D - var49) + var47, par4 + var49, par6 + par10 * (1.0D - var49) + var47, var27, var29);
        var13.addVertexWithUV(par2 + par8 * (1.0D - var49) + var47, par4 + var49, par6 + par10 * (1.0D - var49) - var47, var27, var25);
        var13.addVertexWithUV(par2 + var47 + par8, par4, par6 - var47 + par10, var35, var33);
        var13.addVertexWithUV(par2 + var47 + par8, par4, par6 + var47 + par10, var35, var37);
        var13.addVertexWithUV(par2 - var47 + par8, par4, par6 + var47 + par10, var31, var37);
        var13.addVertexWithUV(par2 - var47 + par8, par4, par6 - var47 + par10, var31, var33);
        var13.addVertexWithUV(par2 - var47, par4 + 1.0D, var43, var15, var17);
        var13.addVertexWithUV(par2 - var47 + par8, par4 + 0.0D, var43 + par10, var15, var21);
        var13.addVertexWithUV(par2 - var47 + par8, par4 + 0.0D, var45 + par10, var19, var21);
        var13.addVertexWithUV(par2 - var47, par4 + 1.0D, var45, var19, var17);
        var13.addVertexWithUV(par2 + var47, par4 + 1.0D, var45, var15, var17);
        var13.addVertexWithUV(par2 + par8 + var47, par4 + 0.0D, var45 + par10, var15, var21);
        var13.addVertexWithUV(par2 + par8 + var47, par4 + 0.0D, var43 + par10, var19, var21);
        var13.addVertexWithUV(par2 + var47, par4 + 1.0D, var43, var19, var17);
        var13.addVertexWithUV(var39, par4 + 1.0D, par6 + var47, var15, var17);
        var13.addVertexWithUV(var39 + par8, par4 + 0.0D, par6 + var47 + par10, var15, var21);
        var13.addVertexWithUV(var41 + par8, par4 + 0.0D, par6 + var47 + par10, var19, var21);
        var13.addVertexWithUV(var41, par4 + 1.0D, par6 + var47, var19, var17);
        var13.addVertexWithUV(var41, par4 + 1.0D, par6 - var47, var15, var17);
        var13.addVertexWithUV(var41 + par8, par4 + 0.0D, par6 - var47 + par10, var15, var21);
        var13.addVertexWithUV(var39 + par8, par4 + 0.0D, par6 - var47 + par10, var19, var21);
        var13.addVertexWithUV(var39, par4 + 1.0D, par6 - var47, var19, var17);
        
    }

    public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
    {
	    tess.startDrawingQuads();
	    tess.setNormal(0.0F, -1.0F, 0.0F);
	    this.renderTorchAtAngle(renderBlocks, block, -0.5D, -0.5D, -0.5D, 0.0D, 0.0D, 0);
	    tess.draw();
    }
}
