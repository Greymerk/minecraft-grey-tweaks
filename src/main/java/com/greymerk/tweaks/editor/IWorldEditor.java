package com.greymerk.tweaks.editor;

import java.nio.file.Path;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;

public interface IWorldEditor {
	
	public boolean set(Coord pos, MetaBlock metaBlock, boolean fillAir, boolean replaceSolid);
	
	public boolean set(Coord pos, MetaBlock metaBlock);
	
	public MetaBlock getBlock(Coord pos);
	
	public boolean hasBlockEntity(Coord pos);
	
	public BlockEntity getBlockEntity(Coord pos);
	
	public long getSeed();
	
	public Random getRandom(Coord pos);
			
	public Coord findSurface(Coord pos);
	
	public boolean isChunkLoaded(Coord pos);
	
	public boolean surroundingChunksLoaded(Coord pos);
	
	public boolean isOverworld();

	public boolean isSolid(Coord pos);
		
	public boolean isSupported(Coord pos);
	
	public boolean isFaceFullSquare(Coord pos, Cardinal dir);
	
	public int getMaxDepth();
	
	public DynamicRegistryManager getRegistryManager();
	
	public FeatureSet getFeatureSet();
	
	public Path getWorldDirectory();
	
	public GameRules getGameRules();
	
	public ServerWorld getServerWorld();
	
	//public RoguelikeState getState();

	boolean isAir(Coord pos);
}
