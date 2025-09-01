package com.hbm.tileentity.machine.rbmk;

import com.hbm.config.GeneralConfig;
import com.hbm.main.MainRegistry;
import com.hbm.util.GameRuleHelper;

import com.hbm.util.Tuple;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RBMKDials {

	public enum RBMKKeys {
		KEY_SAVE_DIALS("dialSaveDials", true),
		KEY_PASSIVE_COOLING("dialPassiveCooling", 1.0),
		KEY_COLUMN_HEAT_FLOW("dialColumnHeatFlow", 0.2),
		KEY_FUEL_DIFFUSION_MOD("dialDiffusionMod", 1.0),
		KEY_HEAT_PROVISION("dialHeatProvision", 0.2),
		KEY_COLUMN_HEIGHT("dialColumnHeight", 2),
		KEY_PERMANENT_SCRAP("dialEnablePermaScrap", true),
		KEY_BOILER_HEAT_CONSUMPTION("dialBoilerHeatConsumption", 0.1),
		KEY_CONTROL_SPEED_MOD("dialControlSpeed", 1.0),
		KEY_REACTIVITY_MOD("dialReactivityMod", 1.0),
		KEY_OUTGASSER_MOD("dialOutgasserSpeedMod", 1.0),
		KEY_SURGE_MOD("dialControlSurgeMod", 1.0),
		KEY_FLUX_RANGE("dialFluxRange", 5),
		KEY_REASIM_RANGE("dialReasimRange", 10),
		KEY_REASIM_COUNT("dialReasimCount", 8),
		KEY_REASIM_MOD("dialReasimOutputMod", 1.0),
		KEY_REASIM_BOILERS("dialReasimBoilers", false),
		KEY_REASIM_BOILER_SPEED("dialReasimBoilerSpeed", 0.05),
		KEY_DISABLE_MELTDOWNS("dialDisableMeltdowns", true),
		KEY_ENABLE_MELTDOWN_OVERPRESSURE("dialEnableMeltdownOverpressure", false),
		KEY_MODERATOR_EFFICIENCY("dialModeratorEfficiency", 1.0),
		KEY_ABSORBER_EFFICIENCY("dialAbsorberEfficiency", 1.0),
		KEY_REFLECTOR_EFFICIENCY("dialReflectorEfficiency", 1.0),
		KEY_DISABLE_DEPLETION("dialDisableDepletion", false),
		KEY_DISABLE_XENON("dialDisableXenon", false),
		KEY_UNIQUE_ROD ("dialUniqueRod", true),
		KEY_REASIM_COOLANT_BOILERS ("dialReasimCoolantBoilers", false),
		KEY_RBMK_BABY_MODE ("dialRBMKBabyMode", true),//RBMK
		KEY_PWR_BABY_MODE ("dialPWRBabyMode", true),//PWR
		KEY_RR_BABY_MODE ("dialResearchBabyMode", true),//Reactor research
		KEY_ZIRNOX_BABY_MODE ("dialZIRNOXBabyMode", true),//ZIRNOX
		KEY_WATZ_BABY_MODE ("dialWatzBabyMode", true),//WATZ
		KEY_ITER_BABY_MODE ("dialITERBabyMode", true),//FUSION
		KEY_ICF_BABY_MODE ("dialICFBabyMode", true),//ICF
		KEY_DFC_BABY_MODE ("dialDFCBabyMode", true),//DFC	
		KEY_CRUCIBLE_BABY_MODE ("dialCrucibleBabyMode", true),//CRUCIBLE
		KEY_HIGH_FLUX_MODE ("dialHighFluxMode", false),
		KEY_LMSR_MODE ("dialLMSRMode", true),//LMSR
		KEY_ALBION_BABY_MODE ("dialAlbionBabyMode", false);//ALBION

		public final String keyString;
		public final Object defValue;

		RBMKKeys(String key, Object def) {
			keyString = key;
			defValue = def;
		}
	}

	public static HashMap<RBMKKeys, List<Tuple.Pair<World, Object>>> gameRules = new HashMap<>();

	public static void createDials(World world) {
		createDials(world, false);
	}

	public static void createDials(World world, boolean forceRecreate) {
		GameRules rules = world.getGameRules();

		for(RBMKKeys key : RBMKKeys.values())
			gameRules.put(key, new ArrayList<>());
		refresh(world);

		if(!rules.getGameRuleBooleanValue(RBMKKeys.KEY_SAVE_DIALS.keyString) || forceRecreate) {
			for(RBMKKeys key : RBMKKeys.values())
				rules.setOrCreateGameRule(key.keyString, String.valueOf(key.defValue));
		}
	}


	/**
	 * Refresh all gamerules.
	 * @param world World to refresh for.
	 */
	public static void refresh(World world) {
		List<Tuple.Pair<World, Object>> toRemove = new ArrayList<>();
		for(List<Tuple.Pair<World, Object>> values : gameRules.values()) {

			for(Tuple.Pair<World, Object> rulePair : values)
				if(rulePair.key == world)
					toRemove.add(rulePair);

			for(Tuple.Pair<World, Object> pair : toRemove)
				values.remove(pair);

			toRemove.clear();
		}

		gameRules.get(RBMKKeys.KEY_PASSIVE_COOLING).add(new Tuple.Pair<>(world, GameRuleHelper.getDoubleMinimum(world, RBMKKeys.KEY_PASSIVE_COOLING, 0.0D)));
		gameRules.get(RBMKKeys.KEY_COLUMN_HEAT_FLOW).add(new Tuple.Pair<>(world, GameRuleHelper.getClampedDouble(world, RBMKKeys.KEY_COLUMN_HEAT_FLOW, 0.0D, 1.0D)));
		gameRules.get(RBMKKeys.KEY_FUEL_DIFFUSION_MOD).add(new Tuple.Pair<>(world, GameRuleHelper.getDoubleMinimum(world, RBMKKeys.KEY_FUEL_DIFFUSION_MOD, 0.0D)));
		gameRules.get(RBMKKeys.KEY_HEAT_PROVISION).add(new Tuple.Pair<>(world, GameRuleHelper.getClampedDouble(world, RBMKKeys.KEY_HEAT_PROVISION, 0.0D, 1.0D)));
		gameRules.get(RBMKKeys.KEY_COLUMN_HEIGHT).add(new Tuple.Pair<>(world, GameRuleHelper.getClampedInt(world, RBMKKeys.KEY_COLUMN_HEIGHT, 2, 16) - 1));
		gameRules.get(RBMKKeys.KEY_PERMANENT_SCRAP).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_PERMANENT_SCRAP.keyString)));
		gameRules.get(RBMKKeys.KEY_BOILER_HEAT_CONSUMPTION).add(new Tuple.Pair<>(world, GameRuleHelper.getDoubleMinimum(world, RBMKKeys.KEY_BOILER_HEAT_CONSUMPTION, 0D)));
		gameRules.get(RBMKKeys.KEY_CONTROL_SPEED_MOD).add(new Tuple.Pair<>(world, GameRuleHelper.getDoubleMinimum(world, RBMKKeys.KEY_CONTROL_SPEED_MOD, 0.0D)));
		gameRules.get(RBMKKeys.KEY_REACTIVITY_MOD).add(new Tuple.Pair<>(world, GameRuleHelper.getDoubleMinimum(world, RBMKKeys.KEY_REACTIVITY_MOD, 0.0D)));
		gameRules.get(RBMKKeys.KEY_OUTGASSER_MOD).add(new Tuple.Pair<>(world, GameRuleHelper.getDoubleMinimum(world, RBMKKeys.KEY_OUTGASSER_MOD, 0.0D)));
		gameRules.get(RBMKKeys.KEY_SURGE_MOD).add(new Tuple.Pair<>(world, GameRuleHelper.getDoubleMinimum(world, RBMKKeys.KEY_SURGE_MOD, 0.0D)));
		gameRules.get(RBMKKeys.KEY_FLUX_RANGE).add(new Tuple.Pair<>(world, GameRuleHelper.getClampedInt(world, RBMKKeys.KEY_FLUX_RANGE, 1, 100)));
		gameRules.get(RBMKKeys.KEY_REASIM_RANGE).add(new Tuple.Pair<>(world, GameRuleHelper.getClampedInt(world, RBMKKeys.KEY_REASIM_RANGE, 1, 100)));
		gameRules.get(RBMKKeys.KEY_REASIM_COUNT).add(new Tuple.Pair<>(world, GameRuleHelper.getClampedInt(world, RBMKKeys.KEY_REASIM_COUNT, 1, 24)));
		gameRules.get(RBMKKeys.KEY_REASIM_MOD).add(new Tuple.Pair<>(world, GameRuleHelper.getDoubleMinimum(world, RBMKKeys.KEY_REASIM_MOD, 0.0D)));
		gameRules.get(RBMKKeys.KEY_REASIM_BOILERS).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_REASIM_BOILERS.keyString) || (GeneralConfig.enable528 && GeneralConfig.enable528ReasimBoilers)));
		gameRules.get(RBMKKeys.KEY_REASIM_BOILER_SPEED).add(new Tuple.Pair<>(world, GameRuleHelper.getClampedDouble(world, RBMKKeys.KEY_REASIM_BOILER_SPEED, 0.0D, 1.0D)));
		gameRules.get(RBMKKeys.KEY_DISABLE_MELTDOWNS).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_DISABLE_MELTDOWNS.keyString)));
		gameRules.get(RBMKKeys.KEY_ENABLE_MELTDOWN_OVERPRESSURE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_ENABLE_MELTDOWN_OVERPRESSURE.keyString)));
		gameRules.get(RBMKKeys.KEY_MODERATOR_EFFICIENCY).add(new Tuple.Pair<>(world, GameRuleHelper.getClampedDouble(world, RBMKKeys.KEY_MODERATOR_EFFICIENCY, 0.0D, 1.0D)));
		gameRules.get(RBMKKeys.KEY_ABSORBER_EFFICIENCY).add(new Tuple.Pair<>(world, GameRuleHelper.getClampedDouble(world, RBMKKeys.KEY_ABSORBER_EFFICIENCY, 0.0D, 1.0D)));
		gameRules.get(RBMKKeys.KEY_REFLECTOR_EFFICIENCY).add(new Tuple.Pair<>(world, GameRuleHelper.getClampedDouble(world, RBMKKeys.KEY_REFLECTOR_EFFICIENCY, 0.0D, 1.0D)));
		gameRules.get(RBMKKeys.KEY_DISABLE_DEPLETION).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_DISABLE_DEPLETION.keyString)));
		gameRules.get(RBMKKeys.KEY_DISABLE_XENON).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_DISABLE_XENON.keyString)));
		gameRules.get(RBMKKeys.KEY_UNIQUE_ROD).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_UNIQUE_ROD.keyString)));
		gameRules.get(RBMKKeys.KEY_REASIM_COOLANT_BOILERS).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_REASIM_COOLANT_BOILERS.keyString)));
		gameRules.get(RBMKKeys.KEY_RBMK_BABY_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_RBMK_BABY_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_PWR_BABY_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_PWR_BABY_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_RR_BABY_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_RR_BABY_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_ZIRNOX_BABY_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_ZIRNOX_BABY_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_WATZ_BABY_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_WATZ_BABY_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_ITER_BABY_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_ITER_BABY_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_ICF_BABY_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_ICF_BABY_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_DFC_BABY_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_DFC_BABY_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_CRUCIBLE_BABY_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_CRUCIBLE_BABY_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_HIGH_FLUX_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_HIGH_FLUX_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_ALBION_BABY_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_ALBION_BABY_MODE.keyString)));
		gameRules.get(RBMKKeys.KEY_LMSR_MODE).add(new Tuple.Pair<>(world, world.getGameRules().getGameRuleBooleanValue(RBMKKeys.KEY_LMSR_MODE.keyString)));

	}

	/**
	 * Gets a gamerule from the internal cache.
	 * This will not work if called on the client.
	 * @param world World to get the gamerule for.
	 * @param rule Rule to get.
	 * @return The rule in an Object.
	 */
	public static Object getGameRule(World world, RBMKKeys rule) {
		if(world.isRemote) {
			MainRegistry.logger.error("Attempted to grab cached gamerules on client side, returning default value.");
			MainRegistry.logger.error("Gamerule: {}, Default Value: {}.", rule.keyString, rule.defValue.toString());
			return rule.defValue;
		}
		return getGameRule(world, rule, false);
	}

	public static Object getGameRule(World world, RBMKKeys rule, boolean isIteration) {
		List<Tuple.Pair<World, Object>> rulesList = new ArrayList<>();

		for(Tuple.Pair<World, Object> rulePair : gameRules.get(rule)) {
			if(rulePair.key == world) {
				rulesList.add(rulePair);
			}
		}

		if(rulesList.isEmpty()) {
			if(isIteration)
				throw new NullPointerException("Cannot find gamerule for dial " + rule.keyString + " after creation.");
			else {
				world.getGameRules().setOrCreateGameRule(rule.keyString, rule.defValue.toString()); // fuck
				refresh(world);
				return getGameRule(world, rule, true);
			}
		} else if(rulesList.size() > 1)
			// what??? why???
			MainRegistry.logger.warn("Duplicate values for gamerules detected! Found {} rules for gamerule {}", rulesList.size(), rule.keyString);

		return rulesList.get(0).value; // realistically there should only be one of this gamerule that satisfies the filter sooooo...
	}

	/**
	 * Returns the amount of heat per tick removed from components passively
	 * @param world
	 * @return >0
	 */
	public static double getPassiveCooling(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_PASSIVE_COOLING);
	}

	/**
	 * Returns the percentual step size how quickly neighboring component heat equalizes. 1 is instant, 0.5 is in 50% steps, et cetera.
	 * @param world
	 * @return [0;1]
	 */
	public static double getColumnHeatFlow(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_COLUMN_HEAT_FLOW);
	}

	/**
	 * Returns a modifier for fuel rod diffusion, i.e. how quickly the core and hull temperatures equalize.
	 * @param world
	 * @return >0
	 */
	public static double getFuelDiffusionMod(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_FUEL_DIFFUSION_MOD);
	}

	/**
	 * Returns the percentual step size how quickly the fuel hull heat and the component heat equalizes. 1 is instant, 0.5 is in 50% steps, et cetera.
	 * @param world
	 * @return [0;1]
	 */
	public static double getFuelHeatProvision(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_HEAT_PROVISION);
	}

	/**
	 * Simple integer that decides how tall the structure is.
	 * @param world
	 * @return [0;15]
	 */
	public static int getColumnHeight(World world) {
		return (int) getGameRule(world, RBMKKeys.KEY_COLUMN_HEIGHT);
	}

	/**
	 * Whether or not scrap entities despawn on their own or remain alive until picked up.
	 * @param world
	 * @return
	 */
	public static boolean getPermaScrap(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_PERMANENT_SCRAP);
	}

	/**
	 * How many heat units are consumed per mB water used.
	 * @param world
	 * @return >0
	 */
	public static double getBoilerHeatConsumption(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_BOILER_HEAT_CONSUMPTION);
	}

	/**
	 * A multiplier for how quickly the control rods move.
	 * @param world
	 * @return >0
	 */
	public static double getControlSpeed(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_CONTROL_SPEED_MOD);
	}

	/**
	 * A multiplier for how much flux the rods give out.
	 * @param world
	 * @return >0
	 */
	public static double getReactivityMod(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_REACTIVITY_MOD);
	}

	/**
	 * A multiplier for how much flux the rods give out.
	 * @param world
	 * @return >0
	 */
	public static double getOutgasserMod(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_OUTGASSER_MOD);
	}

	/**
	 * A multiplier for how high the power surge goes when inserting control rods.
	 * @param world
	 * @return >0
	 */
	public static double getSurgeMod(World world) {
		if(world.isRemote) { // The control rods use this gamerule for RBMK diag, which happens to be calculated on the client side. whoops!
			return GameRuleHelper.getDoubleMinimum(world, RBMKKeys.KEY_PASSIVE_COOLING, 0.0D);
		}
		return (double) getGameRule(world, RBMKKeys.KEY_SURGE_MOD);
	}

	/**
	 * Simple integer that decides how far the flux of a normal fuel rod reaches.
	 * @param world
	 * @return [1;100]
	 */
	public static int getFluxRange(World world) {
		return (int) getGameRule(world, RBMKKeys.KEY_FLUX_RANGE);
	}

	/**
	 * Simple integer that decides how far the flux of a ReaSim fuel rod reaches.
	 * @param world
	 * @return [1;100]
	 */
	public static int getReaSimRange(World world) {
		return (int) getGameRule(world, RBMKKeys.KEY_REASIM_RANGE);
	}

	/**
	 * Simple integer that decides how many neutrons are created from ReaSim fuel rods.
	 * @param world
	 * @return [1;24]
	 */
	public static int getReaSimCount(World world) {
		return (int) getGameRule(world, RBMKKeys.KEY_REASIM_COUNT);
	}

	/**
	 * Returns a modifier for the outgoing flux of individual streams from the ReaSim fuel rod to compensate for the potentially increased stream count.
	 * @param world
	 * @return >0
	 */
	public static double getReaSimOutputMod(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_REASIM_MOD);
	}

	/**
	 * Whether or not all components should act like boilers with dedicated in/outlet blocks
	 * @param world
	 * @return
	 */
	public static boolean getReasimBoilers(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_REASIM_BOILERS);
	}

	/**
	 * How much % of the possible steam ends up being produced per tick
	 * @param world
	 * @return [0;1]
	 */
	public static double getReaSimBoilerSpeed(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_REASIM_BOILER_SPEED);
	}

	/**
	 * Whether or not fuel columns should initiate a meltdown when overheating
	 * The method is in reverse because the default for older worlds will be 'false'
	 * @param world
	 * @return
	 */
	public static boolean getMeltdownsDisabled(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_DISABLE_MELTDOWNS);
	}

	/**
	 * Whether or not connected pipes and turbines should explode when the reactor undergoes a meltdown.
	 * @param world
	 * @return
	 */
	public static boolean getOverpressure(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_ENABLE_MELTDOWN_OVERPRESSURE);
	}

	/**
	 * The percentage of neutrons to moderate from fast to slow when they pass through a moderator.
	 * @param world
	 * @return
	 */
	public static double getModeratorEfficiency(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_MODERATOR_EFFICIENCY);
	}

	/**
	 * The percentage of neutrons to be absorbed when a stream hits an absorber column.
	 * @param world
	 * @return
	 */
	public static double getAbsorberEfficiency(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_ABSORBER_EFFICIENCY);
	}

	/**
	 * The percentage of neutron to reflect when a stream hits a reflector column.
	 * @param world
	 * @return
	 */
	public static double getReflectorEfficiency(World world) {
		return (double) getGameRule(world, RBMKKeys.KEY_REFLECTOR_EFFICIENCY);
	}
	
	/**
	 * Whether fuel rods should deplete, disabling this makes rods last forever
	 * @param world
	 * @return
	 */
	public static boolean getDepletion(World world) {
		return !((boolean) getGameRule(world, RBMKKeys.KEY_DISABLE_DEPLETION));
	}
	
	/**
	 * Whether xenon poison should be calculated
	 * @param world
	 * @return
	 */
	public static boolean getXenon(World world) {
		return !((boolean) getGameRule(world, RBMKKeys.KEY_DISABLE_XENON));
	}
	
	public static boolean getRodUnique(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_UNIQUE_ROD);
	}

	/**
	 * Whether or not all components should act like boilers with dedicated in/outlet blocks
	 * @param world
	 * @return
	 */
	public static boolean getReasimCoolantBoilers(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_REASIM_COOLANT_BOILERS);
	}

	public static boolean getRBMKBaby(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_RBMK_BABY_MODE);
	}

	public static boolean getPWRBaby(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_PWR_BABY_MODE);
	}

	public static boolean getResearchBaby(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_RR_BABY_MODE);
	}

	public static boolean getZIRNOXBaby(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_ZIRNOX_BABY_MODE);
	}	


	public static boolean getWatzBaby(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_WATZ_BABY_MODE);
	}

	public static boolean getITERBaby(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_ITER_BABY_MODE);
	}

	public static boolean getICFBaby(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_ICF_BABY_MODE);
	}

	public static boolean getDFCBaby(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_DFC_BABY_MODE);
	}
	public static boolean getCrucibleBaby(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_CRUCIBLE_BABY_MODE);
	}
	public static boolean getHighFlux(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_HIGH_FLUX_MODE);
	}
	public static boolean getAlbionBaby(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_ALBION_BABY_MODE);
	}
	public static boolean getLMSR(World world) {
		return (boolean) getGameRule(world, RBMKKeys.KEY_LMSR_MODE);
	}
}
