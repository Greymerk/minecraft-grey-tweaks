package com.greymerk.tweaks.editor;

import com.greymerk.tweaks.editor.shapes.IShape;

import net.minecraft.util.RandomSource;

public interface IBlockFactory {
	
	public boolean set(IWorldEditor editor, RandomSource rand, Coord pos);
	
	public boolean set(IWorldEditor editor, RandomSource rand, Coord pos, boolean fillAir, boolean replaceSolid);
	
	public void fill(IWorldEditor editor, RandomSource rand, IShape shape, boolean fillAir, boolean replaceSolid);
	
	public void fill(IWorldEditor editor, RandomSource rand, IShape shape);
	
}
