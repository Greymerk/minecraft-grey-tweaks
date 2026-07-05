package com.greymerk.tickers;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;
import com.greymerk.tweaks.util.ChunkHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;



public class PinkPetalTicker implements IChunkTicker {

	private static final List<Block> canGrow = List.of(
		Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.MYCELIUM, 
		Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT,
		Blocks.MOSS_BLOCK, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS,
		Blocks.FARMLAND
	);
	
	@Override
	public void tick(LevelChunk chunk, int randomTickSpeed) {
		ChunkHelper.processRandomTicker(chunk, randomTickSpeed, (world, pos) -> {
			RandomSource rand = world.getRandom();
			if(rand.nextInt(1000) != 0) return;
			petal(world, rand, pos);
		});
	}
	
	private void petal(Level world, RandomSource rand, BlockPos pos) {
		int daylight = world.getBrightness(LightLayer.SKY, pos);
		if(daylight == 0) return;
		
		Block blockBelow = world.getBlockState(pos.below()).getBlock();
		if(!canGrow.contains(blockBelow)) return;
		if(!isCherryAbove(world, pos, 3)) return;
		if(countNearbyPetals(world, pos, 2) > 5) return;
		
		addPetals(world, pos); 
	}
	
	private boolean isCherryAbove(Level world, BlockPos pos, int range) {
		BoundingBox bb = BoundingBox.of(Coord.of(pos))
			.grow(Cardinal.directions, range)
			.grow(Cardinal.UP, 6)
			.add(Cardinal.UP, 2);
		for(Coord c : bb) {
			Block toCheck = world.getBlockState(c.getBlockPos()).getBlock();
			if(toCheck == Blocks.CHERRY_LEAVES) return true;
		};

		return false;
	}
	
	public int countNearbyPetals(Level world, BlockPos pos, int range) {
		AtomicInteger count = new AtomicInteger(0);
		BoundingBox.of(Coord.of(pos))
			.grow(Cardinal.all, range)
			.forEach(c -> {
				BlockState toCheck = world.getBlockState(c.getBlockPos());
				if(toCheck.getBlock() == Blocks.PINK_PETALS) {
					count.set(count.get() + toCheck.getValue(FlowerBedBlock.AMOUNT));
					
				}
			});
		return count.get();
	}
	
	private void addPetals(Level world, BlockPos pos) {
		BlockState toPlant = world.getBlockState(pos);
		if(toPlant.getBlock() == Blocks.PINK_PETALS) {
			int petalCount = toPlant.getValue(FlowerBedBlock.AMOUNT);
			if(petalCount >= 4) return;
			BlockState bs = toPlant.setValue(FlowerBedBlock.AMOUNT, petalCount + 1);
			world.setBlock(pos, bs, Block.UPDATE_ALL);
		}
		
		if(toPlant.getBlock() == Blocks.AIR) {
			world.setBlock(pos, Blocks.PINK_PETALS.defaultBlockState(), Block.UPDATE_ALL);
		}
	}


}
