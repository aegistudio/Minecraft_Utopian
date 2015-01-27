package net.minecraft.world.biome;

import net.minecraft.block.Block;

public class BiomeGenBeach extends BiomeGenBase
{
    public BiomeGenBeach(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = Block.sand.blockID;
        this.fillerBlock = Block.sand.blockID;
        this.theBiomeDecorator.treeGen.genLoops = -999;
        this.theBiomeDecorator.deadbushGen.genLoops = 0;
        this.theBiomeDecorator.reedGen.genLoops = 0;
        theBiomeDecorator.cactusGen.genLoops = 0;
    }
}
