package net.oriserver.aether.aether.sqlite.playerDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerDBManagerSetting extends SQLiteAPI {

    public PlayerDBManagerSetting(JavaPlugin plugin, String dbname){
        super(plugin,dbname);

        String sql = "CREATE TABLE IF NOT EXISTS Player_Setting (" +
            "`player_uuid` varchar NOT NULL," +
            "`particle` varchar," +
            "`mail` varchar," +
            "`friend` varchar," +
            "`chatroom` varchar," +
            "`playersidebar` varchar," +
            "`chart_again` varchar,"+
            "PRIMARY KEY (`player_uuid`)" +
            ");";
        initialize(sql);
    }

    public ArrayList<Object> getPlayerData(String uuid) {
        List<Object> playerdata = getDB("SELECT * FROM Player_Setting WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Object> pd = new ArrayList<>();
            while(rs.next()){
                pd.add(rs.getBoolean("particle"));
                pd.add(rs.getBoolean("mail"));
                pd.add(rs.getBoolean("friend"));
                pd.add(rs.getBoolean("chatroom"));
                pd.add(rs.getBoolean("playersidebar"));
                pd.add(rs.getBoolean("chart_again"));
            }
            return pd;
        });
        return  (ArrayList<Object>) playerdata;
    }

    public void setPlayerData(String uuid,boolean[] data){
        setDB("UPDATE Player_Setting SET particle = ? WHERE player_uuid = ?",Arrays.asList(data[0],uuid));
        setDB("UPDATE Player_Setting SET mail = ? WHERE player_uuid = ?",Arrays.asList(data[1],uuid));
        setDB("UPDATE Player_Setting SET friend = ? WHERE player_uuid = ?",Arrays.asList(data[2],uuid));
        setDB("UPDATE Player_Setting SET chatroom = ? WHERE player_uuid = ?",Arrays.asList(data[3],uuid));
        setDB("UPDATE Player_Setting SET playersidebar = ? WHERE player_uuid = ?",Arrays.asList(data[4],uuid));
        setDB("UPDATE Player_Setting SET chart_again = ? WHERE player_uuid = ?",Arrays.asList(data[5],uuid));
    }

    public void insertPlayerData(String uuid){
        setDB("INSERT OR IGNORE INTO Player_Setting (player_uuid,particle,mail,friend,chatroom,playersidebar,chart_again) VALUES(?,?,?,?,?,?,?);",Arrays.asList(uuid,false,false,false,false,false,false));
    }


}
