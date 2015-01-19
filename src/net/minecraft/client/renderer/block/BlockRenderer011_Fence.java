package net.minecraft.client.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockFence;
import net.minecraft.client.renderer.Tessellator;

public class BlockRenderer011_Fence extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer011_Fence();
	
	private BlockRenderer011_Fence()
	{
		super(11);
	}
	
	static void nopInit(){}

	@Override
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int x, int y, int z)
    {
		IBlockFence par1BlockFence = (IBlockFence)par1Block;
		
        boolean var5 = false;
        float var6 = 0.375F;
        float var7 = 0.625F;
        renderBlocks.setRenderBounds((double)var6, 0.0D, (double)var6, (double)var7, 1.0D, (double)var7);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, x, y, z);
        var5 = true;
        
        

        boolean canConnectFenceToXNeg = par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, x - 1, y, z);
        boolean canConnectFenceToXPos = par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, x + 1, y, z);
        boolean canConnectFenceToZNeg = par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, x, y, z - 1);
        boolean canConnectFenceToZPos = par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, x, y, z + 1);
        
        boolean var8 = canConnectFenceToXNeg || canConnectFenceToXPos;
        boolean var9 = canConnectFenceToZNeg || canConnectFenceToZPos;

        if (!var8 && !var9) var8 = true;

        var6 = 0.4375F;
        var7 = 0.5625F;
        
        float var16 = canConnectFenceToXNeg ? 0.0F : var6;
        float var17 = canConnectFenceToXPos ? 1.0F : var7;
        float var18 = canConnectFenceToZNeg ? 0.0F : var6;
        float var19 = canConnectFenceToZPos ? 1.0F : var7;

        float var14 = 0.75F;
        float var15 = 0.9375F;
        
        if (var8)
        {
            renderBlocks.setRenderBounds((double)var16, (double)var14, (double)var6, (double)var17, (double)var15, (double)var7);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, x, y, z);
            var5 = true;
        }

        if (var9)
        {
            renderBlocks.setRenderBounds((double)var6, (double)var14, (double)var18, (double)var7, (double)var15, (double)var19);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, x, y, z);
            var5 = true;
        }

        var14 = 0.375F;
        var15 = 0.5625F;

        if (var8)
        {
            renderBlocks.setRenderBounds((double)var16, (double)var14, (double)var6, (double)var17, (double)var15, (double)var7);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, x, y, z);
            var5 = true;
        }

        if (var9)
        {
            renderBlocks.setRenderBounds((double)var6, (double)var14, (double)var18, (double)var7, (double)var15, (double)var19);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, x, y, z);
            var5 = true;
        }

        par1Block.setBlockBoundsBasedOnState(renderBlocks.blockAccess, x, y, z);
        return var5;
    }
	
	public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
	{
		for (int var14 = 0; var14 < 4; ++var14)
        {
            float var8 = 0.125F;

            if (var14 == 0)
            {
                renderBlocks.setRenderBounds((double)(0.5F - var8), 0.0D, 0.0D, (double)(0.5F + var8), 1.0D, (double)(var8 * 2.0F));
            }

            if (var14 == 1)
            {
                renderBlocks.setRenderBounds((double)(0.5F - var8), 0.0D, (double)(1.0F - var8 * 2.0F), (double)(0.5F + var8), 1.0D, 1.0D);
            }

            var8 = 0.0625F;

            if (var14 == 2)
            {
                renderBlocks.setRenderBounds((double)(0.5F - var8), (double)(1.0F - var8 * 3.0F), (double)(-var8 * 2.0F), (double)(0.5F + var8), (double)(1.0F - var8), (double)(1.0F + var8 * 2.0F));
            }

            if (var14 == 3)
            {
                renderBlocks.setRenderBounds((double)(0.5F - var8), (double)(0.5F - var8 * 3.0F), (double)(-var8 * 2.0F), (double)(0.5F + var8), (double)(0.5F - var8), (double)(1.0F + var8 * 2.0F));
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

        renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	}
}
