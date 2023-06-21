package net.oriserver.aether.aether.sqlite;

import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartDBManagerP extends SQLiteManager{
    public ChartDBManagerP(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Chart_Data_Player (" +
                "`player_uuid` varchar NOT NULL," +
                "`stage_id` int NOT NULL," +
                "`chart_time` int NOT NULL," +
                "`clear_count` int NOT NULL," +
                "`date`int NOT NULL," +
                ");";
        initialize(sql);
    }

    public void getpasttime(String uuid,int stage_id){
        List<Integer> pasttime = getDB("SELECT name FROM Chart_Data_Player WHERE player_uuid = ? AND stage_id = ?", Arrays.asList(uuid,stage_id), rs -> {
            List<Integer> pt = new ArrayList<>();
            while(rs.next()){
                pt.add(rs.getInt("chart_time"));
            }
            return pt;
        });
    }
    public int getclear_count(String uuid, int stage_id){
        List<Integer> clear_count = getDB("SELECT chart_count FROM Chart_Data_Player WHERE player_uuid = ? AND stage_id = ?", Arrays.asList(uuid,stage_id), rs -> {
            List<Integer> clear_count = new ArrayList<>();
            while(rs.next()){
                clear_count.add(rs.getInt("chart_count"));
            }
            return clear_count;
        });
        return stage_id;
    }
    public void setgoal(String uuid,int stage_id,Long chart_time){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = now.format(formatter);
        int clear_count = getclear_count(uuid,stage_id);
        setDB("UPDATE Chart_Data_Player SET chart_time = ? WHERE player_uuid = ? AND stage_id = ?",Arrays.asList(chart_time,uuid,stage_id));
        setDB("INSERT OR IGNORE INTO Chart_Data_Player (player_uuid,stage_id,chart_time,clear_count,date) VALUES(?,?,?,?,?)",Arrays.asList(uuid,stage_id,chart_time,clear_count,date));
    }





}
