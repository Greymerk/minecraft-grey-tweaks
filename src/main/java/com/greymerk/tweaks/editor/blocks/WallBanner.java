package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.treasure.loot.items.Banner;

import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

public class WallBanner {
	
	public static void generate(IWorldEditor editor, Random rand, Coord origin, Cardinal dir) {
		WallBanner.generate(editor, Banner.get(editor.getRegistryManager(), rand), origin, dir);
	}
	
	public static void generate(IWorldEditor editor, ItemStack banner, Coord origin, Cardinal dir) {
		
		MetaBlock.of(Blocks.BLACK_WALL_BANNER)
			.with(HorizontalFacingBlock.FACING, Cardinal.facing(dir))
			.set(editor, origin);
		
		BlockEntity be = editor.getBlockEntity(origin);
		if(be == null) return;
		if(!(be instanceof BannerBlockEntity)) return;
		BannerBlockEntity bannerEntity = (BannerBlockEntity)be;
		bannerEntity.readComponents(banner);
		bannerEntity.markDirty();
	}	
}
