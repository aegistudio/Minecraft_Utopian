package net.minecraft.client.renderer.block;

import java.util.Map;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.BiMap;

public abstract class BlockRenderer
{
	public static final Map<Integer, BlockRenderer> blockRenderers = new BiMap<BlockRenderer>();
	
	public static final BlockRenderer empty = new BlockRenderer(-1)
	{
		public boolean onRenderBlock(RenderBlocks renderBlocks, Block block, int x, int y, int z)
		{
			return false;
		}
		
		public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
		{
			
		}
	};
	
	protected BlockRenderer(int rendererId)
	{
		this(rendererId, true);
	}
	
	protected BlockRenderer(int rendererId, boolean checkExistence)
	{
		if(checkExistence) if(blockRenderers.get(rendererId) != null) throw new IllegalArgumentException("Already registered renderer #" + rendererId);
		Minecraft.getMinecraft().getLogAgent().logInfo("Registered block renderer @" + this.getClass().getName());
		blockRenderers.put(rendererId, this);
	}
	
	public static BlockRenderer getBlockRenderer(int rendererId)
	{
		BlockRenderer returnValue;
		if((returnValue = blockRenderers.get(rendererId)) == null) return empty;
		return returnValue;
	}
	
	public abstract boolean onRenderBlock(RenderBlocks renderBlocks, Block block, int x, int y, int z);
	
	public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
	{
		boolean var5 = block.blockID == Block.grass.blockID;

        block.setBlockBoundsForItemRender();
        renderBlocks.setRenderBoundsFromBlock(block);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, -1.0F, 0.0F);
        renderBlocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(block, 0, par2));
        tess.draw();

        if (var5 && renderBlocks.useInventoryTint)
        {
            int var14 = block.getRenderColor(par2);
            float var8 = (float)(var14 >> 16 & 255) / 255.0F;
            float var9 = (float)(var14 >> 8 & 255) / 255.0F;
            float var10 = (float)(var14 & 255) / 255.0F;
            GL11.glColor4f(var8 * par3, var9 * par3, var10 * par3, 1.0F);
        }

        tess.startDrawingQuads();
        tess.setNormal(0.0F, 1.0F, 0.0F);
        renderBlocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(block, 1, par2));
        tess.draw();

        if (var5 && renderBlocks.useInventoryTint)
        {
            GL11.glColor4f(par3, par3, par3, 1.0F);
        }

        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, -1.0F);
        renderBlocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(block, 2, par2));
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, 1.0F);
        renderBlocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(block, 3, par2));
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(-1.0F, 0.0F, 0.0F);
        renderBlocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(block, 4, par2));
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(1.0F, 0.0F, 0.0F);
        renderBlocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(block, 5, par2));
        tess.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
}
