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
import net.oriserver.aether.aether.sqlite.chartDB.ChartStageDB;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ChartManager {//チャートアスレチックのゲームシステムを管理するクラス

    private final ChartHologram chartHologram;
    private final ChartStageInfo chartStageInfo;

    @Autowired
    public ChartManager(JavaPlugin plugin, PlayerManager playerManager, SQLiteManager sqLiteManager){
        new TemporaryData(sqLiteManager.getChartStageDB());
        this.chartStageInfo = new ChartStageInfo(sqLiteManager,this);
        chartHologram = new ChartHologram(sqLiteManager,chartStageInfo);
        ChartStageCreateManager createChartStageManager = new ChartStageCreateManager(plugin,sqLiteManager,chartStageInfo,chartHologram);
        ChartInventory chartInventory = new ChartInventory(playerManager,chartStageInfo);
        ChartStageInventory chartStageInventory = new ChartStageInventory(chartStageInfo);
        ChartGame chartGame = new ChartGame(chartStageInfo,chartHologram,playerManager);
        plugin.getCommand("chart").setExecutor(new ChartStage(createChartStageManager,chartStageInventory));
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new ChartInventoryClickListener(playerManager,chartInventory,chartStageInfo),plugin);
        pluginManager.registerEvents(new ChartItemClickListener(chartInventory),plugin);
        pluginManager.registerEvents(chartGame,plugin);
        pluginManager.registerEvents(new ChartStageInventoryClick(chartStageInventory,chartStageInfo),plugin);
    }
    public ChartStageInfo getChartStageInfo(){return this.chartStageInfo;}
    public ChartHologram getChartHologram(){return this.chartHologram;}
}
