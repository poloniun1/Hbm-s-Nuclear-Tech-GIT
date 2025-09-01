package com.hbm.inventory.material;

import static com.hbm.inventory.material.Mats.*;
import static com.hbm.inventory.material.MaterialShapes.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.hbm.blocks.BlockEnums.EnumStoneType;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.ModBlocks2;
import com.hbm.inventory.OreDictManager;
import com.hbm.inventory.OreDictManager2;
import com.hbm.inventory.OreDictManager.DictFrame;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;
import com.hbm.inventory.material.Mats.MaterialStack;
import com.hbm.inventory.recipes.loader.SerializableRecipe;
import com.hbm.items.ItemEnums;
import com.hbm.items.ModItems;
import com.hbm.items.ItemEnums.EnumAshType;
import com.hbm.items.ItemEnums.EnumCasingType;
import com.hbm.util.Compat;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MatDistribution extends SerializableRecipe {

	@Override
	public void registerDefaults() {
		//vanilla crap
		registerOre("stone",MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(2));
		registerOre("cobblestone",MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(2));
		registerOre("sand",MAT_SILICON, QUANTUM.q(6), MAT_SLAG, QUART.q(2));
		registerEntry(Blocks.gravel,MAT_SILICON, NUGGET.q(3), MAT_SLAG, QUART.q(2));
		registerEntry(Blocks.obsidian, MAT_OBSIDIAN, BLOCK.q(1));
		registerEntry(Blocks.rail, MAT_IRON, INGOT.q(6, 16));
		registerEntry(Blocks.golden_rail, MAT_GOLD, INGOT.q(6, 6), MAT_REDSTONE, DUST.q(1, 6));
		registerEntry(Blocks.detector_rail, MAT_IRON, INGOT.q(6, 6), MAT_REDSTONE, DUST.q(1, 6));
		registerEntry(Items.minecart, MAT_IRON, INGOT.q(5));
		registerEntry(Items.flint, MAT_SILICON, NUGGET.q(4));

		//castables
		registerEntry(ModItems.blade_titanium,				MAT_TITANIUM,		INGOT.q(2));
		registerEntry(ModItems.blade_tungsten,				MAT_TUNGSTEN,		INGOT.q(2));
		registerEntry(ModItems.blades_steel,				MAT_STEEL,			INGOT.q(4));
		registerEntry(ModItems.blades_titanium,				MAT_TITANIUM, 		INGOT.q(4));
		registerEntry(ModItems.blades_advanced_alloy,		MAT_ALLOY,			INGOT.q(4));
		registerEntry(ModItems.stamp_stone_flat,			MAT_STONE,			INGOT.q(3));
		registerEntry(ModItems.stamp_iron_flat,				MAT_IRON,			INGOT.q(3));
		registerEntry(ModItems.stamp_steel_flat,			MAT_STEEL,			INGOT.q(3));
		registerEntry(ModItems.stamp_titanium_flat,			MAT_TITANIUM,		INGOT.q(3));
		registerEntry(ModItems.stamp_obsidian_flat,			MAT_OBSIDIAN,		INGOT.q(3));
		registerEntry(ModItems.pipes_steel,					MAT_STEEL,			BLOCK.q(3));

		registerEntry(DictFrame.fromOne(ModItems.casing, EnumCasingType.SMALL),			MAT_GUNMETAL,		PLATE.q(1, 4));
		registerEntry(DictFrame.fromOne(ModItems.casing, EnumCasingType.SMALL_STEEL),	MAT_WEAPONSTEEL,	PLATE.q(1, 4));
		registerEntry(DictFrame.fromOne(ModItems.casing, EnumCasingType.LARGE),			MAT_GUNMETAL,		PLATE.q(1, 2));
		registerEntry(DictFrame.fromOne(ModItems.casing, EnumCasingType.LARGE_STEEL),	MAT_WEAPONSTEEL,	PLATE.q(1, 2));
		registerEntry(Items.minecart, MAT_IRON, INGOT.q(5));
		registerEntry(DictFrame.fromOne(ModItems.chunk_ore, ItemEnums.EnumChunkType.CRYOLITE), MAT_ALUMINIUM, INGOT.q(1), MAT_SODIUM, INGOT.q(1));
		//actual ores
		if(!Compat.isModLoaded(Compat.MOD_GT6)) {
			registerOre(OreDictManager.IRON.ore(), MAT_IRON, INGOT.q(2), MAT_TITANIUM, NUGGET.q(3),MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(1));
			registerOre(OreDictManager.TI.ore(), MAT_TITANIUM, INGOT.q(2), MAT_IRON, NUGGET.q(3),MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(1));
			registerOre(OreDictManager.W.ore(), MAT_TUNGSTEN, INGOT.q(2), MAT_SILICON, QUANTUM.q(4),MAT_SLAG, QUART.q(1));
			registerOre(OreDictManager.AL.ore(), MAT_ALUMINIUM, INGOT.q(2), MAT_SILICON, QUANTUM.q(4),MAT_SLAG, QUART.q(1));
		}
		
		registerOre(OreDictManager.COAL.ore(), MAT_CARBON, INGOT.q(3), MAT_SLAG, QUART.q(2));
		registerOre(OreDictManager.GOLD.ore(), MAT_GOLD, INGOT.q(2), MAT_LEAD, NUGGET.q(3),MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.U.ore(), MAT_URANIUM, INGOT.q(2), MAT_LEAD, NUGGET.q(3),MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(1));
		for(String ore : OreDictManager.TH232.all(MaterialShapes.ONLY_ORE))		registerOre(OreDictManager.TH232.ore(), MAT_THORIUM, INGOT.q(2), MAT_URANIUM, NUGGET.q(3),MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.CU.ore(), MAT_COPPER, INGOT.q(2),MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.PB.ore(), MAT_LEAD, INGOT.q(2), MAT_GOLD, NUGGET.q(1),MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.BE.ore(), MAT_BERYLLIUM, INGOT.q(2),MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.CO.ore(), MAT_COBALT, INGOT.q(1),MAT_SILICON, QUANTUM.q(4), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.REDSTONE.ore(), MAT_REDSTONE, INGOT.q(4),MAT_SILICON, NUGGET.q(1), MAT_SLAG, QUART.q(1));

		registerOre(OreDictManager.HEMATITE.ore(), MAT_HEMATITE, INGOT.q(1));
		registerOre(OreDictManager.MALACHITE.ore(), MAT_MALACHITE, INGOT.q(6));

		registerOre(OreDictManager.LI.ore(), MAT_LITHIUM, INGOT.q(2), MAT_BERYLLIUM, NUGGET.q(6), MAT_SLAG, QUART.q(1));
		registerOre("oreRareEarth", MAT_DESH, NUGGET.q(6), MAT_ZIRCONIUM, NUGGET.q(2), MAT_NEODYMIUM, NUGGET.q(1), MAT_NIOBIUM, NUGGET.q(3), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.OSMIRIDIUM.ore(), MAT_OSMIRIDIUM, QUANTUM.q(6),MAT_SILICON,NUGGET.q(1), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.BORAX.ore(), MAT_BORON, NUGGET.q(4),MAT_SILICON,QUANTUM.q(4), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.SA326.ore(), MAT_SCHRABIDIUM, INGOT.q(2), MAT_URANIUM, NUGGET.q(6), MAT_SILICON,QUANTUM.q(4), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.AUSTRALIUM.ore(), MAT_AUSTRALIUM, INGOT.q(2), MAT_TANTALIUM, NUGGET.q(6), MAT_SILICON,QUANTUM.q(4), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.QUARTZ.ore(), MAT_SILICON, NUGGET.q(6), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.COLTAN.ore(), MAT_TANTALIUM, NUGGET.q(6), MAT_NIOBIUM, INGOT.q(3,2), MAT_SILICON, QUANTUM.q(6), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.BI.ore(), MAT_BISMUTH, INGOT.q(1), MAT_SILICON, QUANTUM.q(6), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.ZR.ore(), MAT_ZIRCONIUM, INGOT.q(2), MAT_NIOBIUM, NUGGET.q(3), MAT_NEODYMIUM, NUGGET.q(6), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.ND.ore(), MAT_NEODYMIUM, INGOT.q(2), MAT_NIOBIUM, NUGGET.q(3), MAT_ZIRCONIUM, NUGGET.q(6), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager.AS.ore(), MAT_PREARSENIC, QUANTUM.q(3), MAT_SILICON, QUANTUM.q(6), MAT_WHITE_P, NUGGET.q(1), MAT_SLAG, QUART.q(1));
		registerOre(OreDictManager2.PAS.ingot(), MAT_PREARSENIC, QUANTUM.q(4));
		registerOre(OreDictManager.SLAG.block(), MAT_PHOSPHORUS,DUST.q(1), MAT_CALCIUM, INGOT.q(1), MAT_IRON, INGOT.q(1),MAT_WHITE_P, NUGGET.q(1));
		registerOre(OreDictManager.CD.ore(), MAT_CADMIUM, NUGGET.q(6), MAT_SILICON, QUANTUM.q(6), MAT_WHITE_P, NUGGET.q(1), MAT_SLAG, QUART.q(1));
	
		registerEntry(DictFrame.fromOne(ModBlocks.stone_resource, EnumStoneType.LIMESTONE), MAT_FLUX, DUST.q(10));
		registerEntry(ModItems.powder_flux, MAT_FLUX, DUST.q(1));
		registerEntry(ModItems.pile_rod_pu239, MAT_U235, BILLET.q(1), MAT_PU239, BILLET.q(1), MAT_URANIUM, BILLET.q(1), MAT_IRON, INGOT.q(2));
		registerEntry(ModItems.pile_rod_plutonium, MAT_PU239, BILLET.q(1), MAT_PLUTONIUM, BILLET.q(2), MAT_IRON, INGOT.q(2));

		registerEntry(new ItemStack(ModItems.plant_item, 1, 2), MAT_CADMIUM, QUANTUM.q(6));
		registerEntry(new ItemStack(Items.coal, 1, 1), MAT_CARBON, NUGGET.q(3));

		registerEntry(ModItems.bedrock_ore_base, MAT_IRON, BLOCK.q(10), MAT_TITANIUM, BLOCK.q(6), MAT_ALUMINIUM, BLOCK.q(6), 
MAT_LITHIUM, BLOCK.q(3), MAT_COBALT, BLOCK.q(3), MAT_BERYLLIUM, BLOCK.q(5), MAT_CALCIUM, BLOCK.q(7), MAT_SODIUM, BLOCK.q(5,2), 
MAT_TUNGSTEN, BLOCK.q(6), MAT_LEAD, BLOCK.q(6), MAT_COPPER, BLOCK.q(6), MAT_GOLD, BLOCK.q(3), MAT_CADMIUM, BLOCK.q(3), MAT_MERCURY, BLOCK.q(5,2), 
MAT_AUSTRALIUM, BLOCK.q(3,2), MAT_STAR, BLOCK.q(3,2), MAT_TANTALIUM, BLOCK.q(3,2), MAT_BISMUTH, BLOCK.q(3,2), MAT_OSMIRIDIUM, BLOCK.q(1,4), 
MAT_DESH, BLOCK.q(12), MAT_NIOBIUM, BLOCK.q(6), MAT_ZIRCONIUM, BLOCK.q(3), MAT_STRONTIUM, BLOCK.q(3), MAT_NEODYMIUM, BLOCK.q(3,2), 
MAT_URANIUM, BLOCK.q(6), MAT_THORIUM, BLOCK.q(6), MAT_PLUTONIUM, BLOCK.q(2), MAT_RADIUM, BLOCK.q(3), MAT_POLONIUM, BLOCK.q(2), 
MAT_TECHNETIUM, BLOCK.q(2), MAT_SCHRABIDIUM, BLOCK.q(3,2), MAT_GHIORSIUM, BLOCK.q(1), MAT_EUPH, BLOCK.q(3,2), MAT_DNT, BLOCK.q(1,2), 
MAT_CARBON, BLOCK.q(16), MAT_KNO, BLOCK.q(12), MAT_SULFUR, BLOCK.q(12), MAT_ASBESTOS, BLOCK.q(3), 
MAT_PHOSPHORUS, BLOCK.q(8), MAT_BORON, BLOCK.q(3,2), MAT_ARSENIC, BLOCK.q(3,2), 
MAT_REDSTONE, BLOCK.q(12), MAT_FLUORITE, BLOCK.q(12), MAT_SILICON, BLOCK.q(27), MAT_WHITE_P, BLOCK.q(2));
		registerEntry(ModBlocks2.ore_vault, MAT_IRON, NUGGET.q(10), MAT_TITANIUM, NUGGET.q(6), MAT_ALUMINIUM, NUGGET.q(6), 
MAT_LITHIUM, NUGGET.q(3), MAT_COBALT, NUGGET.q(3), MAT_BERYLLIUM, NUGGET.q(5), MAT_CALCIUM, NUGGET.q(7), MAT_SODIUM, NUGGET.q(5,2), 
MAT_TUNGSTEN, NUGGET.q(6), MAT_LEAD, NUGGET.q(6), MAT_COPPER, NUGGET.q(6), MAT_GOLD, NUGGET.q(3), MAT_CADMIUM, NUGGET.q(3), MAT_MERCURY, NUGGET.q(5,2), 
MAT_AUSTRALIUM, NUGGET.q(3,2), MAT_STAR, NUGGET.q(3,2), MAT_TANTALIUM, NUGGET.q(3,2), MAT_BISMUTH, NUGGET.q(3,2), MAT_OSMIRIDIUM, NUGGET.q(1,4), 
MAT_DESH, NUGGET.q(12), MAT_NIOBIUM, NUGGET.q(6), MAT_ZIRCONIUM, NUGGET.q(3), MAT_STRONTIUM, NUGGET.q(3), MAT_NEODYMIUM, NUGGET.q(3,2), 
MAT_URANIUM, NUGGET.q(6), MAT_THORIUM, NUGGET.q(6), MAT_PLUTONIUM, NUGGET.q(2), MAT_RADIUM, NUGGET.q(3), MAT_POLONIUM, NUGGET.q(2), 
MAT_TECHNETIUM, NUGGET.q(2), MAT_SCHRABIDIUM, NUGGET.q(3,2), MAT_GHIORSIUM, NUGGET.q(1), MAT_EUPH, NUGGET.q(3,2), MAT_DNT, NUGGET.q(1,2), 
MAT_CARBON, NUGGET.q(16), MAT_KNO, NUGGET.q(12), MAT_SULFUR, NUGGET.q(12), MAT_ASBESTOS, NUGGET.q(3), 
MAT_PHOSPHORUS, NUGGET.q(8), MAT_BORON, NUGGET.q(3,2), MAT_ARSENIC, NUGGET.q(3,2), 
MAT_REDSTONE, NUGGET.q(12), MAT_FLUORITE, NUGGET.q(12), MAT_SILICON, NUGGET.q(27), MAT_WHITE_P, NUGGET.q(2));
		registerEntry(DictFrame.fromOne(ModItems.powder_ash, EnumAshType.WOOD), MAT_CARBON, NUGGET.q(1));
		registerEntry(DictFrame.fromOne(ModItems.powder_ash, EnumAshType.COAL), MAT_CARBON, NUGGET.q(2));
		registerEntry(DictFrame.fromOne(ModItems.powder_ash, EnumAshType.MISC), MAT_CARBON, NUGGET.q(1));
	}

	public static void registerEntry(Object key, Object... matDef) {
		ComparableStack comp = null;

		if(key instanceof Item) comp = new ComparableStack((Item) key);
		if(key instanceof Block) comp = new ComparableStack((Block) key);
		if(key instanceof ItemStack) comp = new ComparableStack((ItemStack) key);
		if(key instanceof ComparableStack) comp = (ComparableStack) key;

		if(comp == null) return;
		if(matDef.length % 2 == 1) return;

		List<MaterialStack> stacks = new ArrayList();

		for(int i = 0; i < matDef.length; i += 2) {
			stacks.add(new MaterialStack((NTMMaterial) matDef[i], (int) matDef[i + 1]));
		}

		if(stacks.isEmpty()) return;

		materialEntries.put(comp, stacks);
	}

	public static void registerOre(String key, Object... matDef) {
		if(matDef.length % 2 == 1) return;

		List<MaterialStack> stacks = new ArrayList();

		for(int i = 0; i < matDef.length; i += 2) {
			stacks.add(new MaterialStack((NTMMaterial) matDef[i], (int) matDef[i + 1]));
		}

		if(stacks.isEmpty()) return;

		materialOreEntries.put(key, stacks);
	}

	@Override
	public String getFileName() {
		return "hbmCrucibleSmelting.json";
	}

	@Override
	public Object getRecipeObject() {
		List entries = new ArrayList();
		entries.addAll(Mats.materialEntries.entrySet());
		entries.addAll(Mats.materialOreEntries.entrySet());
		return entries;
	}

	@Override
	public void readRecipe(JsonElement recipe) {
		JsonObject obj = (JsonObject) recipe;
		AStack input = this.readAStack(obj.get("input").getAsJsonArray());
		List<MaterialStack> materials = new ArrayList();
		JsonArray output = obj.get("output").getAsJsonArray();
		for(int i = 0; i < output.size(); i++) {
			JsonArray entry = output.get(i).getAsJsonArray();
			String mat = entry.get(0).getAsString();
			int amount = entry.get(1).getAsInt();
			materials.add(new MaterialStack(Mats.matByName.get(mat), amount));
		}
		if(input instanceof ComparableStack) {
			Mats.materialEntries.put((ComparableStack) input, materials);
		} else if(input instanceof OreDictStack) {
			Mats.materialOreEntries.put(((OreDictStack) input).name, materials);
		}
	}

	@Override
	public void writeRecipe(Object recipe, JsonWriter writer) throws IOException {
		AStack toSmelt = null;
		Entry entry = (Entry) recipe;
		List<MaterialStack> materials = (List<MaterialStack>) entry.getValue();
		if(entry.getKey() instanceof String) {
			toSmelt = new OreDictStack((String) entry.getKey());
		} else if(entry.getKey() instanceof ComparableStack) {
			toSmelt = (ComparableStack) entry.getKey();
		}
		if(toSmelt == null) return;
		writer.name("input");
		this.writeAStack(toSmelt, writer);
		writer.name("output").beginArray();
		writer.setIndent("");
		for(MaterialStack stack : materials) {
			writer.beginArray();
			writer.value(stack.material.names[0]).value(stack.amount);
			writer.endArray();
		}
		writer.endArray();
		writer.setIndent("  ");
	}

	@Override
	public void deleteRecipes() {
		Mats.materialEntries.clear();
		Mats.materialOreEntries.clear();
	}

	@Override
	public String getComment() {
		return "Defines a set of items that can be smelted. Smelting generated from the ore dictionary (prefix + material) is auto-generated and cannot be "
				+ "changed. This config only changes fixed items (like recycling for certain metallic objects) and ores (with variable byproducts). "
				+ "Amounts used are in quanta (1 quantum is 1/72 of an ingot or 1/8 of a nugget). Material names are the ore dict suffixes, case-sensitive.";
	}
}
