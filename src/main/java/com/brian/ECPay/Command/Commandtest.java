package com.brian.ECPay.Command;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.brian.ECPay.DataBase.DataBase;

import example.ExampleAllInOne;


public class Commandtest extends ECPayCommand{

	public Commandtest() {
		super(  "test",
				"/test 測試指令",
				new ArrayList<String>(Arrays.asList("ECPay.admin.test")));
	}
	
	@Override
	public void run(CommandSender sender, String commandLabel, Command command, String[] args) throws Exception {
		sender.sendMessage("test");
		if(args.length == 2)
			DataBase.ecpaySystem.createCVSPaymentNO(args[1], "0obriano0", "300", "testItemName", "test");
//		else if (args.length == 2) {
//			ExampleAllInOne a = new ExampleAllInOne();
//	        a.test();
//		}
			
	}
	
	@Override
	public void run(Player player, String commandLabel, Command command, String[] args) throws Exception {
		run((CommandSender)player, commandLabel, command,args);
	}
}
