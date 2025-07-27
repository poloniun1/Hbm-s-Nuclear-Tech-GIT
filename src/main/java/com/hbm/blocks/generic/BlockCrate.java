package com.hbm.blocks.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.weapon.sedna.factory.GunFactory.EnumAmmoSecret;
import com.hbm.blocks.ModBlocks2;

import com.hbm.items.ModItems;
import com.hbm.items.ModItems2;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockCrate extends BlockFalling {

	List<ItemStack> crateList;
	List<ItemStack> weaponList;
	List<ItemStack> leadList;
	List<ItemStack> metalList;
	List<ItemStack> redList;

	public BlockCrate(Material p_i45394_1_) {
		super(p_i45394_1_);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(player.getHeldItem() != null && player.getHeldItem().getItem().equals(ModItems.crowbar)) {
			if(!world.isRemote) {
				dropItems(world, x, y, z);
				world.setBlockToAir(x, y, z);
				world.playSoundEffect(x, y, z, "hbm:block.crateBreak", 0.5F, 1.0F);
			}
			return true;
		}
		return false;
	}

	public void setDrops() {

		crateList = new ArrayList();
		weaponList = new ArrayList();
		leadList = new ArrayList();
		metalList = new ArrayList();
		redList = new ArrayList();

		// Supply Crate
		BlockCrate.addToListWithWeight(crateList, ModItems.centrifuge_element, 600);
		BlockCrate.addToListWithWeight(crateList, ModItems.motor, 800);
		BlockCrate.addToListWithWeight(crateList, ModItems.reactor_core, 400);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.mold_base,3), 900);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.piston_set,1,1), 400);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.piston_set,1,3), 200);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.arc_electrode,3,3), 500);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,5), 100);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,5,1), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,5,2), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,5,3), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,5,4), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,5,5), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,5,6), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,5,7), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,4,8), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,4,9), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,4,10), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,3,11), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,3,12), 50);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,2,13), 20);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,2,14), 20);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,1,15), 20);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,1,16), 20);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,1,17), 20);
		BlockCrate.addToListWithWeight(crateList, new ItemStack(ModItems.circuit,2,18), 50);
		BlockCrate.addToListWithWeight(crateList, ModItems.coil_tungsten, 700);
		BlockCrate.addToListWithWeight(crateList, ModItems.photo_panel, 300);
		BlockCrate.addToListWithWeight(crateList, ModItems.coil_copper, 1000);
		BlockCrate.addToListWithWeight(crateList, ModItems.coil_gold, 800);
		BlockCrate.addToListWithWeight(crateList, ModItems.tank_steel, 900);
		BlockCrate.addToListWithWeight(crateList, ModItems.blades_desh, 300);
		BlockCrate.addToListWithWeight(crateList, ModItems.blades_advanced_alloy, 700);
		BlockCrate.addToListWithWeight(crateList, ModItems.battery_sc_polonium, 400);
		BlockCrate.addToListWithWeight(crateList, ModItems.battery_sc_lead, 100);
		BlockCrate.addToListWithWeight(crateList, ModItems.piston_selenium, 600);
		BlockCrate.addToListWithWeight(crateList, ModItems.battery_schrabidium_cell_4, 300);
		BlockCrate.addToListWithWeight(crateList, ModItems.cube_power, 10);
		BlockCrate.addToListWithWeight(crateList, ModItems.ams_core_thingy, 2);
		BlockCrate.addToListWithWeight(crateList, ModItems.ams_core_eyeofharmony, 5);

		// Weapon Crate
		BlockCrate.addToListWithWeight(weaponList, ModItems.dns_legs, 3);
		BlockCrate.addToListWithWeight(weaponList, ModItems.dns_boots, 3);
		BlockCrate.addToListWithWeight(weaponList, ModItems.dns_plate, 3);
		BlockCrate.addToListWithWeight(weaponList, ModItems.dns_helmet, 3);
		BlockCrate.addToListWithWeight(weaponList, ModItems.meteorite_sword_baleful, 5);
		BlockCrate.addToListWithWeight(weaponList, ModItems.schrabidium_shovel, 5);
		BlockCrate.addToListWithWeight(weaponList, ModItems2.osmiridium_pickaxe, 3);

		BlockCrate.addToListWithWeight(weaponList, ModItems.missile_strong, 25);
		BlockCrate.addToListWithWeight(weaponList, ModItems.missile_stealth, 25);
		BlockCrate.addToListWithWeight(weaponList, ModItems.missile_generic, 25);

		// Lead Crate
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_uranium, 5), 10);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_u235, 3), 5);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_u238, 2), 8);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_plutonium, 5), 7);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_pu238, 2), 5);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_pu239, 2), 4);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_neptunium, 2), 7);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_schrabidium, 1), 3);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_australium, 2), 4);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_au198, 1), 3);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_pb209, 1), 3);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.cell_deuterium, 5), 8);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.cell_tritium, 3), 8);
		BlockCrate.addToListWithWeight(leadList,  ModItems.pellet_rtg_lead, 5);
		BlockCrate.addToListWithWeight(leadList,  new ItemStack(ModItems.ingot_polonium, 2), 5);
		BlockCrate.addToListWithWeight(leadList,  ModItems.rbmk_fuel_empty, 8);
		BlockCrate.addToListWithWeight(leadList,  ModItems.rod_zirnox_empty, 8);

		// Metal Crate
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_press), 100);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_epress), 80);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_difurnace_off), 90);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_difurnace_rtg_off), 50);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_crucible), 70);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_arc_furnace), 50);
		BlockCrate.addToListWithWeight(metalList, new ItemStack(Item.getItemFromBlock(ModBlocks.foundry_basin), 2), 70);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_reactor_breeding), 70);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.reactor_research), 100);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_rtg_furnace_off), 70);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_diesel), 80);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_wood_burner), 80);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_combustion_engine), 40);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_turbofan), 30);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_powerrtg), 40);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.red_pylon), 90);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.crate_desh), 90);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.reactor_zirnox), 90);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.pwr_controller), 80);
		BlockCrate.addToListWithWeight(metalList, new ItemStack(Item.getItemFromBlock(ModBlocks.pwr_fuel), 8), 80);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.pwr_neutron_source), 20);
		BlockCrate.addToListWithWeight(metalList, new ItemStack(Item.getItemFromBlock(ModBlocks.pwr_reflector), 8), 20);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.rbmk_rod_reasim_mod), 30);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.rbmk_outgasser), 50);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_electric_furnace_off), 80);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_assembler), 100);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_fluidtank), 70);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.anvil_dnt), 1);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.anvil_arsenic_bronze), 30);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.machine_schrabidium_battery), 70);
		BlockCrate.addToListWithWeight(metalList, new ItemStack(Item.getItemFromBlock(ModBlocks.custom_machine), 1, 101), 30);
		BlockCrate.addToListWithWeight(metalList, new ItemStack(Item.getItemFromBlock(ModBlocks.custom_machine), 1, 104), 30);
		BlockCrate.addToListWithWeight(metalList, new ItemStack(Item.getItemFromBlock(ModBlocks.cm_port), 3, 3), 30);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks.cm_flux), 30);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks2.rbmk_turbine), 10);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks2.barrel_antimatter_ex), 4);
		BlockCrate.addToListWithWeight(metalList, Item.getItemFromBlock(ModBlocks2.machine_ex_battery), 4);


		// Red Crate
		BlockCrate.addToListWithWeight(redList, ModItems.mysteryshovel, 1);
		BlockCrate.addToListWithWeight(redList, ModItems.gun_heavy_revolver_lilmac, 1);
		BlockCrate.addToListWithWeight(redList, ModItems.gun_autoshotgun_sexy, 1);
		BlockCrate.addToListWithWeight(redList, ModItems.gun_maresleg_broken, 1);
		BlockCrate.addToListWithWeight(redList, new ItemStack(ModItems.ammo_secret, 1, EnumAmmoSecret.M44_EQUESTRIAN.ordinal()), 1);
		BlockCrate.addToListWithWeight(redList, new ItemStack(ModItems.ammo_secret, 1, EnumAmmoSecret.G12_EQUESTRIAN.ordinal()), 1);
		BlockCrate.addToListWithWeight(redList, new ItemStack(ModItems.ammo_secret, 1, EnumAmmoSecret.BMG50_EQUESTRIAN.ordinal()), 1);
		BlockCrate.addToListWithWeight(redList, ModItems.battery_spark, 1);
		BlockCrate.addToListWithWeight(redList, ModItems.bottle_sparkle, 1);
		BlockCrate.addToListWithWeight(redList, ModItems.bottle_rad, 1);
		BlockCrate.addToListWithWeight(redList, ModItems.ring_starmetal, 1);
		BlockCrate.addToListWithWeight(redList, ModItems.flame_pony, 1);
		BlockCrate.addToListWithWeight(redList, Item.getItemFromBlock(ModBlocks.ntm_dirt), 1);
		BlockCrate.addToListWithWeight(redList, Item.getItemFromBlock(ModBlocks.broadcaster_pc), 1);
	}

	public void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		setDrops();

		List<ItemStack> list = new ArrayList();

		int i = this == ModBlocks.crate_weapon ? rand.nextInt(3)+2 : rand.nextInt(3) + 8;


		for(int j = 0; j < i; j++) {

			if(this == ModBlocks.crate)
				list.add(crateList.get(rand.nextInt(crateList.size())));
			if(this == ModBlocks.crate_weapon)
				list.add(weaponList.get(rand.nextInt(weaponList.size())));
			if(this == ModBlocks.crate_lead)
				list.add(leadList.get(rand.nextInt(leadList.size())));
			if(this == ModBlocks.crate_metal)
				list.add(metalList.get(rand.nextInt(metalList.size())));
			if(this == ModBlocks.crate_red)
				list.add(redList.get(rand.nextInt(redList.size())));
		}

		if(this == ModBlocks.crate_red) {
			list.clear();

			for(int k = 0; k < redList.size(); k++) {
				list.add(redList.get(k));
			}
		}

		for(ItemStack stack : list) {
			float f = rand.nextFloat() * 0.8F + 0.1F;
			float f1 = rand.nextFloat() * 0.8F + 0.1F;
			float f2 = rand.nextFloat() * 0.8F + 0.1F;

			EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, stack.copy());

			float f3 = 0.05F;
			entityitem.motionX = (float) rand.nextGaussian() * f3;
			entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
			entityitem.motionZ = (float) rand.nextGaussian() * f3;
			if(!world.isRemote)
				world.spawnEntityInWorld(entityitem);
		}
	}

	public static void addToListWithWeight(List<ItemStack> list, Item item, int weight) {
		for(int i = 0; i < weight; i++) list.add(new ItemStack(item));
	}

	public static void addToListWithWeight(List<ItemStack> list, ItemStack item, int weight) {
		for(int i = 0; i < weight; i++) list.add(item);
	}
}
