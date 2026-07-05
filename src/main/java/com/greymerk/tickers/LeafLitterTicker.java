package com.greymerk.tickers;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;
import com.greymerk.tweaks.util.ChunkHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeafLitterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;


public class LeafLitterTicker implements IChunkTicker {

	List<Block> leafTypes = List.of(
			Blocks.OAK_LEAVES, Blocks.BIRCH_LEAVES, Blocks.ACACIA_LEAVES,
			Blocks.PALE_OAK_LEAVES, Blocks.DARK_OAK_LEAVES);
	
	public static final int MAX_LEAVES = 12;
	
	
	@Override
	public void tick(LevelChunk chunk, int randomTickSpeed) {
		ChunkHelper.processRandomTicker(chunk, randomTickSpeed, (world, pos) -> {
			RandomSource rand = world.getRandom();
			if(rand.nextInt(2000) != 0) return;
			leaf(world, rand, pos);
		});
	}

	private void leaf(Level world, RandomSource rand, BlockPos pos) {
		if(!canPlaceAt(world, pos)) return;
		if(!leavesAbove(world, pos, 3)) return;
		if(leafCount(world, pos, 6) > MAX_LEAVES) return;
		placeLeaf(world, pos);
	}
	
	private int leafCount(Level world, BlockPos pos, int range) {
		AtomicInteger count = new AtomicInteger(0);
		BoundingBox.of(Coord.of(pos))
			.grow(Cardinal.all, range)
			.forEach(c -> {
				BlockState toCheck = world.getBlockState(c.getBlockPos());
				if(toCheck.getBlock() == Blocks.LEAF_LITTER) {
					count.set(count.get() + toCheck.getValue(LeafLitterBlock.AMOUNT));
					
				}
			});
		return count.get();
	}

	private void placeLeaf(Level world, BlockPos pos) {

		if(world.getBlockState(pos).isAir()) {
			Cardinal dir = Cardinal.randDir(world.getRandom());
			Direction facing = Cardinal.facing(dir);
			world.setBlock(pos, Blocks.LEAF_LITTER.defaultBlockState()
					.setValue(LeafLitterBlock.FACING, facing), Block.UPDATE_ALL);
			return;
		}
		
		BlockState leaves = world.getBlockState(pos);
		Block b = leaves.getBlock();
		if(b == Blocks.LEAF_LITTER) {
			int leafCount = leaves.getValue(LeafLitterBlock.AMOUNT);
			if(leafCount >= LeafLitterBlock.MAX_SEGMENT) return;
			leaves = leaves.setValue(LeafLitterBlock.AMOUNT, leafCount + 1);
			world.setBlock(pos, leaves, Block.UPDATE_ALL);
			return;
		}
	}

	private boolean leavesAbove(Level world, BlockPos pos, int range) {
		BoundingBox bb = BoundingBox.of(Coord.of(pos))
				.grow(Cardinal.directions, range)
				.grow(Cardinal.UP, 5)
				.add(Cardinal.UP, 2);
			for(Coord c : bb) {
				Block toCheck = world.getBlockState(c.getBlockPos()).getBlock();
				if(leafTypes.contains(toCheck)) return true;
			};

		return false;
	}

	private boolean canPlaceAt(Level world, BlockPos pos) {
		Block b = world.getBlockState(pos).getBlock();
		if(!(world.getBlockState(pos).isAir() || b == Blocks.LEAF_LITTER)) return false;
		BlockPos blockPos = pos.below();
		return world.getBlockState(blockPos).isFaceSturdy(world, blockPos, Direction.UP);
	}
}
