package com.brian.ECPay.DataBase;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class DataBase {
	/**
	 * 主插件類別
	 * @info 使用以此插件來做事
	 */
	public static Plugin plugin;
	
	// 設定檔
	//public static LoadConfig LoadConfig;
	
	/**
	 * 伺服器類別
	 * @info 使用以此來讀取伺服器資料
	 */
	public static Server server;
	
	/**
	 * 語言包
	 * @info 語言設定檔
	 */
	public static language language = new language();
	
	/**
	 * 插件目錄
	 * @info 插件附屬檔案的存放路徑
	 */
	public static String pluginMainDir = "./plugins/MobDrop/";
	
	//指令目錄
	//public static MainList CommandsList = new MainList();
	
	//公開顯示訊息
	//public static Config Config;
	
	/**
	 * 顯示訊息
	 * @param msg 要顯示的文字
	 * @info 在cmd 裡顯示 "[ECPay] " + msg
	 */
	public static void Print(String msg)
	{
		System.out.print("[ECPay] " + msg);
	}
}
