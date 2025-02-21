package com.greymerk.tweaks.config;

import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.greymerk.tweaks.GreyTweaks;
import com.greymerk.tweaks.util.MixedKey;
import com.greymerk.tweaks.util.MixedMap;


public class ConfigSettings {
	
	private static ConfigSettings instance;
	private MixedMap configs;
	private IConfigStore file;
	
	public static void init(IConfigStore store) {
		instance = new ConfigSettings(store);
	}
	
	public static ConfigSettings getInstance() {
		if(instance == null) {
			instance = new ConfigSettings(new ConfigFile());
		}
		return instance;
	}
	
	private ConfigSettings(IConfigStore store) {
		this.configs = new MixedMap();
		this.file = store;
		this.init();
	}
	
	private void init() {
		this.configs.putMixed(MixedKey.ofBoolean(Config.SUMMER.keyOf()), false);
		this.configs.putMixed(MixedKey.ofBoolean(Config.DEBUG.keyOf()), false);
		if(file.exists()) this.read();
		this.store();
	}

	public JsonObject asJson() {
		JsonObject json = new JsonObject();
		
		json.addProperty(Config.DEBUG.keyOf(), this.get(MixedKey.ofBoolean(Config.DEBUG.keyOf())));
		json.addProperty(Config.SUMMER.keyOf(), this.get(MixedKey.ofBoolean(Config.SUMMER.keyOf())));
		return json;
	}
	
	public void parse(JsonObject json) {
		if(json.has(Config.DEBUG.keyOf())) configs.put(MixedKey.ofBoolean(Config.DEBUG.keyOf()), json.get(Config.DEBUG.keyOf()).getAsBoolean());
		if(json.has(Config.SUMMER.keyOf())) configs.put(MixedKey.ofBoolean(Config.SUMMER.keyOf()), json.get(Config.SUMMER.keyOf()).getAsBoolean());
		
	}
	
	public <T> T get(MixedKey<T> key) {
		if(!this.has(key)) return null;
		return configs.getMixed(key);
	}
	
	public <T> void put(MixedKey<T> key, T value) {
		if(!this.has(key)) return;
		configs.putMixed(key, value);
		this.store();
	}

	public <T> boolean has(MixedKey<T> key) {
		return configs.containsKey(key);
	}

	public void read() {
		Optional<String> contents = file.getFileContents();
		if(contents.isEmpty()) return;
		try {
			String str = contents.get();
			JsonElement jsonElement = JsonParser.parseString(str);
			JsonObject json = jsonElement.getAsJsonObject();
			this.parse(json);	
		} catch (IllegalStateException e) {
			GreyTweaks.LOGGER.info(e.toString());
			GreyTweaks.LOGGER.info("Invalid Json Config - Replacing with defaults");
			this.store();
			return;
		} catch (Exception e){
			GreyTweaks.LOGGER.info(e.toString());
			GreyTweaks.LOGGER.info("Something's wrong with the config file - using defaults for now");
			return;
		}
	}
	
	private void store() {
		JsonObject json = this.asJson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		file.write(gson.toJson(json));
	}
}
