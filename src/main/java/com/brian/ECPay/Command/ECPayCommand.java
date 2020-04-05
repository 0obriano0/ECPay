package com.brian.ECPay.Command;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.collect.Lists;

public class ECPayCommand implements IECPayCommand {
	
	private final transient String name;
	private final transient List<String> permissions;
	
	protected ECPayCommand(final String name,final List<String> permissions) {
        this.name = name;
        this.permissions = permissions;
    }
	
	@Override
	public String getName() {
        return name;
    }
	
	@Override
	public List<String> getPermissions() {
		return permissions;
	}
	
	@Override
	public boolean hasPermission(CommandSender sender) {
		if (!(sender instanceof Player))
			return true;
		else
			return hasPermission((Player)sender);
	}
	
	@Override
	public boolean hasPermission(Player player) {
		if(player.isOp())
			return true;
		for(String per:permissions) {
			if (!player.hasPermission(per))
				return false;
		}
		return true;
	}
	
	@Override
	public void run(CommandSender sender, String commandLabel, Command cmd, String[] args) throws Exception {
		
	}

	@Override
	public void run(Player player, String commandLabel, Command cmd, String[] args) throws Exception {
		
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String commandLabel, Command cmd, String[] args) {
		return Collections.emptyList();
	}

	@Override
	public List<String> tabComplete(Player player, String commandLabel, Command cmd, String[] args) {
		return Collections.emptyList();
	}

}
