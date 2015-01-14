package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockInfoContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class BlockRendererStandard extends BlockRenderer
{
	public static BlockRenderer renderer = new BlockRendererStandard();
	
	private BlockRendererStandard()
	{
		super(0);
	}
	
	static void nopInit(){}
	
    /**
     * Renders a standard cube block at the given coordinates
     */
	
	@Override
	public boolean onRenderBlock(RenderBlocks renderBlocks, Block block, int x, int y, int z)
	{
		int var5 = block.colorMultiplier(renderBlocks.blockAccess, x, y, z);
        float var6 = (float)(var5 >> 16 & 255) / 255.0F;
        float var7 = (float)(var5 >> 8 & 255) / 255.0F;
        float var8 = (float)(var5 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var9 = (var6 * 30.0F + var7 * 59.0F + var8 * 11.0F) / 100.0F;
            float var10 = (var6 * 30.0F + var7 * 70.0F) / 100.0F;
            float var11 = (var6 * 30.0F + var8 * 70.0F) / 100.0F;
            var6 = var9;
            var7 = var10;
            var8 = var11;
        }
        
        BlockInfoContainer whocallme = BlockInfoContainer.getBlockInfoContainer();
        if(Minecraft.isAmbientOcclusionEnabled() && whocallme.getLookupLightValue(block.blockID) == 0)
        {
        	if(renderBlocks.partialRenderBounds) return this.func_102027_b(renderBlocks, block, x, y, z, var6, var7, var8);
        	else return this.renderStandardBlockWithAmbientOcclusion(renderBlocks, block, x, y, z, var6, var7, var8);
        }
        else return this.renderStandardBlockWithColorMultiplier(renderBlocks, block, x, y, z, var6, var7, var8);
	}
	
    /**
     * Renders a standard cube block at the given coordinates, with a given color ratio.  Args: block, x, y, z, r, g, b
     */
    public boolean renderStandardBlockWithColorMultiplier(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7)
    {
        renderBlocks.enableAO = false;
        Tessellator var8 = Tessellator.instance;
        boolean var9 = false;
        float var10 = 0.5F;
        float var11 = 1.0F;
        float var12 = 0.8F;
        float var13 = 0.6F;
        float var14 = var11 * par5;
        float var15 = var11 * par6;
        float var16 = var11 * par7;
        float var17 = var10;
        float var18 = var12;
        float var19 = var13;
        float var20 = var10;
        float var21 = var12;
        float var22 = var13;
        float var23 = var10;
        float var24 = var12;
        float var25 = var13;

        if (par1Block != Block.grass)
        {
            var17 = var10 * par5;
            var18 = var12 * par5;
            var19 = var13 * par5;
            var20 = var10 * par6;
            var21 = var12 * par6;
            var22 = var13 * par6;
            var23 = var10 * par7;
            var24 = var12 * par7;
            var25 = var13 * par7;
        }

        int var26 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4);

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 - 1, par4, 0))
        {
            var8.setBrightness(renderBlocks.renderMinY > 0.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4));
            var8.setColorOpaque_F(var17, var20, var23);
            renderBlocks.renderFaceYNeg(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 0));
            var9 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 + 1, par4, 1))
        {
            var8.setBrightness(renderBlocks.renderMaxY < 1.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4));
            var8.setColorOpaque_F(var14, var15, var16);
            renderBlocks.renderFaceYPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 1));
            var9 = true;
        }

        Icon var28;

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 - 1, 2))
        {
            var8.setBrightness(renderBlocks.renderMinZ > 0.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1));
            var8.setColorOpaque_F(var18, var21, var24);
            var28 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 2);
            renderBlocks.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, var28);

            if (RenderBlocks.fancyGrass && var28.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                var8.setColorOpaque_F(var18 * par5, var21 * par6, var24 * par7);
                renderBlocks.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var9 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 + 1, 3))
        {
            var8.setBrightness(renderBlocks.renderMaxZ < 1.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1));
            var8.setColorOpaque_F(var18, var21, var24);
            var28 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 3);
            renderBlocks.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, var28);

            if (RenderBlocks.fancyGrass && var28.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                var8.setColorOpaque_F(var18 * par5, var21 * par6, var24 * par7);
                renderBlocks.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var9 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 - 1, par3, par4, 4))
        {
            var8.setBrightness(renderBlocks.renderMinX > 0.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4));
            var8.setColorOpaque_F(var19, var22, var25);
            var28 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 4);
            renderBlocks.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, var28);

            if (RenderBlocks.fancyGrass && var28.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                var8.setColorOpaque_F(var19 * par5, var22 * par6, var25 * par7);
                renderBlocks.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var9 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 + 1, par3, par4, 5))
        {
            var8.setBrightness(renderBlocks.renderMaxX < 1.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4));
            var8.setColorOpaque_F(var19, var22, var25);
            var28 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 5);
            renderBlocks.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, var28);

            if (RenderBlocks.fancyGrass && var28.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                var8.setColorOpaque_F(var19 * par5, var22 * par6, var25 * par7);
                renderBlocks.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var9 = true;
        }

        return var9;
    }
	
	public boolean renderStandardBlockWithAmbientOcclusion(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7)
	{
    	BlockInfoContainer whocallme = BlockInfoContainer.getBlockInfoContainer();
    	
        renderBlocks.enableAO = true;
        boolean var8 = false;
        float var9 = 0.0F;
        float var10 = 0.0F;
        float var11 = 0.0F;
        float var12 = 0.0F;
        boolean var13 = true;
        int var14 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4);
        Tessellator var15 = Tessellator.instance;
        var15.setBrightness(983055);

        if (renderBlocks.getBlockIcon(par1Block).getIconName().equals("grass_top"))
        {
            var13 = false;
        }
        else if (renderBlocks.hasOverrideBlockTexture())
        {
            var13 = false;
        }

        boolean var17;
        boolean var16;
        boolean var19;
        boolean var18;
        float var21;
        int var20;

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 - 1, par4, 0))
        {
            if (renderBlocks.renderMinY <= 0.0D)
            {
                --par3;
            }

            renderBlocks.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoLightValueScratchXYNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoLightValueScratchYZNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoLightValueScratchYZNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoLightValueScratchXYPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3 - 1, par4));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3 - 1, par4));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 - 1, par4 + 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 - 1, par4 - 1));

            if (!var19 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXYNN;
                renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXYNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4 - 1);
                renderBlocks.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4 - 1);
            }

            if (!var18 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXYNN;
                renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXYNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4 + 1);
                renderBlocks.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4 + 1);
            }

            if (!var19 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXYPN;
                renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXYPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4 - 1);
                renderBlocks.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4 - 1);
            }

            if (!var18 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXYPN;
                renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXYPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4 + 1);
                renderBlocks.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4 + 1);
            }

            if (renderBlocks.renderMinY <= 0.0D)
            {
                ++par3;
            }

            var20 = var14;

            if (renderBlocks.renderMinY <= 0.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2, par3 - 1, par4))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4);
            var9 = (renderBlocks.aoLightValueScratchXYZNNP + renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchYZNP + var21) / 4.0F;
            var12 = (renderBlocks.aoLightValueScratchYZNP + var21 + renderBlocks.aoLightValueScratchXYZPNP + renderBlocks.aoLightValueScratchXYPN) / 4.0F;
            var11 = (var21 + renderBlocks.aoLightValueScratchYZNN + renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXYZPNN) / 4.0F;
            var10 = (renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXYZNNN + var21 + renderBlocks.aoLightValueScratchYZNN) / 4.0F;
            renderBlocks.brightnessTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessYZNP, var20);
            renderBlocks.brightnessTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNP, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXYPN, var20);
            renderBlocks.brightnessBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNN, renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXYZPNN, var20);
            renderBlocks.brightnessBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessYZNN, var20);

            if (var13)
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5 * 0.5F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6 * 0.5F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7 * 0.5F;
            }
            else
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = 0.5F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = 0.5F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = 0.5F;
            }

            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            renderBlocks.renderFaceYNeg(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 0));
            var8 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 + 1, par4, 1))
        {
            if (renderBlocks.renderMaxY >= 1.0D)
            {
                ++par3;
            }

            renderBlocks.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoLightValueScratchXYNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoLightValueScratchXYPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoLightValueScratchYZPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoLightValueScratchYZPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 + 1);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3 + 1, par4));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3 + 1, par4));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4 + 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4 - 1));

            if (!var19 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXYNP;
                renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXYNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4 - 1);
                renderBlocks.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4 - 1);
            }

            if (!var19 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXYPP;
                renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXYPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4 - 1);
                renderBlocks.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4 - 1);
            }

            if (!var18 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXYNP;
                renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXYNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4 + 1);
                renderBlocks.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4 + 1);
            }

            if (!var18 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXYPP;
                renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXYPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4 + 1);
                renderBlocks.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4 + 1);
            }

            if (renderBlocks.renderMaxY >= 1.0D)
            {
                --par3;
            }

            var20 = var14;

            if (renderBlocks.renderMaxY >= 1.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2, par3 + 1, par4))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var12 = (renderBlocks.aoLightValueScratchXYZNPP + renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchYZPP + var21) / 4.0F;
            var9 = (renderBlocks.aoLightValueScratchYZPP + var21 + renderBlocks.aoLightValueScratchXYZPPP + renderBlocks.aoLightValueScratchXYPP) / 4.0F;
            var10 = (var21 + renderBlocks.aoLightValueScratchYZPN + renderBlocks.aoLightValueScratchXYPP + renderBlocks.aoLightValueScratchXYZPPN) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchXYZNPN + var21 + renderBlocks.aoLightValueScratchYZPN) / 4.0F;
            renderBlocks.brightnessTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNPP, renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessYZPP, var20);
            renderBlocks.brightnessTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPP, renderBlocks.aoBrightnessXYZPPP, renderBlocks.aoBrightnessXYPP, var20);
            renderBlocks.brightnessBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPN, renderBlocks.aoBrightnessXYPP, renderBlocks.aoBrightnessXYZPPN, var20);
            renderBlocks.brightnessBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessYZPN, var20);
            renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5;
            renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6;
            renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7;
            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            renderBlocks.renderFaceYPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 1));
            var8 = true;
        }

        Icon var22;

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 - 1, 2))
        {
            if (renderBlocks.renderMinZ <= 0.0D)
            {
                --par4;
            }

            renderBlocks.aoLightValueScratchXZNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoLightValueScratchYZNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoLightValueScratchYZPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoLightValueScratchXZPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3, par4 - 1));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3, par4 - 1));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4 - 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 - 1, par4 - 1));

            if (!var17 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXZNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4);
                renderBlocks.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4);
            }

            if (!var17 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXZNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4);
                renderBlocks.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4);
            }

            if (!var16 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXZPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4);
                renderBlocks.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4);
            }

            if (!var16 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXZPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4);
                renderBlocks.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4);
            }

            if (renderBlocks.renderMinZ <= 0.0D)
            {
                ++par4;
            }

            var20 = var14;

            if (renderBlocks.renderMinZ <= 0.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2, par3, par4 - 1))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 - 1);
            var9 = (renderBlocks.aoLightValueScratchXZNN + renderBlocks.aoLightValueScratchXYZNPN + var21 + renderBlocks.aoLightValueScratchYZPN) / 4.0F;
            var10 = (var21 + renderBlocks.aoLightValueScratchYZPN + renderBlocks.aoLightValueScratchXZPN + renderBlocks.aoLightValueScratchXYZPPN) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchYZNN + var21 + renderBlocks.aoLightValueScratchXYZPNN + renderBlocks.aoLightValueScratchXZPN) / 4.0F;
            var12 = (renderBlocks.aoLightValueScratchXYZNNN + renderBlocks.aoLightValueScratchXZNN + renderBlocks.aoLightValueScratchYZNN + var21) / 4.0F;
            renderBlocks.brightnessTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessYZPN, var20);
            renderBlocks.brightnessBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPN, renderBlocks.aoBrightnessXZPN, renderBlocks.aoBrightnessXYZPPN, var20);
            renderBlocks.brightnessBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNN, renderBlocks.aoBrightnessXYZPNN, renderBlocks.aoBrightnessXZPN, var20);
            renderBlocks.brightnessTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessYZNN, var20);

            if (var13)
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5 * 0.8F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6 * 0.8F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7 * 0.8F;
            }
            else
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = 0.8F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = 0.8F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = 0.8F;
            }

            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            var22 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 2);
            renderBlocks.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, var22);

            if (RenderBlocks.fancyGrass && var22.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 + 1, 3))
        {
            if (renderBlocks.renderMaxZ >= 1.0D)
            {
                ++par4;
            }

            renderBlocks.aoLightValueScratchXZNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoLightValueScratchXZPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoLightValueScratchYZNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoLightValueScratchYZPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3, par4 + 1));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3, par4 + 1));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4 + 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 - 1, par4 + 1));

            if (!var17 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXZNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4);
                renderBlocks.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4);
            }

            if (!var17 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXZNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4);
                renderBlocks.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4);
            }

            if (!var16 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXZPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4);
                renderBlocks.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4);
            }

            if (!var16 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXZPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4);
                renderBlocks.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4);
            }

            if (renderBlocks.renderMaxZ >= 1.0D)
            {
                --par4;
            }

            var20 = var14;

            if (renderBlocks.renderMaxZ >= 1.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2, par3, par4 + 1))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 + 1);
            var9 = (renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchXYZNPP + var21 + renderBlocks.aoLightValueScratchYZPP) / 4.0F;
            var12 = (var21 + renderBlocks.aoLightValueScratchYZPP + renderBlocks.aoLightValueScratchXZPP + renderBlocks.aoLightValueScratchXYZPPP) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchYZNP + var21 + renderBlocks.aoLightValueScratchXYZPNP + renderBlocks.aoLightValueScratchXZPP) / 4.0F;
            var10 = (renderBlocks.aoLightValueScratchXYZNNP + renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchYZNP + var21) / 4.0F;
            renderBlocks.brightnessTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessXYZNPP, renderBlocks.aoBrightnessYZPP, var20);
            renderBlocks.brightnessTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPP, renderBlocks.aoBrightnessXZPP, renderBlocks.aoBrightnessXYZPPP, var20);
            renderBlocks.brightnessBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNP, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXZPP, var20);
            renderBlocks.brightnessBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessYZNP, var20);

            if (var13)
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5 * 0.8F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6 * 0.8F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7 * 0.8F;
            }
            else
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = 0.8F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = 0.8F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = 0.8F;
            }

            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            var22 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 3);
            renderBlocks.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 3));

            if (RenderBlocks.fancyGrass && var22.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 - 1, par3, par4, 4))
        {
            if (renderBlocks.renderMinX <= 0.0D)
            {
                --par2;
            }

            renderBlocks.aoLightValueScratchXYNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoLightValueScratchXZNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoLightValueScratchXZNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoLightValueScratchXYNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3 + 1, par4));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3 - 1, par4));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3, par4 - 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3, par4 + 1));

            if (!var18 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXZNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1);
                renderBlocks.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1);
            }

            if (!var19 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXZNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1);
                renderBlocks.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1);
            }

            if (!var18 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXZNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1);
                renderBlocks.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1);
            }

            if (!var19 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXZNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1);
                renderBlocks.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1);
            }

            if (renderBlocks.renderMinX <= 0.0D)
            {
                ++par2;
            }

            var20 = var14;

            if (renderBlocks.renderMinX <= 0.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2 - 1, par3, par4))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4);
            var12 = (renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXYZNNP + var21 + renderBlocks.aoLightValueScratchXZNP) / 4.0F;
            var9 = (var21 + renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchXYZNPP) / 4.0F;
            var10 = (renderBlocks.aoLightValueScratchXZNN + var21 + renderBlocks.aoLightValueScratchXYZNPN + renderBlocks.aoLightValueScratchXYNP) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchXYZNNN + renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXZNN + var21) / 4.0F;
            renderBlocks.brightnessTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXZNP, var20);
            renderBlocks.brightnessTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessXYZNPP, var20);
            renderBlocks.brightnessBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessXYNP, var20);
            renderBlocks.brightnessBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXZNN, var20);

            if (var13)
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5 * 0.6F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6 * 0.6F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7 * 0.6F;
            }
            else
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = 0.6F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = 0.6F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = 0.6F;
            }

            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            var22 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 4);
            renderBlocks.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, var22);

            if (RenderBlocks.fancyGrass && var22.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 + 1, par3, par4, 5))
        {
            if (renderBlocks.renderMaxX >= 1.0D)
            {
                ++par2;
            }

            renderBlocks.aoLightValueScratchXYPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoLightValueScratchXZPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoLightValueScratchXZPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoLightValueScratchXYPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3 + 1, par4));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3 - 1, par4));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3, par4 + 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3, par4 - 1));

            if (!var17 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXZPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1);
                renderBlocks.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1);
            }

            if (!var17 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXZPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1);
                renderBlocks.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1);
            }

            if (!var16 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXZPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1);
                renderBlocks.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1);
            }

            if (!var16 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXZPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1);
                renderBlocks.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1);
            }

            if (renderBlocks.renderMaxX >= 1.0D)
            {
                --par2;
            }

            var20 = var14;

            if (renderBlocks.renderMaxX >= 1.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2 + 1, par3, par4))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4);
            var9 = (renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXYZPNP + var21 + renderBlocks.aoLightValueScratchXZPP) / 4.0F;
            var10 = (renderBlocks.aoLightValueScratchXYZPNN + renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXZPN + var21) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchXZPN + var21 + renderBlocks.aoLightValueScratchXYZPPN + renderBlocks.aoLightValueScratchXYPP) / 4.0F;
            var12 = (var21 + renderBlocks.aoLightValueScratchXZPP + renderBlocks.aoLightValueScratchXYPP + renderBlocks.aoLightValueScratchXYZPPP) / 4.0F;
            renderBlocks.brightnessTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXZPP, var20);
            renderBlocks.brightnessTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZPP, renderBlocks.aoBrightnessXYPP, renderBlocks.aoBrightnessXYZPPP, var20);
            renderBlocks.brightnessBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZPN, renderBlocks.aoBrightnessXYZPPN, renderBlocks.aoBrightnessXYPP, var20);
            renderBlocks.brightnessBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZPNN, renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXZPN, var20);

            if (var13)
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5 * 0.6F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6 * 0.6F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7 * 0.6F;
            }
            else
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = 0.6F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = 0.6F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = 0.6F;
            }

            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            var22 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 5);
            renderBlocks.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, var22);

            if (RenderBlocks.fancyGrass && var22.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        renderBlocks.enableAO = false;
        return var8;
	}

    public boolean func_102027_b(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7)
    {
    	BlockInfoContainer whocallme = BlockInfoContainer.getBlockInfoContainer();
    	
        renderBlocks.enableAO = true;
        boolean var8 = false;
        float var9 = 0.0F;
        float var10 = 0.0F;
        float var11 = 0.0F;
        float var12 = 0.0F;
        boolean var13 = true;
        int var14 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4);
        Tessellator var15 = Tessellator.instance;
        var15.setBrightness(983055);

        if (renderBlocks.getBlockIcon(par1Block).getIconName().equals("grass_top"))
        {
            var13 = false;
        }
        else if (renderBlocks.hasOverrideBlockTexture())
        {
            var13 = false;
        }

        boolean var17;
        boolean var16;
        boolean var19;
        boolean var18;
        float var21;
        int var20;

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 - 1, par4, 0))
        {
            if (renderBlocks.renderMinY <= 0.0D)
            {
                --par3;
            }

            renderBlocks.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoLightValueScratchXYNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoLightValueScratchYZNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoLightValueScratchYZNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoLightValueScratchXYPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3 - 1, par4));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3 - 1, par4));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 - 1, par4 + 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 - 1, par4 - 1));

            if (!var19 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXYNN;
                renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXYNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4 - 1);
                renderBlocks.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4 - 1);
            }

            if (!var18 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXYNN;
                renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXYNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4 + 1);
                renderBlocks.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4 + 1);
            }

            if (!var19 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXYPN;
                renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXYPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4 - 1);
                renderBlocks.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4 - 1);
            }

            if (!var18 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXYPN;
                renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXYPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4 + 1);
                renderBlocks.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4 + 1);
            }

            if (renderBlocks.renderMinY <= 0.0D)
            {
                ++par3;
            }

            var20 = var14;

            if (renderBlocks.renderMinY <= 0.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2, par3 - 1, par4))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4);
            var9 = (renderBlocks.aoLightValueScratchXYZNNP + renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchYZNP + var21) / 4.0F;
            var12 = (renderBlocks.aoLightValueScratchYZNP + var21 + renderBlocks.aoLightValueScratchXYZPNP + renderBlocks.aoLightValueScratchXYPN) / 4.0F;
            var11 = (var21 + renderBlocks.aoLightValueScratchYZNN + renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXYZPNN) / 4.0F;
            var10 = (renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXYZNNN + var21 + renderBlocks.aoLightValueScratchYZNN) / 4.0F;
            renderBlocks.brightnessTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessYZNP, var20);
            renderBlocks.brightnessTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNP, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXYPN, var20);
            renderBlocks.brightnessBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNN, renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXYZPNN, var20);
            renderBlocks.brightnessBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessYZNN, var20);

            if (var13)
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5 * 0.5F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6 * 0.5F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7 * 0.5F;
            }
            else
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = 0.5F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = 0.5F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = 0.5F;
            }

            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            renderBlocks.renderFaceYNeg(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 0));
            var8 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 + 1, par4, 1))
        {
            if (renderBlocks.renderMaxY >= 1.0D)
            {
                ++par3;
            }

            renderBlocks.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoLightValueScratchXYNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoLightValueScratchXYPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoLightValueScratchYZPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoLightValueScratchYZPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 + 1);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3 + 1, par4));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3 + 1, par4));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4 + 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4 - 1));

            if (!var19 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXYNP;
                renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXYNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4 - 1);
                renderBlocks.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4 - 1);
            }

            if (!var19 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXYPP;
                renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXYPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4 - 1);
                renderBlocks.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4 - 1);
            }

            if (!var18 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXYNP;
                renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXYNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4 + 1);
                renderBlocks.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4 + 1);
            }

            if (!var18 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXYPP;
                renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXYPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4 + 1);
                renderBlocks.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4 + 1);
            }

            if (renderBlocks.renderMaxY >= 1.0D)
            {
                --par3;
            }

            var20 = var14;

            if (renderBlocks.renderMaxY >= 1.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2, par3 + 1, par4))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var12 = (renderBlocks.aoLightValueScratchXYZNPP + renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchYZPP + var21) / 4.0F;
            var9 = (renderBlocks.aoLightValueScratchYZPP + var21 + renderBlocks.aoLightValueScratchXYZPPP + renderBlocks.aoLightValueScratchXYPP) / 4.0F;
            var10 = (var21 + renderBlocks.aoLightValueScratchYZPN + renderBlocks.aoLightValueScratchXYPP + renderBlocks.aoLightValueScratchXYZPPN) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchXYZNPN + var21 + renderBlocks.aoLightValueScratchYZPN) / 4.0F;
            renderBlocks.brightnessTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNPP, renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessYZPP, var20);
            renderBlocks.brightnessTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPP, renderBlocks.aoBrightnessXYZPPP, renderBlocks.aoBrightnessXYPP, var20);
            renderBlocks.brightnessBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPN, renderBlocks.aoBrightnessXYPP, renderBlocks.aoBrightnessXYZPPN, var20);
            renderBlocks.brightnessBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessYZPN, var20);
            renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5;
            renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6;
            renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7;
            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            renderBlocks.renderFaceYPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 1));
            var8 = true;
        }

        float var23;
        float var22;
        float var25;
        float var24;
        int var27;
        int var26;
        int var29;
        int var28;
        Icon var30;

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 - 1, 2))
        {
            if (renderBlocks.renderMinZ <= 0.0D)
            {
                --par4;
            }

            renderBlocks.aoLightValueScratchXZNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoLightValueScratchYZNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoLightValueScratchYZPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoLightValueScratchXZPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3, par4 - 1));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3, par4 - 1));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4 - 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 - 1, par4 - 1));

            if (!var17 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXZNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4);
                renderBlocks.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4);
            }

            if (!var17 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXZNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4);
                renderBlocks.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4);
            }

            if (!var16 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXZPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4);
                renderBlocks.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4);
            }

            if (!var16 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXZPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4);
                renderBlocks.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4);
            }

            if (renderBlocks.renderMinZ <= 0.0D)
            {
                ++par4;
            }

            var20 = var14;

            if (renderBlocks.renderMinZ <= 0.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2, par3, par4 - 1))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 - 1);
            var22 = (renderBlocks.aoLightValueScratchXZNN + renderBlocks.aoLightValueScratchXYZNPN + var21 + renderBlocks.aoLightValueScratchYZPN) / 4.0F;
            var23 = (var21 + renderBlocks.aoLightValueScratchYZPN + renderBlocks.aoLightValueScratchXZPN + renderBlocks.aoLightValueScratchXYZPPN) / 4.0F;
            var24 = (renderBlocks.aoLightValueScratchYZNN + var21 + renderBlocks.aoLightValueScratchXYZPNN + renderBlocks.aoLightValueScratchXZPN) / 4.0F;
            var25 = (renderBlocks.aoLightValueScratchXYZNNN + renderBlocks.aoLightValueScratchXZNN + renderBlocks.aoLightValueScratchYZNN + var21) / 4.0F;
            var9 = (float)((double)var22 * renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMinX) + (double)var23 * renderBlocks.renderMinY * renderBlocks.renderMinX + (double)var24 * (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMinX + (double)var25 * (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMinX));
            var10 = (float)((double)var22 * renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMaxX) + (double)var23 * renderBlocks.renderMaxY * renderBlocks.renderMaxX + (double)var24 * (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMaxX + (double)var25 * (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMaxX));
            var11 = (float)((double)var22 * renderBlocks.renderMinY * (1.0D - renderBlocks.renderMaxX) + (double)var23 * renderBlocks.renderMinY * renderBlocks.renderMaxX + (double)var24 * (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMaxX + (double)var25 * (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMaxX));
            var12 = (float)((double)var22 * renderBlocks.renderMinY * (1.0D - renderBlocks.renderMinX) + (double)var23 * renderBlocks.renderMinY * renderBlocks.renderMinX + (double)var24 * (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMinX + (double)var25 * (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMinX));
            var26 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessYZPN, var20);
            var27 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPN, renderBlocks.aoBrightnessXZPN, renderBlocks.aoBrightnessXYZPPN, var20);
            var28 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNN, renderBlocks.aoBrightnessXYZPNN, renderBlocks.aoBrightnessXZPN, var20);
            var29 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessYZNN, var20);
            renderBlocks.brightnessTopLeft = renderBlocks.mixAoBrightness(var26, var27, var28, var29, renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMinX), renderBlocks.renderMaxY * renderBlocks.renderMinX, (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMinX, (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMinX));
            renderBlocks.brightnessBottomLeft = renderBlocks.mixAoBrightness(var26, var27, var28, var29, renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMaxX), renderBlocks.renderMaxY * renderBlocks.renderMaxX, (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMaxX, (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMaxX));
            renderBlocks.brightnessBottomRight = renderBlocks.mixAoBrightness(var26, var27, var28, var29, renderBlocks.renderMinY * (1.0D - renderBlocks.renderMaxX), renderBlocks.renderMinY * renderBlocks.renderMaxX, (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMaxX, (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMaxX));
            renderBlocks.brightnessTopRight = renderBlocks.mixAoBrightness(var26, var27, var28, var29, renderBlocks.renderMinY * (1.0D - renderBlocks.renderMinX), renderBlocks.renderMinY * renderBlocks.renderMinX, (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMinX, (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMinX));

            if (var13)
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5 * 0.8F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6 * 0.8F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7 * 0.8F;
            }
            else
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = 0.8F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = 0.8F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = 0.8F;
            }

            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            var30 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 2);
            renderBlocks.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, var30);

            if (RenderBlocks.fancyGrass && var30.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 + 1, 3))
        {
            if (renderBlocks.renderMaxZ >= 1.0D)
            {
                ++par4;
            }

            renderBlocks.aoLightValueScratchXZNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoLightValueScratchXZPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoLightValueScratchYZNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoLightValueScratchYZPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3, par4 + 1));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3, par4 + 1));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 + 1, par4 + 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2, par3 - 1, par4 + 1));

            if (!var17 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXZNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4);
                renderBlocks.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4);
            }

            if (!var17 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXZNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4);
                renderBlocks.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4);
            }

            if (!var16 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXZPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4);
                renderBlocks.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4);
            }

            if (!var16 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXZPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4);
                renderBlocks.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4);
            }

            if (renderBlocks.renderMaxZ >= 1.0D)
            {
                --par4;
            }

            var20 = var14;

            if (renderBlocks.renderMaxZ >= 1.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2, par3, par4 + 1))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 + 1);
            var22 = (renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchXYZNPP + var21 + renderBlocks.aoLightValueScratchYZPP) / 4.0F;
            var23 = (var21 + renderBlocks.aoLightValueScratchYZPP + renderBlocks.aoLightValueScratchXZPP + renderBlocks.aoLightValueScratchXYZPPP) / 4.0F;
            var24 = (renderBlocks.aoLightValueScratchYZNP + var21 + renderBlocks.aoLightValueScratchXYZPNP + renderBlocks.aoLightValueScratchXZPP) / 4.0F;
            var25 = (renderBlocks.aoLightValueScratchXYZNNP + renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchYZNP + var21) / 4.0F;
            var9 = (float)((double)var22 * renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMinX) + (double)var23 * renderBlocks.renderMaxY * renderBlocks.renderMinX + (double)var24 * (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMinX + (double)var25 * (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMinX));
            var10 = (float)((double)var22 * renderBlocks.renderMinY * (1.0D - renderBlocks.renderMinX) + (double)var23 * renderBlocks.renderMinY * renderBlocks.renderMinX + (double)var24 * (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMinX + (double)var25 * (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMinX));
            var11 = (float)((double)var22 * renderBlocks.renderMinY * (1.0D - renderBlocks.renderMaxX) + (double)var23 * renderBlocks.renderMinY * renderBlocks.renderMaxX + (double)var24 * (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMaxX + (double)var25 * (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMaxX));
            var12 = (float)((double)var22 * renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMaxX) + (double)var23 * renderBlocks.renderMaxY * renderBlocks.renderMaxX + (double)var24 * (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMaxX + (double)var25 * (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMaxX));
            var26 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessXYZNPP, renderBlocks.aoBrightnessYZPP, var20);
            var27 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPP, renderBlocks.aoBrightnessXZPP, renderBlocks.aoBrightnessXYZPPP, var20);
            var28 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNP, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXZPP, var20);
            var29 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessYZNP, var20);
            renderBlocks.brightnessTopLeft = renderBlocks.mixAoBrightness(var26, var29, var28, var27, renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMinX), (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMinX), (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMinX, renderBlocks.renderMaxY * renderBlocks.renderMinX);
            renderBlocks.brightnessBottomLeft = renderBlocks.mixAoBrightness(var26, var29, var28, var27, renderBlocks.renderMinY * (1.0D - renderBlocks.renderMinX), (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMinX), (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMinX, renderBlocks.renderMinY * renderBlocks.renderMinX);
            renderBlocks.brightnessBottomRight = renderBlocks.mixAoBrightness(var26, var29, var28, var27, renderBlocks.renderMinY * (1.0D - renderBlocks.renderMaxX), (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMaxX), (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMaxX, renderBlocks.renderMinY * renderBlocks.renderMaxX);
            renderBlocks.brightnessTopRight = renderBlocks.mixAoBrightness(var26, var29, var28, var27, renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMaxX), (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMaxX), (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMaxX, renderBlocks.renderMaxY * renderBlocks.renderMaxX);

            if (var13)
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5 * 0.8F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6 * 0.8F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7 * 0.8F;
            }
            else
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = 0.8F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = 0.8F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = 0.8F;
            }

            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            var30 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 3);
            renderBlocks.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 3));

            if (RenderBlocks.fancyGrass && var30.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 - 1, par3, par4, 4))
        {
            if (renderBlocks.renderMinX <= 0.0D)
            {
                --par2;
            }

            renderBlocks.aoLightValueScratchXYNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoLightValueScratchXZNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoLightValueScratchXZNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoLightValueScratchXYNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3 + 1, par4));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3 - 1, par4));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3, par4 - 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 - 1, par3, par4 + 1));

            if (!var18 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXZNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1);
                renderBlocks.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1);
            }

            if (!var19 && !var17)
            {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXZNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1);
                renderBlocks.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1);
            }

            if (!var18 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXZNN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1);
                renderBlocks.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1);
            }

            if (!var19 && !var16)
            {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXZNP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1);
                renderBlocks.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1);
            }

            if (renderBlocks.renderMinX <= 0.0D)
            {
                ++par2;
            }

            var20 = var14;

            if (renderBlocks.renderMinX <= 0.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2 - 1, par3, par4))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 - 1, par3, par4);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 - 1, par3, par4);
            var22 = (renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXYZNNP + var21 + renderBlocks.aoLightValueScratchXZNP) / 4.0F;
            var23 = (var21 + renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchXYZNPP) / 4.0F;
            var24 = (renderBlocks.aoLightValueScratchXZNN + var21 + renderBlocks.aoLightValueScratchXYZNPN + renderBlocks.aoLightValueScratchXYNP) / 4.0F;
            var25 = (renderBlocks.aoLightValueScratchXYZNNN + renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXZNN + var21) / 4.0F;
            var9 = (float)((double)var23 * renderBlocks.renderMaxY * renderBlocks.renderMaxZ + (double)var24 * renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMaxZ) + (double)var25 * (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMaxZ) + (double)var22 * (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMaxZ);
            var10 = (float)((double)var23 * renderBlocks.renderMaxY * renderBlocks.renderMinZ + (double)var24 * renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMinZ) + (double)var25 * (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMinZ) + (double)var22 * (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMinZ);
            var11 = (float)((double)var23 * renderBlocks.renderMinY * renderBlocks.renderMinZ + (double)var24 * renderBlocks.renderMinY * (1.0D - renderBlocks.renderMinZ) + (double)var25 * (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMinZ) + (double)var22 * (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMinZ);
            var12 = (float)((double)var23 * renderBlocks.renderMinY * renderBlocks.renderMaxZ + (double)var24 * renderBlocks.renderMinY * (1.0D - renderBlocks.renderMaxZ) + (double)var25 * (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMaxZ) + (double)var22 * (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMaxZ);
            var26 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXZNP, var20);
            var27 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessXYZNPP, var20);
            var28 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessXYNP, var20);
            var29 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXZNN, var20);
            renderBlocks.brightnessTopLeft = renderBlocks.mixAoBrightness(var27, var28, var29, var26, renderBlocks.renderMaxY * renderBlocks.renderMaxZ, renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMaxZ), (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMaxZ), (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMaxZ);
            renderBlocks.brightnessBottomLeft = renderBlocks.mixAoBrightness(var27, var28, var29, var26, renderBlocks.renderMaxY * renderBlocks.renderMinZ, renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMinZ), (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMinZ), (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMinZ);
            renderBlocks.brightnessBottomRight = renderBlocks.mixAoBrightness(var27, var28, var29, var26, renderBlocks.renderMinY * renderBlocks.renderMinZ, renderBlocks.renderMinY * (1.0D - renderBlocks.renderMinZ), (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMinZ), (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMinZ);
            renderBlocks.brightnessTopRight = renderBlocks.mixAoBrightness(var27, var28, var29, var26, renderBlocks.renderMinY * renderBlocks.renderMaxZ, renderBlocks.renderMinY * (1.0D - renderBlocks.renderMaxZ), (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMaxZ), (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMaxZ);

            if (var13)
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5 * 0.6F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6 * 0.6F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7 * 0.6F;
            }
            else
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = 0.6F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = 0.6F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = 0.6F;
            }

            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            var30 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 4);
            renderBlocks.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, var30);

            if (RenderBlocks.fancyGrass && var30.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 + 1, par3, par4, 5))
        {
            if (renderBlocks.renderMaxX >= 1.0D)
            {
                ++par2;
            }

            renderBlocks.aoLightValueScratchXYPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoLightValueScratchXZPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoLightValueScratchXZPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoLightValueScratchXYPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var16 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3 + 1, par4));
            var17 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3 - 1, par4));
            var18 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3, par4 + 1));
            var19 = whocallme.getCanBlockGrass(renderBlocks.blockAccess.getBlockId(par2 + 1, par3, par4 - 1));

            if (!var17 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXZPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1);
                renderBlocks.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1);
            }

            if (!var17 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXZPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1);
                renderBlocks.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1);
            }

            if (!var16 && !var19)
            {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXZPN;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1);
                renderBlocks.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1);
            }

            if (!var16 && !var18)
            {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXZPP;
            }
            else
            {
                renderBlocks.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1);
                renderBlocks.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1);
            }

            if (renderBlocks.renderMaxX >= 1.0D)
            {
                --par2;
            }

            var20 = var14;

            if (renderBlocks.renderMaxX >= 1.0D || !renderBlocks.blockAccess.isBlockOpaqueCube(par2 + 1, par3, par4))
            {
                var20 = par1Block.getMixedBrightnessForBlock(renderBlocks.blockAccess, par2 + 1, par3, par4);
            }

            var21 = par1Block.getAmbientOcclusionLightValue(renderBlocks.blockAccess, par2 + 1, par3, par4);
            var22 = (renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXYZPNP + var21 + renderBlocks.aoLightValueScratchXZPP) / 4.0F;
            var23 = (renderBlocks.aoLightValueScratchXYZPNN + renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXZPN + var21) / 4.0F;
            var24 = (renderBlocks.aoLightValueScratchXZPN + var21 + renderBlocks.aoLightValueScratchXYZPPN + renderBlocks.aoLightValueScratchXYPP) / 4.0F;
            var25 = (var21 + renderBlocks.aoLightValueScratchXZPP + renderBlocks.aoLightValueScratchXYPP + renderBlocks.aoLightValueScratchXYZPPP) / 4.0F;
            var9 = (float)((double)var22 * (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMaxZ + (double)var23 * (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMaxZ) + (double)var24 * renderBlocks.renderMinY * (1.0D - renderBlocks.renderMaxZ) + (double)var25 * renderBlocks.renderMinY * renderBlocks.renderMaxZ);
            var10 = (float)((double)var22 * (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMinZ + (double)var23 * (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMinZ) + (double)var24 * renderBlocks.renderMinY * (1.0D - renderBlocks.renderMinZ) + (double)var25 * renderBlocks.renderMinY * renderBlocks.renderMinZ);
            var11 = (float)((double)var22 * (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMinZ + (double)var23 * (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMinZ) + (double)var24 * renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMinZ) + (double)var25 * renderBlocks.renderMaxY * renderBlocks.renderMinZ);
            var12 = (float)((double)var22 * (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMaxZ + (double)var23 * (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMaxZ) + (double)var24 * renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMaxZ) + (double)var25 * renderBlocks.renderMaxY * renderBlocks.renderMaxZ);
            var26 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXZPP, var20);
            var27 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZPP, renderBlocks.aoBrightnessXYPP, renderBlocks.aoBrightnessXYZPPP, var20);
            var28 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZPN, renderBlocks.aoBrightnessXYZPPN, renderBlocks.aoBrightnessXYPP, var20);
            var29 = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZPNN, renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXZPN, var20);
            renderBlocks.brightnessTopLeft = renderBlocks.mixAoBrightness(var26, var29, var28, var27, (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMaxZ, (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMaxZ), renderBlocks.renderMinY * (1.0D - renderBlocks.renderMaxZ), renderBlocks.renderMinY * renderBlocks.renderMaxZ);
            renderBlocks.brightnessBottomLeft = renderBlocks.mixAoBrightness(var26, var29, var28, var27, (1.0D - renderBlocks.renderMinY) * renderBlocks.renderMinZ, (1.0D - renderBlocks.renderMinY) * (1.0D - renderBlocks.renderMinZ), renderBlocks.renderMinY * (1.0D - renderBlocks.renderMinZ), renderBlocks.renderMinY * renderBlocks.renderMinZ);
            renderBlocks.brightnessBottomRight = renderBlocks.mixAoBrightness(var26, var29, var28, var27, (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMinZ, (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMinZ), renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMinZ), renderBlocks.renderMaxY * renderBlocks.renderMinZ);
            renderBlocks.brightnessTopRight = renderBlocks.mixAoBrightness(var26, var29, var28, var27, (1.0D - renderBlocks.renderMaxY) * renderBlocks.renderMaxZ, (1.0D - renderBlocks.renderMaxY) * (1.0D - renderBlocks.renderMaxZ), renderBlocks.renderMaxY * (1.0D - renderBlocks.renderMaxZ), renderBlocks.renderMaxY * renderBlocks.renderMaxZ);

            if (var13)
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = par5 * 0.6F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = par6 * 0.6F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = par7 * 0.6F;
            }
            else
            {
                renderBlocks.colorRedTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorRedTopRight = 0.6F;
                renderBlocks.colorGreenTopLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorGreenBottomRight = renderBlocks.colorGreenTopRight = 0.6F;
                renderBlocks.colorBlueTopLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorBlueBottomRight = renderBlocks.colorBlueTopRight = 0.6F;
            }

            renderBlocks.colorRedTopLeft *= var9;
            renderBlocks.colorGreenTopLeft *= var9;
            renderBlocks.colorBlueTopLeft *= var9;
            renderBlocks.colorRedBottomLeft *= var10;
            renderBlocks.colorGreenBottomLeft *= var10;
            renderBlocks.colorBlueBottomLeft *= var10;
            renderBlocks.colorRedBottomRight *= var11;
            renderBlocks.colorGreenBottomRight *= var11;
            renderBlocks.colorBlueBottomRight *= var11;
            renderBlocks.colorRedTopRight *= var12;
            renderBlocks.colorGreenTopRight *= var12;
            renderBlocks.colorBlueTopRight *= var12;
            var30 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 5);
            renderBlocks.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, var30);

            if (RenderBlocks.fancyGrass && var30.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture())
            {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        renderBlocks.enableAO = false;
        return var8;
    }
}
