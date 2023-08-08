package net.oriserver.aether.aether.sqlite;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerDBManagerR extends SQLiteAPI {

    public PlayerDBManagerR(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Player_data_R (" +
                "`player_uuid` varchar NOT NULL," +
                "`level` int NOT NULL," +
                "`global` int NOT NULL," +
                "`chart` int NOT NULL," +
                "`star` int NOT NULL," +
                "`AP` int NOT NULL," +
                "PRIMARY KEY (`player_uuid`)" +
                ");";
        initialize(sql);
    }

    public ArrayList<Integer> getPlayerData(String uuid) {
        List<Integer> playerdata = getDB("SELECT * FROM Player_data_R WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Integer> pd = new ArrayList<>();
            while(rs.next()){
                pd.add(rs.getInt("level"));
                pd.add(rs.getInt("global"));
                pd.add(rs.getInt("chart"));
                pd.add(rs.getInt("star"));
                pd.add(rs.getInt("AP"));
            }
            return pd;
        });
        return  (ArrayList<Integer>) playerdata;
    }

    public void setPlayerLevel(String uuid,int level){
        setDB("UPDATE Player_data_R SET level = ? WHERE player_uuid = ?",Arrays.asList(level,uuid));
    }

    public void insertPlayerData(String uuid){
        setDB("INSERT OR IGNORE INTO Player_data_R (player_uuid,level,global,chart,star,AP) VALUES(?,?,?,?,?,?);",Arrays.asList(uuid,0,0,0,0,0));
    }

}
