package net.minecraft.world.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.gen.GenListEntry;
import net.minecraft.world.biome.gen.OreGenListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenHills extends BiomeGenBase
{

    protected BiomeGenHills(int par1)
    {
        super(par1);
        super.theBiomeDecorator.oreGenerators.add(new OreGenListEntry(Block.silverfish.blockID, 8, 0, 64, 7));
        super.theBiomeDecorator.addonGenerators.add(new GenListEntry(new WorldGenerator(){

			@Override
			public boolean generate(World world, Random random, int x, int y, int z)
			{
				int blockid = world.getBlockId(x, y, z);
	            if (blockid == Block.stone.blockID)
	            {
	                world.setBlock(x, y, z, Block.oreEmerald.blockID, 0, 2);
	            }
				return true;
			}
		}){

				@Override
				public void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
				{
					int totalLoops = 3 + randomGenerator.nextInt(6);

			        for (int genLoop = 0; genLoop < totalLoops; ++genLoop)
			        {
			        	int x = chunk_X + randomGenerator.nextInt(16);
			            int y = randomGenerator.nextInt(28) + 4;
			            int z = chunk_Z + randomGenerator.nextInt(16);
			            this.generator.generate(currentWorld, randomGenerator, x, y, z);
			        }
				}
		});
    }
}
