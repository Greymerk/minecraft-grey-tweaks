package com.greymerk.tweaks.util;

import java.util.Calendar;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class SnowHelper {
	
	static List<ResourceKey<Biome>> seasonalBiomes = List.of(Biomes.PLAINS);
	
	public static boolean shouldSnow(Level level, BlockPos bp) {
		return getTempAt(level, bp) <= 0.0;
	}
	
	public static float getTempAt(Level level, BlockPos bp) {
		Biome biome = level.getBiome(bp).value();
		return biome.getBaseTemperature();
	}
	
	public static float getSeasonalTempAt(Level level, BlockPos bp) {
		int month = Calendar.MONTH;
		float temp = getTempAt(level, bp);
		boolean winter = month < 3 || month > 10;
		if(winter) return temp - 0.7f;
		return temp;
	}
	
	public static boolean isSeasonBiome(Level world, BlockPos bp) {
		Holder<Biome> biome = world.getBiome(bp);
		for(ResourceKey<Biome> key : seasonalBiomes) {
			if(biome.unwrapKey().get().equals(key)) return true;
		}
		
		return true;
	}
	
}
