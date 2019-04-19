package club.infinitygames.messaging.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class MessageManager {
	public static MessageManager me;
	
	public static MessageManager get() {
		if(me == null) {
			me = new MessageManager();
		}
		return me;
	}
	
	private List<String> _dontDm = new ArrayList<String>();
	
	public List<String> getDisabled() {
		return _dontDm;
	}
	
	public void toggleDm(Player player) {
		if(sendDm(player)) {
			_dontDm.add(player.getName());
		} else {
			_dontDm.remove(player.getName());
		}
	}
	
	public boolean sendDm(Player player) {
		boolean send = true;
		for(String str : getDisabled()) {
			if(player.getName().equalsIgnoreCase(str)) {
				send = false;
			}
		}
		return send;
	}
}
