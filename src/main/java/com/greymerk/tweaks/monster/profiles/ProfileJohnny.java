package com.greymerk.tweaks.monster.profiles;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MobType;
import com.greymerk.tweaks.monster.MonsterProfile;
import com.greymerk.tweaks.treasure.loot.Equipment;
import com.greymerk.tweaks.treasure.loot.provider.ItemSpecialty;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ProfileJohnny implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, Difficulty diff, IEntity mob) {
		mob.setMobClass(MobType.VINDICATOR, false);
		mob.setSlot(EquipmentSlot.MAINHAND, ItemSpecialty.getRandomItem(world.getRegistryManager(), Equipment.AXE, rand, Difficulty.HARDEST));
		MonsterProfile.get(MonsterProfile.TALLMOB).addEquipment(world, rand, Difficulty.HARD, mob);
		mob.setName("Johnny");
	}

}
