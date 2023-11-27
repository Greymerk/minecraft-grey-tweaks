package com.greymerk.tweaks.mixin;

import java.util.Arrays;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;

@Mixin(ServerWorld.class)
public class PinkPetalMixin{
	
	private static Block[] canGrow = {
		Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.MYCELIUM, 
		Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT,
		Blocks.MOSS_BLOCK, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS,
		Blocks.FARMLAND
	};
	
	@Inject(at = @At("HEAD"), method = "tickChunk(Lnet/minecraft/world/chunk/WorldChunk;I)V")
	private void tickChunk(WorldChunk chunk, int randomTickSpeed, CallbackInfo info) {
		
		int petalChance = 200;
		World world = chunk.getWorld();
		Random rand = world.getRandom();
		ChunkPos chunkPos = chunk.getPos();
        int worldX = chunkPos.getStartX();
        int worldZ = chunkPos.getStartZ();
        
        if (randomTickSpeed <= 0) return;
        if (rand.nextInt(petalChance) != 0) return;
        
        ChunkSection[] chunkSections = chunk.getSectionArray();
        
        for (int i = 0; i < chunkSections.length; ++i) {
            int sectionCoord = chunk.sectionIndexToCoord(i);
            int sectionY = ChunkSectionPos.getBlockCoord(sectionCoord);
            for (int l = 0; l < randomTickSpeed; ++l) {
                BlockPos pos = world.getRandomPosInChunk(worldX, sectionY, worldZ, 15);
                petal(world, rand, pos);
            }
        }  	
	}
	
	private void petal(World world, Random rand, BlockPos pos) {
		int daylight = world.getLightLevel(LightType.SKY, pos);
		if(daylight == 0) return;
		
		Block blockBelow = world.getBlockState(pos.down()).getBlock();
		if(!Arrays.asList(canGrow).contains(blockBelow)) return;
		if(!isCherryAbove(world, pos, 3)) return;
		if(countNearbyPetals(world, pos, 2) > 5) return;
		
		addPetals(world, pos); 
	}
	
	private boolean isCherryAbove(World world, BlockPos pos, int range) {
		for(int x = pos.getX() - range; x < pos.getX() + range; ++x) {
			for(int y = pos.getY() + 2; y < pos.getY() + 8; ++y) {
				for(int z = pos.getZ() - range; z < pos.getZ() + range; ++z) {
					BlockPos bp = new BlockPos(x, y, z);
					Block toCheck = world.getBlockState(bp).getBlock();
					if(toCheck == Blocks.CHERRY_LEAVES) return true;	
				}
			}
		}
		return false;
	}
	
	public int countNearbyPetals(World world, BlockPos pos, int range) {
		int petalCount = 0;
		for(int x = pos.getX() - range; x < pos.getX() + range; ++x) {
			for(int y = pos.getY() - range; y < pos.getY() + range; ++y) {
				for(int z = pos.getZ() - range; z < pos.getZ() + range; ++z) {
					BlockPos bp = new BlockPos(x, y, z);
					BlockState toCheck = world.getBlockState(bp);
					if(toCheck.getBlock() == Blocks.PINK_PETALS) {
						petalCount += toCheck.get(FlowerbedBlock.FLOWER_AMOUNT);
					}
				}
			}
		}
		return petalCount;
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
