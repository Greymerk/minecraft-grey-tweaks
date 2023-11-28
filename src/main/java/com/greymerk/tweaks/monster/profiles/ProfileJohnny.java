package com.greymerk.tweaks.monster.profiles;

import net.minecraft.util.math.random.Random;

import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MobType;
import com.greymerk.tweaks.monster.MonsterProfile;
import com.greymerk.tweaks.treasure.loot.Equipment;
import com.greymerk.tweaks.treasure.loot.provider.ItemSpecialty;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.world.World;

public class ProfileJohnny implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, int level, IEntity mob) {
		mob.setMobClass(MobType.VINDICATOR, false);
		mob.setSlot(EquipmentSlot.MAINHAND, ItemSpecialty.getRandomItem(Equipment.AXE, rand, 4));
		MonsterProfile.get(MonsterProfile.TALLMOB).addEquipment(world, rand, 3, mob);
		mob.setName("Johnny");
	}

}
