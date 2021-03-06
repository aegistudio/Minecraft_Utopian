package net.minecraft.world.biome;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;

public class BiomeGenEnd extends BiomeGenBase
{
    public BiomeGenEnd(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 4, 4));
        this.topBlock = Block.dirt.blockID;
        this.fillerBlock = Block.dirt.blockID;
        this.theBiomeDecorator = new BiomeEndDecorator(this);
    }

    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
        return 0;
    }
}
