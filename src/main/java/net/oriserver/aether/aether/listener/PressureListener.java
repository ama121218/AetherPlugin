package net.oriserver.aether.aether.listener;

import java.util.AbstractMap.SimpleEntry;
import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.listener.pressurelocation.chart.ChartGoalTPLocation;
import net.oriserver.aether.aether.statics.CommonMethods;
import net.oriserver.aether.aether.hologram.Hologram;
import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.inventory.chart.ChartLocation;
import net.oriserver.aether.aether.inventory.chart.ChartTimeStandard;
import net.oriserver.aether.aether.inventory.level.LevelLocation;
import net.oriserver.aether.aether.listener.pressurelocation.*;
import net.oriserver.aether.aether.listener.pressurelocation.chart.ChartGoalLocation;
import net.oriserver.aether.aether.listener.pressurelocation.chart.ChartStartLocation;
import net.oriserver.aether.aether.listener.pressurelocation.chart.ChartStartTPLocation;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.sqlite.ChartDBManagerP;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PressureListener implements Listener {

    final private SQLiteManager sqLiteManager;
    final private PlayerManager pm;
    final private Plugin plugin;
    final private LevelGoalLocation levelGoalLocation;
    final private ChartGoalLocation chartGoalLocation;
    final private ChartGoalTPLocation chartGoalTPLocation;
    final private ChartStartLocation chartStartLocation;
    final private ChartStartTPLocation chartStartTPLocation;
    final private GlobalGoalLocation globalGoalLocation;
    final private Hologram hologram;

    private final HashSet<String> goalCoolTime = new HashSet<String>();
    private final HashMap<String, ChartInfo> saveChartStageTime = new HashMap<String,ChartInfo>();
    private final ItemStack prismarine = Item.createitem(Material.PRISMARINE_SHARD,1,ChatColor.GREEN +"time_reset","");



    public PressureListener(PlayerManager pm, SQLiteManager sqLiteManager, Hologram hologram, Plugin plugin) {
        this.pm = pm;
        this.sqLiteManager = sqLiteManager;
        this.plugin = plugin;
        this.levelGoalLocation = new LevelGoalLocation();
        this.chartGoalLocation = new ChartGoalLocation();
        this.chartGoalTPLocation = new ChartGoalTPLocation();
        this.chartStartLocation = new ChartStartLocation();
        this.chartStartTPLocation = new ChartStartTPLocation();
        this.globalGoalLocation = new GlobalGoalLocation();

        this.hologram = hologram;
    }

    @EventHandler
    public void Pressure(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getAction() == Action.PHYSICAL) {
            if (e.getClickedBlock().getType() == Material.GOLD_PLATE) {
                if (!isCoolTimeGoal(player.getName())) {
                    return;
                }
                if(player.getLocation().getWorld().equals(Bukkit.getWorld("shrine"))) {
                    int level = levelGoalLocation.getLevel(e.getClickedBlock().getLocation());
                    if(level==-1)return;
                    handleLevelGoal(player,level);
                }
                else if(player.getLocation().getWorld().equals(Bukkit.getWorld("chart"))) {
                    int chart = chartGoalLocation.getChart(e.getClickedBlock().getLocation());
                    player.sendMessage(""+chart);
                    if(chart==-1){
                        player.sendMessage(""+e.getClickedBlock().getLocation().getX()+","+e.getClickedBlock().getLocation().getY()+","+e.getClickedBlock().getLocation().getZ());
                        Bukkit.getServer().getLogger().info(""+e.getClickedBlock().getLocation().getX()+","+e.getClickedBlock().getLocation().getY()+","+e.getClickedBlock().getLocation().getZ());
                        return;
                    }
                    handleChartGoal(player,chart);
                }
                else if(player.getLocation().getWorld().equals(Bukkit.getWorld("global"))){
                    handleGlobalGoal(player);
                }
            } else if (e.getClickedBlock().getType() == Material.STONE_PLATE) {
                if(player.getLocation().getWorld().equals(Bukkit.getWorld("chart"))) {
                    int chart = chartStartLocation.getChart(e.getClickedBlock().getLocation());
                    if(chart==-1){
                        player.sendMessage(""+e.getClickedBlock().getLocation().getX()+","+e.getClickedBlock().getLocation().getY()+","+e.getClickedBlock().getLocation().getZ());
                        Bukkit.getServer().getLogger().info(""+e.getClickedBlock().getLocation().getX()+","+e.getClickedBlock().getLocation().getY()+","+e.getClickedBlock().getLocation().getZ());
                        return;
                    }
                    handleChartStart(player,chart);
                }
            }
        }
    }

    public void handleLevelGoal(Player p,int level){
        String uuid = String.valueOf(p.getUniqueId());
        CommonMethods.setTeleport(p,LevelLocation.getLevelLocation(level+1),"Level_"+(level+1),pm.getPlayer(uuid));
        PlayerStats playerStats = pm.getPlayer(uuid);
        int player_level = playerStats.getLevel();
        if (level - player_level == 1) {
            playerStats.setLevel(level);
            sqLiteManager.getPlayerDBManagerR().setPlayerLevel(uuid,level);
        }
        p.sendMessage(ChatColor.BOLD+"Level Athletic: "+level+" をクリアしました。");
        CommonMethods.setTeleport(p,LevelLocation.getLevelLocation(level+1),"Level_"+(level+1),pm.getPlayer(uuid));
    }

    public void handleChartStart(Player p,int chart){
        p.sendMessage(ChatColor.BOLD+"測定を開始します");
        saveChartStageTime.put(p.getName(),new ChartInfo(chart,System.currentTimeMillis()));

        p.teleport(chartStartTPLocation.getLocation(chart));
        p.getInventory().addItem(prismarine);
    }
    public void resetChartStart(Player p){
        if(saveChartStageTime.get(p.getName())==null){
            p.sendMessage(ChatColor.DARK_RED+"測定が開始されていないためリセットできません");
            return;
        }
        p.getInventory().remove(Material.PRISMARINE_SHARD);
        p.sendMessage(ChatColor.BOLD+"測定をリセットします");
        int chart = saveChartStageTime.get(p.getName()).getStageID();
        saveChartStageTime.put(p.getName(),new ChartInfo(chart,System.currentTimeMillis()));
        p.teleport(chartStartTPLocation.getLocation(chart));
        p.getInventory().addItem(prismarine);
    }
    public String getChartNumber(int number){
        return "Chart"+((number - 1) / 14 + 1)+"_"+((number - 1) % 14 + 1);
    }

    public void handleChartGoal(Player p,int chart){
        p.getInventory().remove(Material.PRISMARINE_SHARD);
        String uuid = String.valueOf(p.getUniqueId());
        CommonMethods.setTeleport(p,chartGoalTPLocation.getLocation(chart),"Chart_Lobby", pm.getPlayer(uuid));
        PlayerStats playerStats = pm.getPlayer(uuid);
        if(chart-playerStats.getChart()==1){
            playerStats.setChart(chart);
            sqLiteManager.getPlayerDBManagerR().setPlayerChart(uuid,chart);
        }
        if(saveChartStageTime.get(p.getName()) == null) {
            p.sendMessage(ChartLocation.getChartName(chart) + "をクリアしました。 タイム : 測定不能");
            return;
        }
        int stage_id = saveChartStageTime.get(p.getName()).getStageID();
        String stage_name = ChartLocation.getChartName(chart);

        Long this_time = System.currentTimeMillis() - saveChartStageTime.get(p.getName()).getStartTime();
        ChartDBManagerP chartDBManagerP = sqLiteManager.getChartDBManagerP();
        ArrayList<Object> list = chartDBManagerP.getData(uuid,stage_id);

        Long past_time;
        int past_star;
        int this_star = getStar(stage_id,this_time);

        if(list.size()==0){
            chartDBManagerP.setgoal(uuid,stage_id,1,this_time);
            past_time = 0L;
            past_star = 0;
            pm.getPlayer(uuid).setStar(pm.getPlayer(uuid).getStar()+this_star);
            sqLiteManager.getPlayerDBManagerR().setStar(uuid,sqLiteManager.getPlayerDBManagerR().getStar(uuid)+this_star);
        }else{
            past_time = (Long)list.get(0);
            int clear_count = (int)list.get(1);
            past_star = getStar(stage_id,past_time);
            if(this_time<past_time){
                chartDBManagerP.setgoal(uuid,stage_id,clear_count+1,this_time);
                int star = this_star-past_star;
                if(star>0){
                    pm.getPlayer(uuid).setStar(pm.getPlayer(uuid).getStar()+star);
                    sqLiteManager.getPlayerDBManagerR().setStar(uuid,sqLiteManager.getPlayerDBManagerR().getStar(uuid)+star);
                }
            }else{
                chartDBManagerP.setgoal(uuid,stage_id,clear_count+1);
                printChartClear(p,stage_name,past_time,this_time,past_star,this_star,-1);
                saveChartStageTime.remove(p.getName());
                return;
            }
        }
        int ranking = sqLiteManager.getChartRankingDB().insertOrUpdateScoreIfTop5(stage_id,uuid,p.getName(),this_time);
        p.sendMessage("ranking"+ranking);
        printChartClear(p,stage_name,past_time,this_time,past_star,this_star,ranking);
        saveChartStageTime.remove(p.getName());
        if(ranking!=-1&&ranking<=5){
            hologram.setChartTime(stage_id);
        }
        CommonMethods.setTeleport(p,chartGoalTPLocation.getLocation(chart),"Chart_Lobby", pm.getPlayer(uuid));
    }
    public void printChartClear(Player p,String stage_name,Long past_time,Long this_time,int past_star,int this_star,int ranking){
        p.sendMessage("     "+stage_name);
        p.sendMessage(ChatColor.GOLD+"===========================");
        p.sendMessage(ChatColor.GOLD+"past_time: "+(past_time==0L ? "--:--:---":ChartTimeStandard.getStringTime(past_time))+" "+ChartTimeStandard.getStarString(past_star));
        p.sendMessage(ChatColor.GOLD+"this_time: "+ChartTimeStandard.getStringTime(this_time)+" "+ChartTimeStandard.getStarString(this_star));
        if(this_star-past_star>0)p.sendMessage(ChatColor.YELLOW+"✦"+ChatColor.WHITE+"を"+(this_star-past_star)+"個獲得しました");
        if(ranking!=-1&&ranking<=5)p.sendMessage(ranking+"位にランクインしました");
        p.sendMessage(ChatColor.GOLD+"===========================");
    }
    public int getStar(int stage_id,Long time){
        return ChartTimeStandard.getStarRating(stage_id,time);
    }

    public void handleGlobalGoal(Player p){

    }


    public boolean isCoolTimeGoal(String player_name){
        if(!goalCoolTime.contains(player_name)){
            setCoolTimeGoal(player_name);
            return true;
        }else{
            return false;
        }
    }
    public void setCoolTimeGoal(String player_name){
        goalCoolTime.add(player_name);
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                goalCoolTime.remove(player_name);
            }
        };
        task.runTaskLater(plugin,40);
    }

    public class ChartInfo{
        private int stageID;
        private Long startTime;
        private int checkPoint;
        ChartInfo(int stageID,Long startTime){
            this.stageID = stageID;
            this.startTime = startTime;
            this.checkPoint = 0;
        }
        public int getStageID(){
            return this.stageID;
        }
        public Long getStartTime(){
            return this.getStartTime();
        }
        public int getCheckPoint(){
            return checkPoint;
        }
        public void setCheckPoint(int checkPoint){
            this.checkPoint = checkPoint;
        }
    }
}
