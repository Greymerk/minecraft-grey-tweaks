package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.util.MoonHelper;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

@Mixin(MobEntity.class)
public class EquipMobMixin{

	@Inject(at = @At("HEAD"), method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V", cancellable = true)
	protected void initEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo cir) {
		
		MobEntity entity = (MobEntity)(Object)this;
		World world = entity.getEntityWorld();
		
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
	
	private boolean doEquip(Random rand, Difficulty diff) {		
		if(diff == Difficulty.HARDEST) return true;
		if(diff == Difficulty.HARD) return rand.nextInt(3) != 0;
		if(diff == Difficulty.MEDIUM) return rand.nextBoolean();
		if(diff == Difficulty.EASY) return rand.nextInt(3) == 0;
		if(diff == Difficulty.EASIEST) return rand.nextInt(5) == 0;
		return false;
	}
	
	private void setRoguelike(MobEntity mob, Difficulty diff) {
		int level = diff.value;
		
		final int DURATION = 10;
		StatusEffect type = StatusEffects.MINING_FATIGUE.value();
		RegistryEntry<StatusEffect> ref = Registries.STATUS_EFFECT.getEntry(type);
		StatusEffectInstance effect = new StatusEffectInstance(ref, DURATION, level, false, false, false);
		mob.addStatusEffect(effect);
	}
}
