package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public enum Campfire {

	NATURAL, SOUL;
	
	public static void generate(IWorldEditor editor, Coord origin, Campfire type) {
		MetaBlock.of(fromType(type)).set(editor, origin);
	}
	
	public static Block fromType(Campfire type) {
		switch(type) {
		case NATURAL: return Blocks.CAMPFIRE;
		case SOUL: return Blocks.SOUL_CAMPFIRE;
		default: return Blocks.CAMPFIRE;
		}
	}
	
}
