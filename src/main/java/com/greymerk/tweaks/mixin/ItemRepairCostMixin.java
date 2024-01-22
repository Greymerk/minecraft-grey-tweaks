package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.item.ItemStack;

@Mixin(ItemStack.class)
public class ItemRepairCostMixin {

	@Inject(at = @At("HEAD"), method = "Lnet/minecraft/item/ItemStack;getRepairCost()I", cancellable = true)
	private void injectRepairCost(CallbackInfoReturnable<Integer> cir){
		cir.setReturnValue(0);
	}
}
