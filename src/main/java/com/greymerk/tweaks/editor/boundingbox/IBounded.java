package com.greymerk.tweaks.editor.boundingbox;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.shapes.IShape;
import com.greymerk.tweaks.editor.shapes.Shape;

import net.minecraft.nbt.NbtCompound;

public interface IBounded {
	
	public BoundingBox getBoundingBox();
	
	public boolean collide(IBounded other);

	public boolean contains(Coord pos);
	
	public IShape getShape(Shape type);
	
	public Coord getStart();
	
	public Coord getEnd();

	public NbtCompound getNbt();
	
}
