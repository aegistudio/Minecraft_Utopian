package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSwamp extends BiomeGenBase
{
    protected BiomeGenSwamp(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treeGen.genLoops = 2;
        this.theBiomeDecorator.flowerRedGen.genLoops
    		= this.theBiomeDecorator.flowerYellowGen.genLoops = -999;
        this.theBiomeDecorator.deadbushGen.genLoops = 1;
        
        this.theBiomeDecorator.mushroomBrownGen.genLoops
        	= this.theBiomeDecorator.mushroomRedGen.genLoops = 8;
        this.theBiomeDecorator.reedGen.genLoops = 10;
        
        this.theBiomeDecorator.clayTopSoilGen.soilPerChunk = 1;
        this.theBiomeDecorator.waterlilyGen.genLoops = 4;
        this.waterColorMultiplier = 14745518;
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return this.worldGeneratorSwamp;
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerGrass.getGrassColor(var1, var3) & 16711422) + 5115470) / 2;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerFoliage.getFoliageColor(var1, var3) & 16711422) + 5115470) / 2;
    }
}
