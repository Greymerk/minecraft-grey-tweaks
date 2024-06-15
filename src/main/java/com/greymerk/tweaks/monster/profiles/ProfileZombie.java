package com.greymerk.tweaks.monster.profiles;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MonsterProfile;
import com.greymerk.tweaks.treasure.loot.provider.ItemTool;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ProfileZombie implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, Difficulty diff, IEntity mob) {
		
		if(diff.lt(Difficulty.HARD) && rand.nextInt(20) == 0){
			MonsterProfile.get(MonsterProfile.VILLAGER).addEquipment(world, rand, diff, mob);
			return;
		}
		
		if(rand.nextInt(3) == 0) {
			MonsterProfile.get(MonsterProfile.SWORDSMAN).addEquipment(world, rand, diff, mob);
			return;
		}
		
		ItemStack weapon = ItemTool.getRandom(world.getRegistryManager(), world.getEnabledFeatures(), rand, diff, mob.canEnchant(rand, diff));
		mob.setSlot(EquipmentSlot.MAINHAND, weapon);
		MonsterProfile.get(MonsterProfile.TALLMOB).addEquipment(world, rand, diff, mob);
	}

}
