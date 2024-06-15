package com.greymerk.tweaks.editor.blocks.door;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;

public interface IDoor {
	
	public void generate(IWorldEditor editor, Coord pos, Cardinal dir);
	
	public void generate(IWorldEditor editor, Coord pos, Cardinal dir, boolean open);
}
