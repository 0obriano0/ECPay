package com.brian.ECPay.FileIO;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.brian.ECPay.ECPay;

public class FileIO implements IFileIO{
	
	private final transient String FileName;
	private transient String URL = null;
	
	protected FileConfiguration data = null;
	
	public FileIO(String FileName){
		this.FileName = FileName;
	}
	
	public FileIO(String URL,String FileName){
		this.FileName = FileName;
		this.URL = URL;
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
		if(data == null) reloadFile();
		return data;
	}
	
	protected void readFile(){
		File File_load = null;
		String full_url = FileName;
		
		if(URL == null)
			File_load = new File(ECPay.plugin.getDataFolder(), FileName);
		else {
			File_load = new File("./" + ECPay.plugin.getDataFolder().toString() + "/" + URL + "/" + FileName);
			full_url = URL + "\\" + FileName;
		}
		
        if (!File_load.exists()) ECPay.plugin.saveResource(full_url, true);
        data = YamlConfiguration.loadConfiguration(File_load);
	}
	
	@Override
	public boolean reloadFile() {
		readFile();
		return true;
	}
	
}
