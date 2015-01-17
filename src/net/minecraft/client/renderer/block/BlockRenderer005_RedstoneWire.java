package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer005_RedstoneWire extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer005_RedstoneWire();
	
	private BlockRenderer005_RedstoneWire()
	{
		super(5);
	}
	
	static void nopInit(){}

    /**
     * Renders a redstone wire block at the given coordinates
     */
    public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        int var6 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        Icon var7 = BlockRedstoneWire.func_94409_b("redstoneDust_cross");
        Icon var8 = BlockRedstoneWire.func_94409_b("redstoneDust_line");
        Icon var9 = BlockRedstoneWire.func_94409_b("redstoneDust_cross_overlay");
        Icon var10 = BlockRedstoneWire.func_94409_b("redstoneDust_line_overlay");
        var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var11 = 1.0F;
        float var12 = (float)var6 / 15.0F;
        float var13 = var12 * 0.6F + 0.4F;

        if (var6 == 0)
        {
            var13 = 0.3F;
        }

        float var14 = var12 * var12 * 0.7F - 0.5F;
        float var15 = var12 * var12 * 0.6F - 0.7F;

        if (var14 < 0.0F)
        {
            var14 = 0.0F;
        }

        if (var15 < 0.0F)
        {
            var15 = 0.0F;
        }

        var5.setColorOpaque_F(var13, var14, var15);
        boolean var20 = BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2 - 1, par3, par4, 1) || !renderBlocks.blockAccess.isBlockNormalCube(par2 - 1, par3, par4) && BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4, -1);
        boolean var21 = BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2 + 1, par3, par4, 3) || !renderBlocks.blockAccess.isBlockNormalCube(par2 + 1, par3, par4) && BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4, -1);
        boolean var22 = BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2, par3, par4 - 1, 2) || !renderBlocks.blockAccess.isBlockNormalCube(par2, par3, par4 - 1) && BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1, -1);
        boolean var23 = BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2, par3, par4 + 1, 0) || !renderBlocks.blockAccess.isBlockNormalCube(par2, par3, par4 + 1) && BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1, -1);

        if (!renderBlocks.blockAccess.isBlockNormalCube(par2, par3 + 1, par4))
        {
            if (renderBlocks.blockAccess.isBlockNormalCube(par2 - 1, par3, par4) && BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4, -1))
            {
                var20 = true;
            }

            if (renderBlocks.blockAccess.isBlockNormalCube(par2 + 1, par3, par4) && BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4, -1))
            {
                var21 = true;
            }

            if (renderBlocks.blockAccess.isBlockNormalCube(par2, par3, par4 - 1) && BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1, -1))
            {
                var22 = true;
            }

            if (renderBlocks.blockAccess.isBlockNormalCube(par2, par3, par4 + 1) && BlockRedstoneWire.isPowerProviderOrWire(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1, -1))
            {
                var23 = true;
            }
        }

        float var24 = (float)(par2 + 0);
        float var25 = (float)(par2 + 1);
        float var26 = (float)(par4 + 0);
        float var27 = (float)(par4 + 1);
        int var28 = 0;

        if ((var20 || var21) && !var22 && !var23)
        {
            var28 = 1;
        }

        if ((var22 || var23) && !var21 && !var20)
        {
            var28 = 2;
        }

        if (var28 == 0)
        {
            int var29 = 0;
            int var30 = 0;
            int var31 = 16;
            int var32 = 16;

            if (!var20)
            {
                var24 += 0.3125F;
            }

            if (!var20)
            {
                var29 += 5;
            }

            if (!var21)
            {
                var25 -= 0.3125F;
            }

            if (!var21)
            {
                var31 -= 5;
            }

            if (!var22)
            {
                var26 += 0.3125F;
            }

            if (!var22)
            {
                var30 += 5;
            }

            if (!var23)
            {
                var27 -= 0.3125F;
            }

            if (!var23)
            {
                var32 -= 5;
            }

            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var27, (double)var7.getInterpolatedU((double)var31), (double)var7.getInterpolatedV((double)var32));
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var26, (double)var7.getInterpolatedU((double)var31), (double)var7.getInterpolatedV((double)var30));
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var26, (double)var7.getInterpolatedU((double)var29), (double)var7.getInterpolatedV((double)var30));
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var27, (double)var7.getInterpolatedU((double)var29), (double)var7.getInterpolatedV((double)var32));
            var5.setColorOpaque_F(var11, var11, var11);
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var27, (double)var9.getInterpolatedU((double)var31), (double)var9.getInterpolatedV((double)var32));
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var26, (double)var9.getInterpolatedU((double)var31), (double)var9.getInterpolatedV((double)var30));
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var26, (double)var9.getInterpolatedU((double)var29), (double)var9.getInterpolatedV((double)var30));
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var27, (double)var9.getInterpolatedU((double)var29), (double)var9.getInterpolatedV((double)var32));
        }
        else if (var28 == 1)
        {
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var27, (double)var8.getMaxU(), (double)var8.getMaxV());
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var26, (double)var8.getMaxU(), (double)var8.getMinV());
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var26, (double)var8.getMinU(), (double)var8.getMinV());
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var27, (double)var8.getMinU(), (double)var8.getMaxV());
            var5.setColorOpaque_F(var11, var11, var11);
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var27, (double)var10.getMaxU(), (double)var10.getMaxV());
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var26, (double)var10.getMaxU(), (double)var10.getMinV());
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var26, (double)var10.getMinU(), (double)var10.getMinV());
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var27, (double)var10.getMinU(), (double)var10.getMaxV());
        }
        else
        {
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var27, (double)var8.getMaxU(), (double)var8.getMaxV());
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var26, (double)var8.getMinU(), (double)var8.getMaxV());
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var26, (double)var8.getMinU(), (double)var8.getMinV());
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var27, (double)var8.getMaxU(), (double)var8.getMinV());
            var5.setColorOpaque_F(var11, var11, var11);
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var27, (double)var10.getMaxU(), (double)var10.getMaxV());
            var5.addVertexWithUV((double)var25, (double)par3 + 0.015625D, (double)var26, (double)var10.getMinU(), (double)var10.getMaxV());
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var26, (double)var10.getMinU(), (double)var10.getMinV());
            var5.addVertexWithUV((double)var24, (double)par3 + 0.015625D, (double)var27, (double)var10.getMaxU(), (double)var10.getMinV());
        }

        if (!renderBlocks.blockAccess.isBlockNormalCube(par2, par3 + 1, par4))
        {
            if (renderBlocks.blockAccess.isBlockNormalCube(par2 - 1, par3, par4) && renderBlocks.blockAccess.getBlockId(par2 - 1, par3 + 1, par4) == Block.redstoneWire.blockID)
            {
                var5.setColorOpaque_F(var11 * var13, var11 * var14, var11 * var15);
                var5.addVertexWithUV((double)par2 + 0.015625D, (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 1), (double)var8.getMaxU(), (double)var8.getMinV());
                var5.addVertexWithUV((double)par2 + 0.015625D, (double)(par3 + 0), (double)(par4 + 1), (double)var8.getMinU(), (double)var8.getMinV());
                var5.addVertexWithUV((double)par2 + 0.015625D, (double)(par3 + 0), (double)(par4 + 0), (double)var8.getMinU(), (double)var8.getMaxV());
                var5.addVertexWithUV((double)par2 + 0.015625D, (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 0), (double)var8.getMaxU(), (double)var8.getMaxV());
                var5.setColorOpaque_F(var11, var11, var11);
                var5.addVertexWithUV((double)par2 + 0.015625D, (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 1), (double)var10.getMaxU(), (double)var10.getMinV());
                var5.addVertexWithUV((double)par2 + 0.015625D, (double)(par3 + 0), (double)(par4 + 1), (double)var10.getMinU(), (double)var10.getMinV());
                var5.addVertexWithUV((double)par2 + 0.015625D, (double)(par3 + 0), (double)(par4 + 0), (double)var10.getMinU(), (double)var10.getMaxV());
                var5.addVertexWithUV((double)par2 + 0.015625D, (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 0), (double)var10.getMaxU(), (double)var10.getMaxV());
            }

            if (renderBlocks.blockAccess.isBlockNormalCube(par2 + 1, par3, par4) && renderBlocks.blockAccess.getBlockId(par2 + 1, par3 + 1, par4) == Block.redstoneWire.blockID)
            {
                var5.setColorOpaque_F(var11 * var13, var11 * var14, var11 * var15);
                var5.addVertexWithUV((double)(par2 + 1) - 0.015625D, (double)(par3 + 0), (double)(par4 + 1), (double)var8.getMinU(), (double)var8.getMaxV());
                var5.addVertexWithUV((double)(par2 + 1) - 0.015625D, (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 1), (double)var8.getMaxU(), (double)var8.getMaxV());
                var5.addVertexWithUV((double)(par2 + 1) - 0.015625D, (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 0), (double)var8.getMaxU(), (double)var8.getMinV());
                var5.addVertexWithUV((double)(par2 + 1) - 0.015625D, (double)(par3 + 0), (double)(par4 + 0), (double)var8.getMinU(), (double)var8.getMinV());
                var5.setColorOpaque_F(var11, var11, var11);
                var5.addVertexWithUV((double)(par2 + 1) - 0.015625D, (double)(par3 + 0), (double)(par4 + 1), (double)var10.getMinU(), (double)var10.getMaxV());
                var5.addVertexWithUV((double)(par2 + 1) - 0.015625D, (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 1), (double)var10.getMaxU(), (double)var10.getMaxV());
                var5.addVertexWithUV((double)(par2 + 1) - 0.015625D, (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 0), (double)var10.getMaxU(), (double)var10.getMinV());
                var5.addVertexWithUV((double)(par2 + 1) - 0.015625D, (double)(par3 + 0), (double)(par4 + 0), (double)var10.getMinU(), (double)var10.getMinV());
            }

            if (renderBlocks.blockAccess.isBlockNormalCube(par2, par3, par4 - 1) && renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4 - 1) == Block.redstoneWire.blockID)
            {
                var5.setColorOpaque_F(var11 * var13, var11 * var14, var11 * var15);
                var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), (double)par4 + 0.015625D, (double)var8.getMinU(), (double)var8.getMaxV());
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 1) + 0.021875F), (double)par4 + 0.015625D, (double)var8.getMaxU(), (double)var8.getMaxV());
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 1) + 0.021875F), (double)par4 + 0.015625D, (double)var8.getMaxU(), (double)var8.getMinV());
                var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), (double)par4 + 0.015625D, (double)var8.getMinU(), (double)var8.getMinV());
                var5.setColorOpaque_F(var11, var11, var11);
                var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), (double)par4 + 0.015625D, (double)var10.getMinU(), (double)var10.getMaxV());
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 1) + 0.021875F), (double)par4 + 0.015625D, (double)var10.getMaxU(), (double)var10.getMaxV());
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 1) + 0.021875F), (double)par4 + 0.015625D, (double)var10.getMaxU(), (double)var10.getMinV());
                var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), (double)par4 + 0.015625D, (double)var10.getMinU(), (double)var10.getMinV());
            }

            if (renderBlocks.blockAccess.isBlockNormalCube(par2, par3, par4 + 1) && renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4 + 1) == Block.redstoneWire.blockID)
            {
                var5.setColorOpaque_F(var11 * var13, var11 * var14, var11 * var15);
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 1) - 0.015625D, (double)var8.getMaxU(), (double)var8.getMinV());
                var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), (double)(par4 + 1) - 0.015625D, (double)var8.getMinU(), (double)var8.getMinV());
                var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), (double)(par4 + 1) - 0.015625D, (double)var8.getMinU(), (double)var8.getMaxV());
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 1) - 0.015625D, (double)var8.getMaxU(), (double)var8.getMaxV());
                var5.setColorOpaque_F(var11, var11, var11);
                var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 1) - 0.015625D, (double)var10.getMaxU(), (double)var10.getMinV());
                var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), (double)(par4 + 1) - 0.015625D, (double)var10.getMinU(), (double)var10.getMinV());
                var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), (double)(par4 + 1) - 0.015625D, (double)var10.getMinU(), (double)var10.getMaxV());
                var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 1) + 0.021875F), (double)(par4 + 1) - 0.015625D, (double)var10.getMaxU(), (double)var10.getMaxV());
            }
        }

        return true;
    }
}
