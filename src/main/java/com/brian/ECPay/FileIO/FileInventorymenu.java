package com.brian.ECPay.FileIO;

import java.util.List;

import com.brian.ECPay.DataBase.InventoryItems;

public class FileInventorymenu extends FileIO{
	
	private List<InventoryItems> items = null;
	
	public FileInventorymenu() {
		super("Inventorymenu.yml");
	}
	
	public List<InventoryItems> getInventoryItems(){
		if(items == null) reloadFile();
		return items;
	}
	
	@Override
	public boolean reloadFile() {
		readFile();
		
		return false;
	}

}
