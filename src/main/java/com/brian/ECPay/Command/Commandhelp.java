package com.brian.ECPay.Command;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.DataBase.DataBase;

public class Commandhelp extends ECPayCommand{

	public Commandhelp() {
		super(  "help",
				"/ecpay help 取得指令說明",
				new ArrayList<String>(Arrays.asList("ECPay.user.help")));
	}
	
	@Override
	public void run(CommandSender sender, String commandLabel, Command command, String[] args) throws Exception {
		sender.sendMessage(" ");
		sender.sendMessage("=============== ECPay 交易系統 ===============");
		sender.sendMessage(" ");
		for(String command_value :DataBase.getCommands(ECPay.plugin)) {
			IECPayCommand cmd = ECPay.getCommandClass(command_value);
			if(cmd.hasPermission(sender))
				sender.sendMessage(cmd.getHelp());
		}
		sender.sendMessage(" ");
		sender.sendMessage("===========================================");
	}
	
	@Override
	public void run(Player player, String commandLabel, Command command, String[] args) throws Exception {
		run((CommandSender)player, commandLabel, command,args);
	}
}
