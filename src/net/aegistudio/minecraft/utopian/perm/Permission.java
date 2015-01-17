package net.aegistudio.minecraft.utopian.perm;

import java.util.Set;
import java.util.TreeSet;
import net.aegistudio.json.JSONObject;

public class Permission
{
	private final PermissionContainer permissionContainer;
	private final Permission parentLevel;
	
	private boolean hasInitialized = false;
	
	Permission(PermissionContainer container, Permission parentLevel, String permission)
	{
		this.permissionContainer = container;
		this.parentLevel = parentLevel;
	}
	
	/**
	 * permission(permission_name) = permitted set.
	 */
	private final Set<String> permitted = new TreeSet<String>();
	private final Set<String> forbidden = new TreeSet<String>();
	
	public boolean hasPermission(Object obj)
	{
		if(obj == null) return false;
		String performer = permissionContainer.generateName(obj);
		String permissionGroup = permissionContainer.getPermissionGroup(performer);
		
		return this.hasPermissionInternal(performer, permissionGroup);
	}
	
	private boolean hasPermissionInternal(String performer, String permissionGroup)
	{
		if(forbidden.contains(performer)) return false;
		if(permitted.contains(performer)) return true;
		
		if(forbidden.contains(permissionGroup)) return false;
		if(permitted.contains(permissionGroup)) return true;
		
		if(parentLevel != null) return parentLevel.hasPermissionInternal(performer, permissionGroup);
		else return false;
	}
	
	public void permitDefault(String[] allowed, String[] banned)
	{
		if(hasInitialized) return;
		permit(allowed, banned);
		hasInitialized = true;
	}
	
	public void permit(String[] allowed, String[] banned)
	{
		if(allowed != null) for(String allowedEntry : allowed) permitted.add(allowedEntry);
		if(banned != null) for(String bannedEntry : banned) permitted.add(bannedEntry);
	}
	
	public void fill(JSONObject jsonobject)
	{
		if(jsonobject == null) return;
		
		Object permitted = jsonobject.get("permitted");
		if(permitted == null) return;
		if(!(permitted instanceof Object[])) return;
		Object[] permittedArray = (Object[]) permitted;
		for(Object allowedEntry : permittedArray) this.permitted.add((String)allowedEntry);
		
		Object forbidden = jsonobject.get("forbidden");
		if(forbidden == null) return;
		if(!(forbidden instanceof Object[])) return;
		Object[] bannedArray = (Object[]) forbidden;
		for(Object bannedEntry : bannedArray) this.forbidden.add((String)bannedEntry);
	}
	
	public JSONObject toJSONObject()
	{
		JSONObject jsonobject = new JSONObject();
		jsonobject.set("permitted", this.permitted.toArray(new String[0]));
		jsonobject.set("forbidden", this.forbidden.toArray(new String[0]));
		return jsonobject;
	}
}
