package treetwerk.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


import net.md_5.bungee.api.ChatColor;


public class Commands implements CommandExecutor 
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("TreeTwerk"))
		{
			sender.sendMessage(ChatColor.DARK_PURPLE + "TreeTwerk: " + ChatColor.YELLOW + "version 0.1.2");
			sender.sendMessage(ChatColor.DARK_PURPLE + "TreeTwerk: " + ChatColor.YELLOW + "started to reload");				
			treetwerk.main.ConfigFolder config = new treetwerk.main.ConfigFolder();
			config.ClearHashMap();
			config.ReadFile();
			sender.sendMessage(ChatColor.DARK_PURPLE + "TreeTwerk: " + ChatColor.YELLOW + "reloaded");		
		}	
		return false;
	}
}