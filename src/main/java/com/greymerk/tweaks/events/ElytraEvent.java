package com.greymerk.tweaks.events;

import com.greymerk.gamerules.GameRuleTweaks;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents.Allow;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

public class ElytraEvent implements Allow{

	@Override
	public boolean allowElytraFlight(LivingEntity entity) {
		// Whether or not to allow outside of the End dimension.
		
		
		World world = entity.getEntityWorld();
		if(world.isClient) return true;
		
		MinecraftServer mcserver = entity.getServer();
		GameRules rules = mcserver.getGameRules();
		if(rules.getBoolean(GameRuleTweaks.GREY_TWEAK_ALLOW_ELYTRA)) {
			return true;
		}
		
		RegistryKey<DimensionType> dimKey = world.getDimensionEntry().getKey().get();
		RegistryKey<DimensionType> endKey = DimensionTypes.THE_END;
		
		return dimKey == endKey;
	}

}
