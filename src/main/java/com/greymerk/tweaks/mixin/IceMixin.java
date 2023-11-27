package com.greymerk.tweaks.mixin;


import java.util.Calendar;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.IceBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

@Mixin(IceBlock.class)
public class IceMixin {

	@Inject(at = @At("HEAD"), method = "randomTick")
	public void injectRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo info) {
		
		if(!canMelt(world, pos)) return;
		
		if(nearWater(world, pos)) {
			melt(state, world, pos);
			return;
		}
		
		if(isSummer() || random.nextInt(16) == 0) {
			melt(state, world, pos);
		}
	}
	
	private boolean isSummer() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		return month >= Calendar.MAY && month < Calendar.OCTOBER;
	}
	
	public boolean canMelt(World world, BlockPos pos) {
		Biome biome = world.getBiome(pos).value();
		BlockPos above = pos.up();
		
		if (biome.getTemperature() < 0.15) return false;
        if (!world.isSkyVisible(above)) return false;
        
        return true;
	}
	
	public void melt(BlockState state, World world, BlockPos pos) {
        if (world.getDimension().ultrawarm()) {
            world.removeBlock(pos, false);
            return;
        }
        world.setBlockState(pos, Blocks.WATER.getDefaultState());
        world.updateNeighbor(pos, Blocks.WATER, pos);
    }
	
	public boolean nearWater(World world, BlockPos pos) {
		
		if(isWater(world, pos.north())) return true;
		if(isWater(world, pos.south())) return true;
		if(isWater(world, pos.east())) return true;
		if(isWater(world, pos.west())) return true;
		
		return false;
	}
	
	public boolean isWater(World world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos);
        FluidState fluidState = world.getFluidState(pos);
		return fluidState.getFluid() == Fluids.WATER && blockState.getBlock() instanceof FluidBlock;
	}
	
}
