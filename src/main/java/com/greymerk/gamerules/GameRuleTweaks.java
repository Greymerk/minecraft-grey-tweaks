package com.greymerk.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.Category;

public class GameRuleTweaks {
	public static final GameRules.Key<GameRules.BooleanRule> GREY_TWEAK_ALLOW_ELYTRA = GameRuleRegistry.register("allowElytra", Category.MISC, GameRuleFactory.createBooleanRule(true));;
	
	// used to force class loading
	public static void init() {}

}
