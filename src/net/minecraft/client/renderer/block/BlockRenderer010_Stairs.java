package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockStair;

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
}
