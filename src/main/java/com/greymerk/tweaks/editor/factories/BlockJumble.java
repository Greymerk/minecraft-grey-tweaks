package com.greymerk.tweaks.editor.factories;

import java.util.ArrayList;
import java.util.List;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IBlockFactory;
import com.greymerk.tweaks.editor.IWorldEditor;

import net.minecraft.util.math.random.Random;

public class BlockJumble extends BlockBase {

	private List<IBlockFactory> blocks;
	
	public BlockJumble(){
		blocks = new ArrayList<IBlockFactory>();
	}

	public BlockJumble addBlock(IBlockFactory toAdd){
		blocks.add(toAdd);
		return this;
	}

	@Override
	public boolean set(IWorldEditor editor, Random rand, Coord origin, boolean fillAir, boolean replaceSolid) {
		IBlockFactory block = blocks.get(rand.nextInt(blocks.size()));
		return block.set(editor, rand, origin, fillAir, replaceSolid);
	}

}
