package com.greymerk.tweaks.monster.profiles;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MobType;

import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ProfileWitch implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, Difficulty diff, IEntity mob) {
		mob.setMobClass(MobType.WITCH, true);
	}

}
