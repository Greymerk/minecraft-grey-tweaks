package com.greymerk.tickers;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MultifaceBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;

public class ResinTicker implements IChunkTicker {

	@Override
	public void tick(WorldChunk chunk, int randomTickSpeed) {
		int resinChance = 1000;
		World world = chunk.getWorld();
		Random rand = world.getRandom();
		ChunkPos chunkPos = chunk.getPos();
        int worldX = chunkPos.getStartX();
        int worldZ = chunkPos.getStartZ();
        
        if (randomTickSpeed <= 0) return;
        if (rand.nextInt(resinChance) != 0) return;
        
        ChunkSection[] chunkSections = chunk.getSectionArray();
        
        for (int i = 0; i < chunkSections.length; ++i) {
            int sectionCoord = chunk.sectionIndexToCoord(i);
            int sectionY = ChunkSectionPos.getBlockCoord(sectionCoord);
            for (int l = 0; l < randomTickSpeed; ++l) {
                BlockPos pos = world.getRandomPosInChunk(worldX, sectionY, worldZ, 15);
                resin(world, rand, pos);
            }
        } 
	}
	
	private void resin(World world, Random rand, BlockPos pos) {
		if(!world.isAir(pos)) return;
		
		boolean valid = false;
		
		MetaBlock clump = MetaBlock.of(Blocks.RESIN_CLUMP);
		
		for(Cardinal dir : Cardinal.directions) {
			Coord p = Coord.of(pos).add(dir);
			BlockState b = world.getBlockState(p.getBlockPos());
			
			if(b.getBlock() == Blocks.PALE_OAK_LOG) {
				valid = true;
				clump.with(MultifaceBlock.getProperty(Cardinal.facing(Cardinal.reverse(dir))), true);
			}
		}
		
		if(!valid) return;
		
		if(!isIsolated(world, pos)) return;
	
		world.setBlockState(pos, clump.getState());
	}
	
	private boolean isIsolated(World world, BlockPos pos) {
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
