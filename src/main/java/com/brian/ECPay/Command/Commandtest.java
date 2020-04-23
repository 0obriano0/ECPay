package com.brian.ECPay.Command;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.brian.ECPay.DataBase.DataBase;


public class Commandtest extends ECPayCommand{

	public Commandtest() {
		super(  "test",
				"/ecpay test 測試指令",
				new ArrayList<String>(Arrays.asList("ECPay.admin.test")));
	}
	
	@Override
	public void run(CommandSender sender, String commandLabel, Command command, String[] args) throws Exception {
		sender.sendMessage("test");
		if(args.length >= 3 && args[1].equals("Query")) {
			String mysqlcommand = "";
			for(int loopnum1 = 2; loopnum1 < args.length;loopnum1++) {
			if(loopnum1 != 2) mysqlcommand += " ";
				mysqlcommand += args[loopnum1];
			}
			System.out.println("full command = " + mysqlcommand);
			DataBase.mysql.executeQuery(mysqlcommand);
		}else if(args.length >= 3 && args[1].equals("Update")) {
			String mysqlcommand = "";
			for(int loopnum1 = 2; loopnum1 < args.length;loopnum1++) {
				if(loopnum1 != 2) mysqlcommand += " ";
				mysqlcommand += args[loopnum1];
			}
			System.out.println("full command = " + mysqlcommand);
			DataBase.mysql.executeUpdate(mysqlcommand);
		}
		
	}

	
	
	@Override
	public void run(Player player, String commandLabel, Command command, String[] args) throws Exception {
		run((CommandSender)player, commandLabel, command,args);
		if(args.length == 2)
			if(args[1].equals("aaa"))
				DataBase.mysql.executeQuery("INSERT INTO payinto VALUES (20042300002,'0obriano0','2004231129488717','300','test','CVS_CVS','LLL20114012292','2020-04-23 11:29:50','2020-04-24 11:29:50','文字交易一',0)");
			else
				DataBase.ecpaySystem.createCVSPaymentNO(args[1], player.getName(), "300", "testItemName", "test");
	}
}
