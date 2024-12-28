package com.greymerk.tickers;

import net.minecraft.world.chunk.WorldChunk;

public interface IChunkTicker {

	public void tick(WorldChunk chunk, int randomTickSpeed);
	
}
