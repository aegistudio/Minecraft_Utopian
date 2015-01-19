package net.minecraft.client.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockBeacon;
import net.minecraft.client.renderer.Tessellator;

public class BlockRenderer034_Beacon extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer034_Beacon();
	
	private BlockRenderer034_Beacon()
	{
		super(34);
	}
	
	static void nopInit(){}
	
    /**
     * Renders beacon block
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
		IBlockBeacon par1BlockBeacon = (IBlockBeacon) par1Block;
        float var5 = 0.1875F;
        //XXX Rendering beacon base block.
        renderBlocks.setOverrideBlockTexture(renderBlocks.getBlockIcon(par1BlockBeacon.getBaseBlock()));
        renderBlocks.setRenderBounds(0.125D, 0.0062500000931322575D, 0.125D, 0.875D, (double)var5, 0.875D);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);

        //XXX Rendering beacon shell block.
        renderBlocks.setOverrideBlockTexture(renderBlocks.getBlockIcon(par1BlockBeacon.getShellBlock()));
        renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
        
        //XXX Rendering beacon kernel block.
        renderBlocks.setOverrideBlockTexture(((IBlockBeacon)par1BlockBeacon).getBeaconIcon());
        renderBlocks.setRenderBounds(0.1875D, (double)var5, 0.1875D, 0.8125D, 0.875D, 0.8125D);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
        
        renderBlocks.clearOverrideBlockTexture();
        return true;
    }
	
	public void renderBlockAsItem(Tessellator tess, RenderBlocks renderBlocks, Block block, int par2, float par3)
	{
		IBlockBeacon par1BlockBeacon = (IBlockBeacon)block;
		for (int component = 0; component < 3; ++component)
        {
            if (component == 0)
            {
                renderBlocks.setRenderBounds(0.125D, 0.0D, 0.125D, 0.875D, 0.1875D, 0.875D);
                renderBlocks.setOverrideBlockTexture(renderBlocks.getBlockIcon(par1BlockBeacon.getBaseBlock()));
            }
            else if (component == 1)
            {
                renderBlocks.setRenderBounds(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
                renderBlocks.setOverrideBlockTexture(par1BlockBeacon.getBeaconIcon());
            }
            else if (component == 2)
            {
                renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                renderBlocks.setOverrideBlockTexture(renderBlocks.getBlockIcon(par1BlockBeacon.getShellBlock()));
            }

            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tess.startDrawingQuads();
            tess.setNormal(0.0F, -1.0F, 0.0F);
            renderBlocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(block, 0, par2));
            tess.draw();
            tess.startDrawingQuads();
            tess.setNormal(0.0F, 1.0F, 0.0F);
            renderBlocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderBlocks.getBlockIconFromSideAndMetadata(block, 1, par2));
            tess.draw();
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

        renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        renderBlocks.clearOverrideBlockTexture();
	}
}
