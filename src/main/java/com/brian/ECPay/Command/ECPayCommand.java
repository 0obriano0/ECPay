package com.brian.ECPay.Command;

import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.brian.ECPay.DataBase.DataBase;
import com.google.common.collect.Lists;

public class ECPayCommand implements IECPayCommand {
	
	private final transient String name;
	
	protected ECPayCommand(final String name) {
        this.name = name;
    }
	
	@Override
	public String getName() {
        return name;
    }

	@Override
	public void run(CommandSender sender, String commandLabel, Command cmd, String[] args) throws Exception {
		
	}

	@Override
	public void run(Player player, String commandLabel, Command cmd, String[] args) throws Exception {
		
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String commandLabel, Command cmd, String[] args) {
		return null;
	}

	@Override
	public List<String> tabComplete(Player player, String commandLabel, Command cmd, String[] args) {
		return null;
	}
	
	/**
     * Lists all commands.
     *
     * TODO: Use the real commandmap to do this automatically.
     */
    protected final List<String> getCommands() {
        List<String> commands = Lists.newArrayList();
        for (Plugin p : DataBase.server.getPluginManager().getPlugins()) {
            final PluginDescriptionFile desc = p.getDescription();
            final Map<String, Map<String, Object>> cmds = desc.getCommands();
            if (cmds != null) {
                commands.addAll(cmds.keySet());
            }
        }
        return commands;
    }

}
