package com.greymerk.tweaks.mixin;


import java.util.Calendar;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.greymerk.tweaks.util.Season;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;



@Mixin(IceBlock.class)
public class IceMixin {

	@Inject(at = @At("HEAD"), method = "randomTick")
	public void injectRandomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo info) {
		
		if(!canMelt(world, pos)) return;
		
		if(nearWater(world, pos)) {
			melt(state, world, pos);
			return;
		}
		
		if(shouldMelt(random)) {
			melt(state, world, pos);
		}
	}
	
	private boolean shouldMelt(RandomSource rand) {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		
		switch(Season.getSeason(month)) {
		case Season.WINTER: return false;
		case Season.SPRING: return rand.nextInt(16) == 0;
		case Season.SUMMER: return true;
		case Season.FALL: return rand.nextInt(16) == 0;
		default: return true;
		}
	}
	
	
	public boolean canMelt(Level world, BlockPos pos) {
		Biome biome = world.getBiome(pos).value();
		if (!world.canSeeSky(pos.above())) return false;
        return biome.warmEnoughToRain(pos, world.getSeaLevel());
	}
	
	public void melt(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, Blocks.WATER.defaultBlockState(), Block.UPDATE_ALL);
        world.updateNeighborsAt(pos, Blocks.WATER);
    }
	
	public boolean nearWater(Level world, BlockPos pos) {
		
		if(isWater(world, pos.north())) return true;
		if(isWater(world, pos.south())) return true;
		if(isWater(world, pos.east())) return true;
		if(isWater(world, pos.west())) return true;
		
		return false;
	}
	
	public boolean isWater(Level world, BlockPos pos) {
        FluidState fluidState = world.getFluidState(pos);
        return fluidState.is(Fluids.WATER);
	}
	
}
