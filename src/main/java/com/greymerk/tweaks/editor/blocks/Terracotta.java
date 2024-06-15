package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.util.Color;

import net.minecraft.block.GlazedTerracottaBlock;

public class Terracotta {
	public static MetaBlock get(Color color, Cardinal dir){
		return ColorBlock.get(ColorBlock.GLAZED, color)
				.with(GlazedTerracottaBlock.FACING, Cardinal.facing(dir));
	}
}
