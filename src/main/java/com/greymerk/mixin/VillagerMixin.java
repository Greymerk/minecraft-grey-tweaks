package com.greymerk.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.passive.VillagerEntity;

@Mixin(VillagerEntity.class)
public class VillagerMixin {
	@Inject(at = @At("HEAD"), method = "canSummonGolem(J)Z", cancellable = true)
	private void injectCanSummonGolem(long time, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
}
