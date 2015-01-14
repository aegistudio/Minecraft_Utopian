package net.aegistudio.minecraft.utopian.patch.core;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
//import java.util.HashMap;
import java.util.Map;

import net.aegistudio.json.JSONObject;
import net.aegistudio.minecraft.utopian.event.EventHandler;
import net.aegistudio.minecraft.utopian.event.EventHandlerRegistry;
import net.aegistudio.minecraft.utopian.event.runtime.ShutdownEvent;
import net.aegistudio.minecraft.utopian.patch.Patch;
import net.minecraft.block.Block;
import net.minecraft.block.BlockInfoContainer;
import net.minecraft.util.BiMap;

public class Patch_BlockInfoContainer extends BlockInfoContainer implements Patch
{
    /** List of ly/ff (BlockType) containing the already registered blocks. */
    //final Block[] blocksList = new Block[4096];
	//final Map<Integer, Block> blocksListHashmap = new HashMap<Integer, Block>();
	final Map<Integer, Block> blocksListHashmap = new BiMap<Block>();
    
    /**
     * An array of 4096 booleans corresponding to the result of the isOpaqueCube() method for each block ID
     */
    //final boolean[] opaqueCubeLookup = new boolean[4096];
    //final Map<Integer, Boolean> opaqueCubeLookupHashmap = new HashMap<Integer, Boolean>();
	final Map<Integer, Boolean> opaqueCubeLookupHashmap = new BiMap<Boolean>();
    
    /** How much light is subtracted for going through this block */
    //final int[] lightOpacity = new int[4096];
    //final Map<Integer, Integer> lightOpacityHashmap = new HashMap<Integer, Integer>();
	final Map<Integer, Integer> lightOpacityHashmap = new BiMap<Integer>();

    /** Array of booleans that tells if a block can grass */
    //final boolean[] canBlockGrass = new boolean[4096];
    //final Map<Integer, Boolean> canBlockGrassHashmap = new HashMap<Integer, Boolean>();
	final Map<Integer, Boolean> canBlockGrassHashmap = new BiMap<Boolean>();

    /** Amount of light emitted */
    //final int[] lightValue = new int[4096];
    //final Map<Integer, Integer> lightValueHashmap = new HashMap<Integer, Integer>();
    final Map<Integer, Integer> lightValueHashmap = new BiMap<Integer>();

    /**
     * Flag if block ID should use the brightest neighbor light value as its own
     */
    //final boolean[] useNeighborBrightness = new boolean[4096];
    //final Map<Integer, Boolean> useNeighborBrightnessHashmap = new HashMap<Integer, Boolean>();
    final Map<Integer, Boolean> useNeighborBrightnessHashmap = new BiMap<Boolean>();
	
	@Override
	public Block getBlock(int blockid)	{	return blocksListHashmap.get(blockid); /*return blocksList[blockid];*/	}

	@Override
	public void setBlock(Block block)	{	blocksListHashmap.put(block.blockID, block); /*blocksList[block.blockID] = block;*/	}

	@Override
	public boolean getLookupOpaqueCube(int blockid)
	{
		/*return opaqueCubeLookup[blockid];*/
		Boolean returnvalue = opaqueCubeLookupHashmap.get(blockid);
		return returnvalue == null? false : returnvalue;
	}

	@Override
	public int getLookupOpacity(int blockid)
	{
		/*return lightOpacity[blockid];*/
		Integer returnvalue = lightOpacityHashmap.get(blockid);
		return returnvalue == null? 0 : returnvalue;
	}

	@Override
	public void setLookupOpaqueCube(int blockid, boolean isOpaqueCube)
	{
		/*this.opaqueCubeLookup[blockid] = isOpaqueCube;*/
		opaqueCubeLookupHashmap.put(blockid, isOpaqueCube);
	}
	
	@Override
	public boolean getCanBlockGrass(int blockid)
	{
		/*return canBlockGrass[blockid];*/
		Boolean canblockgrass = canBlockGrassHashmap.get(blockid);
		return canblockgrass == null? true : canblockgrass;
	}

	@Override
	public int getLookupLightValue(int blockid)
	{
		/*return lightValue[blockid];*/
		Integer lightvalue = lightValueHashmap.get(blockid);
		return lightvalue == null? 0 : lightvalue;
	}

	@Override
	public boolean getUseNeighbourBrightness(int blockid)
	{
		/*return useNeighborBrightness[blockid];*/
		Boolean useneighbourbrightness = useNeighborBrightnessHashmap.get(blockid);
		return useneighbourbrightness == null? false : useneighbourbrightness;
	}


	@Override
	public void setLookupOpacity(int blockid, int blockOpacity)
	{
		/*this.lightOpacity[blockid] = blockOpacity;*/
		lightOpacityHashmap.put(blockid, blockOpacity);
	}

	@Override
	public void setCanBlockGrass(int blockid, boolean canBlockGrass)
	{
		/*this.canBlockGrass[blockid] = canBlockGrass;*/
		this.canBlockGrassHashmap.put(blockid, canBlockGrass);
	}

	@Override
	public void setLookupLightValue(int blockid, int lightValue)
	{
		/*this.lightValue[blockid] = lightValue;*/
		lightValueHashmap.put(blockid, lightValue);
	}

	@Override
	public void setUseNeighbourBrightness(int blockid,	boolean useNeighbourBrightness)
	{
		/*this.useNeighborBrightness[blockid] = useNeighbourBrightness;*/
		this.useNeighborBrightnessHashmap.put(blockid, useNeighbourBrightness);
	}

	@Override
	public Block[] getRegisteredBlocks()
	{
		/*return this.blocksList;*/
		return this.blocksListHashmap.values().toArray(new Block[0]);
	}

	@Override
	public int getMaximumBlockId()
	{
		//	return this.blocksList.length;
		return 4096;
	}

	@Override
	public boolean install() throws Exception
	{
		Field instance = BlockInfoContainer.class.getDeclaredField("instance");
		instance.setAccessible(true);
		instance.set(null, this);
		instance.setAccessible(false);
		
		EventHandlerRegistry.getEventHandlerRegistry().registerHandler(this, ShutdownEvent.class);
		return true;
	}

	private static final JSONObject blockinfocontainer = new JSONObject();
	static
	{
		blockinfocontainer.set("name", "Block Info Container");
		blockinfocontainer.set("version", "0.0.0.2 alpha");
		blockinfocontainer.set("features", new String[]{
				"Why should we allocate 4096 memory ",
				"cells for just not up to 256 blocks? ",
				"isn't it a waste? What should I do ",
				"if I want to change the limit? so ",
				"I've used a optimized map instead."
		});
	}
	
	@Override
	public JSONObject getDescription()
	{
		return blockinfocontainer;
	}
	
	@EventHandler(ShutdownEvent.class)
	public void onShutdown(ShutdownEvent event)
	{
		this.saveBlockInfo();
	}
	
	public void saveBlockInfo()
	{
		try
		{
			System.out.println("Saving block informations...");
			JSONObject blockRegistry = new JSONObject();
			Block[] blocks = this.getRegisteredBlocks();
			for(Block block : blocks) if(block != null)
				blockRegistry.set(Integer.toString(block.blockID), block.getUnlocalizedName());
			PrintStream blockRegistryWriter = new PrintStream(new File("blocks.json"));
			blockRegistryWriter.print(blockRegistry.toString());
			blockRegistryWriter.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
