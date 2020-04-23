package com.brian.ECPay.Command;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.brian.ECPay.DataBase.DataBase;


public class Commandmysql extends ECPayCommand{

	public Commandmysql() {
		super(  "mysql",
				"/ecpay mysql 資料庫",
				new ArrayList<String>(Arrays.asList("ECPay.admin.mysql")));
	}
	@Override
	public void run(CommandSender sender, String commandLabel, Command command, String[] args) throws Exception {
		if(args.length >= 2) {
			if(args[1].equals("create") && args.length >=3) {
				DataBase.mysql.CreateDataBase(args[2]);
			}
		}
	}
	
	@Override
	public void run(Player player, String commandLabel, Command command, String[] args) throws Exception {
		run((CommandSender)player, commandLabel, command,args);
	}
	
}
