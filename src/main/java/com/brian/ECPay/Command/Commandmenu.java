package com.brian.ECPay.Command;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.brian.ECPay.InventoryGUI.InventoryMenu;

public class Commandmenu extends ECPayCommand{

	public Commandmenu() {
		super(  "menu",
				"/ecpay menu 開啟資訊界面",
				new ArrayList<String>(Arrays.asList("ECPay.user.menu")));
	}
	
	@Override
	public void run(Player player, String commandLabel, Command command, String[] args) throws Exception {
		InventoryMenu.INVENTORY.open(player);
	}
}
