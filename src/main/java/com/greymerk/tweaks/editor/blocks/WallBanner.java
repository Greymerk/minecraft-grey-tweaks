package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.treasure.loot.items.Banner;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BannerBlockEntity;

public class WallBanner {
	
	public static void generate(IWorldEditor editor, RandomSource rand, Coord origin, Cardinal dir) {
		WallBanner.generate(editor, Banner.get(editor.getRegistryManager(), rand), origin, dir);
	}
	
	public static void generate(IWorldEditor editor, ItemStack banner, Coord origin, Cardinal dir) {
		editor.setBlockEntity(origin,
			MetaBlock.of(Blocks.BLACK_WALL_BANNER)
				.with(HorizontalDirectionalBlock.FACING, Cardinal.facing(dir)), 
			BannerBlockEntity.class).ifPresent(bannerEntity -> {
				bannerEntity.applyComponentsFromItemStack(banner);
				bannerEntity.setChanged();	
			});
	}		
}
