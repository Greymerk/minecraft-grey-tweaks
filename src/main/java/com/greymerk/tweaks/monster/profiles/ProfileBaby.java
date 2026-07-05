package com.greymerk.tweaks.monster.profiles;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.IMonsterProfile;
import com.greymerk.tweaks.monster.MobType;
import com.greymerk.tweaks.treasure.loot.provider.ItemNovelty;
import com.greymerk.tweaks.util.WeightedChoice;
import com.greymerk.tweaks.util.WeightedRandomizer;

import net.minecraft.core.RegistryAccess;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;



public class ProfileBaby implements IMonsterProfile {

	@Override
	public void addEquipment(Level world, RandomSource rand, Difficulty diff, IEntity mob) {
		mob.setChild(true);
		
		if(rand.nextInt(10) == 0){
			mob.setMobClass(MobType.ZOMBIEVILLAGER, false);
		}
		
		mob.setSlot(EquipmentSlot.MAINHAND, toy(world.registryAccess(), rand));
	}
	
	private ItemStack toy(RegistryAccess reg, RandomSource rand) {
		
		if(rand.nextInt(100) == 0) return ItemNovelty.getItem(reg, ItemNovelty.VECHS);
		
		WeightedRandomizer<ItemStack> randomizer = new WeightedRandomizer<ItemStack>();
		
		randomizer.add(new WeightedChoice<ItemStack>(new ItemStack(Items.BONE), 1));
		randomizer.add(new WeightedChoice<ItemStack>(new ItemStack(Items.STICK), 1));
		randomizer.add(new WeightedChoice<ItemStack>(new ItemStack(Items.ARROW), 1));
		randomizer.add(new WeightedChoice<ItemStack>(new ItemStack(Items.FEATHER), 1));
		randomizer.add(new WeightedChoice<ItemStack>(new ItemStack(Items.OAK_SIGN), 1));
		randomizer.add(new WeightedChoice<ItemStack>(new ItemStack(Items.CARROT), 1));
		randomizer.add(new WeightedChoice<ItemStack>(new ItemStack(Items.COD), 1));
		randomizer.add(new WeightedChoice<ItemStack>(new ItemStack(Items.SOUL_TORCH), 1));
		
		return randomizer.get(rand);
	}

}
