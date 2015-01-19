package net.aegistudio.minecraft.utopian.patch.core;

import java.lang.reflect.Field;
import java.util.Map;

import net.aegistudio.json.JSONObject;
import net.aegistudio.minecraft.utopian.Dedicated;
import net.aegistudio.minecraft.utopian.ease.TileEntityRendererHelper;
import net.aegistudio.minecraft.utopian.ease.EntityRendererHelper;
import net.aegistudio.minecraft.utopian.event.EventHandler;
import net.aegistudio.minecraft.utopian.event.EventHandlerRegistry;
import net.aegistudio.minecraft.utopian.event.runtime.InitResourceEvent;
import net.aegistudio.minecraft.utopian.patch.Patch;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

@Dedicated.Client
public class Patch_RendererHelper implements Patch, TileEntityRendererHelper.ITileEntityRendererHelper, EntityRendererHelper.IEntityRendererHelper
{
	private Map<Class<?>, TileEntitySpecialRenderer> tileEntityMapper;
	private Map<Class<?>, Render> entityMapper;
	
	public Patch_RendererHelper()
	{
		EventHandlerRegistry.getEventHandlerRegistry().registerHandler(this, InitResourceEvent.class);
	}
	
	public void registerSpecialRenderer(Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer renderer)
	{
		tileEntityMapper.put(tileEntityClass, renderer);
	}
	
	public void unregisterSpecialRenderer(Class<? extends TileEntity> tileEntityClass)
	{
		tileEntityMapper.remove(tileEntityClass);
	}

	@Override
	public boolean install() throws Exception
	{		
		Field instanceTileEntityRenderer = TileEntityRendererHelper.class.getDeclaredField("instance");
		instanceTileEntityRenderer.setAccessible(true);
		instanceTileEntityRenderer.set(null, this);
		instanceTileEntityRenderer.setAccessible(false);
		
		Field instanceEntityRenderer = EntityRendererHelper.class.getDeclaredField("instance");
		instanceEntityRenderer.setAccessible(true);
		instanceEntityRenderer.set(null, this);
		instanceEntityRenderer.setAccessible(false);
		return true;
	}

	@EventHandler
	@SuppressWarnings("unchecked")
	public void postInit(InitResourceEvent event)
	{
		Field[] fields = TileEntityRenderer.class.getDeclaredFields();
		this.tileEntityMapper = null;
		for(Field field : fields){
			if(Map.class.isAssignableFrom(field.getType()))
		try
		{
			field.setAccessible(true);
			this.tileEntityMapper = (Map<Class<?>, TileEntitySpecialRenderer>)(field.get(TileEntityRenderer.instance));
			field.setAccessible(false);
			break;
		}
		catch(Exception exception)
		{
			throw new IllegalArgumentException("Unable to patch the tile entity rendering class for special renderer map.");
		}}
		
		fields = RenderManager.class.getDeclaredFields();
		this.entityMapper = null;
		for(Field field : fields){
			if(Map.class.isAssignableFrom(field.getType()))
		try
		{
			field.setAccessible(true);
			this.entityMapper = (Map<Class<?>, Render>)(field.get(RenderManager.instance));
			field.setAccessible(false);
			break;
		}
		catch(Exception exception)
		{
			throw new IllegalArgumentException("Unable to patch the entity rendering class for special renderer map.");
		}}
	}
	
	private static final JSONObject rendererhelper = new JSONObject();
	static
	{
		rendererhelper.set("name", "Renderer Helper");
		rendererhelper.set("version", "0.0.0.7 alpha");
		rendererhelper.set("features", new String[]{
				"Create your own block, entity, tile ",
				"entity and register your own special ",
				"entity renderer or tile entity render",
				"-er with this, so that you can display ",
				"objects of own your world!"
		});
	}
	
	@Override
	public JSONObject getDescription()
	{
		return rendererhelper;
	}

	@Override
	public void registerRenderer(Class<? extends Entity> tileEntityClass, Render renderer)
	{
		this.entityMapper.put(tileEntityClass, renderer);
	}

	@Override
	public void unregisterRenderer(Class<? extends Entity> tileEntityClass)
	{
		this.entityMapper.remove(tileEntityClass);
	}
}
