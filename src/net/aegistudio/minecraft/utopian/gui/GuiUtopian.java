package net.aegistudio.minecraft.utopian.gui;

import net.aegistudio.minecraft.utopian.patch.Patch;
import net.aegistudio.minecraft.utopian.plugin.Plugin;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.util.EnumChatFormatting;

public class GuiUtopian extends GuiScreen
{
    protected GuiScreen parentGui;

    private GuiUtopianPatchAndPluginList installationList;
    private int updateTimer = -1;

    private GuiSmallButton doneButton;

    public GuiUtopian(GuiScreen guiScreen)
    {
        this.parentGui = guiScreen;
    }

    @Override
    public void initGui()
    {
    	//XXX Adding done button to the gui.
    	
        this.doneButton = new GuiSmallButton(6, this.width / 2 - 75, this.height - 28, "Back to Main Menu");
        this.buttonList.add(this.doneButton);
        
        //XXX Adding patches and plugin slots to the gui.
        
        this.installationList = new GuiUtopianPatchAndPluginList(this.mc, this.fontRenderer, this);
        this.installationList.registerScrollButtons(this.buttonList, 0, 8);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.enabled) switch (button.id)
        {
            case 6:
                this.mc.displayGuiScreen(this.parentGui);
                break;

            default:
                this.installationList.actionPerformed(button);
        }
    }

    private static int INTERPOLATION = 12;
    private static int PADDING_INFOMATION_LEFT = 7;
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawDefaultBackground();
    	
    	this.installationList.drawScreen(par1, par2, par3);
    	
        if (this.updateTimer <= 0)
        {
            this.mc.texturePackList.updateAvaliableTexturePacks();
            this.updateTimer += 20;
        }
        
        this.drawCenteredString(this.fontRenderer, "" + EnumChatFormatting.BOLD + "Minecraft Utopian Patches & Plugins", this.width / 2, 10, 16777215);
        
        this.drawCenteredString(this.fontRenderer, "Installation Info", this.width * 3/ 4, GuiUtopianPatchAndPluginList.UPPER_MARGIN_SLOT, 16777215);
        Object object = this.installationList.getSelectingSlot();
        String selecting_name = null;
        String selecting_type = null;
        String selecting_version = null;
        Object[] selecting_features = null;
        
        if(object instanceof Patch)
        {
        	Patch patch = (Patch) object;
        	selecting_type = "" +  EnumChatFormatting.RED + "Patch";
        	selecting_name = (String) patch.getDescription().get("name");
        	selecting_version = (String) patch.getDescription().get("version");
        	selecting_features = (Object[]) patch.getDescription().get("features");
        }
        else if(object instanceof Plugin)
        {
        	Plugin plugin = (Plugin) object;
        	selecting_type = "" +  EnumChatFormatting.GREEN + "Plugin";
        	selecting_name = (String) plugin.getDescription().get("name");
        	selecting_version = (String) plugin.getDescription().get("version");
        	selecting_features = (Object[]) plugin.getDescription().get("features");
        }
        
        String NOT_PROVIDED = "(NOT PROVIDED)";
        this.drawString(fontRenderer, "Name: " +  EnumChatFormatting.GRAY + (selecting_name == null? NOT_PROVIDED : selecting_name), this.width/2 + PADDING_INFOMATION_LEFT, GuiUtopianPatchAndPluginList.UPPER_MARGIN_SLOT + INTERPOLATION, 16777215);
        this.drawString(fontRenderer, "Type: " +  EnumChatFormatting.GRAY + (selecting_type == null? NOT_PROVIDED : selecting_type), this.width/2 + PADDING_INFOMATION_LEFT, GuiUtopianPatchAndPluginList.UPPER_MARGIN_SLOT + 2 * INTERPOLATION, 16777215);
        this.drawString(fontRenderer, "Version: " + EnumChatFormatting.GRAY + (selecting_version == null? NOT_PROVIDED : selecting_version), this.width/2 + PADDING_INFOMATION_LEFT, GuiUtopianPatchAndPluginList.UPPER_MARGIN_SLOT + 3 * INTERPOLATION, 16777215);
        this.drawString(fontRenderer, "Descriptions: " + EnumChatFormatting.GRAY + (selecting_features == null? NOT_PROVIDED : ""), this.width/2 + PADDING_INFOMATION_LEFT, GuiUtopianPatchAndPluginList.UPPER_MARGIN_SLOT + 4 * INTERPOLATION, 16777215);
        if(selecting_features != null) for(int i = 0; i < selecting_features.length; i ++)
        	this.drawString(fontRenderer, "" +  EnumChatFormatting.GRAY + (String) selecting_features[i], this.width/2 + PADDING_INFOMATION_LEFT, GuiUtopianPatchAndPluginList.UPPER_MARGIN_SLOT + (5 + i) * INTERPOLATION, 16777215);
        
        this.drawCenteredString(this.fontRenderer, "" + EnumChatFormatting.RED + "Patches" + EnumChatFormatting.RESET + " means fixups that change the way minecraft works.", this.width / 2, this.height - 56, 8421504);
        this.drawCenteredString(this.fontRenderer, "" + EnumChatFormatting.GREEN + "Plugins" + EnumChatFormatting.RESET + " means codes that are loaded into game and can be played.", this.width / 2, this.height - 40, 8421504);
    	
        super.drawScreen(par1, par2, par3);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        --this.updateTimer;
    }
}
