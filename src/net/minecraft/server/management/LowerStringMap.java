package net.minecraft.server.management;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LowerStringMap implements Map<String, BanEntry>
{
    private final Map<String, BanEntry> internalMap = new LinkedHashMap<String, BanEntry>();

    public int size()
    {
        return this.internalMap.size();
    }

    public boolean isEmpty()
    {
        return this.internalMap.isEmpty();
    }

    public boolean containsKey(Object par1Obj)
    {
        return this.internalMap.containsKey(par1Obj.toString().toLowerCase());
    }

    public boolean containsValue(Object par1Obj)
    {
        return this.internalMap.containsKey(par1Obj);
    }

    public BanEntry get(Object par1Obj)
    {
        return this.internalMap.get(par1Obj.toString().toLowerCase());
    }
    
    /**
     * a map already defines a general put
     */
    public BanEntry putLower(String par1Str, BanEntry par2Obj)
    {
        return this.internalMap.put(par1Str.toLowerCase(), par2Obj);
    }

    public BanEntry remove(Object par1Obj)
    {
        return this.internalMap.remove(par1Obj.toString().toLowerCase());
    }
    
    public void clear()
    {
        this.internalMap.clear();
    }

    public Set<String> keySet()
    {
        return this.internalMap.keySet();
    }

    public Collection<BanEntry> values()
    {
        return this.internalMap.values();
    }

    public Set<Entry<String, BanEntry>> entrySet()
    {
        return this.internalMap.entrySet();
    }

    public BanEntry put(String par1Obj, BanEntry par2Obj)
    {
        return this.putLower((String)par1Obj, par2Obj);
    }

	@SuppressWarnings("unchecked")
	@Override
	public void putAll(Map<? extends String, ? extends BanEntry> m)
	{
        Iterator<?> var2 = m.entrySet().iterator();

        while (var2.hasNext())
        {
            Entry<? extends String, ? extends BanEntry> var3 = ((Entry<? extends String, ? extends BanEntry>) var2.next());
            this.putLower((String)var3.getKey(), var3.getValue());
        }
	}

}
