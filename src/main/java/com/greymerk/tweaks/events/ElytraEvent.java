package com.greymerk.tweaks.events;

import com.greymerk.gamerules.GameRuleTweaks;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents.Allow;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.minecraft.world.rule.GameRules;

public class ElytraEvent implements Allow{

	@Override
	public boolean allowElytraFlight(LivingEntity entity) {
		// Whether or not to allow elytra flight outside of the End dimension.
		
		
		World world = entity.getEntityWorld();
		if(world.isClient()) return true;
		
		GameRules rules = world.getServer().getWorld(world.getRegistryKey()).getGameRules();
		if(rules.getValue(GameRuleTweaks.GREY_TWEAK_ALLOW_ELYTRA)) {
			return true;
		}
		
		RegistryKey<DimensionType> dimKey = world.getDimensionEntry().getKey().get();
		RegistryKey<DimensionType> endKey = DimensionTypes.THE_END;
		
		return dimKey == endKey;
	}

}
