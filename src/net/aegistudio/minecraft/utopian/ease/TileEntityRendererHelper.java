package net.aegistudio.minecraft.utopian.ease;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRendererHelper
{
	private static ITileEntityRendererHelper instance = new ITileEntityRendererHelper()
	{
		public void registerSpecialRenderer(Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer renderer)	{	}
		
		public void unregisterSpecialRenderer(Class<? extends TileEntity> tileEntityClass)	{	}
	};
	
	public static ITileEntityRendererHelper getInstance()
	{
		return instance;
	}
	
	private TileEntityRendererHelper()	{	}
	
	public interface ITileEntityRendererHelper
	{
		public abstract void registerSpecialRenderer(Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer renderer);
		
		public abstract void unregisterSpecialRenderer(Class<? extends TileEntity> tileEntityClass);
	}
}
