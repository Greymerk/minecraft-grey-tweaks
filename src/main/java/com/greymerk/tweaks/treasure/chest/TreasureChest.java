package com.greymerk.tweaks.treasure.chest;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.editor.Cardinal;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;
import com.greymerk.tweaks.editor.MetaBlock;
import com.greymerk.tweaks.treasure.Inventory;
import com.greymerk.tweaks.treasure.Treasure;
import com.greymerk.tweaks.treasure.loot.Loot;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class TreasureChest implements ITreasureChest{

	private Inventory inventory;
	private Treasure type;
	private RandomizableContainerBlockEntity chest;
	private Difficulty diff;

	public TreasureChest(Treasure type){
		this.type = type;
		this.diff = Difficulty.EASIEST;
	}
	
	public ITreasureChest generate(IWorldEditor editor, RandomSource rand, Coord pos, ChestType block) throws ChestPlacementException {
		for(Cardinal dir : Cardinal.randDirs(rand)) {
			Coord p = pos.copy();
			p.add(dir);
			if(editor.isAir(pos)) {
				return this.generate(editor, rand, pos, dir, block);
			}
		}
		Cardinal dir = Cardinal.randDirs(rand).get(0);
		return this.generate(editor, rand, pos, dir, block);
	}
	
	public ITreasureChest generate(IWorldEditor editor, RandomSource rand, Coord pos, Cardinal dir, ChestType type) throws ChestPlacementException {

		this.diff = Difficulty.fromY(pos.getY());
		
		MetaBlock block = ChestType.get(type);
		setOrientation(block, dir);
		boolean success = block.set(editor, pos);
		
		if(!success){
			throw new ChestPlacementException("Failed to place chest in world");
		}
		
		this.chest = (RandomizableContainerBlockEntity) editor.getBlockEntity(pos);
		this.inventory = new Inventory(rand, chest);		
		Loot.fillChest(editor, this, rand);
		return this;
	}
	
	private void setOrientation(MetaBlock block, Cardinal dir) {
		Block b = block.getBlock();
		
		if(b == Blocks.CHEST || b == Blocks.TRAPPED_CHEST) {
			if(Cardinal.directions.contains(dir)) {
				block.with(HorizontalDirectionalBlock.FACING, Cardinal.facing(dir).getOpposite());	
			}
		}
		
		if(b == Blocks.BARREL) {
			block.with(BlockStateProperties.FACING, Cardinal.facing(Cardinal.UP));
		}
		
		if(b == Blocks.SHULKER_BOX) {
			block.with(DirectionalBlock.FACING, Cardinal.facing(dir));
		}
	}
	
	@Override
	public boolean setSlot(int slot, ItemStack item){
		return this.inventory.setInventorySlot(slot, item);
	}
	
	@Override
	public boolean setRandomEmptySlot(ItemStack item){
		return this.inventory.setRandomEmptySlot(item);
	}
	
	@Override
	public boolean isEmptySlot(int slot){
		return this.inventory.isEmptySlot(slot);
	}

	@Override
	public Treasure getType(){
		return this.type;
	}
	
	@Override
	public int getSize(){
		return this.inventory.getInventorySize();
	}

	@Override
	public Difficulty getLevel() {
		return this.diff;
	}

	@Override
	public void setLootTable(Identifier table) {
		//this.chest.setLootTable(table, (long)Objects.hash(pos.hashCode(), editor.getSeed()));
	}
}
