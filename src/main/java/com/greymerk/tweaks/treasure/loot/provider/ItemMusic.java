package com.greymerk.tweaks.treasure.loot.provider;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.treasure.loot.items.MusicDisk;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

public class ItemMusic extends ItemBase{

	public ItemMusic(int weight, Difficulty diff) {
		super(weight, diff);
	}

	@Override
	public ItemStack getLootItem(Random rand, Difficulty diff) {
		return MusicDisk.getRandomRecord(rand);
	}
	
	

}
