package com.greymerk.tickers;

import com.greymerk.tweaks.DecayBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;

public class DecayTicker implements IChunkTicker {

	@Override
	public void tick(WorldChunk chunk, int randomTickSpeed) {
		final int DECAY_CHANCE = 10000;
		World world = chunk.getWorld();
		Random rand = world.getRandom();
		ChunkPos chunkPos = chunk.getPos();
        int worldX = chunkPos.getStartX();
        int worldZ = chunkPos.getStartZ();
        
        if (randomTickSpeed <= 0) return;
        
        ChunkSection[] chunkSections = chunk.getSectionArray();

        
        for (int i = 0; i < chunkSections.length; ++i) {
            int sectionCoord = chunk.sectionIndexToCoord(i);
            int sectionY = ChunkSectionPos.getBlockCoord(sectionCoord);
            for (int l = 0; l < randomTickSpeed; ++l) {
                BlockPos pos = world.getRandomPosInChunk(worldX, sectionY, worldZ, 15);
                if(rand.nextInt(DECAY_CHANCE) == 0) decay(world, rand, pos);
            }
        }   
	}
	
	private void decay(World world, Random rand, BlockPos pos) {
		BlockState block = world.getBlockState(pos);
		
		if(!DecayBlocks.canDecay(block)) return;
		
		int averageDecay = DecayBlocks.measureAreaDecay(world, pos, 2);
		
		final int SEA_LEVEL = 64;
		final double MIN_DECAY = 1;
		final double DECAY_CURVE = 32;
		double decayFactor = MIN_DECAY + Math.pow(2, -1*((pos.getY() - SEA_LEVEL) / DECAY_CURVE));
		
		if(averageDecay > decayFactor) return;
		
		DecayBlocks.decay(world, rand, pos);
	}
	
	public void printPos(BlockPos pos) {
		System.out.println("Ticking: " 
				+ Integer.toString(pos.getX()) + ", "
				+ Integer.toString(pos.getY()) + ", "
				+ Integer.toString(pos.getZ()));
	}

}
