package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;

@Mixin(MobEntity.class)
public class MobEnchantMixin {	

	@Inject(at = @At("HEAD"), method = "enchantMainHandItem(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V", cancellable = true)
	protected void enchantMainHandItem(ServerWorldAccess world, Random random, LocalDifficulty diff, CallbackInfo cir) {
		MobEntity entity = (MobEntity)(Object)this;
		if(entity.getMainHandStack().isEmpty()) {
			cir.cancel();
		}
		
		ItemStack item = entity.getMainHandStack();
		if(item.hasEnchantments()) {
			cir.cancel();
		}
	}
			
	@Inject(at = @At("HEAD"), method = "enchantEquipment(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/world/LocalDifficulty;)V", cancellable = true)
	protected void enchantEquipment(ServerWorldAccess world, Random random, EquipmentSlot slot, LocalDifficulty diff, CallbackInfo cir) {
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
