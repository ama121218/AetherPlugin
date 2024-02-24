package net.oriserver.aether.aether.sqlite;

import net.oriserver.aether.aether.sqlite.chartDB.ChartCheckPointDB;
import net.oriserver.aether.aether.sqlite.chartDB.ChartRankingDB;
import net.oriserver.aether.aether.sqlite.adminDB.SaveCommandDB;
import net.oriserver.aether.aether.sqlite.adminDB.SaveInventoryDB;
import net.oriserver.aether.aether.sqlite.adminDB.SaveItemDB;
import net.oriserver.aether.aether.sqlite.adminDB.SaveTeleportDB;
import net.oriserver.aether.aether.sqlite.chartDB.ChartDBManagerP;
import net.oriserver.aether.aether.sqlite.chartDB.ChartStageDB;
import net.oriserver.aether.aether.sqlite.playerDB.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQLiteManager {
    private final PlayerDBManagerJQ playerDBManagerJQ;
    private final PlayerDBManagerR playerDBManagerR;
    private final ChartDBManagerP chartDBManagerP;
    private final PlayerDBManagerSetting playerDBManagerSetting;
    private final PlayerDBManagerUUID playerDBManagerUUID;
    private final PhoneSetting phoneSetting;
    private final HeadBlockDB headBlockDB;
    private final SaveTeleportDB saveTeleportDB;
    private final SaveItemDB saveItemDB;
    private final SaveInventoryDB saveInventoryDB;
    private final SaveCommandDB saveCommandDB;
    private final ChartRankingDB chartRankingDB;
    private final ChartCheckPointDB chartCheckPointDB;
    private final ChartStageDB chartStageDB;

    @Autowired
    public SQLiteManager(JavaPlugin plugin){
        playerDBManagerJQ = new PlayerDBManagerJQ(plugin,"Player_data_JQ");
        playerDBManagerR = new PlayerDBManagerR(plugin,"Player_data_R");
        chartDBManagerP = new ChartDBManagerP(plugin,"Chart_Data_Player");
        playerDBManagerSetting = new PlayerDBManagerSetting(plugin,"Player_Setting");
        playerDBManagerUUID = new PlayerDBManagerUUID(plugin,"Player_UUID");
        phoneSetting = new PhoneSetting(plugin,"Phone_Setting");
        headBlockDB = new HeadBlockDB(plugin,"HeadBlock");
        saveTeleportDB = new SaveTeleportDB(plugin,"Save_Teleport");
        saveItemDB = new SaveItemDB(plugin,"Save_Item");
        chartRankingDB = new ChartRankingDB(plugin,"Chart_Ranking");
        saveInventoryDB = new SaveInventoryDB(plugin,"Save_Inventory");
        saveCommandDB = new SaveCommandDB(plugin,"Save_Command");
        chartCheckPointDB = new ChartCheckPointDB(plugin, "ChartCheckPoint");
        chartStageDB = new ChartStageDB(plugin, "ChartStage");
    }

    public PlayerDBManagerJQ getPlayerDBManagerJQ(){return playerDBManagerJQ;}
    public PlayerDBManagerR getPlayerDBManagerR(){return playerDBManagerR;}
    public ChartDBManagerP getChartDBManagerP(){return chartDBManagerP; }
    public PlayerDBManagerSetting getPlayerDBManagerSetting(){return playerDBManagerSetting;}
    public PlayerDBManagerUUID getPlayerDBManagerUUID(){return playerDBManagerUUID;}
    public PhoneSetting getPhoneSetting(){return phoneSetting;}
    public HeadBlockDB getPlayerDBManagerHeadBlock(){return headBlockDB;}
    public SaveTeleportDB getSaveTeleportDB(){return saveTeleportDB;}
    public SaveItemDB getSaveItemDB(){return saveItemDB;}
    public SaveCommandDB getSaveCommandDB(){return saveCommandDB;}
    public ChartRankingDB getChartRankingDB(){return chartRankingDB;}
    public SaveInventoryDB getSaveInventoryDB(){return saveInventoryDB;}
    public ChartCheckPointDB getChartCheckPointDB(){return chartCheckPointDB;}
    public ChartStageDB getChartStageDB(){return chartStageDB;}
}
