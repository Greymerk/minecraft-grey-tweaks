package com.greymerk.tweaks.monster.profiles;



import com.greymerk.tweaks.treasure.loot.Enchant;
import com.greymerk.tweaks.treasure.loot.TippedArrow;
import com.greymerk.tweaks.treasure.loot.provider.ItemWeapon;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MonsterProfile;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ProfileArcher implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, int level, IEntity mob) {
		
		if(Enchant.canEnchant(world.getDifficulty(), rand, level) && rand.nextInt(10) == 0){
			mob.setSlot(EquipmentSlot.OFFHAND, TippedArrow.getHarmful(rand, 1));
		}
		
		mob.setSlot(EquipmentSlot.MAINHAND, ItemWeapon.getBow(rand, level, Enchant.canEnchant(world.getDifficulty(), rand, level)));
		MonsterProfile.get(MonsterProfile.TALLMOB).addEquipment(world, rand, level, mob);
	}

}
