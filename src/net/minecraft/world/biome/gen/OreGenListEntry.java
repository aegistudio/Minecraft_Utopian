package net.minecraft.world.biome.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class OreGenListEntry extends GenListEntry
{
	public int totalGenLoops;
	public int minHeight;
	public int maxHeight;
	
	public OreGenListEntry(WorldGenMinable oreGenerator, int minHeight, int maxHeight, int totalGenLoops)
	{
		super(oreGenerator);
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
		this.totalGenLoops = totalGenLoops;
	}
	
	public OreGenListEntry(int blockid, int numberOfBlocks, int minHeight, int maxHeight, int totalGenLoops)
	{
		this(new WorldGenMinable(blockid, numberOfBlocks), minHeight, maxHeight, totalGenLoops);
	}
	
	public void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
	{
        for (int genLoop = 0; genLoop < totalGenLoops; ++genLoop)
        {
            int x = chunk_X + randomGenerator.nextInt(16);
            int y = randomGenerator.nextInt(maxHeight - minHeight) + minHeight;
            int z = chunk_Z + randomGenerator.nextInt(16);
            generator.generate(currentWorld, randomGenerator, x, y, z);
        }
	}
}
