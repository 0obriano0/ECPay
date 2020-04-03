package com.brian.ECPay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import org.eclipse.jetty.server.Server;

import com.brian.ECPay.webHandler.ResponseHandler;
import com.brian.ECPay.Command.IECPayCommand;
import com.brian.ECPay.DataBase.DataBase;
import com.brian.ECPay.InventoryGUI.InventoryMenu;

import example.ExampleAllInOne;

public class ECPay extends JavaPlugin {
	//private static final Logger LOGGER;
	public static Plugin plugin;
	public static int port;
	public static String ServerIP;
	public static Server server;
	public static BukkitTask webTask;
	public ExampleAllInOne a;
    @Override
    public void onEnable() {
        plugin = this;
        DataBase.plugin = this;
        DataBase.server = getServer();
        saveDefaultConfig();
        reloadConfig();
        port = getConfig().getInt("port");
        ServerIP = getConfig().getString("ServerIP");
        
        this.getLogger().info("將開啟port " + port + " 作為 HTTP API 伺服器");
        this.getLogger().info("server = http://" + ServerIP + ":" + port + "/");
        webTask = new BukkitRunnable() {
            @Override
            public void run() {
            	plugin.getLogger().info("Web running");
                server = new Server(port);
                server.setHandler(new ResponseHandler());
                try {
                    server.start();
                    server.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(this);
        
        this.getLogger().info("使用綠界科技 ");
        a = new ExampleAllInOne();
        //a.test();
    }
    
    public void onDisable() {
    	try {
    		server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	webTask.cancel();
    }
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (label.equalsIgnoreCase("ecpay")) {
    		
    		ECPayCommand(sender,command,label,args,ECPay.class.getClassLoader(),"com.brian.ECPay.Command");
    		DataBase.getCommands();
            if ((sender instanceof Player)) {
            	
            	//a.test();
            	InventoryMenu.INVENTORY.open((Player) sender);
                return true;
            }
    	}
		return false;
    }
    
    public boolean ECPayCommand(CommandSender sender, Command command, String label, String[] args,final ClassLoader classLoader, final String commandPath) {
    	if(args.length == 0) {
    		
    	}
    	return true;
    }
    
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    	List<String> show_commands = new ArrayList<String>();
		for (String key : DataBase.getCommands()){
			if(key.indexOf(args[0].toLowerCase()) != -1)
				show_commands.add(key);	
		}
		return show_commands;
    }
}
