package net.aegistudio.minecraft.utopian.gui;

import java.util.ArrayList;

import net.aegistudio.minecraft.utopian.Installation;
import net.aegistudio.minecraft.utopian.Installer;
import net.aegistudio.minecraft.utopian.patch.Patch;
import net.aegistudio.minecraft.utopian.plugin.Plugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;

public class GuiUtopianPatchAndPluginList extends GuiSlot
{
    private ArrayList<Object> installationSlots;
    
    final GuiUtopian utopianGui;
    final FontRenderer fontRenderer;
    
    private Object selectedSlot = null;
    
    protected static int CELL_HEIGHT = 28;
    
    public static int UPPER_MARGIN_SLOT = 32;
    public static int LOWER_MARGIN_SLOT = 65;
    
    public GuiUtopianPatchAndPluginList(Minecraft mc, FontRenderer renderer, GuiUtopian utopianGui)
    {
        super(mc, utopianGui.width / 2, utopianGui.height, UPPER_MARGIN_SLOT, utopianGui.height - LOWER_MARGIN_SLOT + 4, CELL_HEIGHT);
        this.utopianGui = utopianGui;
        this.fontRenderer = renderer;
        super.SLOT_BAR_BIAS = 0;
        
        this.installationSlots = new ArrayList<Object>();
        
        Installation[] installed = Installer.getInstallations();
        for(Installation installation : installed) installationSlots.add(installation);
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return this.installationSlots.size();
    }

    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int par1, boolean par2)
    {
    	this.selectedSlot = this.installationSlots.get(par1);
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int par1)
    {
        return this.installationSlots.get(par1) == selectedSlot;
    }

    protected int getContentHeight()
    {
        return this.getSize() * CELL_HEIGHT;
    }

    public Object getSelectingSlot()
    {
    	return this.selectedSlot;
    }
    
    protected void drawBackground()
    {
        this.utopianGui.drawDefaultBackground();
    }

    private static int PADDING_TOP = 2;
    private static int PADDING_LEFT = 2;
    private static int INTERPOLATION = 12;
    
    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        super.SLOT_BOX_WIDTH = this.utopianGui.width / 2;
    	
    	Object drawingSlot = this.installationSlots.get(par1);
        if(drawingSlot instanceof Patch) this.utopianGui.drawString(fontRenderer, "" + EnumChatFormatting.RED + "# " + EnumChatFormatting.RESET + ((Patch)drawingSlot).getDescription().get("name"), PADDING_LEFT, par3 + PADDING_TOP, 16777215);
        else if(drawingSlot instanceof Plugin) this.utopianGui.drawString(fontRenderer, "" + EnumChatFormatting.GREEN + "+ " + EnumChatFormatting.RESET + ((Plugin)drawingSlot).getDescription().get("name"), PADDING_LEFT, par3 + PADDING_TOP, 16777215);
        this.utopianGui.drawString(fontRenderer, "" + EnumChatFormatting.GRAY + drawingSlot.getClass().getPackage().getName(), 2, par3  + INTERPOLATION, 16777215);
    }
}
