package net.aegistudio.minecraft.utopian.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is hooked in actual classes and could help patching classes.
 * @author aegistudio
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Intrude
{
	public String value();
}
