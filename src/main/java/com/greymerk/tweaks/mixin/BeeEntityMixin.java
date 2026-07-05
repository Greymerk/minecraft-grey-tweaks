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

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.LegacyRandomSource;



@Mixin(Bee.class)
public class BeeEntityMixin {

    boolean hasSpread = true;

    @Inject (
            method = "tick",
            at = @At("HEAD")
    )
	
    public void tick(CallbackInfo info) {
    	
    	Bee bee = (Bee)(Object)this;
    	
    	if(!bee.hasNectar()) {
    		if(hasSpread) {
    			hasSpread = false;
    		}
    		return;
    	}
    	
    	if(!bee.hasSavedFlowerPos()) return;    	
    	if(bee.hasNectar() && hasSpread) return;
    	
    	Coord flowerPos = Coord.of(bee.getSavedFlowerPos());
    	
    	
    	Level world = bee.level();
    	
    	IWorldEditor editor = WorldEditor.of(world);
    	
    	if(!editor.isOverworld()) {
    		return;
    	}
    	
    	long seed = Objects.hash(flowerPos, bee.level().getGameTime());
    	RandomSource rand = new LegacyRandomSource(seed);
    	
    	MetaBlock flower = editor.getBlock(flowerPos);
    	
    	if(!flower.isIn(BlockTags.SMALL_FLOWERS)) return;
    	
    	//good so far
    	
    	Optional<Coord> o = findViableGrowLocation(editor, rand, flowerPos, flower);
    	if(o.isEmpty() || rand.nextBoolean()) {
    		this.hasSpread = true;
    		return;
    	}
    	    		
    	Coord growPos = o.get();
    	editor.set(growPos, flower);
    	
    	this.produceParticles(editor.getServerWorld(), rand, growPos, ParticleTypes.HAPPY_VILLAGER);
    	SoundEvent se = flower.getState().getSoundType().getPlaceSound();
    	world.playSound(null, growPos.getBlockPos(), se, SoundSource.BLOCKS);
    	this.hasSpread = true;
    }
    
    private Optional<Coord> findViableGrowLocation(IWorldEditor editor, RandomSource rand, Coord flowerPos, MetaBlock flower) {
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
    		if(ground.isIn(List.of(BlockTags.DIRT, BlockTags.MUD, BlockTags.GRASS_BLOCKS))) viable.add(c);
    	});
    	
    	if(viable.isEmpty()) return Optional.empty();
    	
    	if(flowerCount.get() > MAX_FLOWERS) return Optional.empty();
    	
    	RandHelper.shuffle(viable, rand);
    	
    	return Optional.of(viable.getFirst());
    }
    
    private void produceParticles(ServerLevel sw, RandomSource rand, Coord pos, SimpleParticleType params) {
		double spreadWidth = 0.5;
    	double spreadHeight = 0.2;
    	int count = 3;
    	
    	for(int i = 0; i < 5; ++i) {
    		double spreadStartOffset = 0.5 - spreadWidth;
			double x = pos.getX() + spreadStartOffset + rand.nextDouble() * spreadWidth * 2.0;
			double y = pos.getY() + rand.nextDouble() * spreadHeight;
			double z = pos.getZ() + spreadStartOffset + rand.nextDouble() * spreadWidth * 2.0;
    		double xv = rand.nextGaussian() * 0.02;
			double yv = rand.nextGaussian() * 0.02;
			double zv = rand.nextGaussian() * 0.02;
			sw.sendParticles(params, x, y, z, count, xv, yv, zv, spreadHeight);
    	}
	}

}	
