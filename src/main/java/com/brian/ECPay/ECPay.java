package com.brian.ECPay;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.eclipse.jetty.server.Server;

import com.brian.ECPay.webHandler.ResponseHandler;
import com.brian.ECPay.InventoryGUI.InventoryMenu;

import example.ExampleAllInOne;

public class ECPay extends JavaPlugin {
	public static Plugin plugin;
	public static int port;
	public static String ServerIP;
	public static Server server;
	public static BukkitTask webTask;
	public ExampleAllInOne a;
    @Override
    public void onEnable() {
        plugin = this;
        
        saveDefaultConfig();
        reloadConfig();
        port = getConfig().getInt("port");
        ServerIP = getConfig().getString("ServerIP");
        
        /*this.getLogger().info("將開啟port " + port + " 作為 HTTP API 伺服器");
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
        a.test();*/
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
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("ecpay")) {
            if ((sender instanceof Player)) {
            	//a.test();
            	InventoryMenu.INVENTORY.open((Player) sender);
                return true;
            }
    	}
		return false;
    }
}
