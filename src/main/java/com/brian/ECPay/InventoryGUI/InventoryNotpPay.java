package com.brian.ECPay.InventoryGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.brian.ECPay.DataBase.DataBase;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator;

public class InventoryNotpPay extends InventoryTools implements InventoryProvider{
	
	public static SmartInventory INVENTORY ;
	String playername;
	
	public InventoryNotpPay(String playername) {
		super(INVENTORY);
		this.playername = playername;
	}
	
	public static SmartInventory getInventory(String playername) {
		return INVENTORY = SmartInventory.builder()
        .id("Menu")
        .provider(new InventoryNotpPay(playername))
        .size(5, 9)
        .title(ChatColor.BLUE + playername)
        .build();
	}
	
	@Override
	public void init(Player player, InventoryContents contents) {
		// TODO Auto-generated method stub
		List<Map<String,String>> selectdata = DataBase.mysql.executeQuery_listMap("SELECT * FROM `payinfo` WHERE UserName='0obriano0' AND PaySuccess = '0'");
		
		Pagination pagination = contents.pagination();
    	
        ClickableItem[] items = new ClickableItem[selectdata.size()];
        
        for(int loopnum1 = 0;loopnum1 < selectdata.size();loopnum1++)
			items[loopnum1] = ClickableItem.empty(getResultItemFormMysql(selectdata.get(loopnum1)));
        
        pagination.setItems(items);
        pagination.setItemsPerPage(36);

        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 0, 0));
        
        contents.set(4, 0, ClickableItem.of(createPageButton(Material.ACACIA_DOOR,"§a" + DataBase.language.Inventory.back_menu),
                e -> InventoryMenu.INVENTORY.open(player)));
        contents.set(4, 3, ClickableItem.of(createPageButton(Material.ARROW,"§a" + DataBase.language.Inventory.previous),
                e -> InventoryNotpPay.getInventory(playername).open(player, pagination.previous().getPage())));
        contents.set(4, 4, ClickableItem.empty(createPageButton(Material.PAPER,"§a - " + (pagination.getPage() + 1) + " - ")));
        contents.set(4, 5, ClickableItem.of(createPageButton(Material.ARROW,"§a" + DataBase.language.Inventory.next),
                e -> InventoryNotpPay.getInventory(playername).open(player, pagination.next().getPage())));
        
	}

	@Override
	public void update(Player player, InventoryContents contents) {
		// TODO Auto-generated method stub
		
	}
	
	public ItemStack getResultItemFormMysql(Map<String,String> payinfo) {
		// 產生物品用
		ItemStack ResultItem = new ItemStack(Material.PAPER);
	    ItemMeta newItemMeta;
		
		newItemMeta = ResultItem.getItemMeta();
		
		// 名稱
		newItemMeta.setDisplayName(payinfo.get("ItemName"));
		
		List<String> ItemLores = new ArrayList<String>();
		
		ItemLores.add("§f---訂單資訊---");
		ItemLores.add("§f訂單編號:" + payinfo.get("MerchantTradeNo"));
		ItemLores.add("§f訂單編號(第三方):" + payinfo.get("TradeNo"));
		ItemLores.add("§f說明:" + payinfo.get("CustomField1"));
		ItemLores.add("§f繳費金額:" + payinfo.get("TradeAmount"));
		ItemLores.add("§f付款方式:" + payinfo.get("PaymentType"));
		ItemLores.add("§f繳費代碼:" + payinfo.get("PaymentNo"));
		ItemLores.add("§f訂單創建時間:" + payinfo.get("TradeDate"));
		ItemLores.add("§f繳費截止時間:" + payinfo.get("ExpireDate"));
		if(payinfo.get("PaySuccess").equals("1"))
			ItemLores.add("§f繳費狀態: §a已繳費");
		else
			ItemLores.add("§f繳費狀態: §c未繳費");
		newItemMeta.setLore(ItemLores);
		
		// 寫入資料
		ResultItem.setItemMeta(newItemMeta);

		// 回傳
		return ResultItem;
	}
}
