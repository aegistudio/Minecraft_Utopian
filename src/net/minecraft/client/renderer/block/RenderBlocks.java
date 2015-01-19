package net.minecraft.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class RenderBlocks
{
    /** The IBlockAccess used by this instance of RenderBlocks */
    public IBlockAccess blockAccess;

    /**
     * If set to >=0, all block faces will be rendered using this texture index
     */
    public Icon overrideBlockTexture = null;

    /**
     * Set to true if the texture should be flipped horizontally during render*Face
     */
    public boolean flipTexture = false;

    /**
     * If true, renders all faces on all blocks rather than using the logic in Block.shouldSideBeRendered.  Unused.
     */
    public boolean renderAllFaces = false;

    /** Fancy grass side matching biome */
    public static boolean fancyGrass = true;
    public boolean useInventoryTint = true;

    /** The minimum X value for rendering (default 0.0). */
    public double renderMinX;

    /** The maximum X value for rendering (default 1.0). */
    public double renderMaxX;

    /** The minimum Y value for rendering (default 0.0). */
    public double renderMinY;

    /** The maximum Y value for rendering (default 1.0). */
    public double renderMaxY;

    /** The minimum Z value for rendering (default 0.0). */
    public double renderMinZ;

    /** The maximum Z value for rendering (default 1.0). */
    public double renderMaxZ;

    /**
     * Set by overrideBlockBounds, to keep this class from changing the visual bounding box.
     */
    public boolean lockBlockBounds = false;
    public boolean partialRenderBounds = false;
    public final Minecraft minecraftRB;
    public int uvRotateEast = 0;
    public int uvRotateWest = 0;
    public int uvRotateSouth = 0;
    public int uvRotateNorth = 0;
    public int uvRotateTop = 0;
    public int uvRotateBottom = 0;

    /** Whether ambient occlusion is enabled or not */
    public boolean enableAO;

    /**
     * Used as a scratch variable for ambient occlusion on the north/bottom/east corner.
     */
    public float aoLightValueScratchXYZNNN;

    /**
     * Used as a scratch variable for ambient occlusion between the bottom face and the north face.
     */
    public float aoLightValueScratchXYNN;

    /**
     * Used as a scratch variable for ambient occlusion on the north/bottom/west corner.
     */
    public float aoLightValueScratchXYZNNP;

    /**
     * Used as a scratch variable for ambient occlusion between the bottom face and the east face.
     */
    public float aoLightValueScratchYZNN;

    /**
     * Used as a scratch variable for ambient occlusion between the bottom face and the west face.
     */
    public float aoLightValueScratchYZNP;

    /**
     * Used as a scratch variable for ambient occlusion on the south/bottom/east corner.
     */
    public float aoLightValueScratchXYZPNN;

    /**
     * Used as a scratch variable for ambient occlusion between the bottom face and the south face.
     */
    public float aoLightValueScratchXYPN;

    /**
     * Used as a scratch variable for ambient occlusion on the south/bottom/west corner.
     */
    public float aoLightValueScratchXYZPNP;

    /**
     * Used as a scratch variable for ambient occlusion on the north/top/east corner.
     */
    public float aoLightValueScratchXYZNPN;

    /**
     * Used as a scratch variable for ambient occlusion between the top face and the north face.
     */
    public float aoLightValueScratchXYNP;

    /**
     * Used as a scratch variable for ambient occlusion on the north/top/west corner.
     */
    public float aoLightValueScratchXYZNPP;

    /**
     * Used as a scratch variable for ambient occlusion between the top face and the east face.
     */
    public float aoLightValueScratchYZPN;

    /**
     * Used as a scratch variable for ambient occlusion on the south/top/east corner.
     */
    public float aoLightValueScratchXYZPPN;

    /**
     * Used as a scratch variable for ambient occlusion between the top face and the south face.
     */
    public float aoLightValueScratchXYPP;

    /**
     * Used as a scratch variable for ambient occlusion between the top face and the west face.
     */
    public float aoLightValueScratchYZPP;

    /**
     * Used as a scratch variable for ambient occlusion on the south/top/west corner.
     */
    public float aoLightValueScratchXYZPPP;

    /**
     * Used as a scratch variable for ambient occlusion between the north face and the east face.
     */
    public float aoLightValueScratchXZNN;

    /**
     * Used as a scratch variable for ambient occlusion between the south face and the east face.
     */
    public float aoLightValueScratchXZPN;

    /**
     * Used as a scratch variable for ambient occlusion between the north face and the west face.
     */
    public float aoLightValueScratchXZNP;

    /**
     * Used as a scratch variable for ambient occlusion between the south face and the west face.
     */
    public float aoLightValueScratchXZPP;

    /** Ambient occlusion brightness XYZNNN */
    public int aoBrightnessXYZNNN;

    /** Ambient occlusion brightness XYNN */
    public int aoBrightnessXYNN;

    /** Ambient occlusion brightness XYZNNP */
    public int aoBrightnessXYZNNP;

    /** Ambient occlusion brightness YZNN */
    public int aoBrightnessYZNN;

    /** Ambient occlusion brightness YZNP */
    public int aoBrightnessYZNP;

    /** Ambient occlusion brightness XYZPNN */
    public int aoBrightnessXYZPNN;

    /** Ambient occlusion brightness XYPN */
    public int aoBrightnessXYPN;

    /** Ambient occlusion brightness XYZPNP */
    public int aoBrightnessXYZPNP;

    /** Ambient occlusion brightness XYZNPN */
    public int aoBrightnessXYZNPN;

    /** Ambient occlusion brightness XYNP */
    public int aoBrightnessXYNP;

    /** Ambient occlusion brightness XYZNPP */
    public int aoBrightnessXYZNPP;

    /** Ambient occlusion brightness YZPN */
    public int aoBrightnessYZPN;

    /** Ambient occlusion brightness XYZPPN */
    public int aoBrightnessXYZPPN;

    /** Ambient occlusion brightness XYPP */
    public int aoBrightnessXYPP;

    /** Ambient occlusion brightness YZPP */
    public int aoBrightnessYZPP;

    /** Ambient occlusion brightness XYZPPP */
    public int aoBrightnessXYZPPP;

    /** Ambient occlusion brightness XZNN */
    public int aoBrightnessXZNN;

    /** Ambient occlusion brightness XZPN */
    public int aoBrightnessXZPN;

    /** Ambient occlusion brightness XZNP */
    public int aoBrightnessXZNP;

    /** Ambient occlusion brightness XZPP */
    public int aoBrightnessXZPP;

    /** Brightness top left */
    public int brightnessTopLeft;

    /** Brightness bottom left */
    public int brightnessBottomLeft;

    /** Brightness bottom right */
    public int brightnessBottomRight;

    /** Brightness top right */
    public int brightnessTopRight;

    /** Red color value for the top left corner */
    public float colorRedTopLeft;

    /** Red color value for the bottom left corner */
    public float colorRedBottomLeft;

    /** Red color value for the bottom right corner */
    public float colorRedBottomRight;

    /** Red color value for the top right corner */
    public float colorRedTopRight;

    /** Green color value for the top left corner */
    public float colorGreenTopLeft;

    /** Green color value for the bottom left corner */
    public float colorGreenBottomLeft;

    /** Green color value for the bottom right corner */
    public float colorGreenBottomRight;

    /** Green color value for the top right corner */
    public float colorGreenTopRight;

    /** Blue color value for the top left corner */
    public float colorBlueTopLeft;

    /** Blue color value for the bottom left corner */
    public float colorBlueBottomLeft;

    /** Blue color value for the bottom right corner */
    public float colorBlueBottomRight;

    /** Blue color value for the top right corner */
    public float colorBlueTopRight;
    
    public RenderBlocks(IBlockAccess par1IBlockAccess)
    {
        this.blockAccess = par1IBlockAccess;
        this.minecraftRB = Minecraft.getMinecraft();
    }

    public RenderBlocks()
    {
        this.minecraftRB = Minecraft.getMinecraft();
    }

    /**
     * Sets overrideBlockTexture
     */
    public void setOverrideBlockTexture(Icon par1Icon)
    {
        this.overrideBlockTexture = par1Icon;
    }

    /**
     * Clear override block texture
     */
    public void clearOverrideBlockTexture()
    {
        this.overrideBlockTexture = null;
    }

    public boolean hasOverrideBlockTexture()
    {
        return this.overrideBlockTexture != null;
    }

    /**
     * Sets the bounding box for the block to draw in, e.g. 0.25-0.75 on all axes for a half-size, centered block.
     */
    public void setRenderBounds(double par1, double par3, double par5, double par7, double par9, double par11)
    {
        if (!this.lockBlockBounds)
        {
            this.renderMinX = par1;
            this.renderMaxX = par7;
            this.renderMinY = par3;
            this.renderMaxY = par9;
            this.renderMinZ = par5;
            this.renderMaxZ = par11;
            this.partialRenderBounds = this.minecraftRB.gameSettings.ambientOcclusion >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D);
        }
    }

    /**
     * Like setRenderBounds, but automatically pulling the bounds from the given Block.
     */
    public void setRenderBoundsFromBlock(Block par1Block)
    {
        if (!this.lockBlockBounds)
        {
            this.renderMinX = par1Block.getBlockBoundsMinX();
            this.renderMaxX = par1Block.getBlockBoundsMaxX();
            this.renderMinY = par1Block.getBlockBoundsMinY();
            this.renderMaxY = par1Block.getBlockBoundsMaxY();
            this.renderMinZ = par1Block.getBlockBoundsMinZ();
            this.renderMaxZ = par1Block.getBlockBoundsMaxZ();
            this.partialRenderBounds = this.minecraftRB.gameSettings.ambientOcclusion >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D);
        }
    }

    /**
     * Like setRenderBounds, but locks the values so that RenderBlocks won't change them.  If you use this, you must
     * call unlockBlockBounds after you finish rendering!
     */
    public void overrideBlockBounds(double par1, double par3, double par5, double par7, double par9, double par11)
    {
        this.renderMinX = par1;
        this.renderMaxX = par7;
        this.renderMinY = par3;
        this.renderMaxY = par9;
        this.renderMinZ = par5;
        this.renderMaxZ = par11;
        this.lockBlockBounds = true;
        this.partialRenderBounds = this.minecraftRB.gameSettings.ambientOcclusion >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D);
    }

    /**
     * Unlocks the visual bounding box so that RenderBlocks can change it again.
     */
    public void unlockBlockBounds()
    {
        this.lockBlockBounds = false;
    }

    /**
     * Renders a block using the given texture instead of the block's own default texture
     */
    public void renderBlockUsingTexture(Block par1Block, int par2, int par3, int par4, Icon par5Icon)
    {
        this.setOverrideBlockTexture(par5Icon);
        this.renderBlockByRenderType(par1Block, par2, par3, par4);
        this.clearOverrideBlockTexture();
    }

    /**
     * Render all faces of a block
     */
    public void renderBlockAllFaces(Block par1Block, int par2, int par3, int par4)
    {
        this.renderAllFaces = true;
        this.renderBlockByRenderType(par1Block, par2, par3, par4);
        this.renderAllFaces = false;
    }
    
    /**
     * Renders the block at the given coordinates using the block's rendering type
     */
    public boolean renderBlockByRenderType(Block block, int x, int y, int z)
    {
        int renderType = block.getRenderType();

        if (renderType == -1) return false;
        else
        {
            block.setBlockBoundsBasedOnState(this.blockAccess, x, y, z);
            this.setRenderBoundsFromBlock(block);
            
            return BlockRenderer.getBlockRenderer(renderType).onRenderBlock(this, block, x, y, z);
        }
    }
    
    /**
     * Utility function to draw crossed squares
     */
    public void drawCrossedSquares(Block par1Block, int par2, double par3, double par5, double par7, float par9)
    {
        Tessellator var10 = Tessellator.instance;
        Icon var11 = this.getBlockIconFromSideAndMetadata(par1Block, 0, par2);

        if (this.hasOverrideBlockTexture())
        {
            var11 = this.overrideBlockTexture;
        }

        double var12 = (double)var11.getMinU();
        double var14 = (double)var11.getMinV();
        double var16 = (double)var11.getMaxU();
        double var18 = (double)var11.getMaxV();
        double var20 = 0.45D * (double)par9;
        double var22 = par3 + 0.5D - var20;
        double var24 = par3 + 0.5D + var20;
        double var26 = par7 + 0.5D - var20;
        double var28 = par7 + 0.5D + var20;
        var10.addVertexWithUV(var22, par5 + (double)par9, var26, var12, var14);
        var10.addVertexWithUV(var22, par5 + 0.0D, var26, var12, var18);
        var10.addVertexWithUV(var24, par5 + 0.0D, var28, var16, var18);
        var10.addVertexWithUV(var24, par5 + (double)par9, var28, var16, var14);
        var10.addVertexWithUV(var24, par5 + (double)par9, var28, var12, var14);
        var10.addVertexWithUV(var24, par5 + 0.0D, var28, var12, var18);
        var10.addVertexWithUV(var22, par5 + 0.0D, var26, var16, var18);
        var10.addVertexWithUV(var22, par5 + (double)par9, var26, var16, var14);
        var10.addVertexWithUV(var22, par5 + (double)par9, var28, var12, var14);
        var10.addVertexWithUV(var22, par5 + 0.0D, var28, var12, var18);
        var10.addVertexWithUV(var24, par5 + 0.0D, var26, var16, var18);
        var10.addVertexWithUV(var24, par5 + (double)par9, var26, var16, var14);
        var10.addVertexWithUV(var24, par5 + (double)par9, var26, var12, var14);
        var10.addVertexWithUV(var24, par5 + 0.0D, var26, var12, var18);
        var10.addVertexWithUV(var22, par5 + 0.0D, var28, var16, var18);
        var10.addVertexWithUV(var22, par5 + (double)par9, var28, var16, var14);
    }

    /**
     * Renders a falling sand block
     */
    public void renderBlockSandFalling(Block par1Block, World par2World, int par3, int par4, int par5, int par6)
    {
        float var7 = 0.5F;
        float var8 = 1.0F;
        float var9 = 0.8F;
        float var10 = 0.6F;
        Tessellator var11 = Tessellator.instance;
        var11.startDrawingQuads();
        var11.setBrightness(par1Block.getMixedBrightnessForBlock(par2World, par3, par4, par5));
        float var12 = 1.0F;
        float var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var7 * var13, var7 * var13, var7 * var13);
        this.renderFaceYNeg(par1Block, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(par1Block, 0, par6));
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var8 * var13, var8 * var13, var8 * var13);
        this.renderFaceYPos(par1Block, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(par1Block, 1, par6));
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var9 * var13, var9 * var13, var9 * var13);
        this.renderFaceZNeg(par1Block, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(par1Block, 2, par6));
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var9 * var13, var9 * var13, var9 * var13);
        this.renderFaceZPos(par1Block, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(par1Block, 3, par6));
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var10 * var13, var10 * var13, var10 * var13);
        this.renderFaceXNeg(par1Block, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(par1Block, 4, par6));
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var10 * var13, var10 * var13, var10 * var13);
        this.renderFaceXPos(par1Block, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(par1Block, 5, par6));
        var11.draw();
    }

    /**
     * Get ambient occlusion brightness
     */
    
    public int getAoBrightness(int par1, int par2, int par3, int par4)
    {
        if (par1 == 0)
        {
            par1 = par4;
        }

        if (par2 == 0)
        {
            par2 = par4;
        }

        if (par3 == 0)
        {
            par3 = par4;
        }

        return par1 + par2 + par3 + par4 >> 2 & 16711935;
    }

    public int mixAoBrightness(int par1, int par2, int par3, int par4, double par5, double par7, double par9, double par11)
    {
        int var13 = (int)((double)(par1 >> 16 & 255) * par5 + (double)(par2 >> 16 & 255) * par7 + (double)(par3 >> 16 & 255) * par9 + (double)(par4 >> 16 & 255) * par11) & 255;
        int var14 = (int)((double)(par1 & 255) * par5 + (double)(par2 & 255) * par7 + (double)(par3 & 255) * par9 + (double)(par4 & 255) * par11) & 255;
        return var13 << 16 | var14;
    }

    /**
     * Renders the given texture to the bottom face of the block. Args: block, x, y, z, texture
     */
    public void renderFaceYNeg(Block par1Block, double par2, double par4, double par6, Icon par8Icon)
    {
        Tessellator var9 = Tessellator.instance;

        if (this.hasOverrideBlockTexture())
        {
            par8Icon = this.overrideBlockTexture;
        }

        double var10 = (double)par8Icon.getInterpolatedU(this.renderMinX * 16.0D);
        double var12 = (double)par8Icon.getInterpolatedU(this.renderMaxX * 16.0D);
        double var14 = (double)par8Icon.getInterpolatedV(this.renderMinZ * 16.0D);
        double var16 = (double)par8Icon.getInterpolatedV(this.renderMaxZ * 16.0D);

        if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D)
        {
            var10 = (double)par8Icon.getMinU();
            var12 = (double)par8Icon.getMaxU();
        }

        if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D)
        {
            var14 = (double)par8Icon.getMinV();
            var16 = (double)par8Icon.getMaxV();
        }

        double var18 = var12;
        double var20 = var10;
        double var22 = var14;
        double var24 = var16;

        if (this.uvRotateBottom == 2)
        {
            var10 = (double)par8Icon.getInterpolatedU(this.renderMinZ * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(this.renderMaxZ * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
            var22 = var14;
            var24 = var16;
            var18 = var10;
            var20 = var12;
            var14 = var16;
            var16 = var22;
        }
        else if (this.uvRotateBottom == 1)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(this.renderMinX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(this.renderMaxX * 16.0D);
            var18 = var12;
            var20 = var10;
            var10 = var12;
            var12 = var20;
            var22 = var16;
            var24 = var14;
        }
        else if (this.uvRotateBottom == 3)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
            var18 = var12;
            var20 = var10;
            var22 = var14;
            var24 = var16;
        }

        double var26 = par2 + this.renderMinX;
        double var28 = par2 + this.renderMaxX;
        double var30 = par4 + this.renderMinY;
        double var32 = par6 + this.renderMinZ;
        double var34 = par6 + this.renderMaxZ;

        if (this.enableAO)
        {
            var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            var9.setBrightness(this.brightnessTopLeft);
            var9.addVertexWithUV(var26, var30, var34, var20, var24);
            var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            var9.setBrightness(this.brightnessBottomLeft);
            var9.addVertexWithUV(var26, var30, var32, var10, var14);
            var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            var9.setBrightness(this.brightnessBottomRight);
            var9.addVertexWithUV(var28, var30, var32, var18, var22);
            var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            var9.setBrightness(this.brightnessTopRight);
            var9.addVertexWithUV(var28, var30, var34, var12, var16);
        }
        else
        {
            var9.addVertexWithUV(var26, var30, var34, var20, var24);
            var9.addVertexWithUV(var26, var30, var32, var10, var14);
            var9.addVertexWithUV(var28, var30, var32, var18, var22);
            var9.addVertexWithUV(var28, var30, var34, var12, var16);
        }
    }

    /**
     * Renders the given texture to the top face of the block. Args: block, x, y, z, texture
     */
    public void renderFaceYPos(Block par1Block, double par2, double par4, double par6, Icon par8Icon)
    {
        Tessellator var9 = Tessellator.instance;

        if (this.hasOverrideBlockTexture())
        {
            par8Icon = this.overrideBlockTexture;
        }

        double var10 = (double)par8Icon.getInterpolatedU(this.renderMinX * 16.0D);
        double var12 = (double)par8Icon.getInterpolatedU(this.renderMaxX * 16.0D);
        double var14 = (double)par8Icon.getInterpolatedV(this.renderMinZ * 16.0D);
        double var16 = (double)par8Icon.getInterpolatedV(this.renderMaxZ * 16.0D);

        if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D)
        {
            var10 = (double)par8Icon.getMinU();
            var12 = (double)par8Icon.getMaxU();
        }

        if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D)
        {
            var14 = (double)par8Icon.getMinV();
            var16 = (double)par8Icon.getMaxV();
        }

        double var18 = var12;
        double var20 = var10;
        double var22 = var14;
        double var24 = var16;

        if (this.uvRotateTop == 1)
        {
            var10 = (double)par8Icon.getInterpolatedU(this.renderMinZ * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(this.renderMaxZ * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
            var22 = var14;
            var24 = var16;
            var18 = var10;
            var20 = var12;
            var14 = var16;
            var16 = var22;
        }
        else if (this.uvRotateTop == 2)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(this.renderMinX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(this.renderMaxX * 16.0D);
            var18 = var12;
            var20 = var10;
            var10 = var12;
            var12 = var20;
            var22 = var16;
            var24 = var14;
        }
        else if (this.uvRotateTop == 3)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
            var18 = var12;
            var20 = var10;
            var22 = var14;
            var24 = var16;
        }

        double var26 = par2 + this.renderMinX;
        double var28 = par2 + this.renderMaxX;
        double var30 = par4 + this.renderMaxY;
        double var32 = par6 + this.renderMinZ;
        double var34 = par6 + this.renderMaxZ;

        if (this.enableAO)
        {
            var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            var9.setBrightness(this.brightnessTopLeft);
            var9.addVertexWithUV(var28, var30, var34, var12, var16);
            var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            var9.setBrightness(this.brightnessBottomLeft);
            var9.addVertexWithUV(var28, var30, var32, var18, var22);
            var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            var9.setBrightness(this.brightnessBottomRight);
            var9.addVertexWithUV(var26, var30, var32, var10, var14);
            var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            var9.setBrightness(this.brightnessTopRight);
            var9.addVertexWithUV(var26, var30, var34, var20, var24);
        }
        else
        {
            var9.addVertexWithUV(var28, var30, var34, var12, var16);
            var9.addVertexWithUV(var28, var30, var32, var18, var22);
            var9.addVertexWithUV(var26, var30, var32, var10, var14);
            var9.addVertexWithUV(var26, var30, var34, var20, var24);
        }
    }

    /**
     * Renders the given texture to the north (z-negative) face of the block.  Args: block, x, y, z, texture
     */
    public void renderFaceZNeg(Block par1Block, double par2, double par4, double par6, Icon par8Icon)
    {
        Tessellator var9 = Tessellator.instance;

        if (this.hasOverrideBlockTexture())
        {
            par8Icon = this.overrideBlockTexture;
        }

        double var10 = (double)par8Icon.getInterpolatedU(this.renderMinX * 16.0D);
        double var12 = (double)par8Icon.getInterpolatedU(this.renderMaxX * 16.0D);
        double var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
        double var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
        double var18;

        if (this.flipTexture)
        {
            var18 = var10;
            var10 = var12;
            var12 = var18;
        }

        if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D)
        {
            var10 = (double)par8Icon.getMinU();
            var12 = (double)par8Icon.getMaxU();
        }

        if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D)
        {
            var14 = (double)par8Icon.getMinV();
            var16 = (double)par8Icon.getMaxV();
        }

        var18 = var12;
        double var20 = var10;
        double var22 = var14;
        double var24 = var16;

        if (this.uvRotateEast == 2)
        {
            var10 = (double)par8Icon.getInterpolatedU(this.renderMinY * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(this.renderMaxY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
            var22 = var14;
            var24 = var16;
            var18 = var10;
            var20 = var12;
            var14 = var16;
            var16 = var22;
        }
        else if (this.uvRotateEast == 1)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(this.renderMaxX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(this.renderMinX * 16.0D);
            var18 = var12;
            var20 = var10;
            var10 = var12;
            var12 = var20;
            var22 = var16;
            var24 = var14;
        }
        else if (this.uvRotateEast == 3)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(this.renderMaxY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(this.renderMinY * 16.0D);
            var18 = var12;
            var20 = var10;
            var22 = var14;
            var24 = var16;
        }

        double var26 = par2 + this.renderMinX;
        double var28 = par2 + this.renderMaxX;
        double var30 = par4 + this.renderMinY;
        double var32 = par4 + this.renderMaxY;
        double var34 = par6 + this.renderMinZ;

        if (this.enableAO)
        {
            var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            var9.setBrightness(this.brightnessTopLeft);
            var9.addVertexWithUV(var26, var32, var34, var18, var22);
            var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            var9.setBrightness(this.brightnessBottomLeft);
            var9.addVertexWithUV(var28, var32, var34, var10, var14);
            var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            var9.setBrightness(this.brightnessBottomRight);
            var9.addVertexWithUV(var28, var30, var34, var20, var24);
            var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            var9.setBrightness(this.brightnessTopRight);
            var9.addVertexWithUV(var26, var30, var34, var12, var16);
        }
        else
        {
            var9.addVertexWithUV(var26, var32, var34, var18, var22);
            var9.addVertexWithUV(var28, var32, var34, var10, var14);
            var9.addVertexWithUV(var28, var30, var34, var20, var24);
            var9.addVertexWithUV(var26, var30, var34, var12, var16);
        }
    }

    /**
     * Renders the given texture to the south (z-positive) face of the block.  Args: block, x, y, z, texture
     */
    public void renderFaceZPos(Block par1Block, double par2, double par4, double par6, Icon par8Icon)
    {
        Tessellator var9 = Tessellator.instance;

        if (this.hasOverrideBlockTexture())
        {
            par8Icon = this.overrideBlockTexture;
        }

        double var10 = (double)par8Icon.getInterpolatedU(this.renderMinX * 16.0D);
        double var12 = (double)par8Icon.getInterpolatedU(this.renderMaxX * 16.0D);
        double var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
        double var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
        double var18;

        if (this.flipTexture)
        {
            var18 = var10;
            var10 = var12;
            var12 = var18;
        }

        if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D)
        {
            var10 = (double)par8Icon.getMinU();
            var12 = (double)par8Icon.getMaxU();
        }

        if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D)
        {
            var14 = (double)par8Icon.getMinV();
            var16 = (double)par8Icon.getMaxV();
        }

        var18 = var12;
        double var20 = var10;
        double var22 = var14;
        double var24 = var16;

        if (this.uvRotateWest == 1)
        {
            var10 = (double)par8Icon.getInterpolatedU(this.renderMinY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(this.renderMaxY * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
            var22 = var14;
            var24 = var16;
            var18 = var10;
            var20 = var12;
            var14 = var16;
            var16 = var22;
        }
        else if (this.uvRotateWest == 2)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(this.renderMinX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(this.renderMaxX * 16.0D);
            var18 = var12;
            var20 = var10;
            var10 = var12;
            var12 = var20;
            var22 = var16;
            var24 = var14;
        }
        else if (this.uvRotateWest == 3)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(this.renderMaxY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(this.renderMinY * 16.0D);
            var18 = var12;
            var20 = var10;
            var22 = var14;
            var24 = var16;
        }

        double var26 = par2 + this.renderMinX;
        double var28 = par2 + this.renderMaxX;
        double var30 = par4 + this.renderMinY;
        double var32 = par4 + this.renderMaxY;
        double var34 = par6 + this.renderMaxZ;

        if (this.enableAO)
        {
            var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            var9.setBrightness(this.brightnessTopLeft);
            var9.addVertexWithUV(var26, var32, var34, var10, var14);
            var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            var9.setBrightness(this.brightnessBottomLeft);
            var9.addVertexWithUV(var26, var30, var34, var20, var24);
            var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            var9.setBrightness(this.brightnessBottomRight);
            var9.addVertexWithUV(var28, var30, var34, var12, var16);
            var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            var9.setBrightness(this.brightnessTopRight);
            var9.addVertexWithUV(var28, var32, var34, var18, var22);
        }
        else
        {
            var9.addVertexWithUV(var26, var32, var34, var10, var14);
            var9.addVertexWithUV(var26, var30, var34, var20, var24);
            var9.addVertexWithUV(var28, var30, var34, var12, var16);
            var9.addVertexWithUV(var28, var32, var34, var18, var22);
        }
    }

    /**
     * Renders the given texture to the west (x-negative) face of the block.  Args: block, x, y, z, texture
     */
    public void renderFaceXNeg(Block par1Block, double par2, double par4, double par6, Icon par8Icon)
    {
        Tessellator var9 = Tessellator.instance;

        if (this.hasOverrideBlockTexture())
        {
            par8Icon = this.overrideBlockTexture;
        }

        double var10 = (double)par8Icon.getInterpolatedU(this.renderMinZ * 16.0D);
        double var12 = (double)par8Icon.getInterpolatedU(this.renderMaxZ * 16.0D);
        double var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
        double var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
        double var18;

        if (this.flipTexture)
        {
            var18 = var10;
            var10 = var12;
            var12 = var18;
        }

        if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D)
        {
            var10 = (double)par8Icon.getMinU();
            var12 = (double)par8Icon.getMaxU();
        }

        if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D)
        {
            var14 = (double)par8Icon.getMinV();
            var16 = (double)par8Icon.getMaxV();
        }

        var18 = var12;
        double var20 = var10;
        double var22 = var14;
        double var24 = var16;

        if (this.uvRotateNorth == 1)
        {
            var10 = (double)par8Icon.getInterpolatedU(this.renderMinY * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(this.renderMaxY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
            var22 = var14;
            var24 = var16;
            var18 = var10;
            var20 = var12;
            var14 = var16;
            var16 = var22;
        }
        else if (this.uvRotateNorth == 2)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(this.renderMinZ * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(this.renderMaxZ * 16.0D);
            var18 = var12;
            var20 = var10;
            var10 = var12;
            var12 = var20;
            var22 = var16;
            var24 = var14;
        }
        else if (this.uvRotateNorth == 3)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(this.renderMaxY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(this.renderMinY * 16.0D);
            var18 = var12;
            var20 = var10;
            var22 = var14;
            var24 = var16;
        }

        double var26 = par2 + this.renderMinX;
        double var28 = par4 + this.renderMinY;
        double var30 = par4 + this.renderMaxY;
        double var32 = par6 + this.renderMinZ;
        double var34 = par6 + this.renderMaxZ;

        if (this.enableAO)
        {
            var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            var9.setBrightness(this.brightnessTopLeft);
            var9.addVertexWithUV(var26, var30, var34, var18, var22);
            var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            var9.setBrightness(this.brightnessBottomLeft);
            var9.addVertexWithUV(var26, var30, var32, var10, var14);
            var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            var9.setBrightness(this.brightnessBottomRight);
            var9.addVertexWithUV(var26, var28, var32, var20, var24);
            var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            var9.setBrightness(this.brightnessTopRight);
            var9.addVertexWithUV(var26, var28, var34, var12, var16);
        }
        else
        {
            var9.addVertexWithUV(var26, var30, var34, var18, var22);
            var9.addVertexWithUV(var26, var30, var32, var10, var14);
            var9.addVertexWithUV(var26, var28, var32, var20, var24);
            var9.addVertexWithUV(var26, var28, var34, var12, var16);
        }
    }

    /**
     * Renders the given texture to the east (x-positive) face of the block.  Args: block, x, y, z, texture
     */
    public void renderFaceXPos(Block par1Block, double par2, double par4, double par6, Icon par8Icon)
    {
        Tessellator var9 = Tessellator.instance;

        if (this.hasOverrideBlockTexture())
        {
            par8Icon = this.overrideBlockTexture;
        }

        double var10 = (double)par8Icon.getInterpolatedU(this.renderMinZ * 16.0D);
        double var12 = (double)par8Icon.getInterpolatedU(this.renderMaxZ * 16.0D);
        double var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
        double var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
        double var18;

        if (this.flipTexture)
        {
            var18 = var10;
            var10 = var12;
            var12 = var18;
        }

        if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D)
        {
            var10 = (double)par8Icon.getMinU();
            var12 = (double)par8Icon.getMaxU();
        }

        if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D)
        {
            var14 = (double)par8Icon.getMinV();
            var16 = (double)par8Icon.getMaxV();
        }

        var18 = var12;
        double var20 = var10;
        double var22 = var14;
        double var24 = var16;

        if (this.uvRotateSouth == 2)
        {
            var10 = (double)par8Icon.getInterpolatedU(this.renderMinY * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(this.renderMaxY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
            var22 = var14;
            var24 = var16;
            var18 = var10;
            var20 = var12;
            var14 = var16;
            var16 = var22;
        }
        else if (this.uvRotateSouth == 1)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(this.renderMaxZ * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(this.renderMinZ * 16.0D);
            var18 = var12;
            var20 = var10;
            var10 = var12;
            var12 = var20;
            var22 = var16;
            var24 = var14;
        }
        else if (this.uvRotateSouth == 3)
        {
            var10 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
            var12 = (double)par8Icon.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
            var14 = (double)par8Icon.getInterpolatedV(this.renderMaxY * 16.0D);
            var16 = (double)par8Icon.getInterpolatedV(this.renderMinY * 16.0D);
            var18 = var12;
            var20 = var10;
            var22 = var14;
            var24 = var16;
        }

        double var26 = par2 + this.renderMaxX;
        double var28 = par4 + this.renderMinY;
        double var30 = par4 + this.renderMaxY;
        double var32 = par6 + this.renderMinZ;
        double var34 = par6 + this.renderMaxZ;

        if (this.enableAO)
        {
            var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            var9.setBrightness(this.brightnessTopLeft);
            var9.addVertexWithUV(var26, var28, var34, var20, var24);
            var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            var9.setBrightness(this.brightnessBottomLeft);
            var9.addVertexWithUV(var26, var28, var32, var12, var16);
            var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            var9.setBrightness(this.brightnessBottomRight);
            var9.addVertexWithUV(var26, var30, var32, var18, var22);
            var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            var9.setBrightness(this.brightnessTopRight);
            var9.addVertexWithUV(var26, var30, var34, var10, var14);
        }
        else
        {
            var9.addVertexWithUV(var26, var28, var34, var20, var24);
            var9.addVertexWithUV(var26, var28, var32, var12, var16);
            var9.addVertexWithUV(var26, var30, var32, var18, var22);
            var9.addVertexWithUV(var26, var30, var34, var10, var14);
        }
    }

    /**
     * Is called to render the image of a block on an inventory, as a held item, or as a an item on the ground
     */
    public void renderBlockAsItem(Block block, int par2, float par3)
    {
        Tessellator tess = Tessellator.instance;
        boolean isGrassBlock = block.blockID == Block.grass.blockID;

        if (block == Block.dispenser || block == Block.dropper || block == Block.furnaceIdle)
        {
            par2 = 3;
        }

        float var7;
        float var8;
        float var9;

        if (this.useInventoryTint)
        {
            int renderColor = block.getRenderColor(par2);

            if(isGrassBlock) renderColor = 16777215;

            var7 = (float)(renderColor >> 16 & 255) / 255.0F;
            var8 = (float)(renderColor >> 8 & 255) / 255.0F;
            var9 = (float)(renderColor & 255) / 255.0F;
            GL11.glColor4f(var7 * par3, var8 * par3, var9 * par3, 1.0F);
        }

        int renderType = block.getRenderType();
        this.setRenderBoundsFromBlock(block);
        int var14;

        if (renderType != 0 && renderType != 31 && renderType != 39 && renderType != 16 && renderType != 26)
        {
            if (renderType == 1)
            {
            	BlockRenderer001_CrossedSquares.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 19)
            {
                BlockRenderer019_Stem.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 23)
            {
            	BlockRenderer023_LilyPad.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 13)
            {
            	BlockRenderer013_Cactus.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 22)
            {
            	BlockRenderer022_Chest.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 6)
            {
            	BlockRenderer006_Crops.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 2)
            {
            	BlockRenderer002_Torch.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 10)
            {
            	BlockRenderer010_Stairs.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 27)
            {
            	BlockRenderer027_DragonEgg.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 11)
            {
            	BlockRenderer011_Fence.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 21)
            {
                for (var14 = 0; var14 < 3; ++var14)
                {
                    var8 = 0.0625F;

                    if (var14 == 0)
                    {
                        this.setRenderBounds((double)(0.5F - var8), 0.30000001192092896D, 0.0D, (double)(0.5F + var8), 1.0D, (double)(var8 * 2.0F));
                    }

                    if (var14 == 1)
                    {
                        this.setRenderBounds((double)(0.5F - var8), 0.30000001192092896D, (double)(1.0F - var8 * 2.0F), (double)(0.5F + var8), 1.0D, 1.0D);
                    }

                    var8 = 0.0625F;

                    if (var14 == 2)
                    {
                        this.setRenderBounds((double)(0.5F - var8), 0.5D, 0.0D, (double)(0.5F + var8), (double)(1.0F - var8), 1.0D);
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tess.startDrawingQuads();
                    tess.setNormal(0.0F, -1.0F, 0.0F);
                    this.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(block, 0));
                    tess.draw();
                    tess.startDrawingQuads();
                    tess.setNormal(0.0F, 1.0F, 0.0F);
                    this.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(block, 1));
                    tess.draw();
                    tess.startDrawingQuads();
                    tess.setNormal(0.0F, 0.0F, -1.0F);
                    this.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(block, 2));
                    tess.draw();
                    tess.startDrawingQuads();
                    tess.setNormal(0.0F, 0.0F, 1.0F);
                    this.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(block, 3));
                    tess.draw();
                    tess.startDrawingQuads();
                    tess.setNormal(-1.0F, 0.0F, 0.0F);
                    this.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(block, 4));
                    tess.draw();
                    tess.startDrawingQuads();
                    tess.setNormal(1.0F, 0.0F, 0.0F);
                    this.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(block, 5));
                    tess.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }
            }
            else if (renderType == 32)
            {
            	BlockRenderer032_Wall.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 35)
            {
            	BlockRenderer035_Anvil.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 34)
            {
            	BlockRenderer034_Beacon.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
            else if (renderType == 38)
            {
                BlockRenderer038_Hopper.renderer.renderBlockAsItem(tess, this, block, par2, par3);
            }
        }
        else
        {
        	BlockRenderer.getBlockRenderer(renderType).renderBlockAsItem(tess, this, block, par2, par3);
        }
    }

    /**
     * Checks to see if the item's render type indicates that it should be rendered as a regular block or not.
     */
    public static boolean renderItemIn3d(int par0)
    {
        return par0 == 0 ? true : (par0 == 31 ? true : (par0 == 39 ? true : (par0 == 13 ? true : (par0 == 10 ? true : (par0 == 11 ? true : (par0 == 27 ? true : (par0 == 22 ? true : (par0 == 21 ? true : (par0 == 16 ? true : (par0 == 26 ? true : (par0 == 32 ? true : (par0 == 34 ? true : par0 == 35))))))))))));
    }

    public Icon getBlockIcon(Block par1Block, IBlockAccess par2IBlockAccess, int par3, int par4, int par5, int par6)
    {
        return this.getIconSafe(par1Block.getBlockTexture(par2IBlockAccess, par3, par4, par5, par6));
    }

    public Icon getBlockIconFromSideAndMetadata(Block par1Block, int par2, int par3)
    {
        return this.getIconSafe(par1Block.getIcon(par2, par3));
    }

    public Icon getBlockIconFromSide(Block par1Block, int par2)
    {
        return this.getIconSafe(par1Block.getBlockTextureFromSide(par2));
    }

    public Icon getBlockIcon(Block par1Block)
    {
        return this.getIconSafe(par1Block.getBlockTextureFromSide(1));
    }

    public Icon getIconSafe(Icon par1Icon)
    {
        return par1Icon == null ? this.minecraftRB.renderEngine.getMissingIcon(0) : par1Icon;
    }
    
    static
    {
    	BlockRenderer000_Standard.nopInit();
    	BlockRenderer004_Fluids.nopInit();
    	BlockRenderer031_Log.nopInit();
    	BlockRenderer001_CrossedSquares.nopInit();
    	BlockRenderer002_Torch.nopInit();
    	BlockRenderer020_Vine.nopInit();
    	BlockRenderer011_Fence.nopInit();
    	BlockRenderer039_Quartz.nopInit();
    	BlockRenderer005_RedstoneWire.nopInit();
    	BlockRenderer013_Cactus.nopInit();
    	BlockRenderer009_MinecartTrack.nopInit();
    	BlockRenderer019_Stem.nopInit();
    	BlockRenderer023_LilyPad.nopInit();
    	BlockRenderer006_Crops.nopInit();
    	BlockRenderer003_Fire.nopInit();
    	BlockRenderer008_Ladder.nopInit();
    	BlockRenderer007_Door.nopInit();
    	BlockRenderer010_Stairs.nopInit();
    	BlockRenderer027_DragonEgg.nopInit();
    	BlockRenderer032_Wall.nopInit();
    	BlockRenderer012_Lever.nopInit();
    	BlockRenderer029_TripWireSource.nopInit();
    	BlockRenderer018_Pane.nopInit();
    	BlockRenderer030_TripWire.nopInit();
    	BlockRenderer014_Bed.nopInit();
    	BlockRenderer035_Anvil.nopInit();
    	BlockRenderer015_Repeater.nopInit();
    	BlockRenderer036_RedstoneLogic.nopInit();
    	BlockRenderer037_Comparator.nopInit();
    	BlockRenderer016_PistonBase.nopInit();
    	BlockRenderer017_PistonExtension.nopInit();
    	BlockRenderer021_FenceGate.nopInit();
    	BlockRenderer024_Cauldron.nopInit();
    	BlockRenderer033_FlowerPot.nopInit();
    	BlockRenderer025_BrewingStand.nopInit();
    	BlockRenderer026_EndPortalFrame.nopInit();
    	BlockRenderer028_Cocoa.nopInit();
    	BlockRenderer034_Beacon.nopInit();
    	BlockRenderer038_Hopper.nopInit();
    	BlockRenderer022_Chest.nopInit();
    }
}
