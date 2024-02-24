package net.oriserver.aether.aether.sqlite.adminDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveItemDB extends SQLiteAPI {
    public SaveItemDB(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Save_Item (" +
                "`player_uuid` varchar NOT NULL," +
                "`item` varchar," +
                "`item_name` varchar" +
                ");";
        initialize(sql);
    }
    public ArrayList<Object[]> getData(String uuid) {
        List<Object[]> data = getDB("SELECT * FROM Save_Item WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Object[]> d = new ArrayList<>();
            while(rs.next()){
                Object[] objects = new Object[2];
                objects[0] = rs.getString("item");
                objects[1] = rs.getString("item_name");
                d.add(objects);
            }
            return d;
        });
        return  (ArrayList<Object[]>) data;
    }
    public void insertData(String uuid,String item,String item_name) {
        setDB("DELETE FROM Save_Item WHERE player_uuid = ? AND item_name = ?",Arrays.asList(uuid,item_name));
        setDB("INSERT OR IGNORE INTO Save_Item (player_uuid,item,item_name) VALUES(?,?,?);",
                Arrays.asList(uuid,item,item_name));
    }
    public void updateData(String uuid,String item_name1,String item_name2,String item){
        setDB("UPDATE Save_Item SET item = ?, item_name = ? WHERE player_uuid = ? AND item_name = ?",Arrays.asList(item,item_name2,uuid,item_name1));
    }

    public void deleteData(String uuid,String item_name){
        setDB("DELETE FROM Save_Item WHERE player_uuid = ? AND item_name = ?",Arrays.asList(uuid,item_name));
    }
}
