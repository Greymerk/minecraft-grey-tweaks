package com.greymerk.tweaks.treasure.loot;


import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

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
	
	public static Holder<Attribute> getEntry(LootAttribute modifier) {
		switch(modifier) {
		case ARMOR: return Attributes.ARMOR;
		case ARMOR_TOUGHNESS: return Attributes.ARMOR_TOUGHNESS;
		case ATTACK_DAMAGE: return Attributes.ATTACK_DAMAGE;
		case ATTACK_KNOCKBACK: return Attributes.ATTACK_KNOCKBACK;
		case ATTACK_SPEED: return Attributes.ATTACK_SPEED;
		case BLOCK_BREAK_SPEED: return Attributes.BLOCK_BREAK_SPEED;
		case BLOCK_INTERACTION_RANGE: return Attributes.BLOCK_INTERACTION_RANGE;
		case BURNING_TIME: return Attributes.BURNING_TIME;
		case ENTITY_INTERACTION_RANGE: return Attributes.ENTITY_INTERACTION_RANGE;
		case EXPLOSION_KNOCKBACK_RESISTANCE: return Attributes.EXPLOSION_KNOCKBACK_RESISTANCE;
		case FALL_DAMAGE_MULTIPLIER: return Attributes.FALL_DAMAGE_MULTIPLIER;
		case FLYING_SPEED: return Attributes.FLYING_SPEED;
		case FOLLOW_RANGE: return Attributes.FOLLOW_RANGE;
		case GRAVITY: return Attributes.GRAVITY;
		case JUMP_STRENGTH: return Attributes.JUMP_STRENGTH;
		case KNOCKBACK_RESISTANCE: return Attributes.KNOCKBACK_RESISTANCE;
		case LUCK: return Attributes.LUCK;
		case MAX_ABSORPTION: return Attributes.MAX_ABSORPTION;
		case MAX_HEALTH: return Attributes.MAX_HEALTH;
		case MINING_EFFICIENCY: return Attributes.MINING_EFFICIENCY;
		case MOVEMENT_EFFICIENCY: return Attributes.MOVEMENT_EFFICIENCY;
		case MOVEMENT_SPEED: return Attributes.MOVEMENT_SPEED;
		case OXYGEN_BONUS: return Attributes.OXYGEN_BONUS;
		case SAFE_FALL_DISTANCE: return Attributes.SAFE_FALL_DISTANCE;
		case SCALE: return Attributes.SCALE;
		case SNEAKING_SPEED: return Attributes.SNEAKING_SPEED;
		case SPAWN_REINFORCEMENTS: return Attributes.SPAWN_REINFORCEMENTS_CHANCE;
		case STEP_HEIGHT: return Attributes.STEP_HEIGHT;
		case SUBMERGED_MINING_SPEED: return Attributes.SUBMERGED_MINING_SPEED;
		case SWEEPING_DAMAGE_RATIO: return Attributes.SWEEPING_DAMAGE_RATIO;
		case TEMPT_RANGE: return Attributes.TEMPT_RANGE;
		case WATER_MOVEMENT_EFFICIENCY: return Attributes.WATER_MOVEMENT_EFFICIENCY;
		default: return Attributes.MOVEMENT_SPEED;
		}
	}
	
	public static ItemStack addAttribute(ItemStack item, EquipmentSlot slot, LootAttribute modifier, double value) {
		Identifier id = Identifier.withDefaultNamespace(getID(modifier));
		Holder<Attribute> entry = getEntry(modifier);
		
		EquipmentSlotGroup slotModifier = EquipmentSlotGroup.bySlot(slot);
		AttributeModifier entityModifier = new AttributeModifier(id, value, Operation.ADD_MULTIPLIED_BASE);
		ItemAttributeModifiers components = item.get(DataComponents.ATTRIBUTE_MODIFIERS);
		ItemAttributeModifiers toAdd = components.withModifierAdded(entry, entityModifier, slotModifier);
		item.set(DataComponents.ATTRIBUTE_MODIFIERS, toAdd);
		return item;
	}
}
