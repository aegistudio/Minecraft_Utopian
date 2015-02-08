package net.minecraft.client.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StringTranslate;

class GuiSlotLanguage extends GuiSlot
{
    private ArrayList<String> language_slots;
    private TreeMap<String, String> language_list;

    final GuiLanguage languageGui;

    public GuiSlotLanguage(GuiLanguage par1GuiLanguage)
    {
        super(par1GuiLanguage.mc, par1GuiLanguage.width, par1GuiLanguage.height, 32, par1GuiLanguage.height - 65 + 4, 18);
        this.languageGui = par1GuiLanguage;
        this.language_list = StringTranslate.getInstance().getLanguageList();
        this.language_slots = new ArrayList<String>();
        Iterator<String> language_list_iterator = this.language_list.keySet().iterator();

        while (language_list_iterator.hasNext())
        {
            String language_string = (String)language_list_iterator.next();
            this.language_slots.add(language_string);
        }
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return this.language_slots.size();
    }

    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int par1, boolean par2)
    {
        StringTranslate.getInstance().setLanguage((String)this.language_slots.get(par1), false);
        this.languageGui.mc.fontRenderer.setUnicodeFlag(StringTranslate.getInstance().isUnicode());
        GuiLanguage.getGameSettings(this.languageGui).language = (String)this.language_slots.get(par1);
        this.languageGui.fontRenderer.setBidiFlag(StringTranslate.isBidirectional(GuiLanguage.getGameSettings(this.languageGui).language));
        GuiLanguage.getDoneButton(this.languageGui).displayString = StringTranslate.getInstance().translateKey("gui.done");
        GuiLanguage.getGameSettings(this.languageGui).saveOptions();
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int par1)
    {
        return ((String)this.language_slots.get(par1)).equals(StringTranslate.getInstance().getCurrentLanguage());
    }

    /**
     * return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return this.getSize() * 18;
    }

    protected void drawBackground()
    {
        this.languageGui.drawDefaultBackground();
    }

    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        this.languageGui.fontRenderer.setBidiFlag(true);
        this.languageGui.drawCenteredString(this.languageGui.fontRenderer, (String)this.language_list.get(this.language_slots.get(par1)), this.languageGui.width / 2, par3 + 1, 16777215);
        this.languageGui.fontRenderer.setBidiFlag(StringTranslate.isBidirectional(GuiLanguage.getGameSettings(this.languageGui).language));
    }
}
