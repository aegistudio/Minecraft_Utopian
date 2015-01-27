package net.minecraft.world.biome;

public class BiomeGenPlains extends BiomeGenBase
{
    protected BiomeGenPlains(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treeGen.genLoops = -999;
        this.theBiomeDecorator.flowerRedGen.genLoops
    	= this.theBiomeDecorator.flowerYellowGen.genLoops = 4;
        this.theBiomeDecorator.grassGen.genLoops = 10;
    }
}
