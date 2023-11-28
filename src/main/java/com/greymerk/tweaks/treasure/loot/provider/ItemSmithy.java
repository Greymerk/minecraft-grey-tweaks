package com.greymerk.tweaks.treasure.loot.provider;

import net.minecraft.util.math.random.Random;

import com.greymerk.tweaks.treasure.loot.Equipment;
import com.greymerk.tweaks.treasure.loot.Quality;

import net.minecraft.item.ItemStack;

public class ItemSmithy extends ItemBase{

	public ItemSmithy(int weight, int level) {
		super(weight, level);
	}

	@Override
	public ItemStack getLootItem(Random rand, int level) {
		return ItemSpecialty.getRandomItem(Equipment.SWORD, rand, Quality.IRON);
	}
}
