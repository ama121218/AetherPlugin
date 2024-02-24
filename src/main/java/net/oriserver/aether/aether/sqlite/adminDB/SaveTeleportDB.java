package net.oriserver.aether.aether.sqlite.adminDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveTeleportDB extends SQLiteAPI {
    public SaveTeleportDB(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Save_Teleport (" +
                "`player_uuid` varchar NOT NULL," +
                "`st_name` varchar," +
                "`block` varchar," +
                "`world` varchar," +
                "`x` varchar," +
                "`y` varchar," +
                "`z` varchar," +
                "`yaw` varchar," +
                "`pitch` varchar" +
                ");";
        initialize(sql);
    }
    public ArrayList<Object[]> getData(String uuid) {
        List<Object[]> data = getDB("SELECT * FROM Save_Teleport WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Object[]> d = new ArrayList<>();
            while(rs.next()){
                Object[] objects = new Object[8];
                objects[0] = rs.getString("st_name");
                objects[1] = rs.getString("block");
                objects[2] = rs.getString("world");
                objects[3] = rs.getDouble("x");
                objects[4] = rs.getDouble("y");
                objects[5] = rs.getDouble("z");
                objects[6] = rs.getDouble("yaw");
                objects[7] = rs.getDouble("pitch");
                d.add(objects);
            }
            return d;
        });
        return  (ArrayList<Object[]>) data;
    }

    public void insertData(String uuid,String st_name,String block,String world,double x,double y,double z,double yaw,double pitch) {
        setDB("DELETE FROM Save_Teleport WHERE player_uuid = ? AND st_name = ?",Arrays.asList(uuid,st_name));
        setDB("INSERT OR IGNORE INTO Save_Teleport (player_uuid,st_name,block,world,x,y,z,yaw,pitch) VALUES(?,?,?,?,?,?,?,?,?);",
        Arrays.asList(uuid,st_name,block,world,x,y,z,yaw,pitch));
    }
    public void updateData(String uuid,String st_name1,String st_name2,String block,String world,double x,double y,double z,double yaw,double pitch){
        setDB("UPDATE Save_Teleport SET st_name = ?, block = ?, world = ?, x = ?, y = ?, z = ?, yaw = ?, pitch = ? WHERE player_uuid = ? AND st_name = ?",Arrays.asList(st_name2,block,world,x,y,z,yaw,pitch,uuid,st_name1));
    }

    public void deleteData(String uuid,String st_name){
        setDB("DELETE FROM Save_Teleport WHERE player_uuid = ? AND st_name = ?",Arrays.asList(uuid,st_name));
    }
}
