package club.infinitygames.messaging.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import club.infinitygames.messaging.manager.SocialSpyManager;
import net.md_5.bungee.api.ChatColor;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onStaffJoin(PlayerJoinEvent e) {
		// Check if the player is a staff member
		if(!e.getPlayer().hasPermission("infinitygames.socialspy")) {
			return;
		}
		Player staff = e.getPlayer();
		
		SocialSpyManager.get().getSpyingStaff().add(staff.getName());
		staff.sendMessage(ChatColor.YELLOW + "Your Social Spy has automatticly been toggled " + ChatColor.GREEN + "ON!");
		
		return;
	}

}
