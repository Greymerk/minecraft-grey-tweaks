package com.greymerk.tweaks.treasure.loot.rules;

import java.util.List;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.treasure.Treasure;
import com.greymerk.tweaks.treasure.loot.Equipment;
import com.greymerk.tweaks.treasure.loot.ILoot;
import com.greymerk.tweaks.treasure.loot.Loot;
import com.greymerk.tweaks.treasure.loot.Quality;
import com.greymerk.tweaks.treasure.loot.provider.ItemSpecialty;

public class RoguelikeLootRules {

	public static LootRuleManager getLoot(IWorldEditor editor) {
		
		LootRuleManager lootRules = new LootRuleManager();
		ILoot loot = Loot.getLoot(editor);
		lootRules.add(Treasure.STARTER, loot.get(Loot.WEAPON, Difficulty.EASIEST),  Difficulty.EASIEST, 2);
		lootRules.add(Treasure.STARTER, loot.get(Loot.FOOD, Difficulty.EASIEST),  Difficulty.EASIEST, 2);
		lootRules.add(Treasure.STARTER, loot.get(Loot.TOOL, Difficulty.EASIEST),  Difficulty.EASIEST, 2);
		lootRules.add(Treasure.STARTER, loot.get(Loot.SUPPLY, Difficulty.EASIEST),  Difficulty.EASIEST, 2);
		lootRules.add(Treasure.STARTER, new ItemSpecialty(editor.getRegistryManager(), 0, Difficulty.EASIEST, Equipment.LEGS, Quality.WOOD), Difficulty.EASIEST, 2);
		List.of(Difficulty.values()).forEach(difficulty -> {
			lootRules.add(Treasure.ARMOUR, loot.get(Loot.POTION, difficulty),  difficulty, 1);
			lootRules.add(Treasure.ARMOUR, loot.get(Loot.ARMOUR, difficulty),  difficulty, 1);
			lootRules.add(Treasure.ARMOUR, loot.get(Loot.FOOD, difficulty),  difficulty, 1);
			lootRules.add(Treasure.ARMOUR, loot.get(Loot.BLOCK, difficulty),  difficulty, 1);
			lootRules.add(Treasure.WEAPONS, loot.get(Loot.POTION, difficulty),  difficulty, 1);
			lootRules.add(Treasure.WEAPONS, loot.get(Loot.WEAPON, difficulty),  difficulty, 1);
			lootRules.add(Treasure.WEAPONS, loot.get(Loot.FOOD, difficulty),  difficulty, 1);
			lootRules.add(Treasure.WEAPONS, loot.get(Loot.BLOCK, difficulty),  difficulty, 1);
			lootRules.add(Treasure.BLOCKS, loot.get(Loot.BLOCK, difficulty),  difficulty, 8);
			lootRules.add(Treasure.ENCHANTING, loot.get(Loot.ENCHANTING, difficulty),  difficulty, 6);
			lootRules.add(Treasure.FOOD, loot.get(Loot.FOOD, difficulty),  difficulty, 4);
			lootRules.add(Treasure.ORE, loot.get(Loot.ORE, difficulty),  difficulty, 5);
			lootRules.add(Treasure.ORE, loot.get(Loot.TOOL, difficulty),  difficulty, 1);
			lootRules.add(Treasure.POTIONS, loot.get(Loot.POTION, difficulty),  difficulty, 6);
			lootRules.add(Treasure.BREWING, loot.get(Loot.BREWING, difficulty),  difficulty, 8);
			lootRules.add(Treasure.TOOLS, loot.get(Loot.ORE, difficulty),  difficulty, 3);
			lootRules.add(Treasure.TOOLS, loot.get(Loot.TOOL, difficulty),  difficulty, 1);
			lootRules.add(Treasure.TOOLS, loot.get(Loot.BLOCK, difficulty),  difficulty, 2);
			lootRules.add(Treasure.SUPPLIES, loot.get(Loot.SUPPLY, difficulty),  difficulty, 4);
			lootRules.add(Treasure.SUPPLIES, loot.get(Loot.BLOCK, difficulty),  difficulty, 2);
			lootRules.add(Treasure.SUPPLIES, loot.get(Loot.FOOD, difficulty),  difficulty, 2);
			lootRules.add(Treasure.SUPPLIES, loot.get(Loot.ORE, difficulty),  difficulty, 1);
			lootRules.add(Treasure.SUPPLIES, loot.get(Loot.BREWING, difficulty),  difficulty, 1);
			lootRules.add(Treasure.MUSIC, loot.get(Loot.MUSIC, difficulty),  difficulty, 1);
			lootRules.add(Treasure.ALL, loot.get(Loot.JUNK, difficulty),  difficulty, 6);
			lootRules.add(Treasure.ALL, loot.get(Loot.PRECIOUS, difficulty),  difficulty, 1);
		});
		
		return lootRules;
	}
	
}
