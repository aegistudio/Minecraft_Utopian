package net.minecraft.client.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import net.minecraft.client.settings.EnumOptions;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.StatCollector;

public class GuiSnooper extends GuiScreen
{
    /** Instance of GuiScreen. */
    private final GuiScreen snooperGuiScreen;

    /** Instance of GameSettings. */
    private final GameSettings snooperGameSettings;
    private final List<String> snooperStatKey = new ArrayList<String>();
    private final List<String> snooperStatValue = new ArrayList<String>();

    /** The Snooper title. */
    private String snooperTitle;
    private String[] field_74101_n;
    private GuiSnooperList snooperList;
    private GuiButton buttonAllowSnooping;

    public GuiSnooper(GuiScreen par1GuiScreen, GameSettings par2GameSettings)
    {
        this.snooperGuiScreen = par1GuiScreen;
        this.snooperGameSettings = par2GameSettings;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.snooperTitle = StatCollector.translateToLocal("options.snooper.title");
        String var1 = StatCollector.translateToLocal("options.snooper.desc");
        ArrayList<String> var2 = new ArrayList<String>();
        Iterator<String> var3 = this.fontRenderer.listFormattedStringToWidth(var1, this.width - 30).iterator();

        while (var3.hasNext())
        {
            String var4 = (String)var3.next();
            var2.add(var4);
        }

        this.field_74101_n = (String[])var2.toArray(new String[0]);
        this.snooperStatKey.clear();
        this.snooperStatValue.clear();
        this.buttonList.add(this.buttonAllowSnooping = new GuiButton(1, this.width / 2 - 152, this.height - 30, 150, 20, this.snooperGameSettings.getKeyBinding(EnumOptions.SNOOPER_ENABLED)));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height - 30, 150, 20, StatCollector.translateToLocal("gui.done")));
        boolean var6 = this.mc.getIntegratedServer() != null && this.mc.getIntegratedServer().getPlayerUsageSnooper() != null;
        Iterator<Entry<String, String>> var7 = (new TreeMap<String, String>(this.mc.getPlayerUsageSnooper().getCurrentStats())).entrySet().iterator();
        Entry<String, String> statEntry;

        while (var7.hasNext())
        {
            statEntry = var7.next();
            this.snooperStatKey.add((var6 ? "C " : "") + (String)statEntry.getKey());
            this.snooperStatValue.add(this.fontRenderer.trimStringToWidth((String)statEntry.getValue(), this.width - 220));
        }

        if (var6)
        {
            var7 = (new TreeMap<String, String>(this.mc.getIntegratedServer().getPlayerUsageSnooper().getCurrentStats())).entrySet().iterator();

            while (var7.hasNext())
            {
                statEntry = var7.next();
                this.snooperStatKey.add("S " + (String)statEntry.getKey());
                this.snooperStatValue.add(this.fontRenderer.trimStringToWidth((String)statEntry.getValue(), this.width - 220));
            }
        }

        this.snooperList = new GuiSnooperList(this);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 2)
            {
                this.snooperGameSettings.saveOptions();
                this.snooperGameSettings.saveOptions();
                this.mc.displayGuiScreen(this.snooperGuiScreen);
            }

            if (par1GuiButton.id == 1)
            {
                this.snooperGameSettings.setOptionValue(EnumOptions.SNOOPER_ENABLED, 1);
                this.buttonAllowSnooping.displayString = this.snooperGameSettings.getKeyBinding(EnumOptions.SNOOPER_ENABLED);
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.snooperList.drawScreen(par1, par2, par3);
        this.drawCenteredString(this.fontRenderer, this.snooperTitle, this.width / 2, 8, 16777215);
        int var4 = 22;
        String[] var5 = this.field_74101_n;
        int var6 = var5.length;

        for (int var7 = 0; var7 < var6; ++var7)
        {
            String var8 = var5[var7];
            this.drawCenteredString(this.fontRenderer, var8, this.width / 2, var4, 8421504);
            var4 += this.fontRenderer.FONT_HEIGHT;
        }

        super.drawScreen(par1, par2, par3);
    }

    static List<String> getSnooperStatKeys(GuiSnooper guiSnooper)
    {
        return guiSnooper.snooperStatKey;
    }

    static List<String> getSnooperStatValues(GuiSnooper guiSnooper)
    {
        return guiSnooper.snooperStatValue;
    }
}
