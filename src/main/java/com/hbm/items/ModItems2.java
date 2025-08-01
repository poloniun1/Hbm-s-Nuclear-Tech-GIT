package com.hbm.items;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.VersatileConfig;
import com.hbm.handler.BucketHandler;
import com.hbm.handler.ability.IToolAreaAbility;

import com.hbm.interfaces.ICustomWarhead.SaltedFuel.HalfLifeType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTank;
import com.hbm.inventory.material.MaterialShapes;
import com.hbm.inventory.material.Mats;
import com.hbm.items.ItemAmmoEnums.*;
import com.hbm.items.ItemEnums.*;
import com.hbm.items.armor.*;
import com.hbm.items.armor.IArmorDisableModel.EnumPlayerPart;
import com.hbm.items.bomb.*;
import com.hbm.items.food.*;
import com.hbm.items.machine.*;
import com.hbm.items.machine.ItemFELCrystal.EnumWavelengths;
import com.hbm.items.machine.ItemMachineUpgrade.UpgradeType;
import com.hbm.items.machine.ItemPlateFuel.FunctionEnum;
import com.hbm.items.machine.ItemPWRFuel.EnumPWRFuel;
import com.hbm.items.machine.ItemRBMKRod.EnumBurnFunc;
import com.hbm.items.machine.ItemRBMKRod.EnumDepleteFunc;
import com.hbm.items.machine.ItemRTGPelletDepleted.DepletedRTGMaterial;
import com.hbm.items.machine.ItemStamp.StampType;
import com.hbm.items.machine.ItemZirnoxRod.EnumZirnoxType;
import com.hbm.items.special.*;
import com.hbm.items.special.ItemPlasticScrap.ScrapType;
import com.hbm.items.tool.*;
import com.hbm.items.tool.ItemToolAbility.EnumToolType;
import com.hbm.items.weapon.*;
import com.hbm.items.weapon.ItemMissile.*;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.potion.HbmPotion;
import com.hbm.tileentity.machine.rbmk.IRBMKFluxReceiver.NType;

import com.hbm.util.RTGUtil;

import api.hbm.block.IToolable.ToolType;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class ModItems2 {
	
	public static void mainRegistry()
	{
		initializeItem();
		registerItem();
	}

	public static ItemRBMKRod rbmk_fuel_euph;
	public static ItemRBMKPellet rbmk_pellet_euph;

	public static ItemRBMKRod rbmk_fuel_dnt;
	public static ItemRBMKPellet rbmk_pellet_dnt;

	public static ItemRBMKRod rbmk_fuel_sa326;
	public static ItemRBMKPellet rbmk_pellet_sa326;

	public static ItemRBMKRod rbmk_fuel_pobi;
	public static ItemRBMKPellet rbmk_pellet_pobi;

	public static Item powder_astatine_tiny;
	public static Item powder_bromine_tiny;
	public static Item powder_caesium_tiny;
	public static Item powder_iodine_tiny;
	public static Item powder_strontium_tiny;
	public static Item powder_tennessine_tiny;

	public static Item nugget_electronium;
	public static Item nugget_fuller;
	public static Item nugget_u238m2;
	public static Item nugget_star;

	public static Item ingot_manganese;

	public static Item rod_zirnox_pu239_fuel_depleted;
	public static Item rod_zirnox_np237_fuel_depleted;
	public static Item rod_zirnox_sa326_fuel_depleted;
	public static Item rod_zirnox_bred_lead;

	public static Item plate_fuel_atbe;
	public static Item waste_plate_atbe;

	public static Item waste_pu239;
	public static Item waste_np237;
	public static Item waste_sa326;
	public static Item bred_lead;

	public static Item ex_bismuth_pickaxe;
	public static Item osmiridium_pickaxe;
	public static Item missile_carrier;

	public static void initializeItem()
	{	
		rbmk_pellet_euph = (ItemRBMKPellet) new ItemRBMKPellet("euphemium").setUnlocalizedName("rbmk_pellet_euph").setTextureName(RefStrings.MODID+ ":rbmk_pellet_euph");
		rbmk_fuel_euph = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_euph)
				.setYield(1000000D)
				.setStats(1200, 10)
				.setFunction(EnumBurnFunc.QUADRATIC)
				.setHeat(0.1D)
				.setDiffusion(0.5D)
				.setMeltingPoint(100000)
				.setUnlocalizedName("rbmk_fuel_euph").setTextureName(RefStrings.MODID + ":rbmk_fuel_euph");
		
		rbmk_pellet_dnt = (ItemRBMKPellet) new ItemRBMKPellet("dnt").setUnlocalizedName("rbmk_pellet_dnt").setTextureName(RefStrings.MODID+ ":rbmk_pellet_dnt");
		rbmk_fuel_dnt = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_dnt)
				.setYield(1000000D)
				.setStats(1280, 10)
				.setFunction(EnumBurnFunc.QUADRATIC)
				.setHeat(0.1D)
				.setDiffusion(0.5D)
				.setMeltingPoint(100000)
				.setUnlocalizedName("rbmk_fuel_dnt").setTextureName(RefStrings.MODID + ":rbmk_fuel_dnt");

		rbmk_pellet_sa326 = (ItemRBMKPellet) new ItemRBMKPellet("sa326").setUnlocalizedName("rbmk_pellet_sa326").setTextureName(RefStrings.MODID+ ":rbmk_pellet_dnt");
		rbmk_fuel_sa326 = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_sa326)
				.setYield(100000000D)
				.setStats(20000, 1000)
				.setFunction(EnumBurnFunc.LINEAR)
				.setHeat(0.6D)
				.setDiffusion(0.25D)
				.setMeltingPoint(100000)
				.setUnlocalizedName("rbmk_fuel_sa326").setTextureName(RefStrings.MODID + ":rbmk_fuel_dnt");


		rbmk_pellet_pobi = (ItemRBMKPellet) new ItemRBMKPellet("pobi").setUnlocalizedName("rbmk_pellet_pobi").setTextureName(RefStrings.MODID+ ":rbmk_pellet_pobi");
		rbmk_fuel_pobi = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_pobi)
				.setYield(126000000D)
				.setStats(0,10500)
				.setFunction(EnumBurnFunc.CONSTANT)
				.setDepletionFunction(EnumDepleteFunc.LINEAR)
				.setXenon(0.0D, 50D)
				.setHeat(0.1D)
				.setDiffusion(0.5D)
				.setMeltingPoint(100000)
				.setNeutronTypes(NType.SLOW, NType.SLOW) //Beryllium Moderation
				.setUnlocalizedName("rbmk_fuel_pobi").setTextureName(RefStrings.MODID + ":rbmk_fuel_pobi");


		ingot_manganese = new Item().setUnlocalizedName("ingot_manganese").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":ingot_manganese");
		

		nugget_electronium = new Item().setUnlocalizedName("nugget_electronium").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":nugget_electronium");
		nugget_fuller= new Item().setUnlocalizedName("nugget_fuller").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":nugget_fuller");
		nugget_u238m2 = new Item().setUnlocalizedName("nugget_u238m2").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":nugget_u238m2");
		nugget_star = new Item().setUnlocalizedName("nugget_star").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":nugget_star");

		waste_pu239 = new ItemDepletedFuel().setUnlocalizedName("waste_pu239").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":waste_plutonium");
		waste_np237 = new ItemDepletedFuel().setUnlocalizedName("waste_np237").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":waste_uranium");
		waste_sa326 = new ItemDepletedFuel().setUnlocalizedName("waste_sa326").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":waste_schrabidium");
		bred_lead = new Item().setUnlocalizedName("bred_lead").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":waste_thorium");


		powder_astatine_tiny = new Item().setUnlocalizedName("powder_astatine_tiny").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":powder_astatine_tiny");
		powder_bromine_tiny= new Item().setUnlocalizedName("powder_bromine_tiny").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":powder_bromine_tiny");
		powder_caesium_tiny = new Item().setUnlocalizedName("powder_caesium_tiny").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":powder_caesium_tiny");
		powder_iodine_tiny= new Item().setUnlocalizedName("powder_iodine_tiny").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":powder_iodine_tiny");
		powder_strontium_tiny = new Item().setUnlocalizedName("powder_strontium_tiny").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":powder_strontium_tiny");
		powder_tennessine_tiny= new Item().setUnlocalizedName("powder_tennessine_tiny").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":powder_tennessine_tiny");

		rod_zirnox_pu239_fuel_depleted = new Item().setUnlocalizedName("rod_zirnox_pu239_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_zirnox_empty).setTextureName(RefStrings.MODID + ":rod_zirnox_pu239_fuel_depleted");
		rod_zirnox_np237_fuel_depleted = new Item().setUnlocalizedName("rod_zirnox_np237_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_zirnox_empty).setTextureName(RefStrings.MODID + ":rod_zirnox_np237_fuel_depleted");
		rod_zirnox_sa326_fuel_depleted = new Item().setUnlocalizedName("rod_zirnox_sa326_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_zirnox_empty).setTextureName(RefStrings.MODID + ":rod_zirnox_sa326_fuel_depleted");
		rod_zirnox_bred_lead = new Item().setUnlocalizedName("rod_zirnox_bred_lead").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_zirnox_empty).setTextureName(RefStrings.MODID + ":rod_zirnox_thorium_fuel_depleted");


		plate_fuel_atbe = new ItemPlateFuel(2400000).setFunction(FunctionEnum.PASSIVE, 1000).setUnlocalizedName("plate_fuel_atbe").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setTextureName(RefStrings.MODID + ":plate_fuel_atbe");
		waste_plate_atbe = new ItemDepletedFuel().setUnlocalizedName("waste_plate_atbe").setCreativeTab(MainRegistry.partsTab).setTextureName(RefStrings.MODID + ":waste_plate_atbe");

		ToolMaterial matExBismuth = EnumHelper.addToolMaterial("HBM_EXBISMUTH", 4, 0, 50F, 0.0F, 200).setRepairItem(new ItemStack(ModItems.ingot_bismuth,2));
		ex_bismuth_pickaxe = new ItemToolAbility(35F, 0, matExBismuth, EnumToolType.MINER)
				.addAbility(IToolAreaAbility.WORLD, 3)
				.setDepthRockBreaker().setUnlocalizedName("ex_bismuth_pickaxe").setTextureName(RefStrings.MODID + ":bismuth_pickaxe");
		ToolMaterial matOSMIRIDIUM = EnumHelper.addToolMaterial("HBM_OSMIRIDIUM", 4, 0, 200F, 0.0F, 200).setRepairItem(new ItemStack(ModItems.ingot_osmiridium));
		osmiridium_pickaxe = new ItemToolAbility(35F, 0, matOSMIRIDIUM, EnumToolType.MINER)
				.addAbility(IToolAreaAbility.GOD, 3)
				.setDepthRockBreaker().setUnlocalizedName("osmiridium_pickaxe").setTextureName(RefStrings.MODID + ":mese_pickaxe");

		missile_carrier = new Item().setUnlocalizedName("missile_carrier").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab).setTextureName(RefStrings.MODID + ":missile_carrier");		
	}
	
	private static void registerItem() {

		GameRegistry.registerItem(rbmk_fuel_dnt, rbmk_fuel_dnt.getUnlocalizedName());
		GameRegistry.registerItem(rbmk_pellet_dnt, rbmk_pellet_dnt.getUnlocalizedName());
		GameRegistry.registerItem(rbmk_fuel_sa326, rbmk_fuel_sa326.getUnlocalizedName());
		GameRegistry.registerItem(rbmk_pellet_sa326, rbmk_pellet_sa326.getUnlocalizedName());
		GameRegistry.registerItem(rbmk_fuel_euph, rbmk_fuel_euph.getUnlocalizedName());
		GameRegistry.registerItem(rbmk_pellet_euph, rbmk_pellet_euph.getUnlocalizedName());
		GameRegistry.registerItem(rbmk_fuel_pobi, rbmk_fuel_pobi.getUnlocalizedName());
		GameRegistry.registerItem(rbmk_pellet_pobi, rbmk_pellet_pobi.getUnlocalizedName());

		GameRegistry.registerItem(waste_plate_atbe, waste_plate_atbe.getUnlocalizedName());
		GameRegistry.registerItem(plate_fuel_atbe, plate_fuel_atbe.getUnlocalizedName());

		GameRegistry.registerItem(powder_astatine_tiny, powder_astatine_tiny.getUnlocalizedName());
		GameRegistry.registerItem(powder_bromine_tiny, powder_bromine_tiny.getUnlocalizedName());
		GameRegistry.registerItem(powder_caesium_tiny, powder_caesium_tiny.getUnlocalizedName());
		GameRegistry.registerItem(powder_iodine_tiny, powder_iodine_tiny.getUnlocalizedName());
		GameRegistry.registerItem(powder_strontium_tiny, powder_strontium_tiny.getUnlocalizedName());
		GameRegistry.registerItem(powder_tennessine_tiny, powder_tennessine_tiny.getUnlocalizedName());

		GameRegistry.registerItem(nugget_electronium, nugget_electronium.getUnlocalizedName());
		GameRegistry.registerItem(nugget_fuller, nugget_fuller.getUnlocalizedName());
		GameRegistry.registerItem(nugget_u238m2, nugget_u238m2.getUnlocalizedName());
		GameRegistry.registerItem(nugget_star, nugget_star.getUnlocalizedName());

		GameRegistry.registerItem(rod_zirnox_pu239_fuel_depleted, rod_zirnox_pu239_fuel_depleted.getUnlocalizedName());
		GameRegistry.registerItem(rod_zirnox_np237_fuel_depleted, rod_zirnox_np237_fuel_depleted.getUnlocalizedName());
		GameRegistry.registerItem(rod_zirnox_sa326_fuel_depleted, rod_zirnox_sa326_fuel_depleted.getUnlocalizedName());
		GameRegistry.registerItem(rod_zirnox_bred_lead, rod_zirnox_bred_lead.getUnlocalizedName());

		GameRegistry.registerItem(waste_pu239, waste_pu239.getUnlocalizedName());
		GameRegistry.registerItem(waste_np237, waste_np237.getUnlocalizedName());
		GameRegistry.registerItem(waste_sa326, waste_sa326.getUnlocalizedName());
		GameRegistry.registerItem(bred_lead, bred_lead.getUnlocalizedName());

		GameRegistry.registerItem(ingot_manganese, ingot_manganese.getUnlocalizedName());

		GameRegistry.registerItem(ex_bismuth_pickaxe, ex_bismuth_pickaxe.getUnlocalizedName());
		GameRegistry.registerItem(osmiridium_pickaxe, osmiridium_pickaxe.getUnlocalizedName());
		GameRegistry.registerItem(missile_carrier, missile_carrier.getUnlocalizedName());
	}
	
	public static void addRemap(String unloc, Item item, Enum sub) {
		addRemap(unloc, item, sub.ordinal());
	}

	public static void addRemap(String unloc, Item item, int meta) {
		Item remap = new ItemRemap(item, meta).setUnlocalizedName(unloc).setTextureName(RefStrings.MODID + ":plate_armor_titanium");
		GameRegistry.registerItem(remap, remap.getUnlocalizedName());
	}
}
