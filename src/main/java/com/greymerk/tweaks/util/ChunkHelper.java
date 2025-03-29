package com.greymerk.tweaks.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;

public class ChunkHelper {

	public static void processRandomTicker(WorldChunk chunk, int randomTickSpeed, Runnable func) {
		World world = chunk.getWorld();
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
                func.apply(world, pos);
            }
        }
	}

	@FunctionalInterface
	public interface Runnable{
		void apply(World world, BlockPos pos);
	}
}
