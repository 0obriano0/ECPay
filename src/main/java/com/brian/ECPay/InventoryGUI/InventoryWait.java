package com.brian.ECPay.InventoryGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.brian.ECPay.DataBase.DataBase;
import com.brian.ECPay.DataBase.MySQL.UserInfoWaitPost;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator;

public class InventoryWait extends InventoryTools implements InventoryProvider{
	
	public static SmartInventory INVENTORY ;
	String playername;
	
	public InventoryWait(String playername) {
		super(INVENTORY);
		this.playername = playername;
	}
	
	public static SmartInventory getInventory(String playername) {
		return INVENTORY = SmartInventory.builder()
        .id("Wait")
        .provider(new InventoryWait(playername))
        .size(5, 9)
        .title(ChatColor.BLUE + playername)
        .build();
	}
	
	@Override
	public void init(Player player, InventoryContents contents) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Player player, InventoryContents contents) {
		
		List<Map<String,String>> selectdata = new ArrayList<Map<String,String>>();
		
		for(Entry<Long, UserInfoWaitPost> data:DataBase.mysql.UserInfoWaitPost.entrySet())
			if(data.getValue().getUserName().equals(player.getName()))
				selectdata.add(data.getValue().toMap());
		
		Pagination pagination = contents.pagination();
    	
		ClickableItem[] items = new ClickableItem[selectdata.size()];
        
        for(int loopnum1 = 0;loopnum1 < selectdata.size();loopnum1++)
			items[loopnum1] = ClickableItem.empty(getResultItemFormMap(selectdata.get(loopnum1)));
        
        pagination.setItems(items);
        pagination.setItemsPerPage(36);
        
        if(pagination.getPage()*36 > selectdata.size()) pagination.page(selectdata.size() == 0 ? 0 : selectdata.size()/36);
        
        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 0, 0));
        
        contents.set(4, 0, ClickableItem.of(createPageButton(Material.ACACIA_DOOR,"§a" + DataBase.language.Inventory.back_menu),
                e -> InventoryMenu.INVENTORY.open(player)));
        contents.set(4, 3, ClickableItem.of(createPageButton(Material.ARROW,"§a" + DataBase.language.Inventory.previous),
                e -> InventoryWait.getInventory(playername).open(player, pagination.previous().getPage())));
        contents.set(4, 4, ClickableItem.empty(createPageButton(Material.PAPER,"§a - " + (pagination.getPage() + 1) + " - ")));
        contents.set(4, 5, ClickableItem.of(createPageButton(Material.ARROW,"§a" + DataBase.language.Inventory.next),
                e -> InventoryWait.getInventory(playername).open(player, pagination.next().getPage())));
	}
	
	/**
	 * 顯示訂單資訊
	 * @param payinfo 訂單資料
	 * @return 將整理好的物品回傳
	 */
	public ItemStack getResultItemFormMap(Map<String,String> payinfo) {
		// 產生物品用
		ItemStack ResultItem = new ItemStack(Material.PAPER);
	    ItemMeta newItemMeta;
		
		newItemMeta = ResultItem.getItemMeta();
		
		// 名稱
		newItemMeta.setDisplayName(payinfo.get("ItemName"));
		
		List<String> ItemLores = new ArrayList<String>();
		
		ItemLores.add("§f---訂單資訊---");
		ItemLores.add("§f訂單編號:" + payinfo.get("MerchantTradeNo"));
		ItemLores.add("§f說明:" + payinfo.get("CustomField1"));
		ItemLores.add("§f繳費金額:" + payinfo.get("TradeAmount"));
		ItemLores.add("§f付款方式:" + payinfo.get("PaymentType"));
		ItemLores.add("§f---目前狀態---");
		ItemLores.add("§f等待產生代碼");
		ItemLores.add("§f還有 " + payinfo.get("count") + " 秒後失效");
		newItemMeta.setLore(ItemLores);
		
		// 寫入資料
		ResultItem.setItemMeta(newItemMeta);

		// 回傳
		return ResultItem;
	}
}
