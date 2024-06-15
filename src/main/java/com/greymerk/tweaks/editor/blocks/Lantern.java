package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;

public enum Lantern {

	SOUL, FLAME;
	
	public static void set(IWorldEditor editor, Coord origin) {
		Lantern.set(editor, origin, FLAME, true);
	}
	
	public static void set(IWorldEditor editor, Coord origin, Lantern type, boolean hang) {
		if(!editor.getBlock(origin).isReplaceable()) return;
		MetaBlock.of(fromType(type).getDefaultState())
			.with(LanternBlock.HANGING, hang)
			.set(editor, origin);
	}
	
	public static Block fromType(Lantern type) {
		switch(type) {
		case FLAME: return Blocks.LANTERN;
		case SOUL: return Blocks.SOUL_LANTERN;
		default: return Blocks.LANTERN;
		
		}
	}
	
}
