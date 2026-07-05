package com.greymerk.tweaks.events;

import java.util.Optional;

import com.greymerk.gamerules.GameRuleTweaks;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents.Allow;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gamerules.GameRules;


public class ElytraEvent implements Allow{

	@Override
	public boolean allowElytraFlight(LivingEntity entity) {
		// Whether or not to allow elytra flight outside of the End dimension.
		
		
		Level world = entity.level();
		if(world.isClientSide()) return true;
		
		GameRules rules = world.getServer().getLevel(world.dimension()).getGameRules();
		if(rules.get(GameRuleTweaks.GREY_TWEAK_ALLOW_ELYTRA)) {
			return true;
		}
		
		Holder<DimensionType> dimEntry = world.dimensionTypeRegistration();
		Optional<ResourceKey<DimensionType>> dimKeyOpt = dimEntry.unwrapKey();
		ResourceKey<DimensionType> dimKey = dimKeyOpt.get();
		ResourceKey<DimensionType> endKey = BuiltinDimensionTypes.END;
		
		return dimKey == endKey;
	}

}
