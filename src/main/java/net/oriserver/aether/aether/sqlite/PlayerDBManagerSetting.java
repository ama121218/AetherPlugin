package net.oriserver.aether.aether.sqlite;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerDBManagerSetting extends SQLiteAPI{

    PlayerDBManagerSetting(JavaPlugin plugin,String dbname){
        super(plugin,dbname);

        String sql = "CREATE TABLE IF NOT EXISTS Setting (" +
            "`player_uuid` varchar NOT NULL," +
            "`particle` varchar," +
            "`mail` varchar," +
            "`friend` varchar," +
            "`chatroom` varchar," +
            "`playersidebar` varchar," +
            "PRIMARY KEY (`player_uuid`)" +
            ");";
        initialize(sql);
    }

    public ArrayList<Object> getPlayerData(String uuid) {
        List<Object> playerdata = getDB("SELECT * FROM Setting WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Object> pd = new ArrayList<>();
            while(rs.next()){
                pd.add(rs.getBoolean("particle"));
                pd.add(rs.getBoolean("mail"));
                pd.add(rs.getBoolean("friend"));
                pd.add(rs.getBoolean("chatroom"));
                pd.add(rs.getBoolean("playersidebar"));
            }
            return pd;
        });
        return  (ArrayList<Object>) playerdata;
    }

    public void setPlayerData(String uuid,boolean[] data){
        setDB("UPDATE Setting SET particle = ? WHERE player_uuid = ?",Arrays.asList(data[0],uuid));
        setDB("UPDATE Setting SET mail = ? WHERE player_uuid = ?",Arrays.asList(data[1],uuid));
        setDB("UPDATE Setting SET friend = ? WHERE player_uuid = ?",Arrays.asList(data[2],uuid));
        setDB("UPDATE Setting SET chatroom = ? WHERE player_uuid = ?",Arrays.asList(data[3],uuid));
        setDB("UPDATE Setting SET playersidebar = ? WHERE player_uuid = ?",Arrays.asList(data[4],uuid));
    }

    public void insertPlayerData(String uuid){
        setDB("INSERT OR IGNORE INTO Setting (player_uuid,particle,mail,friend,chatroom,playersidebar) VALUES(?,?,?,?,?,?);",Arrays.asList(uuid,false,false,false,false,false));
    }








}
