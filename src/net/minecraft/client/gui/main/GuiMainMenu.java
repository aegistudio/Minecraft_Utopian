package net.minecraft.client.gui.main;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOnlineServers;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class GuiMainMenu extends GuiScreen
{
    /** The RNG used by the Main Menu Screen. */
    private static final Random rand = new Random();

    /** Counts the number of screen updates. */
    private float updateCounter = 0.0F;

    /** The splash message. */
    private String splashText = "missingno";
    private GuiButton buttonResetDemo;

    /** Timer used to rotate the panorama, increases every tick. */
    private int panoramaTimer = 0;

    /**
     * Texture allocated for the current viewport of the main menu's panorama background.
     */
    private int viewportTexture;
    private boolean field_96141_q = true;
    private static boolean field_96140_r = false;
    private static boolean field_96139_s = false;
    private final Object field_104025_t = new Object();
    private String notice_text;
    private String java_ppc_url;

    /** An array of all the paths to the panorama pictures. */
    private static final String[] titlePanoramaPaths = new String[] {"/title/bg/panorama0.png", "/title/bg/panorama1.png", "/title/bg/panorama2.png", "/title/bg/panorama3.png", "/title/bg/panorama4.png", "/title/bg/panorama5.png"};
    public static final String url_display_component = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
    private int url_display_width;
    private int notice_text_width;
    private int field_92022_t;
    private int field_92021_u;
    private int field_92020_v;
    private int field_92019_w;

    private static ISplashLoader splash_loader = null;
    
    public GuiMainMenu()
    {
    	this.splashText = splash_loader.getSplashText();
    	
    //    String string_temp;

        this.updateCounter = rand.nextFloat();
        this.notice_text = "";
   /*	String var14 = System.getProperty("os_architecture");
        string_temp = System.getProperty("java_version");

        if ("ppc".equalsIgnoreCase(var14))
        {
            this.notice_text = "" + EnumChatFormatting.BOLD + "Notice!" + EnumChatFormatting.RESET + " PowerPC compatibility will be dropped in Minecraft 1.6";
            this.java_ppc_url = "http://tinyurl.com/javappc";
        }
        else if (string_temp != null && string_temp.startsWith("1.5"))
        {
            this.notice_text = "" + EnumChatFormatting.BOLD + "Notice!" + EnumChatFormatting.RESET + " Java 1.5 compatibility will be dropped in Minecraft 1.6";
            this.java_ppc_url = "http://tinyurl.com/javappc";
        }

        if (this.notice_text.length() == 0)
        {
            (new Thread(new RunnableTitleScreen(this), "1.6 Update Check Thread")).start();
        }
        */
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        ++this.panoramaTimer;
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2) {}

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.viewportTexture = this.mc.renderEngine.allocateAndSetupTexture(new BufferedImage(256, 256, 2));

        StringTranslate translate = StringTranslate.getInstance();
        int var4 = this.height / 4 + 48;

        if (this.mc.isDemo())
        {
            this.addDemoButtons(var4, 24, translate);
        }
        else
        {
            this.addSingleplayerMultiplayerButtons(var4, 24, translate);
        }

        this.func_96137_a(translate, var4, 24);

        if (this.mc.hideQuitButton)
        {
            this.buttonList.add(new GuiButton(0, this.width / 2 - 100, var4 + 72, translate.translateKey("menu.options")));
        }
        else
        {
            this.buttonList.add(new GuiButton(0, this.width / 2 - 100, var4 + 72 + 12, 98, 20, translate.translateKey("menu.options")));
            this.buttonList.add(new GuiButton(4, this.width / 2 + 2, var4 + 72 + 12, 98, 20, translate.translateKey("menu.quit")));
        }
        
        this.addUtopianButton(translate, var4, 24);
        
        this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, var4 + 72 + 12));
        //Object var5 = this.field_104025_t;

        synchronized (this.field_104025_t)
        {
            this.notice_text_width = this.fontRenderer.getStringWidth(this.notice_text);
            this.url_display_width = this.fontRenderer.getStringWidth(url_display_component);
            int bound_box_width = Math.max(this.notice_text_width, this.url_display_width);
            this.field_92022_t = (this.width - bound_box_width) / 2;
            this.field_92021_u = ((GuiButton)this.buttonList.get(0)).yPosition - 24;
            this.field_92020_v = this.field_92022_t + bound_box_width;
            this.field_92019_w = this.field_92021_u + 24;
        }
    }

    private void func_96137_a(StringTranslate par1StringTranslate, int par2, int par3)
    {
        if (this.field_96141_q)
        {
            if (!field_96140_r)
            {
                field_96140_r = true;
                (new ThreadTitleScreen(this, par1StringTranslate, par2, par3)).start();
            }
            else if (field_96139_s)
            {
                this.addOnlineButton(par1StringTranslate, par2, par3);
            }
        }
    }

    private void addOnlineButton(StringTranslate par1StringTranslate, int par2, int par3)
    {
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, par2 + par3 * 2, par1StringTranslate.translateKey("menu.online")));
    }
    
    private static final int UTOPIAN_BUTTON_ID = 100;
    private static String UTOPIAN_GUI_CLASS = "";
    private static String UTOPIAN_BUTTON_TAG = "Minecraft.Utopian";
    
    private void addUtopianButton(StringTranslate par1StringTranslate, int par2, int par3)
    {
        this.buttonList.add(new GuiButton(UTOPIAN_BUTTON_ID, this.width / 2 - 100, par2 + par3 * 2, UTOPIAN_BUTTON_TAG));
    }

    /**
     * Adds Singleplayer and Multiplayer buttons on Main Menu for players who have bought the game.
     */
    private void addSingleplayerMultiplayerButtons(int par1, int par2, StringTranslate par3StringTranslate)
    {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, par1, par3StringTranslate.translateKey("menu.singleplayer")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, par1 + par2 * 1, par3StringTranslate.translateKey("menu.multiplayer")));
    }

    /**
     * Adds Demo buttons on Main Menu for players who are playing Demo.
     */
    private void addDemoButtons(int par1, int par2, StringTranslate par3StringTranslate)
    {
        this.buttonList.add(new GuiButton(11, this.width / 2 - 100, par1, par3StringTranslate.translateKey("menu.playdemo")));
        this.buttonList.add(this.buttonResetDemo = new GuiButton(12, this.width / 2 - 100, par1 + par2 * 1, par3StringTranslate.translateKey("menu.resetdemo")));
        ISaveFormat var4 = this.mc.getSaveLoader();
        WorldInfo var5 = var4.getWorldInfo("Demo_World");

        if (var5 == null)
        {
            this.buttonResetDemo.enabled = false;
        }
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton gui_button)
    {
        if (gui_button.id == 0)
        {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        if (gui_button.id == 5)
        {
            this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings));
        }

        if (gui_button.id == 1)
        {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }

        if (gui_button.id == 2)
        {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        if (gui_button.id == 3)
        {
            this.mc.displayGuiScreen(new GuiScreenOnlineServers(this));
        }

        if (gui_button.id == 4)
        {
            this.mc.shutdown();
        }

        if (gui_button.id == 11)
        {
            this.mc.launchIntegratedServer("Demo_World", "Demo_World", DemoWorldServer.demoWorldSettings);
        }

        if (gui_button.id == 12)
        {
            ISaveFormat var2 = this.mc.getSaveLoader();
            WorldInfo var3 = var2.getWorldInfo("Demo_World");

            if (var3 != null)
            {
                GuiYesNo var4 = GuiSelectWorld.getDeleteWorldScreen(this, var3.getWorldName(), 12);
                this.mc.displayGuiScreen(var4);
            }
        }
        
        if (gui_button.id == UTOPIAN_BUTTON_ID)
        {
        	try
        	{
        		Class<?> utopian_gui_class = Class.forName(UTOPIAN_GUI_CLASS);
        		GuiScreen utopian_gui = (GuiScreen)(utopian_gui_class.getConstructor(GuiScreen.class).newInstance(this));
        		this.mc.displayGuiScreen(utopian_gui);
        	}
        	catch(Exception exception)
        	{
        		
        	}
        }
    }

    public void confirmClicked(boolean par1, int par2)
    {
        if (par1 && par2 == 12)
        {
            ISaveFormat var6 = this.mc.getSaveLoader();
            var6.flushCache();
            var6.deleteWorldDirectory("Demo_World");
            this.mc.displayGuiScreen(this);
        }
        else if (par2 == 13)
        {
            if (par1)
            {
                try
                {
                    Class<?> var3 = Class.forName("java.awt.Desktop");
                    Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
                    var3.getMethod("browse", new Class[] {URI.class}).invoke(var4, new Object[] {new URI(this.java_ppc_url)});
                }
                catch (Throwable var5)
                {
                    var5.printStackTrace();
                }
            }

            this.mc.displayGuiScreen(this);
        }
    }

    /**
     * Draws the main menu panorama
     */
    private void drawPanorama(int par1, int par2, float par3)
    {
        Tessellator var4 = Tessellator.instance;
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GLU.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        byte var5 = 8;

        for (int var6 = 0; var6 < var5 * var5; ++var6)
        {
            GL11.glPushMatrix();
            float var7 = ((float)(var6 % var5) / (float)var5 - 0.5F) / 64.0F;
            float var8 = ((float)(var6 / var5) / (float)var5 - 0.5F) / 64.0F;
            float var9 = 0.0F;
            GL11.glTranslatef(var7, var8, var9);
            GL11.glRotatef(MathHelper.sin(((float)this.panoramaTimer + par3) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-((float)this.panoramaTimer + par3) * 0.1F, 0.0F, 1.0F, 0.0F);

            for (int var10 = 0; var10 < 6; ++var10)
            {
                GL11.glPushMatrix();

                if (var10 == 1)
                {
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (var10 == 2)
                {
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                }

                if (var10 == 3)
                {
                    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (var10 == 4)
                {
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (var10 == 5)
                {
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                this.mc.renderEngine.bindTexture(titlePanoramaPaths[var10]);
                var4.startDrawingQuads();
                var4.setColorRGBA_I(16777215, 255 / (var6 + 1));
                float var11 = 0.0F;
                var4.addVertexWithUV(-1.0D, -1.0D, 1.0D, (double)(0.0F + var11), (double)(0.0F + var11));
                var4.addVertexWithUV(1.0D, -1.0D, 1.0D, (double)(1.0F - var11), (double)(0.0F + var11));
                var4.addVertexWithUV(1.0D, 1.0D, 1.0D, (double)(1.0F - var11), (double)(1.0F - var11));
                var4.addVertexWithUV(-1.0D, 1.0D, 1.0D, (double)(0.0F + var11), (double)(1.0F - var11));
                var4.draw();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }

        var4.setTranslation(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    /**
     * Rotate and blurs the skybox view in the main menu
     */
    private void rotateAndBlurSkybox(float par1)
    {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.viewportTexture);
        this.mc.renderEngine.resetBoundTexture();
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColorMask(true, true, true, false);
        Tessellator var2 = Tessellator.instance;
        var2.startDrawingQuads();
        byte var3 = 3;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            var2.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F / (float)(var4 + 1));
            int var5 = this.width;
            int var6 = this.height;
            float var7 = (float)(var4 - var3 / 2) / 256.0F;
            var2.addVertexWithUV((double)var5, (double)var6, (double)this.zLevel, (double)(0.0F + var7), 0.0D);
            var2.addVertexWithUV((double)var5, 0.0D, (double)this.zLevel, (double)(1.0F + var7), 0.0D);
            var2.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, (double)(1.0F + var7), 1.0D);
            var2.addVertexWithUV(0.0D, (double)var6, (double)this.zLevel, (double)(0.0F + var7), 1.0D);
        }

        var2.draw();
        GL11.glColorMask(true, true, true, true);
        this.mc.renderEngine.resetBoundTexture();
    }

    /**
     * Renders the skybox in the main menu
     */
    private void renderSkybox(int par1, int par2, float par3)
    {
        GL11.glViewport(0, 0, 256, 256);
        this.drawPanorama(par1, par2, par3);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        Tessellator var4 = Tessellator.instance;
        var4.startDrawingQuads();
        float var5 = this.width > this.height ? 120.0F / (float)this.width : 120.0F / (float)this.height;
        float var6 = (float)this.height * var5 / 256.0F;
        float var7 = (float)this.width * var5 / 256.0F;
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        var4.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int var8 = this.width;
        int var9 = this.height;
        var4.addVertexWithUV(0.0D, (double)var9, (double)this.zLevel, (double)(0.5F - var6), (double)(0.5F + var7));
        var4.addVertexWithUV((double)var8, (double)var9, (double)this.zLevel, (double)(0.5F - var6), (double)(0.5F - var7));
        var4.addVertexWithUV((double)var8, 0.0D, (double)this.zLevel, (double)(0.5F + var6), (double)(0.5F - var7));
        var4.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, (double)(0.5F + var6), (double)(0.5F + var7));
        var4.draw();
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.renderSkybox(par1, par2, par3);
        Tessellator var4 = Tessellator.instance;
        short var5 = 274;
        int var6 = this.width / 2 - var5 / 2;
        byte var7 = 30;
        this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
        this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
        this.mc.renderEngine.bindTexture("/title/mclogo.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        if ((double)this.updateCounter < 1.0E-4D)
        {
            this.drawTexturedModalRect(var6 + 0, var7 + 0, 0, 0, 99, 44);
            this.drawTexturedModalRect(var6 + 99, var7 + 0, 129, 0, 27, 44);
            this.drawTexturedModalRect(var6 + 99 + 26, var7 + 0, 126, 0, 3, 44);
            this.drawTexturedModalRect(var6 + 99 + 26 + 3, var7 + 0, 99, 0, 26, 44);
            this.drawTexturedModalRect(var6 + 155, var7 + 0, 0, 45, 155, 44);
        }
        else
        {
            this.drawTexturedModalRect(var6 + 0, var7 + 0, 0, 0, 155, 44);
            this.drawTexturedModalRect(var6 + 155, var7 + 0, 0, 45, 155, 44);
        }

        var4.setColorOpaque_I(16777215);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.width / 2 + 90), 70.0F, 0.0F);
        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
        float var8 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
        var8 = var8 * 100.0F / (float)(this.fontRenderer.getStringWidth(this.splashText) + 32);
        GL11.glScalef(var8, var8, var8);
        this.drawCenteredString(this.fontRenderer, this.splashText, 0, -8, 16776960);
        GL11.glPopMatrix();
        String minecraft_version = "Minecraft Utopian";

        if (this.mc.isDemo())
        {
            minecraft_version = minecraft_version + " Demo";
        }

        this.drawString(this.fontRenderer, minecraft_version, 2, this.height - 10, 16777215);
        String copyright = "Copyright Mojang AB. Do not distribute!";
        this.drawString(this.fontRenderer, copyright, this.width - this.fontRenderer.getStringWidth(copyright) - 2, this.height - 10, 16777215);
        
        String experiment_only = "" + EnumChatFormatting.BOLD + "Notice: " + EnumChatFormatting.RESET + "This minecraft is experiment-only.";
        this.drawString(this.fontRenderer, experiment_only, this.width - this.fontRenderer.getStringWidth(experiment_only) - 2, this.height - 20, 16777215);
        
        if (this.notice_text != null && this.notice_text.length() > 0)
        {
            drawRect(this.field_92022_t - 2, this.field_92021_u - 2, this.field_92020_v + 2, this.field_92019_w - 1, 1428160512);
            this.drawString(this.fontRenderer, this.notice_text, this.field_92022_t, this.field_92021_u, 16777215);
            this.drawString(this.fontRenderer, url_display_component, (this.width - this.url_display_width) / 2, ((GuiButton)this.buttonList.get(0)).yPosition - 12, 16777215);
        }

        super.drawScreen(par1, par2, par3);
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        //Object var4 = this.field_104025_t;

        synchronized (this.field_104025_t)
        {
            if (this.notice_text.length() > 0 && par1 >= this.field_92022_t && par1 <= this.field_92020_v && par2 >= this.field_92021_u && par2 <= this.field_92019_w)
            {
                GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, this.java_ppc_url, 13, true);
                var5.func_92026_h();
                this.mc.displayGuiScreen(var5);
            }
        }
    }

    static Object func_104004_a(GuiMainMenu par0GuiMainMenu)
    {
        return par0GuiMainMenu.field_104025_t;
    }

    static String func_104005_a(GuiMainMenu par0GuiMainMenu, String par1Str)
    {
        return par0GuiMainMenu.notice_text = par1Str;
    }

    static String func_104013_b(GuiMainMenu par0GuiMainMenu, String par1Str)
    {
        return par0GuiMainMenu.java_ppc_url = par1Str;
    }

    static int func_104006_a(GuiMainMenu par0GuiMainMenu, int par1)
    {
        return par0GuiMainMenu.notice_text_width = par1;
    }

    static String func_104023_b(GuiMainMenu par0GuiMainMenu)
    {
        return par0GuiMainMenu.notice_text;
    }

    static FontRenderer func_104022_c(GuiMainMenu par0GuiMainMenu)
    {
        return par0GuiMainMenu.fontRenderer;
    }

    static int func_104014_b(GuiMainMenu par0GuiMainMenu, int par1)
    {
        return par0GuiMainMenu.url_display_width = par1;
    }

    static FontRenderer func_104007_d(GuiMainMenu par0GuiMainMenu)
    {
        return par0GuiMainMenu.fontRenderer;
    }

    static int func_104016_e(GuiMainMenu par0GuiMainMenu)
    {
        return par0GuiMainMenu.notice_text_width;
    }

    static int func_104015_f(GuiMainMenu par0GuiMainMenu)
    {
        return par0GuiMainMenu.url_display_width;
    }

    static int func_104008_c(GuiMainMenu par0GuiMainMenu, int par1)
    {
        return par0GuiMainMenu.field_92022_t = par1;
    }

    static int func_104009_d(GuiMainMenu par0GuiMainMenu, int par1)
    {
        return par0GuiMainMenu.field_92021_u = par1;
    }

    static List<GuiButton> func_104019_g(GuiMainMenu par0GuiMainMenu)
    {
        return par0GuiMainMenu.buttonList;
    }

    static int func_104011_e(GuiMainMenu par0GuiMainMenu, int par1)
    {
        return par0GuiMainMenu.field_92020_v = par1;
    }

    static int func_104018_h(GuiMainMenu par0GuiMainMenu)
    {
        return par0GuiMainMenu.field_92022_t;
    }

    static int func_104012_f(GuiMainMenu par0GuiMainMenu, int par1)
    {
        return par0GuiMainMenu.field_92019_w = par1;
    }

    static int func_104020_i(GuiMainMenu par0GuiMainMenu)
    {
        return par0GuiMainMenu.field_92021_u;
    }

    static Minecraft func_98058_a(GuiMainMenu p_98058_0_)
    {
        return p_98058_0_.mc;
    }

    static void func_98061_a(GuiMainMenu p_98061_0_, StringTranslate p_98061_1_, int p_98061_2_, int p_98061_3_)
    {
        p_98061_0_.addOnlineButton(p_98061_1_, p_98061_2_, p_98061_3_);
    }

    static boolean func_98059_a(boolean p_98059_0_)
    {
        field_96139_s = p_98059_0_;
        return p_98059_0_;
    }
}
