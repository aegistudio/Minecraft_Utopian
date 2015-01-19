package net.minecraft.client.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;

public class BlockRenderer013_Cactus extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer013_Cactus();
	
	private BlockRenderer013_Cactus()
	{
		super(13);
	}
	
	static void nopInit(){}
	
    /**
     * Renders a cactus block at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        int var5 = par1Block.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
        float var6 = (float)(var5 >> 16 & 255) / 255.0F;
        float var7 = (float)(var5 >> 8 & 255) / 255.0F;
        float var8 = (float)(var5 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var9 = (var6 * 30.0F + var7 * 59.0F + var8 * 11.0F) / 100.0F;
            float var10 = (var6 * 30.0F + var7 * 70.0F) / 100.0F;
            float var11 = (var6 * 30.0F + var8 * 70.0F) / 100.0F;
            var6 = var9;
            var7 = var10;
            var8 = var11;
        }

        return this.renderBlockCactusImpl(renderBlocks, par1Block, par2, par3, par4, var6, var7, var8);
    }

    /**
     * Render block cactus implementation
     */
    public boolean renderBlockCactusImpl(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7)
    {
        Tessellator var8 = Tessellator.instance;
        boolean var9 = false;
        float var10 = 0.5F;
        float var11 = 1.0F;
        float var12 = 0.8F;
        float var13 = 0.6F;
        float var14 = var10 * par5;
        float var15 = var11 * par5;
        float var16 = var12 * par5;
        float var17 = var13 * par5;
        float var18 = var10 * par6;
        float var19 = var11 * par6;
        float var20 = var12 * par6;
        float var21 = var13 * par6;
        float var22 = var10 * par7;
        float var23 = var11 * par7;
        float var24 = var12 * par7;
        float var25 = var13 * par7;
        float var26 = 0.0625F;
        int var28 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4);

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 - 1, par4, 0))
        {
            var8.setBrightness(renderBlocks.renderMinY > 0.0D ? var28 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4));
            var8.setColorOpaque_F(var14, var18, var22);
            renderBlocks.renderFaceYNeg(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 0));
            var9 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 + 1, par4, 1))
        {
            var8.setBrightness(renderBlocks.renderMaxY < 1.0D ? var28 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4));
            var8.setColorOpaque_F(var15, var19, var23);
            renderBlocks.renderFaceYPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 1));
            var9 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 - 1, 2))
        {
            var8.setBrightness(renderBlocks.renderMinZ > 0.0D ? var28 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1));
            var8.setColorOpaque_F(var16, var20, var24);
            var8.addTranslation(0.0F, 0.0F, var26);
            renderBlocks.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 2));
            var8.addTranslation(0.0F, 0.0F, -var26);
            var9 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 + 1, 3))
        {
            var8.setBrightness(renderBlocks.renderMaxZ < 1.0D ? var28 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1));
            var8.setColorOpaque_F(var16, var20, var24);
            var8.addTranslation(0.0F, 0.0F, -var26);
            renderBlocks.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 3));
            var8.addTranslation(0.0F, 0.0F, var26);
            var9 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 - 1, par3, par4, 4))
        {
            var8.setBrightness(renderBlocks.renderMinX > 0.0D ? var28 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4));
            var8.setColorOpaque_F(var17, var21, var25);
            var8.addTranslation(var26, 0.0F, 0.0F);
            renderBlocks.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 4));
            var8.addTranslation(-var26, 0.0F, 0.0F);
            var9 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 + 1, par3, par4, 5))
        {
            var8.setBrightness(renderBlocks.renderMaxX < 1.0D ? var28 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4));
            var8.setColorOpaque_F(var17, var21, var25);
            var8.addTranslation(-var26, 0.0F, 0.0F);
            renderBlocks.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 5));
            var8.addTranslation(var26, 0.0F, 0.0F);
            var9 = true;
        }

        return var9;
    }
    
    public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
    {
        block.setBlockBoundsForItemRender();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float var7 = 0.0625F;
        tess.startDrawingQuads();
        tess.setNormal(0.0F, -1.0F, 0.0F);
        renderBlocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 0));
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 1.0F, 0.0F);
        renderBlocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 1));
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, -1.0F);
        tess.addTranslation(0.0F, 0.0F, var7);
        renderBlocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 2));
        tess.addTranslation(0.0F, 0.0F, -var7);
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, 1.0F);
        tess.addTranslation(0.0F, 0.0F, -var7);
        renderBlocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 3));
        tess.addTranslation(0.0F, 0.0F, var7);
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(-1.0F, 0.0F, 0.0F);
        tess.addTranslation(var7, 0.0F, 0.0F);
        renderBlocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 4));
        tess.addTranslation(-var7, 0.0F, 0.0F);
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(1.0F, 0.0F, 0.0F);
        tess.addTranslation(-var7, 0.0F, 0.0F);
        renderBlocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 5));
        tess.addTranslation(var7, 0.0F, 0.0F);
        tess.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
}
