package com.greymerk.tickers;

import net.minecraft.world.level.chunk.LevelChunk;

public interface IChunkTicker {

	public void tick(LevelChunk chunk, int randomTickSpeed);
	
}
