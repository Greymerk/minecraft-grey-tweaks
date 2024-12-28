package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.greymerk.tickers.ChunkTickManager;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.chunk.WorldChunk;

@Mixin(ServerWorld.class)
public class TickChunkMixin {
	
	@Inject(at = @At("HEAD"), method = "tickChunk(Lnet/minecraft/world/chunk/WorldChunk;I)V")
	private void tickChunk(WorldChunk chunk, int randomTickSpeed, CallbackInfo info) {
		ChunkTickManager tickers = ChunkTickManager.getInstance();
		tickers.run(chunk, randomTickSpeed);
	}
}
