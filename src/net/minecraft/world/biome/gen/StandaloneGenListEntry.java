package net.minecraft.world.biome.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class StandaloneGenListEntry extends GenListEntry
{
	public int genLoops;
	
	protected StandaloneGenListEntry(WorldGenerator generator, int genLoops)
	{
		this(generator, genLoops, false);
	}

	protected StandaloneGenListEntry(WorldGenerator generator, int genLoops, boolean suppress)
	{
		super(generator, suppress);
		this.genLoops = genLoops;
	}
	
	@Override
	public void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
	{
		for(int i = 0; i < genLoops; ++i)
		{
			int x = chunk_X + randomGenerator.nextInt(16) + 8;
			int y = this.generateHeight(currentWorld, randomGenerator);
			int z = chunk_Z + randomGenerator.nextInt(16) + 8;
			this.generator.generate(currentWorld, randomGenerator, x, y, z);
		}
	}
	
	protected int generateHeight(World currentWorld, Random randomGenerator)
	{
		return randomGenerator.nextInt(128);
	}
}
