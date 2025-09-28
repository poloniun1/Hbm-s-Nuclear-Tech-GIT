//Schematic to java Structure by jajo_11 | inspired by "MITHION'S .SCHEMATIC TO JAVA CONVERTINGTOOL"

package com.hbm.world.dungeon;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.itempool.ItemPool;
import com.hbm.itempool.ItemPoolsLegacy;
import com.hbm.tileentity.machine.storage.TileEntityCrateSteel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ResourcePoint extends WorldGenerator
{

	
	protected Block[] GetValidSpawnBlocks()
	{
		return new Block[]
		{
			Blocks.grass,
			Blocks.dirt,
			Blocks.sand,
			Blocks.sandstone,
			Blocks.stone,
			Blocks.hardened_clay,
			Blocks.stained_hardened_clay,
		};
	}

	public boolean LocationIsValidSpawn(World world, int x, int y, int z)
 {

		Block checkBlock = world.getBlock(x, y - 1, z);
		Block blockAbove = world.getBlock(x, y , z);
		Block blockBelow = world.getBlock(x, y - 2, z);

		for (Block i : GetValidSpawnBlocks())
		{
			if (blockAbove != Blocks.air)
			{
				return false;
			}
			if (checkBlock == i)
			{
				return true;
			}
			else if (checkBlock == Blocks.snow_layer && blockBelow == i)
			{
				return true;
			}
			else if (checkBlock.getMaterial() == Material.plants && blockBelow == i)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		int i = rand.nextInt(1);

		if(i == 0)
		{
		    generate_r0(world, rand, x, y, z);
		}

       return true;

	}

	public boolean generate_r0(World world, Random rand, int x, int y, int z)
	{
		if(!LocationIsValidSpawn(world, x, y, z ))
		{
			return false;
		}
		
		for(int i = x - 3; i <= x + 3; i++){
			for(int j = z - 3; j <= z + 3; j++){
				world.setBlock(i, y - 1, j, ModBlocks.concrete_super, 0, 3);
			}
		}

		for(int i = x - 3; i <= x + 3; i++){
			for(int j = z - 3; j <= z + 3; j++){
				world.setBlock(i, y + 4, j, ModBlocks.concrete_super, 0, 3);
			}
		}

		for(int i = y; i <= y + 3; i++){
			for(int j = z - 3; j <= z + 3; j++){
				world.setBlock(x - 3, i, j, ModBlocks.concrete_super, 0, 3);
			}
		}
		
		for(int i = y; i <= y + 3; i++){
			for(int j = z - 3; j <= z + 3; j++){
				world.setBlock(x + 3, i, j, ModBlocks.concrete_super, 0, 3);
			}
		}
		
		for(int i = x - 3; i <= x + 3; i++){
			for(int j = y; j <= y + 3; j++){
				world.setBlock(i, j, z - 3, ModBlocks.concrete_super, 0, 3);
			}
		}

		for(int i = x - 3; i <= x + 3; i++){
			for(int j = y; j <= y + 3; j++){
				world.setBlock(i, j, z + 3, ModBlocks.concrete_super, 0, 3);
			}
		}
		
		for(int i = x - 2; i <= x - 1 ; i++){
			for(int j = z - 2; j <= z + 2; j++){
				world.setBlock(i, y, j, ModBlocks.crate_steel, 0, 3);
				if(world.getBlock(i, y, j) == ModBlocks.crate_steel)
				{
					WeightedRandomChestContent.generateChestContents(rand, ItemPool.getPool(ItemPoolsLegacy.POOL_NUKE_MISC), (TileEntityCrateSteel)world.getTileEntity(i, y, j), 18);
				}
			}
		}
		
		for(int i = x; i <= x + 2; i++){
			for(int j = z - 2; j <= z + 2; j++){
				world.setBlock(i, y, j, ModBlocks.crate_steel, 0, 3);
				if(world.getBlock(i, y , j) == ModBlocks.crate_steel)
				{
					WeightedRandomChestContent.generateChestContents(rand, ItemPool.getPool(ItemPoolsLegacy.POOL_SPACESHIP), (TileEntityCrateSteel)world.getTileEntity(i, y, j), 18);
				}
			}
		}
		
		for(int i = x - 2; i <= x + 2; i++){
			for(int j = z - 2; j <= z + 2; j++){
				world.setBlock(i, y + 1, j, ModBlocks.crate_lead, 0, 3);
			}
		}
		
		for(int i = x - 2; i <= x + 2; i++){
			for(int j = z - 2; j <= z + 2; j++){
				world.setBlock(i, y + 2, j, ModBlocks.crate, 0, 3);
			}
		}

		for(int i = x - 2; i <= x + 2; i++){
			for(int j = z - 2; j <= z + 2; j++){
				world.setBlock(i, y + 3, j, ModBlocks.crate_metal, 0, 3);
			}
		}
		
		return true;

	}
}