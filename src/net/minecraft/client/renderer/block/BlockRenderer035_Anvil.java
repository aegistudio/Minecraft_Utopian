package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockAnvil;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;

public class BlockRenderer035_Anvil extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer035_Anvil();
	
	private BlockRenderer035_Anvil()
	{
		super(35);
	}
	
	static void nopInit(){}
	
    /**
     * Renders anvil
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockAnvil, int par2, int par3, int par4)
    {
        return this.renderBlockAnvilMetadata(renderBlocks, par1BlockAnvil, par2, par3, par4, renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4));
    }

    /**
     * Renders anvil block with metadata
     */
    public boolean renderBlockAnvilMetadata(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4, int par5)
    {
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
        return this.renderBlockAnvilOrient(renderBlocks, par1Block, par2, par3, par4, par5, false);
    }

    /**
     * Renders anvil block with orientation
     */
    public boolean renderBlockAnvilOrient(RenderBlocks renderBlocks, Block par1BlockAnvil, int par2, int par3, int par4, int par5, boolean par6)
    {
        int var7 = par6 ? 0 : par5 & 3;
        boolean var8 = false;
        float var9 = 0.0F;

        switch (var7)
        {
            case 0:
                renderBlocks.uvRotateSouth = 2;
                renderBlocks.uvRotateNorth = 1;
                renderBlocks.uvRotateTop = 3;
                renderBlocks.uvRotateBottom = 3;
                break;

            case 1:
                renderBlocks.uvRotateEast = 1;
                renderBlocks.uvRotateWest = 2;
                renderBlocks.uvRotateTop = 2;
                renderBlocks.uvRotateBottom = 1;
                var8 = true;
                break;

            case 2:
                renderBlocks.uvRotateSouth = 1;
                renderBlocks.uvRotateNorth = 2;
                break;

            case 3:
                renderBlocks.uvRotateEast = 2;
                renderBlocks.uvRotateWest = 1;
                renderBlocks.uvRotateTop = 1;
                renderBlocks.uvRotateBottom = 2;
                var8 = true;
        }

        var9 = this.renderBlockAnvilRotate(renderBlocks, par1BlockAnvil, par2, par3, par4, 0, var9, 0.75F, 0.25F, 0.75F, var8, par6, par5);
        var9 = this.renderBlockAnvilRotate(renderBlocks, par1BlockAnvil, par2, par3, par4, 1, var9, 0.5F, 0.0625F, 0.625F, var8, par6, par5);
        var9 = this.renderBlockAnvilRotate(renderBlocks, par1BlockAnvil, par2, par3, par4, 2, var9, 0.25F, 0.3125F, 0.5F, var8, par6, par5);
        this.renderBlockAnvilRotate(renderBlocks, par1BlockAnvil, par2, par3, par4, 3, var9, 0.625F, 0.375F, 1.0F, var8, par6, par5);
        renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        renderBlocks.uvRotateEast = 0;
        renderBlocks.uvRotateWest = 0;
        renderBlocks.uvRotateSouth = 0;
        renderBlocks.uvRotateNorth = 0;
        renderBlocks.uvRotateTop = 0;
        renderBlocks.uvRotateBottom = 0;
        return true;
    }

    /**
     * Renders anvil block with rotation
     */
    public float renderBlockAnvilRotate(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4, int par5, float par6, float par7, float par8, float par9, boolean par10, boolean par11, int par12)
    {
    	IBlockAnvil par1BlockAnvil = (IBlockAnvil) par1Block;
    	
        if (par10)
        {
            float var13 = par7;
            par7 = par9;
            par9 = var13;
        }

        par7 /= 2.0F;
        par9 /= 2.0F;
        par1BlockAnvil.set_field_82521_b(par5);
        renderBlocks.setRenderBounds((double)(0.5F - par7), (double)par6, (double)(0.5F - par9), (double)(0.5F + par7), (double)(par6 + par8), (double)(0.5F + par9));

        if (par11)
        {
            Tessellator var14 = Tessellator.instance;
            var14.startDrawingQuads();
            var14.setNormal(0.0F, -1.0F, 0.0F);
            renderBlocks.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 0, par12));
            var14.draw();
            var14.startDrawingQuads();
            var14.setNormal(0.0F, 1.0F, 0.0F);
            renderBlocks.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 1, par12));
            var14.draw();
            var14.startDrawingQuads();
            var14.setNormal(0.0F, 0.0F, -1.0F);
            renderBlocks.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 2, par12));
            var14.draw();
            var14.startDrawingQuads();
            var14.setNormal(0.0F, 0.0F, 1.0F);
            renderBlocks.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 3, par12));
            var14.draw();
            var14.startDrawingQuads();
            var14.setNormal(-1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 4, par12));
            var14.draw();
            var14.startDrawingQuads();
            var14.setNormal(1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 5, par12));
            var14.draw();
        }
        else
        {
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
        }

        return par6 + par8;
    }
}
