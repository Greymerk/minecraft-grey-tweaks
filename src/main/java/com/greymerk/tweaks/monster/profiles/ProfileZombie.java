package com.greymerk.tweaks.monster.profiles;

import net.minecraft.util.math.random.Random;

import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MonsterProfile;
import com.greymerk.tweaks.treasure.loot.Enchant;
import com.greymerk.tweaks.treasure.loot.Shield;
import com.greymerk.tweaks.treasure.loot.provider.ItemTool;
import com.greymerk.tweaks.treasure.loot.provider.ItemWeapon;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ProfileZombie implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, int level, IEntity mob) {
		
		if(level > 2 && rand.nextInt(300) == 0){
			MonsterProfile.get(MonsterProfile.EVOKER).addEquipment(world, rand, level, mob);
			return;
		}
				
		if(rand.nextInt(50) == 0){
			MonsterProfile.get(MonsterProfile.BABY).addEquipment(world, rand, level, mob);
			return;
		}
		
		if(rand.nextInt(100) == 0){
			MonsterProfile.get(MonsterProfile.VILLAGER).addEquipment(world, rand, level, mob);
			return;
		}

		if(rand.nextInt(4) == 0) {
			ItemStack weapon = ItemWeapon.getSword(rand, level, true);
			mob.setSlot(EquipmentSlot.MAINHAND, weapon);
			mob.setSlot(EquipmentSlot.OFFHAND, Shield.get(rand));
		} else {
			ItemStack weapon = ItemTool.getRandom(rand, level, Enchant.canEnchant(world.getDifficulty(), rand, level));
			mob.setSlot(EquipmentSlot.MAINHAND, weapon);
		}
		
		MonsterProfile.get(MonsterProfile.TALLMOB).addEquipment(world, rand, level, mob);
		
	}

}
