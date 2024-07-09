package com.hbm.itempool;

import static com.hbm.lib.HbmChestContents.weighted;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;

import net.minecraft.init.Items;
import net.minecraft.util.WeightedRandomChestContent;

public class ItemPoolsSatellite {

	public static final String POOL_SAT_MINER = "POOL_SAT_MINER";
	public static final String POOL_SAT_LUNAR = "POOL_SAT_LUNAR"; //woona
	
	public static void init() {
		
		new ItemPool(POOL_SAT_MINER) {{
			this.pool = new WeightedRandomChestContent[] {
					weighted(ModItems.powder_aluminium, 0, 3, 3, 10),
					weighted(ModItems.powder_iron, 0, 3, 3, 10),
					weighted(ModItems.powder_titanium, 0, 2, 2, 10),
					weighted(ModItems.crystal_tungsten, 0, 2, 2, 7),
					weighted(ModItems.powder_coal, 0, 4, 4, 10),
					weighted(ModItems.powder_uranium, 0, 2, 2, 10),
					weighted(ModItems.powder_plutonium, 0, 1, 1, 10),
					weighted(ModItems.powder_thorium, 0, 2, 2, 10),
					weighted(ModItems.powder_desh, 0, 3, 3, 10),
					weighted(ModItems.powder_diamond, 0, 2, 2, 10),
					weighted(Items.redstone, 0, 5, 5, 10),
					weighted(ModItems.powder_nitan_mix, 0, 2, 2, 10),
					weighted(ModItems.powder_spark_mix, 0, 1, 1, 10),
					weighted(ModItems.powder_power, 0, 2, 2, 10),
					weighted(ModItems.powder_copper, 0, 5, 5, 10),
					weighted(ModItems.powder_lead, 0, 3, 3, 10),
					weighted(ModItems.fluorite, 0, 4, 4, 10),
					weighted(ModItems.powder_lapis, 0, 4, 4, 10),
					weighted(ModItems.powder_combine_steel, 0, 1, 1, 8),
					weighted(ModItems.powder_magic, 0, 1, 1, 7),
					weighted(ModItems.powder_dineutronium, 0, 1, 1, 7),
					weighted(ModItems.ingot_technetium, 0, 1, 1, 10),
					weighted(ModItems.powder_meteorite, 0, 1, 1, 8)
			};
		}};
		
		new ItemPool(POOL_SAT_LUNAR) {{
			this.pool = new WeightedRandomChestContent[] {
					weighted(ModBlocks.moon_turf, 0, 8, 8, 15),
					weighted(ModBlocks.moon_turf, 0, 16, 16, 15),
					weighted(ModItems.powder_lithium, 0, 3, 3, 5),
					weighted(ModItems.powder_iron, 0, 3, 3, 5),
					weighted(ModItems.powder_nitan_mix, 0, 2, 2, 10),
					weighted(ModItems.powder_spark_mix, 0, 1, 1, 10),
					weighted(ModItems.powder_combine_steel, 0, 1, 1, 8),
					weighted(ModItems.powder_magic, 0, 1, 1, 7),
					weighted(ModItems.powder_dineutronium, 0, 1, 1, 7),
					weighted(ModItems.ingot_technetium, 0, 1, 1, 10),
					weighted(ModItems.powder_meteorite, 0, 1, 1, 8)
			};
		}};
	}
}
