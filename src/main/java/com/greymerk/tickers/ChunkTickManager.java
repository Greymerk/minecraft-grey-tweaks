package com.greymerk.tickers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.chunk.WorldChunk;

public class ChunkTickManager {

	private static ChunkTickManager instance = null;
	
	private List<IChunkTicker> tickers;
	
	public ChunkTickManager() {
		this.tickers = new ArrayList<IChunkTicker>(); 
	}
	
	public static ChunkTickManager getInstance() {
		if(instance == null) {
			instance = new ChunkTickManager();
		}
		
		return instance;
	}
	
	public void add(IChunkTicker toAdd) {
		this.tickers.add(toAdd);
	}
	
	public void run(WorldChunk chunk, int randomTickSpeed) {
		this.tickers.forEach(ticker -> {
			ticker.tick(chunk, randomTickSpeed);
		});
	}
}
