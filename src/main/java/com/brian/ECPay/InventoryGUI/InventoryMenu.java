package com.brian.ECPay.InventoryGUI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.brian.ECPay.Command.ECPayCommand;
import com.brian.ECPay.DataBase.DataBase;
import com.brian.ECPay.DataBase.InventoryItems;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;

public class InventoryMenu extends InventoryTools implements InventoryProvider{
	public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("Menu")
            .provider(new InventoryMenu())
            .size(3, 9)
            .title(ChatColor.BLUE + DataBase.language.Inventory.menu)
            .build();
	
	InventoryMenu() {
		super(INVENTORY);
	}
	@Override
	public void init(Player player, InventoryContents contents) {
//		contents.fillBorders(ClickableItem.empty(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
//		
//		contents.set(1, 2, ClickableItem.empty(InventoryTools.createPageButton(Material.ITEM_FRAME,"§a資料顯示","看看就好")));
//		contents.set(2, 8, ClickableItem.of(InventoryTools.createPageButton(Material.BARRIER,"§a" + DataBase.language.Inventory.close),
//                e -> InventoryMenu.INVENTORY.close(player)));
		for(InventoryItems entry : DataBase.fileInventorymenu.getInventoryItems()) {
			if(entry.getInventory() != null && !entry.getInventory().equals(""))
				contents.set(entry.gety(), entry.getx(), ClickableItem.of(entry.getResultItem(),
		                e -> InventoryMenu.goInventory(entry.getInventory(), player, contents)));
			else
				contents.set(entry.gety(), entry.getx(), ClickableItem.empty(entry.getResultItem()));
		}
	}

	@Override
	public void update(Player player, InventoryContents contents) {
		
		
	}

}
