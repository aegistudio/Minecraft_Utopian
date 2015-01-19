package net.aegistudio.minecraft.utopian.event;

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
	/**
	 * This attribute remains only to be compatible with older versions.
	 */
	@Deprecated
	public Class<? extends Event> value() default Event.class;
	
	public boolean async() default false;
}
