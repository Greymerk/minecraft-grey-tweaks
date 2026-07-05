package com.greymerk.tweaks.util;

import com.greymerk.tweaks.Difficulty;

import net.minecraft.world.level.Level;

public class MoonHelper {

	public static Difficulty getDiff(Level world) {
		return getDiff(getPhase(world));
	}
	
	public static int getPhase(Level world) {
		long time = world.getGameTime();
		return getPhase(time);
	}
	
    private static int getPhase(long time) {
        return (int)(time / 24000L % 8L + 8L) % 8;
    }
    
	private static Difficulty getDiff(int phase) {
		if(phase == 0) return Difficulty.HARDEST;
		if(phase == 4) return Difficulty.EASIEST;
		if(phase == 1 || phase == 7) return Difficulty.HARD;
		if(phase == 2 || phase == 6) return Difficulty.MEDIUM;
		return Difficulty.EASY;
	}
	
}
