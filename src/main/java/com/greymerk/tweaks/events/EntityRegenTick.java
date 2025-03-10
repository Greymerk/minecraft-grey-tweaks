package com.greymerk.tweaks.events;

import java.util.List;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndWorldTick;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;

public class EntityRegenTick implements EndWorldTick {

	@Override
	public void onEndTick(ServerWorld world) {
		long time = world.getTime();
		if(time % 100 != 0) return;
		
		List<EntityType<? extends PassiveEntity>> types = List.of(
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
	
	private void regenEntities(ServerWorld world, EntityType<? extends LivingEntity> type) {
		Random rand = world.getRandom();
		List<? extends LivingEntity> entities = world.getEntitiesByType(type, Entity::isAlive);
		entities.forEach(entity -> {
			if(entity.getHealth() == entity.getMaxHealth()) return;
			if(rand.nextInt(10) != 0) return;
			entity.setHealth(entity.getHealth() + 1.0f);
		});
	}
}
