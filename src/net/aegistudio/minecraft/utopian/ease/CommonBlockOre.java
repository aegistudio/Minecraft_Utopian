package net.aegistudio.minecraft.utopian.ease;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * The block that drops the ore items (acting like the coal, lapis and diamond ore).
 * Could be silk touched and generates one item when burnt in furnace.
 * @author aegistudio
 */

public class CommonBlockOre extends CommonBlock
{
	private final Item oreItem;
	private int droppedItemCount = 1;
	private int droppedItemBias = 0;
	
	private int minExp = 0;
	private int maxExp = 0;
	
	public CommonBlockOre(int id, String unlocalizedName, Item oreItem, String... texturePaths)
	{
		this(id, unlocalizedName, Material.rock, oreItem, 0.3f, texturePaths);
	}
	
	protected CommonBlockOre(int id, String unlocalizedName, Material material, Item oreItem, float smeltingTime, String... texturePaths)
	{
		super(id, unlocalizedName, material, true, texturePaths);
		if(oreItem == null) throw new IllegalArgumentException("The target ore item of this ore block should be provided!"); 
		this.oreItem = oreItem;
		FurnaceRecipes.smelting().addSmelting(this.blockID, new ItemStack(this.oreItem), smeltingTime);
	}
	
	public CommonBlockOre setDroppedItemCount(int droppedItemCount)
	{
		if(droppedItemCount > 0) this.droppedItemCount = droppedItemCount;
		return this;
	}
	
	public CommonBlockOre setDroppedItemBias(int droppedItemBias)
	{
		if(droppedItemBias > 0) this.droppedItemBias = droppedItemBias;
		return this;
	}
	
	public CommonBlockOre setMinExperience(int minExp)
	{
		if(minExp < 0) return this;
		this.minExp = minExp;
		if(this.maxExp < this.minExp) this.maxExp = this.minExp;
		return this;
	}
	
	public CommonBlockOre setMaxExperience(int maxExp)
	{
		if(maxExp < this.minExp) return this;
		this.maxExp = maxExp;
		return this;
	}
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.oreItem.itemID;
    }

    public int quantityDropped(Random random)
    {
        return droppedItemBias == 0?  droppedItemCount : droppedItemCount + random.nextInt(droppedItemBias);
    }

    /**
     * Directly copied from the BlockOre.
     */
    public int quantityDroppedWithBonus(int i, Random random)
    {
        if (i > 0 && this.blockID != this.idDropped(0, random, i))
        {
            int var3 = random.nextInt(i + 2) - 1;
            if (var3 < 0) var3 = 0;
            return this.quantityDropped(random) * (var3 + 1);
        }
        else return this.quantityDropped(random);
    }

    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(world, x, y, z, par5, par6, par7);
        if(this.minExp > 0)
        {
	        int exp = MathHelper.getRandomIntegerInRange(world.rand, this.minExp, this.maxExp);
	        this.dropXpOnBlockBreak(world, x, y, z, exp);
        }
    }

    public int damageDropped(int par1)
    {
        return 0;
    }
}
