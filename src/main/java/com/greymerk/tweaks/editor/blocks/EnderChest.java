package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.block.Blocks;
import net.minecraft.block.EnderChestBlock;

public class EnderChest {
	public static void set(IWorldEditor editor, Cardinal dir, Coord pos){
		MetaBlock.of(Blocks.ENDER_CHEST)
			.with(EnderChestBlock.FACING, Cardinal.facing(Cardinal.reverse(dir)))
			.set(editor, pos);
	}
}
