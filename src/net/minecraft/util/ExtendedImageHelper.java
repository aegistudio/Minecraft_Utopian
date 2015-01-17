package net.minecraft.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;

public final class ExtendedImageHelper
{
	public static BufferedImage getExtendedImage(String filename, IOException e) throws IOException
	{
		int index = -1;
		if((index = filename.indexOf("./")) >= 0)
		{
			filename = filename.substring(index + "./".length());
			filename = filename.replace('/', File.separatorChar);
			return ImageIO.read(new File(Minecraft.getMinecraftDir(), filename));
		}
		else if((index = filename.indexOf("file://")) >= 0)
		{
			filename = filename.substring(index + "file://".length());
			filename = filename.replace('/', File.separatorChar);
			return ImageIO.read(new File(filename));
		}
		else throw e;
	}
	
	public static String trimTextureName(String filename)
	{
		String var12 = null;
		int index = -1;
		if((index = filename.indexOf("./")) >= 0)
		{
			filename = filename.substring(index + "./".length());
			var12 = "./" + filename.substring(0, filename.lastIndexOf('.'));
		}
		else if((index = filename.indexOf("file://")) >= 0)
		{
			filename = filename.substring(index + "file://".length());
			var12 = "file://" + filename.substring(0, filename.lastIndexOf('.'));
		}
		return var12;
	}
}
