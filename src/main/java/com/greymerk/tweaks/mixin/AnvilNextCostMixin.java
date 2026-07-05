package com.greymerk.tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.inventory.AnvilMenu;

@Mixin(AnvilMenu.class)
public class AnvilNextCostMixin {
    @Inject(method = "getCost", at = @At("RETURN"), cancellable = true)
    private static void getNextCostMixin(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }
}
