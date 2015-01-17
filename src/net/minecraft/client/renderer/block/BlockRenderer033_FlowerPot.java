package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

//XXX MAY BE OPTIMIZED?

public class BlockRenderer033_FlowerPot extends BlockRenderer
{
	public static final BlockRenderer renderer = new BlockRenderer033_FlowerPot();
	
	private BlockRenderer033_FlowerPot()
	{
		super(33);
	}
	
	static void nopInit(){}
	
    /**
     * Renders flower pot
     */
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block par1BlockFlowerPot, int par2, int par3, int par4)
    {
        BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, par1BlockFlowerPot, par2, par3, par4);
        Tessellator tess = Tessellator.instance;
        tess.setBrightness(par1BlockFlowerPot.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4));
        float var6 = 1.0F;
        int var7 = par1BlockFlowerPot.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
        Icon var8 = renderBlocks.getBlockIconFromSide(par1BlockFlowerPot, 0);
        float var9 = (float)(var7 >> 16 & 255) / 255.0F;
        float var10 = (float)(var7 >> 8 & 255) / 255.0F;
        float var11 = (float)(var7 & 255) / 255.0F;
        float var12;
        float var14;

        if (EntityRenderer.anaglyphEnable)
        {
            var12 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
            float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
            var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
            var9 = var12;
            var10 = var13;
            var11 = var14;
        }

        tess.setColorOpaque_F(var6 * var9, var6 * var10, var6 * var11);
        var12 = 0.1865F;
        renderBlocks.renderFaceXPos(par1BlockFlowerPot, (double)((float)par2 - 0.5F + var12), (double)par3, (double)par4, var8);
        renderBlocks.renderFaceXNeg(par1BlockFlowerPot, (double)((float)par2 + 0.5F - var12), (double)par3, (double)par4, var8);
        renderBlocks.renderFaceZPos(par1BlockFlowerPot, (double)par2, (double)par3, (double)((float)par4 - 0.5F + var12), var8);
        renderBlocks.renderFaceZNeg(par1BlockFlowerPot, (double)par2, (double)par3, (double)((float)par4 + 0.5F - var12), var8);
        renderBlocks.renderFaceYPos(par1BlockFlowerPot, (double)par2, (double)((float)par3 - 0.5F + var12 + 0.1875F), (double)par4, renderBlocks.getBlockIcon(Block.dirt));
        int metadata = renderBlocks.blockAccess.getBlockMetadata(par2, par3, par4);

        if (metadata != 0)
        {
            var14 = 0.0F;
            float var15 = 4.0F;
            float var16 = 0.0F;
            BlockFlower flower = null;

            switch (metadata)
            {
                case 1:
                    flower = Block.plantRed;
                    break;

                case 2:
                    flower = Block.plantYellow;

                case 3:
                case 4:
                case 5:
                case 6:
                default:
                    break;

                case 7:
                    flower = Block.mushroomRed;
                    break;

                case 8:
                    flower = Block.mushroomBrown;
            }

            tess.addTranslation(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);

            if (flower != null)
            {
                renderBlocks.renderBlockByRenderType(flower, par2, par3, par4);
            }
            else if (metadata == 9)
            {
                renderBlocks.renderAllFaces = true;
                float var18 = 0.125F;
                renderBlocks.setRenderBounds((double)(0.5F - var18), 0.0D, (double)(0.5F - var18), (double)(0.5F + var18), 0.25D, (double)(0.5F + var18));
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, Block.cactus, par2, par3, par4);
                renderBlocks.setRenderBounds((double)(0.5F - var18), 0.25D, (double)(0.5F - var18), (double)(0.5F + var18), 0.5D, (double)(0.5F + var18));
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, Block.cactus, par2, par3, par4);
                renderBlocks.setRenderBounds((double)(0.5F - var18), 0.5D, (double)(0.5F - var18), (double)(0.5F + var18), 0.75D, (double)(0.5F + var18));
                BlockRenderer000_Standard.renderer.onRenderBlock(renderBlocks, Block.cactus, par2, par3, par4);
                renderBlocks.renderAllFaces = false;
                renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (metadata == 3)
            {
                renderBlocks.drawCrossedSquares(Block.sapling, 0, (double)par2, (double)par3, (double)par4, 0.75F);
            }
            else if (metadata == 5)
            {
                renderBlocks.drawCrossedSquares(Block.sapling, 2, (double)par2, (double)par3, (double)par4, 0.75F);
            }
            else if (metadata == 4)
            {
                renderBlocks.drawCrossedSquares(Block.sapling, 1, (double)par2, (double)par3, (double)par4, 0.75F);
            }
            else if (metadata == 6)
            {
                renderBlocks.drawCrossedSquares(Block.sapling, 3, (double)par2, (double)par3, (double)par4, 0.75F);
            }
            else if (metadata == 11)
            {
                var7 = Block.tallGrass.colorMultiplier(renderBlocks.blockAccess, par2, par3, par4);
                var9 = (float)(var7 >> 16 & 255) / 255.0F;
                var10 = (float)(var7 >> 8 & 255) / 255.0F;
                var11 = (float)(var7 & 255) / 255.0F;
                tess.setColorOpaque_F(var6 * var9, var6 * var10, var6 * var11);
                renderBlocks.drawCrossedSquares(Block.tallGrass, 2, (double)par2, (double)par3, (double)par4, 0.75F);
            }
            else if (metadata == 10)
            {
                renderBlocks.drawCrossedSquares(Block.deadBush, 2, (double)par2, (double)par3, (double)par4, 0.75F);
            }

            tess.addTranslation(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
        }

        return true;
    }
}
