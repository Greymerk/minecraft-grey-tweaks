package com.greymerk.tickers;

import net.minecraft.world.level.chunk.LevelChunk;

public class ChunkTickTest implements IChunkTicker {

	@Override
	public void tick(LevelChunk chunk, int randomTickSpeed) {
		System.out.println("Tick Chunk " + chunk.getPos().x() + " " + chunk.getPos().z());
	}
}
