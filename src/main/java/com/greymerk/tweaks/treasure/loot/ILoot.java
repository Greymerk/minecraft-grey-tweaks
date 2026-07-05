package com.greymerk.tweaks.treasure.loot;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.util.IWeighted;

import net.minecraft.world.item.ItemStack;



public interface ILoot {
	
	public IWeighted<ItemStack> get(Loot type, Difficulty diff);
	
}
