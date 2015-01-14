package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRendererTorch extends BlockRenderer
{
	
	public static final BlockRenderer renderer = new BlockRendererTorch();
	
	private BlockRendererTorch()
	{
		super(2);
	}
	
	static void nopInit(){}
	
    /**
     * Renders a torch block at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        int var5 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        Tessellator var6 = Tessellator.instance;
        var6.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        double var7 = 0.4000000059604645D;
        double var9 = 0.5D - var7;
        double var11 = 0.20000000298023224D;

        if (var5 == 1) renderBlocks.renderTorchAtAngle(par1Block, (double)par2 - var9, (double)par3 + var11, (double)par4, -var7, 0.0D, 0);
        else if (var5 == 2) renderBlocks.renderTorchAtAngle(par1Block, (double)par2 + var9, (double)par3 + var11, (double)par4, var7, 0.0D, 0);
        else if (var5 == 3) renderBlocks.renderTorchAtAngle(par1Block, (double)par2, (double)par3 + var11, (double)par4 - var9, 0.0D, -var7, 0);
        else if (var5 == 4) renderBlocks.renderTorchAtAngle(par1Block, (double)par2, (double)par3 + var11, (double)par4 + var9, 0.0D, var7, 0);
        else renderBlocks.renderTorchAtAngle(par1Block, (double)par2, (double)par3, (double)par4, 0.0D, 0.0D, 0);

        return true;
    }
}
