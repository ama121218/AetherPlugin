package net.oriserver.aether.aether.sqlite.adminDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveCommandDB extends SQLiteAPI {
    public SaveCommandDB(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Save_Command (" +
                "`player_uuid` varchar NOT NULL," +
                "`item` varchar," +
                "`command` varchar" +
                ");";
        initialize(sql);
    }
    public ArrayList<Object[]> getData(String uuid) {
        List<Object[]> data = getDB("SELECT * FROM Save_Command WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Object[]> d = new ArrayList<>();
            while(rs.next()){
                Object[] objects = new Object[2];
                objects[0] = rs.getString("item");
                objects[1] = rs.getString("command");
                d.add(objects);
            }
            return d;
        });
        return  (ArrayList<Object[]>) data;
    }
    public void insertData(String uuid,String item,String command) {
        setDB("DELETE FROM Save_Command WHERE player_uuid = ? AND command = ?",Arrays.asList(uuid,command));
        setDB("INSERT OR IGNORE INTO Save_Command (player_uuid,item,command) VALUES(?,?,?);",
                Arrays.asList(uuid,item,command));
    }
    public void updateData(String uuid,String command1,String command2,String item){
        setDB("UPDATE Save_Command SET item = ?, command = ? WHERE player_uuid = ? AND command = ?",Arrays.asList(item,command2,uuid,command1));
    }

    public void deleteData(String uuid,String command){
        setDB("DELETE FROM Save_Command WHERE player_uuid = ? AND command = ?",Arrays.asList(uuid,command));
    }
}
