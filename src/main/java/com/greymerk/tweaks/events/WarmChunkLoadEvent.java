package com.greymerk.tweaks.events;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;
import com.greymerk.tweaks.editor.shapes.RectSolid;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents.Load;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.WorldChunk;

public class WarmChunkLoadEvent implements Load{

	@Override
	public void onChunkLoad(ServerWorld world, WorldChunk chunk) {
		
		
		ChunkPos cpos = chunk.getPos();
		Coord start = new Coord(cpos.getStartX(), world.getTopY(), cpos.getStartZ());
		Coord end = new Coord(cpos.getEndX(), world.getTopY(), cpos.getEndZ());
		
		RectSolid cTop = new RectSolid(BoundingBox.of(start, end));
		for(Coord c : cTop) {
			Coord surface = findSurface(chunk, c);
			if(isWarmBiome(world, surface)){
				forceMelt(chunk, surface);	
			}
		}
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
		Block b = bs.getBlock();
		
		if(	   b == Blocks.ACACIA_LEAVES
			|| b == Blocks.AZALEA_LEAVES
			|| b == Blocks.BIRCH_LEAVES
			|| b == Blocks.CHERRY_LEAVES
			|| b == Blocks.DARK_OAK_LEAVES
			|| b == Blocks.FLOWERING_AZALEA_LEAVES
			|| b == Blocks.JUNGLE_LEAVES
			|| b == Blocks.MANGROVE_LEAVES
			|| b == Blocks.OAK_LEAVES
			|| b == Blocks.SPRUCE_LEAVES) {
			return true;
		}
		
		return false;
	}
	
	private boolean isAir(WorldChunk chunk, Coord pos) {
		BlockState bs = chunk.getBlockState(pos.getBlockPos());
		Block b = bs.getBlock();
		
		if(	   b == Blocks.AIR 
			|| b == Blocks.CAVE_AIR
			|| b == Blocks.VOID_AIR) {
			return true;
		}
		
		return false;
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
		BlockPos ubp = under.getBlockPos();
		BlockState ubs = chunk.getBlockState(ubp);
		
		if(ubs.getBlock() == Blocks.GRASS_BLOCK) {
			chunk.setBlockState(ubp, Blocks.GRASS_BLOCK.getDefaultState(), false);
		}
		
		if(ubs.getBlock() == Blocks.MYCELIUM) {
			chunk.setBlockState(ubp, Blocks.MYCELIUM.getDefaultState(), false);
		}
		
		if(ubs.getBlock() == Blocks.PODZOL) {
			chunk.setBlockState(ubp, Blocks.PODZOL.getDefaultState(), false);
		}
	}
}
