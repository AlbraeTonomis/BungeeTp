package de.albrae.test.listener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.albrae.test.main.Main;



public class TeleportListener implements PluginMessageListener{
	String sub;
	Player move;
	Player stand;

	private int taskID;
	private ArrayList<String> schedul =new ArrayList<>();
	
	@Override
	public void onPluginMessageReceived( String channel,  Player player,  byte[] message) {
		
		DataInputStream stream = new DataInputStream(new ByteArrayInputStream(message));
		System.out.println("Info eingegangen");
		
		try {
			String subChannel;
			subChannel = stream.readUTF();
			sub=subChannel;
			String moving = stream.readUTF();
			String standing= stream.readUTF();
			if (schedul.contains(Bukkit.getPlayer(moving).getName())||schedul.contains(Bukkit.getPlayer(standing).getName())) {
				Bukkit.getPlayer(moving).sendMessage("§6 Teleport abgebrochen. "+"§cEin Spieler befindet sich in einem Teleportvorgang");
				return;
			}
			move=Bukkit.getPlayer(moving);
			stand=Bukkit.getPlayer(standing);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
			schedul.add(move.getName());
			schedul.add(stand.getName());
		move.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,20*5,2));
		
		taskID=Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			int i = 4;
			@Override
			public void run() {
				if (i<=0) {
					
					if (sub.equals("TP")) {
					    	
							move.teleport(stand);
							move.sendMessage("§6 Du wurdest zu §c"+stand.getName()+" §6teleportiert");
							stand.sendMessage("§6"+move.getName()+" hat sich zu dir teleportiert");
							schedul.remove(move.getName());
							schedul.remove(stand.getName());
					}
					
					Bukkit.getScheduler().cancelTask(taskID);
				}
				else {
				move.sendMessage("Noch §6"+i+" Sekunden.");
				i--;
			}
			}
		}, 0, 20);
		}  
	
		
		
	}
	
	

