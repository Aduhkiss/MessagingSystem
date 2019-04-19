package club.infinitygames.messaging.command;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import club.infinitygames.messaging.MessagingSystem;
import club.infinitygames.messaging.manager.MessageManager;
import club.infinitygames.messaging.manager.SocialSpyManager;
import net.md_5.bungee.api.ChatColor;

public class MessageCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player player = (Player) sender;
		
		// Check arguments
		if(args == null || args.length < 2) {
			// Stfu, yea i know that I use return true even when the command is hitting an "error"
			player.sendMessage(ChatColor.RED + "Command Usage: /m <Player> <Message>");
			return true;
		}
		
		// Once we are sure that we have everything, then we want to get the message and the player
		Player target = Bukkit.getPlayer(args[0]);
		
		// Check if the player is online
		if(target == null) {
			player.sendMessage(ChatColor.RED + "Sorry but there is no player matching the name of "
		+ ChatColor.YELLOW + args[0] + ChatColor.RED + " currently connected to the server!");
			return true;
		}
		
		// Check if the player actually wants to recieve messages
		if(!MessageManager.get().sendDm(target)) {
			player.sendMessage(ChatColor.RED + "Sorry, but " + ChatColor.YELLOW + target.getName() + ChatColor.RED + " has messages disabled!");
			return true;
		}
		
		// Then we will get the message that the player gave
		String fullMessage = combine(args, 1);
		
		//TODO: Filter the message through the Infinity Chat filter
		
		// Then we will send the message to the player
		
		BukkitRunnable a = new BukkitRunnable() {
			public void run() {
				// Notice in this code, we are using the real names for the players
				SocialSpyManager.get().staff(player.getName(), target.getName(), fullMessage);
			}
		};
		a.runTaskAsynchronously(MessagingSystem.getInstance());
		
		// We use the display name, since we want to use the disguise system as well
		
		//TODO: Implement a way for people to disable private messages
		// and maybe add to a config file for storage?
		
		// Do this crap on another thread entirly just cause I thought of it right now
		
		BukkitRunnable r = new BukkitRunnable() {
			public void run() {
				player.sendMessage(ChatColor.GREEN + "To " + ChatColor.GRAY + target.getDisplayName() + ": " + 
						ChatColor.YELLOW + fullMessage);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 1f);
					
					target.sendMessage(ChatColor.GREEN + "From " + ChatColor.GRAY + player.getDisplayName() + ": " +
					ChatColor.YELLOW + fullMessage);
					target.playSound(target.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 1f);
			}
		};
		r.runTaskAsynchronously(MessagingSystem.getInstance());
		
		// Also log the message to console
		Bukkit.getConsoleSender().sendMessage("[Infinity Message Logs]" + " To " + target.getDisplayName() + " From " + player.getDisplayName() + ": " + fullMessage);
		
		return true;
	}

	/*
	 * Stolen code from my older api
	 * Author of the code is still Atticus tho
	 */
	
	public static String combine(String[] arr, int startPos) {
        StringBuilder str = new StringBuilder();

        for(int i = startPos; i < arr.length; ++i) {
           str = str.append(arr[i] + " ");
        }
        return str.toString();
	}
}
