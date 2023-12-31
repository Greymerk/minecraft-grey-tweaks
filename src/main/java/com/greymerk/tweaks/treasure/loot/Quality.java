package com.greymerk.tweaks.treasure.loot;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.math.random.Random;

import com.greymerk.tweaks.util.IWeighted;
import com.greymerk.tweaks.util.WeightedChoice;
import com.greymerk.tweaks.util.WeightedRandomizer;

public enum Quality{
	
	WOOD, STONE, IRON, GOLD, DIAMOND, NETHERITE;
	
	private static Map<Integer, IWeighted<Quality>> armourQuality;
	private static Map<Integer, IWeighted<Quality>> weaponQuality;
	private static Map<Integer, IWeighted<Quality>> toolQuality;
	static {
		armourQuality = new HashMap<Integer, IWeighted<Quality>>();
		weaponQuality = new HashMap<Integer, IWeighted<Quality>>();
		toolQuality = new HashMap<Integer, IWeighted<Quality>>();
		
		for(int i = 0; i < 5; ++i){
			WeightedRandomizer<Quality> armour = new WeightedRandomizer<Quality>();
			switch(i){
			case 0:
				armour.add(new WeightedChoice<Quality>(WOOD, 100));
				armour.add(new WeightedChoice<Quality>(STONE, 50));
				armour.add(new WeightedChoice<Quality>(IRON, 10));
				armour.add(new WeightedChoice<Quality>(GOLD, 5));
				armour.add(new WeightedChoice<Quality>(DIAMOND, 1));
				armour.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 1:
				armour.add(new WeightedChoice<Quality>(WOOD, 50));
				armour.add(new WeightedChoice<Quality>(STONE, 50));
				armour.add(new WeightedChoice<Quality>(IRON, 80));
				armour.add(new WeightedChoice<Quality>(GOLD, 5));
				armour.add(new WeightedChoice<Quality>(DIAMOND, 1));
				armour.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 2:
				armour.add(new WeightedChoice<Quality>(WOOD, 30));
				armour.add(new WeightedChoice<Quality>(STONE, 30));
				armour.add(new WeightedChoice<Quality>(IRON, 80));
				armour.add(new WeightedChoice<Quality>(GOLD, 10));
				armour.add(new WeightedChoice<Quality>(DIAMOND, 5));
				armour.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 3:
				armour.add(new WeightedChoice<Quality>(WOOD, 5));
				armour.add(new WeightedChoice<Quality>(STONE, 30));
				armour.add(new WeightedChoice<Quality>(IRON, 60));
				armour.add(new WeightedChoice<Quality>(GOLD, 10));
				armour.add(new WeightedChoice<Quality>(DIAMOND, 10));
				armour.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 4:
				armour.add(new WeightedChoice<Quality>(WOOD, 1));
				armour.add(new WeightedChoice<Quality>(STONE, 20));
				armour.add(new WeightedChoice<Quality>(IRON, 40));
				armour.add(new WeightedChoice<Quality>(GOLD, 20));
				armour.add(new WeightedChoice<Quality>(DIAMOND, 10));
				armour.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			}
			armourQuality.put(i, armour);
			
			WeightedRandomizer<Quality> weapon = new WeightedRandomizer<Quality>();
			switch(i){
			case 0:
				weapon.add(new WeightedChoice<Quality>(WOOD, 200));
				weapon.add(new WeightedChoice<Quality>(STONE, 100));
				weapon.add(new WeightedChoice<Quality>(IRON, 50));
				weapon.add(new WeightedChoice<Quality>(GOLD, 5));
				weapon.add(new WeightedChoice<Quality>(DIAMOND, 2));
				weapon.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 1:
				weapon.add(new WeightedChoice<Quality>(WOOD, 50));
				weapon.add(new WeightedChoice<Quality>(STONE, 100));
				weapon.add(new WeightedChoice<Quality>(IRON, 50));
				weapon.add(new WeightedChoice<Quality>(GOLD, 5));
				weapon.add(new WeightedChoice<Quality>(DIAMOND, 2));
				weapon.add(new WeightedChoice<Quality>(NETHERITE, 1));
				
				break;
			case 2:
				weapon.add(new WeightedChoice<Quality>(WOOD, 10));
				weapon.add(new WeightedChoice<Quality>(STONE, 30));
				weapon.add(new WeightedChoice<Quality>(IRON, 80));
				weapon.add(new WeightedChoice<Quality>(GOLD, 5));
				weapon.add(new WeightedChoice<Quality>(DIAMOND, 2));
				weapon.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 3:
				weapon.add(new WeightedChoice<Quality>(WOOD, 1));
				weapon.add(new WeightedChoice<Quality>(STONE, 20));
				weapon.add(new WeightedChoice<Quality>(IRON, 60));
				weapon.add(new WeightedChoice<Quality>(GOLD, 5));
				weapon.add(new WeightedChoice<Quality>(DIAMOND, 5));
				weapon.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 4:
				weapon.add(new WeightedChoice<Quality>(WOOD, 1));
				weapon.add(new WeightedChoice<Quality>(STONE, 5));
				weapon.add(new WeightedChoice<Quality>(IRON, 40));
				weapon.add(new WeightedChoice<Quality>(GOLD, 5));
				weapon.add(new WeightedChoice<Quality>(DIAMOND, 10));
				weapon.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			}
			weaponQuality.put(i, weapon);
			
			WeightedRandomizer<Quality> tool = new WeightedRandomizer<Quality>();
			switch(i){
			case 0:
				tool.add(new WeightedChoice<Quality>(WOOD, 50));
				tool.add(new WeightedChoice<Quality>(STONE, 70));
				tool.add(new WeightedChoice<Quality>(IRON, 30));
				tool.add(new WeightedChoice<Quality>(GOLD, 5));
				tool.add(new WeightedChoice<Quality>(DIAMOND, 1));
				tool.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 1:
				tool.add(new WeightedChoice<Quality>(WOOD, 10));
				tool.add(new WeightedChoice<Quality>(STONE, 80));
				tool.add(new WeightedChoice<Quality>(IRON, 40));
				tool.add(new WeightedChoice<Quality>(GOLD, 5));
				tool.add(new WeightedChoice<Quality>(DIAMOND, 5));
				tool.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 2:
				tool.add(new WeightedChoice<Quality>(WOOD, 5));
				tool.add(new WeightedChoice<Quality>(STONE, 30));
				tool.add(new WeightedChoice<Quality>(IRON, 50));
				tool.add(new WeightedChoice<Quality>(GOLD, 5));
				tool.add(new WeightedChoice<Quality>(DIAMOND, 5));
				tool.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 3:
				tool.add(new WeightedChoice<Quality>(WOOD, 1));
				tool.add(new WeightedChoice<Quality>(STONE, 10));
				tool.add(new WeightedChoice<Quality>(IRON, 40));
				tool.add(new WeightedChoice<Quality>(GOLD, 5));
				tool.add(new WeightedChoice<Quality>(DIAMOND, 5));
				tool.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			case 4:
				tool.add(new WeightedChoice<Quality>(WOOD, 1));
				tool.add(new WeightedChoice<Quality>(STONE, 5));
				tool.add(new WeightedChoice<Quality>(IRON, 30));
				tool.add(new WeightedChoice<Quality>(GOLD, 5));
				tool.add(new WeightedChoice<Quality>(DIAMOND, 5));
				tool.add(new WeightedChoice<Quality>(NETHERITE, 1));
				break;
			}
			toolQuality.put(i, tool);
		}
		
	}

	public static Quality get(Random rand, int level, Equipment type) {
		
		switch(type){
		case SWORD:
		case BOW:
			return weaponQuality.get(level).get(rand);
		case HELMET:
		case CHEST:
		case LEGS:
		case FEET:
			return armourQuality.get(level).get(rand);
		case PICK:
		case AXE:
		case SHOVEL:
			return toolQuality.get(level).get(rand);
		}
		return null;
	}
	
	public static Quality get(int level){
		switch(level){
		case 0: return Quality.WOOD;
		case 1: return Quality.STONE;
		case 2: return Quality.IRON;
		case 3: return Quality.GOLD;
		case 4: return Quality.DIAMOND;
		default: return Quality.WOOD;
		}
	}

	public static Quality getArmourQuality(Random rand, int level) {
		return armourQuality.get(level).get(rand);
	}

	public static Quality getToolQuality(Random rand, int level) {
		return toolQuality.get(level).get(rand);
	}

	public static Quality getWeaponQuality(Random rand, int level) {
		return weaponQuality.get(level).get(rand);
	}
	
	
}
