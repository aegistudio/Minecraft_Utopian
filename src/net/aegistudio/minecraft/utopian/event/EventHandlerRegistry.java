package net.aegistudio.minecraft.utopian.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import net.aegistudio.minecraft.utopian.Dedicated;
import net.aegistudio.minecraft.utopian.UtopianLoader;

public class EventHandlerRegistry
{
	private final HashMap<Class<?>, HashMap<Method, ArrayList<Object>>> handler_list;
	private final HashMap<Class<?>, HashMap<Method, ArrayList<Object>>> async_handler_list;
	
	private static EventHandlerRegistry instance;
	
	protected EventHandlerRegistry()
	{
		this.handler_list = new HashMap<Class<?>, HashMap<Method, ArrayList<Object>>>();
		this.async_handler_list = new HashMap<Class<?>, HashMap<Method, ArrayList<Object>>>();
	}
	
	public static EventHandlerRegistry getEventHandlerRegistry()
	{
		return instance;
	}
	
	/**
	 * Register handler in order to listen to specific events passed by the Minecraft Utopian 
	 * event driven system.
	 * @param obj the handler object to register.
	 * @param event_class the event to listen by the handler.
	 * @return whether the registry is success.
	 */
	
	public boolean registerHandler(Object obj, Class<? extends Event> event_class)
	{
		if(obj == null || event_class == null) return false;
		Method[] methods = obj.getClass().getDeclaredMethods();
		boolean registered = false;
		for(Method method : methods) 
		{
			if(UtopianLoader.is_server && (method.isAnnotationPresent(Dedicated.Client.class) || event_class.isAnnotationPresent(Dedicated.Client.class))) continue;
			if(!UtopianLoader.is_server && (method.isAnnotationPresent(Dedicated.Server.class) || event_class.isAnnotationPresent(Dedicated.Server.class))) continue;
			Class<?>[] parameters = method.getParameterTypes();
			if(parameters.length != 1) continue;
			
			EventHandler event_handler = method.getAnnotation(EventHandler.class);
			if(event_handler == null) continue;
			
			HashMap<Class<?>, HashMap<Method, ArrayList<Object>>> operating_handler_list = this.handler_list;
			if(event_handler.async()) operating_handler_list = this.async_handler_list;
			
			Class<?> annotated_class = parameters[0];
			
			if(annotated_class.equals(event_class))
			{
				HashMap<Method, ArrayList<Object>> handler_object_map;
				if((handler_object_map = operating_handler_list.get(event_class)) == null)
				{
					handler_object_map = new HashMap<Method, ArrayList<Object>>();
					operating_handler_list.put(event_class, handler_object_map);
				}
				
				ArrayList<Object> object_list;
				if((object_list = handler_object_map.get(method)) == null)
				{
					object_list = new ArrayList<Object>();
					handler_object_map.put(method, object_list);
				}
				if(object_list.add(obj)) registered = true;
			}
		}
		return registered;
	}

	/**
	 * Unregister handler with specific event if it no longer needs to listen to the event.
	 * @param obj the handler object to unregister.
	 * @param event_class the event that no longer needs to be listened by this listener.
	 * @return whether the unregistraty is success.
	 */
	
	public boolean unregisterHandler(Object obj, Class<? extends Event> event_class)
	{
		if(obj == null || event_class == null) return false;
		Method[] methods = obj.getClass().getDeclaredMethods();
		EventHandler eventhandler = null;
		for(Method method : methods) if((eventhandler = method.getAnnotation(EventHandler.class)) != null)
			if(eventhandler.value().equals(event_class))
		{
			HashMap<Class<?>, HashMap<Method, ArrayList<Object>>> operating_handler_list = this.handler_list;
			if(eventhandler.async()) operating_handler_list = this.async_handler_list;
			
			HashMap<Method, ArrayList<Object>> handler_object_map;
			if((handler_object_map = operating_handler_list.get(event_class)) == null) return false;
			
			ArrayList<Object> object_list;
			if((object_list = handler_object_map.get(method)) == null) return false;
			
			if(!object_list.remove(obj)) return false;
		}
		return true;
	}
	
	public Method[] listMethods(Class<? extends Event> event_class, boolean async)
	{
		HashMap<Method, ArrayList<Object>> handler_object_map;
		
		HashMap<Class<?>, HashMap<Method, ArrayList<Object>>> operating_handler_list = this.handler_list;
		if(async) operating_handler_list = this.async_handler_list;
		
		if((handler_object_map = operating_handler_list.get(event_class)) == null) return new Method[0];
		return handler_object_map.keySet().toArray(new Method[0]);
	}
	
	public Object[] listHandlers(Class<? extends Event> event_class, Method method, boolean async)
	{
		HashMap<Method, ArrayList<Object>> handler_object_map;
		
		HashMap<Class<?>, HashMap<Method, ArrayList<Object>>> operating_handler_list = this.handler_list;
		if(async) operating_handler_list = this.async_handler_list;
		
		if((handler_object_map = operating_handler_list.get(event_class)) == null) return new Object[0];
		ArrayList<Object> object_list;
		if((object_list = handler_object_map.get(method)) == null) return new Object[0];
		return object_list.toArray(new Object[0]);
	}
	
	public void invoke(Event event)
	{
		this.invokeInternal(event, true);
		this.invokeInternal(event, false);
	}
	
	private void invokeInternal(final Event event, boolean async)
	{
		if(event == null) return;
		Method[] methods = listMethods(event.getClass(), async);
		for(Method method : methods) try
		{
			Object[] object_list = listHandlers(event.getClass(), method, async);
			for(Object obj : object_list)
			{
				method.setAccessible(true);
				if(async)
				{
					final Method async_method = method;
					final Object async_object = obj;
					
					new Thread(new Runnable()
					{
						@Override
						public void run()
						{
							try	{	async_method.invoke(async_object, event);	}
							catch(Exception e)	{		}
						}
					}).start();
				}
				else method.invoke(obj, event);
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
