package com.brian.ECPay.Command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface IECPayCommand  {
	/**
	 *  負責回傳插件名稱
	 * @return 插件名稱
	 */
	String getName();
	
	/**
	 * 拿取權限列表
	 * @return
	 */
	List<String> getPermissions();
	
	/**
	 * 檢查是否有權限
	 * @param sender 輸入者(黑盒子)
	 * @return
	 */
	boolean hasPermission(CommandSender sender);
	
	/**
	 * 檢查是否有權限
	 * @param sender 輸入者(黑盒子 or 玩家)
	 * @return
	 */
	boolean hasPermission(Player player);

	/**
	 *  處理從cmd傳來的指令用
	 * @param player 輸入者(玩家)
	 * @param commandLabel 指令Title
	 * @param cmd 使用指令的使用者相關資訊
	 * @param args 指令後半部分
	 * @throws Exception
	 */
    void run(CommandSender sender, String commandLabel, Command cmd, String[] args) throws Exception;
    
    /**
	 *  處理從player傳來的指令用
	 * @param player 輸入者(玩家)
	 * @param commandLabel 指令Title
	 * @param cmd 使用指令的使用者相關資訊
	 * @param args 指令後半部分
	 * @throws Exception
	 */
    void run(Player player, String commandLabel, Command cmd, String[] args) throws Exception;
    
    /**
	 *  回傳cmd tab相關資料
	 * @param sender 輸入者(黑盒子)
	 * @param commandLabel 指令Title
	 * @param cmd 使用指令的使用者相關資訊
	 * @param args 指令後半部分
	 * @throws Exception
	 */
    List<String> tabComplete(CommandSender sender, String commandLabel, Command cmd, String[] args);

    /**
	 *  回傳player tab相關資料
	 * @param player 輸入者(黑盒子)
	 * @param commandLabel 指令Title
	 * @param cmd 使用指令的使用者相關資訊
	 * @param args 指令後半部分
	 * @throws Exception
	 */
    List<String> tabComplete(Player player, String commandLabel, Command cmd, String[] args);
}
