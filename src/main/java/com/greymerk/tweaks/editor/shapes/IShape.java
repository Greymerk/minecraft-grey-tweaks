package com.greymerk.tweaks.editor.shapes;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IBlockFactory;
import com.greymerk.tweaks.editor.IWorldEditor;

import net.minecraft.util.math.random.Random;

public interface IShape extends Iterable<Coord>{

	public void fill(IWorldEditor editor, Random rand, @NotNull IBlockFactory block);
	
	public void fill(IWorldEditor editor, Random rand, IBlockFactory block, boolean fillAir, boolean replaceSolid);
	
	public List<Coord> get();
	
}
