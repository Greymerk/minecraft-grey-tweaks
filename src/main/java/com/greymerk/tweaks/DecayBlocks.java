package com.greymerk.tweaks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public enum DecayBlocks {

	STONEBRICK, CRACKEDBRICK, MOSSYBRICK, COBBLESTONE, MOSSYCOBBLE, GRAVEL;
	
	
	
	public static DecayBlocks[] decayable = {	STONEBRICK, CRACKEDBRICK,
												MOSSYBRICK, COBBLESTONE,
												MOSSYCOBBLE};
	
	public static Map<Block, DecayBlocks> decayMap;
	
	public static Map<DecayBlocks, Integer> decayWeights;
	
	static {
		decayMap = new HashMap<Block, DecayBlocks>();
		decayMap.put(Blocks.STONE_BRICKS, STONEBRICK);
		decayMap.put(Blocks.CRACKED_STONE_BRICKS, CRACKEDBRICK);
		decayMap.put(Blocks.MOSSY_STONE_BRICKS, MOSSYBRICK);
		decayMap.put(Blocks.COBBLESTONE, COBBLESTONE);
		decayMap.put(Blocks.MOSSY_COBBLESTONE, MOSSYCOBBLE);
		decayMap.put(Blocks.GRAVEL, GRAVEL);
		
		decayWeights = new HashMap<DecayBlocks, Integer>();
		decayWeights.put(STONEBRICK, 0);
		decayWeights.put(CRACKEDBRICK, 16);
		decayWeights.put(MOSSYBRICK, 32);
		decayWeights.put(COBBLESTONE, 64);
		decayWeights.put(MOSSYCOBBLE, 64);
		decayWeights.put(GRAVEL, 128);
		
	}
	
	public static BlockState getBlock(DecayBlocks type) {
		switch(type) {
		case STONEBRICK: return Blocks.STONE_BRICKS.getDefaultState();
		case CRACKEDBRICK: return Blocks.CRACKED_STONE_BRICKS.getDefaultState();
		case MOSSYBRICK: return Blocks.MOSSY_STONE_BRICKS.getDefaultState();
		case COBBLESTONE: return Blocks.COBBLESTONE.getDefaultState();
		case MOSSYCOBBLE: return Blocks.MOSSY_COBBLESTONE.getDefaultState();
		case GRAVEL: return Blocks.GRAVEL.getDefaultState();
		default: return Blocks.STONE_BRICKS.getDefaultState();
		}
	}
	
	public static boolean canDecay(BlockState state) {
		if(!decayMap.containsKey(state.getBlock())) return false;
		DecayBlocks type = decayMap.get(state.getBlock());
		return Arrays.asList(decayable).contains(type);
	}
	
	public static int getWeight(BlockState state) {
		if(!decayMap.containsKey(state.getBlock())) return 0;
		DecayBlocks block = decayMap.get(state.getBlock());
		return decayWeights.get(block);
	}
	
	public static int measureAreaDecay(World world, BlockPos pos, int range) {
		BlockPos start = new BlockPos(pos.getX() - range, pos.getY() - range, pos.getZ() - range);
		
		int countBlocks = 0;
		int totalDecay = 0;
		
		for(int x = start.getX(); x < pos.getX() + range; ++x) {
			for(int y = start.getY(); y < pos.getY() + range; ++y) {
				for(int z = start.getZ(); z < pos.getZ() + range; ++z) {
					BlockState b = world.getBlockState(new BlockPos(x, y, z));
					if(!decayMap.containsKey(b.getBlock())) continue;
					
					++countBlocks;
					totalDecay += decayWeights.get(decayMap.get(b.getBlock()));
				}
			}
		}
		
		if(countBlocks == 0) return 0;
		return totalDecay / countBlocks;
	}
	
	public static void decay(World world, Random rand, BlockPos pos) {
		if(pos.getY() < -60) return;
		
		Block toReplace = world.getBlockState(pos).getBlock();
		if(!decayMap.containsKey(toReplace)) return;
		DecayBlocks type = decayMap.get(toReplace);
		if(type == STONEBRICK) {
			if(rand.nextInt(3) == 0) {
				world.setBlockState(pos, getBlock(MOSSYBRICK));
			} else {
				world.setBlockState(pos, getBlock(CRACKEDBRICK));
			}
		}
	}
}
