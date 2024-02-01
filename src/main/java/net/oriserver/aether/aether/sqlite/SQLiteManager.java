package net.oriserver.aether.aether.sqlite;

import net.oriserver.aether.aether.chart.ChartRankingDB;
import org.bukkit.plugin.java.JavaPlugin;

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
    private final ChartRankingDB chartRankingDB;
    private final SaveInventoryDB saveInventoryDB;
    private final SaveCommandDB saveCommandDB;

    public SQLiteManager(JavaPlugin plugin){
        playerDBManagerJQ = new PlayerDBManagerJQ(plugin,"Player_data_JQ");
        playerDBManagerR = new PlayerDBManagerR(plugin,"Player_data_R");
        chartDBManagerP = new ChartDBManagerP(plugin,"Chart_Data_Player");
        playerDBManagerSetting = new PlayerDBManagerSetting(plugin,"Setting");
        playerDBManagerUUID = new PlayerDBManagerUUID(plugin,"Player_UUID");
        phoneSetting = new PhoneSetting(plugin,"Phone_Setting");
        headBlockDB = new HeadBlockDB(plugin,"HeadBlock");
        saveTeleportDB = new SaveTeleportDB(plugin,"Save_Teleport");
        saveItemDB = new SaveItemDB(plugin,"Save_Item");
        chartRankingDB = new ChartRankingDB(plugin,"Chart_Ranking");
        saveInventoryDB = new SaveInventoryDB(plugin,"Save_Inventory");
        saveCommandDB = new SaveCommandDB(plugin,"Save_Command");
    }

    public PlayerDBManagerJQ getPlayerDBManagerJQ(){
        return this.playerDBManagerJQ;
    }
    public PlayerDBManagerR getPlayerDBManagerR(){
        return this.playerDBManagerR;
    }
    public ChartDBManagerP getChartDBManagerP(){return this.chartDBManagerP; }
    public PlayerDBManagerSetting getPlayerDBManagerSetting(){return this.playerDBManagerSetting;}
    public PlayerDBManagerUUID getPlayerDBManagerUUID(){return this.playerDBManagerUUID;}
    public PhoneSetting getPhoneSetting(){return this.phoneSetting;}
    public HeadBlockDB getPlayerDBManagerHeadBlock(){return this.headBlockDB;}
    public SaveTeleportDB getSaveTeleportDB(){return this.saveTeleportDB;}
    public SaveItemDB getSaveItemDB(){return this.saveItemDB;}
    public SaveCommandDB getSaveCommandDB(){return this.saveCommandDB;}
    public ChartRankingDB getChartRankingDB(){return this.chartRankingDB;}
    public SaveInventoryDB getSaveInventoryDB(){return this.saveInventoryDB;}
}
