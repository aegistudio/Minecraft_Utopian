package net.aegistudio.minecraft.utopian.ease;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

public class EntityRendererHelper
{
	private static IEntityRendererHelper instance = new IEntityRendererHelper()
	{

		@Override
		public void registerRenderer(Class<? extends Entity> tileEntityClass, Render renderer)	{		}

		@Override
		public void unregisterRenderer(Class<? extends Entity> tileEntityClass)	{	}
		
	};
	
	public static IEntityRendererHelper getInstance()
	{
		return instance;
	}
	
	private EntityRendererHelper()	{	}
	
	public interface IEntityRendererHelper
	{
		public abstract void registerRenderer(Class<? extends Entity> tileEntityClass, Render renderer);
		
		public abstract void unregisterRenderer(Class<? extends Entity> tileEntityClass);
	}
}
