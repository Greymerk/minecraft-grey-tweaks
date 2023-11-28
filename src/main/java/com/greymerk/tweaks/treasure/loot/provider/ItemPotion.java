package com.greymerk.tweaks.treasure.loot.provider;

import com.greymerk.tweaks.treasure.loot.PotionForm;
import com.greymerk.tweaks.treasure.loot.PotionItem;
import com.greymerk.tweaks.treasure.loot.PotionMixture;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

public class ItemPotion extends ItemBase{

	public ItemPotion(int weight, int level) {
		super(weight, level);
	}

	@Override
	public ItemStack getLootItem(Random rand, int level) {
		if(level > 2 && rand.nextBoolean()) {
			return PotionMixture.getPotion(rand, PotionMixture.VILE);
		} else if(level > 0 && rand.nextBoolean()) {
			return PotionMixture.getRandom(rand);
		}
		
		final PotionItem[] potions = new PotionItem[]{
				PotionItem.HEALING,
				PotionItem.STRENGTH,
				PotionItem.SWIFTNESS,
				PotionItem.REGEN
		};
		PotionItem type = potions[rand.nextInt(potions.length)];
		return PotionItem.getSpecific(PotionForm.REGULAR, type, true, false);
	}
}
