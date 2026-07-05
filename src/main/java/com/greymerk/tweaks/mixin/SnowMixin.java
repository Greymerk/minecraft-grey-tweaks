package com.greymerk.tweaks.mixin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.greymerk.tweaks.util.Season;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;



@Mixin(SnowLayerBlock.class)
public class SnowMixin {
	
	@Inject(at = @At("HEAD"), method = "randomTick")
	public void injectRandomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo info) {
		if(!world.canSeeSky(pos)) return;
		
		boolean cold = world.getBiome(pos).value().coldEnoughToSnow(pos, world.getSeaLevel());
		
		if(cold) {
			if(world.isRaining()) {
				accumulate(state, world, random, pos);
				return;
			} else {
				return;	
			}
		}
				
		if(isSummer() || world.isRaining() || random.nextInt(16) != 0){
			melt(state, world, random, pos);
		}
	}
	
	private void melt(BlockState state, ServerLevel world, RandomSource rand, BlockPos pos) {
		if(!state.hasProperty(SnowLayerBlock.LAYERS)) return;
		
		int layers = state.getValue(SnowLayerBlock.LAYERS);
		
		if(layers > 1) {
			BlockState meltedSnow = state.setValue(SnowLayerBlock.LAYERS, layers - 1);
			world.removeBlock(pos, false);
			world.setBlock(pos, meltedSnow, Block.UPDATE_ALL);
			return;
		}
		
        SnowLayerBlock.dropResources(state, world, pos);
        world.removeBlock(pos, false);
	}
	
	private void accumulate(BlockState state, ServerLevel world, RandomSource rand, BlockPos pos) {
		if(!validSnowBank(state, world, pos)) return;
		
		int layers = state.getValue(SnowLayerBlock.LAYERS);
		if(layers >= 7) return;
		
		BlockState accumulatedSnow = state.setValue(SnowLayerBlock.LAYERS, layers + 1);
		world.removeBlock(pos, false);
		world.setBlock(pos, accumulatedSnow, Block.UPDATE_ALL);
	}

	private boolean validSnowBank(BlockState state, ServerLevel world, BlockPos pos) {
		if(!world.getBlockState(pos.below()).canOcclude()) return false;
		
		int lowest = lowestNeighbour(state, world, pos);
		if(lowest == 0) return false;
		int layers = state.getValue(SnowLayerBlock.LAYERS);
		if(layers - lowest > 3) return false;
		
		int highest = highestNeighbour(state, world, pos);
		if(highest - layers > 1) return true;
		return false;
	}

	private int highestNeighbour(BlockState state, ServerLevel world, BlockPos pos) {
		
		List<Integer> heights = new ArrayList<Integer>();
		
		heights.add(getHeight(world, pos.north()));
		heights.add(getHeight(world, pos.south()));
		heights.add(getHeight(world, pos.east()));
		heights.add(getHeight(world, pos.west()));
		
		return Collections.max(heights);
	}

	
	private int lowestNeighbour(BlockState state, ServerLevel world, BlockPos pos) {
		
		List<Integer> heights = new ArrayList<Integer>();
		
		heights.add(getHeight(world, pos.north()));
		heights.add(getHeight(world, pos.south()));
		heights.add(getHeight(world, pos.east()));
		heights.add(getHeight(world, pos.west()));
		
		return Collections.min(heights);
	}
	
	private int getHeight(ServerLevel world, BlockPos pos) {
		BlockState block = world.getBlockState(pos); 
		
		if(block.hasProperty(SnowLayerBlock.LAYERS)) {
			return block.getValue(SnowLayerBlock.LAYERS);
		}
		
		if(block.isCollisionShapeFullBlock(world, pos)) return 8;

		
		return 0;
	}
	
	private boolean isSummer() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		return Season.getSeason(month) == Season.SUMMER;
	}
}
 