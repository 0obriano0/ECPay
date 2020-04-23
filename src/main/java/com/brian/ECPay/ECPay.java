package com.brian.ECPay;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.brian.ECPay.webHandler.ResponseHandler;
import com.brian.library.MySQL;
import com.brian.ECPay.Command.IECPayCommand;
import com.brian.ECPay.DataBase.DataBase;
import com.brian.ECPay.DataBase.MySQL.MySQLBase;
import com.brian.ECPay.ECPayTools.ECPaySystem;

import example.ExampleAllInOne;

public class ECPay extends JavaPlugin {
	//private static final Logger LOGGER;
	public static Plugin plugin;
	public static int port;
	public static String ServerIP;
	public static org.eclipse.jetty.server.Server webserver;
	public static BukkitTask webTask;
	public ExampleAllInOne a;
	
    @Override
    public void onEnable() {
        plugin = this;
        
        saveDefaultConfig();
        reloadConfig();

        DataBase.ecpaySystem = new ECPaySystem("");
        
        DataBase.mysql = new MySQLBase(plugin.getConfig().getString("MySQL.user"),plugin.getConfig().getString("MySQL.pass"),plugin.getConfig().getString("MySQL.DB_URL"),plugin.getConfig().getString("MySQL.db"));
        
        DataBase.fileInventorymenu.reloadFile();
        
        File payment_confFile = new File(this.getDataFolder(), "payment_conf.xml");
        if (!payment_confFile.exists()) this.saveResource("payment_conf.xml", true);
        
        openWebServer();
        
        this.getLogger().info("使用綠界科技 ");
    }
    
    @Override
    public void onDisable() {
    	closeWebServer();
    	DataBase.mysql.close();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	return ECPayCommand(sender,command,label,args,ECPay.class.getClassLoader(),"com.brian.ECPay.Command");
    }
    
    public boolean ECPayCommand(CommandSender sender, Command command, String commandLabel, String[] args,final ClassLoader classLoader, final String commandPath) {
    	if (commandLabel.equalsIgnoreCase("ecpay")) {
    		if(args.length >= 1 && DataBase.getCommands(plugin).contains(args[0])) {
    			IECPayCommand cmd = getCommandClass(args[0],classLoader,commandPath);
    			if ((sender instanceof Player)) {    
    				if(!cmd.hasPermission(sender))
    					return false;
            		try {
						cmd.run((Player)sender, commandLabel, command, args);
					} catch (Exception e) {
						e.printStackTrace();
					}
            	}else {
            		try {
						cmd.run(sender, commandLabel, command, args);
					} catch (Exception e) {
						e.printStackTrace();
					}
            	}
                return true;
            }else if(args.length == 0){
            	IECPayCommand cmd = getCommandClass("help",classLoader,commandPath);
            	if(!cmd.hasPermission(sender))
					return false;
            	try {
					cmd.run(sender, commandLabel, command, args);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
    	}
		return false;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    	return onTabComplete(sender,cmd,label,args,ECPay.class.getClassLoader(),"com.brian.ECPay.Command");
    }
    
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args,final ClassLoader classLoader, final String commandPath){
    	if(args.length == 1) {
    		List<String> show_commands = new ArrayList<String>();
    		for (String key : DataBase.getCommands(plugin)){
    			IECPayCommand cmd = getCommandClass(key,classLoader,commandPath);
    			if(key.indexOf(args[0].toLowerCase()) != -1 && cmd.hasPermission(sender))
    				show_commands.add(key);
    		}
    		return show_commands;
    	}else if(args.length > 1 && DataBase.getCommands(plugin).contains(args[0])){
    		
    	}
    	return Collections.emptyList();
    }
    
    /**
	 *  取得指令的類別(class)
	 * @param command 指令名稱
     * @return 該class資料
     */
    public static IECPayCommand getCommandClass(String command) {
    	IECPayCommand cmd = null;
        try {
            cmd = (IECPayCommand) ECPay.class.getClassLoader().loadClass("com.brian.ECPay.Command" + ".Command" + command).newInstance();
        }catch(InstantiationException ex) {
        	ex.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
		return cmd;
    }
    
    /**
	 *  取得指令的類別(class)
	 * @param command 指令名稱
     * @param classLoader 抓取此插件讀取classLoader指令
     * @param commandPath 要抓取插件的檔案位置
     * @return 該class資料
     */
    private IECPayCommand getCommandClass(String command,final ClassLoader classLoader, final String commandPath) {
    	IECPayCommand cmd = null;
        try {
            cmd = (IECPayCommand) classLoader.loadClass(commandPath + ".Command" + command).newInstance();
        }catch(InstantiationException ex) {
        	ex.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
		return cmd;
    }
    
    /**
	  * 開啟接收綠界科技的伺服器端
	 * @return 伺服器資料
	 */
    private static org.eclipse.jetty.server.Server openWebServer() {
    	port = plugin.getConfig().getInt("port");
        ServerIP = plugin.getConfig().getString("ServerIP");
        
        plugin.getLogger().info("將開啟port " + port + " 作為 HTTP API 伺服器");
        plugin.getLogger().info("server = http://" + ServerIP + ":" + port + "/");
        webTask = new BukkitRunnable() {
            @Override
            public void run() {
            	plugin.getLogger().info("Web running");
            	webserver = new org.eclipse.jetty.server.Server(port);
            	webserver.setHandler(new ResponseHandler());
                try {
                	webserver.start();
                	webserver.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(plugin);
    	return webserver;
    }
    
    /**
     * 重新讀取資料
     */
    public static void reload() {
    	DataBase.mysql.close();
    	closeWebServer();
    	plugin.reloadConfig();
    	openWebServer();
    	DataBase.fileInventorymenu.reloadFile();
    	DataBase.mysql = new MySQLBase(plugin.getConfig().getString("MySQL.user"),plugin.getConfig().getString("MySQL.pass"),plugin.getConfig().getString("MySQL.DB_URL"),plugin.getConfig().getString("MySQL.db"));
    }
    
    /**
	 * 關閉接收綠界科技的伺服器端
     * @return
     */
    private static boolean closeWebServer() {
    	try {
    		webserver.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	webTask.cancel();
    	return true;
    }
    
}
