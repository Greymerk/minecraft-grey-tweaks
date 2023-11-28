package com.greymerk.tweaks.monster.profiles;

import net.minecraft.util.math.random.Random;

import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MonsterProfile;

import net.minecraft.world.World;

public class ProfileSkeleton implements IMonsterProfile{

	@Override
	public void addEquipment(World world, Random rand, int level, IEntity mob) {
		
		if(rand.nextInt(40) == 0){
			MonsterProfile.get(MonsterProfile.POISONARCHER).addEquipment(world, rand, level, mob);
			return;
		}
		
		if(rand.nextInt(50) == 0){
			MonsterProfile.get(MonsterProfile.MAGICARCHER).addEquipment(world, rand, level, mob);
			return;
		}
		
		if(rand.nextInt(100) == 0){
			MonsterProfile.get(MonsterProfile.WITHER).addEquipment(world, rand, level, mob);
			return;
		}
		
		if(rand.nextInt(20) == 0){
			MonsterProfile.get(MonsterProfile.SWORDSMAN).addEquipment(world, rand, level, mob);
			return;
		}

		MonsterProfile.get(MonsterProfile.ARCHER).addEquipment(world, rand, level, mob);
	}

}
