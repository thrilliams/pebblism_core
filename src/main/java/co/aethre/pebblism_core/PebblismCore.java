package co.aethre.pebblism_core;

import eu.midnightdust.lib.config.MidnightConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class PebblismCore implements ModInitializer {
	public static final String ID = "pebblism_core";
	public static final String NAME = "Pebblism Core";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public static final GameRules.Key<GameRules.BooleanValue> RULE_DO_MOB_EXPLOSION_BLOCK_DAMAGE =
		GameRuleRegistry.register(
				"doMobExplosionBlockDamage",
				GameRules.Category.MOBS,
				GameRuleFactory.createBooleanRule(true)
		);

	@Override
	public void onInitialize() {
		MidnightConfig.init(ID, PebblismCoreConfig.class);
	}
}
