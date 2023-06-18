package net.oriserver.aether.aether.player;

import org.bukkit.entity.Player;

public class PlayerStats {
    private Player player;
    private int level;
    private int global;
    private int aetherpoint;

    private String chatroom;
    private String[] friend;

    private String language;

    PlayerStats(Player player){
        this.player = player;
    }

    public void initialize(){

    }

}
