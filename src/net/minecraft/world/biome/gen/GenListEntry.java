package net.minecraft.world.biome.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class GenListEntry
{
	protected WorldGenerator generator;
	protected GenListEntry(WorldGenerator generator)
	{
		this(generator, false);
	}
	
	protected GenListEntry(WorldGenerator generator, boolean suppressNotRegistered)
	{
		if(!suppressNotRegistered) if(generator == null) throw new IllegalArgumentException("You should not register null as generator!");
		this.generator = generator;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof GenListEntry)) return false;
		return ((GenListEntry)obj).generator.getClass().equals(this.generator.getClass());
	}
	
	public abstract void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z);	
}
