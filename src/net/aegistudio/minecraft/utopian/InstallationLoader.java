package net.aegistudio.minecraft.utopian;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.sound.midi.Patch;

import net.aegistudio.json.JSONObject;
import net.aegistudio.minecraft.utopian.plugin.Plugin;
import net.aegistudio.minecraft.utopian.util.JSONFile;

public class InstallationLoader
{
	private final ArrayList<Class<?>> installation_patches = new ArrayList<Class<?>>();
	
	private final ArrayList<Class<?>> installation_plugins = new ArrayList<Class<?>>();

	private static InstallationLoader loader;
	
	protected InstallationLoader()
	{
		try
		{
			installation_patches.add(Class.forName("net.aegistudio.minecraft.utopian.patch.core.Patch_MinecraftUtopian"));
			installation_patches.add(Class.forName("net.aegistudio.minecraft.utopian.patch.core.Patch_GuiMainSplash"));
			installation_patches.add(Class.forName("net.aegistudio.minecraft.utopian.patch.core.Patch_GuiUtopian"));
			installation_patches.add(Class.forName("net.aegistudio.minecraft.utopian.patch.core.Patch_BlockInfoContainer"));
			installation_patches.add(Class.forName("net.aegistudio.minecraft.utopian.patch.core.Patch_ItemInfoContainer"));
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	static InstallationLoader getInstallationLoader()
	{
		return loader;
	}

	public static final String INSTALL_ENTRY_NAME = "main.json";
	public static final String LOOKUPS_KEY = "lookups";
	
	private void loadAndRegisterClass(ClassLoader classloader, String classname) throws Exception
	{
		Class<?> loadedclass = classloader.loadClass(classname);
		if(Patch.class.isAssignableFrom(loadedclass)) installation_patches.add(loadedclass);
		else if(Plugin.class.isAssignableFrom(loadedclass)) installation_plugins.add(loadedclass);
	}
	
	void scanningPluginsAndPatches() throws Exception
	{
		File patches_path = new File((String) ClientConfiguration.getConfig().getKey(ClientConfiguration.PATCHES_PATH));
		File plugins_path = new File((String) ClientConfiguration.getConfig().getKey(ClientConfiguration.PLUGINS_PATH));
		
		if(!patches_path.isDirectory() || !patches_path.exists()) patches_path.mkdir();
		if(!plugins_path.isDirectory() || !plugins_path.exists()) plugins_path.mkdir();
		
		ArrayList<File> installation_file = new ArrayList<File>();
		listUnderlyingFiles(installation_file, patches_path);
		listUnderlyingFiles(installation_file, plugins_path);
		
		for(File file : installation_file) if(file.isFile())
		{
			ClassLoader classloader = new URLClassLoader(new URL[]{file.toURI().toURL()});
			
			ZipFile zipfile = null;
			
			if(file.getName().endsWith(".jar")) zipfile = new JarFile(file);
			if(file.getName().endsWith(".zip")) zipfile = new ZipFile(file); 
			
			if(zipfile != null)
			{
				ZipEntry config_entry = zipfile.getEntry(INSTALL_ENTRY_NAME);
				if(config_entry != null) try
				{
					JSONFile install_entry = new JSONFile(zipfile.getInputStream(config_entry));
					JSONObject object = install_entry.getObject();
					Object lookups = object.get(LOOKUPS_KEY);
					if(lookups instanceof String) this.loadAndRegisterClass(classloader, (String)lookups);
					else if(lookups instanceof Object[])
					{
						for(Object tolookup : (Object[])lookups)
							if(tolookup instanceof String) this.loadAndRegisterClass(classloader, (String)tolookup);
					}
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
				}
				zipfile.close();
			}
		}
	}
	
	private void listUnderlyingFiles(ArrayList<File> filelist, File root)
	{
		if(!root.isDirectory()) filelist.add(root);
		else
		{
			File[] underlying_filelist = root.listFiles();
			for(File file : underlying_filelist) listUnderlyingFiles(filelist, file);
		}
	}
	
	void installPatches()
	{
		installFeatures(this.installation_patches);
	}
	
	void installPlugins()
	{
		installFeatures(this.installation_plugins);
	}
	
	private void installFeatures(ArrayList<Class<?>> installation_classpathes)
	{
		for(Class<?> installation_class : installation_classpathes) try
		{
			if(UtopianLoader.is_server && installation_class.isAnnotationPresent(Dedicated.Client.class)) continue;
			if(!UtopianLoader.is_server && installation_class.isAnnotationPresent(Dedicated.Server.class)) continue;
			
			Installation installation_object = (Installation) installation_class.newInstance();
			
			if(Installer.install(installation_object)) System.out.println(installation_object.getClass().getName());
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}