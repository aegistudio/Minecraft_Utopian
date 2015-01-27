package net.aegistudio.minecraft.utopian.ease;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ExpressiveWorldGenerator extends WorldGenerator
{
	private List<List<WorldGenerator>> generation = new ArrayList<List<WorldGenerator>>();
	
	public ExpressiveWorldGenerator(Object... generateParameters)
	{
		if(generateParameters == null || generateParameters.length == 0) 
			throw new IllegalArgumentException("Generate information must be provided!");
		
		ArrayList<String> worldGenerateMap = new ArrayList<String>();
		int index = 0;
		for(; index < generateParameters.length; index ++)
		{
			if(generateParameters[index] instanceof String) worldGenerateMap.add((String) generateParameters[index]);
			else break;
		}
		
		if(worldGenerateMap.size() == 0) throw new IllegalArgumentException("The generate map must be provided!");
		
		char suspendingChar = 0;
		Map<Character, WorldGenerator> callableSubgenerators = new HashMap<Character, WorldGenerator>();
		
		while(index < generateParameters.length)
		{
			if(generateParameters[index] instanceof Character) 
			{
				if(suspendingChar == 0)
					suspendingChar = (char) generateParameters[index];
				else throw new IllegalArgumentException("Unaccepted parameter CHAR in this position!");
				
				if(suspendingChar == ' ') throw new IllegalArgumentException("Can not assign world generators to character space!");
			}
			else
			{
				if(suspendingChar == 0) throw new IllegalArgumentException("Unaccepted parameter OBJECT in this position!");
				WorldGenerator worldGenerator = null;
				if(generateParameters[index] instanceof WorldGenerator) worldGenerator = (WorldGenerator) generateParameters[index];
				else if(generateParameters[index] instanceof Integer) worldGenerator = new BlockGenerator(((Integer)generateParameters[index]));
				else if(generateParameters[index] instanceof Block) worldGenerator = new BlockGenerator(((Block)generateParameters[index]).blockID);
				else if(generateParameters[index] instanceof ItemStack)
				{
					ItemStack itemstack = (ItemStack) generateParameters[index];
					int item = itemstack.getItem().itemID;
					int meta = itemstack.getItemDamage();
					worldGenerator = new BlockGenerator(item, meta);
				}
				
				if(worldGenerator == null) throw new IllegalArgumentException("Unable to convert the given parameter to world generator!");
				callableSubgenerators.put(suspendingChar, worldGenerator);
				suspendingChar = 0;
			}
			
			index ++;
		}
		
		for(String mapStrip : worldGenerateMap)
		{
			List<WorldGenerator> actualStrip = new ArrayList<WorldGenerator>();
			this.generation.add(actualStrip);
			char[] charArray = mapStrip.toCharArray();
			for(char c : charArray) if(c != ' ') actualStrip.add(callableSubgenerators.get(c));
		}
	}
	
	@Override
	public boolean generate(World world, Random randomGenerator, int x, int y, int z)
	{
		for(int j = 0; j < generation.size(); j ++)
		{
			List<WorldGenerator> worldGenStrip = generation.get(j);
			for(int i = 0; i < worldGenStrip.size(); i ++)
			{
				WorldGenerator worldGenerator = worldGenStrip.get(i);
				if(worldGenerator != null) worldGenerator.generate(world, randomGenerator, x + i, y, z + j);
			}
		}
		return false;
	}
}

class BlockGenerator extends WorldGenerator
{
	private int blockToGenerate;
	private int metadata;
	
	public BlockGenerator(int blockid)
	{
		this(blockid, 0);
	}
	
	public BlockGenerator(int blockid, int blockmeta)
	{
		this.blockToGenerate = blockid;
		this.metadata = blockmeta;
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		world.setBlock(x, y, z, this.blockToGenerate, this.metadata, 2);
		return true;
	}
}