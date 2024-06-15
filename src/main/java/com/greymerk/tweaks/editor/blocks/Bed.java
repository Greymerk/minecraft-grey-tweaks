package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.util.Color;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.enums.BedPart;
import net.minecraft.util.math.random.Random;

public class Bed {

	public static void generate(IWorldEditor editor, Random rand, Cardinal dir, Coord pos) {
		generate(editor, dir, pos, Color.get(rand));
	}
	
	
	public static void generate(IWorldEditor editor, Cardinal dir, Coord pos){
		generate(editor, dir, pos, Color.RED);
	}
	
	
	public static void generate(IWorldEditor editor, Cardinal dir, Coord origin, Color color){
		
		Coord pos = origin.copy();
		MetaBlock head = MetaBlock.of(getFromColor(color));
		head.with(BedBlock.PART, BedPart.HEAD);
		head.with(HorizontalFacingBlock.FACING, Cardinal.facing(dir));
		head.set(editor, pos);
		
		pos.add(dir);
		MetaBlock foot = MetaBlock.of(getFromColor(color));
		foot.with(BedBlock.PART, BedPart.FOOT);
		foot.with(HorizontalFacingBlock.FACING, Cardinal.facing(dir));
		foot.set(editor, pos);

	}
	
	public static Block getFromColor(Color c) {
		switch(c) {
		case WHITE: return Blocks.WHITE_BED;
		case ORANGE: return Blocks.ORANGE_BED;
		case MAGENTA: return Blocks.MAGENTA_BED;
		case LIGHT_BLUE: return Blocks.LIGHT_BLUE_BED;
		case YELLOW: return Blocks.YELLOW_BED;
		case LIME: return Blocks.LIME_BED;
		case PINK: return Blocks.PINK_BED;
		case GRAY: return Blocks.GRAY_BED;
		case LIGHT_GRAY: return Blocks.LIGHT_GRAY_BED;
		case CYAN: return Blocks.CYAN_BED;
		case PURPLE: return Blocks.PURPLE_BED;
		case BLUE: return Blocks.BLUE_BED;
		case BROWN: return Blocks.BROWN_BED;
		case GREEN: return Blocks.GREEN_BED;
		case RED: return Blocks.RED_BED;
		case BLACK: return Blocks.BLACK_BED;
		default: return Blocks.RED_BED;
		}
	}
	
	
}
