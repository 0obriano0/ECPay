package com.brian.ECPay.InventoryGUI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;

public class InventoryTools {
	private final transient SmartInventory INVENTORY;
	
	InventoryTools(SmartInventory INVENTORY){
		this.INVENTORY = INVENTORY;
	}
	
	/**
	 * 前往要去的介面
	 * @param name 要去的介面
	 * @param player 玩家資訊
	 * @param contents 介面控制中心
	 */
	public static void goInventory(String name ,Player player, InventoryContents contents) {
		if(name.toLowerCase().equals("notpay")) {
			InventoryNotpPay.getInventory(player.getName()).open(player);
		}else if(name.toLowerCase().equals("wait")) {
			InventoryWait.getInventory(player.getName()).open(player);
		}
	}
	
	/**
	 * 創建一個物品
	 * @param MaterialType minecraft 提供的Material資料
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
	 * @param MaterialType minecraft 提供的Material資料
	 * @param itemname 物品名稱
	 * @param Durability 設定耐久值
	 * @return ItemStack minecraft 物品類別
	 */
	@SuppressWarnings("deprecation")
	static ItemStack createPageButton(Material MaterialType,short Durability,String itemname) {
		ItemMeta newItemMeta = null;
		ItemStack Button = new ItemStack(MaterialType);
		newItemMeta = Button.getItemMeta();
		newItemMeta.setDisplayName(itemname);
		Button.setItemMeta(newItemMeta);
		Button.setDurability(Durability);
		return Button;
	}
	/**
	 * 創建一個物品
	 * @param MaterialType minecraft 提供的Material資料
	 * @param itemname 物品名稱
	 * @param lore 訊息資料(type String)
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
	/**
	 * 創建一個物品
	 * @param MaterialType minecraft 提供的Material資料
	 * @param itemname 物品名稱
	 * @param lore 訊息資料(type List<String>)
	 * @return ItemStack minecraft 物品類別
	 */
	static ItemStack createPageButton(Material MaterialType,String itemname,List<String> lore) {
		ItemStack Button = createPageButton(MaterialType,itemname);
		ItemMeta newItemMeta = Button.getItemMeta();
		newItemMeta.setLore(lore);
		Button.setItemMeta(newItemMeta);
		return Button;
	}
	/**
	 * 創建一個物品
	 * @param MaterialType minecraft 提供的Material資料
	 * @param itemname 物品名稱
	 * @param Durability 設定耐久值
	 * @param lore 訊息資料(type String)
	 * @return ItemStack minecraft 物品類別
	 */
	static ItemStack createPageButton(Material MaterialType,short Durability,String itemname,String lore) {
		ItemStack Button = createPageButton(MaterialType,Durability,itemname);
		ItemMeta newItemMeta = Button.getItemMeta();
		List<String> lore_list = new ArrayList<String>();
		lore_list.add("");
		lore_list.add(lore);
		newItemMeta.setLore(lore_list);
		Button.setItemMeta(newItemMeta);
		return Button;
	}
}
