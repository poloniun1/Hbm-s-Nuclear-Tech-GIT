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
//#MARKER ADD SHAPELESS
recipes.addShapeless(<hbm:item.waste_np237:1> * 2, [<hbm:item.rod_zirnox_np237_fuel_depleted>]);
recipes.addShapeless(<hbm:item.waste_pu239:1> * 2, [<hbm:item.rod_zirnox_pu239_fuel_depleted>]);
recipes.addShapeless(<hbm:item.waste_sa326:1> * 2, [<hbm:item.rod_zirnox_sa326_fuel_depleted>]);
recipes.addShapeless(<hbm:item.bred_lead> * 2, [<hbm:item.rod_zirnox_bred_lead>]);

recipes.addShapeless(<hbm:item.powder_strontium> * 9, [<ore:blockStrontium>]);
recipes.addShapeless(<hbm:item.ingot_technetium> * 9, [<ore:blockTechnetium>]);
recipes.addShapeless(<hbm:item.ingot_au198> * 9, [<ore:blockAu198>]);
recipes.addShapeless(<hbm:item.ingot_pb209> * 9, [<ore:blockPb209>]);
recipes.addShapeless(<hbm:item.ingot_silicon> * 9, [<ore:blockSilicon>]);
recipes.addShapeless(<hbm:item.ingot_arsenic> * 9, [<ore:blockArsenic>]);
recipes.addShapeless(<hbm:item.ingot_osmiridium> * 9, [<ore:blockOsmiridium>]);
recipes.addShapeless(<hbm:item.ingot_calcium> * 9, [<ore:blockCalcium>]);
recipes.addShapeless(<hbm:item.powder_neodymium> * 9, [<ore:blockNeodymium>]);
recipes.addShapeless(<hbm:item.gem_volcanic> * 9, [<ore:blockVolcanic>]);
recipes.addShapeless(<hbm:item.gem_alexandrite> * 9, [<ore:blockAlexandrite>]);
recipes.addShapeless(<hbm:item.powder_sodium> * 9, [<ore:blockSodium>]);

//#MARKER ADD SHAPED
recipes.addShaped(<hbm:tile.block_sodium>, [[<ore:dustSodium>, <ore:dustSodium>,
 <ore:dustSodium>], [<ore:dustSodium>, <ore:dustSodium>, <ore:dustSodium>],
 [<ore:dustSodium>, <ore:dustSodium>, <ore:dustSodium>]]);
recipes.addShaped(<hbm:tile.block_strontium>, [[<ore:dustStrontium>, <ore:dustStrontium>,
 <ore:dustStrontium>], [<ore:dustStrontium>, <ore:dustStrontium>, <ore:dustStrontium>],
 [<ore:dustStrontium>, <ore:dustStrontium>, <ore:dustStrontium>]]);
recipes.addShaped(<hbm:tile.block_technetium>, [[<ore:ingotTc99>, <ore:ingotTc99>,
 <ore:ingotTc99>], [<ore:ingotTc99>, <ore:ingotTc99>, <ore:ingotTc99>],
 [<ore:ingotTc99>, <ore:ingotTc99>, <ore:ingotTc99>]]);
recipes.addShaped(<hbm:tile.block_osmiridium>, [[<ore:ingotOsmiridium>, <ore:ingotOsmiridium>,
 <ore:ingotOsmiridium>], [<ore:ingotOsmiridium>, <ore:ingotOsmiridium>, <ore:ingotOsmiridium>],
 [<ore:ingotOsmiridium>, <ore:ingotOsmiridium>, <ore:ingotOsmiridium>]]);
recipes.addShaped(<hbm:tile.block_arsenic>, [[<ore:ingotArsenic>, <ore:ingotArsenic>,
 <ore:ingotArsenic>], [<ore:ingotArsenic>, <ore:ingotArsenic>, <ore:ingotArsenic>],
 [<ore:ingotArsenic>, <ore:ingotArsenic>, <ore:ingotArsenic>]]);
recipes.addShaped(<hbm:tile.block_au198>, [[<ore:ingotAu198>, <ore:ingotAu198>,
 <ore:ingotAu198>], [<ore:ingotAu198>, <ore:ingotAu198>, <ore:ingotAu198>],
 [<ore:ingotAu198>, <ore:ingotAu198>, <ore:ingotAu198>]]);
recipes.addShaped(<hbm:tile.block_pb209>, [[<ore:ingotPb209>, <ore:ingotPb209>,
 <ore:ingotPb209>], [<ore:ingotPb209>, <ore:ingotPb209>, <ore:ingotPb209>],
 [<ore:ingotPb209>, <ore:ingotPb209>, <ore:ingotPb209>]]);
recipes.addShaped(<hbm:tile.block_silicon>, [[<ore:ingotSilicon>, <ore:ingotSilicon>,
 <ore:ingotSilicon>], [<ore:ingotSilicon>, <ore:ingotSilicon>, <ore:ingotSilicon>],
 [<ore:ingotSilicon>, <ore:ingotSilicon>, <ore:ingotSilicon>]]);
recipes.addShaped(<hbm:tile.block_neodymium>, [[<ore:ingotNeodymium>, <ore:ingotNeodymium>,
 <ore:ingotNeodymium>], [<ore:ingotNeodymium>, <ore:ingotNeodymium>, <ore:ingotNeodymium>],
 [<ore:ingotNeodymium>, <ore:ingotNeodymium>, <ore:ingotNeodymium>]]);
recipes.addShaped(<hbm:tile.block_volcanic>, [[<ore:ingotVolcanic>, <ore:ingotVolcanic>,
 <ore:ingotVolcanic>], [<ore:ingotVolcanic>, <ore:ingotVolcanic>, <ore:ingotVolcanic>],
 [<ore:ingotVolcanic>, <ore:ingotVolcanic>, <ore:ingotVolcanic>]]);
recipes.addShaped(<hbm:tile.block_alexandrite>, [[<ore:ingotAlexandrite>, <ore:ingotAlexandrite>,
 <ore:ingotAlexandrite>], [<ore:ingotAlexandrite>, <ore:ingotAlexandrite>, <ore:ingotAlexandrite>],
 [<ore:ingotAlexandrite>, <ore:ingotAlexandrite>, <ore:ingotAlexandrite>]]);
recipes.addShaped(<hbm:tile.block_calcium>, [[<ore:ingotCalcium>, <ore:ingotCalcium>,
 <ore:ingotCalcium>], [<ore:ingotCalcium>, <ore:ingotCalcium>, <ore:ingotCalcium>],
 [<ore:ingotCalcium>, <ore:ingotCalcium>, <ore:ingotCalcium>]]);


recipes.addShaped(<hbm:item.ingot_starmetal>, [[<ore:nuggetStarmetal>, <ore:nuggetStarmetal>,
 <ore:nuggetStarmetal>], [<ore:nuggetStarmetal>, <ore:nuggetStarmetal>, <ore:nuggetStarmetal>],
 [<ore:nuggetStarmetal>, <ore:nuggetStarmetal>, <ore:nuggetStarmetal>]]);
recipes.addShaped(<hbm:item.ingot_cft>, [[<hbm:item.nugget_fuller>, <hbm:item.nugget_fuller>, 
<hbm:item.nugget_fuller>], [<hbm:item.nugget_fuller>, <hbm:item.nugget_fuller>, <hbm:item.nugget_fuller>],
 [<hbm:item.nugget_fuller>, <hbm:item.nugget_fuller>, <hbm:item.nugget_fuller>]]);
recipes.addShaped(<hbm:item.ingot_u238m2>, [[<hbm:item.nugget_u238m2>, <hbm:item.nugget_u238m2>, 
<hbm:item.nugget_u238m2>], [<hbm:item.nugget_u238m2>, <hbm:item.nugget_u238m2>, <hbm:item.nugget_u238m2>], 
[<hbm:item.nugget_u238m2>, <hbm:item.nugget_u238m2>, <hbm:item.nugget_u238m2>]]);
recipes.addShaped(<hbm:item.powder_tennessine>, [[<ore:dustTinyTennessine>, <ore:dustTinyTennessine>, 
<ore:dustTinyTennessine>], [<ore:dustTinyTennessine>, <ore:dustTinyTennessine>, <ore:dustTinyTennessine>], 
[<ore:dustTinyTennessine>, <ore:dustTinyTennessine>, <ore:dustTinyTennessine>]]);
recipes.addShaped(<hbm:item.powder_strontium>, [[<ore:dustTinyStrontium>, <ore:dustTinyStrontium>,
 <ore:dustTinyStrontium>], [<ore:dustTinyStrontium>, <ore:dustTinyStrontium>, <ore:dustTinyStrontium>],
 [<ore:dustTinyStrontium>, <ore:dustTinyStrontium>, <ore:dustTinyStrontium>]]);
recipes.addShaped(<hbm:item.powder_iodine>, [[<ore:dustTinyIodine>, <ore:dustTinyIodine>, 
<ore:dustTinyIodine>], [<ore:dustTinyIodine>, <ore:dustTinyIodine>, <ore:dustTinyIodine>],
 [<ore:dustTinyIodine>, <ore:dustTinyIodine>, <ore:dustTinyIodine>]]);
recipes.addShaped(<hbm:item.powder_caesium>, [[<ore:dustTinyCaesium>, <ore:dustTinyCaesium>, 
<ore:dustTinyCaesium>], [<ore:dustTinyCaesium>, <ore:dustTinyCaesium>, <ore:dustTinyCaesium>], 
[<ore:dustTinyCaesium>, <ore:dustTinyCaesium>, <ore:dustTinyCaesium>]]);
recipes.addShaped(<hbm:item.powder_bromine>, [[<ore:dustTinyBromine>, <ore:dustTinyBromine>,
 <ore:dustTinyBromine>], [<ore:dustTinyBromine>, <ore:dustTinyBromine>, <ore:dustTinyBromine>],
 [<ore:dustTinyBromine>, <ore:dustTinyBromine>, <ore:dustTinyBromine>]]);
recipes.addShaped(<hbm:item.powder_astatine>, [[<hbm:item.powder_astatine_tiny>, <ore:dustTinyAstatine>, 
<ore:dustTinyAstatine>], [<ore:dustTinyAstatine>, <ore:dustTinyAstatine>, <ore:dustTinyAstatine>], 
[<ore:dustTinyAstatine>, <ore:dustTinyAstatine>, <ore:dustTinyAstatine>]]);

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

<ore:ingotOsmium>.add(<hbm:item.ingot_osmiridium>);
recipes.addShapeless(<hbm:item.powder_flux>*10, [<ore:dustCalcium>, <ore:dustIron>]);

recipes.addShapeless(<hbm:item.billet_ra226be>*3, [<ore:dustRa226>, <ore:dustBeryllium>]);
recipes.addShapeless(<hbm:item.billet_po210be>*3, [<ore:dustPolonium>, <ore:dustBeryllium>]);


recipes.addShapeless(<hbm:item.pwr_fuel:13>, [<hbm:item.plate_polymer>,<ore:ingotEuphemium>,
<ore:nuggetEuphemium>,<ore:nuggetEuphemium>,<ore:nuggetEuphemium>]);
recipes.addShapeless(<hbm:item.pwr_fuel:14>, [<hbm:item.plate_polymer>,<ore:billetUranium>,
<ore:billetUranium>]);

recipes.addShapeless(<hbm:item.rod_zirnox:11>, [<hbm:item.rod_zirnox_empty>,<hbm:item.billet_neptunium>,
<hbm:item.billet_neptunium>]);
recipes.addShapeless(<hbm:item.rod_zirnox:12>, [<hbm:item.rod_zirnox_empty>,<hbm:item.billet_pu239>,
<hbm:item.billet_pu239>]);
recipes.addShapeless(<hbm:item.rod_zirnox:13>, [<hbm:item.rod_zirnox_empty>,<hbm:item.billet_schrabidium>,
<hbm:item.billet_schrabidium>]);
recipes.addShapeless(<hbm:item.rod_zirnox:14>, [<hbm:item.rod_zirnox_empty>,<ore:ingotLead>,
<ore:ingotLead>]);

recipes.addShaped(<hbm:item.watz_pellet:7>, [[null,<ore:ingotEuphemium>,null],
[<ore:ingotEuphemium>,<ore:ingotGraphite>,<ore:ingotEuphemium>],[null,<ore:ingotEuphemium>,null]]);
recipes.addShaped(<hbm:item.watz_pellet:11>, [[null,<ore:ingotUranium>,null],
[<ore:ingotUranium>,<ore:ingotGraphite>,<ore:ingotUranium>],[null,<ore:ingotUranium>,null]]);
recipes.addShaped(<hbm:item.watz_pellet:12>, [[null,<ore:ingotingotU235>,null],
[<ore:ingotU235>,<ore:ingotGraphite>,<ore:ingotU235>],[null,<ore:ingotU235>,null]]);
recipes.addShaped(<hbm:item.watz_pellet:13>, [[null,<ore:ingotPu239>,null],
[<ore:ingotPu239>,<ore:ingotGraphite>,<ore:ingotPu239>],[null,<ore:ingotPu239>,null]]);

recipes.addShapeless(<hbm:item.rbmk_fuel_euph>, [<hbm:item.rbmk_fuel_empty>,<ore:ingotEuphemium>,
<ore:ingotEuphemium>,<ore:ingotEuphemium>,<ore:ingotEuphemium>,<ore:ingotEuphemium>,
<ore:nuggetEuphemium>,<ore:nuggetEuphemium>,<ore:nuggetEuphemium>]);
recipes.addShapeless(<hbm:item.rbmk_fuel_dnt>, [<hbm:item.rbmk_fuel_empty>,<ore:ingotDineutronium>,
<ore:ingotDineutronium>,<ore:ingotDineutronium>,<ore:ingotDineutronium>,<ore:ingotDineutronium>,
<ore:nuggetDineutronium>,<ore:nuggetDineutronium>,<ore:nuggetDineutronium>]);
recipes.addShapeless(<hbm:item.rbmk_fuel_pobi>, [<hbm:item.rbmk_fuel_empty>,<hbm:item.billet_polonium>,
<hbm:item.billet_beryllium>,<hbm:item.billet_bismuth>,<hbm:item.billet_bismuth>,<hbm:item.billet_bismuth>,
<hbm:item.billet_bismuth>,<hbm:item.billet_bismuth>,<hbm:item.billet_bismuth>]);
recipes.addShapeless(<hbm:item.rbmk_fuel_sa326>, [<hbm:item.rbmk_fuel_empty>,<hbm:item.billet_schrabidium>,
<hbm:item.billet_schrabidium>,<hbm:item.billet_schrabidium>,<hbm:item.billet_schrabidium>,<hbm:item.billet_schrabidium>,
<hbm:item.billet_schrabidium>,<hbm:item.billet_schrabidium>,<hbm:item.billet_schrabidium>]);

recipes.addShapeless(<hbm:item.plate_fuel_atbe>, [<hbm:item.powder_astatine_tiny>,<hbm:item.powder_astatine_tiny>,
<hbm:item.powder_astatine_tiny>,<hbm:item.nugget_beryllium>,<hbm:item.nugget_beryllium>,<hbm:item.nugget_beryllium>]);

recipes.addShaped(<hbm:item.pile_rod_plutonium>*2, [[<hbm:item.plate_iron>,<hbm:item.billet_pu239>,<hbm:item.plate_iron>],
[<hbm:item.ingot_pu239>,<hbm:item.pellet_charged>,<hbm:item.ingot_pu239>],
[<hbm:item.plate_iron>,<hbm:item.billet_pu239>,<hbm:item.plate_iron>]]);
recipes.addShaped(<hbm:item.pile_rod_plutonium>*2, [[<hbm:item.plate_iron>,<hbm:item.ingot_pu239>,<hbm:item.plate_iron>],
[<hbm:item.billet_pu239>,<hbm:item.pellet_charged>,<hbm:item.billet_pu239>],
[<hbm:item.plate_iron>,<hbm:item.ingot_pu239>,<hbm:item.plate_iron>]]);
recipes.addShaped(<hbm:item.pile_rod_plutonium>, [[null,<hbm:item.ingot_plutonium>,null],
[<hbm:item.plate_iron>,<hbm:item.pellet_charged>,<hbm:item.plate_iron>],[null,<hbm:item.ingot_plutonium>,null]]);
recipes.addShapeless(<hbm:item.pile_rod_plutonium>, [<hbm:item.pile_rod_pu239>,<hbm:item.pellet_charged>]);

recipes.addShaped(<hbm:item.cube_power>, [[<hbm:item.ingot_electronium>,<hbm:item.ingot_u238m2>,<hbm:item.ingot_electronium>],
[<hbm:item.ingot_electronium>,<hbm:item.laser_crystal_digamma>,<hbm:item.ingot_electronium>],
[<hbm:item.ingot_electronium>,<hbm:item.billet_yharonite>,<hbm:item.ingot_electronium>]]);

recipes.addShaped(<hbm:item.dnt_pickaxe>, [[null,<ore:ingotDineutronium>,<hbm:item.ingot_u238m2>],
[<ore:ingotDineutronium>,<hbm:item.mese_pickaxe>,<ore:ingotDineutronium>],
[<hbm:item.motor_desh>,<ore:ingotDineutronium>,null]]);
recipes.addShaped(<hbm:item.osmiridium_pickaxe>, [[null,<ore:ingotOsmiridium>,<hbm:item.ingot_electronium>],
[<ore:ingotOsmiridium>,<hbm:item.dnt_pickaxe>,<ore:ingotOsmiridium>],
[<hbm:item.motor_bismuth>,<ore:ingotOsmiridium>,null]]);

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


