package com.greymerk.tweaks.events;

import com.greymerk.tweaks.util.SnowHelper;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class CheckTemperatureOfBlock implements UseBlockCallback{

	@Override
	public InteractionResult interact(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
		if(level.isClientSide()) return InteractionResult.PASS;
		if(hand == InteractionHand.OFF_HAND) return InteractionResult.PASS;
		if(!player.isCreative()) return InteractionResult.PASS;
		
		BlockPos bp = hitResult.getBlockPos();
		float temp = SnowHelper.getTempAt(level, bp);
		float btemp = level.getBiome(bp).value().getBaseTemperature();
		
		
		Component message = Component.nullToEmpty(" temp: " + temp + " btemp: " + btemp);
		player.sendOverlayMessage(message);
		return InteractionResult.SUCCESS_SERVER;
	}
	
	
}
