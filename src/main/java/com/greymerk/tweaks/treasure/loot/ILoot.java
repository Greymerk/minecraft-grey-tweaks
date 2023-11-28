package com.greymerk.tweaks.treasure.loot;

import com.greymerk.tweaks.util.IWeighted;

import net.minecraft.item.ItemStack;

public interface ILoot {
	
	public IWeighted<ItemStack> get(Loot type, int level);
	
}
