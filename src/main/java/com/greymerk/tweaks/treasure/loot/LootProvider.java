package com.greymerk.tweaks.treasure.loot;

import java.util.HashMap;
import java.util.Map;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.util.IWeighted;

import net.minecraft.item.ItemStack;

public class LootProvider implements ILoot {

	Map<Difficulty, LootSettings> loot;
	
	public LootProvider(){
		loot = new HashMap<Difficulty, LootSettings>();
	}
	
	public void put(Difficulty diff, LootSettings settings){
		loot.put(diff, settings);
	}
	
	@Override
	public IWeighted<ItemStack> get(Loot type, Difficulty diff){
		return loot.get(diff).get(type);
	}
}
