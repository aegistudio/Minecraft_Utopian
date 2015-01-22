package net.minecraft.world.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.aegistudio.minecraft.utopian.util.Intrude;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.gen.GenListEntry;
import net.minecraft.world.biome.gen.LiquidGenListEntry;
import net.minecraft.world.biome.gen.OreGenListEntry;
import net.minecraft.world.biome.gen.PositionedGenListEntry;
import net.minecraft.world.biome.gen.StandaloneGenListEntry;
import net.minecraft.world.biome.gen.TopSoilGenListEntry;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecorator
{
    /** The world the BiomeDecorator is currently decorating */
    protected World currentWorld;

    /** The Biome Decorator's random number generator. */
    protected Random randomGenerator;

    /** The X-coordinate of the chunk currently being decorated */
    protected int chunk_X;

    /** The Z-coordinate of the chunk currently being decorated */
    protected int chunk_Z;

    /** The biome generator object. */
    protected BiomeGenBase biome;

    //XXX we try to replace the current top soil gen with a special list generating way.
    
    protected TopSoilGenListEntry sandTopSoilGen;
    protected TopSoilGenListEntry sandTopSoilGen2;
    protected TopSoilGenListEntry clayTopSoilGen;
    protected TopSoilGenListEntry gravelTopSoilGen;
    
    @Intrude("topSoilGenerationList")
    protected List<TopSoilGenListEntry> topSoilGenerators = new ArrayList<TopSoilGenListEntry>();
    
    //XXX we try to replace the current oregen with a special list generating way, which might be risky.

    @Intrude("oreGenerationList")
    protected List<OreGenListEntry> oreGenerators = new ArrayList<OreGenListEntry>();
    
    /** Field that holds one of the plantYellow WorldGenFlowers */
    protected WorldGenerator plantYellowGen;

    /** Field that holds one of the plantRed WorldGenFlowers */
    protected WorldGenerator plantRedGen;

    /** Field that holds mushroomBrown WorldGenFlowers */
    protected WorldGenerator mushroomBrownGen;

    /** Field that holds mushroomRed WorldGenFlowers */
    protected WorldGenerator mushroomRedGen;

    protected PositionedGenListEntry bigMushroomGen;

    /** Field that holds WorldGenReed */
    protected WorldGenerator reedGen;

    protected PositionedGenListEntry waterlilyGen;
    protected StandaloneGenListEntry deadbushGen;
    protected PositionedGenListEntry treeGen;
    protected StandaloneGenListEntry grassGen;
    protected StandaloneGenListEntry cactusGen;
    protected StandaloneGenListEntry pumpkinGen;
    
    /**
     * The number of yellow flower patches to generate per chunk. The game generates much less than this number, since
     * it attempts to generate them at a random altitude.
     */
    protected int flowersPerChunk;

    /**
     * The number of extra mushroom patches per chunk. It generates 1/4 this number in brown mushroom patches, and 1/8
     * this number in red mushroom patches. These mushrooms go beyond the default base number of mushrooms.
     */
    protected int mushroomsPerChunk;

    /**
     * The number of reeds to generate per chunk. Reeds won't generate if the randomly selected placement is unsuitable.
     */
    protected int reedsPerChunk;

    /** True if decorator should generate surface lava & water */
    public boolean generateLakes;

    @Intrude("liquidGenerationList")
    protected List<LiquidGenListEntry> liquidGenerators = new ArrayList<LiquidGenListEntry>();
    
    protected LiquidGenListEntry waterGen;
    protected LiquidGenListEntry lavaGen;
    
    @Intrude("addonGenerationList")
    protected List<GenListEntry> addonGenerators = new ArrayList<GenListEntry>();
    
    private boolean bypassCheck = true;
    
    public BiomeDecorator(BiomeGenBase biomeGenBase)
    {
    	/**
    	 * Adding topSoil generators.
    	 */
    	WorldGenSand sandGen = new WorldGenSand(7, Block.sand.blockID);
    	this.sandTopSoilGen = new TopSoilGenListEntry(sandGen, 1);
    	this.topSoilGenerators.add(sandTopSoilGen);
    	this.sandTopSoilGen2 = new TopSoilGenListEntry(sandGen, 3);
    	this.topSoilGenerators.add(sandTopSoilGen2);
    	this.gravelTopSoilGen = new TopSoilGenListEntry(6, Block.gravel.blockID, 0);
    	this.topSoilGenerators.add(gravelTopSoilGen);
    	this.clayTopSoilGen = new TopSoilGenListEntry(new WorldGenClay(4), 1);
    	this.topSoilGenerators.add(this.clayTopSoilGen);
    	
    	/**
    	 * Adding oreGenerators.
    	 */
        this.oreGenerators.add(new OreGenListEntry(Block.dirt.blockID, 32, 0, 128, 20));
        this.oreGenerators.add(new OreGenListEntry(Block.gravel.blockID, 32, 0, 128, 10));
        this.oreGenerators.add(new OreGenListEntry(Block.oreCoal.blockID, 16, 0, 128, 20));
        this.oreGenerators.add(new OreGenListEntry(Block.oreIron.blockID, 8, 0, 64, 20));
        this.oreGenerators.add(new OreGenListEntry(Block.oreGold.blockID, 8, 0, 32, 2));
        this.oreGenerators.add(new OreGenListEntry(Block.oreRedstone.blockID, 7, 0, 16, 8));
        this.oreGenerators.add(new OreGenListEntry(Block.oreDiamond.blockID, 7, 0, 16, 1));
        this.oreGenerators.add(new OreGenListEntry(Block.oreLapis.blockID, 6, 16, 16, 1)
        {
        	@Override
        	public void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
        	{
                for (int genLoop = 0; genLoop < totalGenLoops; ++genLoop)
                {
                    int x = chunk_X + randomGenerator.nextInt(16);
                    int y = randomGenerator.nextInt(maxHeight) + randomGenerator.nextInt(maxHeight) + (minHeight - maxHeight);
                    int z = chunk_Z + randomGenerator.nextInt(16);
                    generator.generate(currentWorld, randomGenerator, x, y, z);
                }
        	}
        });
        
        this.plantYellowGen = new WorldGenFlowers(Block.plantYellow.blockID);
        this.plantRedGen = new WorldGenFlowers(Block.plantRed.blockID);
        this.mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
        this.mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
        
        /**
         * Adding big mushroom generator.
         */
        this.bigMushroomGen = new PositionedGenListEntry(new WorldGenBigMushroom(), 0)
        {

			@Override
			protected int generateHeight(World currentWorld, Random randomGenerator, int x, int z)
			{
				return currentWorld.getHeightValue(x, z);
			}
        	
        };
        
        this.reedGen = new WorldGenReed();
        
        /**
         * Adding cactus generator.
         */
        this.cactusGen = new StandaloneGenListEntry(new WorldGenCactus(), 0){};
        
        /**
         * Adding waterlily generator.
         */
        this.waterlilyGen = new PositionedGenListEntry(new WorldGenWaterlily(), 0)
        {

			@Override
			protected int generateHeight(World currentWorld, Random randomGenerator, int x, int z)
			{
				int y = randomGenerator.nextInt(128);
				while(y > 0 && currentWorld.getBlockId(x, y - 1, z) == 0) y --;
				return y;
			}
        	
        };
        
        /**
         * Adding deadbush generator
         */
        this.deadbushGen = new StandaloneGenListEntry(new WorldGenDeadBush(Block.deadBush.blockID), 0){};
        
        /**
         * Adding tree generator.
         */
        this.treeGen = new PositionedGenListEntry(null, 0, true)
        {
        	@Override
        	public void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
        	{
        		boolean shouldReset = false;
        		if(randomGenerator.nextInt(10) == 0){ this.genLoops ++; shouldReset = true; }
        		generator = BiomeDecorator.this.biome.getRandomWorldGenForTrees(randomGenerator);
        		generator.setScale(1.0D, 1.0D, 1.0D);
        		super.generate(currentWorld, randomGenerator, chunk_X, chunk_Z);
        		if(shouldReset) this.genLoops --;
        	}
        	
			@Override
			protected int generateHeight(World currentWorld, Random randomGenerator, int x, int z)
			{
				return currentWorld.getHeightValue(x, z);
			}
        };
        
        /**
         * Adding grass generator.
         */
        this.grassGen = new StandaloneGenListEntry(null, 0, true)
        {
        	@Override
        	public void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
        	{
        		generator = BiomeDecorator.this.biome.getRandomWorldGenForGrass(randomGenerator);
        		super.generate(currentWorld, randomGenerator, chunk_X, chunk_Z);
        	}
        };
        
        this.flowersPerChunk = 2;
        this.mushroomsPerChunk = 0;
        this.reedsPerChunk = 0;
        
        /**
         * Adding pumpkin generator.
         */
        this.pumpkinGen = new StandaloneGenListEntry(new WorldGenPumpkin(), 1)
        {
        	@Override
        	public void generate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
        	{
        		if(randomGenerator.nextInt(32) == 0) super.generate(currentWorld, randomGenerator, chunk_X, chunk_Z);
        	}
        };
        
        /**
         * Adding liquids' generators.
         */
        this.generateLakes = true;
        this.waterGen = new LiquidGenListEntry(Block.waterMoving.blockID, 50)
        {
			@Override
			protected int generateHeight(World currentWorld, Random randomGenerator)
			{
				return randomGenerator.nextInt(randomGenerator.nextInt(120) + 8);
			}
        };
        this.liquidGenerators.add(this.waterGen);
        
        this.lavaGen = new LiquidGenListEntry(Block.lavaMoving.blockID, 20)
        {
			@Override
			protected int generateHeight(World currentWorld, Random randomGenerator)
			{
				return randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(112) + 8) + 8);
			}
        };
        this.liquidGenerators.add(this.lavaGen);
        
        this.biome = biomeGenBase;
    }

    /**
     * Decorates the world. Calls code that was formerly (pre-1.8) in ChunkProviderGenerate.populate
     */
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        if (this.currentWorld != null) throw new RuntimeException("Already decorating!!");
        else 
        {
	    	if(bypassCheck)
	    	{
	    		bypassCheck = false;
	    		this.bypassCheck();
	    	}
	        this.currentWorld = par1World;
	        this.randomGenerator = par2Random;
	        this.chunk_X = par3;
	        this.chunk_Z = par4;
	        this.decorate();
	        this.currentWorld = null;
	        this.randomGenerator = null;
        }
    }
    
    /**
     * This method will remove all unused decorate functions from the game.
     */
    private void bypassCheck()
    {
    	this.bypassList(oreGenerators);
    	this.bypassList(topSoilGenerators);
    	this.bypassList(addonGenerators);
    	this.bypassList(liquidGenerators);
    }

    private void bypassList(List<? extends GenListEntry> list)
    {
    	for(GenListEntry entry : list) if(this.shouldBypass(entry))
    		list.remove(entry);
    }
    
    /**
     * This method will a certain decorate entry and check whether it should be bypassed.
     */
    
    private boolean shouldBypass(GenListEntry entry)
    {
    	if(entry == null) return true;
    	if(entry instanceof OreGenListEntry)
    	{
    		OreGenListEntry ogentry = (OreGenListEntry) entry;
    		if(ogentry.totalGenLoops <= 0) return true;
    		if(ogentry.maxHeight < 0) return true;
    		if(ogentry.minHeight > ogentry.maxHeight) return true;
    		return false;
    	}
    	else if(entry instanceof LiquidGenListEntry)
    	{
    		LiquidGenListEntry lqgentry = (LiquidGenListEntry) entry;
    		if(lqgentry.genLoops <= 0) return true;
    		return false;
    	}
    	else if(entry instanceof PositionedGenListEntry)
    	{
    		PositionedGenListEntry pgentry = (PositionedGenListEntry) entry;
    		if(pgentry.genLoops < 0) return true;
    		return false;
    	}
    	else if(entry instanceof StandaloneGenListEntry)
    	{
    		StandaloneGenListEntry stdagentry = (StandaloneGenListEntry) entry;
    		if(stdagentry.genLoops < 0) return true;
    		return false;
    	}
    	else return false;
    }
    
    /**
     * The method that does the work of actually decorating chunks
     */
    protected void decorate()
    {
        /**
         * Generates ores in the current chunk.
         */
        this.generateList(this.oreGenerators);
        int var2;
        int var3;

        /**
         * Generates topSoils in the chunk
         */
        this.generateList(this.topSoilGenerators);
        
        int var4;
        
        /**
         * Generates trees in the current chunk.
         */
        this.treeGen.generate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z);
        
        /**
         * Generates big mushrooms in the current chunk.
         */
        this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z);

        int var7;

        for (var2 = 0; var2 < this.flowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantYellowGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);

            if (this.randomGenerator.nextInt(4) == 0)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(128);
                var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                this.plantRedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
            }
        }

        /**
         * Generates grass in the current chunk.
         */
        this.grassGen.generate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z);
        
        /**
         * Generates deadbush in the current chunk.
         */
        this.deadbushGen.generate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z);
        
        /**
         * Generates waterlily in the current chunk.
         */
        this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z);

        for (var2 = 0; var2 < this.mushroomsPerChunk; ++var2)
        {
            if (this.randomGenerator.nextInt(4) == 0)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                var7 = this.currentWorld.getHeightValue(var3, var4);
                this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
            }

            if (this.randomGenerator.nextInt(8) == 0)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                var7 = this.randomGenerator.nextInt(128);
                this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
            }
        }

        if (this.randomGenerator.nextInt(4) == 0)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128);
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        if (this.randomGenerator.nextInt(8) == 0)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128);
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        for (var2 = 0; var2 < this.reedsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            var7 = this.randomGenerator.nextInt(128);
            this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
        }

        for (var2 = 0; var2 < 10; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
        }

        /**
         * Generates pumpkin in the chunk
         */
        this.pumpkinGen.generate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z);
        
        /**
         * Generates cactus in the chunk
         */
        this.cactusGen.generate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z);
        
        this.generateList(this.addonGenerators);
        
        /**
         * Generates lakes in the chunk
         */
        if (this.generateLakes) this.generateList(this.liquidGenerators);
        
        /**
         * Generates addons in the chunk
         */
        
    }
    
    protected void generateList(List<? extends GenListEntry> genlist)
    {
    	for(GenListEntry gen : genlist)
    		gen.generate(currentWorld, randomGenerator, chunk_X, chunk_Z);
    }
    
}
