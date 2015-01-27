package net.minecraft.world.biome.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class PositionedGenListEntry extends GenListEntry
{
	public int genLoops;
	
	protected PositionedGenListEntry(WorldGenerator generator, int genLoops)
	{
		this(generator, genLoops, false);
	}

	protected PositionedGenListEntry(WorldGenerator generator, int genLoops, boolean suppressNotRegistered)
	{
		super(generator, suppressNotRegistered);
		this.genLoops = genLoops;
	}
	
	@Override
	public void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
	{
		for(int i = 0; i < genLoops; ++i)
		{
			int x = chunk_X + randomGenerator.nextInt(16) + 8;
			int z = chunk_Z + randomGenerator.nextInt(16) + 8;
			int y = this.generateHeight(currentWorld, randomGenerator, x, z);
			if(y < 0) continue;
			this.generator.generate(currentWorld, randomGenerator, x, y, z);
		}
	}
	
	protected abstract int generateHeight(World currentWorld, Random randomGenerator, int x, int z);
}
