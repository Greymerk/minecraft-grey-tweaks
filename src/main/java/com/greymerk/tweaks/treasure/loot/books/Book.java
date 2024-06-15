package com.greymerk.tweaks.treasure.loot.books;

import net.minecraft.item.ItemStack;

public enum Book {

	CREDITS;
	
	public static ItemStack get(Book type){
		switch(type){
		
		default: return new BookStarter().get();
		}
	}
	
}
