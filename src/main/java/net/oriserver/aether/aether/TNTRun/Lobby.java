package net.oriserver.aether.aether.TNTRun;

import org.bukkit.entity.Player;

import java.util.HashSet;

public class Lobby {
    private String lobbyName;
    private Stage stage;
    private String lobbyCreatorName;

    private HashSet<String> playerSet = new HashSet<>();
    Lobby(Stage stage,String lobbyCreatorName){
        this.stage = stage;
        this.lobbyCreatorName = lobbyCreatorName;
    }


    public void addPlayer(Player p){
        playerSet.add(p.getUniqueId().toString());
    }
    public void removePlayer(Player p){
        playerSet.remove(p.getUniqueId().toString());
    }


}
