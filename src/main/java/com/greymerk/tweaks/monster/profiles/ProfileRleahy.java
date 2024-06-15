package com.greymerk.tweaks.monster.profiles;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.treasure.loot.Quality;
import com.greymerk.tweaks.treasure.loot.Slot;
import com.greymerk.tweaks.treasure.loot.items.Shield;
import com.greymerk.tweaks.treasure.loot.provider.ItemArmour;
import com.greymerk.tweaks.treasure.loot.provider.ItemNovelty;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ProfileRleahy implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, Difficulty diff, IEntity mob) {
		ItemStack weapon = ItemNovelty.getItem(world.getRegistryManager(), ItemNovelty.RLEAHY);
		mob.setSlot(EquipmentSlot.MAINHAND, weapon);
		mob.setSlot(EquipmentSlot.OFFHAND, Shield.get(world.getRegistryManager(), rand));
		
		ItemStack item = ItemArmour.get(rand, Slot.FEET, Quality.WOOD);
		ItemArmour.dyeArmor(item, 32, 32, 32);
		mob.setSlot(EquipmentSlot.FEET, item);

		item = ItemArmour.get(rand, Slot.LEGS, Quality.WOOD);
		ItemArmour.dyeArmor(item, 0, 51, 102);
		mob.setSlot(EquipmentSlot.LEGS, item);
		
		item = ItemArmour.get(rand, Slot.CHEST, Quality.WOOD);
		ItemArmour.dyeArmor(item, 255, 204, 229);
		mob.setSlot(EquipmentSlot.CHEST, item);
	}
}
