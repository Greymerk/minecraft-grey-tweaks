package com.greymerk.tweaks.editor.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IBlockFactory;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;

import net.minecraft.util.math.random.Random;

public class Sphere implements IShape {

	private BoundingBox bb;
	
	public Sphere(BoundingBox bb){
		this.bb = bb;
	}
	
	@Override
	public Iterator<Coord> iterator() {
		return new SphereIterator(bb);
	}

	@Override
	public void fill(IWorldEditor editor, Random rand, IBlockFactory block) {
		this.fill(editor, rand, block, true, true);

	}

	@Override
	public void fill(IWorldEditor editor, Random rand, IBlockFactory block, boolean fillAir, boolean replaceSolid) {
		for(Coord pos : this){
			block.set(editor, rand, pos, fillAir, replaceSolid);
		}
	}

	@Override
	public List<Coord> get() {
		List<Coord> copy = new ArrayList<Coord>();
		for(Coord pos : this){
			copy.add(pos);
		}
		return copy;
	}

	
	private class SphereIterator implements Iterator<Coord>{
 
		private Coord centre;
		private int radius;
		
		private int layer;
		private int row;
		private int col;

		private Cardinal dir;
		private boolean top;
		
		public SphereIterator(BoundingBox bb){
			this.centre = bb.getStart();
			Coord s = bb.getStart();
			Coord e = bb.getEnd();
			Coord diff = e.sub(s);
			
			int r = diff.getX();
			r = r < diff.getY() ? diff.getY() : r;
			r = r < diff.getZ() ? diff.getZ() : r;
			this.radius = r;
			
			layer = 0;
			row = 0;
			col = 0;
			top = true;
			this.dir = Cardinal.NORTH;
		}
		
		@Override
		public boolean hasNext() {
			return layer < radius;
		}

		@Override
		public Coord next() {
			Coord toReturn = centre.copy();
			toReturn.add(top ? Cardinal.UP : Cardinal.DOWN, layer);
			toReturn.add(dir, row);
			toReturn.add(Cardinal.left(dir), col);
			if(this.dir != Cardinal.NORTH || top){
				if(this.dir == Cardinal.NORTH){
					top = false;
				}
				dir = Cardinal.left(dir);
				return toReturn;
			}
			
			col += 1;
			
			if(inRange(col, layer, row)){
				dir = Cardinal.left(dir);
				top = true;
				return toReturn;
			} else {
				col = 0;
			}
			
			row += 1;

			if(inRange(col, layer, row)){
				dir = Cardinal.left(dir);
				top = true;
				return toReturn;
			} else {
				row = 0;
			}
			
			layer += 1;
			dir = Cardinal.left(dir);
			top = true;
			return toReturn;
		}
		
		private boolean inRange(int x, int y, int z){
			return 	x * x + y * y + z * z < radius * radius;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
