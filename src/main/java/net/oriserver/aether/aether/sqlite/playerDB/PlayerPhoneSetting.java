package net.oriserver.aether.aether.sqlite.playerDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerPhoneSetting extends SQLiteAPI {//プレイヤーごとのスマホ設定を保存するクラス
    public PlayerPhoneSetting(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Phone_Setting (" +
                "`player_uuid` varchar NOT NULL," +
                "`phone` varchar," +
                "`partition` varchar," +
                "`checkpoint` varchar," +
                "PRIMARY KEY (`player_uuid`)" +
                ");";
        initialize(sql);
    }
    public ArrayList<Object> getData(String uuid) {
        List<Object> playerdata = getDB("SELECT * FROM Phone_Setting WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Object> pd = new ArrayList<>();
            while(rs.next()){
                pd.add(rs.getInt("phone"));
                pd.add(rs.getInt("partition"));
                pd.add(rs.getInt("checkpoint"));
            }
            return pd;
        });
        return  (ArrayList<Object>) playerdata;
    }

    public void setData(String uuid,int[] data){
        setDB("UPDATE Phone_Setting SET phone = ? WHERE player_uuid = ?",Arrays.asList(data[0],uuid));
        setDB("UPDATE Phone_Setting SET partition = ? WHERE player_uuid = ?",Arrays.asList(data[1],uuid));
        setDB("UPDATE Phone_Setting SET checkpoint = ? WHERE player_uuid = ?",Arrays.asList(data[2],uuid));
    }

    public void insertData(String uuid){
        setDB("INSERT OR IGNORE INTO Phone_Setting (player_uuid,phone,partition,checkpoint) VALUES(?,?,?,?);",Arrays.asList(uuid,0,0,0));
    }
}
