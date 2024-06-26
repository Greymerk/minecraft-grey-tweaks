package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public enum Anvil {

	ANVIL, CHIPPED, DAMAGED;
	
	public static MetaBlock get(Anvil damage, Cardinal dir){
		return MetaBlock.of(getFromDamage(damage)).with(AnvilBlock.FACING, Cardinal.facing(dir));
	}
	
	private static Block getFromDamage(Anvil damage) {
		switch(damage){
		case ANVIL: return Blocks.ANVIL;
		case CHIPPED: return Blocks.CHIPPED_ANVIL;
		case DAMAGED: return Blocks.DAMAGED_ANVIL;
		default: return Blocks.ANVIL;
		}
	}
	
}
