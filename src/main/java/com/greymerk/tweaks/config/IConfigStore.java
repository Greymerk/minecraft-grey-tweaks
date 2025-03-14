package com.greymerk.tweaks.config;

import java.util.Optional;

public interface IConfigStore {

	Optional<String> getFileContents();

	void write(String content);
	
	boolean exists();

}
