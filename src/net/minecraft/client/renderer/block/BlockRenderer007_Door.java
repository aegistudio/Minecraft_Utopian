package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer007_Door extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer007_Door();
	
	private BlockRenderer007_Door()
	{
		super(7);
	}
	
	static void nopInit(){}
	
    /**
     * Renders a door block at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        int var6 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);

        if ((var6 & 8) != 0)
        {
            if (renderBlocks.blockAccess.getBlockId(par2, par3 - 1, par4) != par1Block.blockID)
            {
                return false;
            }
        }
        else if (renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4) != par1Block.blockID)
        {
            return false;
        }

        boolean var7 = false;
        float var8 = 0.5F;
        float var9 = 1.0F;
        float var10 = 0.8F;
        float var11 = 0.6F;
        int var12 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4);
        var5.setBrightness(renderBlocks.renderMinY > 0.0D ? var12 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4));
        var5.setColorOpaque_F(var8, var8, var8);
        renderBlocks.renderFaceYNeg(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 0));
        var7 = true;
        var5.setBrightness(renderBlocks.renderMaxY < 1.0D ? var12 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4));
        var5.setColorOpaque_F(var9, var9, var9);
        renderBlocks.renderFaceYPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 1));
        var7 = true;
        var5.setBrightness(renderBlocks.renderMinZ > 0.0D ? var12 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1));
        var5.setColorOpaque_F(var10, var10, var10);
        Icon var14 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 2);
        renderBlocks.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, var14);
        var7 = true;
        renderBlocks.flipTexture = false;
        var5.setBrightness(renderBlocks.renderMaxZ < 1.0D ? var12 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1));
        var5.setColorOpaque_F(var10, var10, var10);
        var14 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 3);
        renderBlocks.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, var14);
        var7 = true;
        renderBlocks.flipTexture = false;
        var5.setBrightness(renderBlocks.renderMinX > 0.0D ? var12 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4));
        var5.setColorOpaque_F(var11, var11, var11);
        var14 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 4);
        renderBlocks.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, var14);
        var7 = true;
        renderBlocks.flipTexture = false;
        var5.setBrightness(renderBlocks.renderMaxX < 1.0D ? var12 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4));
        var5.setColorOpaque_F(var11, var11, var11);
        var14 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 5);
        renderBlocks.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, var14);
        var7 = true;
        renderBlocks.flipTexture = false;
        return var7;
    }
}
