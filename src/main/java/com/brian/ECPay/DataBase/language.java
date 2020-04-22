package com.brian.ECPay.DataBase;

import com.brian.ECPay.DataBase.lang.InventoryGUI;
import com.brian.ECPay.DataBase.lang.message;

public class language {
	/**
	 * 插件顯示title
	 * @info ex. "[ECPay]"
	 */
	private String Plugin_name = "[ECPay]";
	
	/**
	 * InventoryGUI 視窗介面的語言設定
	 */
	public InventoryGUI Inventory = new InventoryGUI();
	/**
	 * 基本語言設定
	 */
	public message message = new message();
	
	/**
	 * 設定 顯示title ex. "[ECPay]"
	 * @param Plugin_name 插件資訊
	 */
	public void setPlugin(String Plugin_name){
		this.Plugin_name = Plugin_name;
	}
	
	/**
	 * 抓取 顯示title
	 * @return Plugin_name 插件資訊
	 */
	public String getPlugin(){
		return this.Plugin_name;
	}
}
