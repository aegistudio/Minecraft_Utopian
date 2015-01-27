package net.minecraft.world.biome;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityMooshroom;

public class BiomeGenMushroomIsland extends BiomeGenBase
{
    public BiomeGenMushroomIsland(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treeGen.genLoops = -100;
        this.theBiomeDecorator.flowerRedGen.genLoops
        	= this.theBiomeDecorator.flowerYellowGen.genLoops = -100;
        this.theBiomeDecorator.grassGen.genLoops = -100;
        this.theBiomeDecorator.mushroomBrownGen.genLoops
        	= this.theBiomeDecorator.mushroomRedGen.genLoops = 1;
        this.theBiomeDecorator.bigMushroomGen.genLoops = 1;
        
        this.topBlock = Block.mycelium.blockID;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
    }
}
