package com.greymerk.tweaks.mixin;

import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiManager.Occupancy;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.CatSpawner;



@Mixin(CatSpawner.class)
public class CatSpawnerMixin {

	@Inject(
		at = @At("HEAD"),
		method = "spawnInVillage",
		cancellable = true
	)
	private void spawnInVillage(ServerLevel world, BlockPos pos, CallbackInfo cir) {
		
		PoiManager pois = world.getPoiManager();
		int radius = 48;
		ResourceKey<PoiType> type = PoiTypes.FISHERMAN;
		Predicate<Holder<PoiType>> predicate = entry -> entry.is(type);
		Occupancy occupied = PoiManager.Occupancy.IS_OCCUPIED;
		long fishermen = pois.getCountInRange(predicate, pos, radius, occupied);

		if(fishermen < 4L) cir.cancel();
	}
}
