package com.greymerk.tweaks.treasure.loot;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public enum LootAttribute {

	ARMOR, ARMOR_TOUGHNESS,
	ATTACK_DAMAGE, ATTACK_KNOCKBACK, ATTACK_SPEED,
	FLYING_SPEED, FOLLOW_RANGE, KNOCKBACK_RESISTANCE, LUCK,
	MAX_ABSORPTION, MAX_HEALTH, MOVEMENT_SPEED, SCALE, STEP_HEIGHT,
	JUMP_STRENGTH, BLOCK_INTERACTION_RANGE, ENTITY_INTERACTION_RANGE,
	SPAWN_REINFORCEMENTS, BLOCK_BREAK_SPEED, GRAVITY, SAFE_FALL_DISTANCE,
	FALL_DAMAGE_MULTIPLIER, BURNING_TIME, EXPLOSION_KNOCKBACK_RESISTANCE,
	MINING_EFFICIENCY, MOVEMENT_EFFICIENCY, OXYGEN_BONUS, SNEAKING_SPEED,
	SUBMERGED_MINING_SPEED, SWEEPING_DAMAGE_RATIO, TEMPT_RANGE, WATER_MOVEMENT_EFFICIENCY;
	
	public static String getID(LootAttribute modifier) {
		switch(modifier) {
		case ARMOR: return "armor";
		case ARMOR_TOUGHNESS: return "armor_toughness";
		case ATTACK_DAMAGE: return "attack_damage";
		case ATTACK_KNOCKBACK: return "attack_knockback";
		case ATTACK_SPEED: return "attack_speed";
		case BLOCK_BREAK_SPEED: return "block_break_speed";
		case BLOCK_INTERACTION_RANGE: return "block_interaction_range";
		case BURNING_TIME: return "burning_time";
		case ENTITY_INTERACTION_RANGE: return "entity_interaction_range";
		case EXPLOSION_KNOCKBACK_RESISTANCE: return "explosion_knockback_resistance";
		case FALL_DAMAGE_MULTIPLIER: return "fall_damage_multiplier";
		case FLYING_SPEED: return "flying_speed";
		case FOLLOW_RANGE: return "follow_range";
		case GRAVITY: return "gravity";
		case JUMP_STRENGTH: return "jump_strength";
		case KNOCKBACK_RESISTANCE: return "knockback_resistance";
		case LUCK: return "luck";
		case MAX_ABSORPTION: return "max_absorption";
		case MAX_HEALTH: return "max_health";
		case MINING_EFFICIENCY: return "mining_efficiency";
		case MOVEMENT_EFFICIENCY: return "movement_efficiency";
		case MOVEMENT_SPEED: return "movement_speed";
		case OXYGEN_BONUS: return "oxygen_bonus";
		case SAFE_FALL_DISTANCE: return "safe_fall_distance";
		case SCALE: return "scale";
		case SNEAKING_SPEED: return "sneaking_speed";
		case SPAWN_REINFORCEMENTS: return "spawn_reinforcements";
		case STEP_HEIGHT: return "step_height";
		case SUBMERGED_MINING_SPEED: return "submerged_mining_speed";
		case SWEEPING_DAMAGE_RATIO: return "sweeping_damage_ratio";
		case TEMPT_RANGE: return "tempt_range";
		case WATER_MOVEMENT_EFFICIENCY: return "water_movement_efficiency";
		default: return "movement_speed";
		}
	}
	
	public static RegistryEntry<EntityAttribute> getEntry(LootAttribute modifier) {
		switch(modifier) {
		case ARMOR: return EntityAttributes.ARMOR;
		case ARMOR_TOUGHNESS: return EntityAttributes.ARMOR_TOUGHNESS;
		case ATTACK_DAMAGE: return EntityAttributes.ATTACK_DAMAGE;
		case ATTACK_KNOCKBACK: return EntityAttributes.ATTACK_KNOCKBACK;
		case ATTACK_SPEED: return EntityAttributes.ATTACK_SPEED;
		case BLOCK_BREAK_SPEED: return EntityAttributes.BLOCK_BREAK_SPEED;
		case BLOCK_INTERACTION_RANGE: return EntityAttributes.BLOCK_INTERACTION_RANGE;
		case BURNING_TIME: return EntityAttributes.BURNING_TIME;
		case ENTITY_INTERACTION_RANGE: return EntityAttributes.ENTITY_INTERACTION_RANGE;
		case EXPLOSION_KNOCKBACK_RESISTANCE: return EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE;
		case FALL_DAMAGE_MULTIPLIER: return EntityAttributes.FALL_DAMAGE_MULTIPLIER;
		case FLYING_SPEED: return EntityAttributes.FLYING_SPEED;
		case FOLLOW_RANGE: return EntityAttributes.FOLLOW_RANGE;
		case GRAVITY: return EntityAttributes.GRAVITY;
		case JUMP_STRENGTH: return EntityAttributes.JUMP_STRENGTH;
		case KNOCKBACK_RESISTANCE: return EntityAttributes.KNOCKBACK_RESISTANCE;
		case LUCK: return EntityAttributes.LUCK;
		case MAX_ABSORPTION: return EntityAttributes.MAX_ABSORPTION;
		case MAX_HEALTH: return EntityAttributes.MAX_HEALTH;
		case MINING_EFFICIENCY: return EntityAttributes.MINING_EFFICIENCY;
		case MOVEMENT_EFFICIENCY: return EntityAttributes.MOVEMENT_EFFICIENCY;
		case MOVEMENT_SPEED: return EntityAttributes.MOVEMENT_SPEED;
		case OXYGEN_BONUS: return EntityAttributes.OXYGEN_BONUS;
		case SAFE_FALL_DISTANCE: return EntityAttributes.SAFE_FALL_DISTANCE;
		case SCALE: return EntityAttributes.SCALE;
		case SNEAKING_SPEED: return EntityAttributes.SNEAKING_SPEED;
		case SPAWN_REINFORCEMENTS: return EntityAttributes.SPAWN_REINFORCEMENTS;
		case STEP_HEIGHT: return EntityAttributes.STEP_HEIGHT;
		case SUBMERGED_MINING_SPEED: return EntityAttributes.SUBMERGED_MINING_SPEED;
		case SWEEPING_DAMAGE_RATIO: return EntityAttributes.SWEEPING_DAMAGE_RATIO;
		case TEMPT_RANGE: return EntityAttributes.TEMPT_RANGE;
		case WATER_MOVEMENT_EFFICIENCY: return EntityAttributes.WATER_MOVEMENT_EFFICIENCY;
		default: return EntityAttributes.MOVEMENT_SPEED;
		}
	}
	
	public static ItemStack addAttribute(ItemStack item, EquipmentSlot slot, LootAttribute modifier, double value) {
		Identifier id = Identifier.ofVanilla(getID(modifier));
		RegistryEntry<EntityAttribute> entry = getEntry(modifier);
		AttributeModifierSlot slotModifier = AttributeModifierSlot.forEquipmentSlot(slot);
		EntityAttributeModifier entityModifier = new EntityAttributeModifier(id, value, Operation.ADD_MULTIPLIED_BASE);
		AttributeModifiersComponent components = item.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
		item.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, components.with(entry, entityModifier, slotModifier));
		return item;
	}
}
