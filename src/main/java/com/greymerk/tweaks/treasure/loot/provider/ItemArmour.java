package com.greymerk.tweaks.treasure.loot.provider;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.treasure.loot.Enchant;
import com.greymerk.tweaks.treasure.loot.Equipment;
import com.greymerk.tweaks.treasure.loot.Quality;
import com.greymerk.tweaks.treasure.loot.Slot;
import com.greymerk.tweaks.treasure.loot.trim.Trim;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ItemArmour extends ItemBase {

	private RegistryAccess registry;
	private FeatureFlagSet features;
	
	public ItemArmour(int weight, Difficulty diff, FeatureFlagSet features, RegistryAccess reg) {
		super(weight, diff);
		this.features = features;
		this.registry = reg;
	}	
	
	@Override
	public ItemStack getLootItem(RandomSource rand, Difficulty diff) {
		return getRandom(this.features, this.registry, rand, diff, true);
	}

	public static ItemStack getRandom(FeatureFlagSet features, RegistryAccess reg, RandomSource rand, Difficulty diff, boolean enchant){
		ItemStack item = getRandom(features, reg, rand, diff,
				Slot.getSlotByNumber(rand.nextInt(4) + 1),
				enchant);
		return item;
	}
	
	public static ItemStack getRandom(FeatureFlagSet features, RegistryAccess reg, RandomSource rand, Difficulty diff, Slot slot, boolean enchant){
		if(enchant && rand.nextInt(20 + (Difficulty.value(diff) * 10)) == 0){
			switch(slot){
			case HEAD: return ItemSpecialty.getRandomItem(reg, Equipment.HELMET, rand, diff); 
			case CHEST: return ItemSpecialty.getRandomItem(reg, Equipment.CHEST, rand, diff); 
			case LEGS: return ItemSpecialty.getRandomItem(reg, Equipment.LEGS, rand, diff); 
			case FEET: return ItemSpecialty.getRandomItem(reg, Equipment.FEET, rand, diff);
			default: return new ItemStack(Items.STICK);
			}
		}
		
		ItemStack item = get(rand, slot, Quality.getArmourQuality(rand, diff));
		if(enchant) Enchant.enchantItem(reg, features, rand, item, Enchant.getLevel(rand, diff));
		Trim.addRandom(reg, item, rand);
		return item;
	}
	
	public static ItemStack get(RandomSource rand, Slot slot, Quality quality) {
		
		switch(slot){
		
		case HEAD:
			switch (quality) {
			case NETHERITE: return new ItemStack(Items.NETHERITE_HELMET);
			case DIAMOND: return new ItemStack(Items.DIAMOND_HELMET);
			case GOLD: return new ItemStack(Items.GOLDEN_HELMET);
			case IRON: return new ItemStack(Items.IRON_HELMET);
			case COPPER: return new ItemStack(Items.COPPER_HELMET);
			case STONE: return new ItemStack(Items.CHAINMAIL_HELMET);
			default:
				ItemStack item = new ItemStack(Items.LEATHER_HELMET);
				dyeArmor(item, rand.nextInt(256), rand.nextInt(255), rand.nextInt(255));
				return item;
			}
		
		case FEET:
			switch (quality) {

			case NETHERITE: return new ItemStack(Items.NETHERITE_BOOTS);
			case DIAMOND: return new ItemStack(Items.DIAMOND_BOOTS);
			case GOLD: return new ItemStack(Items.GOLDEN_BOOTS);
			case IRON: return new ItemStack(Items.IRON_BOOTS);
			case COPPER: return new ItemStack(Items.COPPER_BOOTS);
			case STONE: return new ItemStack(Items.CHAINMAIL_BOOTS);
			default:
				ItemStack item = new ItemStack(Items.LEATHER_BOOTS);
				dyeArmor(item, rand.nextInt(256), rand.nextInt(255), rand.nextInt(255));
				return item;
			}
		
		case CHEST:
			switch (quality) {

			case NETHERITE: return new ItemStack(Items.NETHERITE_CHESTPLATE);
			case DIAMOND: return new ItemStack(Items.DIAMOND_CHESTPLATE);
			case GOLD: return new ItemStack(Items.GOLDEN_CHESTPLATE);
			case IRON: return new ItemStack(Items.IRON_CHESTPLATE);
			case COPPER: return new ItemStack(Items.COPPER_CHESTPLATE);
			case STONE: return new ItemStack(Items.CHAINMAIL_CHESTPLATE);
			default:
				ItemStack item = new ItemStack(Items.LEATHER_CHESTPLATE);
				dyeArmor(item, rand.nextInt(256), rand.nextInt(255), rand.nextInt(255));
				return item;
			}
		case LEGS:
			switch (quality) {

			case NETHERITE: return new ItemStack(Items.NETHERITE_LEGGINGS);
			case DIAMOND: return new ItemStack(Items.DIAMOND_LEGGINGS);
			case GOLD: return new ItemStack(Items.GOLDEN_LEGGINGS);
			case IRON: return new ItemStack(Items.IRON_LEGGINGS);
			case COPPER: return new ItemStack(Items.COPPER_LEGGINGS);
			case STONE: return new ItemStack(Items.CHAINMAIL_LEGGINGS);
			default:
				ItemStack item = new ItemStack(Items.LEATHER_LEGGINGS);
				dyeArmor(item, rand.nextInt(256), rand.nextInt(255), rand.nextInt(255));
				return item;
			}
		default: return new ItemStack(Items.LEATHER_CHESTPLATE);
		}
	}

	
	public static ItemStack dyeArmor(ItemStack armor, int r, int g, int b){
		
		int color = r << 16 | g << 8 | b << 0;;
        
		DyedItemColor dye = new DyedItemColor(color);
		armor.set(DataComponents.DYED_COLOR, dye);
		return armor;
	}
	
	public static ItemStack addSpeedAttribute(ItemStack item, double speed) {
		ItemAttributeModifiers modifiers = createAttributeModifiers(speed);
		item.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);		
		return item;
	}

	public static ItemAttributeModifiers createAttributeModifiers(double speed) {
		final Identifier SPEED_MODIFIER_ID = Identifier.withDefaultNamespace("movement_speed");
		AttributeModifier mod = new AttributeModifier(SPEED_MODIFIER_ID, speed, AttributeModifier.Operation.ADD_VALUE);
		
		return ItemAttributeModifiers.builder()
			.add(Attributes.MOVEMENT_SPEED, mod, EquipmentSlotGroup.FEET)
			.build();
	}

}
