package com.greymerk.tweaks.treasure.chest;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.treasure.Treasure;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;



public interface ITreasureChest {
	
	public ITreasureChest generate(IWorldEditor editor, RandomSource rand, Coord pos, ChestType type) throws ChestPlacementException;
	
	public ITreasureChest generate(IWorldEditor editor, RandomSource rand, Coord pos, Cardinal dir, ChestType type) throws ChestPlacementException;
	
	public boolean setSlot(int slot, ItemStack item);
	
	public boolean setRandomEmptySlot(ItemStack item);
	
	public void setLootTable(Identifier table);
	
	public boolean isEmptySlot(int slot);
	
	public Treasure getType();
	
	public int getSize();
	
	public Difficulty getLevel();

}