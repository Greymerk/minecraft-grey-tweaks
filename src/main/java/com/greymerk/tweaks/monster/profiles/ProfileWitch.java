package com.greymerk.tweaks.monster.profiles;

import net.minecraft.util.math.random.Random;

import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MobType;

import net.minecraft.world.World;

public class ProfileWitch implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, int level, IEntity mob) {
		mob.setMobClass(MobType.WITCH, true);
	}

}
