package com.greymerk.tweaks.editor.blocks.slab;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;

public interface ISlab{

	public ISlab upsideDown(boolean upsideDown);
	
	public MetaBlock get();
	
	public boolean set(IWorldEditor editor, Coord pos);
	
	public boolean set(IWorldEditor editor, Coord pos, boolean fillAir, boolean replaceSolid);
	
}
