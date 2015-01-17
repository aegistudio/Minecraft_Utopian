package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockComparator;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer037_Comparator extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer037_Comparator();
	
	private BlockRenderer037_Comparator()
	{
		super(37);
	}
	
	static void nopInit(){}
	
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockComparator, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(par1BlockComparator.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        int var6 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        int var7 = var6 & 3;
        double var8 = 0.0D;
        double var10 = -0.1875D;
        double var12 = 0.0D;
        double var14 = 0.0D;
        double var16 = 0.0D;
        Icon icon;

        if (((IBlockComparator)par1BlockComparator).func_94490_c(var6))
        {
            icon = Block.torchRedstoneActive.getBlockTextureFromSide(0);
        }
        else
        {
            var10 -= 0.1875D;
            icon = Block.torchRedstoneIdle.getBlockTextureFromSide(0);
        }

        switch (var7)
        {
            case 0:
                var12 = -0.3125D;
                var16 = 1.0D;
                break;

            case 1:
                var8 = 0.3125D;
                var14 = -1.0D;
                break;

            case 2:
                var12 = 0.3125D;
                var16 = -1.0D;
                break;

            case 3:
                var8 = -0.3125D;
                var14 = 1.0D;
        }

        ((BlockRenderer002_Torch)BlockRenderer002_Torch.renderer).renderTorchAtAngle(renderBlocks, par1BlockComparator, (double)par2 + 0.25D * var14 + 0.1875D * var16, (double)((float)par3 - 0.1875F), (double)par4 + 0.25D * var16 + 0.1875D * var14, 0.0D, 0.0D, var6);
        ((BlockRenderer002_Torch)BlockRenderer002_Torch.renderer).renderTorchAtAngle(renderBlocks, par1BlockComparator, (double)par2 + 0.25D * var14 + -0.1875D * var16, (double)((float)par3 - 0.1875F), (double)par4 + 0.25D * var16 + -0.1875D * var14, 0.0D, 0.0D, var6);
        renderBlocks.setOverrideBlockTexture(icon);
        ((BlockRenderer002_Torch)BlockRenderer002_Torch.renderer).renderTorchAtAngle(renderBlocks, par1BlockComparator, (double)par2 + var8, (double)par3 + var10, (double)par4 + var12, 0.0D, 0.0D, var6);
        renderBlocks.clearOverrideBlockTexture();
        ((BlockRenderer036_RedstoneLogic)BlockRenderer036_RedstoneLogic.renderer).renderBlockRedstoneLogicMetadata(renderBlocks, par1BlockComparator, par2, par3, par4, var7);
        return true;
    }
}
