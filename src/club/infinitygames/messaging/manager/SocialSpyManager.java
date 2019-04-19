package club.infinitygames.messaging.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class SocialSpyManager {
	
	// Just the basic singleton stuff
	public static SocialSpyManager me;
	
	public static SocialSpyManager get() {
		if(me == null) {
			me = new SocialSpyManager();
		}
		return me;
	}
	
	// then we need to have all kinds of crap in here for saving data
	
	// Also yes im using Strings instead of Players just to make it easier for me to work with
	private List<String> _spyingStaff = new ArrayList<String>();
	
	public List<String> getSpyingStaff() {
		return _spyingStaff;
	}
	
	public void toggleSS(Player player) {
		if(inSocialSpy(player)) {
			_spyingStaff.remove(player.getName());
		} else {
			_spyingStaff.add(player.getName());
		}
	}
	
	public void staff(String sender, String reciever, String message) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(inSocialSpy(p)) {
				p.sendMessage(ChatColor.YELLOW + "[SS] " +
			ChatColor.GREEN + "From " + ChatColor.GRAY + sender + " " + ChatColor.GREEN + "To "
			+ ChatColor.GRAY + reciever + ": " + ChatColor.YELLOW + message);
			}
		}
	}
	
	public boolean inSocialSpy(Player player) {
		boolean spying = false;
		for(String str : getSpyingStaff()) {
			if(player.getName().equalsIgnoreCase(str)) {
				spying = true;
			}
		}
		return spying;
	}
}
