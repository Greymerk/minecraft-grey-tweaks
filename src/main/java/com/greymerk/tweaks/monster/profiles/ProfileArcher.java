package com.greymerk.tweaks.monster.profiles;



import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MonsterProfile;
import com.greymerk.tweaks.treasure.loot.items.TippedArrow;
import com.greymerk.tweaks.treasure.loot.provider.ItemWeapon;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ProfileArcher implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, Difficulty diff, IEntity mob) {
		
		if(mob.canEnchant(rand, diff) && rand.nextInt(10) == 0){
			mob.setSlot(EquipmentSlot.OFFHAND, TippedArrow.getHarmful(rand, 1));
		}
		
		mob.setSlot(EquipmentSlot.MAINHAND, ItemWeapon.getBow(world.getRegistryManager(), world.getEnabledFeatures(), rand, diff, mob.canEnchant(rand, diff)));
		MonsterProfile.get(MonsterProfile.TALLMOB).addEquipment(world, rand, diff, mob);
	}

}
