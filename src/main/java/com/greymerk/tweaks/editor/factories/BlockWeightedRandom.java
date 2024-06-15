package com.greymerk.tweaks.editor.factories;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IBlockFactory;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.util.WeightedChoice;
import com.greymerk.tweaks.util.WeightedRandomizer;

import net.minecraft.util.math.random.Random;

public class BlockWeightedRandom extends BlockBase {

	private WeightedRandomizer<IBlockFactory> blocks;
	
	public BlockWeightedRandom(){
		blocks = new WeightedRandomizer<IBlockFactory>();
	}

	public BlockWeightedRandom addBlock(IBlockFactory toAdd, int weight){
		blocks.add(new WeightedChoice<IBlockFactory>(toAdd, weight));
		return this;
	}

	@Override
	public boolean set(IWorldEditor editor, Random rand, Coord origin, boolean fillAir, boolean replaceSolid) {
		IBlockFactory block = blocks.get(rand);
		return block.set(editor, rand, origin, fillAir, replaceSolid);
	}
}
