package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;

public class BlockRenderer039_Quartz extends BlockRenderer
{
	
	public static final BlockRenderer renderer = new BlockRenderer039_Quartz();
	
	private BlockRenderer039_Quartz()
	{
		super(39);
	}

	static void nopInit(){}
	
	@Override
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block block, int par2, int par3, int par4)
	{
        int var5 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);

        if (var5 == 3)
        {
            renderBlocks.uvRotateEast = 1;
            renderBlocks.uvRotateWest = 1;
            renderBlocks.uvRotateTop = 1;
            renderBlocks.uvRotateBottom = 1;
        }
        else if (var5 == 4)
        {
            renderBlocks.uvRotateSouth = 1;
            renderBlocks.uvRotateNorth = 1;
        }

        boolean var6 = BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, block, par2, par3, par4);
        renderBlocks.uvRotateSouth = 0;
        renderBlocks.uvRotateEast = 0;
        renderBlocks.uvRotateWest = 0;
        renderBlocks.uvRotateNorth = 0;
        renderBlocks.uvRotateTop = 0;
        renderBlocks.uvRotateBottom = 0;
        return var6;
    }
	
}
