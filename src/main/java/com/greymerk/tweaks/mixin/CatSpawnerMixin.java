package com.greymerk.tweaks.mixin;

import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import net.minecraft.world.spawner.CatSpawner;

@Mixin(CatSpawner.class)
public class CatSpawnerMixin {

	@Inject(
		at = @At("HEAD"),
		method = "spawnInHouse(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V",
		cancellable = true
	)
	private void spawnInHouse(ServerWorld world, BlockPos pos, CallbackInfo cir) {
		
		PointOfInterestStorage pois = world.getPointOfInterestStorage();
		int radius = 48;
		RegistryKey<PointOfInterestType> type = PointOfInterestTypes.FISHERMAN;
		Predicate<RegistryEntry<PointOfInterestType>> predicate = entry -> entry.matchesKey(type);
		OccupationStatus occupied = PointOfInterestStorage.OccupationStatus.IS_OCCUPIED;
		long fishermen = pois.count(predicate, pos, radius, occupied);

		if(fishermen < 4L) cir.cancel();
	}
}
