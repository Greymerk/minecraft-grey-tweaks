package com.greymerk.tweaks.editor.blocks.spawners;

import com.greymerk.tweaks.Difficulty;
import com.greymerk.tweaks.editor.Coord;
import com.greymerk.tweaks.editor.IWorldEditor;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;




public enum Spawner {
	
	CREEPER("creeper"),
	CAVESPIDER("cave_spider"),
	SPIDER("spider"),
	SKELETON("skeleton"),
	ZOMBIE("zombie"),
	SILVERFISH("silverfish"),
	ENDERMAN("enderman"),
	WITCH("witch"),
	WITHERBOSS("wither"),
	BAT("bat"),
	LAVASLIME("magma_cube"),
	BLAZE("blaze"),
	SLIME("slime"),
	PRIMEDTNT("tnt"),
	PIGZOMBIE("zombie_pigman");
	
	private String name;
	Spawner(String name){
		this.name = name;
	}
	
	private static final Spawner[] common = {SPIDER, SKELETON, ZOMBIE};
	
	public static String getName(Spawner type) {
		return "minecraft:" + type.name;
	}
	
	public static EntityType<?> getType(Spawner type){
		
		switch(type) {
		case BAT: return EntityTypes.BAT;
		case BLAZE: return EntityTypes.BLAZE;
		case CAVESPIDER: return EntityTypes.CAVE_SPIDER;
		case CREEPER: return EntityTypes.CREEPER;
		case ENDERMAN: return EntityTypes.ENDERMAN;
		case LAVASLIME: return EntityTypes.MAGMA_CUBE;
		case PIGZOMBIE: return EntityTypes.ZOMBIFIED_PIGLIN;
		case PRIMEDTNT: return EntityTypes.TNT;
		case SILVERFISH: return EntityTypes.SILVERFISH;
		case SKELETON: return EntityTypes.SKELETON;
		case SLIME: return EntityTypes.SLIME;
		case SPIDER: return EntityTypes.SPIDER;
		case WITCH: return EntityTypes.WITCH;
		case WITHERBOSS: return EntityTypes.WITHER;
		case ZOMBIE: return EntityTypes.ZOMBIE;
		default: return EntityTypes.ZOMBIE;
		
		}
	}
	
	

	public static void generate(IWorldEditor editor, RandomSource rand, Coord pos){
		Spawner type = common[rand.nextInt(common.length)];
		generate(editor, rand, pos, type);
	}
	
	public static void generate(IWorldEditor editor, RandomSource rand, Coord pos, Spawner type) {
		
		Difficulty diff = Difficulty.fromY(pos.getY());
		new Spawnable(type).generate(editor, rand, pos, diff);
	
	}
}
