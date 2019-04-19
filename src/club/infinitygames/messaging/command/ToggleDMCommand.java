package club.infinitygames.messaging.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import club.infinitygames.messaging.manager.MessageManager;
import net.md_5.bungee.api.ChatColor;

public class ToggleDMCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(MessageManager.get().sendDm(player)) {
			player.sendMessage(ChatColor.YELLOW + "You have disabled private messages!");
		} else {
			player.sendMessage(ChatColor.YELLOW + "You have enabled private messages!");
		}
		
		MessageManager.get().toggleDm(player);
		
		return true;
	}
}
