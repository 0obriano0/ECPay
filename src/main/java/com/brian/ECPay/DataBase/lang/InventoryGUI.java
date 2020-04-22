package com.brian.ECPay.DataBase.lang;

import java.util.ArrayList;
import java.util.List;

public class InventoryGUI {
	public String close = "關閉";
	public String next = "下一頁";
	public String previous = "上一頁";
	public String menu = "主選單";
	public String back = "返回";
	public String back_menu = "回首頁";
	public List<String> admin_lore;
	
	public InventoryGUI() {
		admin_lore = new ArrayList<String>();
		admin_lore.add("點擊左鍵");
		admin_lore.add("拿取道具");
	}
}
