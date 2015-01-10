package net.aegistudio.minecraft.utopian.patch.core;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.aegistudio.json.JSONObject;
import net.aegistudio.minecraft.utopian.JSONFile;
import net.aegistudio.minecraft.utopian.event.EventHandler;
import net.aegistudio.minecraft.utopian.event.EventHandlerRegistry;
import net.aegistudio.minecraft.utopian.event.runtime.ShutdownEvent;
import net.aegistudio.minecraft.utopian.patch.Patch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemInfoContainer;

public class Patch_ItemInfoContainer extends ItemInfoContainer implements Patch
{
	@Override
	public boolean install() throws Exception
	{
		Field instance = ItemInfoContainer.class.getDeclaredField("instance");
		instance.setAccessible(true);
		instance.set(null, this);
		instance.setAccessible(false);
		EventHandlerRegistry.getEventHandlerRegistry().registerHandler(this, ShutdownEvent.class);		
		return true;
	}

	private static final JSONObject iteminfocontainer = new JSONObject();
	static
	{
		iteminfocontainer.set("name", "Item Info Container");
		iteminfocontainer.set("version", "0.0.0.2 alpha");
		iteminfocontainer.set("features", new String[]{
				"32000 items, wow! but we just use very ",
				"small part of them, so why should we ",
				"allocates so many spaces for so little ",
				"items? "
		});
	}
	
	@Override
	public JSONObject getDescription()
	{
		return iteminfocontainer;
	}

	final Map<Integer, Item> itemsList = new HashMap<Integer, Item>();
	
	@Override
	public Item getItem(int itemid)
	{
		return itemsList.get(itemid);
	}

	@Override
	public void setItem(int itemid, Item item)
	{
		itemsList.put(itemid, item);
	}

	@Override
	public Item[] getRegisteredItems()
	{
		return itemsList.values().toArray(new Item[0]);
	}

	@Override
	public int getMaximumItemId()
	{
		return 32000;
	}
	
	@EventHandler(ShutdownEvent.class)
	public void onShutdown(ShutdownEvent event)
	{
		this.saveItemInfo();
	}
	
	public String[] checkCoherence()
	{
		ArrayList<String> noncoherenceItems = new ArrayList<String>(); 
		try
		{
			File itemRegistryFile = new File("items.json");
			JSONObject itemRegistry = new JSONFile(itemRegistryFile).getObject();
			String[] keys = itemRegistry.keys();
			for(String key : keys) if(key != null)
			{
				Integer itemid = Integer.parseInt(key);
				if(this.itemsList.get(itemid) == null)
					noncoherenceItems.add("missing itemid@" + itemid + " : " + itemRegistry.get(key));
				else if(!this.itemsList.get(itemid).getUnlocalizedName().equals(itemRegistry.get(key)))
					noncoherenceItems.add("modified itemid@" + itemid + " : " + itemRegistry.get(key));
			}
		}
		catch(Exception exception)
		{
		}
		return noncoherenceItems.toArray(new String[0]);
	}
	
	public void saveItemInfo()
	{
		try
		{
			System.out.println("Saving item informations...");
			JSONObject itemRegistry = new JSONObject();
			Item[] items = this.getRegisteredItems();
			for(Item item : items) if(item != null)
				itemRegistry.set(Integer.toString(item.itemID), item.getUnlocalizedName());
			PrintStream blockRegistryWriter = new PrintStream(new File("items.json"));
			blockRegistryWriter.print(itemRegistry.toString());
			blockRegistryWriter.close();
		}
		catch(Exception exception)
		{
			
		}
	}
}
