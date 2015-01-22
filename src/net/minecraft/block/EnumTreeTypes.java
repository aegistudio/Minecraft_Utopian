package net.minecraft.block;

public enum EnumTreeTypes
{
	oak("tree_side", "wood"),
	spruce("tree_spruce", "wood_spruce"),
	birch("tree_birch", "wood_birch"),
	jungle("tree_jungle", "wood_jungle");
	
	public final String textureLog;
	
	public final String textureWoodPlank;
	
	private EnumTreeTypes(String textureLog, String textureWoodPlank)
	{
		this.textureLog = textureLog;
		this.textureWoodPlank = textureWoodPlank;
	}
	
	public static final String[] stringArray;
	static
	{
		EnumTreeTypes[] treeTypes = values();
		stringArray = new String[treeTypes.length];
		for(int i = 0; i < treeTypes.length; i ++) stringArray[i] = treeTypes[i].toString();
	}
}
