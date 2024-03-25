package com.greymerk.tweaks;






import java.util.logging.Logger;

import com.greymerk.tweaks.events.WarmChunkLoadEvent;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;

public class GreyTweaks implements ModInitializer {

	public static final String MOD_ID = "grey-tweaks";
	public static final Logger LOGGER = Logger.getLogger(MOD_ID);
	
	@Override
	public void onInitialize() {
		ServerChunkEvents.CHUNK_LOAD.register(new WarmChunkLoadEvent());
	}

}
