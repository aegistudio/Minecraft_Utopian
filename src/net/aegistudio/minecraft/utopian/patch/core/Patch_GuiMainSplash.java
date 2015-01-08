package net.aegistudio.minecraft.utopian.patch.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import net.aegistudio.json.JSONObject;
import net.aegistudio.minecraft.utopian.patch.Patch;
import net.minecraft.client.gui.main.GuiMainMenu;
import net.minecraft.client.gui.main.ISplashLoader;


public class Patch_GuiMainSplash implements Patch, ISplashLoader
{
	
	private ArrayList<String> splashes = null;
	private Random rand = null;
	
	@Override
	public boolean install() throws Exception
	{
		Field field_splash_reader = GuiMainMenu.class.getDeclaredField("splash_loader");
		field_splash_reader.setAccessible(true);
		field_splash_reader.set(null, this);
		field_splash_reader.setAccessible(false);
		
		Field field_rand = GuiMainMenu.class.getDeclaredField("rand");
		field_rand.setAccessible(true);
		this.rand = (Random)(field_rand.get(null));
		field_rand.setAccessible(false);
		
		return true;
	}

	@Override
	public String getSplashText()
	{
		String festival_text = null;
		if((festival_text = this.getFestivalSplashText()) != null) return festival_text;
		
		if(splashes == null)
		{
			BufferedReader splashes_reader = null;
	        String string_temp;

	        try
	        {
	            splashes = new ArrayList<String>();
	            splashes_reader = new BufferedReader(new InputStreamReader(Patch_GuiMainSplash.class.getResourceAsStream("/title/splashes.txt"), Charset.forName("UTF-8")));
	
	            while ((string_temp = splashes_reader.readLine()) != null)
	            {
	                string_temp = string_temp.trim();
	
	                if (string_temp.length() > 0)
	                {
	                    splashes.add(string_temp);
	                }
	            }
	        }
	        catch (IOException ioexception)	{        }
	        finally
	        {
	            if (splashes_reader != null)
	            {
	                try
	                {
	                    splashes_reader.close();
	                }
	                catch (IOException ioexception)	{      }
	            }
	        }
		}
		
        String splashText = null;
        do
        {
            splashText = (String)splashes.get(rand.nextInt(splashes.size()));
        }
        while (splashText.hashCode() == 125780783);
        
		return splashText;
	}
	
	private static final HashMap<Integer, HashMap<Integer, String>> festival_text = new HashMap<Integer, HashMap<Integer, String>>();
	
	public static void putFestivalSplashText(int month, int day, String splash_text)
	{
		if(festival_text.get(month) == null) festival_text.put(month, new HashMap<Integer, String>());
		festival_text.get(month).put(day, splash_text);
	}
	
	static
	{
		putFestivalSplashText(11, 9, "Happy birthday, ez!");
		putFestivalSplashText(6, 1, "Happy birthday, Notch!");
		putFestivalSplashText(12, 24, "Merry X-mas!");
		putFestivalSplashText(1, 1, "Happy new year!");
		putFestivalSplashText(10, 31, "OOoooOOOoooo! Spooky!");
	}
	
	public String getFestivalSplashText()
	{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
		return festival_text.get(calendar.get(Calendar.MONTH) + 1).get(calendar.get(Calendar.DAY_OF_MONTH));
	}

	private static final JSONObject patch_guimainsplash = new JSONObject();
	static
	{
		patch_guimainsplash.set("name", "MainGUI SplashLoader");
		patch_guimainsplash.set("version", "0.0.0.1 alpha");
		patch_guimainsplash.set("features", new String[]{
				"The original implementation of Minecraft ",
				"Main GUI will read data from the disk ",
				"everytime a new splash text is needed, ",
				"which is very time comsuming, as I think.", 
				"So I rewrote the splash text loading way."
		});
	}
	
	@Override
	public JSONObject getDescription()
	{
		return patch_guimainsplash;
	}
}
