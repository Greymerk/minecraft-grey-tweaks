package com.greymerk.tweaks.editor.factories;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IBlockFactory;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.shapes.IShape;

import net.minecraft.util.math.random.Random;

public abstract class BlockBase implements IBlockFactory {
	
	@Override
	public boolean set(IWorldEditor editor, Random rand, Coord pos) {
		return set(editor, rand, pos, true, true);
	}
	
	@Override
	public void fill(IWorldEditor editor, Random rand, IShape shape){
		shape.fill(editor, rand, this, true, true);
	}
	
	@Override
	public void fill(IWorldEditor editor, Random rand, IShape shape, boolean fillAir, boolean replaceSolid){
		shape.fill(editor, rand, this, fillAir, replaceSolid);
	}
}
