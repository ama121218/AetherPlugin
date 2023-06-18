package net.oriserver.aether.aether.listener;

import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UsualListener implements Listener {

    private final PlayerManager pm;


    UsualListener(PlayerManager pm){
        this.pm = pm;
    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        pm.addPlayer(player);
    }





}
