package com.greymerk.tweaks.treasure.loot.trim;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimPattern;

public class Trim {

    
	public static ItemStack addRandom(RegistryAccess registry, ItemStack item, RandomSource rand) {
	    return set(registry, item, TrimPatternEnum.getRandom(rand), TrimMaterialEnum.getRandom(rand));
	}
	
	public static ItemStack set(RegistryAccess registry, ItemStack item, TrimPatternEnum pat, TrimMaterialEnum mat) {
		
		Holder<TrimPattern> pattern = TrimPatternEnum.getEntry(registry, pat);
	    Holder<TrimMaterial> material = TrimMaterialEnum.getEntry(registry, mat);
	    
	    ArmorTrim at = new ArmorTrim(material, pattern);
	    
	    item.set(DataComponents.TRIM, at);
	    
	    return item;
	}
	
}