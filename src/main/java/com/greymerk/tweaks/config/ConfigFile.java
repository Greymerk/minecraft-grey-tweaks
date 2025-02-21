package com.greymerk.tweaks.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.io.IOUtils;

import com.greymerk.tweaks.GreyTweaks;

public class ConfigFile implements IConfigStore {

	private static final String configDirPath = "config";
	private static final String configFilePath = GreyTweaks.MOD_ID + ".json";
	private static final String configFullPath = configDirPath + File.separatorChar + configFilePath;
	
	public ConfigFile() {
	}

	@Override
	public Optional<String> getFileContents() {
		File file = new File(configFullPath);
		if(!file.exists()) {
			return Optional.empty();
		}
		try {
			return Optional.of(IOUtils.toString(new FileReader(file)));
		} catch (FileNotFoundException e) {
			return Optional.empty();
		} catch (IOException e) {
			return Optional.empty();
		}
	}

	@Override
	public void write(String content) {
		File directory = new File(configDirPath);
		
		if(!directory.exists()) {
			directory.mkdirs();
		}

		try {
			FileWriter writer = new FileWriter(configFullPath);
			writer.flush();
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			GreyTweaks.LOGGER.info(e.getMessage());
		}
	}

	@Override
	public boolean exists() {
		File f = new File(configFullPath);
		return f.exists();
	}
}
