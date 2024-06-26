package com.greymerk.tweaks.treasure.loot.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.treasure.loot.WeightedRandomLoot;
import com.greymerk.tweaks.util.WeightedRandomizer;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;

public class ItemPrecious extends ItemBase{
	
	private Map<Difficulty, WeightedRandomizer<ItemStack>> loot;

	
	public ItemPrecious(int weight, Difficulty diff) {
		super(weight, diff);
		
		this.loot = new HashMap<Difficulty, WeightedRandomizer<ItemStack>>();
		
		List.of(Difficulty.values()).forEach(d -> {
			WeightedRandomizer<ItemStack> randomizer = new WeightedRandomizer<ItemStack>();
			loot.put(d, randomizer);
			
			randomizer.add(new WeightedRandomLoot(Items.EMERALD, d.value + 1, 3 * (d.value + 1), 100));
			randomizer.add(new WeightedRandomLoot(Items.AMETHYST_SHARD, 1, 1, 10));
			randomizer.add(new WeightedRandomLoot(Items.DIAMOND, 1, 1, 5));
			if(d.gt(Difficulty.EASY)) {
				randomizer.add(new WeightedRandomLoot(Items.SHULKER_SHELL, 1, 1, diff.value + 1));
				randomizer.add(new WeightedRandomLoot(Items.NETHERITE_SCRAP, 1, 1, 1));
			}
		});
	}

	@Override
	public ItemStack getLootItem(Random rand, Difficulty diff){
		return this.loot.get(diff).get(rand);
	}

}
