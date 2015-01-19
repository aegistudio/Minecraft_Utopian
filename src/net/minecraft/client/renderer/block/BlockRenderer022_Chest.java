package net.minecraft.client.renderer.block;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ChestItemRenderHelper;
import net.minecraft.client.renderer.Tessellator;

public class BlockRenderer022_Chest extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer022_Chest();
	
	private BlockRenderer022_Chest()
	{
		super(22);
	}
	
	static void nopInit(){}

	@Override
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block block, int x,
			int y, int z)
	{
		/* Chest is a tile entity and should be renderered by tile entity renderer*/
		return false;
	}
	
	public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
	{
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        ChestItemRenderHelper.instance.renderChest(block, par2, par3);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}
}
