package net.oriserver.aether.aether.player;

import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlayerManager {
    private final Map<String, PlayerStats> players = new HashMap<String,PlayerStats>();
    private final SQLiteManager sqLiteManager;

    @Autowired
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

    @Override
    public String toString(){
        return "aaa";
    }

}
