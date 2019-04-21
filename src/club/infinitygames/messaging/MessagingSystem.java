package club.infinitygames.messaging;

import org.bukkit.plugin.java.JavaPlugin;

import club.infinitygames.messaging.command.MessageCommand;
import club.infinitygames.messaging.command.SocialSpyCommand;
import club.infinitygames.messaging.command.ToggleDMCommand;

public class MessagingSystem extends JavaPlugin {
	
	public static MessagingSystem me;

	public void onEnable() {
		me = this;
		// register any commands I coded
		getCommand("message").setExecutor(new MessageCommand());
		getCommand("socialspy").setExecutor(new SocialSpyCommand());
		getCommand("toggledm").setExecutor(new ToggleDMCommand());
		
		// then register the event handlers
		//Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		
	}
	
	public static MessagingSystem getInstance() {
		return me;
	}
}
