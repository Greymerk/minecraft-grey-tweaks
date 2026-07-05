package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.util.Color;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;



public class Candle {

	public static void generate(IWorldEditor editor, RandomSource rand, Coord origin) {
		generate(editor, rand, origin, Color.get(rand));
	}
	
	public static void generate(IWorldEditor editor, RandomSource rand, Coord origin, Color color) {
		generate(editor, origin, color, rand.nextIntBetweenInclusive(1, 4), true);
	}
	
	public static void generate(IWorldEditor editor, Coord origin, Color color, int count, boolean lit) {
		if(!editor.isSupported(origin)) return;
		int numCandles;
		if(count == 0) return;
		if(count < 0) {
			numCandles = 1;
		} else if(count > 4) {
			numCandles = 4;
		} else {
			numCandles = count;
		}
		MetaBlock.of(fromColor(color))
			.with(CandleBlock.CANDLES, numCandles)
			.with(CandleBlock.LIT, lit)
			.set(editor, origin);
	}
	
	public static BlockState fromColor(Color color) {
		return Blocks.DYED_CANDLE.pick(Color.get(color)).defaultBlockState();
	}
	
}
