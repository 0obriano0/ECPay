package com.brian.ECPay.DataBase;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public interface IItems {
	/**
	 * @return 物品名稱(自定義名稱)
	 */
	String getItemName();
	/**
	 * @param ItemName 物品名稱(自定義名稱)
	 */
	void setItemName(String ItemName);
	
	/**
	 * @return 要不要使用 ItemName
	 */
	boolean getUseCustomName();
	/**
	 * @param UseCustomName 要不要使用 ItemName
	 */
	void setUseCustomName(boolean UseCustomName);
	
	/**
	 * @return 物品說明
	 */
	List<String> getItemLores();
	/**
	 * @param ItemLores 物品說明
	 */
	void setItemLores(List<String> ItemLores);
	
	/**
	 * @return 物品名稱(Material)
	 */
	String getItemRealname();
	/**
	 * @param ItemRealname 物品名稱(Material)
	 */
	void setItemRealname(String ItemRealname);
	
	/**
	 * @return 紅(0-255)
	 */
	int getRed();
	/**
	 * @param Red 紅(0-255)
	 */
	void setRed(int Red);
	
	/**
	 * @return 綠(0-255)
	 */
	int getGreen();
	/**
	 * @param Green 綠(0-255)
	 */
	void setGreen(int Green);
	
	/**
	 * @return 藍(0-255)
	 */
	int getBlue();
	/**
	 * @param Blue 藍(0-255)
	 */
	void setBlue(int Blue);
	
	/**
	 * @return 物品附魔
	 */
	List<String> getEnchants();
	/**
	 * @param Enchants 物品附魔
	 */
	void setEnchants(List<String> Enchants);
	
	/**
	 * @return 隱藏參數
	 */
	List<String> getItemFlags();
	/**
	 * @param ItemFlags 隱藏參數
	 */
	void setItemFlags(List<String> ItemFlags);
	
	/**
	 * @return 破壞可否
	 */
	boolean getUnbreakable();
	/**
	 * @param Unbreakable 破壞可否
	 */
	void setUnbreakable(boolean Unbreakable);
	
	/**
	 * @return 耐久度
	 */
	short getdurability();
	/**
	 * @param durability 耐久度
	 */
	void setdurability(short durability);
	
	/**
	 * 創建資料檔
	 * @return 取的該物品 ItemStack
	 */
	ItemStack getResultItem();
}
