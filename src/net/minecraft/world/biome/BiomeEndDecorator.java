package net.minecraft.world.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.World;
import net.minecraft.world.biome.gen.GenListEntry;
import net.minecraft.world.gen.feature.WorldGenSpikes;

public class BiomeEndDecorator extends BiomeDecorator
{
    public BiomeEndDecorator(BiomeGenBase par1BiomeGenBase)
    {
        super(par1BiomeGenBase);
        this.addonGenerators.add(new GenListEntry(new WorldGenSpikes(Block.whiteStone.blockID))
        {
			@Override
			public void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
			{
		        if (randomGenerator.nextInt(5) == 0)
		        {
		            int x = chunk_X + randomGenerator.nextInt(16) + 8;
		            int z = chunk_Z + randomGenerator.nextInt(16) + 8;
		            int y = currentWorld.getTopSolidOrLiquidBlock(x, z);
		            generator.generate(currentWorld, randomGenerator, x, y, z);
		        }
			}
        });
    }

    /**
     * The method that does the work of actually decorating chunks
     */
    protected void decorate()
    {
    	this.generateList(oreGenerators);

        this.generateList(addonGenerators);
        
        if (this.chunk_X == 0 && this.chunk_Z == 0)
        {
            EntityDragon var4 = new EntityDragon(this.currentWorld);
            var4.setLocationAndAngles(0.0D, 128.0D, 0.0D, this.randomGenerator.nextFloat() * 360.0F, 0.0F);
            this.currentWorld.spawnEntityInWorld(var4);
        }
    }
}
