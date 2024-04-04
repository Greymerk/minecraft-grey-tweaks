package com.greymerk.tweaks.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndWorldTick;
import net.minecraft.server.world.ServerWorld;

public class SlowDayWorldTickEvent implements EndWorldTick{

	private long lastReverse;
	
	public SlowDayWorldTickEvent() {
		this.lastReverse = 0;
	}
	
	@Override
	public void onEndTick(ServerWorld world) {
		
		final long DAWN = 0;
		final long DUSK = 12000;
		final long DAYLIGHT_SAVINGS = 1; // ticks
		
		long timeTick = world.getTime();
		long timeOfDay = world.getTimeOfDay();
		
		if((timeTick % 5) != 0) return;
		
		if(timeOfDay < DAWN + 100 || timeOfDay > DUSK) {
			if(this.lastReverse != 0) {
				this.lastReverse = 0;	
			}
			return;
		}
		
		if(timeOfDay - this.lastReverse <= DAYLIGHT_SAVINGS) return;
		
		world.setTimeOfDay(timeOfDay - DAYLIGHT_SAVINGS);
		this.lastReverse = timeOfDay - DAYLIGHT_SAVINGS;
		System.out.println("time: " + timeOfDay + " , " + world.getTimeOfDay());
	}

}
