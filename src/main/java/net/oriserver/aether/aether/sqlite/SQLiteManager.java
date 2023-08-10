package net.oriserver.aether.aether.sqlite;

import org.bukkit.plugin.java.JavaPlugin;

public class SQLiteManager {
    private final PlayerDBManagerJQ playerDBManagerJQ;
    private final PlayerDBManagerR playerDBManagerR;
    private final ChartDBManagerP chartDBManagerP;
    private final PlayerDBManagerSetting playerDBManagerSetting;
    private final PlayerDBManagerUUID playerDBManagerUUID;
    private final PlayerDBManagerPhone playerDBManagerPhone;

    public SQLiteManager(JavaPlugin plugin){
        playerDBManagerJQ = new PlayerDBManagerJQ(plugin,"Player_data_Q");
        playerDBManagerR = new PlayerDBManagerR(plugin,"Player_data_R");
        chartDBManagerP = new ChartDBManagerP(plugin,"Chart_Data_Player");
        playerDBManagerSetting = new PlayerDBManagerSetting(plugin,"Setting");
        playerDBManagerUUID = new PlayerDBManagerUUID(plugin,"Player_UUID");
        playerDBManagerPhone = new PlayerDBManagerPhone(plugin,"Phone_Setting");
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
    public PlayerDBManagerPhone getPlayerDBManagerPhone(){return this.playerDBManagerPhone;}
}
