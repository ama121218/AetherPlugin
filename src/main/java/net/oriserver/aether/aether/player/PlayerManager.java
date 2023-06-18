package net.oriserver.aether.aether.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private final Map<String, PlayerStats> players = new HashMap<String,PlayerStats>();

    public void addPlayer(Player player){
        players.put(String.valueOf(player.getUniqueId()), new PlayerStats(player));
    }
    public void removePlayer(Player player) {
        players.remove(String.valueOf(player.getUniqueId()));
    }
    public PlayerStats getPlayer(Player player){
        return players.get(String.valueOf(player.getUniqueId()));
    }




}
