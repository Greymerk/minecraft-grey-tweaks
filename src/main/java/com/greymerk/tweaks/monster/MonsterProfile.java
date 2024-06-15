package com.greymerk.tweaks.monster;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.profiles.ProfileArcher;
import com.greymerk.tweaks.monster.profiles.ProfileAshlea;
import com.greymerk.tweaks.monster.profiles.ProfileBaby;
import com.greymerk.tweaks.monster.profiles.ProfileEvoker;
import com.greymerk.tweaks.monster.profiles.ProfileFireArcher;
import com.greymerk.tweaks.monster.profiles.ProfileHusk;
import com.greymerk.tweaks.monster.profiles.ProfileJohnny;
import com.greymerk.tweaks.monster.profiles.ProfileMagicArcher;
import com.greymerk.tweaks.monster.profiles.ProfilePigman;
import com.greymerk.tweaks.monster.profiles.ProfilePoisonArcher;
import com.greymerk.tweaks.monster.profiles.ProfileRleahy;
import com.greymerk.tweaks.monster.profiles.ProfileSkeleton;
import com.greymerk.tweaks.monster.profiles.ProfileSwordsman;
import com.greymerk.tweaks.monster.profiles.ProfileTallMob;
import com.greymerk.tweaks.monster.profiles.ProfileVillager;
import com.greymerk.tweaks.monster.profiles.ProfileVindicator;
import com.greymerk.tweaks.monster.profiles.ProfileWitch;
import com.greymerk.tweaks.monster.profiles.ProfileWither;
import com.greymerk.tweaks.monster.profiles.ProfileZombie;

import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public enum MonsterProfile {

	TALLMOB, ZOMBIE, PIGMAN, SKELETON, VILLAGER, HUSK, BABY, ASHLEA, RLEAHY, 
	ARCHER, WITHER, FIREARCHER, POISONARCHER, MAGICARCHER, SWORDSMAN, EVOKER, VINDICATOR,
	WITCH, JOHNNY;
	
	public static IMonsterProfile get(MonsterProfile profile){
		switch(profile){
		case TALLMOB: return new ProfileTallMob();
		case ZOMBIE: return new ProfileZombie();
		case PIGMAN: return new ProfilePigman();
		case SKELETON: return new ProfileSkeleton();
		case VILLAGER: return new ProfileVillager();
		case HUSK: return new ProfileHusk();
		case BABY: return new ProfileBaby();
		case ASHLEA: return new ProfileAshlea();
		case RLEAHY: return new ProfileRleahy();
		case ARCHER: return new ProfileArcher();
		case WITHER: return new ProfileWither();
		case FIREARCHER: return new ProfileFireArcher();
		case POISONARCHER: return new ProfilePoisonArcher();
		case MAGICARCHER: return new ProfileMagicArcher();
		case SWORDSMAN: return new ProfileSwordsman();
		case EVOKER: return new ProfileEvoker();
		case VINDICATOR: return new ProfileVindicator();
		case WITCH: return new ProfileWitch();
		case JOHNNY: return new ProfileJohnny();
		default: return new ProfileTallMob();
		}
	}
	
	public static void equip(World world, Random rand, Difficulty diff, IEntity mob){
		
		IMonsterProfile profile = null;
		
		if(mob.instance(ZombieEntity.class)) profile = get(ZOMBIE);
		
		if(mob.instance(SkeletonEntity.class)) profile = get(SKELETON);
		
		if(profile == null) return;
		
		profile.addEquipment(world, rand, diff, mob);
	}
}
