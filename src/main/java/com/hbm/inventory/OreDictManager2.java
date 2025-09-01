package com.hbm.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//i love you
import com.hbm.items.ModItems2;
import com.hbm.blocks.ModBlocks2;
import static com.hbm.inventory.OreDictManager.DictFrame;
import com.hbm.inventory.material.MaterialShapes.*;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.BlockEnums.EnumStoneType;
import com.hbm.blocks.BlockEnums.EnumMeteorType;
import com.hbm.config.GeneralConfig;
import com.hbm.hazard.HazardData;
import com.hbm.hazard.HazardEntry;
import com.hbm.hazard.HazardRegistry;
import com.hbm.hazard.HazardSystem;
import com.hbm.inventory.material.MaterialShapes;
import com.hbm.inventory.material.Mats;
import com.hbm.inventory.material.NTMMaterial;
import com.hbm.inventory.material.NTMMaterial.SmeltingBehavior;
import com.hbm.items.ModItems;
import com.hbm.items.ItemEnums.EnumAshType;
import com.hbm.items.ItemEnums.EnumBriquetteType;
import com.hbm.items.ItemEnums.EnumCokeType;
import com.hbm.items.ItemEnums.EnumTarType;

import com.hbm.main.MainRegistry;
import com.hbm.util.Compat;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

//the more i optimize this, the more it starts looking like gregtech
public class OreDictManager2 {
	private static final HashMap<String, HashSet<String>> reRegistration = new HashMap();

	public static final DictFrame MANGANESE = new DictFrame("Manganese");

	public static final DictFrame COAL = new DictFrame("Coal");
	public static final DictFrame IRON = new DictFrame("Iron");
	public static final DictFrame GOLD = new DictFrame("Gold");
	public static final DictFrame LAPIS = new DictFrame("Lapis");
	public static final DictFrame REDSTONE = new DictFrame("Redstone");
	public static final DictFrame GLOWSTONE = new DictFrame("Glowstone");
	public static final DictFrame NETHERQUARTZ = new DictFrame("NetherQuartz");
	public static final DictFrame DIAMOND = new DictFrame("Diamond");
	public static final DictFrame EMERALD = new DictFrame("Emerald");


	public static final DictFrame TI = new DictFrame("Titanium");
	public static final DictFrame CU = new DictFrame("Copper");
	public static final DictFrame CD = new DictFrame("Cadmium");

	public static final DictFrame AL = new DictFrame("Aluminum");
	//public static final DictFrame BI = new DictFrame("Bismuth");
	public static final DictFrame AS = new DictFrame("Arsenic");
	public static final DictFrame PAS = new DictFrame("PreArsenic");
	public static final DictFrame CA = new DictFrame("Calcium");
	public static final DictFrame B = new DictFrame("Boron");
	public static final DictFrame SI = new DictFrame("Silicon");
	public static final DictFrame STAR = new DictFrame("Starmetal");
	public static final DictFrame DESH = new DictFrame("WorkersAlloy");
	public static final DictFrame EUPH = new DictFrame("Euphemium");
	public static final DictFrame ASBESTOS = new DictFrame("Asbestos");
	public static final DictFrame OSMIRIDIUM = new DictFrame("Osmiridium");

	public static final DictFrame TC99 = new DictFrame("Technetium99", "Tc99");
	public static final DictFrame AU198 = new DictFrame("Gold198", "Au198");
	public static final DictFrame PB209 = new DictFrame("Lead209", "Pb209");
	public static final DictFrame GH336 = new DictFrame("Ghiorsium336", "Gh336");

	public static final DictFrame CINNABAR = new DictFrame("Cinnabar");
	public static final DictFrame HG = new DictFrame("Mercury");

	public static final DictFrame S = new DictFrame("Sulfur");
	public static final DictFrame KNO = new DictFrame("Saltpeter");
	public static final DictFrame F = new DictFrame("Fluorite");
	public static final DictFrame BAUXITE = new DictFrame("Bauxite");
	public static final DictFrame ALEXANDRITE = new DictFrame("Alexandrite");
	public static final DictFrame VOLCANIC = new DictFrame("Volcanic");

	public static final DictFrame TIKITE = new DictFrame("Tikite");
	public static final DictFrame ND = new DictFrame("Neodymium");
	public static final DictFrame P_WHITE = new DictFrame("WhitePhosphorus");
	public static final DictFrame NA = new DictFrame("Sodium");

	public static final DictFrame I = new DictFrame("Iodine");
	public static final DictFrame AT = new DictFrame("Astatine");
	public static final DictFrame CS = new DictFrame("Caesium");
	public static final DictFrame ST = new DictFrame("Strontium");
	public static final DictFrame BR = new DictFrame("Bromine");
	public static final DictFrame TS = new DictFrame("Tennessine") ;
	
	public static void registerOres() {

		MANGANESE	.ore(ModBlocks2.ore_manganese)		.ingot(ModItems2.ingot_manganese);
		COAL	.ingot(Items.coal);
		IRON	.ore(ModBlocks.cluster_iron, ModBlocks.cluster_depth_iron);
		LAPIS	.ingot(ModItems.powder_lapis);
		NETHERQUARTZ	.ingot(Items.quartz);
		DIAMOND	.ingot(ModItems.powder_diamond);
		EMERALD	.ingot(ModItems.powder_emerald);	
		GLOWSTONE	.ingot(Items.glowstone_dust)	.ore(Blocks.glowstone);

		TI	.ore(ModBlocks.cluster_titanium,ModBlocks.cluster_depth_titanium);
		CU	.ore(ModBlocks.cluster_copper);
		CD	.ore(ModBlocks.dirt_oily, ModBlocks.dirt_dead);

		AL	.ore(ModBlocks.cluster_aluminium);
		AS	.ore(ModBlocks.stone_porous, ModBlocks.stone_cracked, ModBlocks.sand_dirty, ModBlocks.sand_dirty_red )	.block(ModBlocks2.block_arsenic);
		PAS	.ingot(ModItems.scrap_oil);
		CA	.ore(DictFrame.fromOne(ModBlocks.stone_resource, EnumStoneType.LIMESTONE) )	.block(ModBlocks2.block_calcium);
		B	.ore(ModBlocks.ore_depth_borax );
		SI	.ore(Blocks.quartz_block)	.block(ModBlocks2.block_silicon);
		STAR		.nugget(ModItems2.nugget_star)		.ore(ModBlocks2.ore_meteor_star);
		EUPH	.ore(ModBlocks.block_euphemium_cluster);
		ASBESTOS	.asbestos(1F)	.ore(DictFrame.fromOne(ModBlocks.stone_resource, EnumStoneType.ASBESTOS));
		OSMIRIDIUM	.ore(ModBlocks.ore_tektite_osmiridium)	.block(ModBlocks2.block_osmiridium);
        		REDSTONE	.ingot(Items.redstone);
		DESH	.ore(ModBlocks.ore_rare, ModBlocks.ore_gneiss_rare);

		TC99	.block(ModBlocks2.block_technetium);
		AU198	.block(ModBlocks2.block_au198);
		PB209	.block(ModBlocks2.block_pb209);
		GH336	.block(ModBlocks2.block_gh336);

		S	.ingot(ModItems.sulfur)	.ore(DictFrame.fromOne(ModBlocks.stone_resource, EnumStoneType.SULFUR));
		KNO	.ingot(ModItems.niter);
		F	.ingot(ModItems.fluorite);
		CINNABAR	.ingot(ModItems.cinnebar);
		HG	.ingot(ModItems.ingot_mercury)	.nugget(ModItems.nugget_mercury)	.block(ModItems.bottle_mercury);
		BAUXITE	.ore(DictFrame.fromOne(ModBlocks.stone_resource, EnumStoneType.BAUXITE));
		ALEXANDRITE	.ingot(ModItems.gem_alexandrite )	.gem(ModItems.gem_alexandrite )	.ore(ModBlocks.ore_alexandrite )	.block(ModBlocks2.block_alexandrite );
		VOLCANIC						.ingot(ModItems.gem_volcanic)	.block(ModBlocks2.block_volcanic);													


		NA	.hydro(1F)		.ore(ModBlocks2.ore_sodium)	.block(ModBlocks2.block_sodium);											
		P_WHITE	.hot(5)	.ore(ModBlocks.ore_nether_fire);
		ND	.ingot(ModItems.powder_neodymium)	.block(ModBlocks2.block_neodymium);
		TIKITE	.ingot(ModItems.powder_spark_mix )	.dust(ModItems.powder_nitan_mix )	.ore(ModBlocks.ore_tikite );

		I	.dustSmall(ModItems2.powder_iodine_tiny);
		AT	.dustSmall(ModItems2.powder_astatine_tiny);
		CS	.dustSmall(ModItems2.powder_caesium_tiny);
		ST	.dustSmall(ModItems2.powder_strontium_tiny)	.block(ModBlocks2.block_strontium);
		BR	.dustSmall(ModItems2.powder_bromine_tiny);
		TS	.dustSmall(ModItems2.powder_tennessine_tiny);
	}

	

}
