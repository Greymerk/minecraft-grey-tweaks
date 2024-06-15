package com.greymerk.tweaks.editor.blocks.slab;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;

public class MetaSlab implements ISlab {

	MetaBlock slab;
	
	public MetaSlab(Block slab) {
		this.slab = MetaBlock.of(slab);
	}
	
	@Override
	public MetaBlock get() {
		return this.slab;
	}
	
	@Override
	public ISlab upsideDown(boolean upsideDown) {
		this.slab.with(SlabBlock.TYPE, upsideDown ? SlabType.TOP : SlabType.BOTTOM);
		return this;
	}

	@Override
	public boolean set(IWorldEditor editor, Coord pos) {
		return editor.set(pos, slab);
	}

	@Override
	public boolean set(IWorldEditor editor, Coord pos, boolean fillAir, boolean replaceSolid) {
		return editor.set(pos, slab, fillAir, replaceSolid);
	}


}
