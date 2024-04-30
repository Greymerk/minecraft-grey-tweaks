package com.greymerk.tweaks.treasure.loot;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public enum PotionEffect {
	
	SPEED(1), SLOWNESS(2), HASTE(3), FATIGUE(4), STRENGTH(5), HEALTH(6), DAMAGE(7), JUMP(8), 
	NAUSIA(9), REGEN(10), RESISTANCE(11), FIRERESIST(12), WATERBREATH(13), INVISIBILITY(14),
	BLINDNESS(15), NIGHTVISION(16), HUNGER(17), WEAKNESS(18), POISON(19), WITHER(20),
	HEALTHBOOST(21), ABSORPTION(22), SATURATION(23),
	GLOWING(24), LEVITATION(25), LUCK(26), BAD_LUCK(27);
	
	public static int TICKS_PER_SECOND = 20;
	
	public int id;
	PotionEffect(int id){
		this.id = id;
	}
	
	public static String getEffectID(PotionEffect type){
		switch(type) {
		case ABSORPTION: return "absorption";
		case BAD_LUCK: return "unluck";
		case BLINDNESS: return "blindness";
		case DAMAGE: return "instant_damage";
		case FATIGUE: return "mining_fatigue";
		case FIRERESIST: return "fire_resistance";
		case GLOWING: return "glowing";
		case HASTE: return "haste";
		case HEALTH: return "instant_health";
		case HEALTHBOOST: return "health_boost";
		case HUNGER: return "hunger";
		case INVISIBILITY: return "invisibility";
		case JUMP: return "jump_boost";
		case LEVITATION: return "levitation";
		case LUCK: return "luck";
		case NAUSIA: return "nausia";
		case NIGHTVISION: return "night_vision";
		case POISON: return "poison";
		case REGEN: return "regeneration";
		case RESISTANCE: return "resistance";
		case SATURATION: return "saturation";
		case SLOWNESS: return "slowness";
		case SPEED: return "speed";
		case STRENGTH: return "strength";
		case WATERBREATH: return "water_breathing";
		case WEAKNESS: return "weakness";
		case WITHER: return "wither";
		default: return "weakness";	
		}
	}
	
	public static StatusEffect getStatusEffect(PotionEffect type) {
		switch(type) {
		case ABSORPTION: return StatusEffects.ABSORPTION.value();
		case BAD_LUCK: return StatusEffects.UNLUCK.value();
		case BLINDNESS: return StatusEffects.BLINDNESS.value();
		case DAMAGE: return StatusEffects.INSTANT_DAMAGE.value();
		case FATIGUE: return StatusEffects.MINING_FATIGUE.value();
		case FIRERESIST: return StatusEffects.FIRE_RESISTANCE.value();
		case GLOWING: return StatusEffects.GLOWING.value();
		case HASTE: return StatusEffects.HASTE.value();
		case HEALTH: return StatusEffects.INSTANT_HEALTH.value();
		case HEALTHBOOST: return StatusEffects.HEALTH_BOOST.value();
		case HUNGER: return StatusEffects.HUNGER.value();
		case INVISIBILITY: return StatusEffects.INVISIBILITY.value();
		case JUMP: return StatusEffects.JUMP_BOOST.value();
		case LEVITATION: return StatusEffects.LEVITATION.value();
		case LUCK: return StatusEffects.LUCK.value();
		case NAUSIA: return StatusEffects.NAUSEA.value();
		case NIGHTVISION: return StatusEffects.NIGHT_VISION.value();
		case POISON: return StatusEffects.POISON.value();
		case REGEN: return StatusEffects.REGENERATION.value();
		case RESISTANCE: return StatusEffects.RESISTANCE.value();
		case SATURATION: return StatusEffects.SATURATION.value();
		case SLOWNESS: return StatusEffects.SLOWNESS.value();
		case SPEED: return StatusEffects.SPEED.value();
		case STRENGTH: return StatusEffects.STRENGTH.value();
		case WATERBREATH: return StatusEffects.WATER_BREATHING.value();
		case WEAKNESS: return StatusEffects.WEAKNESS.value();
		case WITHER: return StatusEffects.WITHER.value();
		default: return StatusEffects.WEAKNESS.value();
		}
	}
	
	public static void addCustomEffect(ItemStack potion, PotionEffect type, int amplifier, int duration){
		
		potion.apply(DataComponentTypes.POTION_CONTENTS, null, null);
	}
}
