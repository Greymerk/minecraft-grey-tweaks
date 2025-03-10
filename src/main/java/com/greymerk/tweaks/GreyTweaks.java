package com.greymerk.tweaks;






import java.util.logging.Logger;

import com.greymerk.gamerules.GameRuleTweaks;
import com.greymerk.tickers.ChunkTickManager;
import com.greymerk.tickers.DecayTicker;
import com.greymerk.tickers.PinkPetalTicker;
import com.greymerk.tickers.ResinTicker;
import com.greymerk.tweaks.config.ConfigFile;
import com.greymerk.tweaks.config.ConfigSettings;
import com.greymerk.tweaks.events.ElytraEvent;
import com.greymerk.tweaks.events.EntityLoadEvent;
import com.greymerk.tweaks.events.EntityRegenTick;
import com.greymerk.tweaks.events.WarmChunkLoadEvent;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class GreyTweaks implements ModInitializer {

	public static final String MOD_ID = "grey-tweaks";
	public static final Logger LOGGER = Logger.getLogger(MOD_ID);	
	
	{
		GameRuleTweaks.init();
	}
	
	@Override
	public void onInitialize() {
		ServerChunkEvents.CHUNK_LOAD.register(new WarmChunkLoadEvent());
		//ServerTickEvents.END_WORLD_TICK.register(new SlowDayWorldTickEvent());
		ServerEntityEvents.ENTITY_LOAD.register(new EntityLoadEvent());
		EntityElytraEvents.ALLOW.register(new ElytraEvent());
		ServerTickEvents.END_WORLD_TICK.register(new EntityRegenTick());
		
		ChunkTickManager tickers = ChunkTickManager.getInstance();
		tickers.add(new PinkPetalTicker());
		tickers.add(new DecayTicker());
		tickers.add(new ResinTicker());
		
		ConfigSettings.init(new ConfigFile());
	}
	
}
