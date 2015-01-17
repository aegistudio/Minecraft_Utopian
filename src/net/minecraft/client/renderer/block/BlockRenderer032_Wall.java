package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockWall;

public class BlockRenderer032_Wall extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer032_Wall();
	
	private BlockRenderer032_Wall()
	{
		super(32);
	}
	
	static void nopInit(){}

    /**
     * Renders wall block
     */
	
	@Override
 	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
		IBlockWall par1BlockWall = (IBlockWall) par1Block;
        boolean var5 = par1BlockWall.canConnectWallTo(renderBlocks.blockAccess, par2 - 1, par3, par4);
        boolean var6 = par1BlockWall.canConnectWallTo(renderBlocks.blockAccess, par2 + 1, par3, par4);
        boolean var7 = par1BlockWall.canConnectWallTo(renderBlocks.blockAccess, par2, par3, par4 - 1);
        boolean var8 = par1BlockWall.canConnectWallTo(renderBlocks.blockAccess, par2, par3, par4 + 1);
        boolean var9 = var7 && var8 && !var5 && !var6;
        boolean var10 = !var7 && !var8 && var5 && var6;
        boolean var11 = renderBlocks.blockAccess.isAirBlock(par2, par3 + 1, par4);

        if ((var9 || var10) && var11)
        {
            if (var9)
            {
                renderBlocks.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            }
            else
            {
                renderBlocks.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            }
        }
        else
        {
            renderBlocks.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);

            if (var5)
            {
                renderBlocks.setRenderBounds(0.0D, 0.0D, 0.3125D, 0.25D, 0.8125D, 0.6875D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            }

            if (var6)
            {
                renderBlocks.setRenderBounds(0.75D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            }

            if (var7)
            {
                renderBlocks.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 0.25D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            }

            if (var8)
            {
                renderBlocks.setRenderBounds(0.3125D, 0.0D, 0.75D, 0.6875D, 0.8125D, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            }
        }

        par1Block.setBlockBoundsBasedOnState(renderBlocks.blockAccess, par2, par3, par4);
        return true;
    }
}
