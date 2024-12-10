package com.greymerk.tweaks.treasure.loot;

import java.util.HashMap;
import java.util.Map;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.util.IWeighted;
import com.greymerk.tweaks.util.WeightedChoice;
import com.greymerk.tweaks.util.WeightedRandomizer;

import net.minecraft.util.math.random.Random;

public enum Quality{
	
	WOOD, STONE, IRON, GOLD, DIAMOND, NETHERITE;
	
	private static Map<Difficulty, IWeighted<Quality>> armourQuality;
	private static Map<Difficulty, IWeighted<Quality>> weaponQuality;
	private static Map<Difficulty, IWeighted<Quality>> toolQuality;
	static {
		armourQuality = new HashMap<Difficulty, IWeighted<Quality>>();
		weaponQuality = new HashMap<Difficulty, IWeighted<Quality>>();
		toolQuality = new HashMap<Difficulty, IWeighted<Quality>>();
		
		armourQuality.put(Difficulty.EASIEST, 	create(120, 60, 40, 10, 2, 1));
		armourQuality.put(Difficulty.EASY, 		create(80, 60, 60, 10, 2, 1));
		armourQuality.put(Difficulty.MEDIUM, 	create(20, 80, 80, 20, 10, 1));
		armourQuality.put(Difficulty.HARD, 		create(20, 30, 100, 10, 10, 1));
		armourQuality.put(Difficulty.HARDEST, 	create(10, 20, 60, 5, 20, 1));
		
		weaponQuality.put(Difficulty.EASIEST, 	create(20, 160, 40, 2, 2, 1));
		weaponQuality.put(Difficulty.EASY, 		create(20, 120, 60, 2, 5, 1));
		weaponQuality.put(Difficulty.MEDIUM, 	create(20, 80, 160, 5, 10, 1));
		weaponQuality.put(Difficulty.HARD, 		create(10, 40, 120, 5, 10, 1));
		weaponQuality.put(Difficulty.HARDEST, 	create(5, 5, 80, 5, 10, 1));
		
		toolQuality.put(Difficulty.EASIEST, 	create(30, 200, 100, 2, 2, 1));
		toolQuality.put(Difficulty.EASY, 		create(20, 120, 200, 2, 10, 1));
		toolQuality.put(Difficulty.MEDIUM, 		create(5, 80, 160, 5, 10, 1));
		toolQuality.put(Difficulty.HARD, 		create(2, 20, 120, 5, 20, 1));
		toolQuality.put(Difficulty.HARDEST, 	create(1, 5, 80, 5, 20, 1));
	}

	private static WeightedRandomizer<Quality> create(int wood, int stone, int iron, int gold, int diamond, int netherite){
		WeightedRandomizer<Quality> randomizer = new WeightedRandomizer<Quality>();
		randomizer.add(new WeightedChoice<Quality>(WOOD, wood));
		randomizer.add(new WeightedChoice<Quality>(STONE, stone));
		randomizer.add(new WeightedChoice<Quality>(IRON, iron));
		randomizer.add(new WeightedChoice<Quality>(GOLD, gold));
		randomizer.add(new WeightedChoice<Quality>(DIAMOND, diamond));
		randomizer.add(new WeightedChoice<Quality>(NETHERITE, netherite));
		return randomizer;
	}

	public static Quality get(Random rand, Difficulty diff, Equipment type) {
		
		switch(type){
		case SWORD:
		case BOW:
			return weaponQuality.get(diff).get(rand);
		case HELMET:
		case CHEST:
		case LEGS:
		case FEET:
			return armourQuality.get(diff).get(rand);
		case PICK:
		case AXE:
		case SHOVEL:
			return toolQuality.get(diff).get(rand);
		}
		return null;
	}
	
	public static Quality get(Difficulty diff){
		switch(diff){
		case EASIEST: return Quality.WOOD;
		case EASY: return Quality.STONE;
		case MEDIUM: return Quality.IRON;
		case HARD: return Quality.GOLD;
		case HARDEST: return Quality.DIAMOND;
		default: return Quality.WOOD;
		}
	}

	public static Quality getArmourQuality(Random rand, Difficulty diff) {
		return armourQuality.get(diff).get(rand);
	}

	public static Quality getToolQuality(Random rand, Difficulty diff) {
		return toolQuality.get(diff).get(rand);
	}

	public static Quality getWeaponQuality(Random rand, Difficulty diff) {
		return weaponQuality.get(diff).get(rand);
	}
	
	
}
