package net.aegistudio.minecraft.utopian.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class SingletonIntruder<Singleton>
{
	private final String instance_field;
	public SingletonIntruder(String instance_field)
	{
		this.instance_field = instance_field;
	}
	
	public Singleton intrude(Class<Singleton> singleton_class, Class<? extends Singleton> subsingleton_class) throws Exception
	{
		Constructor<Singleton> singleton_constructor = singleton_class.getDeclaredConstructor();
		Constructor<? extends Singleton> subsingleton_constructor = subsingleton_class.getDeclaredConstructor();
		
		if(!singleton_class.equals(subsingleton_class)) singleton_constructor.setAccessible(true);
		subsingleton_constructor.setAccessible(true);
		Singleton singleton = subsingleton_constructor.newInstance();
		subsingleton_constructor.setAccessible(false);
		if(!singleton_class.equals(subsingleton_class)) singleton_constructor.setAccessible(false);
		
		Field singleton_field = singleton_class.getDeclaredField(instance_field);
		singleton_field.setAccessible(true);
		singleton_field.set(null, singleton);
		singleton_field.setAccessible(false);
		
		return singleton;
	}
}
