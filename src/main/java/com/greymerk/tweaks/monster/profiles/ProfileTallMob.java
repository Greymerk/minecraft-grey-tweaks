package com.greymerk.tweaks.monster.profiles;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.treasure.loot.Loot;
import com.greymerk.tweaks.treasure.loot.Slot;

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
		for(EquipmentSlot slot : new EquipmentSlot[]{
				EquipmentSlot.HEAD,
				EquipmentSlot.CHEST,
				EquipmentSlot.LEGS,
				EquipmentSlot.FEET
				}){
			ItemStack item = Loot.getEquipmentBySlot(features, reg, rand, Slot.getSlot(slot), Difficulty.from(ilvl), mob.canEnchant(rand, diff));
			mob.setSlot(slot, item);
		}

	}

}
