package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.editor.boundingbox.BoundingBox;
import com.greymerk.tweaks.editor.shapes.RectSolid;

import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;



public class Vine {

	public static void fill(IWorldEditor editor, RandomSource rand, Coord start, Coord end){
		for(Coord cursor : new RectSolid(BoundingBox.of(start, end))){
			set(editor, cursor);
		}
	}
	
	public static void set(IWorldEditor editor, Coord origin){
		if(!canPlace(editor, origin)) return;
		setSides(editor, origin);
	}
	
	public static boolean canPlace(IWorldEditor editor, Coord origin) {
		if(!editor.isAir(origin)) return false;
		for(Cardinal dir : Cardinal.directions) {
			if(editor.isFaceFullSquare(origin.copy().add(dir), Cardinal.reverse(dir))) return true;
		}
		return false;
	}
	
	public static void setSides(IWorldEditor editor, Coord origin){
		for(Cardinal dir : Cardinal.values()) {
			if(dir == Cardinal.DOWN) return;
			setFace(editor, origin, Cardinal.reverse(dir));
		}
	}
	
	public static void setFace(IWorldEditor editor, Coord origin, Cardinal dir) {
		Coord pos = origin.copy().add(dir);
		if(!editor.isFaceFullSquare(pos, dir)) return;
		Direction facing = Cardinal.directions.contains(dir) 
				? Cardinal.facing(Cardinal.reverse(dir))
				: Cardinal.facing(dir);
		BooleanProperty facingProperty = VineBlock.getPropertyForFace(facing);
		if(facingProperty == null) return;
		MetaBlock.of(Blocks.VINE)
			.with(facingProperty, true)
			.set(editor, origin.copy());
	}
}
