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
import net.minecraft.block.LeafLitterBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;

public class LeafLitterTicker implements IChunkTicker {

	List<Block> leafTypes = List.of(
			Blocks.OAK_LEAVES, Blocks.BIRCH_LEAVES, Blocks.ACACIA_LEAVES,
			Blocks.PALE_OAK_LEAVES, Blocks.DARK_OAK_LEAVES);
	
	public static final int MAX_LEAVES = 12;
	
	
	@Override
	public void tick(WorldChunk chunk, int randomTickSpeed) {
		ChunkHelper.processRandomTicker(chunk, randomTickSpeed, (world, pos) -> {
			Random rand = world.random;
			if(rand.nextInt(50) != 0) return;
			leaf(world, rand, pos);
		});
	}

	private void leaf(World world, Random rand, BlockPos pos) {
		if(!canPlaceAt(world, pos)) return;
		if(!leavesAbove(world, pos, 3)) return;
		if(leafCount(world, pos, 6) > MAX_LEAVES) return;
		placeLeaf(world, pos);
	}
	
	private int leafCount(World world, BlockPos pos, int range) {
		AtomicInteger count = new AtomicInteger(0);
		BoundingBox.of(Coord.of(pos))
			.grow(Cardinal.all, range)
			.forEach(c -> {
				BlockState toCheck = world.getBlockState(c.getBlockPos());
				if(toCheck.getBlock() == Blocks.LEAF_LITTER) {
					count.set(count.get() + toCheck.get(LeafLitterBlock.SEGMENT_AMOUNT));
					
				}
			});
		return count.get();
	}

	private void placeLeaf(World world, BlockPos pos) {

		if(world.isAir(pos)) {
			Cardinal dir = Cardinal.randDir(world.random);
			Direction facing = Cardinal.facing(dir);
			System.out.println(dir.name());
			world.setBlockState(pos, Blocks.LEAF_LITTER.getDefaultState()
					.with(LeafLitterBlock.HORIZONTAL_FACING, facing));
			return;
		}
		
		BlockState leaves = world.getBlockState(pos);
		Block b = leaves.getBlock();
		if(b == Blocks.LEAF_LITTER) {
			int leafCount = leaves.get(LeafLitterBlock.SEGMENT_AMOUNT);
			if(leafCount >= LeafLitterBlock.MAX_SEGMENTS) return;
			leaves = leaves.with(LeafLitterBlock.SEGMENT_AMOUNT, leafCount + 1);
			world.setBlockState(pos, leaves);
			return;
		}
	}

	private boolean leavesAbove(World world, BlockPos pos, int range) {
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

	private boolean canPlaceAt(World world, BlockPos pos) {
		Block b = world.getBlockState(pos).getBlock();
		if(!(world.isAir(pos) || b == Blocks.LEAF_LITTER)) return false;
		BlockPos blockPos = pos.down();
		return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, Direction.UP);
	}

}
