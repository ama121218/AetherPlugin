package net.oriserver.aether.aether.chart;

import net.oriserver.aether.aether.chart.command.ChartStage;
import net.oriserver.aether.aether.chart.hologram.ChartHologram;
import net.oriserver.aether.aether.chart.inventory.ChartInventory;
import net.oriserver.aether.aether.chart.inventory.ChartStageInventory;
import net.oriserver.aether.aether.chart.listener.ChartInventoryClickListener;
import net.oriserver.aether.aether.chart.listener.ChartItemClickListener;
import net.oriserver.aether.aether.chart.listener.ChartStageInventoryClick;
import net.oriserver.aether.aether.chart.stage.ChartStageCreateManager;
import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.chart.stage.TemporaryData;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;




public class ChartManager {

    private final JavaPlugin plugin;
    private final ChartGame chartGame;
    private final ChartStageInfo chartStageInfo;
    private final ChartInventory chartInventory;
    private final SQLiteAPI chartStageDB;
    private final SQLiteAPI chartCheckPointDB;
    private final ChartHologram chartHologram;
    private final ChartStageInventory chartStageInventory;

    private final ChartStageCreateManager createChartStageManager;

    public ChartManager(JavaPlugin plugin, PlayerManager pm,ChartRankingDB chartRankingDB){
        this.plugin = plugin;


        chartStageDB = new SQLiteAPI(plugin, "ChartStage");
        chartCheckPointDB = new SQLiteAPI(plugin,"ChartCheckPoint");

        initializeDB();

        new TemporaryData(chartStageDB);


        chartStageInfo = new ChartStageInfo(chartStageDB,chartCheckPointDB,this);
        chartHologram = new ChartHologram(chartRankingDB,chartStageInfo);
        createChartStageManager = new ChartStageCreateManager(plugin,chartStageDB,chartCheckPointDB,chartStageInfo,chartHologram);


        chartInventory = new ChartInventory(pm,chartStageInfo);
        chartStageInventory = new ChartStageInventory(chartStageInfo);

        chartGame = new ChartGame(chartStageInfo,chartHologram,pm);

        plugin.getCommand("chart").setExecutor(new ChartStage(createChartStageManager,chartStageInventory));

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new ChartInventoryClickListener(pm,chartInventory,chartStageInfo),plugin);
        pluginManager.registerEvents(new ChartItemClickListener(chartInventory),plugin);
        pluginManager.registerEvents(chartGame,plugin);
        pluginManager.registerEvents(new ChartStageInventoryClick(chartStageInventory,chartStageInfo),plugin);
    }


    public ChartStageInfo getChartStageInfo(){return this.chartStageInfo;}

    public void initializeDB(){
        chartStageDB.initialize("CREATE TABLE IF NOT EXISTS ChartStage (" +
                "`stage_id` varchar NOT NULL," +
                "`stage_name` varchar NOT NULL," +
                "`stage_tp_x` varchar NOT NULL," +
                "`stage_tp_y` varchar NOT NULL," +
                "`stage_tp_z` varchar NOT NULL," +
                "`stage_tp_yaw` varchar NOT NULL," +
                "`stage_tp_pitch` varchar NOT NULL," +
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
                "`star_time_3` varchar NOT NULL," +
                "`star_time_2` varchar NOT NULL," +
                "`star_time_1` varchar NOT NULL," +
                "PRIMARY KEY (`stage_id`)" +
                ");"
        );
        chartCheckPointDB.initialize("CREATE TABLE IF NOT EXISTS ChartCheckPoint (" +
                "`stage_id` varchar NOT NULL," +
                "`point` INT NOT NULL," +
                "`x` varchar NOT NULL," +
                "`y` varchar NOT NULL," +
                "`z` varchar NOT NULL," +
                "PRIMARY KEY (`stage_id`, `point`)" +
                ");"
        );
    }

    public ChartHologram getChartHologram(){
        return this.chartHologram;
    }
}
