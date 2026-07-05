package com.greymerk.tickers;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;



public class ResinTicker implements IChunkTicker {

	@Override
	public void tick(LevelChunk chunk, int randomTickSpeed) {
		int resinChance = 1000;
		Level world = chunk.getLevel();
		RandomSource rand = world.getRandom();
		ChunkPos chunkPos = chunk.getPos();
        int worldX = chunkPos.getMinBlockX();
        int worldZ = chunkPos.getMinBlockZ();
        
        if (randomTickSpeed <= 0) return;
        if (rand.nextInt(resinChance) != 0) return;
        
        LevelChunkSection[] chunkSections = chunk.getSections();
        
        for (int i = 0; i < chunkSections.length; ++i) {
            int sectionCoord = chunk.getSectionYFromSectionIndex(i);
            int sectionY = SectionPos.sectionToBlockCoord(sectionCoord);
            for (int l = 0; l < randomTickSpeed; ++l) {
                BlockPos pos = world.getBlockRandomPos(worldX, sectionY, worldZ, 15);
                resin(world, rand, pos);
            }
        } 
	}
	
	private void resin(Level world, RandomSource rand, BlockPos pos) {
		if(!world.getBlockState(pos).isAir()) return;
		
		boolean valid = false;
		
		MetaBlock clump = MetaBlock.of(Blocks.RESIN_CLUMP);
		
		for(Cardinal dir : Cardinal.directions) {
			Coord p = Coord.of(pos).add(dir);
			BlockState b = world.getBlockState(p.getBlockPos());
			
			if(b.getBlock() == Blocks.PALE_OAK_LOG) {
				valid = true;
				clump.with(MultifaceBlock.getFaceProperty(Cardinal.facing(Cardinal.reverse(dir))), true);
			}
		}
		
		if(!valid) return;
		
		if(!isIsolated(world, pos)) return;
	
		world.setBlock(pos, clump.getState(), Block.UPDATE_ALL);
	}
	
	private boolean isIsolated(Level world, BlockPos pos) {
		BoundingBox bb = BoundingBox.of(Coord.of(pos));
		bb.grow(Cardinal.all, 2);
		
		int clumps = 0;
		for(Coord p : bb.get()) {
			if(world.getBlockState(p.getBlockPos()).getBlock() == Blocks.RESIN_CLUMP) {
				clumps++;
			}
		}
		
		return clumps <= 3;
	}
}
