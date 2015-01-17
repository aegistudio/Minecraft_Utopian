package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTripWire;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRenderer030_TripWire extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer030_TripWire();

	private BlockRenderer030_TripWire()
	{
		super(30);
	}

	static void nopInit(){}
	
    /**
     * Renders a trip wire block at the given coordinates
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator var5 = Tessellator.instance;
        Icon var6 = renderBlocks.getBlockIconFromSide(par1Block, 0);
        int var7 = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);
        boolean var8 = (var7 & 4) == 4;
        boolean var9 = (var7 & 2) == 2;

        if (renderBlocks.hasOverrideBlockTexture())
        {
            var6 = renderBlocks.overrideBlockTexture;
        }

        var5.setBrightness(par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var10 = par1Block.getBlockBrightness(renderBlocks.blockAccess, par2, par3, par4) * 0.75F;
        var5.setColorOpaque_F(var10, var10, var10);
        double var11 = (double)var6.getMinU();
        double var13 = (double)var6.getInterpolatedV(var8 ? 2.0D : 0.0D);
        double var15 = (double)var6.getMaxU();
        double var17 = (double)var6.getInterpolatedV(var8 ? 4.0D : 2.0D);
        double var19 = (double)(var9 ? 3.5F : 1.5F) / 16.0D;
        boolean var21 = BlockTripWire.func_72148_a(renderBlocks.blockAccess, par2, par3, par4, var7, 1);
        boolean var22 = BlockTripWire.func_72148_a(renderBlocks.blockAccess, par2, par3, par4, var7, 3);
        boolean var23 = BlockTripWire.func_72148_a(renderBlocks.blockAccess, par2, par3, par4, var7, 2);
        boolean var24 = BlockTripWire.func_72148_a(renderBlocks.blockAccess, par2, par3, par4, var7, 0);
        float var25 = 0.03125F;
        float var26 = 0.5F - var25 / 2.0F;
        float var27 = var26 + var25;

        if (!var23 && !var22 && !var24 && !var21)
        {
            var23 = true;
            var24 = true;
        }

        if (var23)
        {
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.25D, var11, var13);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.25D, var11, var17);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4, var15, var17);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4, var15, var13);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4, var15, var13);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4, var15, var17);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.25D, var11, var17);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.25D, var11, var13);
        }

        if (var23 || var24 && !var22 && !var21)
        {
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.5D, var11, var13);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.5D, var11, var17);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.25D, var15, var17);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.25D, var15, var13);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.25D, var15, var13);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.25D, var15, var17);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.5D, var11, var17);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.5D, var11, var13);
        }

        if (var24 || var23 && !var22 && !var21)
        {
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.75D, var11, var13);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.75D, var11, var17);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.5D, var15, var17);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.5D, var15, var13);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.5D, var15, var13);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.5D, var15, var17);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.75D, var11, var17);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.75D, var11, var13);
        }

        if (var24)
        {
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)(par4 + 1), var11, var13);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)(par4 + 1), var11, var17);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.75D, var15, var17);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.75D, var15, var13);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)par4 + 0.75D, var15, var13);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)par4 + 0.75D, var15, var17);
            var5.addVertexWithUV((double)((float)par2 + var27), (double)par3 + var19, (double)(par4 + 1), var11, var17);
            var5.addVertexWithUV((double)((float)par2 + var26), (double)par3 + var19, (double)(par4 + 1), var11, var13);
        }

        if (var21)
        {
            var5.addVertexWithUV((double)par2, (double)par3 + var19, (double)((float)par4 + var27), var11, var17);
            var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var19, (double)((float)par4 + var27), var15, var17);
            var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var19, (double)((float)par4 + var26), var15, var13);
            var5.addVertexWithUV((double)par2, (double)par3 + var19, (double)((float)par4 + var26), var11, var13);
            var5.addVertexWithUV((double)par2, (double)par3 + var19, (double)((float)par4 + var26), var11, var13);
            var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var19, (double)((float)par4 + var26), var15, var13);
            var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var19, (double)((float)par4 + var27), var15, var17);
            var5.addVertexWithUV((double)par2, (double)par3 + var19, (double)((float)par4 + var27), var11, var17);
        }

        if (var21 || var22 && !var23 && !var24)
        {
            var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var19, (double)((float)par4 + var27), var11, var17);
            var5.addVertexWithUV((double)par2 + 0.5D, (double)par3 + var19, (double)((float)par4 + var27), var15, var17);
            var5.addVertexWithUV((double)par2 + 0.5D, (double)par3 + var19, (double)((float)par4 + var26), var15, var13);
            var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var19, (double)((float)par4 + var26), var11, var13);
            var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var19, (double)((float)par4 + var26), var11, var13);
            var5.addVertexWithUV((double)par2 + 0.5D, (double)par3 + var19, (double)((float)par4 + var26), var15, var13);
            var5.addVertexWithUV((double)par2 + 0.5D, (double)par3 + var19, (double)((float)par4 + var27), var15, var17);
            var5.addVertexWithUV((double)par2 + 0.25D, (double)par3 + var19, (double)((float)par4 + var27), var11, var17);
        }

        if (var22 || var21 && !var23 && !var24)
        {
            var5.addVertexWithUV((double)par2 + 0.5D, (double)par3 + var19, (double)((float)par4 + var27), var11, var17);
            var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var19, (double)((float)par4 + var27), var15, var17);
            var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var19, (double)((float)par4 + var26), var15, var13);
            var5.addVertexWithUV((double)par2 + 0.5D, (double)par3 + var19, (double)((float)par4 + var26), var11, var13);
            var5.addVertexWithUV((double)par2 + 0.5D, (double)par3 + var19, (double)((float)par4 + var26), var11, var13);
            var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var19, (double)((float)par4 + var26), var15, var13);
            var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var19, (double)((float)par4 + var27), var15, var17);
            var5.addVertexWithUV((double)par2 + 0.5D, (double)par3 + var19, (double)((float)par4 + var27), var11, var17);
        }

        if (var22)
        {
            var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var19, (double)((float)par4 + var27), var11, var17);
            var5.addVertexWithUV((double)(par2 + 1), (double)par3 + var19, (double)((float)par4 + var27), var15, var17);
            var5.addVertexWithUV((double)(par2 + 1), (double)par3 + var19, (double)((float)par4 + var26), var15, var13);
            var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var19, (double)((float)par4 + var26), var11, var13);
            var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var19, (double)((float)par4 + var26), var11, var13);
            var5.addVertexWithUV((double)(par2 + 1), (double)par3 + var19, (double)((float)par4 + var26), var15, var13);
            var5.addVertexWithUV((double)(par2 + 1), (double)par3 + var19, (double)((float)par4 + var27), var15, var17);
            var5.addVertexWithUV((double)par2 + 0.75D, (double)par3 + var19, (double)((float)par4 + var27), var11, var17);
        }

        return true;
    }
}
