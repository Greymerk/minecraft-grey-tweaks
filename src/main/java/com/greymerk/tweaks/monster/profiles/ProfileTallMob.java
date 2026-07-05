package com.greymerk.tweaks.monster.profiles;

import java.util.List;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.treasure.loot.Loot;
import com.greymerk.tweaks.treasure.loot.Slot;
import com.greymerk.tweaks.treasure.loot.provider.ItemNovelty;

import net.minecraft.core.RegistryAccess;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ProfileTallMob implements IMonsterProfile {

	@Override
	public void addEquipment(Level world, RandomSource rand, Difficulty diff, IEntity mob) {
		RegistryAccess reg = world.registryAccess();
		FeatureFlagSet features = world.enabledFeatures();
		
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
