package com.greymerk.tweaks.treasure.loot.items;

import java.util.ArrayList;
import java.util.List;

import com.greymerk.tweaks.util.Color;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.block.entity.BannerPatterns;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.BannerPatternsComponent.Layer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;

public class Banner {

	public static RegistryKey<BannerPattern> getRandomPattern(Random rand) {
		final List<RegistryKey<BannerPattern>> PATTERNS = List.of(
				BannerPatterns.BORDER,
				BannerPatterns.BRICKS,
				BannerPatterns.CIRCLE,
				BannerPatterns.CREEPER,
				BannerPatterns.CROSS,
				BannerPatterns.CURLY_BORDER,
				BannerPatterns.DIAGONAL_LEFT,
				BannerPatterns.DIAGONAL_RIGHT,
				BannerPatterns.DIAGONAL_UP_LEFT,
				BannerPatterns.DIAGONAL_UP_RIGHT,
				BannerPatterns.FLOWER,
				BannerPatterns.GRADIENT,
				BannerPatterns.GRADIENT_UP,
				BannerPatterns.HALF_HORIZONTAL,
				BannerPatterns.HALF_HORIZONTAL_BOTTOM,
				BannerPatterns.HALF_VERTICAL,
				BannerPatterns.HALF_VERTICAL_RIGHT,
				BannerPatterns.MOJANG,
				BannerPatterns.PIGLIN,
				BannerPatterns.RHOMBUS,
				BannerPatterns.SKULL,
				BannerPatterns.SMALL_STRIPES,
				BannerPatterns.SQUARE_BOTTOM_LEFT,
				BannerPatterns.SQUARE_BOTTOM_RIGHT,
				BannerPatterns.SQUARE_TOP_LEFT,
				BannerPatterns.SQUARE_TOP_RIGHT,
				BannerPatterns.STRAIGHT_CROSS,
				BannerPatterns.STRIPE_BOTTOM,
				BannerPatterns.STRIPE_CENTER,
				BannerPatterns.STRIPE_DOWNLEFT,
				BannerPatterns.STRIPE_DOWNRIGHT,
				BannerPatterns.STRIPE_LEFT,
				BannerPatterns.STRIPE_MIDDLE,
				BannerPatterns.STRIPE_RIGHT,
				BannerPatterns.STRIPE_TOP,
				BannerPatterns.TRIANGLE_BOTTOM,
				BannerPatterns.TRIANGLE_TOP,
				BannerPatterns.TRIANGLES_BOTTOM,
				BannerPatterns.TRIANGLES_TOP
		);
		
		return PATTERNS.get(rand.nextInt(PATTERNS.size()));
	}
	
	public static ItemStack get(DynamicRegistryManager reg, Random rand){
		Registry<BannerPattern> patterns = reg.getOrThrow(RegistryKeys.BANNER_PATTERN);
		
		ItemStack banner = new ItemStack(Items.BLACK_BANNER);
		banner.set(DataComponentTypes.BANNER_PATTERNS, createLayersComponent(patterns, rand, rand.nextInt(3) + 3));
		return banner;
	}
	
	public static BannerPatternsComponent createLayersComponent(Registry<BannerPattern> patterns, Random rand, int count) {
		List<Layer> layers = new ArrayList<Layer>();
		BannerPatternsComponent component = new BannerPatternsComponent(layers);
		layers.add(createLayer(patterns, BannerPatterns.BASE, Color.get(rand)));
		
		for(int i = 0; i < count; ++i) {
			RegistryKey<BannerPattern> key = Banner.getRandomPattern(rand);
			Layer toAdd = createLayer(patterns, key, Color.get(rand));
			layers.add(toAdd);	
		}
		
		return component;
	}
	
	public static Layer createLayer(Registry<BannerPattern> reg, RegistryKey<BannerPattern> key, Color c) {
		RegistryEntry<BannerPattern> entry = reg.getOrThrow(key);
		return new Layer(entry, Color.get(c));
	}
		
}