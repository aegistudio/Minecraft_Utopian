package net.minecraft.world.biome.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TopSoilGenListEntry extends GenListEntry
{

	public int soilPerChunk;
	
	public TopSoilGenListEntry(WorldGenerator generator, int soilPerChunk)
	{
		super(generator);
	}
	
	public TopSoilGenListEntry(int blocksPerChunk, int blockId, int soilPerChunk)
	{
		this(new WorldGenSand(blocksPerChunk, blockId), soilPerChunk);
	}
	
	@Override
	public void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
	{
		for(int genLoops = 0; genLoops < this.soilPerChunk; genLoops ++)
		{
            int x = chunk_X + randomGenerator.nextInt(16) + 8;
            int z = chunk_Z + randomGenerator.nextInt(16) + 8;
            this.generator.generate(currentWorld, randomGenerator, x, currentWorld.getTopSolidOrLiquidBlock(x, z), z);
		}
	}
	
}
