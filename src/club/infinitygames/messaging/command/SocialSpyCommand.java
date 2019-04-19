package club.infinitygames.messaging.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import club.infinitygames.messaging.manager.SocialSpyManager;
import net.md_5.bungee.api.ChatColor;

public class SocialSpyCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(SocialSpyManager.get().inSocialSpy(player)) {
			player.sendMessage(ChatColor.YELLOW + "You will no longer see players messages!");
		} else {
			player.sendMessage(ChatColor.YELLOW + "You will now see players messages!");
		}
		
		SocialSpyManager.get().toggleSS(player);
		
		return true;
	}

}
