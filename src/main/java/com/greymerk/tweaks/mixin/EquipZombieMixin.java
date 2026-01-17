package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.monster.IEntity;
import com.greymerk.tweaks.monster.MetaEntity;
import com.greymerk.tweaks.monster.MonsterProfile;
import com.greymerk.tweaks.util.MoonHelper;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

@Mixin(ZombieEntity.class)
public class EquipZombieMixin{

	@Inject(at = @At("TAIL"), method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V", cancellable = true)
	protected void initEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo cir) {
		
		MobEntity entity = (MobEntity)(Object)this;
		World world = entity.getEntityWorld();
		Difficulty diff = MoonHelper.getDiff(world);
		
		if(!doEquip(random, diff)) return;
		
		IEntity mob = new MetaEntity(entity);
		MonsterProfile.get(MonsterProfile.ZOMBIE).addEquipment(world, random, diff, mob);
		cir.cancel();
	}
	
	private boolean doEquip(Random rand, Difficulty diff) {		
		if(diff == Difficulty.HARDEST) return true;
		if(diff == Difficulty.HARD) return rand.nextInt(3) != 0;
		if(diff == Difficulty.MEDIUM) return rand.nextBoolean();
		if(diff == Difficulty.EASY) return rand.nextInt(3) == 0;
		if(diff == Difficulty.EASIEST) return rand.nextInt(5) == 0;
		return false;
	}
}
