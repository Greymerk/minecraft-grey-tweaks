package com.greymerk.tweaks.monster.profiles;

import net.minecraft.util.math.random.Random;

import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.treasure.loot.Enchant;
import com.greymerk.tweaks.treasure.loot.Loot;
import com.greymerk.tweaks.treasure.loot.Slot;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ProfileTallMob implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, int level, IEntity mob) {
		int ilvl = level <= 2 ? level : 2 + rand.nextInt(3);
		for(EquipmentSlot slot : new EquipmentSlot[]{
				EquipmentSlot.HEAD,
				EquipmentSlot.CHEST,
				EquipmentSlot.LEGS,
				EquipmentSlot.FEET
				}){
			ItemStack item = Loot.getEquipmentBySlot(rand, Slot.getSlot(slot), ilvl, Enchant.canEnchant(world.getDifficulty(), rand, level));
			mob.setSlot(slot, item);
		}

	}

}
