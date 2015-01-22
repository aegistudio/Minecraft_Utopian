package net.minecraft.world.biome;

public class BiomeGenPlains extends BiomeGenBase
{
    protected BiomeGenPlains(int par1)
    {
        super(par1);
        //this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.treeGen.genLoops = -999;
        this.theBiomeDecorator.flowersPerChunk = 4;
        //this.theBiomeDecorator.grassPerChunk = 10;
        this.theBiomeDecorator.grassGen.genLoops = 10;
    }
}
