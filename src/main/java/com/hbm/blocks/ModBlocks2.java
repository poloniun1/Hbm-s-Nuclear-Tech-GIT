package com.hbm.blocks;

import com.hbm.blocks.BlockEnums.*;
import com.hbm.blocks.bomb.*;
import com.hbm.blocks.fluid.*;
import com.hbm.blocks.gas.*;
import com.hbm.blocks.generic.*;
import com.hbm.blocks.generic.BlockHazard.ExtDisplayEffect;
import com.hbm.blocks.generic.BlockMotherOfAllOres.ItemRandomOreBlock;
import com.hbm.blocks.machine.*;
import com.hbm.blocks.machine.pile.*;
import com.hbm.blocks.machine.rbmk.*;
import com.hbm.blocks.network.*;
import com.hbm.blocks.rail.*;
import com.hbm.blocks.test.*;
import com.hbm.blocks.turret.*;
import com.hbm.items.block.*;
import com.hbm.items.bomb.ItemPrototypeBlock;
import com.hbm.items.special.ItemOreBlock;
import com.hbm.lib.ModDamageSource;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.DoorDecl;
import com.hbm.tileentity.machine.storage.TileEntityFileCabinet;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModBlocks2 {
	
	public static void mainRegistry()
	{
		initializeBlock();
		registerBlock();
	}
	
	public static Block machine_dineutronium_battery_ex;
	public static Block machine_electron_battery;
	public static Block machine_ex_battery;

	public static Block barrel_antimatter_ex;

	public static Block silo_hatch_ex;
	public static Block silo_hatch_large_ex;

	public static Block ore_manganese;
	public static Block ore_sodium;

	public static Block rbmk_turbine;
	public static Block rbmk_large_turbine;
	public static Block rbmk_chungus;

	private static void initializeBlock() {
		machine_dineutronium_battery_ex = new MachineBattery(Material.iron, 100_000_000_000_000L).setBlockName("machine_dineutronium_battery_ex").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
		machine_electron_battery = new MachineBattery(Material.iron, 50_000_000_000_000_000L).setBlockName("machine_electron_battery").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
		machine_ex_battery = new MachineBattery(Material.iron, 4_600_000_000_000_000_000L).setBlockName("machine_ex_battery").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);

		rbmk_turbine = new MachineTurbine(Material.iron).setBlockName("rbmk_turbine").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab).setBlockTextureName(RefStrings.MODID + ":machine_turbine");
		rbmk_large_turbine = new MachineLargeTurbine(Material.iron).setBlockName("rbmk_large_turbine").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab).setBlockTextureName(RefStrings.MODID + ":machine_large_turbine");
		rbmk_chungus = new MachineChungus(Material.iron).setBlockName("rbmk_chungus").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab).setBlockTextureName(RefStrings.MODID + ":machine_chungus");

		barrel_antimatter_ex = new BlockFluidBarrel(Material.iron, 4096000).setBlockName("barrel_antimatter_ex").setStepSound(Block.soundTypeMetal).setHardness(2.0F).setResistance(5.0F).setCreativeTab(MainRegistry.machineTab).setBlockTextureName(RefStrings.MODID + ":barrel_antimatter");

		silo_hatch_ex = new BlockDoorGeneric(Material.iron, DoorDecl.SILO_HATCH).setBlockName("silo_hatch_ex").setHardness(10.0F).setResistance(30000.0F).setCreativeTab(MainRegistry.machineTab).setBlockTextureName(RefStrings.MODID + ":block_steel");
		silo_hatch_large_ex = new BlockDoorGeneric(Material.iron, DoorDecl.SILO_HATCH_LARGE).setBlockName("silo_hatch_large").setHardness(10.0F).setResistance(50000.0F).setCreativeTab(MainRegistry.machineTab).setBlockTextureName(RefStrings.MODID + ":block_steel");

		ore_manganese = new BlockGeneric(Material.rock).setBlockName("ore_manganese").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":ore_manganese");		
		ore_sodium = new BlockGeneric(Material.rock).setBlockName("ore_sodium").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":ore_sodium");		

	}

	private static void registerBlock() {

		register(machine_dineutronium_battery_ex);
		register(machine_electron_battery);
		register(machine_ex_battery);

		register(rbmk_turbine);
		register(rbmk_large_turbine);
		register(rbmk_chungus);

		register(barrel_antimatter_ex);

		register(ore_manganese);
		register(ore_sodium);

		GameRegistry.registerBlock(silo_hatch_ex, silo_hatch_ex.getUnlocalizedName());
		GameRegistry.registerBlock(silo_hatch_large_ex, silo_hatch_large_ex.getUnlocalizedName());
	}
	
	private static void register(Block b) {
		GameRegistry.registerBlock(b, ItemBlockBase.class, b.getUnlocalizedName());
	}
	
	private static void register(Block b, Class<? extends ItemBlock> clazz) {
		GameRegistry.registerBlock(b, clazz, b.getUnlocalizedName());
	}
	
	public static void addRemap(String unloc, Block block, int meta) {
		Block remap = new BlockRemap(block, meta).setBlockName(unloc);
		register(remap, ItemBlockRemap.class);
	}
}
