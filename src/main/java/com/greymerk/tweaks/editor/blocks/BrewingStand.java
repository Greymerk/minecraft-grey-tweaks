package com.greymerk.tweaks.editor.blocks;

import java.util.List;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.ItemStack;

public enum BrewingStand {

	LEFT(0), MIDDLE(1), RIGHT(2), INGREDIENT(3), FUEL(4);
	
	public static final List<BrewingStand> slots = List.of(LEFT, MIDDLE, RIGHT);
	
	private int id;
	BrewingStand(int id){
		this.id = id;
	}
	
	public static boolean generate(IWorldEditor editor, Coord pos){
		MetaBlock stand = MetaBlock.of(Blocks.BREWING_STAND);
		return stand.set(editor, pos);
	}
	
	public static boolean add(IWorldEditor editor, Coord pos, BrewingStand slot, ItemStack item){
		BrewingStandBlockEntity stand = get(editor, pos);
		if(stand == null) return false;
		stand.setStack(slot.id, item);
		return true;
	}
	
	private static BrewingStandBlockEntity get(IWorldEditor editor, Coord pos){
		MetaBlock stand = editor.getBlock(pos);
		if(stand.getBlock() != Blocks.BREWING_STAND) return null;
		BlockEntity te = editor.getBlockEntity(pos);
		if(te == null) return null;
		if(!(te instanceof BrewingStandBlockEntity)) return null;
		BrewingStandBlockEntity brewingTE = (BrewingStandBlockEntity)te;
		return brewingTE;
	}
}
