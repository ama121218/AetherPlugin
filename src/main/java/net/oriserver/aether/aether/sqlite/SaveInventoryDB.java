package net.oriserver.aether.aether.sqlite;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveInventoryDB extends SQLiteAPI{
    public SaveInventoryDB(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Save_Inventory (" +
                "`player_uuid` varchar NOT NULL," +
                "`inventory_name` varchar," +
                "`slot_number` varchar," +
                "`item` varchar," +
                "`item_name` varchar" +
                ");";
        initialize(sql);
    }
    public ArrayList<String> getInventoryData(String uuid) {
        List<String> data = getDB("SELECT * FROM Save_Inventory WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<String> dataDB = new ArrayList<>();
            while(rs.next()){
                if(!dataDB.contains(rs.getString("inventory_name"))){
                    dataDB.add(rs.getString("inventory_name"));
                }
            }
            return dataDB;
        });
        return  (ArrayList<String>) data;
    }

    public ArrayList<Object[]> getDataInInventory(String uuid,String inventory_name) {
        List<Object[]> data = getDB("SELECT * FROM Save_Inventory WHERE player_uuid = ? AND inventory_name = ?", Arrays.asList(uuid,inventory_name), rs -> {
            List<Object[]> d = new ArrayList<>();
            while(rs.next()){
                Object[] objects = new Object[3];
                objects[0] = rs.getInt("slot_number");
                objects[1] = rs.getString("item");
                objects[2] = rs.getString("item_name");
                d.add(objects);
            }
            return d;
        });
        return  (ArrayList<Object[]>) data;
    }



    public void insertData(String uuid,String inventory_name,int slot_number,String item,String item_name) {
        setDB("INSERT OR IGNORE INTO Save_Inventory (player_uuid,inventory_name,slot_number,item,item_name) VALUES(?,?,?,?,?);",
                Arrays.asList(uuid,inventory_name,slot_number,item,item_name));
    }
    public void updateData(String uuid,String inventory_name1,String inventory_name2){
        setDB("UPDATE Save_Inventory SET inventory_name = ? WHERE player_uuid = ? AND inventory_name = ?",Arrays.asList(inventory_name2,uuid,inventory_name1));
    }

    public void deleteData(String uuid,String inventory_name){
        setDB("DELETE FROM Save_Inventory WHERE player_uuid = ? AND inventory_name = ?",Arrays.asList(uuid,inventory_name));
    }
}
