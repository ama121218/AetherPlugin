package net.oriserver.aether.aether.player;

import net.oriserver.aether.aether.sqlite.PlayerDBManagerJQ;
import net.oriserver.aether.aether.sqlite.PlayerDBManagerR;
import net.oriserver.aether.aether.sqlite.PlayerDBManagerSetting;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private final Map<String, PlayerStats> players = new HashMap<String,PlayerStats>();
    private final SQLiteManager sqLiteManager;

    public PlayerManager(SQLiteManager sqLiteManager){
        this.sqLiteManager = sqLiteManager;
    }

    public void addPlayer(Player player){
        players.put(String.valueOf(player.getUniqueId()), new PlayerStats(player,sqLiteManager));
    }
    public SQLiteManager getSqLiteManager(){return  this.sqLiteManager;}

    public void removePlayer(String uuid) {
        players.remove(uuid);
    }
    public PlayerStats getPlayer(String uuid){return players.get(uuid);}
    public boolean isPlayer(String uuid){return players.containsKey(uuid);}




}
