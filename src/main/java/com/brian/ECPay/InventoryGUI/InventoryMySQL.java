package com.brian.ECPay.InventoryGUI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.brian.ECPay.DataBase.DataBase;

import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;

public class InventoryMySQL extends InventoryTools implements InventoryProvider{
	public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("MySQL")
            .provider(new InventoryMySQL())
            .size(3, 9)
            .title(ChatColor.BLUE + DataBase.language.Inventory.menu)
            .build();
	
	InventoryMySQL() {
		super(INVENTORY);
	}
	
	@Override
	public void init(Player player, InventoryContents contents) {
		
	}

	@Override
	public void update(Player player, InventoryContents contents) {
		
		
	}
}
