package com.brian.ECPay.FileIO;

import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

public interface IFileIO {
	String getFileName();
	
	FileConfiguration getFileforYML();
	
	Map<String, String> getFileforMap();
	
	boolean reloadFile();
	
}
