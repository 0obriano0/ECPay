package com.brian.ECPay.FileIO;

import java.util.ArrayList;
import java.util.List;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.DataBase.InventoryItems;
import com.brian.ECPay.Exception.ItemSettingfailException;

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
		if(items == null)
			items = new ArrayList<InventoryItems>();
		else
			items.clear();
		int success = 0;
		int fail = 0;
		for (String ItemKey : data.getConfigurationSection("items").getKeys(false)){
	    	String getItemsURL_full =  "items." + ItemKey;
	    	int x = 0;
	    	int y = 0;
	    	String ItemName = "";
	    	String ItemStack = "";
	    	
	    	if(data.contains(getItemsURL_full + ".x")) x = data.getInt(getItemsURL_full + ".x")-1;
	    	if(data.contains(getItemsURL_full + ".y")) y = data.getInt(getItemsURL_full + ".y")-1;
	    	if(data.contains(getItemsURL_full + ".ItemStack")) ItemStack = data.getString(getItemsURL_full + ".ItemStack").toUpperCase();
	    	if(data.contains(getItemsURL_full + ".ItemName")) ItemName = data.getString(getItemsURL_full + ".ItemName");
	    	InventoryItems inventoryItem = null;
	    	try {
	    		inventoryItem = new InventoryItems(x,y,ItemName,ItemStack);
			} catch (ItemSettingfailException e) {
				ECPay.plugin.getLogger().warning(this.getFileName() + " item : " + ItemKey + " Load ERROR,Please Check ItemName not null , ItemStack not null, x > 0 and y > 0");
				fail++;
				continue;
			}
	    	
	    	try {
	    		if(data.contains(getItemsURL_full + ".Durability")) inventoryItem.setdurability((short)data.getInt(getItemsURL_full + ".durability"));
			} catch (ItemSettingfailException e) {
				ECPay.plugin.getLogger().warning(this.getFileName() + " item : " + ItemKey + " Load ERROR,Please Check durability need >= 0");
				fail++;
				continue;
			}
	    	if(data.contains(getItemsURL_full + ".Enchants")) inventoryItem.setEnchants(data.getStringList(getItemsURL_full + ".Enchants"));
	    	if(data.contains(getItemsURL_full + ".Color")) inventoryItem.setItem_Color(data.getInt(getItemsURL_full + ".Color"));
	    	if(data.contains(getItemsURL_full + ".ItemFlags")) inventoryItem.setItemFlags(data.getStringList(getItemsURL_full + ".ItemFlags"));
	    	if(data.contains(getItemsURL_full + ".Lores")) inventoryItem.setItemLores(data.getStringList(getItemsURL_full + ".Lores"));
	    	if(data.contains(getItemsURL_full + ".Skull_Player")) inventoryItem.setSKULL_Player(data.getString(getItemsURL_full + ".Skull_Player"));
	    	if(data.contains(getItemsURL_full + ".Texture")) inventoryItem.settexture(data.getString(getItemsURL_full + ".Texture"));
	    	if(data.contains(getItemsURL_full + ".Unbreakable")) inventoryItem.setUnbreakable(data.getBoolean(getItemsURL_full + ".Unbreakable"));
	    	if(data.contains(getItemsURL_full + ".Red")) inventoryItem.setRed(data.getInt(getItemsURL_full + ".Red"));
	    	if(data.contains(getItemsURL_full + ".Green")) inventoryItem.setGreen(data.getInt(getItemsURL_full + ".Green"));
	    	if(data.contains(getItemsURL_full + ".Blue")) inventoryItem.setBlue(data.getInt(getItemsURL_full + ".Blue"));
	    	if(data.contains(getItemsURL_full + ".UseCustomName")) inventoryItem.setUseCustomName(data.getBoolean(getItemsURL_full + ".UseCustomName"));
	    	if(data.contains(getItemsURL_full + ".Inventory")) inventoryItem.setInventory(data.getString(getItemsURL_full + ".Inventory"));
	    	
	    	items.add(inventoryItem);
	    	success++;
	    }
		ECPay.plugin.getLogger().info("load file [" + this.getFileName() + "] success: " + success + " fail: " + fail);
		return true;
	}

}
