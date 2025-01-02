
package com.greymerk.tweaks.treasure.loot.provider;

import com.greymerk.tweaks.treasure.loot.Enchant;
import com.greymerk.tweaks.treasure.loot.Loot;
import com.greymerk.tweaks.treasure.loot.LootAttribute;
import com.greymerk.tweaks.treasure.loot.trim.Trim;
import com.greymerk.tweaks.treasure.loot.trim.TrimMaterial;
import com.greymerk.tweaks.treasure.loot.trim.TrimPattern;
import com.greymerk.tweaks.util.IWeighted;
import com.greymerk.tweaks.util.TextFormat;
import com.greymerk.tweaks.util.WeightedChoice;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Rarity;

public enum ItemNovelty {

	GREYMERK, NULL, ZISTEAU, AVIDYA, ASHLEA, TRAVELERS, 
	CLEO, WINDFORCE, RLEAHY, VECHS, GENERIKB, FOURLES,
	BURNING, RUNNERS;
		
	public static IWeighted<ItemStack> get(DynamicRegistryManager reg, ItemNovelty choice, int weight){
		return new WeightedChoice<ItemStack>(getItem(reg, choice), weight);
	}
	
	public static ItemStack getItem(DynamicRegistryManager reg, ItemNovelty choice){
		
		ItemStack item;
		
		switch(choice){
		
		case GREYMERK:
			item = new ItemStack(Items.IRON_AXE);
			Loot.setItemName(item, "Greymerk's Hatchet");
			Loot.setItemLore(item, "Pointlessly sharp", TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.SHARPNESS), 3);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.KNOCKBACK), 1);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.UNBREAKING), 2);
			LootAttribute.addAttribute(item, EquipmentSlot.MAINHAND, LootAttribute.ATTACK_SPEED, 1);
			return item;
		case NULL:
			item = new ItemStack(Items.NETHERITE_SWORD);
			Loot.setItemName(item, "Null Pointer");
			Loot.setItemLore(item, "Exceptional", TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.SHARPNESS), 5);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.KNOCKBACK), 2);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.UNBREAKING), 3);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.MENDING), 1);
			return item;
		case ZISTEAU:
			item = new ItemStack(Items.OAK_SIGN);
			Loot.setItemName(item, "Battle Sign");
			Loot.setItemLore(item, "\"That's what you get!\"", TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.SHARPNESS), 5);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.KNOCKBACK), 3);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.FIREASPECT), 1);
			return item;
		case AVIDYA:
			item = new ItemStack(Items.MILK_BUCKET);
			Loot.setItemName(item, "White Russian");
			Loot.setItemLore(item, "The dude's favourite", TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			return item;
		case ASHLEA:
			item = new ItemStack(Items.COOKIE);
			Loot.setItemName(item, "Oatmeal Cookie");
			Loot.setItemLore(item, "Perfect for elevensies", TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			return item;
		case CLEO:
			item = new ItemStack(Items.COD);
			Loot.setItemName(item, "Digging Feesh");
			Loot.setItemLore(item, "Feesh are not efeeshent for digging", TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.EFFICIENCY), 10);
			return item;
		case RLEAHY:
			item = new ItemStack(Items.BREAD);
			Loot.setItemName(item, "Battle Sub");
			Loot.setItemLore(item, "With extra pastrami", TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.SHARPNESS), 1);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.KNOCKBACK), 1);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.FIREASPECT), 1);
			return item;
		case WINDFORCE:
			item = new ItemStack(Items.BOW);
			Loot.setItemName(item, "Windforce");
			Loot.setItemLore(item, "Found on many battlefields", TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.POWER), 5);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.PUNCH), 2);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.UNBREAKING), 3);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.INFINITY), 1);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.MENDING), 1);
			return item;
		case VECHS:
			item = new ItemStack(Items.STICK);
			Loot.setItemName(item, "Legendary Stick");
			Loot.setItemLore(item, "\"Really?!\"",  TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.UNBREAKING), 1);
			return item;
		case GENERIKB:
			item = new ItemStack(Items.BAKED_POTATO);
			Loot.setItemName(item, "Hot Potato");
			Loot.setItemLore(item, "All a hermit needs",  TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.FIREASPECT), 1);
			return item;
		case FOURLES:
			item = new ItemStack(Items.COCOA_BEANS);
			Loot.setItemName(item, "Darkroast Beans");
			Loot.setItemLore(item, "\"Mmmm... Dark Roast\"",  TextFormat.DARKGREEN);
			Loot.setRarity(item, Rarity.EPIC);
			return item;
		case BURNING:
			item = new ItemStack(Items.BOW);
			Loot.setItemName(item, "Burning Bow");
			Loot.setRarity(item, Rarity.EPIC);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.FLAME), 1);
			return item;
		case RUNNERS:
			item = new ItemStack(Items.LEATHER_BOOTS);
			Loot.setItemName(item, "Dungeon Runners");
			Loot.setRarity(item, Rarity.EPIC);
			ItemArmour.dyeArmor(item, 220, 200, 240);
			LootAttribute.addAttribute(item, EquipmentSlot.FEET, LootAttribute.MOVEMENT_SPEED, 0.2);
			Trim.set(reg, item, TrimPattern.COAST, TrimMaterial.LAPIS);
			return item;
		case TRAVELERS:
			item = new ItemStack(Items.LEATHER_BOOTS);
			Loot.setItemName(item, "Farland Travelers");
			Loot.setRarity(item, Rarity.EPIC);
			LootAttribute.addAttribute(item, EquipmentSlot.FEET, LootAttribute.MOVEMENT_SPEED, 0.4);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.PROTECTION), 3);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.FEATHERFALLING), 2);
			item.addEnchantment(Enchant.getEnchant(reg, Enchant.UNBREAKING), 3);
			ItemArmour.dyeArmor(item, 165, 42, 42);
			Trim.set(reg, item, TrimPattern.DUNE, TrimMaterial.GOLD);
			return item;
		default:
			return new ItemStack(Items.STICK);
		}
	}
}
