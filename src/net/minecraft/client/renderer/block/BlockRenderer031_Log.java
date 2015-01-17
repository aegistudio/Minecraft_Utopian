package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;

public class BlockRenderer031_Log extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer031_Log();
	
	private BlockRenderer031_Log()
	{
		super(31);
	}

	static void nopInit(){}
	
	/*
     * Renders a log block at the given coordinates
     */
    public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        int var5 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        int var6 = var5 & 12;

        if (var6 == 4)
        {
            renderBlocks.uvRotateEast = 1;
            renderBlocks.uvRotateWest = 1;
            renderBlocks.uvRotateTop = 1;
            renderBlocks.uvRotateBottom = 1;
        }
        else if (var6 == 8)
        {
            renderBlocks.uvRotateSouth = 1;
            renderBlocks.uvRotateNorth = 1;
        }

        boolean var7 = BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
        renderBlocks.uvRotateSouth = 0;
        renderBlocks.uvRotateEast = 0;
        renderBlocks.uvRotateWest = 0;
        renderBlocks.uvRotateNorth = 0;
        renderBlocks.uvRotateTop = 0;
        renderBlocks.uvRotateBottom = 0;
        return var7;
    }
	
}
