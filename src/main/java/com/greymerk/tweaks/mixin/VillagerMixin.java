package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.npc.villager.Villager;

@Mixin(Villager.class)
public class VillagerMixin {
	@Inject(at = @At("HEAD"), method = "golemSpawnConditionsMet(J)Z", cancellable = true)
	private void injectGolemSpawnConditionsMet(long time, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
}
