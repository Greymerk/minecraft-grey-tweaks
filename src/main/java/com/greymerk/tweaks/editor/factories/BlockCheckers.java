package com.greymerk.tweaks.editor.factories;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IBlockFactory;
import com.greymerk.tweaks.editor.IWorldEditor;

import net.minecraft.util.math.random.Random;

public class BlockCheckers extends BlockBase {

	private IBlockFactory fillOne;
	private IBlockFactory fillTwo;
	private Coord offset;
	
	
	public BlockCheckers(IBlockFactory fillOne, IBlockFactory fillTwo, Coord offset){
		this.fillOne = fillOne;
		this.fillTwo = fillTwo;
		this.offset = offset.copy();
	}
	
	public BlockCheckers(IBlockFactory fillOne, IBlockFactory fillTwo){
		this(fillOne, fillTwo, Coord.ZERO);
	}

	@Override
	public boolean set(IWorldEditor editor, Random rand, Coord origin, boolean fillAir, boolean replaceSolid) {
		
		int x = origin.getX() - this.offset.getX();
		int y = origin.getY() - this.offset.getY();
		int z = origin.getZ() - this.offset.getY();
		
		if (x % 2 == 0) {
			if(z % 2 == 0){
				if(y % 2 == 0){
					return fillOne.set(editor, rand, origin.copy(), fillAir, replaceSolid);
				} else {
					return fillTwo.set(editor, rand, origin.copy(), fillAir, replaceSolid);
				}
			} else {
				if(y % 2 == 0){
					return fillTwo.set(editor, rand, origin.copy(), fillAir, replaceSolid);
				} else {
					return fillOne.set(editor, rand, origin.copy(), fillAir, replaceSolid);
				}
			}
		} else {
			if(z % 2 == 0){
				if(y % 2 == 0){
					return fillTwo.set(editor, rand, origin.copy(), fillAir, replaceSolid);
				} else {
					return fillOne.set(editor, rand, origin.copy(), fillAir, replaceSolid);
				}
			} else {
				if(y % 2 == 0){
					return fillOne.set(editor, rand, origin.copy(), fillAir, replaceSolid);
				} else {
					return fillTwo.set(editor, rand, origin.copy(), fillAir, replaceSolid);
				}
			}
		}
	}
}
