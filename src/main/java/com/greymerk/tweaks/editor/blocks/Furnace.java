package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.block.Blocks;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.item.ItemStack;

public class Furnace {

	public static final int FUEL_SLOT = 1;
	public static final int OUTPUT_SLOT = 2;
	
	public static void generate(IWorldEditor editor, Cardinal dir, Coord pos){
		generate(editor, dir, pos, false, ItemStack.EMPTY);
	}
	
	public static void generate(IWorldEditor editor, boolean lit, Cardinal dir, Coord pos){
		generate(editor, dir, pos, lit, ItemStack.EMPTY);
	}
	
	public static void generate(IWorldEditor editor, Cardinal dir, Coord pos, boolean lit, ItemStack fuel){
		MetaBlock.of(Blocks.FURNACE)
			.with(FurnaceBlock.LIT, lit)
			.with(FurnaceBlock.FACING, Cardinal.facing(Cardinal.reverse(dir)))
			.set(editor, pos);
		
		BlockEntity te = editor.getBlockEntity(pos);
		if(te == null) return;
		if(!(te instanceof FurnaceBlockEntity)) return;
		FurnaceBlockEntity teFurnace = (FurnaceBlockEntity)te;
		teFurnace.setStack(FUEL_SLOT, fuel);
	}
}
