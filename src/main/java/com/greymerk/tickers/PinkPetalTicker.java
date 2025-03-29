package com.greymerk.tickers;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;
import com.greymerk.tweaks.util.ChunkHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;

public class PinkPetalTicker implements IChunkTicker {

	private static final List<Block> canGrow = List.of(
		Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.MYCELIUM, 
		Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT,
		Blocks.MOSS_BLOCK, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS,
		Blocks.FARMLAND
	);
	
	@Override
	public void tick(WorldChunk chunk, int randomTickSpeed) {
		ChunkHelper.processRandomTicker(chunk, randomTickSpeed, (world, pos) -> {
			Random rand = world.random;
			if(rand.nextInt(1000) != 0) return;
			petal(world, rand, pos);
		});
	}
	
	private void petal(World world, Random rand, BlockPos pos) {
		int daylight = world.getLightLevel(LightType.SKY, pos);
		if(daylight == 0) return;
		
		Block blockBelow = world.getBlockState(pos.down()).getBlock();
		if(!canGrow.contains(blockBelow)) return;
		if(!isCherryAbove(world, pos, 3)) return;
		if(countNearbyPetals(world, pos, 2) > 5) return;
		
		addPetals(world, pos); 
	}
	
	private boolean isCherryAbove(World world, BlockPos pos, int range) {
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
	
	public int countNearbyPetals(World world, BlockPos pos, int range) {
		AtomicInteger count = new AtomicInteger(0);
		BoundingBox.of(Coord.of(pos))
			.grow(Cardinal.all, range)
			.forEach(c -> {
				BlockState toCheck = world.getBlockState(c.getBlockPos());
				if(toCheck.getBlock() == Blocks.PINK_PETALS) {
					count.set(count.get() + toCheck.get(FlowerbedBlock.FLOWER_AMOUNT));
					
				}
			});
		return count.get();
	}
	
	private void addPetals(World world, BlockPos pos) {
		BlockState toPlant = world.getBlockState(pos);
		if(toPlant.getBlock() == Blocks.PINK_PETALS) {
			int petalCount = toPlant.get(FlowerbedBlock.FLOWER_AMOUNT);
			if(petalCount >= 4) return;
			BlockState bs = toPlant.with(FlowerbedBlock.FLOWER_AMOUNT, petalCount + 1);
			world.setBlockState(pos, bs);
		}
		
		if(toPlant.getBlock() == Blocks.AIR) {
			world.setBlockState(pos, Blocks.PINK_PETALS.getDefaultState());
		}
	}


}
