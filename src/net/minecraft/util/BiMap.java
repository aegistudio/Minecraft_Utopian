package net.minecraft.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class is composed by aegistudio for quick hash calculation
 * and efficiency-space trade off.
 * @author aegistudio
 */

public class BiMap<Types> implements Map<Integer, Types>
{
	
	public Object[][] bimapper;
	
	public BiMap()
	{
		this(16, 8);
	}
	
	private final int chunkSize;
	private final int chunkMask;
	private final int hisize;
	
	public BiMap(int maximalBits, int chunkSize)
	{
		if(chunkSize <= 0) throw new IllegalArgumentException();
		if(maximalBits <= chunkSize) throw new IllegalArgumentException();
		this.chunkSize = chunkSize;
		this.chunkMask = (1 << this.chunkSize) - 1;
		this.hisize = 1 << (maximalBits - this.chunkSize);
		bimapper = new Object[hisize][];
		this.clear();
	}
	
	@Override
	public void clear()
	{
		for(int i = 0; i < bimapper.length; i ++) bimapper[i] = null;
	}

	@Override
	public boolean containsKey(Object key)
	{
		return this.get(key) != null;
	}

	private int calculateHighBits(int key)
	{
		int hibit = key >> this.chunkSize;
		if(hibit < 0 || hibit >= hisize) return -1;
		return hibit;
	}
	
	private int calculateLowBits(int key)
	{
		return key & chunkMask;
	}
	
	@Override
	public boolean containsValue(Object value)
	{
		if(value == null) return false;
		for(int i = 0; i < bimapper.length; i ++) if(bimapper[i] != null)
			for(int j = 0; j < bimapper[i].length; j ++) if((bimapper[i])[j] != null)
				if((bimapper[i])[j].equals(value)) return true;
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<Integer, Types>> entrySet()
	{
		HashSet<java.util.Map.Entry<Integer, Types>> set = new HashSet<java.util.Map.Entry<Integer, Types>>();
		for(int i = 0; i < bimapper.length; i ++) 
			if(bimapper[i] != null)
				for(int j = 0; j < bimapper[i].length; j ++) if((bimapper[i])[j] != null)
					set.add(new Entry<Types>(this, i, j));
		return set;
	}

	@SuppressWarnings("hiding")
	private class Entry<Types> implements Map.Entry<Integer, Types>
	{
		private final int i_copied;
		private final int j_copied;
		private final int key;
		private final BiMap<Types> bimap;
		
		public Entry(BiMap<Types> bimap, int i_copied, int j_copied)
		{
			this.bimap = bimap;
			this.i_copied = i_copied;
			this.j_copied = j_copied;
			this.key = (i_copied << bimap.chunkSize) + j_copied;
		}
		
		@Override
		public Integer getKey()
		{
			return key;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Types getValue()
		{
			return (Types) (bimap.bimapper[i_copied])[j_copied];
		}

		@Override
		public Types setValue(Types value)
		{
			(bimap.bimapper[i_copied])[j_copied] = value;
			return value;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Types get(Object key)
	{
		if(key == null) return null;
		if(!(key instanceof Integer)) return null;
		Integer intkey = (Integer)key;
		int hibit = -1;
		if((hibit = this.calculateHighBits(intkey)) == -1) return null;
		if(bimapper[hibit] == null) return null;
		int lobit = this.calculateLowBits(intkey);
		return (Types)(bimapper[hibit])[lobit];
	}

	@Override
	public boolean isEmpty()
	{
		for(int i = 0; i < bimapper.length; i ++) 
			if(bimapper[i] != null)
				for(int j = 0; j < bimapper[i].length; j ++) if((bimapper[i])[j] != null)
					return false;
		return true;
	}

	@Override
	public Set<Integer> keySet()
	{
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < bimapper.length; i ++) 
			if(bimapper[i] != null)
			{
				int hibit = i << chunkSize;
				for(int j = 0; j < bimapper[i].length; j ++) if((bimapper[i])[j] != null)
					set.add(hibit + j);
			}
		return set;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Types put(Integer key, Types value)
	{
		if(key == null) return null;
		if(!(key instanceof Integer)) return null;
		Integer intkey = (Integer)key;
		int hibit = -1;
		if((hibit = this.calculateHighBits(intkey)) == -1) return null;
		if(bimapper[hibit] == null) bimapper[hibit] = new Object[1 << this.chunkSize];
		int lobit = this.calculateLowBits(intkey);
		(bimapper[hibit])[lobit] = value;
		return (Types) (bimapper[hibit])[lobit];
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends Types> m)
	{
		Integer[] keys = m.keySet().toArray(new Integer[0]);
		for(Integer key : keys) put(key, m.get(key));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Types remove(Object key)
	{
		if(key == null) return null;
		if(!(key instanceof Integer)) return null;
		Integer intkey = (Integer)key;
		int hibit = -1;
		if((hibit = this.calculateHighBits(intkey)) == -1) return null;
		if(bimapper[hibit] == null) return null;
		int lobit = this.calculateLowBits(intkey);
		Types returnValue = (Types) (bimapper[hibit])[lobit];
		(bimapper[hibit])[lobit] = null;
		return returnValue;
	}

	/**
	 * this method is useless/not used.
	 * @return null
	 */
	
	@Deprecated
	@Override
	public int size()
	{
		return this.keySet().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Types> values()
	{
		ArrayList<Types> set = new ArrayList<Types>();
		for(int i = 0; i < bimapper.length; i ++) 
			if(bimapper[i] != null)
				for(int j = 0; j < bimapper[i].length; j ++) if((bimapper[i])[j] != null)
					set.add((Types) (bimapper[i])[j]);
		return set;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] arguments)
	{
		Map<Integer, String> bimap = new BiMap<String>();
		bimap.put(10021, "AA");
		bimap.put(3, "AA");
		bimap.put(10031, "AC");
		Integer[] keys = bimap.keySet().toArray(new Integer[0]);
		for(Integer key : keys) System.out.println(key + ":" + bimap.get(key));
		
		String[] values = bimap.values().toArray(new String[0]);
		for(String value : values) System.out.println(value);
		
		Object[] entries = bimap.entrySet().toArray();
		for(Object entry : entries)
		{
			Map.Entry<Integer, String> mapentry = (java.util.Map.Entry<Integer, String>) entry;
			System.out.println(mapentry.getKey() + ":" + mapentry.getValue());
		}
	}
}
