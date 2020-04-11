package com.brian.ECPay.FileIO;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.brian.ECPay.ECPay;

public class FileIO implements IFileIO{
	
	private final transient String FileName;
	
	private FileConfiguration fileconfiguration = null;
	
	public FileIO(String FileName){
		this.FileName = FileName;
	}
	
	@Override
	public String getFileName() {
		return FileName;
	}
	
	@Override
	public String getPath() {
		return "./" + ECPay.plugin.getDataFolder() + "FileName";
	}
	
	@Override
	public FileConfiguration getFileforYML() {
		if(fileconfiguration == null) reloadFile();
		return fileconfiguration;
	}
	
	protected void readFile(){
		File File_load = new File(ECPay.plugin.getDataFolder(), FileName);
        if (!File_load.exists()) ECPay.plugin.saveResource(FileName, true);
        fileconfiguration = YamlConfiguration.loadConfiguration(File_load);
	}
	
	@Override
	public boolean reloadFile() {
		return false;
	}
	
}
