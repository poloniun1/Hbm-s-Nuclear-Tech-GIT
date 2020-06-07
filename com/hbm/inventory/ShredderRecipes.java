package com.hbm.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ShredderRecipes {

	public static HashMap<ComparableStack, ItemStack> shredderRecipes = new HashMap();
	public static HashMap<Object, Object> neiShredderRecipes;
	
	public static void registerShredder() {
		
		String[] names = OreDictionary.getOreNames();
		
		for(int i = 0; i < names.length; i++) {
			
			String name = names[i];
			
			//if the dict contains invalid names, skip
			if(name == null || name.isEmpty())
				continue;
			
			List<ItemStack> matches = OreDictionary.getOres(name);
			
			//if the name isn't assigned to an ore, also skip
			if(matches == null || matches.isEmpty())
				continue;

			if(name.length() > 5 && name.substring(0, 5).equals("ingot")) {
				ItemStack dust = getDustByName(name.substring(5));
				
				if(dust != null && dust.getItem() != ModItems.scrap) {

					for(ItemStack stack : matches) {
						shredderRecipes.put(new ComparableStack(stack), dust);
					}
				}
			} else if(name.length() > 3 && name.substring(0, 3).equals("ore")) {
				ItemStack dust = getDustByName(name.substring(3));
				
				if(dust != null && dust.getItem() != ModItems.scrap) {
					
					dust.stackSize = 2;

					for(ItemStack stack : matches) {
						shredderRecipes.put(new ComparableStack(stack), dust);
					}
				}
			} else if(name.length() > 5 && name.substring(0, 5).equals("block")) {
				ItemStack dust = getDustByName(name.substring(5));
				
				if(dust != null && dust.getItem() != ModItems.scrap) {
					
					dust.stackSize = 9;

					for(ItemStack stack : matches) {
						shredderRecipes.put(new ComparableStack(stack), dust);
					}
				}
			} else if(name.length() > 3 && name.substring(0, 3).equals("gem")) {
				ItemStack dust = getDustByName(name.substring(3));
				
				if(dust != null && dust.getItem() != ModItems.scrap) {

					for(ItemStack stack : matches) {
						shredderRecipes.put(new ComparableStack(stack), dust);
					}
				}
			} else if(name.length() > 3 && name.substring(0, 4).equals("dust")) {

				for(ItemStack stack : matches) {
					shredderRecipes.put(new ComparableStack(stack), new ItemStack(ModItems.dust));
				}
			}
		}
	}
	
	public static void registerOverrides() {

		ShredderRecipes.setRecipe(ModItems.scrap, new ItemStack(ModItems.dust));
		ShredderRecipes.setRecipe(ModItems.dust, new ItemStack(ModItems.dust));
		ShredderRecipes.setRecipe(Blocks.glowstone, new ItemStack(Items.glowstone_dust, 4));
		ShredderRecipes.setRecipe(new ItemStack(Blocks.quartz_block, 1, 0), new ItemStack(ModItems.powder_quartz, 4));
		ShredderRecipes.setRecipe(new ItemStack(Blocks.quartz_block, 1, 1), new ItemStack(ModItems.powder_quartz, 4));
		ShredderRecipes.setRecipe(new ItemStack(Blocks.quartz_block, 1, 2), new ItemStack(ModItems.powder_quartz, 4));
		ShredderRecipes.setRecipe(Blocks.quartz_stairs, new ItemStack(ModItems.powder_quartz, 3));
		ShredderRecipes.setRecipe(new ItemStack(Blocks.stone_slab, 1, 7), new ItemStack(ModItems.powder_quartz, 2));
		ShredderRecipes.setRecipe(Items.quartz, new ItemStack(ModItems.powder_quartz));
		ShredderRecipes.setRecipe(Blocks.quartz_ore, new ItemStack(ModItems.powder_quartz, 2));
		ShredderRecipes.setRecipe(ModBlocks.ore_nether_fire, new ItemStack(ModItems.powder_fire, 6));
		ShredderRecipes.setRecipe(Blocks.packed_ice, new ItemStack(ModItems.powder_ice, 1));
		ShredderRecipes.setRecipe(ModBlocks.brick_light, new ItemStack(Items.clay_ball, 4));
		ShredderRecipes.setRecipe(ModBlocks.concrete, new ItemStack(Blocks.gravel, 1));
		ShredderRecipes.setRecipe(ModBlocks.concrete_smooth, new ItemStack(Blocks.gravel, 1));
		ShredderRecipes.setRecipe(ModBlocks.brick_concrete, new ItemStack(Blocks.gravel, 1));
		ShredderRecipes.setRecipe(ModBlocks.brick_concrete_mossy, new ItemStack(Blocks.gravel, 1));
		ShredderRecipes.setRecipe(ModBlocks.brick_concrete_cracked, new ItemStack(Blocks.gravel, 1));
		ShredderRecipes.setRecipe(ModBlocks.brick_concrete_broken, new ItemStack(Blocks.gravel, 1));
		ShredderRecipes.setRecipe(ModBlocks.brick_obsidian, new ItemStack(ModBlocks.gravel_obsidian, 1));
		ShredderRecipes.setRecipe(Blocks.obsidian, new ItemStack(ModBlocks.gravel_obsidian, 1));
		ShredderRecipes.setRecipe(Blocks.stone, new ItemStack(Blocks.gravel, 1));
		ShredderRecipes.setRecipe(Blocks.cobblestone, new ItemStack(Blocks.gravel, 1));
		ShredderRecipes.setRecipe(Blocks.stonebrick, new ItemStack(Blocks.gravel, 1));
		ShredderRecipes.setRecipe(Blocks.gravel, new ItemStack(Blocks.sand, 1));
		ShredderRecipes.setRecipe(Blocks.sand, new ItemStack(ModItems.dust, 2));
		ShredderRecipes.setRecipe(Blocks.brick_block, new ItemStack(Items.clay_ball, 4));
		ShredderRecipes.setRecipe(Blocks.brick_stairs, new ItemStack(Items.clay_ball, 3));
		ShredderRecipes.setRecipe(Items.flower_pot, new ItemStack(Items.clay_ball, 3));
		ShredderRecipes.setRecipe(Items.brick, new ItemStack(Items.clay_ball, 1));
		ShredderRecipes.setRecipe(Blocks.sandstone, new ItemStack(Blocks.sand, 4));
		ShredderRecipes.setRecipe(Blocks.sandstone_stairs, new ItemStack(Blocks.sand, 6));
		ShredderRecipes.setRecipe(Blocks.clay, new ItemStack(Items.clay_ball, 4));
		ShredderRecipes.setRecipe(Blocks.hardened_clay, new ItemStack(Items.clay_ball, 4));
		ShredderRecipes.setRecipe(Blocks.tnt, new ItemStack(Items.gunpowder, 5));
		ShredderRecipes.setRecipe(ModItems.powder_quartz, new ItemStack(ModItems.powder_lithium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.powder_lapis, new ItemStack(ModItems.powder_cobalt_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_neodymium, new ItemStack(ModItems.powder_neodymium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_cobalt, new ItemStack(ModItems.powder_cobalt_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_niobium, new ItemStack(ModItems.powder_niobium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_cerium, new ItemStack(ModItems.powder_cerium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_lanthanium, new ItemStack(ModItems.powder_lanthanium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_actinium, new ItemStack(ModItems.powder_actinium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_meteorite, new ItemStack(ModItems.powder_meteorite_tiny, 1));
		ShredderRecipes.setRecipe(ModBlocks.block_meteor, new ItemStack(ModItems.powder_meteorite, 10));
		ShredderRecipes.setRecipe(Items.enchanted_book, new ItemStack(ModItems.powder_magic, 1));
		ShredderRecipes.setRecipe(ModItems.arc_electrode_burnt, new ItemStack(ModItems.powder_coal, 1));
		ShredderRecipes.setRecipe(ModItems.arc_electrode_desh, new ItemStack(ModItems.powder_desh, 2));
		ShredderRecipes.setRecipe(ModBlocks.meteor_polished, new ItemStack(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.meteor_brick, new ItemStack(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.meteor_brick_mossy, new ItemStack(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.meteor_brick_cracked, new ItemStack(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.meteor_brick_chiseled, new ItemStack(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.meteor_pillar, new ItemStack(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.ore_rare, new ItemStack(ModItems.powder_desh_mix, 1));

		ShredderRecipes.setRecipe(ModItems.crystal_iron, new ItemStack(ModItems.powder_iron, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_gold, new ItemStack(ModItems.powder_iron, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_redstone, new ItemStack(Items.redstone, 8));
		ShredderRecipes.setRecipe(ModItems.crystal_uranium, new ItemStack(ModItems.powder_uranium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_plutonium, new ItemStack(ModItems.powder_plutonium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_thorium, new ItemStack(ModItems.powder_thorium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_titanium, new ItemStack(ModItems.powder_titanium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_sulfur, new ItemStack(ModItems.sulfur, 8));
		ShredderRecipes.setRecipe(ModItems.crystal_niter, new ItemStack(ModItems.niter, 8));
		ShredderRecipes.setRecipe(ModItems.crystal_copper, new ItemStack(ModItems.powder_copper, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_tungsten, new ItemStack(ModItems.powder_tungsten, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_aluminium, new ItemStack(ModItems.powder_aluminium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_fluorite, new ItemStack(ModItems.fluorite, 8));
		ShredderRecipes.setRecipe(ModItems.crystal_beryllium, new ItemStack(ModItems.powder_beryllium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_lead, new ItemStack(ModItems.powder_lead, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_schrabidium, new ItemStack(ModItems.powder_schrabidium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_rare, new ItemStack(ModItems.powder_desh_mix, 2));
		ShredderRecipes.setRecipe(ModItems.crystal_phosphorus, new ItemStack(ModItems.powder_fire, 8));
		ShredderRecipes.setRecipe(ModItems.crystal_trixite, new ItemStack(ModItems.powder_plutonium, 6));
		ShredderRecipes.setRecipe(ModItems.crystal_lithium, new ItemStack(ModItems.powder_lithium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_starmetal, new ItemStack(ModItems.powder_dura_steel, 6));

		for(int i = 0; i < 16; i++) {
			ShredderRecipes.setRecipe(new ItemStack(Blocks.stained_hardened_clay, 1, i), new ItemStack(Items.clay_ball, 4));
			ShredderRecipes.setRecipe(new ItemStack(Blocks.wool, 1, i), new ItemStack(Items.string, 4));
		}
	}
	
	public static ItemStack getDustByName(String name) {
		
		List<ItemStack> matches = OreDictionary.getOres("dust" + name);
		
		if(matches != null && !matches.isEmpty())
			return matches.get(0).copy();
		
		return new ItemStack(ModItems.scrap);
	}
	
	public static void setRecipe(Item in, ItemStack out) {
		
		shredderRecipes.put(new ComparableStack(in), out);
	}
	
	public static void setRecipe(Block in, ItemStack out) {
		
		shredderRecipes.put(new ComparableStack(in), out);
	}
	
	public static void setRecipe(ItemStack in, ItemStack out) {
		
		shredderRecipes.put(new ComparableStack(in), out);
	}
	
	public static Map<Object, Object> getShredderRecipes() {
		
		//convert the map only once to save on processing power (might be more ram intensive but that can't be THAT bad, right?)
		if(neiShredderRecipes == null)
			neiShredderRecipes = new HashMap(shredderRecipes);
		
		return neiShredderRecipes;
	}
	
	public static ItemStack getShredderResult(ItemStack stack) {
		
		if(stack == null || stack.getItem() == null)
			return new ItemStack(ModItems.scrap);
		
		ItemStack sta = shredderRecipes.get(new ComparableStack(stack).makeSingular());
		
		/*if(sta != null)
			System.out.println(stack.getDisplayName() + " resulted " + sta.getDisplayName());
		else
			System.out.println(stack.getDisplayName() + " resulted null");*/
		
		return sta == null ? new ItemStack(ModItems.scrap) : sta;
	}
}
