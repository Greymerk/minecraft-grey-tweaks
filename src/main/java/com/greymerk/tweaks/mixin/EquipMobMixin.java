package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.util.MoonHelper;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;



@Mixin(Mob.class)
public class EquipMobMixin{

	@Inject(at = @At("HEAD"), method = "populateDefaultEquipmentSlots", cancellable = true)
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty, CallbackInfo cir) {
		
		Mob entity = (Mob)(Object)this;
		Level world = entity.level();
		
		Difficulty diff = MoonHelper.getDiff(world);
		
		//System.out.println("phase: " + phase + " diff: " + diff);
		if(!doEquip(random, diff)) return;
		
		/*
		IEntity mob = new MetaEntity(entity);
		MonsterProfile.equip(world, random, random.nextInt(diff + 1), mob);
		cir.cancel();
		*/
		
		this.setRoguelike(entity, diff);
	}
	
	private boolean doEquip(RandomSource rand, Difficulty diff) {		
		if(diff == Difficulty.HARDEST) return true;
		if(diff == Difficulty.HARD) return rand.nextInt(3) != 0;
		if(diff == Difficulty.MEDIUM) return rand.nextBoolean();
		if(diff == Difficulty.EASY) return rand.nextInt(3) == 0;
		if(diff == Difficulty.EASIEST) return rand.nextInt(5) == 0;
		return false;
	}
	
	private void setRoguelike(Mob mob, Difficulty diff) {
		Holder<MobEffect> ref = MobEffects.MINING_FATIGUE;
		int level = diff.value;
		final int DURATION = 10;
		
		MobEffectInstance effect = new MobEffectInstance(ref, DURATION, level, false, false, false);
		mob.addEffect(effect);
	}
}
