package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockBeacon;

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
}
