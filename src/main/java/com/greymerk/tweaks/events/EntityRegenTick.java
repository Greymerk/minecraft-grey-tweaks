package com.greymerk.tweaks.events;

import java.util.List;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndLevelTick;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;



public class EntityRegenTick implements EndLevelTick {

	@Override
	public void onEndTick(ServerLevel world) {
		long time = world.getGameTime();
		if(time % 100 != 0) return;
		
		List<EntityType<? extends AgeableMob>> types = List.of(
				EntityType.BEE, 
				EntityType.VILLAGER,
				EntityType.SHEEP,
				EntityType.CHICKEN,
				EntityType.COW,
				EntityType.PIG,
				EntityType.CAT,
				EntityType.MOOSHROOM,
				EntityType.TURTLE,
				EntityType.RABBIT
				);
		
		types.forEach(type -> {
			regenEntities(world, type);	
		});
	}
	
	private void regenEntities(ServerLevel world, EntityType<? extends LivingEntity> type) {
		RandomSource rand = world.getRandom();
		List<? extends LivingEntity> entities = world.getEntities(type, Entity::isAlive);
		entities.forEach(entity -> {
			if(entity.getHealth() == entity.getMaxHealth()) return;
			if(rand.nextInt(10) != 0) return;
			entity.setHealth(entity.getHealth() + 1.0f);
		});
	}
}
