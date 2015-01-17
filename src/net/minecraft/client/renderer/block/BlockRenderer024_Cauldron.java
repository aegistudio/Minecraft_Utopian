package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockFluid;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer024_Cauldron extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer024_Cauldron();
	
	private BlockRenderer024_Cauldron()
	{
		super(24);
	}

	static void nopInit(){}
	
    /**
     * Render block cauldron
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockCauldron, int par2, int par3, int par4)
    {
    	BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockCauldron, par2, par3, par4);
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(par1BlockCauldron.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var6 = 1.0F;
        int var7 = par1BlockCauldron.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;
        float var12;

        if (EntityRenderer.anaglyphEnable)
        {
            float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var11;
            var9 = var12;
            var10 = var13;
        }

        var5.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
        Icon var16 = par1BlockCauldron.getBlockTextureFromSide(2);
        var12 = 0.125F;
        renderBlocks.renderFaceXPos(par1BlockCauldron, (double)((float)par2 - 1.0F + var12), (double)par3, (double)par4, var16);
        renderBlocks.renderFaceXNeg(par1BlockCauldron, (double)((float)par2 + 1.0F - var12), (double)par3, (double)par4, var16);
        renderBlocks.renderFaceZPos(par1BlockCauldron, (double)par2, (double)par3, (double)((float)par4 - 1.0F + var12), var16);
        renderBlocks.renderFaceZNeg(par1BlockCauldron, (double)par2, (double)par3, (double)((float)par4 + 1.0F - var12), var16);
        Icon var17 = BlockCauldron.func_94375_b("cauldron_inner");
        renderBlocks.renderFaceYPos(par1BlockCauldron, (double)par2, (double)((float)par3 - 1.0F + 0.25F), (double)par4, var17);
        renderBlocks.renderFaceYNeg(par1BlockCauldron, (double)par2, (double)((float)par3 + 1.0F - 0.75F), (double)par4, var17);
        int var14 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);

        if (var14 > 0)
        {
            Icon var15 = BlockFluid.func_94424_b("water");

            if (var14 > 3)
            {
                var14 = 3;
            }

            renderBlocks.renderFaceYPos(par1BlockCauldron, (double)par2, (double)((float)par3 - 1.0F + (6.0F + (float)var14 * 3.0F) / 16.0F), (double)par4, var15);
        }

        return true;
    }
}
