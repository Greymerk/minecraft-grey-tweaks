package com.greymerk.tickers;

import com.greymerk.tweaks.DecayBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;



public class DecayTicker implements IChunkTicker {

	@Override
	public void tick(LevelChunk chunk, int randomTickSpeed) {
		final int DECAY_CHANCE = 10000;
		Level world = chunk.getLevel();
		RandomSource rand = world.getRandom();
		ChunkPos chunkPos = chunk.getPos();
        int worldX = chunkPos.getMinBlockX();
        int worldZ = chunkPos.getMinBlockZ();
        
        if (randomTickSpeed <= 0) return;
        
        LevelChunkSection[] chunkSections = chunk.getSections();

        
        for (int i = 0; i < chunkSections.length; ++i) {
            int sectionCoord = chunk.getSectionIndexFromSectionY(i);
            int sectionY = SectionPos.sectionToBlockCoord(sectionCoord);
            for (int l = 0; l < randomTickSpeed; ++l) {
            	BlockPos bp = world.getBlockRandomPos(worldX, sectionY, worldZ, 15);
                if(rand.nextInt(DECAY_CHANCE) == 0) decay(world, rand, bp);
            }
        }   
	}
	
	private void decay(Level world, RandomSource rand, BlockPos pos) {
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
