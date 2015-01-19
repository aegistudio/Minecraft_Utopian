package net.minecraft.client.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;

public class BlockRenderer027_DragonEgg extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer027_DragonEgg();
	
	private BlockRenderer027_DragonEgg()
	{
		super(27);
	}
	
	private static final byte[] leftnumMap = new byte[]{2, 3, 4, 5, 6, 7, 6, 3};
	private static final byte[] rightnumMap = new byte[]{1, 1, 1, 2, 3, 5, 2, 1};
	
	static void nopInit(){}
	
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockDragonEgg, int par2, int par3, int par4)
    {
        boolean var5 = false;
        int var6 = 0;

        for (int component = 0; component < 8; ++component)
        {
            byte leftnum = leftnumMap[component];
            byte rightnum = rightnumMap[component];
            
            float var10 = (float)leftnum / 16.0F;
            float var11 = 1.0F - (float)var6 / 16.0F;
            float var12 = 1.0F - (float)(var6 + rightnum) / 16.0F;
            var6 += rightnum;
            renderBlocks.setRenderBounds((double)(0.5F - var10), (double)var12, (double)(0.5F - var10), (double)(0.5F + var10), (double)var11, (double)(0.5F + var10));
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockDragonEgg, par2, par3, par4);
        }

        var5 = true;
        renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        return var5;
    }
	
	public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
	{
        int var14 = 0;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tess.startDrawingQuads();

        for (int component = 0; component < 8; ++component)
        {
            byte leftnum = leftnumMap[component];
            byte rightnum = rightnumMap[component];
            
            float var11 = (float)leftnum / 16.0F;
            float var12 = 1.0F - (float)var14 / 16.0F;
            float var13 = 1.0F - (float)(var14 + rightnum) / 16.0F;
            var14 += rightnum;
            renderBlocks.setRenderBounds((double)(0.5F - var11), (double)var13, (double)(0.5F - var11), (double)(0.5F + var11), (double)var12, (double)(0.5F + var11));
            tess.setNormal(0.0F, -1.0F, 0.0F);
            renderBlocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 0));
            tess.setNormal(0.0F, 1.0F, 0.0F);
            renderBlocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 1));
            tess.setNormal(0.0F, 0.0F, -1.0F);
            renderBlocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 2));
            tess.setNormal(0.0F, 0.0F, 1.0F);
            renderBlocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 3));
            tess.setNormal(-1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 4));
            tess.setNormal(1.0F, 0.0F, 0.0F);
            renderBlocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSide(block, 5));
        }

        tess.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	}
}
