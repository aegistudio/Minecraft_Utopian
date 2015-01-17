package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.IBlockRailBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer009_MinecartTrack extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer009_MinecartTrack();
	
	private BlockRenderer009_MinecartTrack()
	{
		super(9);
	}
	
	static void nopInit(){}
	
    /**
     * Renders a minecart track block at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        IBlockRailBase par1BlockRailBase = (IBlockRailBase)par1Block;
        int var6 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        Icon var7 = renderBlocks.getBlockIconFromSideAndMetadata(par1Block, 0, var6);

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var7 = renderBlocks.overrideBlockTexture;
        }

        if (par1BlockRailBase.isPowered())
        {
            var6 &= 7;
        }

        var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        double var8 = (double)var7.getMinU();
        double var10 = (double)var7.getMinV();
        double var12 = (double)var7.getMaxU();
        double var14 = (double)var7.getMaxV();
        double var16 = 0.0625D;
        double var18 = (double)(par2 + 1);
        double var20 = (double)(par2 + 1);
        double var22 = (double)(par2 + 0);
        double var24 = (double)(par2 + 0);
        double var26 = (double)(par4 + 0);
        double var28 = (double)(par4 + 1);
        double var30 = (double)(par4 + 1);
        double var32 = (double)(par4 + 0);
        double var34 = (double)par3 + var16;
        double var36 = (double)par3 + var16;
        double var38 = (double)par3 + var16;
        double var40 = (double)par3 + var16;

        if (var6 != 1 && var6 != 2 && var6 != 3 && var6 != 7)
        {
            if (var6 == 8)
            {
                var18 = var20 = (double)(par2 + 0);
                var22 = var24 = (double)(par2 + 1);
                var26 = var32 = (double)(par4 + 1);
                var28 = var30 = (double)(par4 + 0);
            }
            else if (var6 == 9)
            {
                var18 = var24 = (double)(par2 + 0);
                var20 = var22 = (double)(par2 + 1);
                var26 = var28 = (double)(par4 + 0);
                var30 = var32 = (double)(par4 + 1);
            }
        }
        else
        {
            var18 = var24 = (double)(par2 + 1);
            var20 = var22 = (double)(par2 + 0);
            var26 = var28 = (double)(par4 + 1);
            var30 = var32 = (double)(par4 + 0);
        }

        if (var6 != 2 && var6 != 4)
        {
            if (var6 == 3 || var6 == 5)
            {
                ++var36;
                ++var38;
            }
        }
        else
        {
            ++var34;
            ++var40;
        }

        var5.addVertexWithUV(var18, var34, var26, var12, var10);
        var5.addVertexWithUV(var20, var36, var28, var12, var14);
        var5.addVertexWithUV(var22, var38, var30, var8, var14);
        var5.addVertexWithUV(var24, var40, var32, var8, var10);
        var5.addVertexWithUV(var24, var40, var32, var8, var10);
        var5.addVertexWithUV(var22, var38, var30, var8, var14);
        var5.addVertexWithUV(var20, var36, var28, var12, var14);
        var5.addVertexWithUV(var18, var34, var26, var12, var10);
        return true;
    }
}
