package net.minecraft.world.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.gen.PositionedGenListEntry;
import net.minecraft.world.gen.feature.WorldGenDesertWells;

public class BiomeGenDesert extends BiomeGenBase
{
    public BiomeGenDesert(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = Block.sand.blockID;
        this.fillerBlock = Block.sand.blockID;
        this.theBiomeDecorator.treeGen.genLoops = -999;
        this.theBiomeDecorator.deadbushGen.genLoops = 2;
        this.theBiomeDecorator.reedGen.genLoops = 50;
        this.theBiomeDecorator.cactusGen.genLoops = 10;
        
        this.theBiomeDecorator.addonGenerators.add(new PositionedGenListEntry(new WorldGenDesertWells(), 1)
        {
        	public int generateHeight(World world, Random randomGenerator, int x, int z)
        	{
        		if(randomGenerator.nextInt(1000) != 0) return -1;
        		return world.getHeightValue(x, z) + 1;
        	}
        });
    }
}
