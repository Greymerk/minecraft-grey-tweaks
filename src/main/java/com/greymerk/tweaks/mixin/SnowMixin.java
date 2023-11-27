package com.greymerk.tweaks.mixin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;

@Mixin(SnowBlock.class)
public class SnowMixin {
	
	private static float FREEZING = 0.15f;
	
	@Inject(at = @At("HEAD"), method = "randomTick")
	public void injectRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo info) {
		if (!world.isSkyVisible(pos)) return;
		
		if(!isColdBiome(world, pos)) {
			if(isSummer()) {
				meltNeighbours(world, random, pos, 4);
			} else if(world.isRaining()){
				meltNeighbours(world, random, pos, 2);
			} else if(random.nextInt(10) != 0){
				melt(state, world, random, pos);
			}
		}
		
		if(isColdBiome(world, pos) && world.isRaining()) {
			accumulate(state, world, random, pos);
		}
	}
	
	public void melt(BlockState state, ServerWorld world, Random rand, BlockPos pos) {
		if(!state.contains(SnowBlock.LAYERS)) return;
		
		int layers = state.get(SnowBlock.LAYERS);
		if(layers > 1) {
			BlockState meltedSnow = state.with(SnowBlock.LAYERS, layers - 1);
			world.removeBlock(pos, false);
			world.setBlockState(pos, meltedSnow);
			return;
		}
		
        SnowBlock.dropStacks(state, world, pos);
        world.removeBlock(pos, false);
	}
	
	public void meltNeighbours(ServerWorld world, Random rand, BlockPos pos, int range) {

		if (!world.isSkyVisible(pos)) return;
		if(range <= 0) return;
		
		for(int x = pos.getX() - 1; x <= pos.getX() + 1; x++) {
			for(int y = pos.getY() - 1; y <= pos.getY() + 1; y++) {
				for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z++){
					if(rand.nextBoolean()) continue;
					BlockPos p = new BlockPos(x, y, z);
					if(p.equals(pos)) continue;
					BlockState bs = world.getBlockState(p);
					if(!bs.contains(SnowBlock.LAYERS)) continue;
					meltNeighbours(world, rand, pos, range - 1);
				}
			}
		}
		melt(world.getBlockState(pos), world, rand, pos);
	}
	
	public void accumulate(BlockState state, ServerWorld world, Random rand, BlockPos pos) {
		if(!validSnowBank(state, world, pos)) return;
		
		int layers = state.get(SnowBlock.LAYERS);
		if(layers >= 7) return;
		
		BlockState accumulatedSnow = state.with(SnowBlock.LAYERS, layers + 1);
		world.removeBlock(pos, false);
		world.setBlockState(pos, accumulatedSnow);
	}

	public boolean validSnowBank(BlockState state, ServerWorld world, BlockPos pos) {
		if(!world.getBlockState(pos.down()).isOpaque()) return false;
		
		int lowest = lowestNeighbour(state, world, pos);
		if(lowest == 0) return false;
		int layers = state.get(SnowBlock.LAYERS);
		if(layers - lowest > 3) return false;
		
		int highest = highestNeighbour(state, world, pos);
		if(highest - layers > 1) return true;
		return false;
	}

	public int highestNeighbour(BlockState state, ServerWorld world, BlockPos pos) {
		
		List<Integer> heights = new ArrayList<Integer>();
		
		heights.add(getHeight(world, pos.north()));
		heights.add(getHeight(world, pos.south()));
		heights.add(getHeight(world, pos.east()));
		heights.add(getHeight(world, pos.west()));
		
		return Collections.max(heights);
	}

	
	public int lowestNeighbour(BlockState state, ServerWorld world, BlockPos pos) {
		
		List<Integer> heights = new ArrayList<Integer>();
		
		heights.add(getHeight(world, pos.north()));
		heights.add(getHeight(world, pos.south()));
		heights.add(getHeight(world, pos.east()));
		heights.add(getHeight(world, pos.west()));
		
		return Collections.min(heights);
	}
	
	public int getHeight(ServerWorld world, BlockPos pos) {
		BlockState block = world.getBlockState(pos); 
		
		if(block.contains(SnowBlock.LAYERS)) {
			return block.get(SnowBlock.LAYERS);
		}
		
		if(block.isFullCube(world, pos)) return 8;
		
		return 0;
	}
	
	public boolean isHigher(BlockState state, ServerWorld world, BlockPos pos) {
		int layers = state.get(SnowBlock.LAYERS);
		if(layers >= 7) return false;
		
		BlockState otherBlock = world.getBlockState(pos);
		
		if(otherBlock.isFullCube(world, pos)) return true;
		if(otherBlock.contains(SnowBlock.LAYERS)) {
			int otherLayers = otherBlock.get(SnowBlock.LAYERS);
			if (otherLayers - layers > 1) return true;	
		}
		
		return false;
	}
	
	public boolean isColdBiome(ServerWorld world, BlockPos pos) {
		Biome biome = world.getBiome(pos).value();
		float temp = biome.getTemperature();
		return temp <= FREEZING;
	}
	
	private boolean isSummer() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		return month >= Calendar.MAY && month < Calendar.OCTOBER;
	}
}
 