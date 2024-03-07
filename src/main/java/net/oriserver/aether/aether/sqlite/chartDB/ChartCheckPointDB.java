package net.oriserver.aether.aether.sqlite.chartDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class ChartCheckPointDB extends SQLiteAPI {//ステージごとのチェックポイントを扱うクラス

    public ChartCheckPointDB(JavaPlugin plugin, String dbname){
        super(plugin, dbname);
        String sql = "CREATE TABLE IF NOT EXISTS ChartCheckPoint (" +
                "`stage_id` varchar NOT NULL," +
                "`point` INT NOT NULL," +
                "`x` varchar NOT NULL," +
                "`y` varchar NOT NULL," +
                "`z` varchar NOT NULL," +
                "PRIMARY KEY (`stage_id`, `point`)" +
                ");";
        initialize(sql);
    }
}
