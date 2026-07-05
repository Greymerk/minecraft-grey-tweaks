package com.greymerk.tweaks.monster.profiles;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MobType;
import com.greymerk.tweaks.treasure.loot.Enchant;
import com.greymerk.tweaks.treasure.loot.Quality;
import com.greymerk.tweaks.treasure.loot.Slot;
import com.greymerk.tweaks.treasure.loot.potions.PotionEffect;
import com.greymerk.tweaks.treasure.loot.provider.ItemArmour;
import com.greymerk.tweaks.treasure.loot.provider.ItemNovelty;
import com.greymerk.tweaks.treasure.loot.trim.Trim;
import com.greymerk.tweaks.treasure.loot.trim.TrimMaterialEnum;
import com.greymerk.tweaks.treasure.loot.trim.TrimPatternEnum;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class ProfileFireArcher implements IMonsterProfile {

	@Override
	public void addEquipment(Level world, RandomSource rand, Difficulty diff, IEntity mob) {
		
		mob.setMobClass(MobType.STRAY, false);
		
		mob.setOnFire(60 * 60);
		mob.setEffect(PotionEffect.getInstance(PotionEffect.FIRERESIST, 1, 60 * 60));
		mob.setEffect(PotionEffect.getInstance(PotionEffect.SPEED, 1, 60 * 60));
		
		mob.setSlot(EquipmentSlot.MAINHAND, ItemNovelty.getItem(world.registryAccess(), ItemNovelty.BURNING));
		
		for(EquipmentSlot slot : new EquipmentSlot[]{
				EquipmentSlot.HEAD,
				EquipmentSlot.CHEST,
				EquipmentSlot.LEGS,
				EquipmentSlot.FEET
				}){
			ItemStack item = ItemArmour.get(rand, Slot.getSlot(slot), Quality.WOOD);
			Enchant.enchantItem(world.registryAccess(), world.enabledFeatures(), rand, item, 20);
			ItemArmour.dyeArmor(item, 200, 50, 52); // dark red
			Trim.set(world.registryAccess(), item, TrimPatternEnum.RIB, TrimMaterialEnum.GOLD);
			mob.setSlot(slot, item);
		}
		
	}

}
