package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
		int phase = world.getMoonPhase();
		int diff = this.getMoonDiff(phase);
		
		//System.out.println("phase: " + phase + " diff: " + diff);
		if(!doEquip(random, diff)) return;
		
		/*
		IEntity mob = new MetaEntity(entity);
		MonsterProfile.equip(world, random, random.nextInt(diff + 1), mob);
		cir.cancel();
		*/
		
		this.setRoguelike(entity, diff);
	}
	
	private boolean doEquip(Random rand, int diff) {		
		if(diff == 4) return true;
		if(diff == 3) return rand.nextInt(3) != 0;
		if(diff == 2) return rand.nextBoolean();
		if(diff == 1) return rand.nextInt(3) == 0;
		if(diff == 0) return rand.nextInt(5) == 0;
		return false;
	}
	
	private int getMoonDiff(int phase) {
		if(phase == 0) return 4;
		if(phase == 4) return 0;
		if(phase == 1 || phase == 7) return 3;
		if(phase == 2 || phase == 6) return 2;
		return 1;
	}
	
	private void setRoguelike(MobEntity mob, int level) {
		final int DURATION = 10;
		StatusEffect type = StatusEffects.MINING_FATIGUE.value();
		RegistryEntry<StatusEffect> ref = Registries.STATUS_EFFECT.getEntry(type);
		StatusEffectInstance effect = new StatusEffectInstance(ref, DURATION, level, false, false, false);
		mob.addStatusEffect(effect);
	}
}
