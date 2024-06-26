package com.greymerk.tweaks.editor.shapes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IBlockFactory;
import com.greymerk.tweaks.editor.IWorldEditor;

import net.minecraft.util.math.random.Random;

public class MultiShape implements IShape {
	
	private Set<Coord> shape;
	
	public MultiShape(){
		shape = new HashSet<Coord>();
	}
	
	public void addShape(IShape toAdd){
		for(Coord pos : toAdd){
			shape.add(pos);
		}
	}
	
	@Override
	public Iterator<Coord> iterator() {
		return shape.iterator();
	}

	@Override
	public void fill(IWorldEditor editor, Random rand, IBlockFactory block) {
		this.fill(editor, rand, block, true, true);
	}

	@Override
	public void fill(IWorldEditor editor, Random rand, IBlockFactory block, boolean fillAir, boolean replaceSolid) {
		for(Coord c : this){
			block.set(editor, rand, c, fillAir, replaceSolid);
		}
	}

	@Override
	public List<Coord> get() {
		List<Coord> coords = new ArrayList<Coord>();
		for(Coord pos : this.shape){
			coords.add(pos.copy());
		}
		return coords;
	}
}
