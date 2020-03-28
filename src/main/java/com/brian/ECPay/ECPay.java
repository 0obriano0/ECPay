package com.brian.ECPay;

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
    
    /*@Override
    public boolean onCommand(final CommandSender sender, final Command command, final String commandLabel, final String[] args) {
        return onCommandEssentials(sender, command, commandLabel, args, Essentials.class.getClassLoader(), "com.earth2me.essentials.commands.Command", "essentials.", null);
    }

    @Override
    public boolean onCommandEssentials(final CommandSender cSender, final Command command, final String commandLabel, final String[] args, final ClassLoader classLoader, final String commandPath, final String permissionPrefix, final IEssentialsModule module) {
        // Allow plugins to override the command via onCommand
        if (!getSettings().isCommandOverridden(command.getName()) && (!commandLabel.startsWith("e") || commandLabel.equalsIgnoreCase(command.getName()))) {
            final PluginCommand pc = alternativeCommandsHandler.getAlternative(commandLabel);
            if (pc != null) {
                alternativeCommandsHandler.executed(commandLabel, pc);
                try {
                    return pc.execute(cSender, commandLabel, args);
                } catch (final Exception ex) {
                    Bukkit.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
                    cSender.sendMessage(tl("internalError"));
                    return true;
                }
            }
        }

        try {

            User user = null;
            Block bSenderBlock = null;
            if (cSender instanceof Player) {
                user = getUser((Player) cSender);
            } else if (cSender instanceof BlockCommandSender) {
                BlockCommandSender bsender = (BlockCommandSender) cSender;
                bSenderBlock = bsender.getBlock();
            }

            if (bSenderBlock != null) {
                if (getSettings().logCommandBlockCommands()) {
                    Bukkit.getLogger().log(Level.INFO, "CommandBlock at {0},{1},{2} issued server command: /{3} {4}", new Object[]{bSenderBlock.getX(), bSenderBlock.getY(), bSenderBlock.getZ(), commandLabel, EssentialsCommand.getFinalArg(args, 0)});
                }
            } else if (user == null) {
                Bukkit.getLogger().log(Level.INFO, "{0} issued server command: /{1} {2}", new Object[]{cSender.getName(), commandLabel, EssentialsCommand.getFinalArg(args, 0)});
            }

            CommandSource sender = new CommandSource(cSender);

            // New mail notification
            if (user != null && !getSettings().isCommandDisabled("mail") && !command.getName().equals("mail") && user.isAuthorized("essentials.mail")) {
                user.notifyOfMail();
            }

            //Print version even if admin command is not available #easteregg
            if (commandLabel.equalsIgnoreCase("essversion")) {
                sender.sendMessage("This server is running Essentials " + getDescription().getVersion());
                return true;
            }

            // Check for disabled commands
            if (getSettings().isCommandDisabled(commandLabel)) {
                return true;
            }

            IEssentialsCommand cmd;
            try {
                cmd = (IEssentialsCommand) classLoader.loadClass(commandPath + command.getName()).newInstance();
                cmd.setEssentials(this);
                cmd.setEssentialsModule(module);
            } catch (Exception ex) {
                sender.sendMessage(tl("commandNotLoaded", commandLabel));
                LOGGER.log(Level.SEVERE, tl("commandNotLoaded", commandLabel), ex);
                return true;
            }

            // Check authorization
            if (user != null && !user.isAuthorized(cmd, permissionPrefix)) {
                LOGGER.log(Level.INFO, tl("deniedAccessCommand", user.getName()));
                user.sendMessage(tl("noAccessCommand"));
                return true;
            }

            if (user != null && user.isJailed() && !user.isAuthorized(cmd, "essentials.jail.allow.")) {
                if (user.getJailTimeout() > 0) {
                    user.sendMessage(tl("playerJailedFor", user.getName(), DateUtil.formatDateDiff(user.getJailTimeout())));
                } else {
                    user.sendMessage(tl("jailMessage"));
                }
                return true;
            }

            // Run the command
            try {
                if (user == null) {
                    cmd.run(getServer(), sender, commandLabel, command, args);
                } else {
                    cmd.run(getServer(), user, commandLabel, command, args);
                }
                return true;
            } catch (NoChargeException ex) {
                return true;
            } catch (QuietAbortException ex) {
                return true;
            } catch (NotEnoughArgumentsException ex) {
                sender.sendMessage(command.getDescription());
                sender.sendMessage(command.getUsage().replaceAll("<command>", commandLabel));
                if (!ex.getMessage().isEmpty()) {
                    sender.sendMessage(ex.getMessage());
                }
                return true;
            } catch (Exception ex) {
                showError(sender, ex, commandLabel);
                if (settings.isDebug()) {
                    ex.printStackTrace();
                }
                return true;
            }
        } catch (Throwable ex) {
            LOGGER.log(Level.SEVERE, tl("commandFailed", commandLabel), ex);
            return true;
        }
    }*/
}
