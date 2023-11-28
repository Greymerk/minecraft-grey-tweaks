package com.greymerk.tweaks.treasure.loot.provider;

import net.minecraft.util.math.random.Random;

import com.greymerk.tweaks.treasure.loot.MusicDisk;

import net.minecraft.item.ItemStack;

public class ItemRecord extends ItemBase{

	public ItemRecord(int weight, int level) {
		super(weight, level);
	}

	@Override
	public ItemStack getLootItem(Random rand, int level) {
		return MusicDisk.getRandomRecord(rand);
	}
	
	

}
