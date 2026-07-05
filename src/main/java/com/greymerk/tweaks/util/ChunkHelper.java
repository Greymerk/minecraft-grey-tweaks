package com.greymerk.tweaks.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;

public class ChunkHelper {

	public static void processRandomTicker(LevelChunk chunk, int randomTickSpeed, Runnable func) {
		Level world = chunk.getLevel();
		ChunkPos chunkPos = chunk.getPos();
        int worldX = chunkPos.getMinBlockX();
        int worldZ = chunkPos.getMinBlockZ();
        
        if (randomTickSpeed <= 0) return;
        
        LevelChunkSection[] chunkSections = chunk.getSections();
        
        for (int i = 0; i < chunkSections.length; ++i) {
            int sectionCoord = chunk.getSectionYFromSectionIndex(i);
            int sectionY = SectionPos.sectionToBlockCoord(sectionCoord);
            for (int l = 0; l < randomTickSpeed; ++l) {
                BlockPos pos = world.getBlockRandomPos(worldX, sectionY, worldZ, 15);
                func.apply(world, pos);
            }
        }
	}

	@FunctionalInterface
	public interface Runnable{
		void apply(Level world, BlockPos pos);
	}
}
