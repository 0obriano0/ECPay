package com.brian.ECPay.DataBase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.Exception.ItemSettingfailException;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;


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
	private boolean UseCustomName = true;
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
	//顏色
	private int Item_Color = 0;
	//自訂義頭顱
	private String texture;
	//玩家名稱(頭顱用)
	private String SKULL_Player;
	
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
		if(ItemRealname.equals("PLAYER_HEAD") && !SKULL_Player.equals(""))
			return getplayerhead();
		else if(ItemRealname.equals("CUSTOM_SKULL") && !texture.equals(""))
			return getcustomSkull();
		else {
			// 產生物品用
			ItemStack ResultItem;
		    ItemMeta newItemMeta;
		    LeatherArmorMeta LeatherArmorMeta;
		    
		    String STR_color = "";
		    if(Item_Color > 0) STR_color = DataBase.Color.get(Item_Color-1).toUpperCase() + "_";
//		    ECPay.plugin.getLogger().info("ItemRealname = " + ItemRealname);
//		    ECPay.plugin.getLogger().info("Item_Color = " + Item_Color);
//		    ECPay.plugin.getLogger().info("STR_color + ItemRealname = " + STR_color + ItemRealname);
			// 合成後得到的物品設定
		    ResultItem = new ItemStack(Material.getMaterial(STR_color + ItemRealname));
		    
		    ResultItem.setDurability(durability);
		    
			// 判斷是否要設定顏色
			if(ItemRealname.split("_")[0].equals("LEATHER")) {
				LeatherArmorMeta = (LeatherArmorMeta)ResultItem.getItemMeta();
				LeatherArmorMeta.setColor(Color.fromRGB(this.Red, this.Green, this.Blue));
				ResultItem.setItemMeta(LeatherArmorMeta);
			}
			
			newItemMeta = ResultItem.getItemMeta();
			if(Enchants != null)
				// 附魔
				for (int i = 0; i < Enchants.size(); i++)
				{
					String[] EnchantsParts = Enchants.get(i).split(":");
					int level = Integer.parseInt(EnchantsParts[1]);
					Enchantment enchantment = Enchantment.getByName(EnchantsParts[0]);
					newItemMeta.addEnchant(enchantment, level, true);
				}
			// 名稱
			if (UseCustomName)
				newItemMeta.setDisplayName(ItemName);
			
			// 說明
			if (ItemLores !=null && ItemLores.size() > 0)
			{
				newItemMeta.setLore(ItemLores);
			}
			if(ItemFlags != null)
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
	}
	
	/**
	 * @return 取得玩家頭盧
	 */
	@SuppressWarnings("deprecation")
	private ItemStack getplayerhead(){
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        if (UseCustomName)
        	skullMeta.setDisplayName(ItemName);
        else
        	skullMeta.setDisplayName(SKULL_Player);
        skullMeta.setOwningPlayer(ECPay.plugin.getServer().getOfflinePlayer(SKULL_Player));
        skull.setItemMeta(skullMeta);
		return skull;
	}
	
	/**
	 * @return 取得自訂義頭盧
	 */
	@SuppressWarnings("deprecation")
	private ItemStack getcustomSkull(){
        ItemStack head = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short)3);
        if (texture.isEmpty()) return head;
       
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
       
        profile.getProperties().put("textures", new Property("textures", texture));
       
        try
        {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
           
        }
        catch (IllegalArgumentException|NoSuchFieldException|SecurityException | IllegalAccessException error)
        {
            error.printStackTrace();
        }
        if (UseCustomName)
        	headMeta.setDisplayName(ItemName);
        else
        	headMeta.setDisplayName("CUSTOM_SKULL");
        
        head.setItemMeta(headMeta);
        return head;
    }
	
	private List<String> formatListSting(List<String> formatdat) {
		List<String> final_data = new ArrayList<String>();
		for(String data : formatdat) {
			final_data.add(data.replaceAll("&", "§"));
		}
		return final_data;
	}
	
	@Override
	public String getItemName() {
		return ItemName;
	}
	
	@Override
	public void setItemName(String ItemName) throws NullPointerException{
		if(ItemName == null) throw new NullPointerException();
		this.ItemName = ItemName.replaceAll("&", "§");
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
		this.ItemLores = formatListSting(ItemLores);
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
	public void setdurability(short durability) throws ItemSettingfailException {
		if(durability < 0) throw new ItemSettingfailException("durability need >= 0");
		this.durability = durability;
	}
	
	@Override
	public int getItem_Color() {
		return Item_Color;
	}

	@Override
	public void setItem_Color(int Item_Color) {
		this.Item_Color = Item_Color;
	}
	
	@Override
	public String gettexture() {
		return texture;
	}

	@Override
	public void settexture(String texture) {
		this.texture = texture;
	}

	@Override
	public String getSKULL_Player() {
		return SKULL_Player;
	}

	@Override
	public void setSKULL_Player(String SKULL_Player) {
		this.SKULL_Player = SKULL_Player;
	}
}
