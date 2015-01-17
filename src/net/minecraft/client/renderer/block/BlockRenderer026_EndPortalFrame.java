package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.IBlockEndPortalFrame;

public class BlockRenderer026_EndPortalFrame extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer026_EndPortalFrame();
	
	private BlockRenderer026_EndPortalFrame()
	{
		super(26);
	}

	static void nopInit(){}
	
    /**
     * Render BlockEndPortalFrame
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockEndPortalFrame, int par2, int par3, int par4)
    {
        int var5 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        int var6 = var5 & 3;

        if (var6 == 0)
        {
            renderBlocks.uvRotateTop = 3;
        }
        else if (var6 == 3)
        {
            renderBlocks.uvRotateTop = 1;
        }
        else if (var6 == 1)
        {
            renderBlocks.uvRotateTop = 2;
        }

        if (!BlockEndPortalFrame.isEnderEyeInserted(var5))
        {
            renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockEndPortalFrame, par2, par3, par4);
            renderBlocks.uvRotateTop = 0;
            return true;
        }
        else
        {
            renderBlocks.renderAllFaces = true;
            renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockEndPortalFrame, par2, par3, par4);
            renderBlocks.setOverrideBlockTexture(((IBlockEndPortalFrame)par1BlockEndPortalFrame).func_94398_p());
            renderBlocks.setRenderBounds(0.25D, 0.8125D, 0.25D, 0.75D, 1.0D, 0.75D);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockEndPortalFrame, par2, par3, par4);
            renderBlocks.renderAllFaces = false;
            renderBlocks.clearOverrideBlockTexture();
            renderBlocks.uvRotateTop = 0;
            return true;
        }
    }
}
