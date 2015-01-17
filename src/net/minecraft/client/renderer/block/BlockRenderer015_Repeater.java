package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.IBlockRedstoneRepeater;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer015_Repeater extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer015_Repeater();
	
	private BlockRenderer015_Repeater()
	{
		super(15);
	}
	
	static void nopInit(){}
	
	/**
     * render a redstone repeater at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
		IBlockRedstoneRepeater par1BlockRedstoneRepeater = (IBlockRedstoneRepeater) par1Block;
		
        int var5 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        int var6 = var5 & 3;
        int var7 = (var5 & 12) >> 2;
        Tessellator var8 = Tessellator.instance;
        var8.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        var8.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        double var9 = -0.1875D;
        boolean var11 = par1BlockRedstoneRepeater.func_94476_e(renderBlocks.blockAccess, par2, par3, par4, var5);
        double var12 = 0.0D;
        double var14 = 0.0D;
        double var16 = 0.0D;
        double var18 = 0.0D;

        switch (var6)
        {
            case 0:
                var18 = -0.3125D;
                var14 = BlockRedstoneRepeater.repeaterTorchOffset[var7];
                break;

            case 1:
                var16 = 0.3125D;
                var12 = -BlockRedstoneRepeater.repeaterTorchOffset[var7];
                break;

            case 2:
                var18 = 0.3125D;
                var14 = -BlockRedstoneRepeater.repeaterTorchOffset[var7];
                break;

            case 3:
                var16 = -0.3125D;
                var12 = BlockRedstoneRepeater.repeaterTorchOffset[var7];
        }

        if (!var11)
        {
            ((BlockRenderer002_Torch)BlockRenderer002_Torch.renderer).renderTorchAtAngle(renderBlocks, par1Block, (double)par2 + var12, (double)par3 + var9, (double)par4 + var14, 0.0D, 0.0D, 0);
        }
        else
        {
            Icon var20 = renderBlocks.getBlockIcon(Block.bedrock);
            renderBlocks.setOverrideBlockTexture(var20);
            float var21 = 2.0F;
            float var22 = 14.0F;
            float var23 = 7.0F;
            float var24 = 9.0F;

            switch (var6)
            {
                case 1:
                case 3:
                    var21 = 7.0F;
                    var22 = 9.0F;
                    var23 = 2.0F;
                    var24 = 14.0F;

                case 0:
                case 2:
                default:
                    renderBlocks.setRenderBounds((double)(var21 / 16.0F + (float)var12), 0.125D, (double)(var23 / 16.0F + (float)var14), (double)(var22 / 16.0F + (float)var12), 0.25D, (double)(var24 / 16.0F + (float)var14));
                    double var25 = (double)var20.getInterpolatedU((double)var21);
                    double var27 = (double)var20.getInterpolatedV((double)var23);
                    double var29 = (double)var20.getInterpolatedU((double)var22);
                    double var31 = (double)var20.getInterpolatedV((double)var24);
                    var8.addVertexWithUV((double)((float)par2 + var21 / 16.0F) + var12, (double)((float)par3 + 0.25F), (double)((float)par4 + var23 / 16.0F) + var14, var25, var27);
                    var8.addVertexWithUV((double)((float)par2 + var21 / 16.0F) + var12, (double)((float)par3 + 0.25F), (double)((float)par4 + var24 / 16.0F) + var14, var25, var31);
                    var8.addVertexWithUV((double)((float)par2 + var22 / 16.0F) + var12, (double)((float)par3 + 0.25F), (double)((float)par4 + var24 / 16.0F) + var14, var29, var31);
                    var8.addVertexWithUV((double)((float)par2 + var22 / 16.0F) + var12, (double)((float)par3 + 0.25F), (double)((float)par4 + var23 / 16.0F) + var14, var29, var27);
                    BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
                    renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
                    renderBlocks.clearOverrideBlockTexture();
            }
        }

        var8.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        var8.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        ((BlockRenderer002_Torch)BlockRenderer002_Torch.renderer).renderTorchAtAngle(renderBlocks, par1Block, (double)par2 + var16, (double)par3 + var9, (double)par4 + var18, 0.0D, 0.0D, 0);
        BlockRenderer036_RedstoneLogic.renderer.onRenderBlock(renderBlocks, par1Block, par2, par3, par4);
        return true;
    }
}
