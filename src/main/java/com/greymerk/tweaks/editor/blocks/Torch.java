package com.greymerk.tweaks.editor.blocks;


import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;

public enum Torch {

	WOODEN, SOUL;
	
	public static void generate(IWorldEditor editor, Torch type, Cardinal dir, Coord pos){
		
		BlockState torch;
		boolean wall = Cardinal.directions.contains(dir);
		
		switch(type){
		case WOODEN: torch = wall 
				? Blocks.WALL_TORCH.getDefaultState()
				: Blocks.TORCH.getDefaultState(); break;
		case SOUL: torch = wall
				? Blocks.SOUL_WALL_TORCH.getDefaultState()
				: Blocks.SOUL_TORCH.getDefaultState(); break;
		default: torch = Blocks.TORCH.getDefaultState(); break;
		}		
		
		if(wall) {
			torch.with(WallTorchBlock.FACING, Cardinal.facing(Cardinal.reverse(dir)));
		}
		
		if(editor.isSupported(pos)) MetaBlock.of(Blocks.TORCH).set(editor, pos);
	}	
}
