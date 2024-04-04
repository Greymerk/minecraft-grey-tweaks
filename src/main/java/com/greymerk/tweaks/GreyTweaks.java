package com.greymerk.tweaks;






import java.util.logging.Logger;

import com.greymerk.tweaks.events.EntityLoadEvent;
import com.greymerk.tweaks.events.WarmChunkLoadEvent;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

public class GreyTweaks implements ModInitializer {

	public static final String MOD_ID = "grey-tweaks";
	public static final Logger LOGGER = Logger.getLogger(MOD_ID);
	
	@Override
	public void onInitialize() {
		ServerChunkEvents.CHUNK_LOAD.register(new WarmChunkLoadEvent());
		//ServerTickEvents.END_WORLD_TICK.register(new SlowDayWorldTickEvent());
		ServerEntityEvents.ENTITY_LOAD.register(new EntityLoadEvent());
	}

}
