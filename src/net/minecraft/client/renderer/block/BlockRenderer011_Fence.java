package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockFence;

public class BlockRenderer011_Fence extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer011_Fence();
	
	private BlockRenderer011_Fence()
	{
		super(11);
	}
	
	static void nopInit(){}

	@Override
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
		IBlockFence par1BlockFence = (IBlockFence)par1Block;
		
        boolean var5 = false;
        float var6 = 0.375F;
        float var7 = 0.625F;
        renderBlocks.setRenderBounds((double)var6, 0.0D, (double)var6, (double)var7, 1.0D, (double)var7);
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
        var5 = true;
        boolean var8 = false;
        boolean var9 = false;

        if (par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, par2 - 1, par3, par4) || par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, par2 + 1, par3, par4))
        {
            var8 = true;
        }

        if (par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, par2, par3, par4 - 1) || par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, par2, par3, par4 + 1))
        {
            var9 = true;
        }

        boolean var10 = par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, par2 - 1, par3, par4);
        boolean var11 = par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, par2 + 1, par3, par4);
        boolean var12 = par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, par2, par3, par4 - 1);
        boolean var13 = par1BlockFence.canConnectFenceTo(renderBlocks.blockAccess, par2, par3, par4 + 1);

        if (!var8 && !var9)
        {
            var8 = true;
        }

        var6 = 0.4375F;
        var7 = 0.5625F;
        float var14 = 0.75F;
        float var15 = 0.9375F;
        float var16 = var10 ? 0.0F : var6;
        float var17 = var11 ? 1.0F : var7;
        float var18 = var12 ? 0.0F : var6;
        float var19 = var13 ? 1.0F : var7;

        if (var8)
        {
            renderBlocks.setRenderBounds((double)var16, (double)var14, (double)var6, (double)var17, (double)var15, (double)var7);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            var5 = true;
        }

        if (var9)
        {
            renderBlocks.setRenderBounds((double)var6, (double)var14, (double)var18, (double)var7, (double)var15, (double)var19);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            var5 = true;
        }

        var14 = 0.375F;
        var15 = 0.5625F;

        if (var8)
        {
            renderBlocks.setRenderBounds((double)var16, (double)var14, (double)var6, (double)var17, (double)var15, (double)var7);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            var5 = true;
        }

        if (var9)
        {
            renderBlocks.setRenderBounds((double)var6, (double)var14, (double)var18, (double)var7, (double)var15, (double)var19);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            var5 = true;
        }

        par1Block.setBlockBoundsBasedOnState(renderBlocks.blockAccess, par2, par3, par4);
        return var5;
    }
	
}
