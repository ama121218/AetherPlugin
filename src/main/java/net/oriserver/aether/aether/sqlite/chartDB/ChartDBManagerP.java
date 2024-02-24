package net.oriserver.aether.aether.sqlite.chartDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartDBManagerP extends SQLiteAPI {
    public ChartDBManagerP(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Chart_Data_Player (" +
                "`player_uuid` varchar NOT NULL," +
                "`stage_id` int NOT NULL," +
                "`clear_count` int NOT NULL," +
                "`chart_time` int NOT NULL," +
                "`date` int NOT NULL," +
                "PRIMARY KEY (`player_uuid`, `stage_id`)" +
                ");";
        initialize(sql);
    }



    public ArrayList<Object[]> getDatas(String uuid,int min,int max){
        List<Object[]> data = getDB("SELECT * FROM Chart_Data_Player WHERE player_uuid = ? AND stage_id BETWEEN ? AND ? ORDER BY stage_id ASC", Arrays.asList(uuid, min, max), rs -> {
            List<Object[]> pt = new ArrayList<>();
            int count = min;
            while(rs.next()){
                int stage_id = rs.getInt("stage_id");
                while (count < stage_id) {
                    pt.add(null);
                    count++;
                }
                Object[] objects = new Object[2];
                objects[0] = rs.getLong("chart_time");
                objects[1] = rs.getInt("clear_count");
                pt.add(objects);
                count++;
            }
            while (count <= max) {
                pt.add(null);
                count++;
            }
            return pt;
        });
        return (ArrayList<Object[]>) data;
    }

    public ArrayList<Object> getData(String uuid,int stage_id){
        List<Object> data = getDB("SELECT * FROM Chart_Data_Player WHERE player_uuid = ? AND stage_id = ?", Arrays.asList(uuid,stage_id), rs -> {
            List<Object> pt = new ArrayList<>();
            while(rs.next()){
                pt.add(rs.getLong("chart_time"));
                pt.add(rs.getInt("clear_count"));
            }
            return pt;
        });
        return (ArrayList<Object>) data;
    }



    public List<Integer> getclear_count(String uuid, int stage_id){
        return getDB("SELECT clear_count FROM Chart_Data_Player WHERE player_uuid = ? AND stage_id = ?", Arrays.asList(uuid,stage_id), rs -> {
            List<Integer> clear_count = new ArrayList<>();
            while(rs.next()){
                clear_count.add(rs.getInt("clear_count"));
            }
            return clear_count;
        });
    }


    public void setgoal(String uuid,int stage_id,int clear_count,Long chart_time){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = now.format(formatter);
        setDB("INSERT OR REPLACE INTO Chart_Data_Player (player_uuid,stage_id,clear_count,chart_time,date) VALUES(?,?,?,?,?)",Arrays.asList(uuid,stage_id,clear_count,chart_time,date));
    }
    public void setgoal(String uuid,int stage_id,int clear_count){
        setDB("UPDATE Chart_Data_Player SET clear_count = ? WHERE player_uuid = ? AND stage_id = ?", Arrays.asList(clear_count, uuid, stage_id));
    }

    public void setgoalcount1(String uuid,int stage_id){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = now.format(formatter);
        List<Integer> list = getclear_count(uuid,stage_id);
        if(list.size()==0){
            setDB("INSERT OR IGNORE INTO Chart_Data_Player (player_uuid,stage_id,clear_count,chart_time,date) VALUES(?,?,?,?,?)",Arrays.asList(uuid,stage_id,1,6039999,date));
        }else{
            setDB("UPDATE Chart_Data_Player SET clear_count = ? WHERE player_uuid = ? AND stage_id = ?", Arrays.asList(list.get(0)+1, uuid, stage_id));
        }
    }




}
