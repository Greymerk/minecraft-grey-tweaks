package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.InfinityEnchantment;

@Mixin(InfinityEnchantment.class)
public class InfinityMixin extends Enchantment{
	protected InfinityMixin(Enchantment.Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Inject(at = @At("HEAD"), method = "canAccept(Lnet/minecraft/enchantment/Enchantment;)Z", cancellable = true)
	private void injectInfinityCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(super.canAccept(other));
	}
}