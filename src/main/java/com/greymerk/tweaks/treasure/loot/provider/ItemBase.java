package com.greymerk.tweaks.treasure.loot.provider;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.util.IWeighted;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

public abstract class ItemBase implements IWeighted<ItemStack> {

	private int weight;
	protected Difficulty diff;
	
	public ItemBase(int weight){
		this.weight = weight;
		this.diff = Difficulty.EASIEST;
	}
	
	public ItemBase(int weight, Difficulty diff){
		this.weight = weight;
		this.diff = diff;
	}
	
	public abstract ItemStack getLootItem(RandomSource rand, Difficulty diff);

	@Override
	public int getWeight() {
		return weight;
	}
	
	@Override
	public ItemStack get(RandomSource rand) {
		return getLootItem(rand, diff);
	}
}
