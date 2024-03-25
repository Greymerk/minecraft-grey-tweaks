package com.greymerk.tweaks.editor.shapes;

import com.greymerk.tweaks.editor.Coord;

public enum Shape {

	RECTSOLID;
	
	public static IShape get(Shape type, Coord start, Coord end){
		switch(type){
		case RECTSOLID: return new RectSolid(start, end);
		default: return new RectSolid(start, end);
		}
	}
}
