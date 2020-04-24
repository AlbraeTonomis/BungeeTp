package de.albrae.test.main;




import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.albrae.test.cmds.TpaCmd;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
	private static Main plugin;
    @Override
    public void onEnable() {
    	plugin=this;
    	getProxy().getPluginManager().registerCommand(this, new TpaCmd());
        System.out.println("Yay! It loads!");
        BungeeCord.getInstance().registerChannel("tp:channel");
        getProxy().getPluginManager().registerListener(this, new Events(this));
    }
    public void sendToServer(String channel,String message,String message2, ServerInfo server) {
    	ByteArrayOutputStream stream= new ByteArrayOutputStream();
    	DataOutputStream output= new DataOutputStream(stream);
    	
    	try {
			output.writeUTF(channel);
			output.writeUTF(message);
			output.writeUTF(message2);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	server.sendData("tp:channel", stream.toByteArray());
    }

    public static Main getPlugin() {
	return plugin;
    }
}
