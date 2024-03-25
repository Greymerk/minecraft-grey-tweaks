package com.greymerk.tweaks.editor;

import com.greymerk.tweaks.editor.shapes.IShape;

import net.minecraft.util.math.random.Random;

public interface IBlockFactory {
	
	public boolean set(IWorldEditor editor, Random rand, Coord pos);
	
	public boolean set(IWorldEditor editor, Random rand, Coord pos, boolean fillAir, boolean replaceSolid);
	
	public void fill(IWorldEditor editor, Random rand, IShape shape, boolean fillAir, boolean replaceSolid);
	
	public void fill(IWorldEditor editor, Random rand, IShape shape);
	
}
