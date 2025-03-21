package com.greymerk.tweaks.events;

import java.util.Map;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.MetaEntity;
import com.greymerk.tweaks.monster.MonsterProfile;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;

public class EntityLoadEvent implements Load{

	@Override
	public void onLoad(Entity entity, ServerWorld world) {
		if(!(entity instanceof AbstractSkeletonEntity || entity instanceof ZombieEntity)) return;
		MobEntity mob = (MobEntity)entity;
		
		Map<RegistryEntry<StatusEffect>, StatusEffectInstance> effects = mob.getActiveStatusEffects();
		if(!effects.containsKey(StatusEffects.MINING_FATIGUE)) return;
		
		StatusEffectInstance effect = effects.get(StatusEffects.MINING_FATIGUE);
		int level = effect.getAmplifier();
		mob.removeStatusEffect(StatusEffects.MINING_FATIGUE);
		
		IEntity monster = new MetaEntity(mob);
		Random random = world.getRandom();
		MonsterProfile.equip(world, random, Difficulty.from(random.nextInt(level + 1)), monster);
		
		//System.out.println("Set roguelike on " + mob.getType().toString());
	}
}
