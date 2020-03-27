package com.brian.ECPay.InventoryGUI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryTools {
	/**
	 * 創建一個物品
	 * @param MaterialType
	 * @param itemname 物品名稱
	 * @return ItemStack minecraft 物品類別
	 */
	static ItemStack createPageButton(Material MaterialType,String itemname) {
		ItemMeta newItemMeta = null;
		ItemStack Button = new ItemStack(MaterialType);
		newItemMeta = Button.getItemMeta();
		newItemMeta.setDisplayName(itemname);
		Button.setItemMeta(newItemMeta);
		return Button;
	}
	/**
	 * 創建一個物品
	 * @param MaterialType
	 * @param itemname 物品名稱
	 * @param lore 訊息資料
	 * @return ItemStack minecraft 物品類別
	 */
	static ItemStack createPageButton(Material MaterialType,String itemname,String lore) {
		ItemStack Button = createPageButton(MaterialType,itemname);
		ItemMeta newItemMeta = Button.getItemMeta();
		List<String> lore_list = new ArrayList<String>();
		lore_list.add("");
		lore_list.add(lore);
		newItemMeta.setLore(lore_list);
		Button.setItemMeta(newItemMeta);
		return Button;
	}
}
