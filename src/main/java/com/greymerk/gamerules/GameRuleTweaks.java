package com.greymerk.gamerules;

import com.greymerk.tweaks.GreyTweaks;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.rule.GameRule;

public class GameRuleTweaks {
	//public static final GameRules.Key<GameRules.BooleanRule> GREY_TWEAK_ALLOW_ELYTRA = GameRuleRegistry.register("allowElytra", Category.MISC, GameRuleFactory.createBooleanRule(true));;
	private static final String id = "allow_elytra";
	
	public static final GameRule<Boolean> GREY_TWEAK_ALLOW_ELYTRA = GameRuleBuilder.forBoolean(false).buildAndRegister(Identifier.of(GreyTweaks.MOD_ID, id)); 
	
	// used to force class loading
	public static void init() {}

}
