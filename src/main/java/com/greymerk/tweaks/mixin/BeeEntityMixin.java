package com.greymerk.tweaks.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.editor.WorldEditor;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;
import com.greymerk.tweaks.util.math.RandHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

@Mixin(BeeEntity.class)
public class BeeEntityMixin {

    boolean hasSpread = true;

    @Inject (
            method = "tick()V",
            at = @At("HEAD")
    )
	
    public void tick(CallbackInfo info) {
    	BeeEntity bee = (BeeEntity)(Object)this;
    	
    	if(!bee.hasNectar()) {
    		if(hasSpread) {
    			hasSpread = false;
    		}
    		return;
    	}
    	if(!bee.hasFlower()) return;
    	if(bee.hasNectar() && hasSpread) return;
    	
    	
    	Coord flowerPos = Coord.of(bee.getFlowerPos());
        
    	World world = bee.getEntityWorld();
    	if(!isOverworld(world)) {
    		return;
    	}
    	
    	Random rand = new CheckedRandom(Objects.hash(flowerPos, bee.getWorld().getTime()));
    	IWorldEditor editor = new WorldEditor(world);
    	
    	MetaBlock flower = editor.getBlock(flowerPos);
    	if(!flower.isIn(BlockTags.SMALL_FLOWERS)) return;
    	
    	Optional<Coord> o = findViableGrowLocation(editor, rand, flowerPos, flower);
    	if(o.isEmpty() || rand.nextBoolean()) {
    		this.hasSpread = true;
    		return;
    	}
    	    		
    	Coord growPos = o.get();
    	editor.set(growPos, flower);
    	
    	ServerWorld sw = editor.getServerWorld();
    	
    	this.produceParticles(sw, rand, growPos, ParticleTypes.HAPPY_VILLAGER);
    	world.playSound(null, growPos.getBlockPos(), flower.getState().getSoundGroup().getPlaceSound(), SoundCategory.BLOCKS);
    	this.hasSpread = true;
    }
    
    private Optional<Coord> findViableGrowLocation(IWorldEditor editor, Random rand, Coord flowerPos, MetaBlock flower) {
    	final int FLOWER_SPREAD_DISTANCE = 4;
    	final int MAX_FLOWERS = 4;
    	
    	BoundingBox bb = BoundingBox.of(flowerPos)
    			.grow(Cardinal.directions, FLOWER_SPREAD_DISTANCE)
    			.grow(Cardinal.UP).grow(Cardinal.DOWN);
    	List<Coord> viable = new ArrayList<Coord>();
    	
    	AtomicReference<Integer> flowerCount = new AtomicReference<Integer>();
    	flowerCount.set(0);
    	
    	bb.forEach(c -> {
    		Block b = editor.getBlock(c).getBlock();
    		if(b == flower.getBlock()) flowerCount.set(flowerCount.get() + 1);
    		
    		if(!editor.isAir(c)) return;
    		Coord below = c.copy().add(Cardinal.DOWN);
    		MetaBlock ground = editor.getBlock(below);
    		if(ground.isIn(BlockTags.DIRT)) viable.add(c);
    	});
    	
    	if(viable.isEmpty()) return Optional.empty();
    	
    	if(flowerCount.get() > MAX_FLOWERS) return Optional.empty();
    	
    	RandHelper.shuffle(viable, rand);
    	
    	return Optional.of(viable.getFirst());
    }
    
    private void produceParticles(ServerWorld sw, Random rand, Coord pos, ParticleEffect parameters) {
		
		double d = rand.nextGaussian() * 0.02;
		double e = rand.nextGaussian() * 0.02;
		double f = rand.nextGaussian() * 0.02;
		double x = (double)pos.getX() + 0.5;
		double y = (double)pos.getY() + 0.5;
		double z = (double)pos.getZ() + 0.5;
		sw.spawnParticles(parameters, x, y, z, 20, d, e, f, 0.3);
		
	}
    
    private boolean isOverworld(World world) {
    	RegistryEntry<DimensionType> dimEntry = world.getDimensionEntry();
    	Optional<RegistryKey<DimensionType>> optDimKey = dimEntry.getKey();
    	if(optDimKey.isEmpty()) return false;
    	RegistryKey<DimensionType> dimKey = optDimKey.get();
    	return dimKey == DimensionTypes.OVERWORLD;
    }
}	
