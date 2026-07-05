package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerLevelAccessor;



@Mixin(Mob.class)
public class MobEnchantMixin {	

	@Inject(at = @At("HEAD"), method = "enchantSpawnedWeapon", cancellable = true)
	protected void enchantMainHandItem(ServerLevelAccessor world, RandomSource random, DifficultyInstance diff, CallbackInfo cir) {
		Mob entity = (Mob)(Object)this;
		if(entity.getMainHandItem().isEmpty()) {
			cir.cancel();
		}
		
		ItemStack item = entity.getMainHandItem();
		if(item.getEnchantments().isEmpty()) {
			cir.cancel();
		}
	}
			
	@Inject(at = @At("HEAD"), method = "enchantSpawnedArmor", cancellable = true)
	protected void enchantEquipment(ServerLevelAccessor world, RandomSource random, EquipmentSlot slot, DifficultyInstance diff, CallbackInfo cir) {
		Mob entity = (Mob)(Object)this;
		
		if(!entity.hasItemInSlot(slot)) {
			cir.cancel();
		}
		
		ItemStack item = entity.getItemBySlot(slot);
		
		if(item.getEnchantments().isEmpty()) {
			cir.cancel();
		}
	}	
}
