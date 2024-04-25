package com.greymerk.tweaks.treasure.loot;

import java.util.List;

import com.greymerk.tweaks.treasure.loot.provider.ItemArmour;
import com.greymerk.tweaks.treasure.loot.provider.ItemBrewing;
import com.greymerk.tweaks.treasure.loot.provider.ItemEnchBonus;
import com.greymerk.tweaks.treasure.loot.provider.ItemEnchBook;
import com.greymerk.tweaks.treasure.loot.provider.ItemFood;
import com.greymerk.tweaks.treasure.loot.provider.ItemJunk;
import com.greymerk.tweaks.treasure.loot.provider.ItemOre;
import com.greymerk.tweaks.treasure.loot.provider.ItemPotion;
import com.greymerk.tweaks.treasure.loot.provider.ItemRecord;
import com.greymerk.tweaks.treasure.loot.provider.ItemSmithy;
import com.greymerk.tweaks.treasure.loot.provider.ItemSpecialty;
import com.greymerk.tweaks.treasure.loot.provider.ItemSupply;
import com.greymerk.tweaks.treasure.loot.provider.ItemTool;
import com.greymerk.tweaks.treasure.loot.provider.ItemWeapon;
import com.greymerk.tweaks.util.IWeighted;
import com.greymerk.tweaks.util.TextFormat;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;

public enum Loot {
	
	WEAPON, ARMOUR, JUNK, ORE, TOOL, POTION, FOOD, ENCHANTBOOK,
	ENCHANTBONUS, SUPPLY, MUSIC, SMITHY, SPECIAL, REWARD, BREWING;

	public static ILoot getLoot(){
		
		LootProvider loot = new LootProvider();
		for(int i = 0; i < 5; ++i){
			loot.put(i, new LootSettings(i));
		}
		
		return loot;
	}
	
	
	public static IWeighted<ItemStack> getProvider(Loot type, int level){
		switch(type){
		case WEAPON: return new ItemWeapon(0, level);
		case ARMOUR: return new ItemArmour(0, level);
		case JUNK: return new ItemJunk(0, level);
		case ORE: return new ItemOre(0, level);
		case TOOL: return new ItemTool(0, level);
		case POTION: return new ItemPotion(0, level);
		case BREWING: return new ItemBrewing(0, level);
		case FOOD: return new ItemFood(0, level);
		case ENCHANTBOOK: return new ItemEnchBook(0, level);
		case ENCHANTBONUS: return new ItemEnchBonus(0, level);
		case SUPPLY: return new ItemSupply(0, level);
		case MUSIC: return new ItemRecord(0, level);
		case SMITHY: return new ItemSmithy(0, level);
		case SPECIAL: return new ItemSpecialty(0, level);
		case REWARD:
		}
		
		return new WeightedRandomLoot(Items.STICK, 1);
	}
	
	public static ItemStack getEquipmentBySlot(Random rand, EquipmentSlot slot, int level, boolean enchant){
		if(slot == EquipmentSlot.MAINHAND){
			return ItemWeapon.getRandom(rand, level, enchant);
		}
		
		return ItemArmour.getRandom(rand, level, Slot.getSlot(slot), enchant);
	}
	
	public static ItemStack getEquipmentBySlot(Random rand, Slot slot, int level, boolean enchant){
		
		if(slot == Slot.WEAPON){
			return ItemWeapon.getRandom(rand, level, enchant);
		}
		
		return ItemArmour.getRandom(rand, level, slot, enchant);
	}

	public static void setItemLore(ItemStack item, String loreText){
		LoreComponent lore = new LoreComponent(List.of());
		lore.with(Text.of(loreText));
		item.set(DataComponentTypes.LORE, lore);  
	}
	
	public static void setItemLore(ItemStack item, String loreText, TextFormat option){
		setItemLore(item, TextFormat.apply(loreText, option).getString());
	}
	
	public static void setItemName(ItemStack item, String name, TextFormat option){
		
		if(option == null){
			item.set(DataComponentTypes.CUSTOM_NAME, Text.literal(name));
			return;
		}
		
		item.set(DataComponentTypes.CUSTOM_NAME, TextFormat.apply(name, option));
	}
	
	public static void setItemName(ItemStack item, String name){
		setItemName(item, name, null);
	}
}
