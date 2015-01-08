package net.aegistudio.minecraft.utopian.plugin.event;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * The annotation that indicates the specific method will be called
 * when the declared event occurs. 
 * @author aegistudio
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler
{
	public Class<? extends Event> value();
	
	public boolean async() default false;
}
