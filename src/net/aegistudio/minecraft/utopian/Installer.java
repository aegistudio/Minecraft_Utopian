package net.aegistudio.minecraft.utopian;

import java.util.ArrayList;

public final class Installer
{
	private static final ArrayList<Installation> installations = new ArrayList<Installation>();
	
	public static boolean install(Installation install)
	{
		if(installations.contains(install)) return false;
		try
		{
			if(!install.install()) throw new Exception("Installation fails.");
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
		installations.add(install);
		return true;
	}
	
	public static Installation[] getInstallations()
	{
		return installations.toArray(new Installation[0]);
	}
}
