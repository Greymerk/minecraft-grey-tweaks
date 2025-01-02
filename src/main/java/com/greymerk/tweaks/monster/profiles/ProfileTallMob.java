package com.greymerk.tweaks.monster.profiles;

import java.util.List;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.treasure.loot.Loot;
import com.greymerk.tweaks.treasure.loot.Slot;
import com.greymerk.tweaks.treasure.loot.provider.ItemNovelty;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ProfileTallMob implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, Difficulty diff, IEntity mob) {
		DynamicRegistryManager reg = world.getRegistryManager();
		FeatureSet features = world.getEnabledFeatures();
		
		int ilvl = diff.lt(Difficulty.HARD) ? diff.value : 2 + rand.nextInt(3);

		List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET).forEach(slot -> {
			
			if(slot == EquipmentSlot.FEET) {
				if(rand.nextInt(200) == 0)	{
					mob.setSlot(slot, ItemNovelty.getItem(reg, ItemNovelty.TRAVELERS));
					return;
				}
				if(rand.nextInt(50) == 0) {
					mob.setSlot(slot, ItemNovelty.getItem(reg, ItemNovelty.RUNNERS));
					return;
				}
			}
			
			ItemStack item = Loot.getEquipmentBySlot(features, reg, rand, Slot.getSlot(slot), Difficulty.from(ilvl), mob.canEnchant(rand, diff));
			mob.setSlot(slot, item);	
		});
	}
}
