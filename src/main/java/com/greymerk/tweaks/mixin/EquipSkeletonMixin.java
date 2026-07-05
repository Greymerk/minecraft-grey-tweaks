package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.MetaEntity;
import com.greymerk.tweaks.monster.MonsterProfile;
import com.greymerk.tweaks.util.MoonHelper;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.level.Level;

@Mixin(AbstractSkeleton.class)
public class EquipSkeletonMixin{

	@Inject(at = @At("HEAD"), method = "populateDefaultEquipmentSlots", cancellable = true)
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty, CallbackInfo cir) {
		
		Mob entity = (Mob)(Object)this;
		Level world = entity.level();
		Difficulty diff = MoonHelper.getDiff(world);
		
		if(!doEquip(random, diff)) return;
		
		IEntity mob = new MetaEntity(entity);
		MonsterProfile.get(MonsterProfile.SKELETON).addEquipment(world, random, diff, mob);
		cir.cancel();
	}
	
	private boolean doEquip(RandomSource rand, Difficulty diff) {		
		if(diff == Difficulty.HARDEST) return true;
		if(diff == Difficulty.HARD) return rand.nextInt(3) != 0;
		if(diff == Difficulty.MEDIUM) return rand.nextBoolean();
		if(diff == Difficulty.EASY) return rand.nextInt(3) == 0;
		if(diff == Difficulty.EASIEST) return rand.nextInt(5) == 0;
		return false;
	}
	

}
