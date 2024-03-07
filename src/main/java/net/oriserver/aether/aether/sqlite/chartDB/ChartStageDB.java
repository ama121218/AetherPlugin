package net.oriserver.aether.aether.sqlite.chartDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class ChartStageDB extends SQLiteAPI {//Chartステージのデータを扱うクラス
    public ChartStageDB(JavaPlugin plugin, String dbname){
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS ChartStage (" +
                "`stage_id` varchar NOT NULL," +
                "`stage_color` varchar NOT NULL," +
                "`stage_name` varchar NOT NULL," +
                "`stage_x` varchar NOT NULL," +
                "`stage_y` varchar NOT NULL," +
                "`stage_z` varchar NOT NULL," +
                "`stage_tp_x` varchar NOT NULL," +
                "`stage_tp_y` varchar NOT NULL," +
                "`stage_tp_z` varchar NOT NULL," +
                "`stage_tp_yaw` varchar NOT NULL," +
                "`stage_tp_pitch` varchar NOT NULL," +
                "`back_stage_x` varchar NOT NULL," +
                "`back_stage_y` varchar NOT NULL," +
                "`back_stage_z` varchar NOT NULL," +
                "`back_stage_tp_x` varchar NOT NULL," +
                "`back_stage_tp_y` varchar NOT NULL," +
                "`back_stage_tp_z` varchar NOT NULL," +
                "`back_stage_tp_yaw` varchar NOT NULL," +
                "`back_stage_tp_pitch` varchar NOT NULL," +
                "`start_x` varchar NOT NULL," +
                "`start_y` varchar NOT NULL," +
                "`start_z` varchar NOT NULL," +
                "`start_tp_x` varchar NOT NULL," +
                "`start_tp_y` varchar NOT NULL," +
                "`start_tp_z` varchar NOT NULL," +
                "`start_tp_yaw` varchar NOT NULL," +
                "`start_tp_pitch` varchar NOT NULL," +
                "`goal_x` varchar NOT NULL," +
                "`goal_y` varchar NOT NULL," +
                "`goal_z` varchar NOT NULL," +
                "`goal_tp_x` varchar NOT NULL," +
                "`goal_tp_y` varchar NOT NULL," +
                "`goal_tp_z` varchar NOT NULL," +
                "`goal_tp_yaw` varchar NOT NULL," +
                "`goal_tp_pitch` varchar NOT NULL," +
                "`hologram_stageName_x` varchar NOT NULL," +
                "`hologram_stageName_y` varchar NOT NULL," +
                "`hologram_stageName_z` varchar NOT NULL," +
                "`hologram_time_x` varchar NOT NULL," +
                "`hologram_time_y` varchar NOT NULL," +
                "`hologram_time_z` varchar NOT NULL," +
                "`star_time_3` varchar NOT NULL," +
                "`star_time_2` varchar NOT NULL," +
                "`star_time_1` varchar NOT NULL," +
                "PRIMARY KEY (`stage_id`)" +
                ");";
        initialize(sql);
    }
}
