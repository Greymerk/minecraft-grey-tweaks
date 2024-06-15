package com.greymerk.tweaks.treasure.loot.rules;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.treasure.Treasure;
import com.greymerk.tweaks.treasure.TreasureManager;
import com.greymerk.tweaks.treasure.chest.ITreasureChest;
import com.greymerk.tweaks.util.IWeighted;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

public class LootRule {

	private Treasure type;
	private IWeighted<ItemStack> item;
	Difficulty diff;
	int amount;
	
	public LootRule(Treasure type, IWeighted<ItemStack> item, Difficulty diff, int amount){
		this.type = type;
		this.item = item;
		this.diff = diff;
		this.amount = amount;
	}
	
	public void process(Random rand, TreasureManager treasure){
		treasure.addItemToAll(rand, type, diff, item, amount);	
	}

	public void process(Random rand, ITreasureChest chest) {
		if(chest.getLevel() != this.diff) return;
		if(this.type == Treasure.ALL) addItems(rand, chest);
		if(this.type == chest.getType()) addItems(rand, chest);
	}
	
	public void addItems(Random rand, ITreasureChest chest) {
		for(int i = 0; i < amount; ++i) {
			chest.setRandomEmptySlot(item.get(rand));
		}
	}
	
	
	
	@Override
	public String toString(){
		
		String type = this.type.toString();
		int amount = this.amount;
		
		return "type: " + type + " diff: " + this.diff + " amount: " + amount;
	}
}
