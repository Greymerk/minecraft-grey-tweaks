package com.greymerk.tweaks.monster.profiles;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MonsterProfile;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;



public class ProfileSkeleton implements IMonsterProfile{

	@Override
	public void addEquipment(Level world, RandomSource rand, Difficulty diff, IEntity mob) {
		
		if(diff.gt(Difficulty.EASY) && rand.nextInt(50) == 0){
			MonsterProfile.get(MonsterProfile.POISONARCHER).addEquipment(world, rand, diff, mob);
			return;
		}
		
		if(diff.gt(Difficulty.EASY) && rand.nextInt(50) == 0){
			MonsterProfile.get(MonsterProfile.MAGICARCHER).addEquipment(world, rand, diff, mob);
			return;
		}
		
		if(diff.gt(Difficulty.EASY) && rand.nextInt(50) == 0){
			MonsterProfile.get(MonsterProfile.FIREARCHER).addEquipment(world, rand, diff, mob);
			return;
		}
		
		if(diff.gt(Difficulty.EASY) && rand.nextInt(20) == 0){
			MonsterProfile.get(MonsterProfile.WITHER).addEquipment(world, rand, diff, mob);
			return;
		}
		
		if(diff.gt(Difficulty.EASIEST) && rand.nextInt(20) == 0){
			MonsterProfile.get(MonsterProfile.SWORDSMAN).addEquipment(world, rand, diff, mob);
			return;
		}

		MonsterProfile.get(MonsterProfile.ARCHER).addEquipment(world, rand, diff, mob);
	}

}
