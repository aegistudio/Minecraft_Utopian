package net.aegistudio.minecraft.utopian.ease;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.world.World;

public class CommonBlockGravity extends CommonBlock
{
	public static byte chunkSize = 32;
	
	public CommonBlockGravity(int id, String unlocalizedName, String... texturePaths)
	{
		this(id, unlocalizedName, Material.sand, texturePaths);
	}
	
	public CommonBlockGravity(int id, String unlocalizedName, Material material, String... texturePaths)
	{
		this(id, unlocalizedName, material, true, texturePaths);
	}
	
	protected CommonBlockGravity(int id, String unlocalizedName, Material material, boolean registerItemBlock, String... texturePaths)
	{
		super(id, unlocalizedName, material, registerItemBlock, texturePaths);
	}
	
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote) this.tryToFall(par1World, par2, par3, par4);
    }
    
    protected void tryToFall(World world, int x, int y, int z)
    {
    	blockFallingHelper(this, world, x, y, z);
    }
    
    public static void blockFallingHelper(Block block, World world, int x, int y, int z)
    {
		if (BlockSand.canFallBelow(world, x, y - 1, z) && y >= 0)
		{
		
			if (!BlockSand.fallInstantly && world.checkChunksExist(x - chunkSize, y - chunkSize, z - chunkSize, x + chunkSize, y + chunkSize, z + chunkSize))
		    {
		        if (!world.isRemote)
		        {
		            EntityFallingSand fallingSandEntity = new EntityFallingSand(world, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block.blockID, world.getBlockMetadata(x, y, z));
		            world.spawnEntityInWorld(fallingSandEntity);
		        }
		    }
		    else
		    {
		        world.setBlockToAir(x, y, z);
		
		        while (BlockSand.canFallBelow(world, x, y - 1, z) && y > 0)
		        {
		            --y;
		        }
		
		        if (y > 0) world.setBlock(x, y, z, block.blockID);
		    }
		}
    }
    
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
    }
    
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
    }
    
    public int tickRate(World par1World)
    {
        return 5;
    }
}
