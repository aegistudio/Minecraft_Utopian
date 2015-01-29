package net.minecraft.entity.player;

import java.util.concurrent.Callable;
import net.minecraft.item.ItemStack;

class CallableItemName implements Callable<String>
{
    final ItemStack theItemStack;

    final InventoryPlayer playerInventory;

    CallableItemName(InventoryPlayer par1InventoryPlayer, ItemStack par2ItemStack)
    {
        this.playerInventory = par1InventoryPlayer;
        this.theItemStack = par2ItemStack;
    }

    public String callItemDisplayName()
    {
        return this.theItemStack.getDisplayName();
    }

    public String call()
    {
        return this.callItemDisplayName();
    }
}
