package net.oriserver.aether.aether.sqlite.playerDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeadBlockDB extends SQLiteAPI {
    public HeadBlockDB(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS HeadBlock (" +
                "`player_uuid` varchar NOT NULL," +
                "`s1` varchar NOT NULL," +
                "`s2` varchar NOT NULL," +
                "`s3` varchar NOT NULL," +
                "`s4` varchar NOT NULL," +
                "PRIMARY KEY (`player_uuid`)" +
                ");";
        initialize(sql);
    }

    public ArrayList<Object> getData(String uuid) {
        List<Object> playerdata = getDB("SELECT * FROM HeadBlock WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Object> pd = new ArrayList<>();
            while(rs.next()){
                pd.add(rs.getString("s1"));
                pd.add(rs.getString("s2"));
                pd.add(rs.getString("s3"));
                pd.add(rs.getString("s4"));
            }
            return pd;
        });
        return  (ArrayList<Object>) playerdata;
    }

    public void setData(String uuid,String[] data){
        setDB("UPDATE HeadBlock SET s1 = ? WHERE player_uuid = ?",Arrays.asList(data[0],uuid));
        setDB("UPDATE HeadBlock SET s2 = ? WHERE player_uuid = ?",Arrays.asList(data[1],uuid));
        setDB("UPDATE HeadBlock SET s3 = ? WHERE player_uuid = ?",Arrays.asList(data[2],uuid));
        setDB("UPDATE HeadBlock SET s4 = ? WHERE player_uuid = ?",Arrays.asList(data[2],uuid));
    }

    public void insertData(String uuid){
        setDB("INSERT OR IGNORE INTO HeadBlock (player_uuid,s1,s2,s3,s4) VALUES(?,?,?,?,?);",Arrays.asList(uuid,"000000000000000","000000000000000","000000000000000","000000000000000"));
    }
}
