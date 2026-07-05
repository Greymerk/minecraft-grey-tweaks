package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.greymerk.tickers.ChunkTickManager;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;


@Mixin(ServerLevel.class)
public class TickChunkMixin {
	
	@Inject(at = @At("HEAD"), method = "tickChunk")
	private void tickChunk(LevelChunk chunk, int randomTickSpeed, CallbackInfo info) {
		Level world = chunk.getLevel();
		ChunkPos cp = chunk.getPos();
		if(!world.hasChunk(cp.x(), cp.z())) return;
		
		ChunkTickManager tickers = ChunkTickManager.getInstance();
		tickers.run(chunk, randomTickSpeed);
	}
}
