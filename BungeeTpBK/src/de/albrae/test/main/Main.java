package de.albrae.test.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.albrae.test.listener.TeleportListener;

public class Main extends JavaPlugin {
	
	private TeleportListener channelListenter;
	private static Main plugin;
	
	public void onEnable() {
		plugin=this; 

		this.channelListenter = new TeleportListener();
		
		
		Bukkit.getMessenger().registerIncomingPluginChannel(this, "tp:channel", channelListenter);
	}
	public static Main getPlugin() {
		return plugin;
	}
}
