package com.greymerk.tweaks.editor.blocks.stair;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.shapes.IShape;

import net.minecraft.util.math.random.Random;

public interface IStair {

	public IStair setOrientation(Cardinal dir, Boolean upsideDown);
	
	public boolean set(IWorldEditor editor, Random rand, Coord pos);
	
	public boolean set(IWorldEditor editor, Random rand, Coord pos, boolean fillAir, boolean replaceSolid);

	public void fill(IWorldEditor editor, Random rand, IShape shape, boolean fillAir, boolean replaceSolid);
	
	public void fill(IWorldEditor editor, Random rand, IShape shape);
}
