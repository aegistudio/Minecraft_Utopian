package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer017_PistonExtension extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer017_PistonExtension();
	
	private BlockRenderer017_PistonExtension()
	{
		super(17);
	}
	
	static void nopInit(){}

	@Override
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block block, int x,
			int y, int z)
	{
		return this.renderPistonExtension(renderBlocks, block, x, y, z, true);		
	}
	
    /**
     * Render piston rod up/down
     */
    private void renderPistonRodUD(RenderBlocks renderBlocks, double par1, double par3, double par5, double par7, double par9, double par11, float par13, double par14)
    {
        Icon var16 = BlockPistonBase.func_94496_b("piston_side");

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var16 = renderBlocks.overrideBlockTexture;
        }

        Tessellator var17 = Tessellator.instance;
        double var18 = (double)var16.getMinU();
        double var20 = (double)var16.getMinV();
        double var22 = (double)var16.getInterpolatedU(par14);
        double var24 = (double)var16.getInterpolatedV(4.0D);
        var17.setColorOpaque_F(par13, par13, par13);
        var17.addVertexWithUV(par1, par7, par9, var22, var20);
        var17.addVertexWithUV(par1, par5, par9, var18, var20);
        var17.addVertexWithUV(par3, par5, par11, var18, var24);
        var17.addVertexWithUV(par3, par7, par11, var22, var24);
    }

    /**
     * Render piston rod south/north
     */
    private void renderPistonRodSN(RenderBlocks renderBlocks, double par1, double par3, double par5, double par7, double par9, double par11, float par13, double par14)
    {
        Icon var16 = BlockPistonBase.func_94496_b("piston_side");

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var16 = renderBlocks.overrideBlockTexture;
        }

        Tessellator var17 = Tessellator.instance;
        double var18 = (double)var16.getMinU();
        double var20 = (double)var16.getMinV();
        double var22 = (double)var16.getInterpolatedU(par14);
        double var24 = (double)var16.getInterpolatedV(4.0D);
        var17.setColorOpaque_F(par13, par13, par13);
        var17.addVertexWithUV(par1, par5, par11, var22, var20);
        var17.addVertexWithUV(par1, par5, par9, var18, var20);
        var17.addVertexWithUV(par3, par7, par9, var18, var24);
        var17.addVertexWithUV(par3, par7, par11, var22, var24);
    }

    /**
     * Render piston rod east/west
     */
    private void renderPistonRodEW(RenderBlocks renderBlocks, double par1, double par3, double par5, double par7, double par9, double par11, float par13, double par14)
    {
        Icon var16 = BlockPistonBase.func_94496_b("piston_side");

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var16 = renderBlocks.overrideBlockTexture;
        }

        Tessellator var17 = Tessellator.instance;
        double var18 = (double)var16.getMinU();
        double var20 = (double)var16.getMinV();
        double var22 = (double)var16.getInterpolatedU(par14);
        double var24 = (double)var16.getInterpolatedV(4.0D);
        var17.setColorOpaque_F(par13, par13, par13);
        var17.addVertexWithUV(par3, par5, par9, var22, var20);
        var17.addVertexWithUV(par1, par5, par9, var18, var20);
        var17.addVertexWithUV(par1, par7, par11, var18, var24);
        var17.addVertexWithUV(par3, par7, par11, var22, var24);
    }

    /**
     * Render all faces of the piston extension
     */
    public void renderPistonExtensionAllFaces(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4, boolean par5)
    {
        renderBlocks.renderAllFaces = true;
        this.renderPistonExtension(renderBlocks, par1Block, par2, par3, par4, par5);
        renderBlocks.renderAllFaces = false;
    }

    /**
     * renders the pushing part of a piston
     */
    private boolean renderPistonExtension(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4, boolean par5)
    {
        int var6 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        int var7 = BlockPistonExtension.getDirectionMeta(var6);
        float var11 = par1Block.getBlockBrightness(renderBlocks.blockAccess, par2, par3, par4);
        float var12 = par5 ? 1.0F : 0.5F;
        double var13 = par5 ? 16.0D : 8.0D;

        switch (var7)
        {
            case 0:
                renderBlocks.uvRotateEast = 3;
                renderBlocks.uvRotateWest = 3;
                renderBlocks.uvRotateSouth = 3;
                renderBlocks.uvRotateNorth = 3;
                renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
                this.renderPistonRodUD(renderBlocks, (double)((float)par2 + 0.375F), (double)((float)par2 + 0.625F), (double)((float)par3 + 0.25F), (double)((float)par3 + 0.25F + var12), (double)((float)par4 + 0.625F), (double)((float)par4 + 0.625F), var11 * 0.8F, var13);
                this.renderPistonRodUD(renderBlocks, (double)((float)par2 + 0.625F), (double)((float)par2 + 0.375F), (double)((float)par3 + 0.25F), (double)((float)par3 + 0.25F + var12), (double)((float)par4 + 0.375F), (double)((float)par4 + 0.375F), var11 * 0.8F, var13);
                this.renderPistonRodUD(renderBlocks, (double)((float)par2 + 0.375F), (double)((float)par2 + 0.375F), (double)((float)par3 + 0.25F), (double)((float)par3 + 0.25F + var12), (double)((float)par4 + 0.375F), (double)((float)par4 + 0.625F), var11 * 0.6F, var13);
                this.renderPistonRodUD(renderBlocks, (double)((float)par2 + 0.625F), (double)((float)par2 + 0.625F), (double)((float)par3 + 0.25F), (double)((float)par3 + 0.25F + var12), (double)((float)par4 + 0.625F), (double)((float)par4 + 0.375F), var11 * 0.6F, var13);
                break;

            case 1:
                renderBlocks.setRenderBounds(0.0D, 0.75D, 0.0D, 1.0D, 1.0D, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
                this.renderPistonRodUD(renderBlocks, (double)((float)par2 + 0.375F), (double)((float)par2 + 0.625F), (double)((float)par3 - 0.25F + 1.0F - var12), (double)((float)par3 - 0.25F + 1.0F), (double)((float)par4 + 0.625F), (double)((float)par4 + 0.625F), var11 * 0.8F, var13);
                this.renderPistonRodUD(renderBlocks, (double)((float)par2 + 0.625F), (double)((float)par2 + 0.375F), (double)((float)par3 - 0.25F + 1.0F - var12), (double)((float)par3 - 0.25F + 1.0F), (double)((float)par4 + 0.375F), (double)((float)par4 + 0.375F), var11 * 0.8F, var13);
                this.renderPistonRodUD(renderBlocks, (double)((float)par2 + 0.375F), (double)((float)par2 + 0.375F), (double)((float)par3 - 0.25F + 1.0F - var12), (double)((float)par3 - 0.25F + 1.0F), (double)((float)par4 + 0.375F), (double)((float)par4 + 0.625F), var11 * 0.6F, var13);
                this.renderPistonRodUD(renderBlocks, (double)((float)par2 + 0.625F), (double)((float)par2 + 0.625F), (double)((float)par3 - 0.25F + 1.0F - var12), (double)((float)par3 - 0.25F + 1.0F), (double)((float)par4 + 0.625F), (double)((float)par4 + 0.375F), var11 * 0.6F, var13);
                break;

            case 2:
                renderBlocks.uvRotateSouth = 1;
                renderBlocks.uvRotateNorth = 2;
                renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
                this.renderPistonRodSN(renderBlocks, (double)((float)par2 + 0.375F), (double)((float)par2 + 0.375F), (double)((float)par3 + 0.625F), (double)((float)par3 + 0.375F), (double)((float)par4 + 0.25F), (double)((float)par4 + 0.25F + var12), var11 * 0.6F, var13);
                this.renderPistonRodSN(renderBlocks, (double)((float)par2 + 0.625F), (double)((float)par2 + 0.625F), (double)((float)par3 + 0.375F), (double)((float)par3 + 0.625F), (double)((float)par4 + 0.25F), (double)((float)par4 + 0.25F + var12), var11 * 0.6F, var13);
                this.renderPistonRodSN(renderBlocks, (double)((float)par2 + 0.375F), (double)((float)par2 + 0.625F), (double)((float)par3 + 0.375F), (double)((float)par3 + 0.375F), (double)((float)par4 + 0.25F), (double)((float)par4 + 0.25F + var12), var11 * 0.5F, var13);
                this.renderPistonRodSN(renderBlocks, (double)((float)par2 + 0.625F), (double)((float)par2 + 0.375F), (double)((float)par3 + 0.625F), (double)((float)par3 + 0.625F), (double)((float)par4 + 0.25F), (double)((float)par4 + 0.25F + var12), var11, var13);
                break;

            case 3:
                renderBlocks.uvRotateSouth = 2;
                renderBlocks.uvRotateNorth = 1;
                renderBlocks.uvRotateTop = 3;
                renderBlocks.uvRotateBottom = 3;
                renderBlocks.setRenderBounds(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
                this.renderPistonRodSN(renderBlocks, (double)((float)par2 + 0.375F), (double)((float)par2 + 0.375F), (double)((float)par3 + 0.625F), (double)((float)par3 + 0.375F), (double)((float)par4 - 0.25F + 1.0F - var12), (double)((float)par4 - 0.25F + 1.0F), var11 * 0.6F, var13);
                this.renderPistonRodSN(renderBlocks, (double)((float)par2 + 0.625F), (double)((float)par2 + 0.625F), (double)((float)par3 + 0.375F), (double)((float)par3 + 0.625F), (double)((float)par4 - 0.25F + 1.0F - var12), (double)((float)par4 - 0.25F + 1.0F), var11 * 0.6F, var13);
                this.renderPistonRodSN(renderBlocks, (double)((float)par2 + 0.375F), (double)((float)par2 + 0.625F), (double)((float)par3 + 0.375F), (double)((float)par3 + 0.375F), (double)((float)par4 - 0.25F + 1.0F - var12), (double)((float)par4 - 0.25F + 1.0F), var11 * 0.5F, var13);
                this.renderPistonRodSN(renderBlocks, (double)((float)par2 + 0.625F), (double)((float)par2 + 0.375F), (double)((float)par3 + 0.625F), (double)((float)par3 + 0.625F), (double)((float)par4 - 0.25F + 1.0F - var12), (double)((float)par4 - 0.25F + 1.0F), var11, var13);
                break;

            case 4:
                renderBlocks.uvRotateEast = 1;
                renderBlocks.uvRotateWest = 2;
                renderBlocks.uvRotateTop = 2;
                renderBlocks.uvRotateBottom = 1;
                renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
                this.renderPistonRodEW(renderBlocks, (double)((float)par2 + 0.25F), (double)((float)par2 + 0.25F + var12), (double)((float)par3 + 0.375F), (double)((float)par3 + 0.375F), (double)((float)par4 + 0.625F), (double)((float)par4 + 0.375F), var11 * 0.5F, var13);
                this.renderPistonRodEW(renderBlocks, (double)((float)par2 + 0.25F), (double)((float)par2 + 0.25F + var12), (double)((float)par3 + 0.625F), (double)((float)par3 + 0.625F), (double)((float)par4 + 0.375F), (double)((float)par4 + 0.625F), var11, var13);
                this.renderPistonRodEW(renderBlocks, (double)((float)par2 + 0.25F), (double)((float)par2 + 0.25F + var12), (double)((float)par3 + 0.375F), (double)((float)par3 + 0.625F), (double)((float)par4 + 0.375F), (double)((float)par4 + 0.375F), var11 * 0.6F, var13);
                this.renderPistonRodEW(renderBlocks, (double)((float)par2 + 0.25F), (double)((float)par2 + 0.25F + var12), (double)((float)par3 + 0.625F), (double)((float)par3 + 0.375F), (double)((float)par4 + 0.625F), (double)((float)par4 + 0.625F), var11 * 0.6F, var13);
                break;

            case 5:
                renderBlocks.uvRotateEast = 2;
                renderBlocks.uvRotateWest = 1;
                renderBlocks.uvRotateTop = 1;
                renderBlocks.uvRotateBottom = 2;
                renderBlocks.setRenderBounds(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
                this.renderPistonRodEW(renderBlocks, (double)((float)par2 - 0.25F + 1.0F - var12), (double)((float)par2 - 0.25F + 1.0F), (double)((float)par3 + 0.375F), (double)((float)par3 + 0.375F), (double)((float)par4 + 0.625F), (double)((float)par4 + 0.375F), var11 * 0.5F, var13);
                this.renderPistonRodEW(renderBlocks, (double)((float)par2 - 0.25F + 1.0F - var12), (double)((float)par2 - 0.25F + 1.0F), (double)((float)par3 + 0.625F), (double)((float)par3 + 0.625F), (double)((float)par4 + 0.375F), (double)((float)par4 + 0.625F), var11, var13);
                this.renderPistonRodEW(renderBlocks, (double)((float)par2 - 0.25F + 1.0F - var12), (double)((float)par2 - 0.25F + 1.0F), (double)((float)par3 + 0.375F), (double)((float)par3 + 0.625F), (double)((float)par4 + 0.375F), (double)((float)par4 + 0.375F), var11 * 0.6F, var13);
                this.renderPistonRodEW(renderBlocks, (double)((float)par2 - 0.25F + 1.0F - var12), (double)((float)par2 - 0.25F + 1.0F), (double)((float)par3 + 0.625F), (double)((float)par3 + 0.375F), (double)((float)par4 + 0.625F), (double)((float)par4 + 0.625F), var11 * 0.6F, var13);
        }

        renderBlocks.uvRotateEast = 0;
        renderBlocks.uvRotateWest = 0;
        renderBlocks.uvRotateSouth = 0;
        renderBlocks.uvRotateNorth = 0;
        renderBlocks.uvRotateTop = 0;
        renderBlocks.uvRotateBottom = 0;
        renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        return true;
    }
}
