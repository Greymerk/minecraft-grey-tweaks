package com.greymerk.tweaks.events;

import com.greymerk.tweaks.config.Config;
import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents.Load;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;


public class WarmChunkLoadEvent implements Load{

	@Override
	public void onChunkLoad(ServerLevel world, LevelChunk chunk, boolean generated) {
		
		if(!Config.ofBoolean(Config.SUMMER)) return;
		
		chunkTopBox(world, chunk).forEach(c -> {
			Coord surface = findSurface(chunk, c);
			if(isWarmBiome(world, surface)){
				forceMelt(world, surface);
			}
		});
	}
	
	private BoundingBox chunkTopBox(ServerLevel world, LevelChunk chunk) {
		ChunkPos cpos = chunk.getPos();
		Coord start = new Coord(cpos.getMinBlockX(), world.getMaxY(), cpos.getMinBlockZ());
		Coord end = new Coord(cpos.getMaxBlockX(), world.getMaxY(), cpos.getMaxBlockZ());
		return BoundingBox.of(start, end);
	}
	
	private Coord findSurface(LevelChunk chunk, Coord top) {
		Coord pos = top.copy();
		
		while(pos.getY() > 0) {
			if(isAir(chunk, pos) || isLeaves(chunk, pos)) {
				pos.add(Cardinal.DOWN);
			} else {
				return pos;	
			}
		}
		return pos;
	}
	
	private boolean isLeaves(LevelChunk chunk, Coord pos) {
		BlockState bs = chunk.getBlockState(pos.getBlockPos());
		return bs.is(BlockTags.LEAVES);
	}
	
	private boolean isAir(LevelChunk chunk, Coord pos) {
		BlockState bs = chunk.getBlockState(pos.getBlockPos());
		return bs.is(BlockTags.AIR);
	}
	
	private boolean isWarmBiome(ServerLevel world, Coord pos) {
		Holder<Biome> entry = world.getBiome(pos.getBlockPos());
		if(entry == null) return false;
		Biome biome = entry.value();
		return biome.warmEnoughToRain(pos.getBlockPos(), world.getSeaLevel());
	}

	private void forceMelt(ServerLevel world, Coord pos) {
		BlockPos bp = pos.getBlockPos();
		BlockState bs = world.getBlockState(bp);
		
		//Block b = bs.getBlock();
		
		/*
		if(b == Blocks.ICE) {
			//world.setBlock(bp, Blocks.WATER.defaultBlockState(), Block.UPDATE_ALL);
		}
		
		if(bs.hasProperty(SnowLayerBlock.LAYERS)) {
			 //meltSnow(world, pos);
		}
		*/
	}
	
	private void meltSnow(ServerLevel world, Coord pos) {
		BlockPos bp = pos.getBlockPos();
		world.setBlock(bp, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
		Coord under = pos.copy().add(Cardinal.DOWN);
		fixSnowSurface(world, under);
	}
	
	private void fixSnowSurface(ServerLevel world, Coord surface) {
		BlockPos bp = surface.getBlockPos();
		BlockState bs = world.getBlockState(bp);
		if(bs.getBlock() == Blocks.GRASS_BLOCK) {
			world.setBlock(bp, Blocks.GRASS_BLOCK.defaultBlockState(), Block.UPDATE_ALL);
		}
		
		if(bs.getBlock() == Blocks.MYCELIUM) {
			world.setBlock(bp, Blocks.MYCELIUM.defaultBlockState(), Block.UPDATE_ALL);
		}
		
		if(bs.getBlock() == Blocks.PODZOL) {
			world.setBlock(bp, Blocks.PODZOL.defaultBlockState(), Block.UPDATE_ALL);
		}
	}
}
