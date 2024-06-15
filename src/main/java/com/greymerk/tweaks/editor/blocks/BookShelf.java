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

import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.util.math.random.Random;


public class BookShelf {

	public static void set(IWorldEditor editor, Random rand, Coord origin, Cardinal dir) {
		
		MetaBlock.of(Blocks.CHISELED_BOOKSHELF)
			.with(HorizontalFacingBlock.FACING, Cardinal.facing(Cardinal.reverse(dir)))
			.set(editor, origin);
		
		BlockEntity be = editor.getBlockEntity(origin);
		
		if(be == null) return;
		if(!(be instanceof ChiseledBookshelfBlockEntity)) return;
		
		ChiseledBookshelfBlockEntity shelf = (ChiseledBookshelfBlockEntity)be;
		
		getSlots(rand).forEach(i -> {
			shelf.setStack(i, Enchant.getBook(editor.getRegistryManager(), rand, Difficulty.fromY(origin.getY())));		
		});
		
		shelf.markDirty();
	}
	
	private static List<Integer> getSlots(Random rand){
		List<Integer> slots = IntStream.rangeClosed(0, 5).boxed().collect(Collectors.toList());
		RandHelper.shuffle(slots, rand);
		int count = rand.nextBetween(1, 3);
		return slots.subList(0, count);
	}
	
}
