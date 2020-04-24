package de.albrae.test.cmds;


import de.albrae.test.main.Main;


import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TpaCmd extends Command {

 

	public TpaCmd() {
		super("tpa");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("&7You can not use this command as console or comandblocks"));
            return ;
        }

        if (args.length == 1) {
            ProxiedPlayer fromPlayer = (ProxiedPlayer) sender;

            String targetName = args[0];
            ProxiedPlayer targetPlayer = ProxyServer.getInstance().getPlayer(targetName);

            if (targetPlayer == null) {
                sender.sendMessage(new TextComponent("Kann den Spieler §c"+args[0]+" nicht finden"));
                return ;
            }
            else {
            	ServerInfo target = targetPlayer.getServer().getInfo();
            	if (!fromPlayer.getServer().getInfo().getName().equalsIgnoreCase(targetPlayer.getServer().getInfo().getName())){
            		fromPlayer.connect(target);
            	}
            	fromPlayer.sendMessage(new TextComponent("§cWarte auf deinen Teleport"));
   				 
    			    	Main.getPlugin().sendToServer("TP", fromPlayer.getName(), targetPlayer.getName() , target);
    	            	targetPlayer.sendMessage(new TextComponent("§6"+fromPlayer.getName()+" versucht sich zu dir teleportieren"));
    	            	
    			  
            	
            	
            	
            }

            
        } else sender.sendMessage(new TextComponent("Verwende /tpa <Spieler>"));
        return ;
    }

	

	
	
}