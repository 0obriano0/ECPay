package com.brian.ECPay.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.InventoryGUI.InventoryNotpPay;

public class Commandnotpay extends ECPayCommand{
	public Commandnotpay() {
		super(  "notpay",
				"/ecpay notpay <username> 開啟未繳費資訊界面",
				new ArrayList<String>(Arrays.asList("ECPay.admin.notpay")));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run(Player player, String commandLabel, Command command, String[] args) throws Exception {
		if(args.length == 2 && (ECPay.server.getOfflinePlayer(args[1]) != null || args[1].equals("*"))) 
			InventoryNotpPay.getInventory(args[1]).open(player);
	}
	
	@Override
	public List<String> tabComplete(Player player, String commandLabel, Command command, String[] args) {
		if(args.length == 2) {
			List<String> tablist = new ArrayList<String>();
			if ("*".indexOf(args[1].toLowerCase())!= -1) tablist.add("*");
			for(OfflinePlayer Offlineplayer : ECPay.server.getOfflinePlayers())
				if(Offlineplayer.getName().toLowerCase().indexOf(args[1].toLowerCase()) != -1)
					tablist.add(Offlineplayer.getName());
			return tablist;
		}
		return Collections.emptyList();
	}
}
