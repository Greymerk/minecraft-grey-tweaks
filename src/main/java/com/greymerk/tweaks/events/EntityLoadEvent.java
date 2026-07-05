package com.greymerk.tweaks.events;

import java.util.Map;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.MetaEntity;
import com.greymerk.tweaks.monster.MonsterProfile;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents.Load;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.monster.zombie.Zombie;

public class EntityLoadEvent implements Load{

	@Override
	public void onLoad(Entity entity, ServerLevel world) {
		if(!(entity instanceof AbstractSkeleton || entity instanceof Zombie)) return;
		Mob mob = (Mob)entity;
		
		Map<Holder<MobEffect>, MobEffectInstance> effects = mob.getActiveEffectsMap();
		if(!effects.containsKey(MobEffects.MINING_FATIGUE)) return;
		
		MobEffectInstance effect = effects.get(MobEffects.MINING_FATIGUE);
		int level = effect.getAmplifier();
		mob.removeEffect(MobEffects.MINING_FATIGUE);
		
		IEntity monster = new MetaEntity(mob);
		RandomSource random = world.getRandom();
		MonsterProfile.equip(world, random, Difficulty.from(random.nextInt(level + 1)), monster);
		
		//System.out.println("Set roguelike on " + mob.getType().toString());
	}
}
