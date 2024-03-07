package net.oriserver.aether.aether.sqlite.playerDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerJoinQuitDataDB extends SQLiteAPI {//プレイヤーが入出退出時に保存するデータを扱うクラス

    public PlayerJoinQuitDataDB(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Player_data_JQ (" +
                "`player_uuid` varchar NOT NULL," +
                "`jump_count` int NOT NULL," +
                "`last_local` int NOT NULL," +
                "`play_time` int NOT NULL," +
                "PRIMARY KEY (`player_uuid`)" +
                ");";
        initialize(sql);
    }

    public ArrayList<Object> getData(String uuid) {
        List<Object> playerdata = getDB("SELECT * FROM Player_data_JQ WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Object> pd = new ArrayList<>();
            while(rs.next()){
                pd.add(rs.getInt("jump_count"));
                pd.add(rs.getString("last_local"));
                pd.add(rs.getLong("play_time"));
            }
            return pd;
        });
        return  (ArrayList<Object>) playerdata;
    }

    public void setData(String uuid,Object[] data){
        setDB("UPDATE Player_data_JQ SET jump_count = ? WHERE player_uuid = ?",Arrays.asList(data[0],uuid));
        setDB("UPDATE Player_data_JQ SET last_local = ? WHERE player_uuid = ?",Arrays.asList(data[1],uuid));
        setDB("UPDATE Player_data_JQ SET play_time = ? WHERE player_uuid = ?",Arrays.asList(data[2],uuid));
    }

    public void insertPlayerData(String uuid){
        setDB("INSERT OR IGNORE INTO Player_data_JQ (player_uuid,jump_count,last_local,play_time) VALUES(?,?,?,?);",Arrays.asList(uuid,0,"lobby",0));
    }

}