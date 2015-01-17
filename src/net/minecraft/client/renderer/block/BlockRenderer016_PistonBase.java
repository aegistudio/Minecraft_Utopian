package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.IBlockPistonBase;

public class BlockRenderer016_PistonBase extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer016_PistonBase();
	
	private BlockRenderer016_PistonBase()
	{
		super(16);
	}
	
	static void nopInit(){}

	@Override
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block block, int x,
			int y, int z)
	{
		return this.renderPistonBase(renderBlocks, block, x, y, z, false);
	}
	
    /**
     * renders a block as a piston base
     */
    public boolean renderPistonBase(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4, boolean par5)
    {
        int var6 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        boolean var7 = par5 || (var6 & 8) != 0;
        int var8 = BlockPistonBase.getOrientation(var6);

        if (var7)
        {
            switch (var8)
            {
                case 0:
                    renderBlocks.uvRotateEast = 3;
                    renderBlocks.uvRotateWest = 3;
                    renderBlocks.uvRotateSouth = 3;
                    renderBlocks.uvRotateNorth = 3;
                    renderBlocks.setRenderBounds(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);
                    break;

                case 1:
                    renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
                    break;

                case 2:
                    renderBlocks.uvRotateSouth = 1;
                    renderBlocks.uvRotateNorth = 2;
                    renderBlocks.setRenderBounds(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D);
                    break;

                case 3:
                    renderBlocks.uvRotateSouth = 2;
                    renderBlocks.uvRotateNorth = 1;
                    renderBlocks.uvRotateTop = 3;
                    renderBlocks.uvRotateBottom = 3;
                    renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D);
                    break;

                case 4:
                    renderBlocks.uvRotateEast = 1;
                    renderBlocks.uvRotateWest = 2;
                    renderBlocks.uvRotateTop = 2;
                    renderBlocks.uvRotateBottom = 1;
                    renderBlocks.setRenderBounds(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                    break;

                case 5:
                    renderBlocks.uvRotateEast = 2;
                    renderBlocks.uvRotateWest = 1;
                    renderBlocks.uvRotateTop = 1;
                    renderBlocks.uvRotateBottom = 2;
                    renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D);
            }

            ((IBlockPistonBase)par1Block).func_96479_b((float)renderBlocks.renderMinX, (float)renderBlocks.renderMinY, (float)renderBlocks.renderMinZ, (float)renderBlocks.renderMaxX, (float)renderBlocks.renderMaxY, (float)renderBlocks.renderMaxZ);
            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            renderBlocks.uvRotateEast = 0;
            renderBlocks.uvRotateWest = 0;
            renderBlocks.uvRotateSouth = 0;
            renderBlocks.uvRotateNorth = 0;
            renderBlocks.uvRotateTop = 0;
            renderBlocks.uvRotateBottom = 0;
            renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            ((IBlockPistonBase)par1Block).func_96479_b((float)renderBlocks.renderMinX, (float)renderBlocks.renderMinY, (float)renderBlocks.renderMinZ, (float)renderBlocks.renderMaxX, (float)renderBlocks.renderMaxY, (float)renderBlocks.renderMaxZ);
        }
        else
        {
            switch (var8)
            {
                case 0:
                    renderBlocks.uvRotateEast = 3;
                    renderBlocks.uvRotateWest = 3;
                    renderBlocks.uvRotateSouth = 3;
                    renderBlocks.uvRotateNorth = 3;

                case 1:
                default:
                    break;

                case 2:
                    renderBlocks.uvRotateSouth = 1;
                    renderBlocks.uvRotateNorth = 2;
                    break;

                case 3:
                    renderBlocks.uvRotateSouth = 2;
                    renderBlocks.uvRotateNorth = 1;
                    renderBlocks.uvRotateTop = 3;
                    renderBlocks.uvRotateBottom = 3;
                    break;

                case 4:
                    renderBlocks.uvRotateEast = 1;
                    renderBlocks.uvRotateWest = 2;
                    renderBlocks.uvRotateTop = 2;
                    renderBlocks.uvRotateBottom = 1;
                    break;

                case 5:
                    renderBlocks.uvRotateEast = 2;
                    renderBlocks.uvRotateWest = 1;
                    renderBlocks.uvRotateTop = 1;
                    renderBlocks.uvRotateBottom = 2;
            }

            BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
            renderBlocks.uvRotateEast = 0;
            renderBlocks.uvRotateWest = 0;
            renderBlocks.uvRotateSouth = 0;
            renderBlocks.uvRotateNorth = 0;
            renderBlocks.uvRotateTop = 0;
            renderBlocks.uvRotateBottom = 0;
        }

        return true;
    }

    /**
     * Render all faces of the piston base
     */
    public void renderPistonBaseAllFaces(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        renderBlocks.renderAllFaces = true;
        this.renderPistonBase(renderBlocks, par1Block, par2, par3, par4, true);
        renderBlocks.renderAllFaces = false;
    }
}
