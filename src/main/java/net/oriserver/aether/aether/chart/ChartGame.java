package net.oriserver.aether.aether.chart;


import net.oriserver.aether.aether.chart.events.*;
import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.chart.hologram.ChartHologram;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.sqlite.chartDB.ChartPlayerDataDB;
import net.oriserver.aether.aether.statics.CommonMethods;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class ChartGame implements Listener {//プレイヤーイベントからチャートアスレチックのスタート、タイム測定、ゴールを管理するクラス

    private final ChartStageInfo chartStageInfo;
    private final ChartHologram hologram;
    private final PlayerManager playerManager;
    private final HashMap<String, ChartInfo> saveChartStageTime = new HashMap<>();
    private final ItemStack prismarine = Item.createitem(Material.PRISMARINE_SHARD,1, ChatColor.GREEN +"time_reset","");

    public ChartGame(ChartStageInfo chartStageInfo, ChartHologram hologram, PlayerManager playerManager){
        this.chartStageInfo = chartStageInfo;
        this.hologram = hologram;
        this.playerManager = playerManager;
    }

    public void handleChartStart(Player p,int chart){
        Location location = chartStageInfo.getStartLocation(chart);
        if(location==null){
            p.sendMessage("ステージがありません");
            return;
        }
        p.sendMessage(ChatColor.BOLD+"測定を開始します");
        saveChartStageTime.put(p.getUniqueId().toString(),new ChartInfo(chart,System.currentTimeMillis(),location));
        saveChartStageTime.get(p.getUniqueId().toString()).setCheckPoint(location.clone());
        CommonMethods.setTeleport(p,location,""+chart,playerManager.getPlayer(p.getUniqueId().toString()));
        p.getInventory().addItem(prismarine);

    }

    @EventHandler
    public void handleChartStageTP(ChartStageTPEvent e){
        Player p = e.getPlayer();
        int chart = chartStageInfo.getStage(e.getS_Location());
        if(chart==-1){
            chart = chartStageInfo.getBackStage(e.getS_Location());
            if(chart==-1)return;
            Location location = chartStageInfo.getBackStageTP(chart);
            if(location==null){p.sendMessage("ステージがありません");return;}
            CommonMethods.setTeleport(p,location,"Chart Lobby",playerManager.getPlayer(p.getUniqueId().toString()));
            p.sendMessage("Chart World にテレポートしました");
        }else{
            Location location = chartStageInfo.getStageTP(chart);
            if(location==null){p.sendMessage("ステージがありません");return;}
            CommonMethods.setTeleport(p,location,"Chart_"+chart,playerManager.getPlayer(p.getUniqueId().toString()));
            p.sendMessage("Chart_"+chart+"にテレポートしました");
        }
    }

    @EventHandler
    public void resetChartStart(ChartTimeResetEvent e){
        Player p = e.getPlayer();
        if(!saveChartStageTime.containsKey(p.getUniqueId().toString())){
            p.sendMessage(ChatColor.DARK_RED+"測定が開始されていないためリセットできません");
            return;
        }
        p.getInventory().remove(Material.PRISMARINE_SHARD);
        p.sendMessage(ChatColor.BOLD+"測定をリセットします");
        int chart = saveChartStageTime.get(p.getUniqueId().toString()).getStageID();
        Location location = chartStageInfo.getStartLocation(chart);
        if(location==null){
            p.sendMessage("ステージがありません");
            return;
        }
        saveChartStageTime.put(p.getUniqueId().toString(),new ChartInfo(chart,System.currentTimeMillis(),location));
        CommonMethods.setTeleport(p,location,""+chart,playerManager.getPlayer(p.getUniqueId().toString()));
        p.getInventory().addItem(prismarine);
    }

    @EventHandler
    public void handleChartCheckpoint(ChartCheckPointEvent e){
        Player p = e.getPlayer();
        String string_location = e.getS_Location();

        String string_checkpoint_index = chartStageInfo.getCheckPoint(string_location);
        if(string_checkpoint_index.equals("")){p.sendMessage("チェックポイントがありません");return;}
        Location location = chartStageInfo.getCheckPointLocation(string_checkpoint_index);
        if(location == null){p.sendMessage("チェックポイントがありません");return;}

        if(!saveChartStageTime.containsKey(p.getUniqueId().toString())){
            saveChartStageTime.put(p.getUniqueId().toString(),new ChartInfo(Integer.parseInt(string_checkpoint_index.split("_")[0]),null,location));
            saveChartStageTime.get(p.getUniqueId().toString()).setStringCheckPointIndex(string_checkpoint_index);
            p.sendMessage(ChatColor.YELLOW+"チェックポイントを設定しました("+(Integer.parseInt(string_checkpoint_index.split("_")[1]))+"/"+chartStageInfo.getCheckPointAmount(Integer.parseInt(string_checkpoint_index.split("_")[0]))+")");
            return;
        }

        ChartInfo chartInfo = saveChartStageTime.get(p.getUniqueId().toString());
        if(chartInfo.getStringCheckPointIndex().equals(string_checkpoint_index))return;

        chartInfo.setCheckPoint(location.clone());
        chartInfo.setStringCheckPointIndex(string_checkpoint_index);

        p.sendMessage(ChatColor.YELLOW+"チェックポイントを設定しました("+(Integer.parseInt(string_checkpoint_index.split("_")[1]))+"/"+chartStageInfo.getCheckPointAmount(chartInfo.getStageID())+")");

    }
    @EventHandler
    public void handleChartCheckpointTP(ChartCheckPointTPEvent e){
        Player p = e.getPlayer();
        if(!saveChartStageTime.containsKey(p.getUniqueId().toString())){
            p.sendMessage(ChatColor.DARK_RED+"測定が開始されていない、またはチェックポイントが設定されていないため戻れません");
            return;
        }
        ChartInfo chartInfo = saveChartStageTime.get(p.getUniqueId().toString());
        Location tp_location = chartInfo.getCheckPoint();
        tp_location.setYaw(p.getLocation().getYaw());
        tp_location.setPitch(p.getLocation().getPitch());
        p.teleport(tp_location);
    }

    @EventHandler
    public void handleChartStartGoal(ChartStartGoalEvent e){
        int chart = chartStageInfo.getStartStage(e.getLocation());
        if(chart!=-1){
            handleChartStart(e.getPlayer(),chart);
        }else{
            chart = chartStageInfo.getGoalStage(e.getLocation());
            if(chart==-1)return;
            handleChartGoal(e.getPlayer(),chart);
        }
    }

    public void handleChartGoal(Player p,int chart){
        p.getInventory().remove(Material.PRISMARINE_SHARD);
        String uuid = p.getUniqueId().toString();
        PlayerStats playerStats = playerManager.getPlayer(uuid);
        if(playerStats.isChartagainonoff()) CommonMethods.setTeleport(p,chartStageInfo.getStageTP(chart),"Chart_"+chart, playerManager.getPlayer(uuid));
        else CommonMethods.setTeleport(p,chartStageInfo.getGoalLocation(chart),"Chart_Lobby", playerManager.getPlayer(uuid));


        if(chart-playerStats.getChart()==1){
            playerStats.setChart(chart);
            playerManager.getSqLiteManager().getPlayerRealTimeDataDB().setPlayerChart(uuid,chart);
        }

        ChartInfo chartInfo = saveChartStageTime.get(p.getUniqueId().toString());
        if(chartInfo == null || chartInfo.getStartTime() == null) {
            ChartPlayerDataDB chartPlayerDataDB = playerManager.getSqLiteManager().getChartPlayerDataDB();
            p.sendMessage(chartStageInfo.getStageName(chart) + "をクリアしました。 タイム : 測定不能");
            chartPlayerDataDB.setgoalcount1(uuid,chart);
            saveChartStageTime.remove(p.getUniqueId().toString());
            return;
        }

        int stage_id = chartInfo.getStageID();
        String stage_name = chartStageInfo.getStageName(chart);

        Long this_time = System.currentTimeMillis() - chartInfo.getStartTime();
        ChartPlayerDataDB chartPlayerDataDB = playerManager.getSqLiteManager().getChartPlayerDataDB();
        ArrayList<Object> list = chartPlayerDataDB.getData(uuid,stage_id);

        Long past_time;
        int past_star;
        int this_star = getStar(stage_id,this_time);

        if(list.size()==0){
            chartPlayerDataDB.setgoal(uuid,stage_id,1,this_time);
            past_time = 0L;
            past_star = 0;
            playerManager.getPlayer(uuid).setStar(playerManager.getPlayer(uuid).getStar()+this_star);
            playerManager.getSqLiteManager().getPlayerRealTimeDataDB().setStar(uuid,playerManager.getSqLiteManager().getPlayerRealTimeDataDB().getStar(uuid)+this_star);
        }else{
            past_time = (Long)list.get(0);
            int clear_count = (int)list.get(1);
            past_star = getStar(stage_id,past_time);
            if(this_time<past_time){
                chartPlayerDataDB.setgoal(uuid,stage_id,clear_count+1,this_time);
                int star = this_star-past_star;
                if(star>0){
                    playerManager.getPlayer(uuid).setStar(playerManager.getPlayer(uuid).getStar()+star);
                    playerManager.getSqLiteManager().getPlayerRealTimeDataDB().setStar(uuid,playerManager.getSqLiteManager().getPlayerRealTimeDataDB().getStar(uuid)+star);
                }
            }else{
                chartPlayerDataDB.setgoal(uuid,stage_id,clear_count+1);
                printChartClear(p,stage_name,past_time,this_time,past_star,this_star,-1);
                saveChartStageTime.remove(p.getUniqueId().toString());
                return;
            }
        }
        int ranking = playerManager.getSqLiteManager().getChartRankingDB().insertOrUpdateScoreIfTop5(stage_id,p.getUniqueId().toString(),p.getName(),this_time);
        p.sendMessage("ranking"+ranking);
        printChartClear(p,stage_name,past_time,this_time,past_star,this_star,ranking);
        saveChartStageTime.remove(p.getUniqueId().toString());
        if(ranking!=-1&&ranking<=5){
            hologram.setChartTime(stage_id);
        }

        if(playerStats.isChartagainonoff()) CommonMethods.setTeleport(p,chartStageInfo.getStageTP(chart),"Chart_"+chart, playerManager.getPlayer(uuid));
        else CommonMethods.setTeleport(p,chartStageInfo.getGoalLocation(chart),"Chart_Lobby", playerManager.getPlayer(uuid));

    }

    public void printChartClear(Player p,String stage_name,Long past_time,Long this_time,int past_star,int this_star,int ranking){
        p.sendMessage(ChatColor.GOLD+"===========================");
        p.sendMessage(ChatColor.GOLD+"past_time: "+(past_time==0L ? "--:--:---":chartStageInfo.getStringTime(past_time))+" "+chartStageInfo.getStarString(past_star));
        p.sendMessage(ChatColor.GOLD+"this_time: "+chartStageInfo.getStringTime(this_time)+" "+chartStageInfo.getStarString(this_star));
        if(this_star-past_star>0)p.sendMessage(ChatColor.YELLOW+"✦"+ChatColor.WHITE+"を"+(this_star-past_star)+"個獲得しました");
        if(ranking!=-1&&ranking<=5)p.sendMessage(ranking+"位にランクインしました");
        p.sendMessage(ChatColor.GOLD+"===========================");
    }



    public int getStar(int stage_id,Long time){
        return chartStageInfo.getStarRating(stage_id,time);
    }

    public class ChartInfo{
        private int stageID;
        private Long startTime;
        private Location checkPoint;
        private String stageID_checkpoint;
        ChartInfo(int stageID,Long startTime,Location start_location){
            this.stageID = stageID;
            this.startTime = startTime;
            this.checkPoint = start_location.clone();
            stageID_checkpoint = stageID + "_0";
        }
        public int getStageID(){
            return stageID;
        }
        public Long getStartTime(){
            return startTime;
        }
        public Location getCheckPoint(){
            return checkPoint;
        }
        public String getStringCheckPointIndex(){return stageID_checkpoint;}
        public void setStringCheckPointIndex(String stageID_checkpoint){this.stageID_checkpoint = stageID_checkpoint;}
        public void setCheckPoint(Location checkPoint){
            this.checkPoint = checkPoint.clone();
        }
    }
}
