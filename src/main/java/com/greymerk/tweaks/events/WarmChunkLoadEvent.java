package com.greymerk.tweaks.events;

import com.greymerk.tweaks.config.Config;
import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents.Load;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.WorldChunk;

public class WarmChunkLoadEvent implements Load{

	@Override
	public void onChunkLoad(ServerWorld world, WorldChunk chunk) {
		
		if(!Config.ofBoolean(Config.SUMMER)) return;
		
		chunkTopBox(world, chunk).forEach(c -> {
			Coord surface = findSurface(chunk, c);
			if(isWarmBiome(world, surface)){
				forceMelt(chunk, surface);
			}
		});
	}
	


	private BoundingBox chunkTopBox(ServerWorld world, WorldChunk chunk) {
		ChunkPos cpos = chunk.getPos();
		Coord start = new Coord(cpos.getStartX(), world.getTopYInclusive(), cpos.getStartZ());
		Coord end = new Coord(cpos.getEndX(), world.getTopYInclusive(), cpos.getEndZ());
		return BoundingBox.of(start, end);
	}
	
	private Coord findSurface(WorldChunk chunk, Coord top) {
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
	
	private boolean isLeaves(WorldChunk chunk, Coord pos) {
		BlockState bs = chunk.getBlockState(pos.getBlockPos());
		return bs.isIn(BlockTags.LEAVES);
	}
	
	private boolean isAir(WorldChunk chunk, Coord pos) {
		BlockState bs = chunk.getBlockState(pos.getBlockPos());
		return bs.isIn(BlockTags.AIR);
	}
	
	private boolean isWarmBiome(ServerWorld world, Coord pos) {
		RegistryEntry<Biome> entry = world.getBiome(pos.getBlockPos());
		if(entry == null) return false;
		Biome biome = entry.value();
		float temp = biome.getTemperature();
		return temp > 0;
	}

	private void forceMelt(WorldChunk chunk, Coord pos) {
		BlockPos bp = pos.getBlockPos();
		BlockState bs = chunk.getBlockState(bp);
		
		Block b = bs.getBlock();
		if(b == Blocks.ICE) {
			chunk.setBlockState(bp, Blocks.WATER.getDefaultState(), false);
		}
		
		if(bs.contains(SnowBlock.LAYERS)) {
			 meltSnow(chunk, pos);
		}
		
	}
	
	private void meltSnow(WorldChunk chunk, Coord pos) {
		BlockPos bp = pos.getBlockPos();
		chunk.setBlockState(bp, Blocks.AIR.getDefaultState(), false);
		Coord under = pos.copy().add(Cardinal.DOWN);
		fixSnowSurface(chunk, under);
	}
	
	private void fixSnowSurface(WorldChunk chunk, Coord surface) {
		BlockPos bp = surface.getBlockPos();
		BlockState bs = chunk.getBlockState(bp);
		if(bs.getBlock() == Blocks.GRASS_BLOCK) {
			chunk.setBlockState(bp, Blocks.GRASS_BLOCK.getDefaultState(), false);
		}
		
		if(bs.getBlock() == Blocks.MYCELIUM) {
			chunk.setBlockState(bp, Blocks.MYCELIUM.getDefaultState(), false);
		}
		
		if(bs.getBlock() == Blocks.PODZOL) {
			chunk.setBlockState(bp, Blocks.PODZOL.getDefaultState(), false);
		}
	}
}
