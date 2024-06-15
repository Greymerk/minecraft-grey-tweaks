package com.greymerk.tweaks.treasure.loot;

import java.util.ArrayList;
import java.util.List;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.treasure.Treasure;
import com.greymerk.tweaks.treasure.TreasureManager;
import com.greymerk.tweaks.treasure.chest.ITreasureChest;

import net.minecraft.util.Identifier;

public class LootTableRule {

	private Identifier table;
	private List<Treasure> type;
	List<Difficulty> diff;
	
	public void process(TreasureManager treasure){
		List<ITreasureChest> chests = getMatching(treasure);
		for(ITreasureChest chest : chests){
			if(chest.getType() != Treasure.EMPTY) chest.setLootTable(table);
		}
	}
	
	private List<ITreasureChest> getMatching(TreasureManager treasure){
		if(this.type == null && this.diff == null) return treasure.getChests();
		
		List<ITreasureChest> chests = new ArrayList<ITreasureChest>();
		if(this.type == null){
			for(Difficulty d : this.diff){
				chests.addAll(treasure.getChests(d));
			}
		}
		
		if(this.diff == null){
			for(Treasure type : this.type){
				chests.addAll(treasure.getChests(type));
			}
		}
		
		return chests;
	}
}
