package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockFenceGate;

public class BlockRenderer021_FenceGate extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer021_FenceGate();
	
	private BlockRenderer021_FenceGate()
	{
		super(21);
	}

	static void nopInit(){}
	
    /**
     * Render block fence gate
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockFenceGate, int par2, int par3, int par4)
    {
        boolean var5 = true;
        int var6 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        boolean var7 = BlockFenceGate.isFenceGateOpen(var6);
        int var8 = BlockDirectional.getDirection(var6);
        float var9 = 0.375F;
        float var10 = 0.5625F;
        float var11 = 0.75F;
        float var12 = 0.9375F;
        float var13 = 0.3125F;
        float var14 = 1.0F;

        if ((var8 == 2 || var8 == 0) && renderBlocks.blockAccess.getBlockId(par2 - 1, par3, par4) == Block.cobblestoneWall.blockID && renderBlocks.blockAccess.getBlockId(par2 + 1, par3, par4) == Block.cobblestoneWall.blockID || (var8 == 3 || var8 == 1) && renderBlocks.blockAccess.getBlockId(par2, par3, par4 - 1) == Block.cobblestoneWall.blockID && renderBlocks.blockAccess.getBlockId(par2, par3, par4 + 1) == Block.cobblestoneWall.blockID)
        {
            var9 -= 0.1875F;
            var10 -= 0.1875F;
            var11 -= 0.1875F;
            var12 -= 0.1875F;
            var13 -= 0.1875F;
            var14 -= 0.1875F;
        }

        renderBlocks.renderAllFaces = true;
        float var15;
        float var17;
        float var16;
        float var18;

        if (var8 != 3 && var8 != 1)
        {
            var15 = 0.0F;
            var16 = 0.125F;
            var17 = 0.4375F;
            var18 = 0.5625F;
            renderBlocks.setRenderBounds((double)var15, (double)var13, (double)var17, (double)var16, (double)var14, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            var15 = 0.875F;
            var16 = 1.0F;
            renderBlocks.setRenderBounds((double)var15, (double)var13, (double)var17, (double)var16, (double)var14, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
        }
        else
        {
            renderBlocks.uvRotateTop = 1;
            var15 = 0.4375F;
            var16 = 0.5625F;
            var17 = 0.0F;
            var18 = 0.125F;
            renderBlocks.setRenderBounds((double)var15, (double)var13, (double)var17, (double)var16, (double)var14, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            var17 = 0.875F;
            var18 = 1.0F;
            renderBlocks.setRenderBounds((double)var15, (double)var13, (double)var17, (double)var16, (double)var14, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            renderBlocks.uvRotateTop = 0;
        }

        if (var7)
        {
            if (var8 == 2 || var8 == 0)
            {
                renderBlocks.uvRotateTop = 1;
            }

            if (var8 == 3)
            {
                renderBlocks.setRenderBounds(0.8125D, (double)var9, 0.0D, 0.9375D, (double)var12, 0.125D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.8125D, (double)var9, 0.875D, 0.9375D, (double)var12, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.5625D, (double)var9, 0.0D, 0.8125D, (double)var10, 0.125D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.5625D, (double)var9, 0.875D, 0.8125D, (double)var10, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.5625D, (double)var11, 0.0D, 0.8125D, (double)var12, 0.125D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.5625D, (double)var11, 0.875D, 0.8125D, (double)var12, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            }
            else if (var8 == 1)
            {
                renderBlocks.setRenderBounds(0.0625D, (double)var9, 0.0D, 0.1875D, (double)var12, 0.125D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.0625D, (double)var9, 0.875D, 0.1875D, (double)var12, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.1875D, (double)var9, 0.0D, 0.4375D, (double)var10, 0.125D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.1875D, (double)var9, 0.875D, 0.4375D, (double)var10, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.1875D, (double)var11, 0.0D, 0.4375D, (double)var12, 0.125D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.1875D, (double)var11, 0.875D, 0.4375D, (double)var12, 1.0D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            }
            else if (var8 == 0)
            {
                renderBlocks.setRenderBounds(0.0D, (double)var9, 0.8125D, 0.125D, (double)var12, 0.9375D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.875D, (double)var9, 0.8125D, 1.0D, (double)var12, 0.9375D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.0D, (double)var9, 0.5625D, 0.125D, (double)var10, 0.8125D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.875D, (double)var9, 0.5625D, 1.0D, (double)var10, 0.8125D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.0D, (double)var11, 0.5625D, 0.125D, (double)var12, 0.8125D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.875D, (double)var11, 0.5625D, 1.0D, (double)var12, 0.8125D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            }
            else if (var8 == 2)
            {
                renderBlocks.setRenderBounds(0.0D, (double)var9, 0.0625D, 0.125D, (double)var12, 0.1875D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.875D, (double)var9, 0.0625D, 1.0D, (double)var12, 0.1875D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.0D, (double)var9, 0.1875D, 0.125D, (double)var10, 0.4375D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.875D, (double)var9, 0.1875D, 1.0D, (double)var10, 0.4375D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.0D, (double)var11, 0.1875D, 0.125D, (double)var12, 0.4375D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
                renderBlocks.setRenderBounds(0.875D, (double)var11, 0.1875D, 1.0D, (double)var12, 0.4375D);
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            }
        }
        else if (var8 != 3 && var8 != 1)
        {
            var15 = 0.375F;
            var16 = 0.5F;
            var17 = 0.4375F;
            var18 = 0.5625F;
            renderBlocks.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var12, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            var15 = 0.5F;
            var16 = 0.625F;
            renderBlocks.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var12, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            var15 = 0.625F;
            var16 = 0.875F;
            renderBlocks.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var10, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            renderBlocks.setRenderBounds((double)var15, (double)var11, (double)var17, (double)var16, (double)var12, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            var15 = 0.125F;
            var16 = 0.375F;
            renderBlocks.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var10, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            renderBlocks.setRenderBounds((double)var15, (double)var11, (double)var17, (double)var16, (double)var12, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
        }
        else
        {
            renderBlocks.uvRotateTop = 1;
            var15 = 0.4375F;
            var16 = 0.5625F;
            var17 = 0.375F;
            var18 = 0.5F;
            renderBlocks.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var12, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            var17 = 0.5F;
            var18 = 0.625F;
            renderBlocks.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var12, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            var17 = 0.625F;
            var18 = 0.875F;
            renderBlocks.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var10, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            renderBlocks.setRenderBounds((double)var15, (double)var11, (double)var17, (double)var16, (double)var12, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            var17 = 0.125F;
            var18 = 0.375F;
            renderBlocks.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var10, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
            renderBlocks.setRenderBounds((double)var15, (double)var11, (double)var17, (double)var16, (double)var12, (double)var18);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFenceGate, par2, par3, par4);
        }

        renderBlocks.renderAllFaces = false;
        renderBlocks.uvRotateTop = 0;
        renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        return var5;
    }
}
