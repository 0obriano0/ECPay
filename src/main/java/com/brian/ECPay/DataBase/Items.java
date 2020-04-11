package com.brian.ECPay.DataBase;

import java.util.List;

import com.brian.ECPay.Exception.ItemSettingfailException;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;


/*
HIDE_ATTRIBUTES                   隱藏 NBT
HIDE_ENCHANTS                    隱藏 附魔
HIDE_UNBREAKABLE              隱藏 無法破壞
HIDE_POTION_EFFECTS        隱藏 藥水效果/界符盒內容物/唱片號碼
HIDE_DESTROYS                     隱藏 可破壞方塊
HIDE_PLACED_ON                  隱藏 可放置於 
*/

public class Items implements IItems{
	//物品名稱
	private transient String ItemName;
	//物品名稱
	private boolean UseCustomName;
	//物品名稱(系統名稱)
	private transient String ItemRealname;
	// 物品說明
	private List<String> ItemLores;
	// 顏色
	private int Red;
	private int Green;
	private int Blue;
	// 物品附魔
	private List<String> Enchants;
	// 隱藏數值
	private List<String> ItemFlags;
	//破壞可否
	private boolean Unbreakable;
	//耐久度
	private short durability;
	
	public Items(String ItemName, String ItemRealname)throws ItemSettingfailException{
		try {
			setItemName(ItemName);
			setItemRealname(ItemRealname);
		}catch(NullPointerException e) {
			e.printStackTrace();
			throw new ItemSettingfailException("ItemName or ItemRealname is null");
		}
		
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public ItemStack getResultItem() {
		// 產生物品用
		ItemStack ResultItem;
	    ItemMeta newItemMeta;
	    LeatherArmorMeta LeatherArmorMeta;
	    
		// 合成後得到的物品設定
	    ResultItem = new ItemStack(Material.getMaterial(ItemRealname));
	    
	    ResultItem.setDurability(durability);
	    
		// 判斷是否要設定顏色
		if(ItemRealname.split("_")[0].equals("LEATHER")) {
			LeatherArmorMeta = (LeatherArmorMeta)ResultItem.getItemMeta();
			LeatherArmorMeta.setColor(Color.fromRGB(this.Red, this.Green, this.Blue));
			ResultItem.setItemMeta(LeatherArmorMeta);
		}
		
		newItemMeta = ResultItem.getItemMeta();
		// 附魔
		for (int i = 0; i < this.Enchants.size(); i++)
		{
			String[] EnchantsParts = this.Enchants.get(i).split(":");
			int level = Integer.parseInt(EnchantsParts[1]);
			Enchantment enchantment = Enchantment.getByName(EnchantsParts[0]);
			newItemMeta.addEnchant(enchantment, level, true);
		}
		// 名稱
		if (this.UseCustomName)
			newItemMeta.setDisplayName(this.ItemName);
		
		// 說明
		if (this.ItemLores.size() > 0)
		{
			newItemMeta.setLore(this.ItemLores);
		}
		
		for(String itemflag : ItemFlags)
			newItemMeta.addItemFlags(ItemFlag.valueOf(itemflag));
		
		if(Unbreakable) newItemMeta.setUnbreakable(Unbreakable);
		
		// 寫入資料
		ResultItem.setItemMeta(newItemMeta);
	    // 設定耐久為最高
		ResultItem.setDurability((short)0);
		// 回傳
		return ResultItem;
	}

	@Override
	public String getItemName() {
		return ItemName;
	}
	
	@Override
	public void setItemName(String ItemName) throws NullPointerException{
		if(ItemName == null) throw new NullPointerException();
		this.ItemName = ItemName;
	}

	@Override
	public boolean getUseCustomName() {
		return UseCustomName;
	}

	@Override
	public void setUseCustomName(boolean UseCustomName) {
		this.UseCustomName = UseCustomName;
	}

	@Override
	public List<String> getItemLores() {
		return ItemLores;
	}

	@Override
	public void setItemLores(List<String> ItemLores) {
		this.ItemLores = ItemLores;
	}

	@Override
	public String getItemRealname() throws NullPointerException{
		if(ItemName == null) throw new NullPointerException();
		new ItemStack(Material.getMaterial(ItemRealname));
		return ItemRealname;
	}

	@Override
	public void setItemRealname(String ItemRealname) {
		this.ItemRealname = ItemRealname;
		
	}

	@Override
	public int getRed() {
		return Red;
	}

	@Override
	public void setRed(int Red) {
		this.Red = Red;
	}

	@Override
	public int getGreen() {
		return Green;
	}

	@Override
	public void setGreen(int Green) {
		this.Green = Green;
	}

	@Override
	public int getBlue() {
		return Blue;
	}

	@Override
	public void setBlue(int Blue) {
		this.Blue = Blue;
	}

	@Override
	public List<String> getEnchants() {
		return Enchants;
	}

	@Override
	public void setEnchants(List<String> Enchants) {
		this.Enchants = Enchants;
	}

	@Override
	public List<String> getItemFlags() {
		return ItemFlags;
	}

	@Override
	public void setItemFlags(List<String> ItemFlags) {
		this.ItemFlags = ItemFlags;
	}

	@Override
	public boolean getUnbreakable() {
		return Unbreakable;
	}

	@Override
	public void setUnbreakable(boolean Unbreakable) {
		this.Unbreakable = Unbreakable;
	}

	@Override
	public short getdurability() {
		return durability;
	}

	@Override
	public void setdurability(short durability) {
		this.durability = durability;
	}
}
