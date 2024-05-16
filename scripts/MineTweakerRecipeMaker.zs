// File generated by MineTweakerRecipeMaker
//                     READ THIS HEADER BEFORE EDITING ANYTHING
// ================================================================================
//     This file is read and changed by the mod.
//     If you remove/edit any of the markers, IT WILL STOP WORKING!
//     If you want to make manual edits, make a backup of this file!
//     Markers look like this: "//#MARKER something"
//     They indicate where calls should be placed, so that MineTweaker does them in the correct order.
//     Removes come first, then stuff is added.
// ================================================================================
//

// ================================================================================
//#MARKER REMOVE

// ================================================================================
//#MARKER REMOVE SHAPELESS

// ================================================================================
//#MARKER REMOVE SHAPED

// ================================================================================
//#MARKER ADD
//import mods.ic2.Canner;
//import mods.ic2.Compressor;
//import mods.ic2.Extractor;
//import mods.ic2.Macerator;
//import mods.ic2.MetalFormer;
//import mods.ic2.OreWasher;
//import mods.ic2.ThermalCentrifuge;
//<ore:oreBeryllium>.add(<RotaryCraft:rotarycraft_item_customingot:4>);
<ore:oreManganese>.add(<shincolle:BlockPolymetalOre>);
<ore:oreManganese>.add(<shincolle:BlockPolymetalGravel>);
<ore:ingotManganese>.add(<shincolle:AbyssMetal>);
//<ore:ingotTungsten>.add(<RotaryCraft:rotarycraft_item_compacts:5>);
<ore:ingotOsmium>.add(<hbm:item.ingot_osmiridium>);
recipes.addShapeless(<hbm:item.powder_flux>*10, [<ore:dustCalcium>, <ore:dustIron>]);
recipes.addShapeless(<hbm:item.billet_ra226be>*3, [<ore:dustRa226>, <ore:dustBeryllium>]);
recipes.addShapeless(<hbm:item.billet_ra226be>*3, [<ore:ingotRa226>, <ore:ingotBeryllium>]);
recipes.addShapeless(<hbm:item.billet_po210be>*3, [<ore:dustPolonium>, <ore:dustBeryllium>]);
recipes.addShapeless(<hbm:item.billet_po210be>*3, [<ore:ingotPolonium>, <ore:ingotBeryllium>]);
recipes.addShapeless(<hbm:item.pwr_fuel:13>, [<hbm:item.plate_polymer>,<ore:ingotEuphemium>]);
recipes.addShaped(<hbm:item.watz_pellet:7>, [[null,<ore:ingotEuphemium>,null],
[<ore:ingotEuphemium>,<ore:ingotGraphite>,<ore:ingotEuphemium>],[null,<ore:ingotEuphemium>,null]]);
recipes.addShapeless(<ha:item.rbmk_fuel_euph>, [<hbm:item.rbmk_fuel_empty>,<ore:ingotEuphemium>,
<ore:ingotEuphemium>,<ore:ingotEuphemium>,
<ore:ingotEuphemium>,<ore:ingotEuphemium>]);
recipes.addShapeless(<ha:item.rbmk_fuel_dnt>, [<hbm:item.rbmk_fuel_empty>,<ore:ingotDineutronium>,
<ore:ingotDineutronium>,<ore:ingotDineutronium>,
<ore:ingotDineutronium>,<ore:ingotDineutronium>]);
recipes.addShapeless(<ha:item.rbmk_fuel_pobi>, [<hbm:item.rbmk_fuel_empty>,<hbm:item.billet_polonium>,
<hbm:item.billet_beryllium>,<hbm:item.billet_bismuth>,<hbm:item.billet_bismuth>,<hbm:item.billet_bismuth>,
<hbm:item.billet_bismuth>,<hbm:item.billet_bismuth>,<hbm:item.billet_bismuth>]);
recipes.addShapeless(<ha:item.plate_fuel_atbe>*2, [<ore:dustAstatine>,<ore:dustBeryllium>]);
recipes.addShaped(<hbm:item.pile_rod_plutonium>*2, [[null,<hbm:item.ingot_pu239>,null],
[<hbm:item.plate_iron>,<hbm:item.ingot_pu239>,<hbm:item.plate_iron>],[null,<hbm:item.ingot_pu239>,null]]);
recipes.addShaped(<hbm:item.pile_rod_plutonium>, [[null,<hbm:item.billet_plutonium>,null],
[<hbm:item.plate_iron>,<hbm:item.billet_plutonium>,<hbm:item.plate_iron>],[null,<hbm:item.billet_plutonium>,null]]);
recipes.addShaped(<hbm:item.cube_power>, [[<hbm:item.ingot_electronium>,<hbm:item.ingot_u238m2>,<hbm:item.ingot_electronium>],
[<hbm:item.ingot_electronium>,<hbm:item.laser_crystal_digamma>,<hbm:item.ingot_electronium>],
[<hbm:item.ingot_electronium>,<hbm:item.billet_yharonite>,<hbm:item.ingot_electronium>]]);
recipes.addShaped(<ha:tile.rbmk_turbine>, [[<hbm:tile.rbmk_heater>,<hbm:tile.rbmk_heater>,<hbm:tile.rbmk_heater>],
[<hbm:tile.rbmk_heater>,<hbm:tile.machine_turbine>,<hbm:tile.rbmk_heater>],
[<hbm:tile.rbmk_heater>,<hbm:tile.rbmk_heater>,<hbm:tile.rbmk_heater>]]);

//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:11>);
//furnace.addRecipe(<hbm:item.nugget_mercury>,<RotaryCraft:rotarycraft_item_modextracts:47>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:63>);
//furnace.addRecipe(<hbm:item.powder_titanium>,<RotaryCraft:rotarycraft_item_modextracts:255>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:21>);
//furnace.addRecipe(<hbm:item.powder_tungsten>,<RotaryCraft:rotarycraft_item_modextracts:87>);
//furnace.addRecipe(<hbm:item.powder_tungsten>,<RotaryCraft:rotarycraft_item_extracts:33>);
//furnace.remove(<minecraft:emerald>,<RotaryCraft:rotarycraft_item_extracts:30>);
//furnace.addRecipe(<hbm:item.powder_emerald>,<RotaryCraft:rotarycraft_item_extracts:30>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:84>);
//furnace.addRecipe(<hbm:item.powder_thorium>,<RotaryCraft:rotarycraft_item_modextracts:339>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:2>);
//furnace.addRecipe(<hbm:item.powder_lead>,<RotaryCraft:rotarycraft_item_modextracts:11>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:1>);
//furnace.addRecipe(<hbm:item.powder_copper>,<RotaryCraft:rotarycraft_item_modextracts:7>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:6>);
//furnace.addRecipe(<hbm:item.powder_aluminium>,<RotaryCraft:rotarycraft_item_modextracts:27>);
//furnace.remove(<minecraft:gold_ingot>,<RotaryCraft:rotarycraft_item_extracts:26>);
//furnace.addRecipe(<hbm:item.powder_gold>,<RotaryCraft:rotarycraft_item_extracts:26>);
//furnace.remove(<minecraft:diamond>,<RotaryCraft:rotarycraft_item_extracts:29>);
//furnace.addRecipe(<hbm:item.powder_diamond>,<RotaryCraft:rotarycraft_item_extracts:29>);
//furnace.remove(<minecraft:coal>,<RotaryCraft:rotarycraft_item_extracts:24>);
//furnace.addRecipe(<hbm:item.powder_coal>*2,<RotaryCraft:rotarycraft_item_extracts:24>);
//furnace.remove(<minecraft:iron_ingot>,<RotaryCraft:rotarycraft_item_extracts:25>);
//furnace.addRecipe(<hbm:item.powder_iron>,<RotaryCraft:rotarycraft_item_extracts:25>);
//furnace.remove(<minecraft:redstone>,<RotaryCraft:rotarycraft_item_extracts:27>);
//furnace.addRecipe(<minecraft:redstone>*3,<RotaryCraft:rotarycraft_item_extracts:27>);
//furnace.remove(<minecraft:dye:4>,<RotaryCraft:rotarycraft_item_extracts:28>);
//furnace.addRecipe(<hbm:item.powder_lapis>*3,<RotaryCraft:rotarycraft_item_extracts:28>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:52>);
//furnace.addRecipe(<hbm:item.fluorite>*3,<RotaryCraft:rotarycraft_item_modextracts:211>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:48>);
//furnace.addRecipe(<RotaryCraft:rotarycraft_item_modingots:48>*3,<RotaryCraft:rotarycraft_item_modextracts:195>);
//furnace.addRecipe(<RotaryCraft:rotarycraft_item_modingots:20>,<minecraft:gunpowder>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:24>);
//furnace.addRecipe(<hbm:item.gem_volcanic>,<RotaryCraft:rotarycraft_item_modextracts:99>);
//furnace.remove(<RotaryCraft:rotarycraft_item_compacts:7>);
//furnace.addRecipe(<hbm:item.nugget_mercury>,<RotaryCraft:rotarycraft_item_extracts:32>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:9>);
//furnace.addRecipe(<hbm:item.powder_quartz>,<RotaryCraft:rotarycraft_item_modextracts:39>);
//furnace.remove(<minecraft:quartz>,<RotaryCraft:rotarycraft_item_extracts:31>);
//furnace.addRecipe(<hbm:item.powder_quartz>,<RotaryCraft:rotarycraft_item_extracts:31>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:3>);
//furnace.addRecipe(<hbm:item.powder_cobalt>,<RotaryCraft:rotarycraft_item_modextracts:15>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:41>);
//furnace.addRecipe(<hbm:item.powder_cobalt>,<RotaryCraft:rotarycraft_item_modextracts:167>);
//furnace.remove(<RotaryCraft:rotarycraft_item_modingots:10>);
//furnace.addRecipe(<hbm:item.powder_uranium>,<RotaryCraft:rotarycraft_item_modextracts:43>);

//furnace.remove(<RotaryCraft:rotarycraft_item_customingot>);
//furnace.addRecipe(<hbm:item.gem_alexandrite>,<RotaryCraft:rotarycraft_item_customextract:3>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:1>);
//furnace.addRecipe(<hbm:item.ingot_arsenic>,<RotaryCraft:rotarycraft_item_customextract:7>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:2>);
//furnace.addRecipe(<hbm:item.powder_asbestos>,<RotaryCraft:rotarycraft_item_customextract:11>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:3>);
//furnace.addRecipe(<hbm:item.powder_australium>,<RotaryCraft:rotarycraft_item_customextract:15>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:4>);
//furnace.addRecipe(<hbm:item.powder_beryllium>,<RotaryCraft:rotarycraft_item_customextract:19>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:5>);
//furnace.addRecipe(<hbm:item.powder_bismuth>,<RotaryCraft:rotarycraft_item_customextract:23>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:6>);
//furnace.addRecipe(<hbm:item.powder_boron>,<RotaryCraft:rotarycraft_item_customextract:27>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:7>);
//furnace.addRecipe(<hbm:item.powder_calcium>,<RotaryCraft:rotarycraft_item_customextract:31>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:8>);
//furnace.addRecipe(<hbm:item.powder_tantalium>,<RotaryCraft:rotarycraft_item_customextract:35>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:9>);
//furnace.addRecipe(<hbm:item.powder_euphemium>,<RotaryCraft:rotarycraft_item_customextract:39>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:10>);
//furnace.addRecipe(<minecraft:glowstone_dust>,<RotaryCraft:rotarycraft_item_customextract:43>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:11>);
//furnace.addRecipe(<hbm:item.powder_lithium>,<RotaryCraft:rotarycraft_item_customextract:47>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:12>);
//furnace.addRecipe(<shincolle:AbyssMetal:1>,<RotaryCraft:rotarycraft_item_customextract:51>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:13>);
//furnace.addRecipe(<hbm:item.powder_neodymium>,<RotaryCraft:rotarycraft_item_customextract:55>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:14>);
//furnace.addRecipe(<hbm:item.powder_niobium>,<RotaryCraft:rotarycraft_item_customextract:59>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:15>);
//furnace.addRecipe(<hbm:item.ingot_osmiridium>,<RotaryCraft:rotarycraft_item_customextract:63>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:16>);
//furnace.addRecipe(<hbm:item.powder_plutonium>,<RotaryCraft:rotarycraft_item_customextract:67>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:17>);
//furnace.addRecipe(<hbm:item.powder_schrabidium>,<RotaryCraft:rotarycraft_item_customextract:71>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:18>);
//furnace.addRecipe(<hbm:item.ingot_starmetal>,<RotaryCraft:rotarycraft_item_customextract:75>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:19>);
//furnace.addRecipe(<hbm:item.powder_nitan_mix>,<RotaryCraft:rotarycraft_item_customextract:79>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:20>);
//furnace.addRecipe(<hbm:item.gem_volcanic>,<RotaryCraft:rotarycraft_item_customextract:83>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:21>);
//furnace.addRecipe(<hbm:item.powder_fire>,<RotaryCraft:rotarycraft_item_customextract:87>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:22>);
//furnace.addRecipe(<hbm:item.powder_desh>,<RotaryCraft:rotarycraft_item_customextract:91>);
//furnace.remove(<RotaryCraft:rotarycraft_item_customingot:23>);
//furnace.addRecipe(<hbm:item.powder_zirconium>,<RotaryCraft:rotarycraft_item_customextract:95>);
//import mods.nei.NEI;

//Canner.addEnrichRecipe(<liquid:molecule.glucose>*500,<liquid:water>*500, <minecraft:sugar>);
//Canner.addEnrichRecipe(<liquid:molecule.butene>*1000,<liquid:molecule.ethanol>*200, <minecraft:coal>);
//Canner.addEnrichRecipe(<liquid:molecule.ethanol>*1000,<liquid:molecule.butene>*1000, <hbm:item.fluid_tank_full:1>);
//Canner.addEnrichRecipe(<liquid:molecule.propane>*1000,<liquid:molecule.butene>*1000, <hbm:item.fluid_tank_full:37>);
//Canner.addEnrichRecipe(<liquid:molecule.ethanol>*500,<liquid:molecule.glucose>*500, <RotaryCraft:rotarycraft_item_yeast>);
//Canner.addEnrichRecipe(<liquid:molecule.ethanol>*1000,<liquid:molecule.butene>*1000,<liquid:water>*8);
//Macerator.removeallRecipe();
//Extractor.addRecipe(<hbm:item.fluid_tank_full:59>,<IC2:itemFluidCell>.withTag({Fluid: {FluidName: "rc ethanol", Amount: 1000}}));
//Extractor.addRecipe(<IC2:itemFluidCell>.withTag({Fluid: {FluidName: "rc ethanol", Amount: 1000}}),<hbm:item.fluid_tank_full:59>);