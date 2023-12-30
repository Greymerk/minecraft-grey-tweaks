package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

@Mixin(MobEntity.class)
public class MobEnchantMixin {	

	@Inject(at = @At("HEAD"), method = "enchantMainHandItem(Lnet/minecraft/util/math/random/Random;F)V", cancellable = true)
	protected void enchantMainHandItem(Random random, float power, CallbackInfo cir) {
		MobEntity entity = (MobEntity)(Object)this;
		if(entity.getMainHandStack().isEmpty()) {
			cir.cancel();
		}
		
		ItemStack item = entity.getMainHandStack();
		if(item.hasEnchantments()) {
			cir.cancel();
		}
	}
			
	@Inject(at = @At("HEAD"), method = "enchantEquipment(Lnet/minecraft/util/math/random/Random;FLnet/minecraft/entity/EquipmentSlot;)V", cancellable = true)
	protected void enchantEquipment(Random random, float power, EquipmentSlot slot, CallbackInfo cir) {
		MobEntity entity = (MobEntity)(Object)this;
		
		if(!entity.hasStackEquipped(slot)) {
			cir.cancel();
		}
		
		ItemStack item = entity.getEquippedStack(slot);
		
		if(item.hasEnchantments()) {
			cir.cancel();
		}
	}	
}
