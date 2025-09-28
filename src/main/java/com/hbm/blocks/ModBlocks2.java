package com.hbm.blocks;

import com.hbm.blocks.BlockEnums.*;
import com.hbm.blocks.bomb.*;
import com.hbm.blocks.fluid.*;
import com.hbm.blocks.gas.*;
import com.hbm.blocks.generic.*;
import com.hbm.blocks.generic.BlockHazard.ExtDisplayEffect;
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
import net.minecraft.block.material.*;
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
	public static Block ore_meteor_star;
	public static Block ore_vault;

	public static Block block_alexandrite;
	public static Block block_arsenic;
	public static Block block_au198;
	public static Block block_volcanic;
	public static Block block_osmiridium;
	public static Block block_pb209;
	public static Block block_technetium;
	public static Block block_silicon;
	public static Block block_strontium;
	public static Block block_neodymium;
	public static Block block_calcium;
	public static Block block_sodium;
	public static Block block_gh336;

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
		silo_hatch_large_ex = new BlockDoorGeneric(Material.iron, DoorDecl.SILO_HATCH_LARGE).setBlockName("silo_hatch_large_ex").setHardness(10.0F).setResistance(50000.0F).setCreativeTab(MainRegistry.machineTab).setBlockTextureName(RefStrings.MODID + ":block_steel");

		ore_manganese = new BlockGeneric(Material.rock).setBlockName("ore_manganese").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":ore_manganese");		
		ore_sodium = new BlockGeneric(Material.rock).setBlockName("ore_sodium").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":ore_sodium");		
		ore_meteor_star = new BlockGeneric(Material.rock).setBlockName("ore_meteor_star").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":ore_meteor_star");		
		ore_vault = new BlockGeneric(Material.rock).setBlockName("ore_vault").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":ore_vault");		

		block_alexandrite = new BlockGeneric(Material.rock).setBlockName("block_alexandrite").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_alexandrite");		
		block_arsenic = new BlockGeneric(Material.rock).setBlockName("block_arsenic").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_arsenic");		
		block_au198 = new BlockGeneric(Material.rock).setBlockName("block_au198").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_au198");		
		block_volcanic = new BlockGeneric(Material.rock).setBlockName("block_volcanic").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_volcanic");		
		block_osmiridium = new BlockGeneric(Material.rock).setBlockName("block_osmiridium").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_osmiridium");		
		block_pb209 = new BlockGeneric(Material.rock).setBlockName("block_pb209").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_pb209");		
		block_technetium = new BlockGeneric(Material.rock).setBlockName("block_technetium").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_technetium");		
		block_silicon = new BlockGeneric(Material.rock).setBlockName("block_silicon").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_silicon");		
		block_strontium = new BlockGeneric(Material.rock).setBlockName("block_strontium").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_strontium");		
		block_neodymium = new BlockGeneric(Material.rock).setBlockName("block_neodymium").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_neodymium");		
		block_calcium = new BlockGeneric(Material.rock).setBlockName("block_calcium").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_calcium");		
		block_sodium = new BlockGeneric(Material.rock).setBlockName("block_sodium").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_sodium");		
		block_gh336 = new BlockGeneric(Material.rock).setBlockName("block_gh336").setCreativeTab(null).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab).setBlockTextureName(RefStrings.MODID + ":block_gh336");		

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
		register(ore_meteor_star);
		register(ore_vault);

		register(block_alexandrite);
		register(block_arsenic);
		register(block_au198);
		register(block_volcanic);
		register(block_osmiridium);
		register(block_pb209);
		register(block_technetium);
		register(block_silicon);
		register(block_strontium);
		register(block_neodymium);
		register(block_calcium);
		register(block_sodium);
		register(block_gh336);

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
