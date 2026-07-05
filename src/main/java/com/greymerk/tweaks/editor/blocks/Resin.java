package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;


public class Resin {

	public static void set(IWorldEditor editor, Coord pos, Cardinal dir) {
		MetaBlock resin = MetaBlock.of(Blocks.RESIN_CLUMP);
		resin.with(MultifaceBlock.getFaceProperty(Cardinal.facing(Cardinal.reverse(dir))), true);
		resin.set(editor, pos);
	}
	
}
