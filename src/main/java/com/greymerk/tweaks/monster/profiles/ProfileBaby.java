package com.greymerk.tweaks.monster.profiles;

import net.minecraft.util.math.random.Random;

import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MonsterProfile;
import com.greymerk.tweaks.treasure.loot.Enchant;
import com.greymerk.tweaks.treasure.loot.provider.ItemTool;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ProfileBaby implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, int level, IEntity mob) {
		mob.setChild(true);
		
		if(rand.nextBoolean()){
			MonsterProfile.get(MonsterProfile.VILLAGER).addEquipment(world, rand, level, mob);
		}
		
		ItemStack weapon = ItemTool.getRandom(rand, level, Enchant.canEnchant(world.getDifficulty(), rand, level));
		mob.setSlot(EquipmentSlot.MAINHAND, weapon);
	}

}
