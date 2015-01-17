package net.minecraft.client.renderer.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.texturepacks.ITexturePack;
import net.minecraft.util.ExtendedImageHelper;

public class TextureManager
{
    private static TextureManager instance;
    private int nextTextureID = 0;
    private final HashMap texturesMap = new HashMap();
    private final HashMap mapNameToId = new HashMap();

    public static void init()
    {
        instance = new TextureManager();
    }

    public static TextureManager instance()
    {
        return instance;
    }

    public int getNextTextureId()
    {
        return this.nextTextureID++;
    }

    public void registerTexture(String par1Str, Texture par2Texture)
    {
        this.mapNameToId.put(par1Str, Integer.valueOf(par2Texture.getTextureId()));

        if (!this.texturesMap.containsKey(Integer.valueOf(par2Texture.getTextureId())))
        {
            this.texturesMap.put(Integer.valueOf(par2Texture.getTextureId()), par2Texture);
        }
    }

    public void registerTexture(Texture par1Texture)
    {
        if (this.texturesMap.containsValue(par1Texture))
        {
            Minecraft.getMinecraft().getLogAgent().logWarning("TextureManager.registerTexture called, but this texture has already been registered. ignoring.");
        }
        else
        {
            this.texturesMap.put(Integer.valueOf(par1Texture.getTextureId()), par1Texture);
        }
    }

    public Stitcher createStitcher(String par1Str)
    {
        int var2 = Minecraft.getGLMaximumTextureSize();
        return new Stitcher(par1Str, var2, var2, true);
    }

    public List createTexture(String filename)
    {
        ArrayList createdTextures = new ArrayList();
        ITexturePack texturepack = Minecraft.getMinecraft().texturePackList.getSelectedTexturePack();

        try
        {
        	BufferedImage image = null;
        	String var12 = null;
        	try
        	{
        		image = ImageIO.read(texturepack.getResourceAsStream("/" + filename));
        		var12 = this.getBasename(filename);
        	}
        	catch(IOException e)
        	{
        		image = ExtendedImageHelper.getExtendedImage(filename, e);
        		var12 = ExtendedImageHelper.trimTextureName(filename);
        	}
        	
            int height = image.getHeight();
            int width = image.getWidth();

            if (this.hasAnimationTxt(filename, texturepack))
            {
                int var13 = width;
                int var14 = width;
                int var15 = height / width;

                for (int var16 = 0; var16 < var15; ++var16)
                {
                    Texture var17 = this.makeTexture(var12, 2, var13, var14, 10496, 6408, 9728, 9728, false, image.getSubimage(0, var14 * var16, var13, var14));
                    createdTextures.add(var17);
                }
            }
            else if (width == height)
            {
                createdTextures.add(this.makeTexture(var12, 2, width, height, 10496, 6408, 9728, 9728, false, image));
            }
            else
            {
                Minecraft.getMinecraft().getLogAgent().logWarning("TextureManager.createTexture: Skipping " + filename + " because of broken aspect ratio and not animation");
            }

            return createdTextures;
        }
        catch (FileNotFoundException var18)
        {
            Minecraft.getMinecraft().getLogAgent().logWarning("TextureManager.createTexture called for file " + filename + ", but that file does not exist. Ignoring.");
        }
        catch (IOException var19)
        {
            Minecraft.getMinecraft().getLogAgent().logWarning("TextureManager.createTexture encountered an IOException when trying to read file " + filename + ". Ignoring.");
        }

        return createdTextures;
    }

    /**
     * Strips directory and file extension from the specified path, returning only the filename
     */
    private String getBasename(String par1Str)
    {
        File var2 = new File(par1Str);
        return var2.getName().substring(0, var2.getName().lastIndexOf(46));
    }

    /**
     * Returns true if specified texture pack contains animation data for the specified texture file
     */
    private boolean hasAnimationTxt(String par1Str, ITexturePack par2ITexturePack)
    {
        String var3 = "/" + par1Str.substring(0, par1Str.lastIndexOf(46)) + ".txt";
        boolean var4 = par2ITexturePack.func_98138_b("/" + par1Str, false);
        return Minecraft.getMinecraft().texturePackList.getSelectedTexturePack().func_98138_b(var3, !var4);
    }

    public Texture makeTexture(String par1Str, int par2, int par3, int par4, int par5, int par6, int par7, int par8, boolean par9, BufferedImage par10BufferedImage)
    {
        Texture var11 = new Texture(par1Str, par2, par3, par4, par5, par6, par7, par8, par10BufferedImage);
        this.registerTexture(var11);
        return var11;
    }

    public Texture createEmptyTexture(String par1Str, int par2, int par3, int par4, int par5)
    {
        return this.makeTexture(par1Str, par2, par3, par4, 10496, par5, 9728, 9728, false, (BufferedImage)null);
    }
}
