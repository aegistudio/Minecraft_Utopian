package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer036_RedstoneLogic extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer036_RedstoneLogic();
	
	private BlockRenderer036_RedstoneLogic()
	{
		super(36);
	}
	
	static void nopInit(){}
	
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockRedstoneLogic, int par2, int par3, int par4)
    {
        /*Tessellator var5 = Tessellator.instance;*/
        this.renderBlockRedstoneLogicMetadata(renderBlocks, par1BlockRedstoneLogic, par2, par3, par4, renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4) & 3);
        return true;
    }

    public void renderBlockRedstoneLogicMetadata(RenderBlocks renderBlocks, Block par1BlockRedstoneLogic, int par2, int par3, int par4, int par5)
    {
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockRedstoneLogic, par2, par3, par4);
        Tessellator var6 = Tessellator.instance;
        var6.setBrightness(par1BlockRedstoneLogic.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        int var7 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        Icon var8 = renderBlocks.getBlockIconFromSideAndMetadata(par1BlockRedstoneLogic, 1, var7);
        double var9 = (double)var8.getMinU();
        double var11 = (double)var8.getMaxU();
        double var13 = (double)var8.getMinV();
        double var15 = (double)var8.getMaxV();
        double var17 = 0.125D;
        double var19 = (double)(par2 + 1);
        double var21 = (double)(par2 + 1);
        double var23 = (double)(par2 + 0);
        double var25 = (double)(par2 + 0);
        double var27 = (double)(par4 + 0);
        double var29 = (double)(par4 + 1);
        double var31 = (double)(par4 + 1);
        double var33 = (double)(par4 + 0);
        double var35 = (double)par3 + var17;

        if (par5 == 2)
        {
            var19 = var21 = (double)(par2 + 0);
            var23 = var25 = (double)(par2 + 1);
            var27 = var33 = (double)(par4 + 1);
            var29 = var31 = (double)(par4 + 0);
        }
        else if (par5 == 3)
        {
            var19 = var25 = (double)(par2 + 0);
            var21 = var23 = (double)(par2 + 1);
            var27 = var29 = (double)(par4 + 0);
            var31 = var33 = (double)(par4 + 1);
        }
        else if (par5 == 1)
        {
            var19 = var25 = (double)(par2 + 1);
            var21 = var23 = (double)(par2 + 0);
            var27 = var29 = (double)(par4 + 1);
            var31 = var33 = (double)(par4 + 0);
        }

        var6.addVertexWithUV(var25, var35, var33, var9, var13);
        var6.addVertexWithUV(var23, var35, var31, var9, var15);
        var6.addVertexWithUV(var21, var35, var29, var11, var15);
        var6.addVertexWithUV(var19, var35, var27, var11, var13);
    }
}
