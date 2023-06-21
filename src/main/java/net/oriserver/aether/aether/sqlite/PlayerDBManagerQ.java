package net.oriserver.aether.aether.sqlite;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerDBManagerQ extends SQLiteManager{

    public PlayerDBManagerQ(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Player_data_Q (" +
                "`player_uuid` varchar NOT NULL," +
                "`jump_count` int NOT NULL," +
                "`last_local` int NOT NULL," +
                "`play_time` int NOT NULL," +
                "PRIMARY KEY (`player_uuid`)" +
                ");";
        initialize(sql);
    }

    public ArrayList<Integer> getPlayerData(String uuid) {
        List<Integer> playerdata = getDB("SELECT name FROM Player_data_Q WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Integer> pd = new ArrayList<>();
            while(rs.next()){
                pd.add(rs.getInt("jump_count"));
                pd.add(rs.getInt("last_local"));
                pd.add(rs.getInt("play_time"));
            }
            return pd;
        });
        return  (ArrayList<Integer>) playerdata;
    }

    public void setPlayerData(String uuid,int[] data){
        setDB("UPDATE Player SET jump_count = ? WHERE player_uuid = ?",Arrays.asList(data[0],uuid));
        setDB("UPDATE Player SET last_local = ? WHERE player_uuid = ?",Arrays.asList(data[1],uuid));
        setDB("UPDATE Player SET play_time = ? WHERE player_uuid = ?",Arrays.asList(data[2],uuid));
    }


}