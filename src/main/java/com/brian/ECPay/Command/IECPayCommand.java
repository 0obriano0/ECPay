package com.brian.ECPay.Command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
/**
 * ECPay 基本指令系統宣告
 * @author brian
 *
 */
public interface IECPayCommand  {
	/**
	 *  負責回傳插件名稱
	 * @return 插件名稱
	 */
	String getName();
	
	/**
	 * 拿取help指令說明
	 * @return 指令說明
	 */
	String getHelp();
	
	/**
	 * 拿取權限列表
	 * @return 權限列表
	 */
	List<String> getPermissions();
	
	/**
	 * 檢查是否有權限
	 * @param sender 輸入者(黑盒子)
	 * @return 是否有權限
	 */
	boolean hasPermission(CommandSender sender);
	
	/**
	 * 檢查是否有權限
	 * @param player 輸入者(黑盒子 or 玩家)
	 * @return 是否有權限
	 */
	boolean hasPermission(Player player);

	/**
	 *  處理從cmd傳來的指令用
	 * @param sender 輸入者(玩家)
	 * @param commandLabel 指令Title
	 * @param command 使用指令的使用者相關資訊
	 * @param args 指令後半部分
	 * @throws Exception 執行指令錯誤時回報錯誤訊息
	 */
    void run(CommandSender sender, String commandLabel, Command command, String[] args) throws Exception;
    
    /**
	 *  處理從player傳來的指令用
	 * @param player 輸入者(玩家)
	 * @param commandLabel 指令Title
	 * @param command 使用指令的使用者相關資訊
	 * @param args 指令後半部分
	 * @throws Exception 執行指令錯誤時回報錯誤訊息
	 */
    void run(Player player, String commandLabel, Command command, String[] args) throws Exception;
    
    /**
	 *  回傳cmd tab相關資料
	 * @param sender 輸入者(黑盒子)
	 * @param commandLabel 指令Title
	 * @param command 使用指令的使用者相關資訊
	 * @param args 指令後半部分
	 * @return 取得指令列表
	 */
    List<String> tabComplete(CommandSender sender, String commandLabel, Command command, String[] args);

    /**
	 *  回傳player tab相關資料
	 * @param player 輸入者(黑盒子)
	 * @param commandLabel 指令Title
	 * @param command 使用指令的使用者相關資訊
	 * @param args 指令後半部分
	 * @return 取得指令列表
	 */
    List<String> tabComplete(Player player, String commandLabel, Command command, String[] args);
}
