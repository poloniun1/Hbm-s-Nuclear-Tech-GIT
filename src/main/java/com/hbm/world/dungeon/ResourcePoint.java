//Schematic to java Structure by jajo_11 | inspired by "MITHION'S .SCHEMATIC TO JAVA CONVERTINGTOOL"

package com.hbm.world.dungeon;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.itempool.ItemPool;
import com.hbm.itempool.ItemPoolsLegacy;
import com.hbm.itempool.ItemPoolsSingle;
import com.hbm.itempool.ItemPoolsSatellite;
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
		
		for(int i = x ; i <= x + 5; i++){
			for(int j = z; j <= z + 5; j++){
				world.setBlock(i, y - 1, j, ModBlocks.reinforced_ducrete, 0, 3);
			}
		}

		for(int i = x ; i <= x + 5; i++){
			for(int j = z; j <= z + 5; j++){
				world.setBlock(i, y + 2, j, ModBlocks.reinforced_ducrete, 0, 3);
			}
		}

		for(int i = y; i <= y + 1; i++){
			for(int j = z; j <= z + 5; j++){
				world.setBlock(x, i, j, ModBlocks.reinforced_ducrete, 0, 3);
			}
		}
		
		for(int i = y; i <= y + 1; i++){
			for(int j = z; j <= z + 5; j++){
				world.setBlock(x + 5, i, j, ModBlocks.reinforced_ducrete, 0, 3);
			}
		}
		
		for(int i = x; i <= x + 5; i++){
			for(int j = y; j <= y + 1; j++){
				world.setBlock(i, j, z, ModBlocks.reinforced_ducrete, 0, 3);
			}
		}

		for(int i = x; i <= x + 5; i++){
			for(int j = y; j <= y + 1; j++){
				world.setBlock(i, j, z + 5, ModBlocks.reinforced_ducrete, 0, 3);
			}
		}
		
		for(int i = x + 1; i <= x + 4 ; i++){
				world.setBlock(i, y, z + 1, ModBlocks.crate_steel, 0, 3);
				if(world.getBlock(i, y, z + 1) == ModBlocks.crate_steel)
				{
					WeightedRandomChestContent.generateChestContents(rand, ItemPool.getPool(ItemPoolsLegacy.POOL_NUKE_MISC), (TileEntityCrateSteel)world.getTileEntity(i, y, z + 1), 24);
				}
		}

		for(int i = x + 1; i <= x + 4; i++){
				world.setBlock(i, y, z + 2, ModBlocks.crate_steel, 0, 3);
				if(world.getBlock(i, y, z + 2) == ModBlocks.crate_steel)
				{
					WeightedRandomChestContent.generateChestContents(rand, ItemPool.getPool(ItemPoolsLegacy.POOL_SPACESHIP), (TileEntityCrateSteel)world.getTileEntity(i, y, z + 2), 24);
				}
		}


		for(int i = x + 1; i <= x + 4; i++){
			for(int j = z + 3; j <= z + 4; j++){
				world.setBlock(i, y, j, ModBlocks.crate_steel, 0, 3);
				if(world.getBlock(i, y, j) == ModBlocks.crate_steel)
				{
					WeightedRandomChestContent.generateChestContents(rand, ItemPool.getPool(ItemPoolsSatellite.POOL_SAT_MINER), (TileEntityCrateSteel)world.getTileEntity(i, y, j), 24);
				}
			}
		}

		
		for(int i = x + 1; i <= x + 4; i++){
				world.setBlock(i, y + 1, z + 1, ModBlocks.crate_lead, 0, 3);
		}
		
		for(int i = x + 1; i <= x + 4; i++){
				world.setBlock(i, y + 1, z + 2, ModBlocks.crate, 0, 3);
		}
		
		for(int i = x + 1; i <= x + 4; i++){
				world.setBlock(i, y + 1, z + 3, ModBlocks.crate_metal, 0, 3);
		}
		
		for(int i = x + 1; i <= x + 4; i++){
				world.setBlock(i, y + 1, z + 4, ModBlocks.crate_steel, 0, 3);
				if(world.getBlock(i, y + 1, z + 4) == ModBlocks.crate_steel)
				{
					WeightedRandomChestContent.generateChestContents(rand, ItemPool.getPool(ItemPoolsSingle.POOL_POWDER), (TileEntityCrateSteel)world.getTileEntity(i, y + 1, z + 4), 24);
				}
		}
		
		return true;

	}
}