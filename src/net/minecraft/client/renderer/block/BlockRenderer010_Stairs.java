package net.minecraft.client.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockStair;
import net.minecraft.client.renderer.Tessellator;

public class BlockRenderer010_Stairs extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer010_Stairs();
	
	private BlockRenderer010_Stairs()
	{
		super(10);
	}
	
	static void nopInit(){}

    /**
     * Renders a stair block at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
		IBlockStair par1BlockStairs = (IBlockStair) par1Block;
        par1BlockStairs.func_82541_d(renderBlocks.blockAccess, par2, par3, par4);
        renderBlocks.setRenderBoundsFromBlock(par1Block);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
        boolean var5 = par1BlockStairs.func_82542_g(renderBlocks.blockAccess, par2, par3, par4);
        renderBlocks.setRenderBoundsFromBlock(par1Block);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);

        if (var5 && par1BlockStairs.func_82544_h(renderBlocks.blockAccess, par2, par3, par4))
        {
            renderBlocks.setRenderBoundsFromBlock(par1Block);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
        }

        return true;
    }
	
	public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
	{
		for (int var14 = 0; var14 < 2; ++var14)
        {
            if (var14 == 0)
            {
                renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
            }

            if (var14 == 1)
            {
                renderBlocks.setRenderBounds(0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
            }

            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
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
            renderBlocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 2));
            tess.draw();
            tess.startDrawingQuads();
            tess.setNormal(0.0F, 0.0F, 1.0F);
            renderBlocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 3));
            tess.draw();
            tess.startDrawingQuads();
            tess.setNormal(-1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 4));
            tess.draw();
            tess.startDrawingQuads();
            tess.setNormal(1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 5));
            tess.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
	}
}
