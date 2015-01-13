package net.minecraft.item;

public abstract class ItemInfoContainer
{
	private static ItemInfoContainer instance;
	
	public static ItemInfoContainer getItemInfoContainer()
	{
		return instance;
	}
	
	public abstract Item getItem(int itemid);
	
	public abstract void setItem(Item item);
	
	public abstract Item[] getRegisteredItems();
	
	public abstract int getMaximumItemId();
}
