package net.minecraft.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockInfoContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.BlockRenderer016_PistonBase;
import net.minecraft.client.renderer.block.BlockRenderer017_PistonExtension;
import net.minecraft.client.renderer.block.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class TileEntityRendererPiston extends TileEntitySpecialRenderer
{
    /** instance of RenderBlocks used to draw the piston base and extension. */
    private RenderBlocks blockRenderer;

    public void renderPiston(TileEntityPiston par1TileEntityPiston, double par2, double par4, double par6, float par8)
    {
    	BlockInfoContainer whocallme = BlockInfoContainer.getBlockInfoContainer();
        Block var9 = whocallme.getBlock(par1TileEntityPiston.getStoredBlockID());

        if (var9 != null && par1TileEntityPiston.getProgress(par8) < 1.0F)
        {
            Tessellator var10 = Tessellator.instance;
            this.bindTextureByName("/terrain.png");
            RenderHelper.disableStandardItemLighting();
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_CULL_FACE);

            if (Minecraft.isAmbientOcclusionEnabled())
            {
                GL11.glShadeModel(GL11.GL_SMOOTH);
            }
            else
            {
                GL11.glShadeModel(GL11.GL_FLAT);
            }

            var10.startDrawingQuads();
            var10.setTranslation((double)((float)par2 - (float)par1TileEntityPiston.xCoord + par1TileEntityPiston.getOffsetX(par8)), (double)((float)par4 - (float)par1TileEntityPiston.yCoord + par1TileEntityPiston.getOffsetY(par8)), (double)((float)par6 - (float)par1TileEntityPiston.zCoord + par1TileEntityPiston.getOffsetZ(par8)));
            var10.setColorOpaque(1, 1, 1);

            if (var9 == Block.pistonExtension && par1TileEntityPiston.getProgress(par8) < 0.5F)
            {
                ((BlockRenderer017_PistonExtension)BlockRenderer017_PistonExtension.renderer).renderPistonExtensionAllFaces(this.blockRenderer, var9, par1TileEntityPiston.xCoord, par1TileEntityPiston.yCoord, par1TileEntityPiston.zCoord, false);
            }
            else if (par1TileEntityPiston.shouldRenderHead() && !par1TileEntityPiston.isExtending())
            {
                Block.pistonExtension.setHeadTexture(((BlockPistonBase)var9).getPistonExtensionTexture());
                ((BlockRenderer017_PistonExtension)BlockRenderer017_PistonExtension.renderer).renderPistonExtensionAllFaces(this.blockRenderer, Block.pistonExtension, par1TileEntityPiston.xCoord, par1TileEntityPiston.yCoord, par1TileEntityPiston.zCoord, par1TileEntityPiston.getProgress(par8) < 0.5F);
                Block.pistonExtension.clearHeadTexture();
                var10.setTranslation((double)((float)par2 - (float)par1TileEntityPiston.xCoord), (double)((float)par4 - (float)par1TileEntityPiston.yCoord), (double)((float)par6 - (float)par1TileEntityPiston.zCoord));
                ((BlockRenderer016_PistonBase)BlockRenderer016_PistonBase.renderer).renderPistonBaseAllFaces(this.blockRenderer, var9, par1TileEntityPiston.xCoord, par1TileEntityPiston.yCoord, par1TileEntityPiston.zCoord);
            }
            else
            {
                this.blockRenderer.renderBlockAllFaces(var9, par1TileEntityPiston.xCoord, par1TileEntityPiston.yCoord, par1TileEntityPiston.zCoord);
            }

            var10.setTranslation(0.0D, 0.0D, 0.0D);
            var10.draw();
            RenderHelper.enableStandardItemLighting();
        }
    }

    /**
     * Called when the ingame world being rendered changes (e.g. on world -> nether travel) due to using one renderer
     * per tile entity type, rather than instance
     */
    public void onWorldChange(World par1World)
    {
        this.blockRenderer = new RenderBlocks(par1World);
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderPiston((TileEntityPiston)par1TileEntity, par2, par4, par6, par8);
    }
}
