package com.brian.ECPay.FileIO;

import org.bukkit.configuration.file.FileConfiguration;

public interface IFileIO {
	/**
	 * 取得檔案名稱(含副檔名)
	 * @return 檔案名稱(String)
	 */
	String getFileName();
	
	/**
	 * 取得檔案相對路徑
	 * @return 檔案相對路徑(String)
	 */
	String getPath();
	
	/**
	 * 取得檔案資料(用YML檔格式)
	 * @return 檔案資料(FileConfiguration)
	 */
	FileConfiguration getFileforYML();
	
	/**
	 * 重新讀取檔案資料
	 * @return 是否讀取成功
	 */
	boolean reloadFile();
	
}
