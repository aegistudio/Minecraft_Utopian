package net.aegistudio.minecraft.utopian;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface Dedicated
{
	
	/********************************************************************************* 
	 * Installations with Dedicated.Client will not be installed when running server.
	 * Methods with Dedicated.Client will not be called when running server.
	 * If you want the installation run in both server and client, please don't annotate
	 * it with Dedicated.Client or Dedicated.Server.
	 * @author aegistudio
	 *********************************************************************************/
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface Client	{	}
	
	/********************************************************************************* 
	 * Installations with Dedicated.Server will not be installed when running client.
	 * Methods with Dedicated.Server will not be called when running client.
	 * If you want the installation run in both server and client, please don't annotate
	 * it with Dedicated.Client or Dedicated.Server.
	 * @author aegistudio
	 *********************************************************************************/
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface Server	{	}
	
}