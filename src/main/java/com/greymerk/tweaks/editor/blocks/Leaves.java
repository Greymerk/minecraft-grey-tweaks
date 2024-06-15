package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.block.LeavesBlock;

public class Leaves {
	
	public static MetaBlock get(Wood type, boolean persistent){
		return MetaBlock.of(Wood.getLeaf(type)).with(LeavesBlock.PERSISTENT, persistent);
	}
}
