package com.greymerk.tweaks.editor.factories;

import java.util.ArrayList;
import java.util.List;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IBlockFactory;
import com.greymerk.tweaks.editor.IWorldEditor;

import net.minecraft.util.math.random.Random;

public class BlockColumns extends BlockBase{

	private List<IBlockFactory> blocks;
	
	public BlockColumns(){
		blocks = new ArrayList<IBlockFactory>();
	}
	
	public void addBlock(IBlockFactory toAdd){
		blocks.add(toAdd);
	}
	
	@Override
	public boolean set(IWorldEditor editor, Random rand, Coord pos, boolean fillAir, boolean replaceSolid) {
		int size = blocks.size();
		int choice = Math.abs((pos.getX() % size + pos.getZ() % size)) % size;
		IBlockFactory block = blocks.get(choice);
		return block.set(editor, rand, pos, fillAir, replaceSolid);
	}

}
