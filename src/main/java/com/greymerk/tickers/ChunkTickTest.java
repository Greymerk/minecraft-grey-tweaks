package com.greymerk.tickers;

import net.minecraft.world.chunk.WorldChunk;

public class ChunkTickTest implements IChunkTicker {

	@Override
	public void tick(WorldChunk chunk, int randomTickSpeed) {
		System.out.println("Tick Chunk " + chunk.getPos().x + " " + chunk.getPos().z);
	}
}
