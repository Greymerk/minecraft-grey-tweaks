package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;

public enum Button {

	STONE, OAK;
	
	public static Block fromType(Button type) {
		switch(type) {
		case OAK: return Blocks.OAK_BUTTON;
		case STONE: return Blocks.STONE_BUTTON;
		default: return Blocks.STONE_BUTTON;
		
		}
	}
	
	public static void generate(IWorldEditor editor, Coord origin, Cardinal dir, Button type) {
		MetaBlock.of(fromType(type))
			.with(ButtonBlock.FACING, Cardinal.facing(Cardinal.reverse(dir)))
			.set(editor, origin);
	}
	
}
