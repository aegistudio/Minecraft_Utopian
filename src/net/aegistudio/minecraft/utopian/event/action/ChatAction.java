package net.aegistudio.minecraft.utopian.event.action;

import net.aegistudio.minecraft.utopian.event.Event;
import net.minecraft.entity.player.EntityPlayer;

/**
 * - Raised when the player is to send a chat or command through its chatting gui. (Client)<br>
 * - Raised when the player is send a chat message. (Server)
 * @side <b>Both</b>
 * @author aegistudio
 */

public class ChatAction extends Event implements Action
{

	private EntityPlayer player;
	private String chatmessage;
	private boolean isServerAction;
	
	public ChatAction(EntityPlayer player, String chatmessage, boolean isServerAction)
	{
		this.player = player;
		this.chatmessage = chatmessage;
		this.isServerAction = isServerAction;
	}
	
	@Override
	public EntityPlayer getEventPlayer()
	{
		return this.player;
	}

	@Override
	public boolean isServerAction()
	{
		return this.isServerAction;
	}
	
	public String getChatMessage()
	{
		return this.chatmessage;
	}
	
	public void setChatMessage(String chatmessage)
	{
		this.chatmessage = chatmessage;
	}
}
