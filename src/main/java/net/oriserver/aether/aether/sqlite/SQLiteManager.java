package net.oriserver.aether.aether.sqlite;

import net.oriserver.aether.aether.sqlite.chartDB.ChartCheckPointDB;
import net.oriserver.aether.aether.sqlite.chartDB.ChartRankingDB;
import net.oriserver.aether.aether.sqlite.adminDB.SaveCommandDB;
import net.oriserver.aether.aether.sqlite.adminDB.SaveInventoryDB;
import net.oriserver.aether.aether.sqlite.adminDB.SaveItemDB;
import net.oriserver.aether.aether.sqlite.adminDB.SaveTeleportDB;
import net.oriserver.aether.aether.sqlite.chartDB.ChartPlayerDataDB;
import net.oriserver.aether.aether.sqlite.chartDB.ChartStageDB;
import net.oriserver.aether.aether.sqlite.playerDB.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQLiteManager {//データベースを扱うクラスのインスタンスをまとめるクラス
    private final PlayerJoinQuitDataDB playerJoinQuitDataDB;
    private final PlayerRealTimeDataDB playerRealTimeDataDB;
    private final ChartPlayerDataDB chartPlayerDataDB;
    private final PlayerSettingDB playerSettingDB;
    private final PlayerUUIDDB playerUUIDDB;
    private final PlayerPhoneSetting playerPhoneSetting;
    private final PlayerHeadBlockDB playerHeadBlockDB;
    private final SaveTeleportDB saveTeleportDB;
    private final SaveItemDB saveItemDB;
    private final SaveInventoryDB saveInventoryDB;
    private final SaveCommandDB saveCommandDB;
    private final ChartRankingDB chartRankingDB;
    private final ChartCheckPointDB chartCheckPointDB;
    private final ChartStageDB chartStageDB;

    @Autowired
    public SQLiteManager(JavaPlugin plugin){
        playerJoinQuitDataDB = new PlayerJoinQuitDataDB(plugin,"Player_data_JQ");
        playerRealTimeDataDB = new PlayerRealTimeDataDB(plugin,"Player_data_R");
        chartPlayerDataDB = new ChartPlayerDataDB(plugin,"Chart_Data_Player");
        playerSettingDB = new PlayerSettingDB(plugin,"Player_Setting");
        playerUUIDDB = new PlayerUUIDDB(plugin,"Player_UUID");
        playerPhoneSetting = new PlayerPhoneSetting(plugin,"Phone_Setting");
        playerHeadBlockDB = new PlayerHeadBlockDB(plugin,"HeadBlock");
        saveTeleportDB = new SaveTeleportDB(plugin,"Save_Teleport");
        saveItemDB = new SaveItemDB(plugin,"Save_Item");
        chartRankingDB = new ChartRankingDB(plugin,"Chart_Ranking");
        saveInventoryDB = new SaveInventoryDB(plugin,"Save_Inventory");
        saveCommandDB = new SaveCommandDB(plugin,"Save_Command");
        chartCheckPointDB = new ChartCheckPointDB(plugin, "ChartCheckPoint");
        chartStageDB = new ChartStageDB(plugin, "ChartStage");
    }

    public PlayerJoinQuitDataDB getPlayerJoinQuitDataDB(){return playerJoinQuitDataDB;}
    public PlayerRealTimeDataDB getPlayerRealTimeDataDB(){return playerRealTimeDataDB;}
    public ChartPlayerDataDB getChartPlayerDataDB(){return chartPlayerDataDB; }
    public PlayerSettingDB getPlayerSettingDB(){return playerSettingDB;}
    public PlayerUUIDDB getPlayerUUIDDB(){return playerUUIDDB;}
    public PlayerPhoneSetting getPlayerPhoneSetting(){return playerPhoneSetting;}
    public PlayerHeadBlockDB getPlayerHeadBlockDB(){return playerHeadBlockDB;}
    public SaveTeleportDB getSaveTeleportDB(){return saveTeleportDB;}
    public SaveItemDB getSaveItemDB(){return saveItemDB;}
    public SaveCommandDB getSaveCommandDB(){return saveCommandDB;}
    public ChartRankingDB getChartRankingDB(){return chartRankingDB;}
    public SaveInventoryDB getSaveInventoryDB(){return saveInventoryDB;}
    public ChartCheckPointDB getChartCheckPointDB(){return chartCheckPointDB;}
    public ChartStageDB getChartStageDB(){return chartStageDB;}
}
