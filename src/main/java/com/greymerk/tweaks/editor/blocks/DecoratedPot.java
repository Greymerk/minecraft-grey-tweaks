package com.greymerk.tweaks.editor.blocks;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.treasure.loot.Loot;
import com.greymerk.tweaks.util.IWeighted;
import com.greymerk.tweaks.util.WeightedChoice;
import com.greymerk.tweaks.util.WeightedRandomizer;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.PotDecorations;




public class DecoratedPot {

	public static void set(IWorldEditor editor, RandomSource rand, Coord origin) {
		set(editor, rand, origin, Loot.PRECIOUS);
	}
	
	public static void set(IWorldEditor editor, RandomSource rand, Coord origin, Loot type) {
		
		if(!MetaBlock.of(Blocks.DECORATED_POT).set(editor, origin)) return;
		
		BlockEntity be = editor.getBlockEntity(origin);
		
		if(be == null) return;
		if(!(be instanceof DecoratedPotBlockEntity)) return;
		
		DecoratedPotBlockEntity potEntity = (DecoratedPotBlockEntity)be;
		
		
		IWeighted<Item> faceroll = getFaceRoller();
		
		PotDecorations sherds = new PotDecorations(faceroll.get(rand), faceroll.get(rand), faceroll.get(rand), faceroll.get(rand));
		
		DataComponentPatch.Builder changes = DataComponentPatch.builder();
		changes.set(DataComponents.POT_DECORATIONS, sherds);
		
		potEntity.applyComponents(potEntity.components(), changes.build());		

		ItemStack loot = Loot.getLootItem(editor, type, rand, Difficulty.fromY(origin.getY()));
		potEntity.setTheItem(loot);
		
		potEntity.setChanged();
		
	}
	
	private static IWeighted<Item> getFaceRoller(){
		
		WeightedRandomizer<Item> faceroll = new WeightedRandomizer<Item>();
		faceroll.add(new WeightedChoice<Item>(Items.BRICK, 50));
		faceroll.add(new WeightedChoice<Item>(Items.ANGLER_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.ARCHER_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.ARMS_UP_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.BLADE_POTTERY_SHERD, 3));
		faceroll.add(new WeightedChoice<Item>(Items.BREWER_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.BURN_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.DANGER_POTTERY_SHERD, 3));
		faceroll.add(new WeightedChoice<Item>(Items.EXPLORER_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.FRIEND_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.HEART_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.HEARTBREAK_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.HOWL_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.MINER_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.MOURNER_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.PLENTY_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.PRIZE_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.SHEAF_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.SHELTER_POTTERY_SHERD, 1));
		faceroll.add(new WeightedChoice<Item>(Items.SKULL_POTTERY_SHERD, 5));
		faceroll.add(new WeightedChoice<Item>(Items.SNORT_POTTERY_SHERD, 1));
		
		return faceroll;
	}
	
}
