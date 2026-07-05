package com.greymerk.tweaks.editor.blocks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.treasure.loot.Enchant;
import com.greymerk.tweaks.util.math.RandHelper;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;


public class BookShelf {

	public static void set(IWorldEditor editor, RandomSource rand, Coord origin, Cardinal dir) {
		
		MetaBlock.of(Blocks.CHISELED_BOOKSHELF)
			.with(HorizontalDirectionalBlock.FACING, Cardinal.facing(Cardinal.reverse(dir)))
			.set(editor, origin);
		
		BlockEntity be = editor.getBlockEntity(origin);
		
		if(be == null) return;
		if(!(be instanceof ChiseledBookShelfBlockEntity)) return;
		
		ChiseledBookShelfBlockEntity shelf = (ChiseledBookShelfBlockEntity)be;
		
		getSlots(rand).forEach(i -> {
			shelf.setItem(i, Enchant.getBook(editor.getRegistryManager(), rand, Difficulty.fromY(origin.getY())));		
		});
		
		shelf.setChanged();
	}
	
	private static List<Integer> getSlots(RandomSource rand){
		List<Integer> slots = IntStream.rangeClosed(0, 5).boxed().collect(Collectors.toList());
		RandHelper.shuffle(slots, rand);
		int count = rand.nextIntBetweenInclusive(1, 3);
		return slots.subList(0, count);
	}
	
}
