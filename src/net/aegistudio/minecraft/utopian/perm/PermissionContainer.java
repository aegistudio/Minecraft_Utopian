package net.aegistudio.minecraft.utopian.perm;

import java.util.Map;
import java.util.TreeMap;

import net.aegistudio.json.JSONObject;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityCommandBlock;

public class PermissionContainer
{
	private final Map<String, String> permissionGroups = new TreeMap<String, String>();
	private final Map<String, Permission> permissions = new TreeMap<String, Permission>();
	
	public Permission registerPermission(String permission)
	{
		Permission permObj = null;
		if((permObj = permissions.get(permission)) != null) return permObj;
		int truncation = permission.lastIndexOf('.');
		if(truncation != -1) permObj = registerPermission(permission.substring(0, truncation));
		Permission current = new Permission(this, permObj, permission);
		permissions.put(permission, current);
		return current;
	}
	
	public String getPermissionGroup(String performer)
	{
		String group = permissionGroups.get(performer);
		if(group == null) group = ".default";
		return group;
	}
	
	public String generateName(Object obj)
	{
		if(obj instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP)obj;
			return "player." + player.getEntityName();
		}
		else if(obj instanceof MinecraftServer) return "server";
		else if(obj instanceof TileEntityCommandBlock) return "commandBlock";
		else return ".default";
	}
	
	public JSONObject toJSONObject()
	{
		JSONObject jsonobject = new JSONObject();
		jsonobject.set("groups", toPermissionGroups());
		jsonobject.set("permissions", toPermissions());
		return jsonobject;
	}
	
	public JSONObject toPermissionGroups()
	{
		String[] keys = permissionGroups.keySet().toArray(new String[0]);
		JSONObject jsonobject = new JSONObject();
		for(String key : keys) jsonobject.set(key, permissionGroups.get(key));
		return jsonobject;
	}
	
	public JSONObject toPermissions()
	{
		String[] keys = permissions.keySet().toArray(new String[0]);
		JSONObject jsonobject = new JSONObject();
		for(String key : keys) jsonobject.set(key, permissions.get(key).toJSONObject());
		return jsonobject;
	}
}
