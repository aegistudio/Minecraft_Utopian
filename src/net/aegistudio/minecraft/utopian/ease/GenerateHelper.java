package net.aegistudio.minecraft.utopian.ease;

import java.lang.reflect.Field;
import java.util.List;

import net.aegistudio.minecraft.utopian.util.Intrude;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.gen.GenListEntry;
import net.minecraft.world.biome.gen.LiquidGenListEntry;
import net.minecraft.world.biome.gen.OreGenListEntry;
import net.minecraft.world.biome.gen.TopSoilGenListEntry;

public class GenerateHelper
{
	
	private static final GenerateHelper instance = new GenerateHelper();
	private Field oreGenerationList;
	private Field addonGenerationList;
	private Field topSoilGenerationList;
	private Field liquidGenerationList;
	
	public static GenerateHelper getInstance()
	{
		return instance;
	}
	
	protected GenerateHelper()
	{
		Field[] fields = BiomeDecorator.class.getDeclaredFields();
		for(Field field : fields) if(field.isAnnotationPresent(Intrude.class))
		{
			Intrude intrude = field.getAnnotation(Intrude.class);
			if("oreGenerationList".equals(intrude.value())) this.oreGenerationList = field;
			else if("addonGenerationList".equals(intrude.value())) this.addonGenerationList = field;
			else if("topSoilGenerationList".equals(intrude.value())) this.topSoilGenerationList = field;
			else if("liquidGenerationList".equalsIgnoreCase(intrude.value())) this.liquidGenerationList = field;
		}
	}
	
	@SuppressWarnings({"unchecked"})
	public boolean registerDecoration(int biomeId, GenListEntry entry)
	{
		BiomeGenBase biome = BiomeGenBase.biomeList.get(biomeId);
		if(biome == null) return false;
		try
		{
			boolean bool = false;
			if(entry instanceof OreGenListEntry)
			{
				oreGenerationList.setAccessible(true);
				List<OreGenListEntry> list = (List<OreGenListEntry>) oreGenerationList.get(biome.theBiomeDecorator);
				bool = list.add((OreGenListEntry) entry);
				oreGenerationList.setAccessible(false);
			}
			else if(entry instanceof TopSoilGenListEntry)
			{
				topSoilGenerationList.setAccessible(true);
				List<TopSoilGenListEntry> list = (List<TopSoilGenListEntry>) topSoilGenerationList.get(biome.theBiomeDecorator);
				bool = list.add((TopSoilGenListEntry) entry);
				topSoilGenerationList.setAccessible(false);
			}
			else if(entry instanceof LiquidGenListEntry)
			{
				liquidGenerationList.setAccessible(true);
				List<LiquidGenListEntry> list = (List<LiquidGenListEntry>) liquidGenerationList.get(biome.theBiomeDecorator);
				bool = list.add((LiquidGenListEntry) entry);
				liquidGenerationList.setAccessible(false);
			}
			else
			{
				addonGenerationList.setAccessible(true);
				List<GenListEntry> list = (List<GenListEntry>) addonGenerationList.get(biome.theBiomeDecorator);
				bool = list.add(entry);
				addonGenerationList.setAccessible(false);
			}
			return bool;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
